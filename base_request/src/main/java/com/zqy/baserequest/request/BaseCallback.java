package com.zqy.baserequest.request;

import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.convert.StringConvert;
import com.lzy.okgo.request.base.Request;
import com.zqy.baserequest.util.JEventUtils;
import com.zqy.baserequest.util.RequestResponseUtil;

import okhttp3.Response;

/**
 * Author: zhangqingyou
 * Date: 2020/4/7
 * Des:
 */

public abstract class BaseCallback extends AbsCallback<String> {
    private StringConvert convert;
    public String TAG;//网络请求TAG（用于日志过滤）-- 后缀地址
    private String baseUrl;//请求全地址路劲

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
            TAG = url.substring(indexOf, url.length());
        } catch (Exception e) {
            TAG = "网络请求";
        }
        RequestResponseUtil.setIsRequest(baseUrl, false);//设置不可请求

    }


    @Override
    public void onError(com.lzy.okgo.model.Response<String> response) {
        super.onError(response);
        //极光计数事件（接口连接失败使用）
        JEventUtils.onCountEventError(response, TAG);

    }

    @Override
    public void onSuccess(com.lzy.okgo.model.Response<String> response) {
        //打印完成请求链接
        JEventUtils.logRequest(response, TAG);

    }

    @Override
    public void onFinish() {
        super.onFinish();
        RequestResponseUtil.setIsRequest(baseUrl, true);//设置可请求
    }


}
