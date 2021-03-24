package com.zqy.superhttp.response;

import java.io.Serializable;


/**
 * 作者: zhangqingyou
 * 时间: 2021/3/24
 * 描述:
 */
public class Result<T> extends BaseResult<T> implements Serializable {
    private int code;
    private String msg;

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

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data != null ? data.toString() : "null" +
                '}';
    }
}
