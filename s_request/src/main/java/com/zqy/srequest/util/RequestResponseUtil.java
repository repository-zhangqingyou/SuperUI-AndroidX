package com.zqy.srequest.util;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 作者: zhangqingyou
 * 时间: 2020/9/14
 * 描述: 通用请求
 */
public class RequestResponseUtil {
    //防止同一个接口频繁请求，当前请求响应后才能继续下个请求
    /**
     * String:当前请求标识
     * Boolean：是否可请求
     */
    private static Map<String, Boolean> requestResponseMap = new LinkedHashMap<>();

    public static void setIsRequest(String url, boolean isr) {
        requestResponseMap.put(url, isr);
    }

    public static boolean getIsRequest(String url) {
        boolean isRequest = true;
        if (requestResponseMap.containsKey(url)) {
            isRequest = requestResponseMap.get(url);
        } else {
            //默认可请求
            requestResponseMap.put(url, true);
        }
        return isRequest;

    }
}
