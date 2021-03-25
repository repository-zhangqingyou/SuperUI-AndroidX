package com.zqy.superui.core.ui.activity.simple;


import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.widget.Toolbar;

import com.zqy.superui.R;
import com.zqy.superui.core.ui.activity.ToolbarActivity;

/**
 * 作者: zhangqingyou
 * 时间: 2021/3/25
 * 描述: 简单的含有顶部标题的Activity
 */
public abstract class SimpleToolbarActivity extends ToolbarActivity {

    private Toolbar mToolbar;
    private LinearLayout mLlContainer;
    private LinearLayout mLlRootView;

    @Override
    public Object getLayout() {
        return R.layout.superui_simple_toolbar_activity;
    }

    @Override
    public Toolbar getToolbar() {
        return mToolbar;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }


    @Override
    public void initView() {
        mToolbar = findViewById(R.id.toolbar);
        mLlContainer = findViewById(R.id.ll_Container);
        mLlRootView = findViewById(R.id.ll_RootView);


        Object layout = getContentLayout();
        if (layout instanceof Integer) {
            View inflate = View.inflate(getContext(), (Integer) layout, null);
            mLlContainer.addView(inflate, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

        } else if (layout instanceof View) {
            mLlContainer.addView((View) layout);
        }


    }

    /**
     * 根视图
     *
     * @return
     */
    public LinearLayout getRootView() {
        return mLlRootView;
    }

    /**
     * 容器内视图（ Toolbar下面的内容视图）
     *
     * @return
     */
    public LinearLayout getContainer() {
        return mLlContainer;
    }


    /**
     * Toolbar下面的内容视图
     */
    public abstract Object getContentLayout();

}

