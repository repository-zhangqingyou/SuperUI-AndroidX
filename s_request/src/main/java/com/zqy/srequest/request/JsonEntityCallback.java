package com.zqy.srequest.request;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.zqy.srequest.util.JEventUtils;
import com.zqy.sutils.ToastUtil;

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
    public void onSuccess(com.lzy.okgo.model.Response<String> response) {
        super.onSuccess(response);
        try {
            T t = new Gson().fromJson(response.body(), classOfBean);
            onSuccess(t);
        } catch (JsonSyntaxException e) {
            ToastUtil.toast(TAG + "_json数据格式错误");
            Log.d(TAG + "_json数据格式错误:",e.getMessage());
            //极光计数事件（接口返回json数据解析错误使用）
            JEventUtils.onCountEventJsonError(response, TAG);
        }

    }

    /**
     * 对返回数据进行操作的回调， UI线程
     */
    protected abstract void onSuccess(T t);

}
