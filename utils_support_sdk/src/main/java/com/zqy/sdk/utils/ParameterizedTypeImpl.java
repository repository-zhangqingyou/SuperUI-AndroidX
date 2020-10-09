package com.zqy.sdk.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;


/**
 * Gson解析不支持泛型，利用ParameterizedType获取泛型参数类型
 * Author: zhangqingyou
 * Date: 2020/4/7
 * Des:
 */
public class ParameterizedTypeImpl implements ParameterizedType {
    Class clazz;

    public ParameterizedTypeImpl(Class clz) {
        clazz = clz;
    }

    @Override
    public Type[] getActualTypeArguments() {
        //返回实际类型组成的数据
        return new Type[]{clazz};
    }

    @Override
    public Type getRawType() {
        //返回原生类型，即HashMap
        return List.class;
    }

    @Override
    public Type getOwnerType() {
        //返回Type对象
        return null;
    }
}
