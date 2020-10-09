package com.zqy.http.request;


import com.zqy.http.okgo.model.Response;
import com.zqy.http.okgo.request.base.Request;

/**
 * 作者: zhangqingyou
 * 时间: 2020/7/8 9:17
 * 描述:
 */
public interface ApiCallback<T> {
    /**
     * 请求网络开始前，UI线程
     */
    void onStart(String baseUrl, String endUrl, Request<T, ? extends Request> request);

    /**
     * 请求网络结束后，UI线程
     */
    void onFinish(String msg);


    /**
     * 请求失败，响应错误，数据解析错误等，都会回调该方法， UI线程
     */
    void onError(String baseUrl, String endUrl, Response<T> response);

    /**
     * 对返回数据进行操作的回调， UI线程
     */
    void onSuccess(String baseUrl, String endUrl, Response<T> response);


}
