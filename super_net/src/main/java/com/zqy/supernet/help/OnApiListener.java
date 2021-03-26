package com.zqy.supernet.help;


import okhttp3.Request;

/**
 * 作者: zhangqingyou
 * 时间: 2021/3/27
 * 描述: 全局响应结果回调
 */
public interface OnApiListener {

    /**
     * 请求网络开始前，UI线程
     *
     */
    void onStart(Request request);

    /**
     * 请求网络结束后，UI线程
     */
    void onFinish();


    /**
     * 请求失败，响应错误，数据解析错误等，都会回调该方法， UI线程
     */
    void onError(String msg);

    /**
     * 对返回数据进行操作的回调， UI线程
     */
    void onSuccess(String beanName, String json);


}
