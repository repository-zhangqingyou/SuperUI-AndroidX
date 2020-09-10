package com.zqy.baserequest.bean;

import java.io.Serializable;

/**
 * CREATED BY DY ON 2020/6/30.
 * TIME BY 15:40.
 *
 * @author DY
 **/
public class UserInfo implements Serializable {

    /**
     * code : 200
     * msg : 个人资料
     * data : {"id":47,"username":"u1000047","nickname":"u1000047","phone":"15531876900","avatar":"","signature":"","gender":0,"birthdate":698256000}
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
         * id : 47
         * username : u1000047
         * nickname : u1000047
         * phone : 15531876900
         * avatar :
         * signature :
         * gender : 0
         * birthdate : 698256000
         */

        private int id;
        private String username;
        private String nickname;
        private String phone;
        private String avatar;
        private String signature;
        private int gender;
        private int birthdate;
        private int is_anchor;

        public int getIs_anchor() {
            return is_anchor;
        }

        public void setIs_anchor(int is_anchor) {
            this.is_anchor = is_anchor;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public int getBirthdate() {
            return birthdate;
        }

        public void setBirthdate(int birthdate) {
            this.birthdate = birthdate;
        }
    }
}
