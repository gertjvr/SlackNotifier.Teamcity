/*
 * Copyright 2000-2014 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package gertjvr.slacknotifier;

import com.intellij.openapi.diagnostic.Logger;
import jetbrains.buildServer.serverSide.BuildServerAdapter;
import jetbrains.buildServer.serverSide.SBuildServer;
import jetbrains.buildServer.serverSide.SRunningBuild;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static gertjvr.slacknotifier.TeamCityEvent.BUILD_STARTED;

public class SlackServerExtension extends BuildServerAdapter
{
    private final static Logger logger = Logger.getInstance("slackNotifier.buildServer.PLUGINS");

    private final SBuildServer server;
    private final SlackApiProcessor processor;
    private final SlackConfiguration configuration;

    public SlackServerExtension(SBuildServer sBuildServer)
    {
        server = sBuildServer;
        processor = new SlackApiProcessor();
        configuration = new SlackConfiguration("redfrogs", "bYtmwsdB2rA1vz6PdjssoVNI");
    }

    public void register()
    {
        server.addListener(this);
        logger.debug("Server extension registered");
    }

    @Override
    public void buildStarted(@NotNull SRunningBuild sRunningBuild)
    {
        super.buildStarted(sRunningBuild);
        if (configuration.getEvents().getBuildStartedStatus())
        {
            processBuildEvent(sRunningBuild, BUILD_STARTED);
        }
    }

    @Override
    public void buildFinished(@NotNull SRunningBuild sRunningBuild)
    {
        super.buildFinished(sRunningBuild);
        if (sRunningBuild.getBuildStatus() != null && sRunningBuild.getBuildStatus().isSuccessful() && configuration.getEvents().getBuildSuccessfulStatus())
        {
            processBuildEvent(sRunningBuild, TeamCityEvent.BUILD_SUCCESSFUL);
        } else if (sRunningBuild.getBuildStatus() != null && sRunningBuild.getBuildStatus().isFailed() && configuration.getEvents().getBuildFailedStatus())
        {
            processBuildEvent(sRunningBuild, TeamCityEvent.BUILD_FAILED);
        }
    }

    @Override
    public void buildInterrupted(@NotNull SRunningBuild sRunningBuild)
    {
        super.buildInterrupted(sRunningBuild);
        if (configuration.getEvents().getBuildInterruptedStatus())
        {
            processBuildEvent(sRunningBuild, TeamCityEvent.BUILD_INTERRUPTED);
        }
    }

    @Override
    public void serverStartup()
    {
        super.serverStartup();
        if (configuration.getEvents().getServerStartupStatus())
        {
            processServerEvent(TeamCityEvent.SERVER_STARTUP);
        }
    }

    @Override
    public void serverShutdown()
    {
        super.serverShutdown();
        if (configuration.getEvents().getServerShutdownStatus())
        {
            processServerEvent(TeamCityEvent.SERVER_SHUTDOWN);
        }
    }

    private void sendNotification(List<SlackNotificationAttachment> attachments)
    {
        logger.debug(String.format("sendNotification: %s", attachments.size()));

        try
        {
            if (attachments.size() == 0)
                return;

            String apiUrl = String.format("https://%1$s.slack.com/services/hooks/incoming-webhook?token=%2$s", configuration.getOrganization(), configuration.getToken());
            logger.debug(String.format("url: %s", apiUrl));

            SlackNotificationMessage notification = new SlackNotificationMessage(
                    configuration.getDefaultChannel(),
                    configuration.getDefaultUsername(),
                    attachments);

            processor.sendNotification(
                    apiUrl,
                    notification);
        } catch (Exception ex)
        {
            logger.error(String.format("sendNotification: %s", ex.getMessage()), ex);
        }
    }

    private void processServerEvent(TeamCityEvent event)
    {
        logger.debug(String.format("processServerEvent: %s", event));

        try
        {

            String message;
            String color;
            switch (event)
            {
                case SERVER_STARTUP:
                    message = "Build server starting up.";
                    color = "good";
                    break;
                case SERVER_SHUTDOWN:
                    message = "Build server shutting down.";
                    color = "warning";
                    break;
                default:
                    return;
            }

            List<SlackNotificationAttachment> attachments = new ArrayList<SlackNotificationAttachment>();
            attachments.add(new SlackNotificationAttachment(message, color));

            sendNotification(attachments);
        } catch (Exception ex)
        {
            logger.error(String.format("processServerEvent: %s", ex.getMessage()), ex);
        }
    }

    private void processBuildEvent(SRunningBuild build, TeamCityEvent event)
    {
        logger.debug(String.format("processBuildEvent: %s", event));

        try
        {
            String fullName = (build.getBuildType() != null) ? build.getBuildType().getFullName() : null;
            String triggeredBy = build.getTriggeredBy().getAsString();
            String serverUrl = server.getRootUrl();
            String projectId = build.getProjectExternalId();
            long buildId = build.getBuildId();
            String buildTypeId = build.getBuildTypeId();
            String buildNumber = build.getBuildNumber();
            String projectUrl = String.format("<%1$s/project.html?projectId=%2$s|%3$s>", serverUrl, projectId, fullName);
            String buildUrl = String.format("<%1$s/viewLog.html?buildId=%2$s&tab=buildResultsDiv&buildTypeId=%3$s|#%4$s>", serverUrl, buildId, buildTypeId, buildNumber);

            Boolean hasBranch = (build.getBranch() != null);
            String branchDisplayName = hasBranch ? build.getBranch().getDisplayName() : null;

            List<SlackNotificationAttachment> attachments = new ArrayList<SlackNotificationAttachment>();

            switch (event)
            {
                case BUILD_STARTED:
                {
                    String text = (hasBranch)
                            ? String.format("Build %1$s on branch %2$s has started.", projectUrl, branchDisplayName)
                            : String.format("Build %s has started.", projectUrl);

                    List<SlackNotificationAttachmentField> fields = new ArrayList<SlackNotificationAttachmentField>();
                    fields.add(new SlackNotificationAttachmentField(String.format("This is build number %1$s and was triggered by %2$s.", buildUrl, triggeredBy)));

                    attachments.add(new SlackNotificationAttachment(text, "good", fields));

                    break;
                }
                case BUILD_SUCCESSFUL:
                {
                    String text = (hasBranch)
                            ? String.format("Build %1$s on branch %2$s was successful.", projectUrl, branchDisplayName)
                            : String.format("Build %s was successful.", projectUrl);

                    List<SlackNotificationAttachmentField> fields = new ArrayList<SlackNotificationAttachmentField>();
                    fields.add(new SlackNotificationAttachmentField(String.format("This is build number %1$s and was triggered by %2$s.", buildUrl, triggeredBy)));

                    attachments.add(new SlackNotificationAttachment(text, "good", fields));

                    break;
                }
                case BUILD_FAILED:
                {
                    String text = (hasBranch)
                            ? String.format("Build %1$s on branch %2$s has failed.", projectUrl, branchDisplayName)
                            : String.format("Build %s has failed.", projectUrl);

                    List<SlackNotificationAttachmentField> fields = new ArrayList<SlackNotificationAttachmentField>();
                    fields.add(new SlackNotificationAttachmentField(String.format("This is build number %1$s and was triggered by %2$s.", buildUrl, triggeredBy)));

                    attachments.add(new SlackNotificationAttachment(text, "danger", fields));

                    break;
                }
            }

            sendNotification(attachments);
        } catch (Exception ex)
        {
            logger.error(String.format("processBuildEvent: %s", ex.getMessage()), ex);
        }
    }
}
