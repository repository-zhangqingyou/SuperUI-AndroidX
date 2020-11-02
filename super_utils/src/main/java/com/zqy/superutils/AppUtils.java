package com.zqy.superutils;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import com.zqy.superutils.countdownutil.CountdownTimerTask;

public class AppUtils {
    private final static String TAG = "AppUtils";
    /**
     * 获取应用程序名称
     */
    public static String getAppName() {
        String appName = "";
        try {
            PackageManager pm = SuperUtilsManage.getApplication().getPackageManager();
            PackageInfo pi = pm.getPackageInfo(SuperUtilsManage.getApplication().getPackageName(), 0);
            appName = pi == null ? null : pi.applicationInfo.loadLabel(pm).toString();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();

        }
        Log.d(TAG, "app名字：" + appName);
        Log.d(TAG, "app包名：" + SuperUtilsManage.getApplication().getPackageName());
        return appName;
    }


    /**
     * 读取Application下的标签值
     *
     * @return
     */
    public static String getMetaData(String metaDataName) {
        String value = "";
        PackageManager pm = SuperUtilsManage.getApplication().getPackageManager();
        String packageName = SuperUtilsManage.getApplication().getPackageName();
        try {
            ApplicationInfo ai = pm.getApplicationInfo(packageName, PackageManager.GET_META_DATA);
            value = String.valueOf(ai.metaData.get(metaDataName));
        } catch (PackageManager.NameNotFoundException e) {
        }
        Log.d(TAG, "Application下的标签值 -- name:" + metaDataName + " -- value:" + value);

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
