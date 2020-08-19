package com.hjy.baserequest.data;

/**
 * 作者: zhangqingyou
 * 时间: 2020/6/11 16:30
 * 描述:
 */
public class UserData {

    private String user_id;
    private String user_token;

    private String username;
    private String nickname;
    private String phone;
    private String avatar;
    private String signature;
    private String family_id;//默认加入的家族id
    private int gender;
    private int birthdate;
    private int is_anchor;

    public int getIs_anchor() {
        return is_anchor;
    }

    public void setIs_anchor(int is_anchor) {
        this.is_anchor = is_anchor;
    }
    public UserData(String user_id, String user_token) {
        this.user_id = user_id;
        this.user_token = user_token;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_token() {
        return user_token;
    }

    public void setUser_token(String user_token) {
        this.user_token = user_token;
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

    public String getFamily_id() {
        return family_id;
    }

    public void setFamily_id(String family_id) {
        this.family_id = family_id;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "user_id='" + user_id + '\'' +
                ", user_token='" + user_token + '\'' +
                ", username='" + username + '\'' +
                ", nickname='" + nickname + '\'' +
                ", phone='" + phone + '\'' +
                ", avatar='" + avatar + '\'' +
                ", signature='" + signature + '\'' +
                ", family_id='" + family_id + '\'' +
                ", gender=" + gender +
                ", birthdate=" + birthdate +
                ", is_anchor=" + is_anchor +
                '}';
    }
}
