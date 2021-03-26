package com.zqy.supernet;

import android.app.Application;

import com.zqy.supernet.help.AskConfigure;
import com.zqy.supernet.help.OnApiListener;
import com.zqy.supernet.help.RetrofitHelp;

import retrofit2.Retrofit;


/**
 * 作者: zhangqingyou
 * 时间: 2021/3/26
 * 描述:
 */
public class SuperNetManager {
    private static Application application;
    private static boolean debug;
    private static AskConfigure askConfigure;
    private static OnApiListener onApiListener;

    public static void init(Application application, AskConfigure askConfigure) {
        SuperNetManager.application = application;
        SuperNetManager.askConfigure = askConfigure;


    }

    public static Application getApplication() {
        return application;
    }

    public static boolean isDebug() {
        return debug;
    }

    public static void setDebug(boolean debug) {
        SuperNetManager.debug = debug;
    }

    public static AskConfigure getAskConfigure() {
        return askConfigure;
    }


    public static <S> S getApiService(Class<S> service, String baseUrl) {
        Retrofit retrofit = new RetrofitHelp().getRetrofit(baseUrl);
        return retrofit.create(service);
    }

    /**
     * 获取全局响应结果回调
     *
     * @return
     */
    public static OnApiListener getOnApiListener() {
        return onApiListener;
    }

    /**
     * 设置全局响应结果监听
     *
     * @param onApiListener
     */
    public static void setOnApiListener(OnApiListener onApiListener) {
        SuperNetManager.onApiListener = onApiListener;
    }


}
