package com.zqy.superhttp.request;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.zqy.superhttp.SuperHttpManager;
import com.zqy.superhttp.module.Result;


/**
 * 作者: zhangqingyou
 * 时间: 2020/9/14
 * 描述: json对象实体类
 */

public abstract class JsonBeanCallback<T> extends BaseCallback {

    @Override
    public void onFinish(String msg) {

    }

    @Override
    public void onSuccess(com.lzy.okgo.model.Response<String> response) {
        super.onSuccess(response);
        try {
            Result<T> t = new Gson().fromJson(response.body(), Result.class);
            onSuccess(t);
        } catch (JsonSyntaxException e) {

            if (SuperHttpManager.getApiCallbackService() != null) {
                SuperHttpManager.getApiCallbackService().onError(getBaseUrl(), getEndUrl(), response);
            }

        }

    }

    /**
     * 对返回数据进行操作的回调， UI线程
     */
    public abstract void onSuccess(Result<T> result);

}
