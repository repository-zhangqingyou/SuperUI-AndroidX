package com.zqy.suidemo.config;

import android.app.Application;
import android.content.Context;
import android.os.Environment;

import androidx.multidex.MultiDex;

import com.zqy.superhttp.SuperHttpManage;
import com.zqy.superui.SuperUIManage;
import com.zqy.superutils.SuperUtilsManage;


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

        SuperUtilsManage.init(getApplication());//工具初始化
        SuperUtilsManage.setLogTag("SuperUI-AndroidX");//初始化日志Tag
        SuperUtilsManage.setCache(Environment.getExternalStorageDirectory() + "/.SuperUI-AndroidX");//初始化缓存路径
        SuperHttpManage.init(getApplication());
        SuperHttpManage.setDebug(true);
        SuperUIManage.init(true, getApplication());

    }


}
