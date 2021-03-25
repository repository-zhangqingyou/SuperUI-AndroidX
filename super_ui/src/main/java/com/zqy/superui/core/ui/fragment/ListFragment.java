package com.zqy.superui.core.ui.fragment;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;


/**
 * 作者: zhangqingyou
 * 时间: 2021/2/7 15:46
 * 描述:  带有列表的基类Fragment
 */
public abstract class ListFragment extends AbsFragment {
    private RecyclerView recyclerView;
    private RecyclerView.ItemDecoration itemDecoration;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;


    @Override
    public void initData() {
        recyclerView = getRecyclerView();
        layoutManager = getLayoutManager();
        itemDecoration = getItemDecoration();
        adapter = getAdapter();
        if (recyclerView != null) {

            if (itemDecoration != null) {
                recyclerView.addItemDecoration(itemDecoration);
            }

            if (layoutManager != null) {
                recyclerView.setLayoutManager(layoutManager);
            }

            if (isEnableOverScrollMode()) {
                recyclerView.setOverScrollMode(View.OVER_SCROLL_ALWAYS);//
            } else {
                recyclerView.setOverScrollMode(View.OVER_SCROLL_NEVER);//去掉阴影
            }

            if (adapter != null) {
                recyclerView.setAdapter(adapter);
            }


        }

    }


    public abstract RecyclerView getRecyclerView();

    public abstract RecyclerView.LayoutManager getLayoutManager();//布局管理

    public abstract RecyclerView.ItemDecoration getItemDecoration();//分割线

    public abstract RecyclerView.Adapter getAdapter();//适配器

    public abstract boolean isEnableOverScrollMode();//是否开启阴影





}
