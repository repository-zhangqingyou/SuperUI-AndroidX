package com.zqy.superhttp.http;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.zqy.superhttp.SuperHttpManager;
import com.zqy.superhttp.response.BaseResult;


/**
 * 作者: zhangqingyou
 * 时间: 2020/9/14
 * 描述: json对象实体类
 */

public abstract class JsonBeanCallback<T, K extends BaseResult<T>> extends BaseCallback {
    private Class<K> classOfBean;//json对象实体

    public JsonBeanCallback(Class<K> classOfBean) {
        super();
        this.classOfBean = classOfBean;
    }

    public JsonBeanCallback(String requestName, Class<K> classOfBean) {
        super(requestName);
        this.classOfBean = classOfBean;
    }

    @Override
    public void onFinish(String msg) {
    }


    @Override
    public void onSuccess(com.lzy.okgo.model.Response<String> response) {
        super.onSuccess(response);
        try {

            K tResult = new Gson().fromJson(response.body(), classOfBean);

            onSuccess(tResult);
        } catch (JsonSyntaxException e) {
            if (SuperHttpManager.getApiCallbackService() != null) {
                SuperHttpManager.getApiCallbackService().onError(getBaseUrl(), getEndUrl(), response);
            }
        }
    }

    /**
     * 对返回数据进行操作的回调， UI线程
     */
    public abstract void onSuccess(K result);

}
