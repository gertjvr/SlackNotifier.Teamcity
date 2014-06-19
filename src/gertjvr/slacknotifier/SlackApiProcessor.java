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

import com.google.gson.Gson;
import com.intellij.openapi.diagnostic.Logger;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.entity.ContentType;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class SlackApiProcessor
{

    private final static Logger logger = Logger.getInstance("jetbrains.buildServer.SLACK");

    public void sendNotification(String url, SlackNotificationMessage notification) throws IOException
    {
        String content = new Gson().toJson(notification);
        logger.debug(String.format("sendNotification: %s", content));

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);

        httpPost.setEntity(new StringEntity(content, ContentType.APPLICATION_JSON));
        CloseableHttpResponse response = httpClient.execute(httpPost);

        try
        {
            HttpEntity entity = response.getEntity();
            EntityUtils.consume(entity);
        } catch (Exception ex)
        {
            logger.error(String.format("sendNotification %s", ex.getMessage()), ex);
        } finally
        {
            response.close();
        }
    }
}
