package com.zqy.superui.core.ui.activity.simple;


import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.zqy.superui.R;
import com.zqy.superui.core.ui.activity.ToolbarActivity;

/**
 * 作者: zhangqingyou
 * 时间: 2021/3/25
 * 描述: 简单的含有顶部标题的列表Activity
 */
public abstract class SimpleToolbarListActivity extends ToolbarActivity {
    private Toolbar mToolbar;
    private LinearLayout mLlContainer;
    private LinearLayout mLlRootView;
    private TextView mTbText;

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
        mTbText = findViewById(R.id.tb_Text);
        mLlContainer = findViewById(R.id.ll_Container);
        mLlRootView = findViewById(R.id.ll_RootView);


        Fragment fragment = getFragment();


        if (fragment != null) {
            //开启事务
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.ll_Container, fragment);
            transaction.show(fragment);
            transaction.commit();
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
     * Toolbar中间文本
     *
     * @return
     */
    public TextView getTbText() {
        return mTbText;
    }

    /**
     * Toolbar下面的内容视图（）
     */
    public abstract Fragment getFragment();

}

