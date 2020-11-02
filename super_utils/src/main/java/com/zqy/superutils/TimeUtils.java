package com.zqy.superutils;

import android.util.Log;

import java.util.Calendar;

/**
 * 作者: zhangqingyou
 * 时间: 2020/7/2 14:46
 * 描述:
 */
/**
 * 作者: zhangqingyou
 * 时间: 2020/7/2 14:46
 * 描述:
 */
public class TimeUtils {
    private static final String TAG ="时间" ;

    /**
     * 是否为昨天
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
     */
    public static boolean isToday(Long timeStamp) {
        Calendar todayCalendar = Calendar.getInstance();
        Log.d(TAG, "isToday: "+timeStamp);
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
     */
    public static boolean isThisYear(Long timeStamp) {
        Calendar thisyearCalender = Calendar.getInstance();
        Calendar calendar=Calendar.getInstance();
        Log.d(TAG, "isThisYear: "+thisyearCalender);
        calendar.setTimeInMillis(timeStamp);
        Log.d(TAG, "isThisYear: "+calendar);
        return  calendar.get(Calendar.YEAR)==(thisyearCalender.get(Calendar.YEAR));
    }
}
