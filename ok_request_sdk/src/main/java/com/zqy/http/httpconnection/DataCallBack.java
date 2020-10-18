package com.zqy.http.httpconnection;


import java.util.Map;

/**
 * Author: zhangqingyou
 * Date: 2020/5/22
 * Des:
 */
public interface DataCallBack<T> {
    /**
     * 请求开始前
     */
    void onStart(String url, Map<String, String> headers, Map<String, String> params);

    void onError(String erro);

    void onSuccess(T response);

    void onFinish(String msg);

}
