package com.zqy.srequest.request;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.zqy.srequest.RequestManage;

/**
 * Author: zhangqingyou
 * Date: 2020/4/7
 * Des:
 *
 * @param <T> json对象实体类
 */

public abstract class JsonEntityCallback<T> extends BaseCallback {
    private Class<T> classOfBean;//json对象实体

    public JsonEntityCallback(Class<T> classOfBean) {
        this.classOfBean = classOfBean;
    }

    public JsonEntityCallback(Class<T> classOfBean, String requestName) {
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
            T t = new Gson().fromJson(response.body(), classOfBean);
            onSuccess(t);
        } catch (JsonSyntaxException e) {
            if (RequestManage.getApiCallback() != null) {
                response.setException(new JsonSyntaxException("json数据格式错误:" + e.getMessage()));
                RequestManage.getApiCallback().onError(getBaseUrl(), getEndUrl(), response);
            }
        }

    }

    /**
     * 对返回数据进行操作的回调， UI线程
     */
    public abstract void onSuccess(T t);

}
