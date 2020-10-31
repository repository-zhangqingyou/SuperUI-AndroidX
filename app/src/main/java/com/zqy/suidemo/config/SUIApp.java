package com.zqy.suidemo.config;

import android.app.Application;
import android.content.Context;
import android.os.Environment;

import androidx.multidex.MultiDex;

import com.zqy.srequest.HttpManage;
import com.zqy.sui.SuperUIManage;
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
        initModule();
    }

    /**
     *
     */
    private void initModule() {

        UtilsManage.init(getApplication());//工具初始化
        UtilsManage.setLogTag("SuperUI-AndroidX");//初始化日志Tag
        UtilsManage.setCache(Environment.getExternalStorageDirectory() + "/.SuperUI-AndroidX");//初始化缓存路径
        HttpManage.init(getApplication());
        HttpManage.setDEBUG(true);
        SuperUIManage.init(getApplication());

    }


}
