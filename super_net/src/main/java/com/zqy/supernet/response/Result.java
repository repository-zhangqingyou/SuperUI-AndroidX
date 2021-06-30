package com.zqy.supernet.response;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;


/**
 * 作者: zhangqingyou
 * 时间: 2021/3/24
 * 描述:
 */
@Getter
@Setter
public class Result<T> extends BaseResult<T> implements Serializable {
    private int code;
    private String msg;

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + (data != null ? data.toString() : "null") +
                '}';
    }
}
