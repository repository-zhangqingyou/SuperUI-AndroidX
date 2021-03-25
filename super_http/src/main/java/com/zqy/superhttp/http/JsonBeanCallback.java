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

public abstract class JsonBeanCallback<T> extends BaseCallback {
    private Class<BaseResult<T>> classOfBean;//json对象实体

    public JsonBeanCallback(Class<BaseResult<T>> classOfBean) {
        super();
        this.classOfBean = classOfBean;
    }

    public JsonBeanCallback(String requestName, Class<BaseResult<T>> classOfBean) {
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

            BaseResult<T> tResult = new Gson().fromJson(response.body(), classOfBean);

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
    public abstract void onSuccess(BaseResult<T> result);

}
