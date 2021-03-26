package com.zqy.supernet;

import android.app.Application;

import com.zqy.supernet.help.AskConfigure;
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


    public static <S> S getApiService(Class<S> service) {
        Retrofit retrofit = RetrofitHelp.getInstance().getRetrofit();
        return retrofit.create(service);
    }


}
