package com.zqy.http.httpconnection;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.util.Map;

/**
 * Author: zhangqingyou
 * Date: 2020/5/22 11:12
 * Des:
 */
public abstract class StringDataCallBack implements DataCallBack<String> {
    private String urlEnd = "";

    public String getUrlEnd() {
        return urlEnd;
    }


    /**
     * @param url
     * @param headers
     * @param requestParameters
     */
    @Override
    public void onStart(final String url, final Map<String, String> headers, final Map<String, String> requestParameters) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                urlEnd = getUrlEnd(url);
                if (requestParameters != null) {
                    logRequest(urlEnd, "请求参数：" + requestParameters.toString());
                }
                onUIStart(url, headers, requestParameters);
            }
        });
    }

    @Override
    public void onError(final String erro) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                logRequest(urlEnd, "连接失败：" + erro);
                onUIError(erro);
            }
        });
    }

    @Override
    public void onSuccess(final String s) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                logRequest(urlEnd, "返回数据：" + s);
                onUISuccess(s);
            }
        });
    }

    @Override
    public void onFinish() {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                onUIFinish();
            }
        });
    }

    /**
     * ui线程
     * 请求开始前
     */
    public abstract void onUIStart(String url, Map<String, String> headers, Map<String, String> requestParameters);

    /**
     * ui线程
     * 请求错误
     */
    public abstract void onUIError(String erro);

    /**
     * ui线程
     * 请求成功
     */
    public abstract void onUISuccess(String response);

    /**
     * ui线程
     * 请求结束  不管成功还是失败
     */
    public abstract void onUIFinish();

    /**
     * 打印请求信息
     *
     * @param text
     */
    public void logRequest(String tag, String text) {
        Log.d(tag, text);
    }

    public String getUrlEnd(String url) {
        String urlEnd = null;
        try {
            String urlreplace = url.replace("//", "");
            int indexOf = urlreplace.indexOf("/");
            urlEnd = urlreplace.substring(indexOf, urlreplace.length());
        } catch (Exception e) {
            urlEnd = "网络请求";
        }
        return urlEnd;
    }
}
