package com.zqy.supernet.help.imp;


import android.util.Log;

import com.blankj.utilcode.util.AppUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okio.Buffer;

/**
 * 作者: zhangqingyou
 * 时间: 2021/7/6
 * 描述: 简单全局响应结果回调
 */
public class SimpleOnApiListener implements OnApiListener {
    private String url;

    @Override
    public void onStart(Request request) {
//        Map<String, String> parameter = new LinkedHashMap<>();
//        if (request.body() instanceof FormBody) {
//            FormBody formBody = (FormBody) request.body();
//            for (int i = 0; i < formBody.size(); i++) {
//                parameter.put(formBody.encodedName(i), formBody.encodedValue(i));
//            }
//        } else if (request.body() instanceof MultipartBody) {
//            MultipartBody multipartBody = (MultipartBody) request.body();
//        }

        try {
            url = request.url().toString();

            Headers headers = request.headers();

            RequestBody requestBody = request.body();
            Buffer buffer = new Buffer();
            requestBody.writeTo(buffer);
            Charset charset = StandardCharsets.UTF_8;
            MediaType contentType = requestBody.contentType();
            String paramsStr = buffer.readString(charset);

            StringBuilder stringBuilder=new StringBuilder();
            stringBuilder
                    .append("\n请求类型:" )
                    .append(contentType != null ? contentType.toString() : "")
                    .append("\n请求Headers:")
                    .append(headers.toString())
                    .append("\n请求参数:")
                    .append(paramsStr);

            Log.d(AppUtils.getAppName() + "-接口", "onStart-----url:" + url + stringBuilder.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onFinish() {
        // Log.d(AppUtils.getAppName() + "-接口", "onFinish");

    }

    @Override
    public void onError(Throwable msg) {
        Log.d(AppUtils.getAppName() + "-接口", url + "------onError:" + msg.getMessage());
    }

    @Override
    public void onSuccess(String beanName, String json) {
        Log.d(AppUtils.getAppName() + "-接口", url + "------onSuccess----------json:" + json);
    }
}
