package com.yunpan.base.tool;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author Administrator
 */
public class HttpClientUtils {

    private static final Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);

    public static String postJsonReq(String url, String reqBody) throws Exception {
        logger.info("==========>请求URL:{}内容:{}",url,reqBody);
        CloseableHttpClient httpclient = HttpClients.createDefault();
        StringEntity reqEntity = new StringEntity(reqBody,
                ContentType.create("application/json", Consts.UTF_8));
        reqEntity.setChunked(true);
        HttpPost httppost = new HttpPost(url);
        httppost.setEntity(reqEntity);
        InputStream instream = null;
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httppost);

            HttpEntity rspEntity = response.getEntity();
            if (rspEntity != null) {
                instream = rspEntity.getContent();
                String result = IOUtils.toString(instream, "utf-8");
                return result;
            }
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            httpclient.close();
        }
        return null;
    }


    public static void main(String[] args) throws Exception {
        System.out.println(postJsonReq("http://www.baidu.com", "xxx"));

    }
}
