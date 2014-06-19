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

public class SlackConfiguration
{

    private String organization;
    private String token;
    private Boolean disabledStatus;
    private Boolean defaultNotifyStatus;
    private String defaultUsername;
    private String defaultChannel;
    private SlackEventConfiguration events;

    public SlackConfiguration(String organization, String token)
    {
        this.organization = organization;
        this.token = token;
        this.disabledStatus = false;
        this.defaultNotifyStatus = true;
        this.defaultUsername = "TeamCity Server";
        this.defaultChannel = null;
        this.events = new SlackEventConfiguration();
    }

    public SlackConfiguration(String organization, String token, Boolean disabledStatus, Boolean defaultNotifyStatus, String defaultUsername, String defaultChannel, SlackEventConfiguration events)
    {
        this.organization = organization;
        this.token = token;
        this.disabledStatus = disabledStatus;
        this.defaultNotifyStatus = defaultNotifyStatus;
        this.defaultUsername = defaultUsername;
        this.defaultChannel = defaultChannel;
        this.events = events;
    }

    public String getOrganization()
    {
        return organization;
    }

    public void setOrganization(String organization)
    {
        this.organization = organization;
    }

    public String getToken()
    {
        return token;
    }

    public void setToken(String token)
    {
        this.token = token;
    }

    public Boolean getDisabledStatus()
    {
        return disabledStatus;
    }

    public void setDisabledStatus(Boolean disabledStatus)
    {
        this.disabledStatus = disabledStatus;
    }

    public Boolean getDefaultNotifyStatus()
    {
        return defaultNotifyStatus;
    }

    public void setDefaultNotifyStatus(Boolean defaultNotifyStatus)
    {
        this.defaultNotifyStatus = defaultNotifyStatus;
    }

    public String getDefaultUsername()
    {
        return defaultUsername;
    }

    public void setDefaultUsername(String defaultUsername)
    {
        this.defaultUsername = defaultUsername;
    }

    public String getDefaultChannel()
    {
        return defaultChannel;
    }

    public void setDefaultChannel(String defaultChannel)
    {
        this.defaultChannel = defaultChannel;
    }

    public SlackEventConfiguration getEvents()
    {
        return events;
    }
}
