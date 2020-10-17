package com.zqy.sdk;

import android.app.Application;
import android.text.TextUtils;

import com.bun.miitmdid.core.MdidSdkHelper;
import com.bun.miitmdid.interfaces.IIdentifierListener;
import com.bun.miitmdid.interfaces.IdSupplier;
import com.zqy.sdk.logger.Logger;
import com.zqy.sdk.utilcode.util.Utils;
import com.zqy.sdk.utils.CacheUtil;


/**
 * 缓存工具类
 * Author: zhangqingyou
 * Date: 2020/4/7
 * Des:
 */
public class UtilsManage {
    private static Application application;
    private static String logTag;
    private static IdSupplier idSupplier;

    public static void init(Application application) {
        UtilsManage.application = application;

        Utils.init(application);//初始化android工具类

        if (TextUtils.isEmpty(logTag)) {
            Logger.init("日志");
        }

        MdidSdkHelper.InitSdk(application, true, new IIdentifierListener() {
            @Override
            public void OnSupport(boolean b, IdSupplier idSupplier) {
                UtilsManage.idSupplier = idSupplier;
            }
        });
    }

    public static Application getApplication() {
        return application;
    }

    /**
     * 设置日志TAG
     *
     * @param logTag 日志TAG
     */
    public static void setLogTag(String logTag) {
        UtilsManage.logTag = logTag;
        Logger.init(logTag);
    }

    /**
     * 设置缓存配置
     *
     * @param rootDir     根文件夹名
     * @param packageName 包名（二级文件夹名）
     */
    public static void setCache(String rootDir, String packageName) {
        CacheUtil.init(rootDir, packageName);
    }

    /**
     * 移动安全联盟SDK获取设备信息
     *
     * @return
     */
    public static IdSupplier getIdSupplier() {
        return idSupplier;
    }
}
