package com.zqy.googlelib;

import android.app.Application;

/**
 * 作者: zhangqingyou
 * 时间: 2020/9/17 10:30
 * 描述: 初始化包名
 */
public class AndroidX {
    private static String packageName;
    private static Application application;
    public static void init(Application application,String packageName) {
        AndroidX.packageName = packageName;
        AndroidX.application = application;
        UtilResources.init(application);
    }

    public static String getPackageName() {
        return packageName;
    }

    public static Application getApplication() {
        return application;
    }
}
