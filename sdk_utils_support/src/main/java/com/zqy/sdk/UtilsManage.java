package com.zqy.sdk;

import android.app.Application;
import android.text.TextUtils;

import com.zqy.sdk.logger.Logger;
import com.zqy.sdk.utilcode.util.Utils;


/**
 * 缓存工具类
 * Author: zhangqingyou
 * Date: 2020/4/7
 * Des:
 */
public class UtilsManage {
    private static Application application;
    private static String logTag;

    public static void init(Application application) {
        Utils.init(application);//初始化android工具类
        UtilsManage.application = application;
        if (TextUtils.isEmpty(logTag)) {
            Logger.init("日志");
        }

    }

    public static void setLogTag(String logTag) {
        UtilsManage.logTag = logTag;
        Logger.init(logTag);
    }

    public static Application getApplication() {
        return application;
    }


}
