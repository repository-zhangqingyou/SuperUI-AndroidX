package com.zqy.superui.core.ui.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.BarUtils;
import com.trello.rxlifecycle4.components.support.RxFragment;
import com.zqy.superui.R;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Author: zhangqingyou
 * Date: 2020/4/7
 * Des:
 */
public abstract class SuperUIFragment extends RxFragment {
    public View mRootView;
    private Map<String, Fragment> stringFragmentMap;
    public int _8sdp, _10sdp, _12sdp;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ARouter.getInstance().inject(this);//获取传参
        _8sdp = getResources().getDimensionPixelSize(R.dimen._8sdp);
        _10sdp = getResources().getDimensionPixelSize(R.dimen._10sdp);
        _12sdp = getResources().getDimensionPixelSize(R.dimen._12sdp);
        if (mRootView == null) {
            mRootView = inflater.inflate(getLayoutId(), container, false);
            initView(mRootView);
            initData();
            listener();
        }
        return mRootView;
    }


    /**
     * 布局的资源id
     *
     * @return
     */
    @LayoutRes
    public abstract int getLayoutId();

    /**
     * 初始化view
     */
    public abstract void initView(View mRootView);

    /**************************************************************
     *  自定义的回调方法，子类需求重写
     *************************************************************/

    /**
     * 当前fragment可见状态发生变化时会回调该方法
     * 如果当前fragment是第一次加载，等待onCreateView后才会回调该方法，其它情况回调时机跟 {@link #setUserVisibleHint(boolean)}一致
     * 在该回调方法中你可以做一些加载数据操作，甚至是控件的操作，因为配合fragment的view复用机制，你不用担心在对控件操作中会报 null 异常
     *
     * @param isVisible true  不可见 -> 可见
     *                  false 可见  -> 不可见
     */


    public abstract void onFragmentVisibleChange(boolean isVisible);

    /**
     * 初始化数据
     */
    public abstract void initData();


    /**
     * view监听
     */
    public abstract void listener();


    protected <T extends View> T findViewById(int id) {
        return mRootView.findViewById(id);
    }


    /**
     * 消息提示
     *
     * @param text
     */
    public void toast(String text, int duration, int gravity) {
        if (!Objects.requireNonNull(getActivity()).isFinishing() && !TextUtils.isEmpty(text)) {
            Toast toast = Toast.makeText(getContext(), text, duration);
            toast.setGravity(gravity, 0, 0);
            toast.show();
        }
    }

    public void toast(String text, int duration) {
        toast(text, duration, Gravity.CENTER);
    }

    public void toast(String text) {
        toast(text, Toast.LENGTH_SHORT, Gravity.CENTER);
    }

    public void toastBottom(String text) {
        toast(text, Toast.LENGTH_LONG, Gravity.BOTTOM);
    }

    public void toastTop(String text) {
        toast(text, Toast.LENGTH_LONG, Gravity.TOP);
    }


    private boolean isVisible;
    private boolean isViewPagerFragment;//是否是ViewPager+Fragment

    /**
     * setUserVisibleHint的使用场景:FragmentPagerAdapter+ViewPager
     *
     * ViewPager+Fragment
     * 因为Fragment懒加载机制的原因，切换时Fragment并未销毁，不会触发onPause()，因此需要用setUserVisibleHint()来判断
     * 在Fragment OnCreateView()方法之前调用的
     * <p>
     * 使用场景：当fragment结合viewpager使用的时候 这个方法会调用
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isVisible = isVisibleToUser;
        isViewPagerFragment = true;


        if (mRootView != null) { //hasCreateView;//是否创建视图
            onFragmentVisibleChange(isVisibleToUser);
        }

    }

    /**
     * 使用场景：add hide show进行fragment切换的时候 这个方法会调用
     *
     * @param hidden
     */

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        isVisible = !hidden;

//        if (hidden) {
//            // 不在最前端显示 相当于调用了onPause();
//        } else {
//            // 在最前端显示 相当于调用了onResume();
//        }
        if (mRootView != null) {
            onFragmentVisibleChange(isVisible);
        }
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        if (isViewPagerFragment) {//是否是ViewPager+Fragment
//            onFragmentVisibleChange(isVisible);
//        } else {
//            onFragmentVisibleChange(true);
//        }

    }


    /**
     * 显示指定Fragment（其他隐藏）
     */
    public void showFragment(@IdRes int containerViewId, Fragment fragment) {
        if (stringFragmentMap == null) stringFragmentMap = new LinkedHashMap();
        //开启事务
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();

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
            } catch (java.lang.InstantiationException e) {
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


    public void setPaddingTop(View view) {
        try {
            view.setPadding(0, BarUtils.getStatusBarHeight(), 0, 0);

        } catch (Exception e) {

        }
    }

    public void setPaddingNumTop(View view, int num) {
        try {
            view.setPadding(0, BarUtils.getStatusBarHeight() + num, 0, 0);
        } catch (Exception e) {

        }
    }

    /**
     * 设置状态栏是否可见
     */
    public void setStatusBarVisibility(boolean isVisible) {
        BarUtils.setStatusBarVisibility(getActivity(), isVisible);//
    }

    /**
     * 透明状态栏
     */
    public void transparentStatusBar() {
        BarUtils.transparentStatusBar(getActivity());//
    }

    /**
     * 设置状态栏是否为浅色模式
     *
     * @param isLightMode
     */
    public void setStatusBarLightMode(boolean isLightMode) {
        BarUtils.setStatusBarLightMode(getActivity(), isLightMode);
    }

    /**
     * 设置导航栏是否为浅色模式
     *
     * @param isLightMode
     */
    public void setNavBarLightMode(boolean isLightMode) {
        BarUtils.setNavBarLightMode(getActivity(), isLightMode);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (stringFragmentMap != null) {
            stringFragmentMap.clear();
            stringFragmentMap = null;
        }
    }
}
