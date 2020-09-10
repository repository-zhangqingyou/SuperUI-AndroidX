package com.zqy.baserequest.bean;

import java.io.Serializable;
import java.util.List;
/**
 * Author: zhangqingyou
 * Date: 2020/4/7
 * Des:
 */
public class UploadImage implements Serializable {


    private String Code;
    private String msg;
    private List<String> data;


    public String getCode() {
        return Code;
    }

    public void setCode(String Code) {
        this.Code = Code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

}
