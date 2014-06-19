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

import java.util.List;

public class SlackNotificationAttachment
{

    private String fallback;
    private String text;
    private String pretext;
    private String color;
    private List<SlackNotificationAttachmentField> fields;

    public SlackNotificationAttachment(String text, String color)
    {
        this.fallback = null;
        this.text = text;
        this.pretext = null;
        this.color = color;
        this.fields = null;
    }

    public SlackNotificationAttachment(String text, String color, List<SlackNotificationAttachmentField> fields)
    {
        this.fallback = null;
        this.text = text;
        this.pretext = null;
        this.color = color;
        this.fields = fields;
    }

    public SlackNotificationAttachment(String fallback, String text, String pretext, String color, List<SlackNotificationAttachmentField> fields)
    {
        this.fallback = fallback;
        this.text = text;
        this.pretext = pretext;
        this.color = color;
        this.fields = fields;
    }

    public String getFallback()
    {
        return fallback;
    }

    public void setFallback(String fallback)
    {
        this.fallback = fallback;
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public String getPretext()
    {
        return pretext;
    }

    public void setPretext(String pretext)
    {
        this.pretext = pretext;
    }

    public String getColor()
    {
        return color;
    }

    public void setColor(String color)
    {
        this.color = color;
    }

    public List<SlackNotificationAttachmentField> getFields()
    {
        return fields;
    }

    public void setFields(List<SlackNotificationAttachmentField> fields)
    {
        this.fields = fields;
    }
}
