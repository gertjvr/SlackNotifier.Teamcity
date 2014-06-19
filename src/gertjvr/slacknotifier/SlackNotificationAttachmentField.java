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

public class SlackNotificationAttachmentField
{
    private String title;
    private String value;
    private Boolean short1;

    public SlackNotificationAttachmentField(String value)
    {
        this.title = null;
        this.value = value;
        this.short1 = false;
    }

    public SlackNotificationAttachmentField(String title, String value, Boolean short1)
    {
        this.title = title;
        this.value = value;
        this.short1 = short1;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }

    public Boolean getShort()
    {
        return short1;
    }

    public void setShort(Boolean short1)
    {
        this.short1 = short1;
    }
}
