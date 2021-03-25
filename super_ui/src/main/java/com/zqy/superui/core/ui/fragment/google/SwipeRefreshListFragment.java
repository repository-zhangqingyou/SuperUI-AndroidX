package com.zqy.superui.core.ui.fragment.google;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.zqy.superui.core.ui.fragment.ListFragment;


/**
 * 作者: zhangqingyou
 * 时间: 2021/2/7 15:46
 * 描述:  带有官方刷新列表的基类Fragment
 */
public abstract class SwipeRefreshListFragment extends ListFragment implements SwipeRefreshLayout.OnRefreshListener, OnLoadMoreListener {
    private LoadMoreListener loadMoreListener;

    @Override
    public void initData() {
        super.initData();
        if (getSwipeRefreshLayout() != null) {
            getSwipeRefreshLayout().setColorSchemeResources(getColorSchemeResources());
        }

    }

    @Override
    public void listener() {

        if (isEnableRefresh()) {
            //下拉
            if (getSwipeRefreshLayout() != null) {
                getSwipeRefreshLayout().setOnRefreshListener(this);
            }
        }
        if (isEnableLoadMore()) {
            //上拉
            if (getRecyclerView() != null) {
                loadMoreListener = new LoadMoreListener() {
                    @Override
                    public void onLoadMore() {
                        SwipeRefreshListFragment.this.onLoadMore();
                    }
                };
                getRecyclerView().addOnScrollListener(loadMoreListener);
            }

        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (isEnableRefresh()) {
            //下拉
            if (getSwipeRefreshLayout() != null) {
                getSwipeRefreshLayout().setOnRefreshListener(null);
            }
        }

        if (isEnableLoadMore()) {
            //上拉
            if (getRecyclerView() != null && loadMoreListener != null)
                getRecyclerView().removeOnScrollListener(loadMoreListener);
        }
    }


    public abstract SwipeRefreshLayout getSwipeRefreshLayout();


    public abstract boolean isEnableRefresh();

    public abstract boolean isEnableLoadMore();

    public abstract int[] getColorSchemeResources();


}
