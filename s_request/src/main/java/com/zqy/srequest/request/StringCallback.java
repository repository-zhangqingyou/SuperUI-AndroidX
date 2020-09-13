package com.zqy.srequest.request;

/**
 * Author: zhangqingyou
 * Date: 2020/9/14
 * Des:
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
    public void onSuccess(com.lzy.okgo.model.Response<String> response) {
        super.onSuccess(response);
        onSuccess(response.body());
    }

    /**
     * 对返回数据进行操作的回调， UI线程
     */
    public abstract void onSuccess(String body);

}
