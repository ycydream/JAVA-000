package com.feather.cloud.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class MyHttpClientUtil {

    public static void main(String[] args) {
        //请求url
        //String url = "https://www.baidu.com";
        String url = "http://localhost:8801";
        Map<String, Object> params = new HashMap<>();
        //System.out.println(MyHttpClientUtil.myPost(url, params));
        System.out.println(MyHttpClientUtil.myGet(url));
    }

    public static String myGet(String url){
        String result = null;
        //实例化HttpClient实例
        CloseableHttpClient client = HttpClients.createDefault();
        try{

            HttpGet httpGet = new HttpGet(url);
            //添加请求头
            httpGet.addHeader("Content-Type", "application/json");
            httpGet.addHeader("Accept", "application/json");
            //发起请求
            CloseableHttpResponse response = client.execute(httpGet);
            //获得请求状态码
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                //获得请求实体
                HttpEntity entity = response.getEntity();
                result = EntityUtils.toString(entity, "utf-8");
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }
    }

    public static String myPost(String url, Map<String, Object> params){
        String result = null;
        CloseableHttpClient client = HttpClients.createDefault();
        try{
            //实例化HttpClient实例
            HttpPost httpPost = new HttpPost(url);
            //添加请求头
            httpPost.addHeader("Content-Type", "application/json");
            httpPost.addHeader("Accept", "application/json");
            //请求数据
            JSONObject obj = new JSONObject(params);
            System.out.println(obj.toJSONString());
            StringEntity strEntity = new StringEntity(obj.toJSONString(), Charset.forName("UTF-8"));
            strEntity.setContentType("application/json");
            httpPost.setEntity(strEntity);
            //设置超时时间
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(80000).setConnectTimeout(80000).build();
            httpPost.setConfig(requestConfig);
            //发起请求
            CloseableHttpResponse response = client.execute(httpPost);
            //获得请求状态码
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                //获得请求实体
                HttpEntity entity = response.getEntity();
                result = EntityUtils.toString(entity, "utf-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }
    }
}
