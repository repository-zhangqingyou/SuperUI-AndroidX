package com.zqy.superui.core.ui.fragment.simple;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener;
import com.zqy.superui.core.ui.fragment.FragmentBuilder;

/**
 * 作者: zhangqingyou
 * 时间: 2021/7/23
 * 描述: 构造简单的-带有状态页的刷新列表Fragment (采用第三方下拉刷新)
 */
public class SimpleSmartRefreshLoadingListFragment2 extends SimpleSmartRefreshLoadingListFragment {

    //Fragment配置
    private FragmentBuilder fragmentBuilder;
    //下拉上拉监听
    private OnRefreshLoadMoreListener onRefreshLoadMoreListener;

    public SimpleSmartRefreshLoadingListFragment2(FragmentBuilder fragmentBuilder, OnRefreshLoadMoreListener onRefreshLoadMoreListener) {
        this.fragmentBuilder = fragmentBuilder;
        this.onRefreshLoadMoreListener = onRefreshLoadMoreListener;
    }

    public SimpleSmartRefreshLoadingListFragment2(FragmentBuilder fragmentBuilder) {
        this(fragmentBuilder, null);
    }

    private SimpleSmartRefreshLoadingListFragment2() {
    }


    @Override
    public boolean isEnableRefresh() {
        return fragmentBuilder.isEnableRefresh();
    }

    @Override
    public boolean isEnableLoadMore() {
        return fragmentBuilder.isEnableLoadMore();
    }

    @Override
    public boolean isEnableOverScrollMode() {
        return fragmentBuilder.isEnableOverScrollMode();
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        if (onRefreshLoadMoreListener != null) onRefreshLoadMoreListener.onRefresh(refreshLayout);
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        if (onRefreshLoadMoreListener != null) onRefreshLoadMoreListener.onRefresh(refreshLayout);
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        RecyclerView.LayoutManager layoutManager = fragmentBuilder.getLayoutManager();
        return layoutManager == null ? super.getLayoutManager() : layoutManager;
    }

    @Override
    public RecyclerView.ItemDecoration getItemDecoration() {
        RecyclerView.ItemDecoration itemDecoration = fragmentBuilder.getItemDecoration();
        return itemDecoration == null ? super.getItemDecoration() : itemDecoration;
    }

    @Override
    public RecyclerView.Adapter getAdapter() {
        return fragmentBuilder.getAdapter();
    }


}
