package com.zqy.superhttp.http;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;
import com.zqy.superhttp.SuperHttpManager;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 作者: zhangqingyou
 * 时间: 2020/9/14
 * 描述: 通用请求
 */

public class SuperHttpConnection {
    /**
     * 当前请求响应后才能继续下个请求
     * String:当前请求标识
     * Boolean：是否可请求
     */
    private static Map<String, Boolean> apiMap = new HashMap<>();

    /**
     * 设置接口是否可请求
     *
     * @param url       完整地址
     * @param isRequest 当前地址是否可请求
     */
    protected static void setIsRequest(String url, boolean isRequest) {
        apiMap.put(url, isRequest);
    }

    /**
     * 获取指定接口是否可请求
     *
     * @param url 完整地址
     * @return
     */
    private static boolean isRequest(String url) {
        boolean isRequest = true;
        if (apiMap.containsKey(url)) {
            isRequest = apiMap.get(url);
        } else {
            //默认可请求
            apiMap.put(url, true);
        }
        return isRequest;

    }
    //--------------------------------------------------基本请求 start-------------------------------------------------

    /**
     * 下载
     *
     * @param appUrl 下载地址
     */
    public static void download(String appUrl, FileCallback callback) {
        OkGo.<File>get(appUrl)
                //.tag(this)
                .execute(callback);
    }

    /**
     * json格式的请求方式
     *
     * @param url
     * @param json
     * @param callback
     */
    public static void postJson(String url, String json, BaseCallback callback) {
        if (isRequest(url)) {
            OkGo.<String>post(url)
                    .tag(url)
                    .upJson(json)
                    .execute(callback);
        } else {
            callback.onFinish("【" + url + "】还未响应，勿频繁请求！");

            if (SuperHttpManager.getApiCallbackService() != null) {
                SuperHttpManager.getApiCallbackService().onFinish("【" + url + "】还未响应，勿频繁请求！");
            }

        }

    }

    public static void get(String url, Map<String, Object> params, BaseCallback callback) {
        if (isRequest(url)) {
            Map<String, String> param = new LinkedHashMap<>();
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                if (key != null && value != null)
                    param.put(entry.getKey(), entry.getValue().toString());
            }
            OkGo.<String>get(url)
                    .tag(url)
                    .params(param)
                    .execute(callback);
        } else {
            callback.onFinish("【" + url + "】还未响应，勿频繁请求！");
            if (SuperHttpManager.getApiCallbackService() != null) {
                SuperHttpManager.getApiCallbackService().onFinish("【" + url + "】还未响应，勿频繁请求！");
            }
        }


    }

    public static void post(String url, Map<String, Object> params, BaseCallback callback) {
        if (isRequest(url)) {
            Map<String, String> param = new LinkedHashMap<>();
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                if (key != null && value != null)
                    param.put(entry.getKey(), entry.getValue().toString());
            }

            OkGo.<String>post(url)
                    .tag(url)
                    .params(param)
                    .execute(callback);
        } else {
            callback.onFinish("【" + url + "】还未响应，勿频繁请求！");
            if (SuperHttpManager.getApiCallbackService() != null) {
                SuperHttpManager.getApiCallbackService().onFinish("【" + url + "】还未响应，勿频繁请求！");
            }
        }

    }
    //--------------------------------------------------基本请求 end-------------------------------------------------


}
