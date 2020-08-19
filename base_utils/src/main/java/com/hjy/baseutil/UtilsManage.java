package com.hjy.baseutil;

import android.app.Application;

import com.blankj.utilcode.util.Utils;
/**
 * 缓存工具类
 * Author: zhangqingyou
 * Date: 2020/4/7
 * Des:
 */
public class UtilsManage {
    private static Application application;

    public static void init(Application application) {
        Utils.init(application);//初始化android工具类
        UtilsManage.application = application;
    }

    public static Application getApplication() {
        return application;
    }


}
