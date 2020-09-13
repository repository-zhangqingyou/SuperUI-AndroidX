package com.zqy.suidemo.config;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

import com.zqy.srequest.RequestManage;
import com.zqy.sui.BaseUIManage;
import com.zqy.sutils.UtilsManage;

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
        initModule();//
    }

    /**
     *
     */
    private void initModule() {
        //工具模组初始化
        UtilsManage.init(this);
        //网络请求模组初始化
        RequestManage.init(this);
        RequestManage.setDEBUG(true);
        BaseUIManage.init(this);
    }


}
