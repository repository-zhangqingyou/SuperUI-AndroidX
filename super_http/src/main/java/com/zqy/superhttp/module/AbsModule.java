package com.zqy.superhttp.module;

import android.text.TextUtils;

import java.io.Serializable;


/**
 * 作者: zhangqingyou
 * 时间: 2020/9/14
 * 描述: 通用请求
 */

public class AbsModule implements Serializable {


    /**
     * code : 200
     * msg : 发送成功
     * data : null
     */

    private int code;
    private int Code;
    private String msg;
    private String Msg;
    private Object data;

    public int getCode() {
        if (code != 0)
            return code;
        else
            return Code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        if (TextUtils.isEmpty(msg))
            return Msg;
        else
            return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
