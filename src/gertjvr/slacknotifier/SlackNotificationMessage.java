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

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.List;

public class SlackNotificationMessage
{

    private String channel;
    private String username;
    private String text;
    private String parse;
    private Boolean link_names;
    private Boolean unfurl_links;
    private String icon_url;
    private String icon_emoji;
    private List<SlackNotificationAttachment> attachments;

    public SlackNotificationMessage(String channel, String username, List<SlackNotificationAttachment> attachments)
    {
        this.channel = channel;
        this.username = username;
        this.text = null;
        this.parse = null;
        this.link_names = true;
        this.unfurl_links = true;
        this.icon_url = null;
        this.icon_emoji = null;
        this.attachments = attachments;
    }

    public SlackNotificationMessage(String channel, String username, String text, String parse, Boolean link_names, Boolean unfurl_links, String icon_url, String icon_emoji, List<SlackNotificationAttachment> attachments)
    {
        this.channel = channel;
        this.username = username;
        this.text = text;
        this.parse = parse;
        this.link_names = link_names;
        this.unfurl_links = unfurl_links;
        this.icon_url = icon_url;
        this.icon_emoji = icon_emoji;
        this.attachments = attachments;
    }

    public String getChannel()
    {
        return channel;
    }

    public void setChannel(String channel)
    {
        this.channel = channel;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public String getParse()
    {
        return parse;
    }

    public void setParse(String parse)
    {
        this.parse = parse;
    }

    public String getIcon_url()
    {
        return icon_url;
    }

    public void setIcon_url(String icon_url)
    {
        this.icon_url = icon_url;
    }

    public String getIcon_emoji()
    {
        return icon_emoji;
    }

    public void setIcon_emoji(String icon_emoji)
    {
        this.icon_emoji = icon_emoji;
    }

    public List<SlackNotificationAttachment> getAttachments()
    {
        return attachments;
    }

    public void setAttachments(List<SlackNotificationAttachment> attachments)
    {
        this.attachments = attachments;
    }

    public Boolean getUnfurl_links()
    {
        return unfurl_links;
    }

    public void setUnfurl_links(Boolean unfurl_links)
    {
        this.unfurl_links = unfurl_links;
    }

    public Boolean getLink_names()
    {
        return link_names;
    }

    public void setLink_names(Boolean link_names)
    {
        this.link_names = link_names;
    }
}
