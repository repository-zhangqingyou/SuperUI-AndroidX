package com.zqy.superutils.manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.text.TextUtils;

import com.blankj.utilcode.util.CacheDiskUtils;
import com.blankj.utilcode.util.EncodeUtils;
import com.blankj.utilcode.util.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * 缓存工具类
 * Author: zhangqingyou
 * Date: 2020/4/7
 * Des:
 */
public class CacheManager {


    //系统包目录
    private static String systemPath;
    //存储空间隐藏目录.zqy
    private static String storagePath;
    private static String rootDir = ".zqy";

    private static String cacheRootPath;

    protected static void init(String cacheRootPath) {
        CacheManager.cacheRootPath = cacheRootPath;
    }

    //*******************************系统包目录********************************

    private static String getSystemPath() {
        if (TextUtils.isEmpty(systemPath))
            systemPath = SuperUtilsManager.getApplication().getFilesDir().getAbsolutePath();
        return systemPath;
    }

    //用户信息缓存目录
    public static String getUserPath() {
        return getSystemPath() + "/user";
    }

    //数据缓存目录
    public static String getPackageDataPath() {
        return getSystemPath() + "/data";
    }

    //任务记录缓存目录
    public static String getPackageJobPath() {
        return getSystemPath() + "/job";
    }

    //*******************************存储空间********************************


    private static String getStoragePath() {
        if (TextUtils.isEmpty(storagePath)) {
            if (TextUtils.isEmpty(cacheRootPath)) {
                storagePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/.zqy/" + SuperUtilsManager.getApplication().getPackageName();
                //storagePath = Environment.getExternalStorageDirectory() + "/" + CacheUtil.cacheRootPath;
            } else {
                // storagePath = Environment.getExternalStorageDirectory() + "/" + CacheUtil.cacheRootPath + "/" + UtilsManage.getApplication().getPackageName();
                storagePath = cacheRootPath;
            }
        }
        return storagePath;
    }

    //缓存设备码 （不可清除）
    public static String getDeviceMD5Path() {
        return getStoragePath() + "/final/device";
    }

    //SD数据缓存目录(不可清除)
    public static String getFinalDataPath() {
        return getStoragePath() + "/final/data";
    }

    //数据库目录(不可清除)
    public static String getDatabasePath() {
        return getStoragePath() + "/final/database";
    }

    //其他数据缓存目录（可清除）
    public static String getStringDataPath() {
        return getStoragePath() + "/data";
    }

    //媒体数据缓存目录（可清除）
    public static String getMediaDataPath() {
        return getStoragePath() + "/media";
    }

    //图片缓存目录（可清除）
    public static String getImagePath() {
        return getStoragePath() + "/image";
    }

    //下载缓存目录（可清除）
    public static String getDownloadPath() {
        return getStoragePath() + "/download";
    }

    //程序错误日志
    public static String getErroLogPath() {
        return getStoragePath() + "/errolog";
    }

    /**
     * 写入信息
     */
    public static synchronized void writeString(String path, String key, String msg) {
        if (!TextUtils.isEmpty(path) && !TextUtils.isEmpty(key) && msg != null) {
            CacheDiskUtils.getInstance(new File(path)).put(key, new String(EncodeUtils.base64Encode(msg)));//加密写入缓存
        }
    }

    /**
     * 写入信息(集合)
     */
    public static synchronized void writeStringList(String path, String key, List<String> stringList) {
        if (!TextUtils.isEmpty(path) && !TextUtils.isEmpty(key) && stringList != null) {
            String[] toArray = stringList.toArray(new String[]{});
            CacheDiskUtils.getInstance(new File(path)).put(key, toArray);
        }
    }

    /**
     * 读取信息
     */
    public static synchronized String readString(String path, String key) {
        if (!TextUtils.isEmpty(path) && !TextUtils.isEmpty(key)) {
            String string = CacheDiskUtils.getInstance(new File(path)).getString(key);
            if (!TextUtils.isEmpty(string)) {
                return new String(EncodeUtils.base64Decode(string));//读取信息并解密
            }
        }
        return "";
    }

    /**
     * 读取信息(集合)
     */
    public static synchronized List<String> readStringList(String path, String key) {
        if (!TextUtils.isEmpty(path) && !TextUtils.isEmpty(key)) {
            Object serializable = CacheDiskUtils.getInstance(new File(path)).getSerializable(key);
            if (serializable != null && serializable instanceof String[]) {
                String[] toArray = (String[]) serializable;
                List list = Arrays.asList(toArray);
                List arrayList = new ArrayList(list);
                return arrayList;
            }
        }
        return new ArrayList<>();
    }

    /**
     * 删除指定目录信息
     */
    public static synchronized void delteDirString(String path) {
        if (!TextUtils.isEmpty(path))
            CacheDiskUtils.getInstance(new File(path)).clear();
    }

    /**
     * 删除指定目录指定key信息
     */
    public static synchronized void delteString(String path, String key) {
        if (!TextUtils.isEmpty(path) && !TextUtils.isEmpty(key))
            CacheDiskUtils.getInstance(new File(path)).remove(key);
    }

    /**
     * 获取目录大小
     *
     * @param path
     * @return
     */
    public static synchronized String getDirSize(String path) {
        if (!TextUtils.isEmpty(path))
            return FileUtils.getSize(path);
        return "";
    }


    /**
     * Context.MODE_PRIVATE：
     * 为默认操作模式，代表该文件是私有数据，只能被应用本身访问，
     * 在该模式下，写入的内容key同名会覆盖原文件的内容
     * <p>
     * Context.MODE_APPEND：模式会检查文件是否存在，存在就往文件追加内容，否则就创建新文件。
     * <p>
     * Context.MODE_WORLD_READABLE和Context.MODE_WORLD_WRITEABLE用来控制其他应用是否有权限读写该文件。
     * <p>
     * MODE_WORLD_READABLE：表示当前文件可以被其他应用读取；
     * <p>
     * MODE_WORLD_WRITEABLE：表示当前文件可以被其他应用写入。
     *
     * @param context
     * @param sharedPreferencesName xml文件名
     * @param key
     * @param value
     */
    public static void savaSPString(Context context, String sharedPreferencesName, String key, String value) {
        if (!TextUtils.isEmpty(sharedPreferencesName) && !TextUtils.isEmpty(key) && value != null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(sharedPreferencesName, Context.MODE_PRIVATE);
            sharedPreferences.edit().putString(key, value)
                    .commit();
        }
    }

    public static String getSPString(Context context, String sharedPreferencesName, String key) {
        if (!TextUtils.isEmpty(sharedPreferencesName) && !TextUtils.isEmpty(key)) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(sharedPreferencesName, Context.MODE_PRIVATE);
            return sharedPreferences.getString(key, "");
        }
        return "";

    }

    public static void delteSP(Context context, String sharedPreferencesName) {
        if (!TextUtils.isEmpty(sharedPreferencesName)) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(sharedPreferencesName, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.commit();
        }
    }
}

