package com.zqy.sdk;

import android.app.Application;

import com.blankj.utilcode.util.Utils;
import com.orhanobut.logger.Logger;


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
        Logger.init("日志");
    }

    public static Application getApplication() {
        return application;
    }


}
