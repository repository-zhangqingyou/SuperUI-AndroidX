package com.zqy.suidemo.baseui;

import android.os.Bundle;

import androidx.annotation.Nullable;



/**
 * Author: zhangqingyou
 * Date: 2020/4/7
 * Des:
 */
public abstract class BaseActivity extends com.zqy.sdk.sui.ui.BaseActivity {
    private String canonicalName;  //

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // JAnalyticsInterface.onPageStart(this, canonicalName);
    }


    @Override
    protected void onResume() {
        super.onResume();
       // JPushInterface.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
       // JPushInterface.onPause(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
      //  JAnalyticsInterface.onPageEnd(this, canonicalName);

    }

}

