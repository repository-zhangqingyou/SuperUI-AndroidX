package com.zqy.suidemo.config;

import android.app.Application;
import android.content.Context;
import android.os.Environment;

import androidx.multidex.MultiDex;

import com.blankj.utilcode.util.AppUtils;
import com.zqy.suidemo.database.DaoMaster;
import com.zqy.suidemo.database.DaoSession;
import com.zqy.superhttp.SuperHttpManage;
import com.zqy.superui.SuperUIManage;
import com.zqy.superutils.database.GreenDaoContext;
import com.zqy.superutils.manager.SuperUtilsManage;

import org.greenrobot.greendao.database.Database;


/**
 * 作者: zhangqingyou
 * 时间: 2020/8/19 15:07
 * 描述:
 */
public class SUIApp extends Application {
    private static Application application;

    /**
     * 获取Application
     */
    public static Application getApplication() {
        return application;
    }

    @Override
    public void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        application = this;
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initModule();
    }

    /**
     *
     */
    private void initModule() {

        SuperUtilsManage.init(getApplication());//工具初始化
        SuperUtilsManage.setLogTag("SuperUI-AndroidX");//初始化日志Tag
        SuperUtilsManage.setCache(Environment.getExternalStorageDirectory() + "/.SuperUI-AndroidX");//初始化缓存路径
        SuperHttpManage.init(getApplication());
        SuperHttpManage.setDebug(true);
        SuperUIManage.init(true, getApplication());


        // 常规SQLite数据库
        GreenDaoContext greenDaoContext = new GreenDaoContext(this);//里面有个上下文GreenDaoContext继承了ContextWrapper,里面设置了数据库路径，
        // greenDaoContext.setCurrentUserId(userId);
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(greenDaoContext, AppUtils.getAppName() + "数据库");//
        Database db = helper.getWritableDb();
        // 数据库db = helper.getEncryptedWritableDb（“encryption-key”）;
        DaoSession daoSession = new DaoMaster(db).newSession();
        SuperUtilsManage.initGreenDB(daoSession);

    }


}
