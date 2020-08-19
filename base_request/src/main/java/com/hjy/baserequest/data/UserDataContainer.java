package com.hjy.baserequest.data;

import android.util.Log;

import com.google.gson.Gson;
import com.hjy.baseutil.CacheUtil;


/**
 * 用户数据容器类
 * Author: zhangqingyou
 * Date: 2020/5/12 11:30
 * Des:
 */
public class UserDataContainer {
    private final String userinfoCachekey = "userinfo";
    private final String userListCachekey = "user_list";
    private static UserDataContainer mInstance;
    private UserData userData;//用户信息
    // private List<PhoneLoginUserBean.DataBean.UserListBean> user_list;//用户账号列表

    private UserDataContainer() {

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
            if (userJson != null) {
                userData = new Gson().fromJson(userJson, UserData.class);
                Log.d("UserDataContainer", "userData:" + userData.toString());
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


}
