package com.zqy.baseutil;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

import com.blankj.utilcode.util.DeviceUtils;
import com.blankj.utilcode.util.PhoneUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 作者: zhangqingyou
 * 时间: 2020/6/11 16:01
 * 描述: 获取手机系统内部信息工具类
 */
public class GetSystemInfoUtil {

    /**
     * 安卓10 获取设备标识
     *
     * @param //appIdsUpdater
     */
//    @SuppressLint("MissingPermission")
//    public static void getDeviceId(MiitHelper.AppIdsUpdater appIdsUpdater) {
//        MiitHelper.getInstance().getDeviceIds(UtilsManage.getApplication(), appIdsUpdater);
//    }
    @SuppressLint("MissingPermission")
    public static String getImeiArray() {
        return PhoneUtils.getIMEI();
    }

    /**
     * 获取IMEI串
     *
     * @param isReadCache 是否读取缓存
     * @return
     */
    @SuppressLint("MissingPermission")
    public static String getImeiArray(boolean isReadCache) {
        String 缓存IMEI串 = CacheUtil.readString(CacheUtil.getDeviceMD5Path(), "IMEI串");
        String IMEI串;
        if (isReadCache && !TextUtils.isEmpty(缓存IMEI串)) {
            IMEI串 = 缓存IMEI串;
        } else {
            ArrayList<String> stringArrayList = new ArrayList<>();
            if (Build.VERSION.SDK_INT < 21) {
                //如果获取系统的IMEI/MEID，14位代表meid 15位是imei
                String imeiOrMeid = getImeiOrMeid();
                if (!TextUtils.isEmpty(imeiOrMeid))
                    stringArrayList.add(imeiOrMeid);
                // 21版本是5.0，判断是否是5.0以上的系统  5.0系统直接获取IMEI1,IMEI2,MEID
            } else if (Build.VERSION.SDK_INT >= 21) {
                String imei1 = getImei1();
                String imei2 = getImei2();
                String meid = getMeid();
                if (!TextUtils.isEmpty(imei1))
                    stringArrayList.add(imei1);
                if (!TextUtils.isEmpty(imei2))
                    stringArrayList.add(imei2);
                if (!TextUtils.isEmpty(meid))
                    stringArrayList.add(meid);

            }


            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < stringArrayList.size(); i++) {
                String imei = stringArrayList.get(i);

                if (i == stringArrayList.size() - 1)
                    stringBuffer.append(imei);
                else
                    stringBuffer.append(imei + ",");

            }


            IMEI串 = stringBuffer.toString();
        }

        if (TextUtils.isEmpty(缓存IMEI串))
            CacheUtil.writeString(CacheUtil.getDeviceMD5Path(), "IMEI串", IMEI串);


        Log.d("GetSystemInfoUtil", "所有Imeid:" + IMEI串);

