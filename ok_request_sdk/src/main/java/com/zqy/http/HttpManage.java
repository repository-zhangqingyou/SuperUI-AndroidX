package com.zqy.http;

import android.app.Application;

import com.zqy.http.httpconnection.HttpApiCallback;
import com.zqy.http.okgo.OkGo;
import com.zqy.http.okgo.cache.CacheEntity;
import com.zqy.http.okgo.cache.CacheMode;
import com.zqy.http.okgo.cookie.CookieJarImpl;
import com.zqy.http.okgo.cookie.store.SPCookieStore;
import com.zqy.http.okgo.model.HttpHeaders;
import com.zqy.http.okhttp3.OkHttpClient;
import com.zqy.http.request.ApiCallback;

import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;


/**
 * 作者: zhangqingyou
 * 时间: 2020/9/14
 * 描述:
 */
public class HttpManage {
    private static Application application;
    private static boolean DEBUG;
    private static ApiCallback apiCallback;//okhttp所有接口回调
    private static HttpApiCallback httpApiCallback;//原生所有接口回调

    public static Application getApplication() {
        return application;
    }

    public static void init(Application application) {
        HttpManage.application = application;
        okGoInit();
    }


    public static boolean isDEBUG() {
        return DEBUG;
    }

    public static void setDEBUG(boolean dEBUG) {
        DEBUG = dEBUG;
    }


    private static void okGoInit() {
        try {
            Class c = Class.forName("java.lang.Daemons");
            Field maxField = c.getDeclaredField("MAX_FINALIZE_NANOS");
            maxField.setAccessible(true);
            maxField.set(null, Long.MAX_VALUE);
        } catch (Exception e) {

        }

        OkHttpClient.Builder buildeOkHttpClient = new OkHttpClient.Builder();

        buildeOkHttpClient.readTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);    //全局的读取超时时间
        buildeOkHttpClient.writeTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);    //全局的写入超时时间
        buildeOkHttpClient.connectTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);  //全局的连接超时时间
        buildeOkHttpClient.cookieJar(new CookieJarImpl(new SPCookieStore(getApplication())));
        OkHttpClient build = buildeOkHttpClient.build();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.put("charset", "utf-8");
        OkGo.getInstance().init(getApplication())
                //可以全局统一设置缓存模式,默认是不使用缓存,可以不传,具体其他模式看 github 介绍 https://github.com/jeasonlzy/
                .setCacheMode(CacheMode.NO_CACHE)
                //可以全局统一设置缓存时间,默认永不过期,具体使用方法看 github 介绍
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)
                .setOkHttpClient(build)
                .addCommonHeaders(httpHeaders)
                .setRetryCount(3);

    }


    /**
     * okhttp设置所有接口响应回调
     */
    public static ApiCallback getApiCallback() {
        return apiCallback;
    }
    /**
     * okhttp设置所有接口响应回调
     */
    public static void setApiCallback(ApiCallback apiCallback) {
        HttpManage.apiCallback = apiCallback;
    }
    /**
     * 原生设置所有接口响应回调
     */
    public static HttpApiCallback getHttpApiCallback() {
        return httpApiCallback;
    }
    /**
     * 原生设置所有接口响应回调
     */
    public static void setHttpApiCallback(HttpApiCallback httpApiCallback) {
        HttpManage.httpApiCallback = httpApiCallback;
    }
}
