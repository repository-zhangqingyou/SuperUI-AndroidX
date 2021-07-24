package com.zqy.superui.core.ui.fragment.simple;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener;
import com.zqy.superui.core.ui.fragment.FragmentBuilder;

/**
 * 作者: zhangqingyou
 * 时间: 2021/7/23
 * 描述: 构造简单的列表Fragment
 */
public class SimpleSmartRefreshListFragment2 extends SimpleSmartRefreshListFragment {

    //Fragment配置
    private FragmentBuilder fragmentBuilder;
    //下拉上拉监听
    private OnRefreshLoadMoreListener onRefreshLoadMoreListener;

    public SimpleSmartRefreshListFragment2(FragmentBuilder fragmentBuilder, OnRefreshLoadMoreListener onRefreshLoadMoreListener) {
        this.fragmentBuilder = fragmentBuilder;
        this.onRefreshLoadMoreListener = onRefreshLoadMoreListener;
    }

    public SimpleSmartRefreshListFragment2(FragmentBuilder fragmentBuilder) {
        this(fragmentBuilder, null);
    }

    private SimpleSmartRefreshListFragment2() {
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
