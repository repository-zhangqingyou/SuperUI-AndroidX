package com.zqy.superui.core.ui.activity.erro;

import android.text.TextUtils;

import com.zqy.superui.core.ui.activity.simple.SimpleToolbarActivity;
import com.zqy.superutils.JsonUtils;
import com.zqy.superutils.model.Crash;

/**
 * 作者: zhangqingyou
 * 时间: 2021/8/23
 * 描述: 异常捕获
 */
public abstract class SuperUIErrorActivity extends SimpleToolbarActivity {
    public final static String CRASH_KEY = "异常信息";//mTtListView

    @Override
    public void initData() {
        super.initData();
        String stringExtra = getIntent().getStringExtra(CRASH_KEY);
        if (!TextUtils.isEmpty(stringExtra)) {
            /*
                //手机信息
    private String cpuAbi;//CPU架构 如： "x86","armeabi-v7a", "armeabi"
    private String brand; //手机品牌 如： 雷电模拟器7.1.2
    private String model;//手机型号 如： xxx
    private String sdkVersionCode; //安卓sdk版本  如：25
    private String sdkVersionName; //安卓版本  如：7.1.2


    //错误信息
    private CrashType crashType;//异常类型   0
    private String errorType;//错误类型   java.lang.NullPointerException
    private String errorMessage;//简单的错误消息   Attempt to invoke virtual method 'void com.zqy.superui.core.widget.textview.SuperTextView.setOnClickListener(android.view.View$OnClickListener)' on a null object reference
    private String errorStack;//错误堆栈信息
             */

            Crash crash = JsonUtils.jsonToObject(stringExtra, Crash.class);
            onCrash(crash);
        }


    }

    public abstract void onCrash(Crash crash);

}
