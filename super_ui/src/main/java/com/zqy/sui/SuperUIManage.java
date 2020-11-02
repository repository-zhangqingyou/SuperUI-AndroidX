package com.zqy.sui;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.beta.download.DownloadListener;
import com.tencent.bugly.beta.download.DownloadTask;
import com.tencent.bugly.beta.interfaces.BetaPatchListener;
import com.weavey.loading.lib.LoadingLayout;
import com.xuexiang.xui.XUI;
import com.zqy.sutils.GlideCacheUtil;

import java.util.Locale;

/**
 * Author: zhangqingyou
 * Date: 2020/4/7
 * Des:
 */
public class SuperUIManage {
    private static Application application;
    private static boolean Debug;


    public static void init(boolean debug, Application application) {
        SuperUIManage.application = application;

        XUI.init(application); //初始化UI框架
        setDebug(debug);
        //初始化占位View
        getLoadingLayoutConfig()
                .setErrorText("出错啦~请稍后重试！")
                .setEmptyText("暂无数据~请稍后重试！")
                .setNoNetworkText("无网络连接，请检查您的网络···")
//                .setErrorImage(R.mipmap.define_error)
//                .setEmptyImage(R.mipmap.define_empty)
//                .setNoNetworkImage(R.mipmap.define_nonetwork)
//                .setAllTipTextColor(R.color.bui_black_light)
                .setAllTipTextSize(16)
                .setReloadButtonText("点我重试哦")
                .setReloadButtonTextSize(16)
                //.setReloadButtonTextColor(R.color.bui_black_light)
                .setReloadButtonWidthAndHeight(150, 40);

        //设置默认字体为华文行楷，这里写你的字体库
        //  XUI.getInstance().initFontStyle("fonts/hwxk.ttf");
        //工具模组初始化
        //UtilsManage.init(application);
        //网络请求模组初始化
//        HttpManage.init(application);
//        HttpManage.setDEBUG(true);


    }

    public static Application getApplication() {
        return application;
    }

    public static boolean isDebug() {
        return Debug;
    }

    public static void setDebug(boolean debug) {
        Debug = debug;
        XUI.debug(debug);  //开启UI框架调试日志
    }

    /**
     * 获取LoadingLayout配置
     *
     * @return
     */
    public static LoadingLayout.Config getLoadingLayoutConfig() {
        return LoadingLayout.getConfig();
    }

    /**
     * Bugly配置渠道信息已失效 先初始化Tinker再初始化Bugly
     */
    public static void initBugly(String buglyAppId, String channel) {
        //Tinker
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

        Bugly.init(getApplication(), buglyAppId, true);
        Beta.init(getApplication(), true);

    }
}
