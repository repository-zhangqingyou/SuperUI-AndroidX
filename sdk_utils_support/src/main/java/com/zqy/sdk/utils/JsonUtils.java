package com.zqy.sdk.utils;


import com.zqy.sdk.gson.Gson;

import java.lang.reflect.Type;
import java.util.List;

public class JsonUtils {

    /**
     * 将对象转换成json字符串。
     * <p>Title: pojoToJson</p>
     * <p>Description: </p>
     *
     * @param data
     * @return
     */
    public static String objectToJson(Object data) {
        String string = new Gson().toJson(data);
        return string;

    }



    /**
     * 将json结果集转化为对象
     *
     * @param jsonData json数据
     * @param beanType 对象中的object类型
     * @return
     */
    public static <T> T jsonToObject(String jsonData, Class<T> beanType) {
        try {
            T t = new Gson().fromJson(jsonData, beanType);
            return t;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将json数据转换成pojo对象list
     * <p>Title: jsonToList</p>
     * <p>Description: </p>
     *
     * @param jsonData
     * @param beanType
     * @return
     */
    public static <T> List<T> jsonToList(String jsonData, Class<T> beanType) {
        Type type = new ParameterizedTypeImpl(beanType);
        List<T> list = new Gson().fromJson(jsonData, type);
        return list;
    }


}
