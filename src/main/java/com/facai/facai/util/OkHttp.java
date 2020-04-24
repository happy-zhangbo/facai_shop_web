package com.facai.facai.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import okhttp3.*;
import okio.BufferedSink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;


import javax.validation.constraints.NotNull;
import java.io.DataOutputStream;
import java.io.IOException;
import java.rmi.server.RemoteRef;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Handler;

public class OkHttp {
    private Logger logger = LoggerFactory.getLogger(getClass());
    public enum State {SUCCESS, FAILURE, NETWORK_FAILURE}
    private static volatile OkHttp mInstance;
    private OkHttpClient okHttpClient;

    static {
        getInstance();
    }

    public OkHttp() {
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)//10秒连接超时
                .writeTimeout(10, TimeUnit.SECONDS)//10m秒写入超时
                .readTimeout(10, TimeUnit.SECONDS)//10秒读取超时
                .build();
    }
    /** * 第一步，写一个单例，这里用的懒汉式，也可以使用饿汉 * @return */
    public static OkHttp getInstance() {
        if (mInstance == null) {
            synchronized (OkHttp.class) {
                if (null == mInstance) {
                    mInstance = new OkHttp();
                }
            }
        }
        return mInstance;
    }

    /**
     * 对外提供的GET方法，同步方式
     * @param url
     * @return
     */
    public static Response doGet(String url,Map<String,String> map){
        url += "?";
        for (String key:map.keySet()) {
            url += key+"="+map.get(key)+"&";
        }
        url = url.substring(0,url.length()-1);
        System.out.println(url);
        return mInstance.inner_DoGet(url);
    }

    /**
     * GET方式请求的内部逻辑处理，同步
     * @param url
     * @return
     */
    private Response inner_DoGet(String url){
        Request request = new Request.Builder().url(url).build();
        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();
        }catch (Exception e){
            logger.error("url:"+url+" - request error");
            e.printStackTrace();

        }
        return response;
    }

    /**
     * 对外提供的GET获取String的方法
     * @param url
     * @return
     */
    public static String doGetString(String url){
        String res = "";
        try {
            res = mInstance.inner_DoGet(url).body().string();
        }catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }

    /**
     * 对外提供的GET 异步方式
     * @param url
     * @param callback
     */
    public static void doGetAsync(String url,RestCallBack callback){
        mInstance.inner_doGetAsync(url,callback);
    }
    /**
     * GET方式请求的内部逻辑处理，异步
     * @param url
     * @return
     */
    private void inner_doGetAsync(String url,RestCallBack callback) {
        Request request = new Request.Builder().url(url).build();

        handle(url, callback, request);
    }

    /**
     * 对外提供的POST方法，同步方式
     * @param url
     * @return
     */
    public static Response doPost(String url, Map<String,String> map){

        RequestBody requestBody = mInstance.setRequestBody(map);

        return  mInstance.inner_DoPost(url,requestBody);
    }

    /**
     * 对外提供的POST获取String的方法
     * @param url
     * @return
     */
    public static String doPostString(String url, Map<String,String> map){
        String res = "";
        try {
            RequestBody requestBody = mInstance.setRequestBody(map);
            res = mInstance.inner_DoPost(url,requestBody).body().string();
        }catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }

    /**
     * 对外提供的POST获取String的方法
     * @param url
     * @return
     */
    public static String doPostString(String url, String param){
        String res = "";
        try {
            RequestBody requestBody = new RequestBody() {
                @Nullable
                @Override
                public MediaType contentType() {
                    return MediaType.parse("text/xml; charset=utf-8");
                }

                @Override
                public void writeTo(@NotNull BufferedSink bufferedSink) throws IOException {
                    DataOutputStream out = new DataOutputStream(bufferedSink.outputStream());
                    out.writeBytes(param);    //写入流
                }
            };
            res = mInstance.inner_DoPost(url,requestBody).body().string();
        }catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }

    /**
     * POST方式请求的内部逻辑处理，同步
     * @param url
     * @return
     */
    private Response inner_DoPost(String url,RequestBody requestBody){

        Request request = new Request.Builder().post(requestBody).url(url).build();

        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();
        }catch (Exception e){
            logger.error("url:"+url+" - request error");
            e.printStackTrace();

        }
        return response;
    }

    /**
     * post的请求参数，构造RequestBody
     * @param BodyParams
     * @return
     */
    private RequestBody setRequestBody(Map<String, String> BodyParams){
        RequestBody body=null;
        okhttp3.FormBody.Builder formEncodingBuilder=new okhttp3.FormBody.Builder();
        if(BodyParams != null){
            Iterator<String> iterator = BodyParams.keySet().iterator();
            String key = "";
            while (iterator.hasNext()) {
                key = iterator.next().toString();
                formEncodingBuilder.add(key, BodyParams.get(key));
                logger.info("post http -"+"post_Params==="+key+"===="+BodyParams.get(key));
            }
        }
        body=formEncodingBuilder.build();
        return body;

    }

    /**
     * 对外提供的POST 异步方式
     * @param url
     * @param callback
     */
    public static void doPostAsync(String url, Map<String,String> map,RestCallBack callback){
        mInstance.inner_doPostAsync(url,map,callback);
    }
    /**
     * POST方式请求的内部逻辑处理，异步
     * @param url
     * @return
     */
    private void inner_doPostAsync(String url,Map<String,String> map,RestCallBack callback) {
        RequestBody requestBody = setRequestBody(map);

        Request request = new Request.Builder().post(requestBody).url(url).build();

        handle(url, callback, request);
    }

    /**
     * 异步处理方式
     * @param url
     * @param callback
     * @param request
     */
    private void handle(String url, RestCallBack callback, Request request) {
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                callback.onCallBack(State.NETWORK_FAILURE,"error");
                logger.error("url:"+url+" - request error");
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    String result = response.body().string();
                    logger.info("request success - "+result);
                    callback.onCallBack(State.SUCCESS,result);
                } else {
                    logger.warn("request waring response code - "+Integer.toString(response.code()));
                    callback.onCallBack(State.FAILURE, Integer.toString(response.code()));
                }
            }
        });
    }


    /**
     * 数据回调接口
     */
    public interface RestCallBack{
        void onCallBack(State state,String result);
    }
}
