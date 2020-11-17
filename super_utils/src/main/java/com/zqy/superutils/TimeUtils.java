package com.zqy.superutils;

import android.util.Log;

import java.util.Calendar;
import java.util.Locale;

/**
 * 作者: zhangqingyou
 * 时间: 2020/7/2 14:46
 * 描述:
 */
public class TimeUtils {
    private static final String TAG = "时间";

    /**
     * 是否为昨天
     *
     * @param timeStamp 单位 毫秒
     * @return
     */
    public static boolean isYesterday(Long timeStamp) {
        Calendar todayCalendar = Calendar.getInstance();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeStamp);
        if (calendar.get(Calendar.YEAR) == (todayCalendar.get(Calendar.YEAR))) {
            int diffDay = todayCalendar.get(Calendar.DAY_OF_YEAR) - calendar.get(Calendar.DAY_OF_YEAR);
            return diffDay == 1;
        }
        return false;
    }

    /**
     * 是否为今天
     *
     * @param timeStamp 单位 毫秒
     * @return
     */
    public static boolean isToday(Long timeStamp) {
        Calendar todayCalendar = Calendar.getInstance();
        Log.d(TAG, "isToday: " + timeStamp);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeStamp);
        if (calendar.get(Calendar.YEAR) == (todayCalendar.get(Calendar.YEAR))) {
            int diffDay = todayCalendar.get(Calendar.DAY_OF_YEAR) - calendar.get(Calendar.DAY_OF_YEAR);
            return diffDay == 0;
        }
        return false;
    }

    /**
     * 是否为今年
     *
     * @param timeStamp 单位 毫秒
     * @return
     */
    public static boolean isThisYear(Long timeStamp) {
        Calendar thisyearCalender = Calendar.getInstance();
        Calendar calendar = Calendar.getInstance();
        Log.d(TAG, "isThisYear: " + thisyearCalender);
        calendar.setTimeInMillis(timeStamp);
        Log.d(TAG, "isThisYear: " + calendar);
        return calendar.get(Calendar.YEAR) == (thisyearCalender.get(Calendar.YEAR));
    }

    /**
     * 格式化播放时间
     *
     * @param seconds 秒数
     *                时间＜1小时显示分秒，显示样式 00:20
     *                时间 ≥1小时显示时分秒，显示样式 01:11:12
     */
    public static String formatSeconds(long seconds) {
        String standardTime;
        if (seconds <= 0) {
            standardTime = "00:00";
        } else if (seconds < 60) {
            standardTime = String.format(Locale.getDefault(), "00:%02d", seconds % 60);
        } else if (seconds < 3600) {
            standardTime = String.format(Locale.getDefault(), "%02d:%02d", seconds / 60, seconds % 60);
        } else {
            standardTime = String.format(Locale.getDefault(), "%02d:%02d:%02d", seconds / 3600, seconds % 3600 / 60, seconds % 60);
        }
        return standardTime;
    }


    /**
     * 获取格式化时间
     *
     * @param timeStamp 时间戳 单位 毫秒
     * @return
     */
    public static String formatTaime(Long timeStamp) {
        String millis2String = "";
        if (TimeUtils.isYesterday(timeStamp)) {
            //是昨天
            millis2String = com.blankj.utilcode.util.TimeUtils.millis2String(timeStamp, "HH:mm");
            return "昨天" + millis2String;
        } else {
            if (TimeUtils.isToday(timeStamp)) {
                //是今天
                millis2String = com.blankj.utilcode.util.TimeUtils.millis2String(timeStamp, "HH:mm");
                return millis2String;
            } else {
                millis2String = com.blankj.utilcode.util.TimeUtils.millis2String(timeStamp, "MM.dd HH:mm");
                return millis2String;
            }
        }
    }
}
