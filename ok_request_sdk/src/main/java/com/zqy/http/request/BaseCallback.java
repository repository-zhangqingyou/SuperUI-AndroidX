package com.zqy.http.request;

import android.text.TextUtils;

import com.zqy.http.HttpManage;
import com.zqy.http.okgo.callback.AbsCallback;
import com.zqy.http.okgo.convert.StringConvert;
import com.zqy.http.okgo.request.base.Request;
import com.zqy.http.okhttp3.Response;


/**
 * 作者: zhangqingyou
 * 时间: 2020/9/14
 * 描述: 通用请求
 */

public abstract class BaseCallback extends AbsCallback<String> {
    private StringConvert convert;
    private String TAG;//网络请求TAG（用于日志过滤）-- 后缀地址
    private String baseUrl;//请求全地址路劲
    private String endUrl = "";//请求后缀地址

    public BaseCallback() {
        convert = new StringConvert();
    }

    public BaseCallback(String requestName) {
        convert = new StringConvert();
        this.TAG = requestName;
    }

    @Override
    public String convertResponse(Response response) throws Throwable {
        String s = convert.convertResponse(response);
        response.close();
        return s;
    }


    @Override
    public void onStart(Request<String, ? extends Request> request) {
        super.onStart(request);
        try {
            baseUrl = request.getBaseUrl();
            String url = baseUrl.replace("//", "");
            int indexOf = url.indexOf("/");
            endUrl = url.substring(indexOf, url.length());

            if (TextUtils.isEmpty(TAG)) {
                TAG = endUrl;
            }
        } catch (Exception e) {
            TAG = "网络请求";
        }

        if (HttpManage.getApiCallback() != null) {
            HttpManage.getApiCallback().onStart(baseUrl, endUrl, request);
        }


    }


    @Override
    public void onError(com.zqy.http.okgo.model.Response<String> response) {
        super.onError(response);
        if (HttpManage.getApiCallback() != null) {
            HttpManage.getApiCallback().onError(baseUrl, endUrl, response);
        }
    }

    @Override
    public void onSuccess(com.zqy.http.okgo.model.Response<String> response) {
        if (HttpManage.getApiCallback() != null) {
            HttpManage.getApiCallback().onSuccess(baseUrl, endUrl, response);
        }
    }

    @Override
    public void onFinish() {
        super.onFinish();
        OKRequest.setIsRequest(baseUrl, true);//设置可请求
        onFinish("请求完成");

        if (HttpManage.getApiCallback() != null) {
            HttpManage.getApiCallback().onFinish("请求完成");
        }
    }

    /**
     * 对返回数据进行操作的回调， UI线程
     */
    public abstract void onFinish(String msg);

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
