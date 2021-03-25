package com.zqy.superui.core.ui.activity;


import android.view.View;

import androidx.annotation.MenuRes;
import androidx.appcompat.widget.Toolbar;

/**
 * 作者: zhangqingyou
 * 时间: 2021/3/25
 * 描述: 含有顶部标题的Activity
 */
public abstract class ToolbarActivity extends AbsActivity implements Toolbar.OnMenuItemClickListener {
    @Override
    public void initData() {
        Toolbar mToolbar = getToolbar();
        int toolbarMenu = getToolbarMenu();
        if (mToolbar != null ) {
           // mToolbar.setTitleTextAppearance(this, R.style.ToolbarTitleAppearance);

            if (toolbarMenu > 0){
                mToolbar.inflateMenu(toolbarMenu);
            }

        }

    }

    @Override
    public void listener() {
        Toolbar mToolbar = getToolbar();
        if (mToolbar != null) {
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onNavigationBackClick(v);
                }
            });


            mToolbar.setOnMenuItemClickListener(this);

        }

    }


    /**
     * 返回监听
     *
     * @param v
     */
    public void onNavigationBackClick(View v) {
        finish();
    }

    public abstract Toolbar getToolbar();

    @MenuRes
    public abstract int getToolbarMenu();


    @Override
    protected void onDestroy() {
        Toolbar mToolbar = getToolbar();
        if (mToolbar != null) {
            mToolbar.setNavigationOnClickListener(null);
            mToolbar.setOnMenuItemClickListener(null);
        }
        super.onDestroy();
    }
}

