package com.zqy.superhttp.module;

import java.io.Serializable;

/**
 * 作者: zhangqingyou
 * 时间: 2021/3/24
 * 描述:
 */
public class Result<T> implements Serializable {
    public int code;
    public String msg;
    public T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
