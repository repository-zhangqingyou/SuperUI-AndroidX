package com.hjy.baserequest.bean;

import java.io.Serializable;
/**
 * Author: zhangqingyou
 * Date: 2020/4/7
 * Des:
 */
public class UploadImageOne implements Serializable {


    /**
     * data : 52499ee01903e876835a62d4e2aba89c
     * message : 上传成功
     * success : true
     */

    private String data;
    private String message;
    private boolean success;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
