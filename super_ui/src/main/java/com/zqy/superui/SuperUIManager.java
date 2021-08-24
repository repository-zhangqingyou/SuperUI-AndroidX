package com.zqy.superui;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;

import com.blankj.utilcode.util.ActivityUtils;
import com.xuexiang.xui.XUI;
import com.zqy.superui.core.ui.activity.erro.SuperUIErrorActivity;
import com.zqy.superui.loadinglayout.LoadingLayout;
import com.zqy.superutils.JsonUtils;
import com.zqy.superutils.impl.CrashCallback;
import com.zqy.superutils.manager.SuperUtilsManager;
import com.zqy.superutils.model.Crash;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Author: zhangqingyou
 * Date: 2020/4/7
 * Des:
 */
public class SuperUIManager {
    private static Application application;
    private static boolean debug;


    public static void init(boolean debug, Application application) {
        SuperUIManager.application = application;

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
                .setReloadButtonWH(150, 40);

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
        return debug;
    }

    public static void setDebug(boolean debug) {
        SuperUIManager.debug = debug;
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
     * 全局捕获异常页面
     *
     * @param act
     */
    public static void setErrorActivity(Class<? extends SuperUIErrorActivity> act) {
        SuperUtilsManager.setCrashCallback(new CrashCallback() {
            @Override
            public void onCrash(Crash crash) {
                Intent intent = new Intent(application, act);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(SuperUIErrorActivity.CRASH_KEY, JsonUtils.objectToJson(crash));
                application.startActivity(intent);

                List<Activity> activityList = ActivityUtils.getActivityList();
                for (Activity activity : activityList) {
                    if (!activity.getClass().getSimpleName().equals(act.getSimpleName())) {
                        activity.finish();
                    }
                }
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        //如果不关闭程序,会导致程序无法启动
                        android.os.Process.killProcess(android.os.Process.myPid());
                    }
                }, 1000);


            }
        });
    }


}
