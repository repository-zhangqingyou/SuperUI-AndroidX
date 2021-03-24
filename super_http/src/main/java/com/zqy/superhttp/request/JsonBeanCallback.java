package com.zqy.superhttp.request;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.zqy.superhttp.SuperHttpManager;

import java.lang.reflect.Type;


/**
 * 作者: zhangqingyou
 * 时间: 2020/9/14
 * 描述: json对象实体类
 */

public abstract class JsonBeanCallback<T> extends BaseCallback {

    public JsonBeanCallback() {
        super();
    }

    public JsonBeanCallback(String requestName) {
        super(requestName);
    }

    @Override
    public void onFinish(String msg) {
    }


    @Override
    public void onSuccess(com.lzy.okgo.model.Response<String> response) {
        super.onSuccess(response);
        try {
            Type type = new TypeToken<T>() {
            }.getType();

            T tResult = new Gson().fromJson(response.body(), type);
            onSuccess(tResult);
//            Object code = map.get("code");
//            Object msg = map.get("msg");
//            Object data = map.get("data");
//
//            Result<T> tResult = new Result<>();
//            if (code instanceof Integer) {
//                tResult.setCode((Integer) code);
//            }
//            if (msg instanceof String) {
//                tResult.setMsg((String) msg);
//            }
//            tResult.setData(new Gson().fromJson(response.body(), T.class));


        } catch (JsonSyntaxException e) {

            if (SuperHttpManager.getApiCallbackService() != null) {
                SuperHttpManager.getApiCallbackService().onError(getBaseUrl(), getEndUrl(), response);
            }
        }
    }

    /**
     * 对返回数据进行操作的回调， UI线程
     */
    public abstract void onSuccess(T result);

}
