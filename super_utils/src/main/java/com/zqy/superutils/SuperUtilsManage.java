package com.zqy.superutils;

import android.app.Application;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.blankj.utilcode.util.Utils;
import com.bun.miitmdid.core.MdidSdkHelper;
import com.bun.miitmdid.interfaces.IIdentifierListener;
import com.bun.miitmdid.interfaces.IdSupplier;
import com.orhanobut.logger.Logger;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.beta.download.DownloadListener;
import com.tencent.bugly.beta.download.DownloadTask;
import com.tencent.bugly.beta.interfaces.BetaPatchListener;

import java.util.Locale;

import io.realm.Realm;


/**
 * 工具类
 * Author: zhangqingyou
 * Date: 2020/4/7
 * Des:
 */
public class SuperUtilsManage {
    private static Application application;
    private static String logTag;
    private static IdSupplier idSupplier;

    public static void init(Application application) {
        SuperUtilsManage.application = application;
        Utils.init(application);//初始化android工具类
        Realm.init(application);//realm数据库
        if (TextUtils.isEmpty(logTag)) {
            Logger.init("日志");
        }

        MdidSdkHelper.InitSdk(application, true, new IIdentifierListener() {
            @Override
            public void OnSupport(boolean b, IdSupplier idSupplier) {
                SuperUtilsManage.idSupplier = idSupplier;
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
        SuperUtilsManage.logTag = logTag;
        Logger.init(logTag);
    }

    /**
     * 设置缓存配置
     *
     * @param cacheRootPath 缓存路径
     */
    public static void setCache(String cacheRootPath) {
        CacheUtil.init(cacheRootPath);
    }

    /**
     * 移动安全联盟SDK获取设备信息
     *
     * @return
     */
    public static IdSupplier getIdSupplier() {
        return idSupplier;
    }


    /**
     * Bugly配置渠道信息已失效 先初始化Tinker再初始化Bugly
     *
     * @param isInitBetaPatch 是否初始化补丁更新
     * @param buglyAppId
     * @param channel         渠道ud
     */
    public static void initBugly(boolean isInitBetaPatch, String buglyAppId, String channel) {
        if (isInitBetaPatch) {
            // 设置是否显示弹窗提示用户重启
            Beta.canNotifyUserRestart = true;
            // 设置是否显示弹窗提示用户重启
            Beta.canNotifyUserRestart = true;
            //自动检查更新开关
            Beta.autoCheckUpgrade = true;
            //自动初始化开关
            Beta.autoInit = false;
            Beta.appChannel = channel;


            Beta.betaPatchListener = new BetaPatchListener() {
                @Override
                public void onPatchReceived(String patchFile) {
                    Log.d("initTinker", "补丁下载地址");
                    Toast.makeText(getApplication(), "补丁下载地址" + patchFile, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onDownloadReceived(long savedLength, long totalLength) {
                    String format = String.format(Locale.getDefault(), "%s %d%%",
                            Beta.strNotificationDownloading,
                            (int) (totalLength == 0 ? 0 : savedLength * 100 / totalLength));

                    Log.d("initTinker", format);

                    Toast.makeText(getApplication(), format,
                            Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onDownloadSuccess(String msg) {
                    Log.d("initTinker", "补丁下载成功");
                    Toast.makeText(getApplication(), "补丁下载成功", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onDownloadFailure(String msg) {
                    Log.d("initTinker", "补丁下载失败");
                    Toast.makeText(getApplication(), "补丁下载失败", Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onApplySuccess(String msg) {
                    Log.d("initTinker", "补丁应用成功");
                    Toast.makeText(getApplication(), "补丁应用成功", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onApplyFailure(String msg) {
                    Log.d("initTinker", "补丁应用失败");
                    Toast.makeText(getApplication(), "补丁应用失败", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onPatchRollback() {
                    Log.d("initTinker", "补丁回滚");
                    Toast.makeText(getApplication(), "补丁回滚", Toast.LENGTH_SHORT).show();
                }
            };

            /*注册下载监听，监听下载事件*/
            Beta.registerDownloadListener(new DownloadListener() {
                @Override
                public void onReceive(DownloadTask downloadTask) {
                    GlideCacheUtil.getInstance().clearImageAllCache(application);
                }

                @Override
                public void onCompleted(DownloadTask downloadTask) {
                    GlideCacheUtil.getInstance().clearImageAllCache(application);
                }

                @Override
                public void onFailed(DownloadTask downloadTask, int i, String s) {

                }
            });
            //必须要所有配置设置完毕才 安装tinker
            Beta.installTinker();
        }

        Bugly.init(getApplication(), buglyAppId, true);

        if (isInitBetaPatch) {
            Beta.init(getApplication(), true);
        }


    }
}
