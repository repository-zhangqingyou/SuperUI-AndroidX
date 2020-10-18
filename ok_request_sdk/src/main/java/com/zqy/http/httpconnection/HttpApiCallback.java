package com.zqy.http.httpconnection;


import java.util.Map;

/**
 * 作者: zhangqingyou
 * 时间: 2020/10/18 20:54
 * 描述:
 */
public interface HttpApiCallback<T> {
    /**
     * 请求网络开始前，UI线程
     */
    void onStart(String baseUrl, String endUrl, Map<String, String> headers,  Map<String, String> requestParameters);
    /**
     * 请求网络结束后，UI线程
     */
    void onFinish(String msg);


    /**
     * 请求失败，响应错误，数据解析错误等，都会回调该方法， UI线程
     */
    void onError(String baseUrl, String endUrl, String erro);

    /**
     * 对返回数据进行操作的回调， UI线程
     */
    void onSuccess(String baseUrl, String endUrl,String response);


}
