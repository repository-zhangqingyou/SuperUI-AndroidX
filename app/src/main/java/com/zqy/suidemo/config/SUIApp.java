package com.zqy.suidemo.config;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

import com.zqy.sui.SUIManage;

/**
 * 作者: zhangqingyou
 * 时间: 2020/8/19 15:07
 * 描述:
 */
public class SUIApp extends Application {
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
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initModule();
    }

    /**
     *
     */
    private void initModule() {
        SUIManage.init(this);
       // SUIManage.initBugly("","SUI");
    }


}