        return IMEI串;
    }

    /**
     * 覆盖写入imei到缓存
     */
    public static void writeImei() {
        CacheUtil.writeString(CacheUtil.getDeviceMD5Path(), "IMEI串", getImeiArray(false));
    }


    /**
     * 获取设备唯一标识
     *
     * @param isReadCache 是否读取缓存
     * @return
     */
    public static String getUniqueDeviceId(boolean isReadCache) {
        String 缓存设备码 = CacheUtil.readString(CacheUtil.getDeviceMD5Path(), "设备码");
        String 设备码;
        if (isReadCache && !TextUtils.isEmpty(缓存设备码)) {
            设备码 = 缓存设备码;
        } else {
            设备码 = DeviceUtils.getUniqueDeviceId();//无缓存  则生成
        }

        //如果本地无缓存则再次写入
        if (TextUtils.isEmpty(缓存设备码))
            CacheUtil.writeString(CacheUtil.getDeviceMD5Path(), "设备码", 设备码);

        return 设备码;
    }


    /**
     * 覆盖写入 设备唯一标识 到缓存
     */
    public static void writeImeiUniqueDeviceId() {
        CacheUtil.writeString(CacheUtil.getDeviceMD5Path(), "设备码", getUniqueDeviceId(false));
    }


    //获取第一个IMEI
    @SuppressLint("MissingPermission")
    public static String getImei1() {
        String imei = "";
        if (Build.VERSION.SDK_INT < 21) {//api小于21时只有这一个方法，所以获取到的值可能是meid,也可能是imei
            try {
                TelephonyManager tm = (TelephonyManager) UtilsManage.getApplication().getSystemService(Activity.TELEPHONY_SERVICE);
                imei = tm.getDeviceId();
            } catch (Exception e) {
            }
            return imei;
        } else {
            try {

                TelephonyManager tm = (TelephonyManager) UtilsManage.getApplication().getSystemService(Context.TELEPHONY_SERVICE);
                boolean ret = false;
                if (tm != null && tm.getClass() != null && tm.getClass().getDeclaredMethods() != null) {
                    for (Method m : tm.getClass().getDeclaredMethods()) {
                        if (m.getName().equals("getPhoneCount")) {
                            ret = true;
                            break;
                        }
                    }
                }
                if (ret) {
                    Map<String, String> map = getImeiAndMeid();
                    imei = map.get("imei1");
                }


            } catch (Exception e) {
            }
        }
        return imei;

    }

    //获取第二个IMEI，MEID。可能为空（单卡时）
    @SuppressLint("NewApi")
    public static String getImei2() {
        String imei2 = "";

        if (Build.VERSION.SDK_INT < 21) return imei2;
        try {
            TelephonyManager tm = (TelephonyManager) UtilsManage.getApplication().getSystemService(Context.TELEPHONY_SERVICE);
            boolean ret = false;
            if (tm != null && tm.getClass() != null && tm.getClass().getDeclaredMethods() != null) {
                for (Method m : tm.getClass().getDeclaredMethods()) {
                    if (m.getName().equals("getPhoneCount")) {
                        ret = true;
                        break;
                    }
                }
            }
            if (ret) {
                Map<String, String> map = getImeiAndMeid();
                imei2 = map.get("imei2");
            }

        } catch (Exception e) {
        }

        return imei2;
    }

    //获取meid
    public static String getMeid() {
        String meid = "";

        if (Build.VERSION.SDK_INT < 21) return meid;
        try {
            TelephonyManager tm = (TelephonyManager) UtilsManage.getApplication().getSystemService(Context.TELEPHONY_SERVICE);
            boolean ret = false;
            if (tm != null && tm.getClass() != null && tm.getClass().getDeclaredMethods() != null) {
                for (Method m : tm.getClass().getDeclaredMethods()) {
                    if (m.getName().equals("getPhoneCount")) {
                        ret = true;
                        break;
                    }
                }
            }
            if (ret) {
                Map<String, String> map = getImeiAndMeid();
                meid = map.get("meid");
            }
        } catch (Exception e) {
        }

        return meid;
    }

    /**
     * 系统4.0的时候
     * 获取手机IMEI 或者Meid
     *
     * @return 手机IMEI
     */
    @SuppressLint("MissingPermission")
    private static String getImeiOrMeid() {
        String meid = "";
        try {
            TelephonyManager tm = (TelephonyManager) UtilsManage.getApplication().getSystemService(Activity.TELEPHONY_SERVICE);
            if (tm != null) {
                meid = tm.getDeviceId();
            }
        } catch (Exception e) {
        }

        return meid;
    }

    /**
     * 拿到imei或者meid后判断是有多少位数
     *
     * @return
     */
    public static int getNumber() {
        int count = 0;
        long number = Long.parseLong(getImeiOrMeid().trim());

        while (number > 0) {
            number = number / 10;
            count++;
        }
        return count;
    }


    /**
     * Flyme 说 5.0 6.0统一使用这个获取IMEI IMEI2 MEID
     *
     * @return
     */
    @SuppressLint({"MissingPermission", "NewApi"})
    private static Map getImeiAndMeid() {
        Map<String, String> map = new HashMap<String, String>();
        TelephonyManager mTelephonyManager = (TelephonyManager) UtilsManage.getApplication().getSystemService(Activity.TELEPHONY_SERVICE);
        Class<?> clazz = null;
        Method method = null;//(int slotId)

        try {
            clazz = Class.forName("android.os.SystemProperties");
            method = clazz.getMethod("get", String.class, String.class);
            String gsm = (String) method.invoke(null, "ril.gsm.imei", "");


            String meid = (String) method.invoke(null, "ril.cdma.meid", "");
            map.put("meid", meid);
            if (!TextUtils.isEmpty(gsm)) {
                //the value of gsm like:xxxxxx,xxxxxx
                String imeiArray[] = gsm.split(",");
                if (imeiArray != null && imeiArray.length > 0) {
                    map.put("imei1", imeiArray[0]);

                    if (imeiArray.length > 1) {
                        map.put("imei2", imeiArray[1]);
                    } else {
                        map.put("imei2", mTelephonyManager.getDeviceId(1));
                    }
                } else {
                    map.put("imei1", mTelephonyManager.getDeviceId(0));
                    map.put("imei2", mTelephonyManager.getDeviceId(1));
                }
            } else {
                map.put("imei1", mTelephonyManager.getDeviceId(0));
                map.put("imei2", mTelephonyManager.getDeviceId(1));

            }

        } catch (Exception e) {

        }
        return map;
    }


    /**
     * 判断imei有没有被改变过
     *
     * @return
     */
    public static boolean isChangeDevice() {
        boolean isChangeDevice = true;
        String device = getImeiArray(true);
        if (!TextUtils.isEmpty(device)) {
            String[] split = device.split(",");
            List<String> stringList1 = Arrays.asList(split);

            String imei = getImeiArray(false);
            String[] split1 = imei.split(",");
            List<String> stringList2 = Arrays.asList(split1);
            for (String string1 : stringList1) {
                for (String string2 : stringList2) {
                    if (string1.equals(string2)) {
                        isChangeDevice = false;
                    }
                }
            }
        } else
            isChangeDevice = false;

        Log.d("GetSystemInfoUtil", "判断imei有没有被改变过:" + isChangeDevice);
        return isChangeDevice;
    }


}

