package com.hjy.baserequest.bean;

import java.io.Serializable;

/**
 * 作者: zhangqingyou
 * 时间: 2020/6/11 16:56
 * 描述:帐号登录
 */
public class AccountsLoginUserBean implements Serializable {


    /**
     * code : 200
     * msg : 账号登录
     * data : {"user_id":30,"token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1OTEzMjU0NzUsIm5iZiI6MTU5MTMyNTQ3NSwiZXhwIjoxNTkxOTc3NTk5LCJkYXRhIjp7InVzZXJfaWQiOjMwfX0.Vv_xBiV7Zvt6HEL3-7tT8hBN-Fno7e0d2JDPZqt7ZMM"}
     */

    private int code;
    private String msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * user_id : 30
         * token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1OTEzMjU0NzUsIm5iZiI6MTU5MTMyNTQ3NSwiZXhwIjoxNTkxOTc3NTk5LCJkYXRhIjp7InVzZXJfaWQiOjMwfX0.Vv_xBiV7Zvt6HEL3-7tT8hBN-Fno7e0d2JDPZqt7ZMM
         */

        private int user_id;
        private String token;

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
