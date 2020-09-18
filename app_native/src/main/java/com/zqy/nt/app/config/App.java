package com.zqy.nt.app.config;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

import com.orhanobut.logger.Logger;
import com.zqy.googlelib.AndroidX;


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
        AndroidX.init(getApplication(),"123");
        initModule();
    }

    /**
     *
     */
    private void initModule() {


    }


}
