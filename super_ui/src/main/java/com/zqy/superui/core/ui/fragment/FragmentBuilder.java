package com.zqy.superui.core.ui.fragment;

import androidx.recyclerview.widget.RecyclerView;

import lombok.Getter;

/**
 * 作者: zhangqingyou
 * 时间: 2021/7/23
 * 描述: Fragment配置 ViewBinding
 */
@Getter
public class FragmentBuilder {
    //分割线
    private RecyclerView.ItemDecoration itemDecoration;
    //布局管理器
    private RecyclerView.LayoutManager layoutManager;
    //适配器
    private RecyclerView.Adapter adapter;
    //是否下拉刷新，是否上拉加载更多，是否有阴影
    private boolean isEnableRefresh, isEnableLoadMore, isEnableOverScrollMode;

    public FragmentBuilder setEnableRefresh(boolean enableRefresh) {
        isEnableRefresh = enableRefresh;
        return this;
    }

    public FragmentBuilder setEnableLoadMore(boolean enableLoadMore) {
        isEnableLoadMore = enableLoadMore;
        return this;
    }

    public FragmentBuilder setEnableOverScrollMode(boolean enableOverScrollMode) {
        isEnableOverScrollMode = enableOverScrollMode;
        return this;
    }

    public FragmentBuilder setItemDecoration(RecyclerView.ItemDecoration itemDecoration) {
        this.itemDecoration = itemDecoration;
        return this;
    }

    public FragmentBuilder setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        this.layoutManager = layoutManager;
        return this;
    }

    public FragmentBuilder setAdapter(RecyclerView.Adapter adapter) {
        this.adapter = adapter;
        return this;
    }
}
