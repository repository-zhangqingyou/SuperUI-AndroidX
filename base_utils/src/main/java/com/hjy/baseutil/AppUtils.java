package com.hjy.baseutil;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import com.hjy.baseutil.countdownutil.CountdownTimerTask;

public class AppUtils {
    /**
     * 获取应用程序名称
     */
    public static String getAppName() {
        String appName = "";
        try {
            PackageManager pm = UtilsManage.getApplication().getPackageManager();
            PackageInfo pi = pm.getPackageInfo(UtilsManage.getApplication().getPackageName(), 0);
            appName = pi == null ? null : pi.applicationInfo.loadLabel(pm).toString();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();

        }
        Log.d("AppUtils", "app名字：" + appName);
        Log.d("AppUtils", "app包名：" + UtilsManage.getApplication().getPackageName());
        return appName;
    }


    /**
     * 读取Application下的标签值
     *
     * @return
     */
    public static String getMetaData(String metaDataName) {
        String value = "";
        PackageManager pm = UtilsManage.getApplication().getPackageManager();
        String packageName = UtilsManage.getApplication().getPackageName();
        try {
            ApplicationInfo ai = pm.getApplicationInfo(packageName, PackageManager.GET_META_DATA);
            value = String.valueOf(ai.metaData.get(metaDataName));
        } catch (PackageManager.NameNotFoundException e) {
        }
        Log.d("AppUtils", "Application下的标签值 -- name:" + metaDataName + " -- value:" + value);

        return value;

    }


    /**
     * 关闭所有Activity 再结束进程
     */
    public static void exitApp() {
        ActivitysUtil.finishAllActivities();
        CountdownTimerTask countdownTimerTask = new CountdownTimerTask();
        countdownTimerTask.stateCountdownExecution(new Runnable() {
            @Override
            public void run() {
                System.exit(0);
            }
        }, 0.2f);

    }


}
