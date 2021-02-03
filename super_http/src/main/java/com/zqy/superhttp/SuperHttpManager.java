package com.zqy.superhttp;

import android.app.Application;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.cookie.CookieJarImpl;
import com.lzy.okgo.cookie.store.SPCookieStore;
import com.lzy.okgo.model.HttpHeaders;
import com.zqy.superhttp.request.ApiCallbackService;

import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;


/**
 * 作者: zhangqingyou
 * 时间: 2020/9/14
 * 描述:
 */
public class SuperHttpManager {
    private static Application application;
    private static boolean debug;
    private static ApiCallbackService apiCallbackService;


    public static void init(Application application) {
        SuperHttpManager.application = application;
        okGoInit();
    }

    public static Application getApplication() {
        return application;
    }

    public static boolean isDebug() {
        return debug;
    }

    public static void setDebug(boolean debug) {
        SuperHttpManager.debug = debug;
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
     * 接口全局响应回调
     */
    public static ApiCallbackService getApiCallbackService() {
        return apiCallbackService;
    }
    /**
     * 接口全局响应回调
     */
    public static void setApiCallbackService(ApiCallbackService apiCallbackService) {
        SuperHttpManager.apiCallbackService = apiCallbackService;
    }
}
