package com.zqy.superui.core.ui.fragment;

import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener;

/**
 * 作者: zhangqingyou
 * 时间: 2021/3/14
 * 描述:
 */
public abstract class SmartRefreshListFragment extends ListFragment implements OnRefreshLoadMoreListener {

    @Override
    public void initData() {
        super.initData();
        SmartRefreshLayout smartRefreshLayout = getSmartRefreshLayout();
        if (smartRefreshLayout != null) {
            //下拉 上拉设置
            //设置 Header 样式
            ClassicsHeader classicsHeader = new ClassicsHeader(getActivity());
            smartRefreshLayout.setRefreshHeader(classicsHeader);
            //设置 Footer  样式
            smartRefreshLayout.setRefreshFooter(new ClassicsFooter(getActivity()));
            smartRefreshLayout.setEnableRefresh(isEnableRefresh());//是否允许下拉
            smartRefreshLayout.setEnableLoadMore(isEnableLoadMore());//设置是否启用上拉加载更多
            smartRefreshLayout.setEnableAutoLoadMore(true);//是否启用列表惯性滑动到底部时自动加载更多
            //设置刷新加载时禁止所有列表操作
            smartRefreshLayout.setDisableContentWhenRefresh(true);//设置是否开启在刷新时候禁止操作内容视图
            smartRefreshLayout.setDisableContentWhenLoading(true);//设置是否开启在加载时候禁止操作内容视图
        }

    }

    @Override
    public void listener() {
        SmartRefreshLayout smartRefreshLayout = getSmartRefreshLayout();
        //下拉
        if (smartRefreshLayout != null) {
            smartRefreshLayout.setOnRefreshListener(this);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        SmartRefreshLayout smartRefreshLayout = getSmartRefreshLayout();
        //下拉
        if (smartRefreshLayout != null) {
            smartRefreshLayout.setOnRefreshListener(null);
        }
    }

    /**
     * 关闭下拉刷新和上拉加载更多
     */
    public void onFinishRefreshAndLoadMore() {
        onFinishRefreshAndLoadMore(200);
    }

    /**
     * 关闭下拉刷新和上拉加载更多
     *
     * @param delayed 延迟多少毫秒关闭
     */
    public void onFinishRefreshAndLoadMore(int delayed) {
        SmartRefreshLayout smartRefreshLayout = getSmartRefreshLayout();
        if (smartRefreshLayout != null) {
            if (isEnableRefresh()) smartRefreshLayout.finishRefresh(delayed);
            if (isEnableLoadMore()) smartRefreshLayout.finishLoadMore(delayed);
        }
    }


    public abstract SmartRefreshLayout getSmartRefreshLayout();

    public abstract boolean isEnableRefresh();//是否开启下拉

    public abstract boolean isEnableLoadMore();//是否开启上拉加载更多

}
