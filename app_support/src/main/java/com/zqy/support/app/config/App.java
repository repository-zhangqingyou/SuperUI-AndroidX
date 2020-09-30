package com.zqy.support.app.config;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import com.orhanobut.logger.Logger;
import com.zqy.google.android.Android;
import com.zqy.sdk.UtilsManage;


/**
 * 作者: zhangqingyou
 * 时间: 2020/8/19 15:07
 * 描述:
 */
public class App extends Application {
    private static Application application;

    /**
     * 获取Application
     */
    public static Application getApplication() {
        return application;
    }

    @Override
    public void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        application = this;
        MultiDex.install(this);
        Logger.init("androidx库重构");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // AndroidX.init(getApplication(),"com.zqy.googlelib");//com.zqy.googlelib
        initModule();
        Android.init(this,getPackageName());
    }

    /**
     *
     */
    private void initModule() {
        UtilsManage.init(getApplication());

    }


}
