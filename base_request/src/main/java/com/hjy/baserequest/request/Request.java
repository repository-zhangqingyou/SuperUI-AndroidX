package com.hjy.baserequest.request;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.hjy.baserequest.bean.DescAndCode;
import com.hjy.baserequest.data.UserData;
import com.hjy.baserequest.data.UserDataContainer;
import com.hjy.baserequest.util.RequestResponseUtil;
import com.hjy.baseutil.code.impl.RSAEncrypt;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.Callback;
import com.lzy.okgo.callback.FileCallback;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * APP和SDK通用请求
 * <p>
 * Author: zhangqingyou
 * Date: 2020/4/7
 * Des:
 */
public class Request {

    private static Request request;
    private boolean signSwitch = true;//请求参数加密开关

    private Request() {
    }

    public static Request getInstance() {
        if (request == null) {
            synchronized (Request.class) {
                if (request == null) {
                    request = new Request();
                }
            }
        }
        return request;
    }


    //--------------------------------------------------基本请求 start-------------------------------------------------
    public final int GET = 1;
    public final int POST = 2;
    public final int POST_JSON = 3;

    /**
     * 下载
     *
     * @param appUrl 下载地址
     */
    public void download(String appUrl, FileCallback callback) {
        OkGo.<File>get(appUrl)
                .tag(this)
                .execute(callback);
    }

    /**
     * json格式的请求方式
     *
     * @param url
     * @param json
     * @param callback
     */
    public void okgo_postJson(String url, String json, Callback callback) {
        OkGo.<String>post(url)
                //.tag(requestTag(url))
                .upJson(json)
                .execute(callback);
    }

    public void okgo_get(String url, Map<String, Object> params, Callback callback) {
        Map<String, String> param = new LinkedHashMap<>();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (key != null && value != null)
                param.put(entry.getKey(), entry.getValue().toString());
        }
        OkGo.<String>get(url)
                // .tag(requestTag(url))
                .params(param)
                .execute(callback);
    }

    public void okgo_post(String url, Map<String, Object> params, Callback callback) {
        Map<String, String> param = new LinkedHashMap<>();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (key != null && value != null)
                param.put(entry.getKey(), entry.getValue().toString());
        }

        OkGo.<String>post(url)
                //.tag(requestTag(url))
                .params(param)
                .execute(callback);

    }
    //--------------------------------------------------基本请求 end-------------------------------------------------

    //--------------------------------------------------加密方式和公共请求 start-------------------------------------------------


    /**
     * 公共请求
     *
     * @param map
     * @return
     */
    public Map<String, Object> getParams(Map<String, Object> map) {
        // 添加公共信息
        String user_token = "";
        String user_id = "";
        UserData userData = UserDataContainer.getInstance().getUserData();
        if (userData != null) {
            user_token = userData.getUser_token();
            user_id = userData.getUser_id();
        }
        map.put("user_id", user_id);
        map.put("request_time", String.valueOf(System.currentTimeMillis()));
        map.put("token", user_token);//
        return map;
    }

    /**
     * 加密请求参数
     *
     * @param params
     * @return
     */
    private String getSign(Map<String, Object> params) {
        String toJson = new Gson().toJson(params);
        String encrypt = RSAEncrypt.encryptPublicKey(toJson);//加密
        return encrypt;
    }


    /**
     * 添加加密丶公共请求后的通用请求
     * * <p>
     * * 在泛型类中声明了一个泛型方法，使用泛型E，这种泛型E可以为任意类型。可以类型与T相同，也可以不同。
     * * 由于泛型方法在声明的时候会声明泛型<E>，因此即使在泛型类中并未声明泛型，编译器也能够正确识别泛型方法中识别的泛型。
     *
     * @param requestType 请求方式
     * @param url         请求地址
     * @param param       请求参数合集
     * @param absCallback 请求结果回调
     */

    public void request(int requestType, String url, Map<String, Object> param, BaseCallback absCallback) {
        if (RequestResponseUtil.getIsRequest(url)) {//防止同一个接口频繁请求，当前请求响应后才能继续下个请求
            Map<String, Object> params = getParams(param);//公共请求

            String sign = getSign(params);

            if (signSwitch) {
                params.put("is_rsa", 0);//加密
                params.put("sign", sign);//加密
            } else {
                params.put("is_rsa", 10);//不加密
                params.put("sign", "");//加密
            }

            if (requestType == GET) {
                okgo_get(url, params, absCallback);
            } else if (requestType == POST) {
                okgo_post(url, params, absCallback);
            } else if (requestType == POST_JSON) {
                JsonObject jsonObjectString = new JsonObject();
                for (Map.Entry<String, Object> entry : params.entrySet()) {
                    jsonObjectString.addProperty(entry.getKey(), entry.getValue().toString());
                }
                okgo_postJson(url, jsonObjectString.toString(), absCallback);
            }
        } else {
            absCallback.onFinish();
        }
    }


    //--------------------------------------------------加密方式和公共请求 end-------------------------------------------------

    /**
     * 测试
     */
    public void test(JsonEntityCallback<DescAndCode> jsonBeanCallback) {
        Map<String, Object> param = new LinkedHashMap<>();
        request(POST, API.test, param, jsonBeanCallback);

    }


    /**
     * 获取短信验证码
     *
     * @param phone              手机号码
     * @param type               短信业务:app_quickLogin 快速登录，app_bindPhone 绑定手机号，app_forgetPassword  忘记密码，app_editpassword 修改密码
     * @param jsonEntityCallback
     */
//    public void smsVerificationCode(String phone, String type, JsonEntityCallback<DescAndCode> jsonEntityCallback) {
//        Map<String, Object> param = new LinkedHashMap<>();
//        param.put("phone", phone);
//        param.put("type", type);
//        request(POST, API.smsVerificationCode, param, jsonEntityCallback);
//    }

    /**
     * 游客登录
     *
     * @param jsonEntityCallback
     */
//    public void visitorLogin(JsonEntityCallback<AccountsLoginUserBean> jsonEntityCallback) {
//        Map<String, Object> param = new LinkedHashMap<>();
//        request(POST, API.visitorLogin, param, jsonEntityCallback);
//    }


    /**
     * 手机号登录
     *
     * @param phone              手机号码
     * @param code               验证码
     * @param jsonEntityCallback
     */
//    public void phoneLogin(String phone, String code, JsonEntityCallback<PhoneLoginUserBean> jsonEntityCallback) {
//        JsonObject jsonObject = new JsonObject();
//        jsonObject.addProperty("phone", phone);
//        jsonObject.addProperty("code", code);
//        request(POST, API.phoneLogin, jsonObject, jsonEntityCallback);
//    }


    /**
     * 账号密码登录
     *
     * @param account            账号
     * @param password           密码
     * @param jsonEntityCallback
     */
//    public void accountPasswordLogin(String account, String password, JsonEntityCallback<AccountsLoginUserBean> jsonEntityCallback) {
//        Map<String, Object> param = new LinkedHashMap<>();
//        param.put("account", account);
//        param.put("password", password);
//        request(POST, API.accountPasswordLogin, param, jsonEntityCallback);
//    }

}
