package com.zqy.superhttp.request;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.zqy.superhttp.SuperHttpManager;
import com.zqy.superutils.ParameterizedTypeImpl;

import java.lang.reflect.Type;
import java.util.List;


/**
 * 作者: zhangqingyou
 * 时间: 2020/9/14
 * 描述: json为数组时使用
 */

public abstract class JsonArryEntityCallback<T> extends BaseCallback {
    private Class<T> classOfBean;//json对象实体

    public JsonArryEntityCallback(Class<T> classOfBean) {
        this.classOfBean = classOfBean;

    }

    public JsonArryEntityCallback(Class<T> classOfBean, String requestName) {
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
            //泛型转换
            Type type = new ParameterizedTypeImpl(classOfBean);
            List<T> list = new Gson().fromJson(response.body(), type);
            onSuccess(list);
        } catch (JsonSyntaxException e) {
            if (SuperHttpManager.getApiCallbackServiceLoader() != null) {
                for (ApiCallbackService service : SuperHttpManager.getApiCallbackServiceLoader()) {
                    response.setException(new JsonSyntaxException("json数据格式错误:" + e.getMessage()));
                    service.onError(getBaseUrl(), getEndUrl(), response);
                }
            }



        }

    }


    /**
     * 对返回数据进行操作的回调， UI线程
     */
    public abstract void onSuccess(List<T> tList);


}
