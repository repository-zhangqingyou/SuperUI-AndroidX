package com.zqy.suidemo.service;

import android.util.Log;

import com.google.auto.service.AutoService;
import com.google.gson.JsonSyntaxException;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.zqy.srequest.request.ApiCallbackService;
import com.zqy.sutils.ToastUtil;

/**
 * 作者: zhangqingyou
 * 时间: 2020/9/14 9:03
 * 描述: 所有接口响应回调
 */
@AutoService(ApiCallbackService.class)//自动注入，必须使用此注解，否则所有方法不会调用
public class ApiCallback implements ApiCallbackService {
    @Override
    public void onStart(String baseUrl, String endUrl, Request request) {
    }

    @Override
    public void onFinish(String msg) {
    }

    @Override
    public void onError(String baseUrl, String endUrl, Response response) {
        Throwable exception = response.getException();
        okhttp3.Response responseRawResponse = response.getRawResponse();
        String requestString = "";//请求信息
        String bodyString = "";//返回信息
        if (responseRawResponse != null) {
            if (responseRawResponse.request() != null) {
                requestString = responseRawResponse.request().toString();
                Log.d(endUrl + "出错啦！--请求信息：", requestString);
            }

            if (responseRawResponse.body() != null) {
                bodyString = responseRawResponse.body().toString();
                Log.d(endUrl + "出错啦！--返回信息：", bodyString);
            }

        }

        if (exception != null) {
            ToastUtil.toast(endUrl + "出错啦！--错误信息：" + exception.getMessage());
            Log.d(endUrl + "出错啦！--错误信息：", exception.getMessage());

            if (exception instanceof JsonSyntaxException) {
                // 极光计数事件（接口返回json数据解析错误使用）
                //  JEventUtils.onJsonCountEventError(baseUrl, requestString, bodyString, exception.getMessage());
            } else {
                //极光计数事件（接口连接失败使用）
                // JEventUtils.onCountEventError(baseUrl, requestString, bodyString, exception.getMessage());
            }
        }

    }

    @Override
    public void onSuccess(String baseUrl, String endUrl, Response response) {
        okhttp3.Response responseRawResponse = response.getRawResponse();
        String requestString = "";//请求信息
        String bodyString = "";//返回信息
        if (responseRawResponse != null) {
            if (responseRawResponse.request() != null) {
                requestString = responseRawResponse.request().toString();
            }
            if (responseRawResponse.body() != null) {
                bodyString = responseRawResponse.body().toString();
            }

        }
        Log.d(endUrl, "成功啦！请求信息:" + requestString + "\n返回信息：" + bodyString);
    }
}
