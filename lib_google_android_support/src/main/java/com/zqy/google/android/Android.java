package com.zqy.google.android;

import android.app.Application;

/**
 * 作者: zhangqingyou
 * 时间: 2020/9/17 10:30
 * 描述: 初始化包名
 */
public class Android {
    private static String packageName = "com.zqy.google.android.support";
    private static Application application;

    public static void init(Application application, String packageName) {
        Android.packageName = packageName;
        Android.application = application;
        ResourcesUtil.init(application);
    }

    public static String getPackageName() {
        return packageName;
    }

    public static Application getApplication() {
        return application;
    }
}