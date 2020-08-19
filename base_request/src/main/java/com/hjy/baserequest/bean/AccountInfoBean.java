package com.hjy.baserequest.bean;

import java.io.Serializable;

/**
 * CREATED BY DY ON 2020/7/6.
 * TIME BY 18:26.
 *
 * @author DY
 **/
public class AccountInfoBean implements Serializable {

    /**
     * code : 200
     * msg : 账号信息
     * data : {"phone":"147****4469","is_set_password":1}
     *
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

    public static class DataBean implements Serializable{
        /**
         * phone : 147****4469
         * is_set_password : 1
         * 是否设置密码:0=未设置，1=已设置
         */

        private String phone;
        private int is_set_password;

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getIs_set_password() {
            return is_set_password;
        }

        public void setIs_set_password(int is_set_password) {
            this.is_set_password = is_set_password;
        }
    }
}
