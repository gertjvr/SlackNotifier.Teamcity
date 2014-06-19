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

public class SlackEventConfiguration
{

    private Boolean buildStartedStatus;
    private Boolean buildSuccessfulStatus;
    private Boolean buildFailedStatus;
    private Boolean buildInterruptedStatus;
    private Boolean serverStartupStatus;
    private Boolean serverShutdownStatus;

    public SlackEventConfiguration()
    {
        this.buildStartedStatus = true;
        this.buildSuccessfulStatus = true;
        this.buildFailedStatus = true;
        this.buildInterruptedStatus = true;
        this.serverStartupStatus = true;
        this.serverShutdownStatus = true;
    }

    public SlackEventConfiguration(Boolean buildStartedStatus, Boolean buildSuccessfulStatus, Boolean buildFailedStatus, Boolean buildInterruptedStatus, Boolean serverStartupStatus, Boolean serverShutdownStatus)
    {
        this.buildStartedStatus = buildStartedStatus;
        this.buildSuccessfulStatus = buildSuccessfulStatus;
        this.buildFailedStatus = buildFailedStatus;
        this.buildInterruptedStatus = buildInterruptedStatus;
        this.serverStartupStatus = serverStartupStatus;
        this.serverShutdownStatus = serverShutdownStatus;
    }

    public Boolean getBuildStartedStatus()
    {
        return buildStartedStatus;
    }

    public void setBuildStartedStatus(Boolean buildStartedStatus)
    {
        this.buildStartedStatus = buildStartedStatus;
    }

    public Boolean getBuildSuccessfulStatus()
    {
        return buildSuccessfulStatus;
    }

    public void setBuildSuccessfulStatus(Boolean buildSuccessfulStatus)
    {
        this.buildSuccessfulStatus = buildSuccessfulStatus;
    }

    public Boolean getBuildFailedStatus()
    {
        return buildFailedStatus;
    }

    public void setBuildFailedStatus(Boolean buildFailedStatus)
    {
        this.buildFailedStatus = buildFailedStatus;
    }

    public Boolean getBuildInterruptedStatus()
    {
        return buildInterruptedStatus;
    }

    public void setBuildInterruptedStatus(Boolean buildInterruptedStatus)
    {
        this.buildInterruptedStatus = buildInterruptedStatus;
    }

    public Boolean getServerStartupStatus()
    {
        return serverStartupStatus;
    }

    public void setServerStartupStatus(Boolean serverStartupStatus)
    {
        this.serverStartupStatus = serverStartupStatus;
    }

    public Boolean getServerShutdownStatus()
    {
        return serverShutdownStatus;
    }

    public void setServerShutdownStatus(Boolean serverShutdownStatus)
    {
        this.serverShutdownStatus = serverShutdownStatus;
    }
}
