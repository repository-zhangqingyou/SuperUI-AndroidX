package com.zqy.http.httpconnection;

import com.zqy.sdk.gson.Gson;
import com.zqy.sdk.gson.JsonSyntaxException;

import java.util.Map;

/**
 * Author: zhangqingyou
 * Date: 2020/5/22
 * Des:
 * <p>
 *
 * @param <T> json对象实体类
 */
public abstract class JsonEntityCallback<T> extends StringDataCallBack {
    private Class<T> classOfBean;

    public JsonEntityCallback(Class<T> classOfBean) {
        this.classOfBean = classOfBean;
    }

    @Override
    public void onUIStart(String url, Map<String, String> headers, Map<String, String> requestParameters) {

    }

    @Override
    public void onUIFinish() {

    }

    /**
     * 请求成功
     *
     * @param response
     */
    @Override
    public void onUISuccess(String response) {
        try {
            T t = new Gson().fromJson(response, classOfBean);
            onUISuccessEntity(t);
        } catch (JsonSyntaxException e) {
            logRequest(getUrlEnd(), "_json数据格式错误");
        }
    }

    /**
     * 返回数据进行操作的回调， UI线程
     */
    public abstract void onUISuccessEntity(T t);


}