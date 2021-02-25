package com.zqy.superui.core.ui;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.blankj.utilcode.util.BarUtils;
import com.bumptech.glide.Glide;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Author: zhangqingyou
 * Date: 2020/4/7
 * Des:
 */
public abstract class AbsActivity extends AppCompatActivity {
    private String canonicalName;  //
    private Map<String, Fragment> stringFragmentMap;

    /**
     * 是否支持侧滑返回
     */
    // public static final String KEY_SUPPORT_SLIDE_BACK = "key_support_slide_back";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(getLayout());
        canonicalName = this.getClass().getCanonicalName();
        Log.d("BaseActivity", canonicalName);

        // 侧滑回调
//        if (isSupportSlideBack()) {
//            SlideBack.with(this)
//                    .haveScroll(true)
//                    .callBack(new SlideBackCallBack() {
//                        @Override
//                        public void onSlideBack() {
//                            finish();
//                        }
//                    })
//                    .register();
//        }


    }

    /**
     * 布局(可以是id  也可以是view)
     *
     * @return
     */
    public abstract Object getLayout();

    public void init(Object layout) {
        if (layout instanceof Integer) {
            setContentView((Integer) layout);
        } else if (layout instanceof View) {
            setContentView((View) layout);
        }

        initView();
        initData();
        listener();
    }

    /**
     * 初始化view
     */
    public abstract void initView();

    /**
     * 初始化数据
     */
    public abstract void initData();


    /**
     * view监听写在这里面
     */
    public abstract void listener();

    /**
     * 设置状态栏是否可见
     */
    public void setStatusBarVisibility(boolean isVisible) {
        BarUtils.setStatusBarVisibility(this, isVisible);//
    }

    /**
     * 透明状态栏
     */
    public void transparentStatusBar() {
        BarUtils.transparentStatusBar(this);//
    }

    /**
     * 设置状态栏是否为浅色模式
     *
     * @param isLightMode
     */
    public void setStatusBarLightMode(boolean isLightMode) {
        BarUtils.setStatusBarLightMode(this, isLightMode);
    }

    /**
     * 设置导航栏是否为浅色模式
     *
     * @param isLightMode
     */
    public void setNavBarLightMode(boolean isLightMode) {
        BarUtils.setNavBarLightMode(this, isLightMode);
    }

    /**
     * view设置PaddingTop为状态栏高度 （适用于状态栏全透明时）
     *
     * @param view
     */
    public void setPaddingTop(View view) {
        view.setPadding(0, BarUtils.getStatusBarHeight(), 0, 0);
    }


    /**
     * 权限回调
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    //


    /**
     * 显示指定Fragment（其他隐藏）
     */
    public void showFragment(@IdRes int containerViewId, Fragment fragment) {
        if (stringFragmentMap == null) stringFragmentMap = new LinkedHashMap();
        //开启事务
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        String canonicalName = fragment.getClass().getCanonicalName();
        if (stringFragmentMap.get(canonicalName) == null) {
            transaction.add(containerViewId, fragment, canonicalName);
            stringFragmentMap.put(canonicalName, fragment);
            Log.d("canonicalName", "---put---" + canonicalName);
        }


        //显示指定Fragment（其他隐藏）
        for (Map.Entry<String, Fragment> entry : stringFragmentMap.entrySet()) {
            if (canonicalName.equals(entry.getKey())) {
                Log.d("canonicalName", "---show---" + entry.getKey());
                transaction.show(entry.getValue());
            } else {
                Log.d("canonicalName", "---hide---" + entry.getKey());
                transaction.hide(entry.getValue());
            }
        }
        try {
            transaction.commit();
            Log.d("canonicalName", "---commit---");
        } catch (Exception e) {
            Log.d("canonicalName", "---Exception---" + e.getMessage());
        }
    }

    /**
     * 显示指定Fragment（其他隐藏）
     */
    public void showFragment(@IdRes int containerViewId, Class<? extends Fragment> clazzFragment) {
        if (stringFragmentMap == null) stringFragmentMap = new LinkedHashMap();
        String canonicalName = clazzFragment.getCanonicalName();
        if (stringFragmentMap.get(canonicalName) == null) {
            try {
                Fragment fragment = clazzFragment.newInstance();
                showFragment(containerViewId, fragment);
            } catch (IllegalAccessException e) {
                Log.d("canonicalName", "---Exception---" + e.toString());
            } catch (InstantiationException e) {
                Log.d("canonicalName", "---Exception---" + e.toString());
            }
        } else {
            showFragment(containerViewId, stringFragmentMap.get(canonicalName));
        }
    }

    /**
     * 查找Fragment
     *
     * @param clazzFragment
     * @return
     */
    public <T> T getFragment(Class<T> clazzFragment) {
        T fragment = null;
        String canonicalName = clazzFragment.getCanonicalName();
        if (stringFragmentMap != null && stringFragmentMap.containsKey(canonicalName)) {
            try {
                fragment = (T) stringFragmentMap.get(canonicalName);
            } catch (Exception e) {
            }
        }
        return fragment;
    }

    public Context getContext() {
        return this;
    }

    public Activity getActivity() {
        return this;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_VOLUME_UP:
                Log.d("LogUtils", "音量加");
                AudioManager mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
                mAudioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_RAISE, AudioManager.FX_FOCUS_NAVIGATION_UP);
                return true;
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                Log.d("LogUtils", "音量减");
                AudioManager mAudioManager2 = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
                mAudioManager2.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_LOWER, AudioManager.FX_FOCUS_NAVIGATION_UP);
                return true;
            case KeyEvent.KEYCODE_BACK:
                finish();
                return true;
        }
        return true;
    }


    @Override
    protected void onStop() {
        super.onStop();
        Glide.get(this).clearMemory();//清理Glide
    }


    /**
     * @return 是否支持侧滑返回
     */
//    protected boolean isSupportSlideBack() {
//        CoreSwitchBean page = getIntent().getParcelableExtra(CoreSwitchBean.KEY_SWITCH_BEAN);
//        return page == null || page.getBundle() == null || page.getBundle().getBoolean(KEY_SUPPORT_SLIDE_BACK, true);
//    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (stringFragmentMap != null) {
            stringFragmentMap.clear();
            stringFragmentMap = null;
        }
//        if (isSupportSlideBack()) {
//            SlideBack.unregister(this);
//        }
    }

}

