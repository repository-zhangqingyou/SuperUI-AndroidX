package com.zqy.srequest.request;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;
import com.zqy.srequest.RequestManage;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 作者: zhangqingyou
 * 时间: 2020/9/14
 * 描述: 通用请求
 */

public class SRequest {
    /**
     * 当前请求响应后才能继续下个请求
     * String:当前请求标识
     * Boolean：是否可请求
     */
    private static Map<String, Boolean> requestResponseMap = new HashMap<>();

    /**
     * 设置接口是否可请求
     *
     * @param url       完整地址
     * @param isRequest 当前地址是否可请求
     */
    public static void setIsRequest(String url, boolean isRequest) {
        requestResponseMap.put(url, isRequest);
    }

    /**
     * 获取指定接口是否可请求
     *
     * @param url 完整地址
     * @return
     */
    public static boolean isRequest(String url) {
        boolean isRequest = true;
        if (requestResponseMap.containsKey(url)) {
            isRequest = requestResponseMap.get(url);
        } else {
            //默认可请求
            requestResponseMap.put(url, true);
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
    public static void okgo_postJson(String url, String json, BaseCallback callback) {
        if (isRequest(url)) {//防止同一个接口频繁请求，当前请求响应后才能继续下个请求
            setIsRequest(url, false);
            OkGo.<String>post(url)
                    //.tag(requestTag(url))
                    .upJson(json)
                    .execute(callback);
        } else {
            callback.onFinish(url + "还未响应，相同接口无法再次请求！");
            if (RequestManage.getApiCallbackServiceLoader() != null) {
                for (ApiCallbackService service : RequestManage.getApiCallbackServiceLoader()) {
                    service.onFinish(url + "还未响应，相同接口无法再次请求！");
                }
            }


        }

    }

    public static void okgo_get(String url, Map<String, Object> params, BaseCallback callback) {
        if (isRequest(url)) {//防止同一个接口频繁请求，当前请求响应后才能继续下个请求
            setIsRequest(url, false);
            Map<String, String> param = new LinkedHashMap<>();
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                if (key != null && value != null)
                    param.put(entry.getKey(), entry.getValue().toString());
            }
            OkGo.<String>get(url)
                    // .tag(requestTag(url))
                    .params(param)
                    .execute(callback);
        } else {
            callback.onFinish(url + "还未响应，相同接口无法再次请求！");
            if (RequestManage.getApiCallbackServiceLoader() != null) {
                for (ApiCallbackService service : RequestManage.getApiCallbackServiceLoader()) {
                    service.onFinish(url + "还未响应，相同接口无法再次请求！");
                }
            }
        }


    }

    public static void okgo_post(String url, Map<String, Object> params, BaseCallback callback) {
        if (isRequest(url)) {//防止同一个接口频繁请求，当前请求响应后才能继续下个请求
            setIsRequest(url, false);
            Map<String, String> param = new LinkedHashMap<>();
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                if (key != null && value != null)
                    param.put(entry.getKey(), entry.getValue().toString());
            }

            OkGo.<String>post(url)
                    //.tag(requestTag(url))
                    .params(param)
                    .execute(callback);
        } else {
            callback.onFinish(url + "还未响应，相同接口无法再次请求！");
            if (RequestManage.getApiCallbackServiceLoader() != null) {
                for (ApiCallbackService service : RequestManage.getApiCallbackServiceLoader()) {
                    service.onFinish(url + "还未响应，相同接口无法再次请求！");
                }
            }
        }


    }
    //--------------------------------------------------基本请求 end-------------------------------------------------


}
