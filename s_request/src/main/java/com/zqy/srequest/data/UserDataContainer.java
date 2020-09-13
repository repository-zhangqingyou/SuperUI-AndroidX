package com.zqy.srequest.data;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.zqy.sutils.CacheUtil;


/**
 * 用户数据容器类
 * Author: zhangqingyou
 * Date: 2020/5/12 11:30
 * Des:
 */
public class UserDataContainer {
    private String TAG;
    private final String userinfoCachekey = "用户信息";
    private final String userListCachekey = "用户历史帐号信息";
    private static UserDataContainer mInstance;
    private UserData userData;//用户信息
    // private List<PhoneLoginUserBean.DataBean.UserListBean> user_list;//用户账号列表

    private UserDataContainer() {
        TAG = getClass().getSimpleName();
    }

    public static UserDataContainer getInstance() {
        if (mInstance == null) {
            synchronized (UserDataContainer.class) {
                if (mInstance == null) {
                    mInstance = new UserDataContainer();
                }
            }
        }
        return mInstance;
    }

    /**
     * 清除用户信息
     */
    public void clear() {
        userData = null;
//        if (user_list != null) {
//            user_list.clear();
//        }
//        user_list = null;

        CacheUtil.delteString(CacheUtil.getUserPath(), userinfoCachekey);
        CacheUtil.delteString(CacheUtil.getUserPath(), userListCachekey);
    }


    /**
     * 用户是否登录
     *
     * @return
     */
    public boolean isLogin() {
        boolean isLogin = true;
        if (userData == null) {
            isLogin = false;
        }
        return isLogin;
    }

    public UserData getUserData() {
        //如果用户已登陆，从本地读取
        if (userData == null) {
            String userJson = CacheUtil.readString(CacheUtil.getUserPath(), userinfoCachekey);
            if (!TextUtils.isEmpty(userJson)) {
                userData = new Gson().fromJson(userJson, UserData.class);
                return userData;
            } else {
                return null;
            }
        } else
            return userData;

    }

    public String getUserId() {
        String user_id = "";
        UserData userData = getUserData();
        if (userData != null) {
            if (userData.getUser_id() != null)
                user_id = userData.getUser_id();
        }
        return user_id;
    }

    /**
     * 保存
     *
     * @param userData
     */
    public void saveUser(UserData userData) {
        this.userData = userData;
        CacheUtil.writeString(CacheUtil.getUserPath(), userinfoCachekey, new Gson().toJson(userData));
    }

//    public List<PhoneLoginUserBean.DataBean.UserListBean> getUserList() {
//        //如果用户已登陆，从本地读取
//        if (user_list == null) {
//            String userJson = CacheUtil.readString(CacheUtil.getUserPath(), userListCachekey);
//            if (userJson != null) {
//                //泛型转换
//                Type type = new ParameterizedTypeImpl(PhoneLoginUserBean.DataBean.UserListBean.class);
//                user_list = new Gson().fromJson(userJson, type);
//                return user_list;
//            } else {
//                return null;
//            }
//        } else
//            return user_list;
//
//
//    }
//
//    public void saveUserList(List<PhoneLoginUserBean.DataBean.UserListBean> user_list) {
//        this.user_list = user_list;
//        CacheUtil.writeString(CacheUtil.getUserPath(), userListCachekey, new Gson().toJson(user_list));
//
//    }


    public static class UserData {

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


}
