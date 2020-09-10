package com.zqy.baserequest.bean;

import java.io.Serializable;

/**
 * Author: zhangqingyou
 * Date: 2020/4/7
 * Des:
 */

public class DescAndCode implements Serializable {


    /**
     * code : 200
     * msg : 发送成功
     * data : null
     */

    private int code;
    private String msg;
    private Object data;

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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
