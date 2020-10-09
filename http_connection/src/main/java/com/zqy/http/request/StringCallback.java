package com.zqy.http.request;


/**
 * 作者: zhangqingyou
 * 时间: 2020/9/14
 * 描述:
 */
public abstract class StringCallback extends BaseCallback {

    public StringCallback() {
    }

    public StringCallback(String requestName) {
        super(requestName);
    }

    @Override
    public void onFinish(String msg) {

    }

    @Override
    public void onSuccess(com.zqy.http.okgo.model.Response<String> response) {
        super.onSuccess(response);
        onSuccess(response.body());
    }

    /**
     * 对返回数据进行操作的回调， UI线程
     */
    public abstract void onSuccess(String body);

}
