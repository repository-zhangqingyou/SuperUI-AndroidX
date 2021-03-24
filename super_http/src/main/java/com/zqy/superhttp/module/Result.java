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

}
