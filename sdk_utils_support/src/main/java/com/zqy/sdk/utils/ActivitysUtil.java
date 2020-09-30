package com.zqy.sdk.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.text.TextUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Author: zhangqingyou
 * Date: 2020/4/25 9:40
 * Des:
 */
public class ActivitysUtil {

    public static List<Activity> getAllActivitys() {
        List<Activity> list = new ArrayList<>();
        try {
            Class<?> activityThread = Class.forName("android.app.ActivityThread");
            Method currentActivityThread = activityThread.getDeclaredMethod("currentActivityThread");
            currentActivityThread.setAccessible(true);
            //获取主线程对象
            Object activityThreadObject = currentActivityThread.invoke(null);
            Field mActivitiesField = activityThread.getDeclaredField("mActivities");
            mActivitiesField.setAccessible(true);
            Map<Object, Object> mActivities = (Map<Object, Object>) mActivitiesField.get(activityThreadObject);
            for (Map.Entry<Object, Object> entry : mActivities.entrySet()) {
                Object value = entry.getValue();
                Class<?> activityClientRecordClass = value.getClass();
                Field activityField = activityClientRecordClass.getDeclaredField("activity");
                activityField.setAccessible(true);
                Object o = activityField.get(value);
                list.add((Activity) o);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 关闭所有页面退出
     */
    public static void finishAllActivities() {
        List<Activity> activityList = getAllActivitys();
        for (int i = activityList.size() - 1; i >= 0; --i) {// remove from top
            Activity activity = activityList.get(i);
            activity.finish();
            activity.overridePendingTransition(0, 0);
        }
    }

    /**
     * ,返回true，为显示,否则不是
     */
    /**
     * 判断某个界面是否在前台
     *
     * @param context
     * @param activityClassName 当前Activity名
     * @return
     */
    public static boolean isForeground(Activity context, String activityClassName) {
        if (context == null || TextUtils.isEmpty(activityClassName))
            return false;
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> list = am.getRunningTasks(1);
        if (list != null && list.size() > 0) {
            ComponentName cpn = list.get(0).topActivity;
            return activityClassName.equals(cpn.getClassName());
        }
        return false;

    }
}
