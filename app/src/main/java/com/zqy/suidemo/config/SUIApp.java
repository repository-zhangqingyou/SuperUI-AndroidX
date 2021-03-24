package com.zqy.suidemo.config;

import android.app.Application;
import android.content.Context;
import android.os.Environment;
import android.util.Log;

import androidx.multidex.MultiDex;

import com.blankj.utilcode.util.AppUtils;
import com.google.gson.JsonSyntaxException;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.zqy.suidemo.database.DaoMaster;
import com.zqy.suidemo.database.DaoSession;
import com.zqy.superhttp.SuperHttpManager;
import com.zqy.superhttp.http.ApiCallbackService;
import com.zqy.superui.SuperUIManager;
import com.zqy.superutils.ToastUtil;
import com.zqy.superutils.database.GreenDaoContext;
import com.zqy.superutils.manager.SuperUtilsManager;

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

        SuperUtilsManager.init(getApplication());//工具初始化
        SuperUtilsManager.setLogTag("SuperUI-AndroidX");//初始化日志Tag
        SuperUtilsManager.setCache(Environment.getExternalStorageDirectory() + "/.SuperUI-AndroidX");//初始化缓存路径
        SuperHttpManager.init(getApplication());
        SuperHttpManager.setDebug(true);
        SuperHttpManager.setApiCallbackService(new ApiCallbackService() {
            @Override
            public void onStart(String baseUrl, String endUrl, Request request) {
            }

            @Override
            public void onFinish(String msg) {
            }

            @Override
            public void onError(String baseUrl, String endUrl, Response response) {
                Throwable exception = response.getException();
                okhttp3.Response responseRawResponse = response.getRawResponse();
                String requestString = "";//请求信息
                String bodyString = "";//返回信息
                if (responseRawResponse != null) {
                    if (responseRawResponse.request() != null) {
                        requestString = responseRawResponse.request().toString();
                        Log.d(endUrl + "出错啦！--请求信息：", requestString);
                    }

                    if (responseRawResponse.body() != null) {
                        bodyString = responseRawResponse.body().toString();
                        Log.d(endUrl + "出错啦！--返回信息：", bodyString);
                    }

                }

                if (exception != null) {
                    ToastUtil.toast(endUrl + "出错啦！--错误信息：" + exception.getMessage());
                    Log.d(endUrl + "出错啦！--错误信息：", exception.getMessage());

                    if (exception instanceof JsonSyntaxException) {
                        // 极光计数事件（接口返回json数据解析错误使用）
                        //  JEventUtils.onJsonCountEventError(baseUrl, requestString, bodyString, exception.getMessage());
                    } else {
                        //极光计数事件（接口连接失败使用）
                        // JEventUtils.onCountEventError(baseUrl, requestString, bodyString, exception.getMessage());
                    }
                }

            }

            @Override
            public void onSuccess(String baseUrl, String endUrl, Response response) {
                okhttp3.Response responseRawResponse = response.getRawResponse();
                String requestString = "";//请求信息
                String bodyString = "";//返回信息
                if (responseRawResponse != null) {
                    if (responseRawResponse.request() != null) {
                        requestString = responseRawResponse.request().toString();
                    }
                    if (responseRawResponse.body() != null) {
                        bodyString = responseRawResponse.body().toString();
                    }

                }
                Log.d(endUrl, "成功啦！请求信息:" + requestString + "\n返回信息：" + bodyString);
            }
        });
        SuperUIManager.init(true, getApplication());


        // 常规SQLite数据库
        GreenDaoContext greenDaoContext = new GreenDaoContext(this);//里面有个上下文GreenDaoContext继承了ContextWrapper,里面设置了数据库路径，
        // greenDaoContext.setCurrentUserId(userId);
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(greenDaoContext, AppUtils.getAppName() + "数据库");//
        Database db = helper.getWritableDb();
        // 数据库db = helper.getEncryptedWritableDb（“encryption-key”）;
        DaoSession daoSession = new DaoMaster(db).newSession();
        SuperUtilsManager.initGreenDB(daoSession);

    }


}
