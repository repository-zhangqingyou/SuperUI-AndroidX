package com.zqy.http.httpconnection;

import com.zqy.sdk.utils.gson.Gson;
import com.zqy.sdk.utils.gson.JsonSyntaxException;
import com.zqy.sdk.utils.utils.ParameterizedTypeImpl;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;


/**
 * Author: zhangqingyou
 * Date: 2020/5/22
 * Des:
 * <p>
 * json为数组时使用
 *
 * @param <T> json对象实体类
 */

public abstract class JsonArryEntityCallback<T> extends StringDataCallBack {
    private Class<T> classOfBean;//json对象实体

    public JsonArryEntityCallback(Class<T> classOfBean) {
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
            //泛型转换
            Type type = new ParameterizedTypeImpl(classOfBean);
            List<T> list = new Gson().fromJson(response, type);
            onUISuccessEntity(list);
        } catch (JsonSyntaxException e) {
            logRequest(getUrlEnd(), "_json数据格式错误");
        }
    }

    /**
     * 返回数据进行操作的回调， UI线程
     */
    public abstract void onUISuccessEntity(List<T> tList);


}
