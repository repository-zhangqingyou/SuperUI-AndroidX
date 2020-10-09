package com.zqy.sdk.utils.countdownutil;

/**
 * Author :leilei on 2016/11/11 2326.
 */
public class TimeTools {

    //毫秒换成00:00:00
    public static String getCountTimeByLong(long finishTime) {
        int totalTime = (int) (finishTime / 1000);//秒
        int day = 0, hour = 0, minute = 0, second = 0;
        //1天(d)=86400秒(s)
        if (86400 <= totalTime) {
            day = totalTime / 86400;
            totalTime = totalTime - 86400 * day;
        }
        if (3600 <= totalTime) {
            hour = totalTime / 3600;
            totalTime = totalTime - 3600 * hour;
        }
        if (60 <= totalTime) {
            minute = totalTime / 60;
            totalTime = totalTime - 60 * minute;
        }
        if (0 <= totalTime) {
            second = totalTime;
        }
        StringBuilder sb = new StringBuilder();
        if (day < 10) {
            sb.append("0").append(day).append("天");
        } else {
            sb.append(day).append("天");
        }

        if (hour < 10) {
            sb.append("0").append(hour).append("时");
        } else {
            sb.append(hour).append("时");
        }
        if (minute < 10) {
            sb.append("0").append(minute).append("分");
        } else {
            sb.append(minute).append("分");
        }
        if (second < 10) {
            sb.append("0").append(second).append("秒");
        } else {
            sb.append(second).append("秒");

        }
        return sb.toString();

    }

}
