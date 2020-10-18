package com.zqy.http.httpconnection;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

import com.zqy.http.HttpManage;

import java.util.Map;

/**
 * Author: zhangqingyou
 * Date: 2020/5/22 11:12
 * Des:
 */
public abstract class StringDataCallBack implements DataCallBack<String> {
    private String TAG;//网络请求TAG（用于日志过滤）-- 后缀地址
    private String baseUrl;//请求全地址路劲
    private String endUrl = "";//请求后缀地址


    public StringDataCallBack() {
    }

    public StringDataCallBack(String requestName) {
        this.TAG = requestName;
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
                try {
                    baseUrl = url;
                    String url = baseUrl.replace("//", "");
                    int indexOf = url.indexOf("/");
                    endUrl = url.substring(indexOf, url.length());

                    if (TextUtils.isEmpty(TAG)) {
                        TAG = endUrl;
                    }
                } catch (Exception e) {
                    TAG = "网络请求";
                }

                if (HttpManage.getHttpApiCallback() != null) {
                    HttpManage.getHttpApiCallback().onStart(baseUrl, endUrl, headers, requestParameters);
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
                if (HttpManage.getHttpApiCallback() != null) {
                    HttpManage.getHttpApiCallback().onError(baseUrl, endUrl, erro);
                }

                onUIError(erro);
            }
        });
    }

    @Override
    public void onSuccess(final String response) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {

                if (HttpManage.getHttpApiCallback() != null) {
                    HttpManage.getHttpApiCallback().onSuccess(baseUrl, endUrl, response);
                }

                onUISuccess(response);
            }
        });
    }

    @Override
    public void onFinish(final String msg) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                if (HttpManage.getHttpApiCallback() != null) {
                    HttpManage.getHttpApiCallback().onFinish(msg);
                }

                onUIFinish(msg);
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
    public abstract void onUIFinish(String msg);

//    /**
//     * 打印请求信息
//     *
//     * @param text
//     */
//    public void logRequest(String tag, String text) {
//        Log.d(tag, text);
//    }
//
//    public String getUrlEnd(String url) {
//        String urlEnd = null;
//        try {
//            String urlreplace = url.replace("//", "");
//            int indexOf = urlreplace.indexOf("/");
//            urlEnd = urlreplace.substring(indexOf, urlreplace.length());
//        } catch (Exception e) {
//            urlEnd = "网络请求";
//        }
//        return urlEnd;
//    }

    /**
     * 获取当前请求TAG
     *
     * @return
     */
    public String getTAG() {
        return TAG;
    }

    /**
     * 获取当前完整请求地址
     *
     * @return
     */
    public String getBaseUrl() {
        return baseUrl;
    }

    /**
     * 请求后缀地址
     *
     * @return
     */
    public String getEndUrl() {
        return endUrl;
    }
}
