package com.zqy.superhttp.response;

import java.io.Serializable;

/**
 * 作者: zhangqingyou
 * 时间: 2021/3/24
 * 描述:
 */
public class BaseResult<T> implements Serializable {

    protected T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
