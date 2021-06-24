package com.zqy.superui.core.ui.fragment.simple;

import android.graphics.Paint;
import android.view.View;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.zqy.superui.R;
import com.zqy.superui.core.enums.LoadingStatus;
import com.zqy.superui.core.other.divider.HorizontalDividerItemDecoration;
import com.zqy.superui.core.ui.fragment.SmartRefreshListFragment;
import com.zqy.superui.loadinglayout.LoadingLayout;

/**
 * 作者: zhangqingyou
 * 时间: 2021/3/25
 * 描述: 简单的-带有状态页的刷新列表Fragment (采用第三方下拉刷新)
 */
public abstract class SimpleSmartRefreshLoadingListFragment extends SmartRefreshListFragment {
    private RecyclerView mRecyclerView;
    private SmartRefreshLayout mSmartRefreshLayout;
    private LoadingLayout mLoadingLayout;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    public int getLayoutId() {
        return R.layout.superui_smartrefresh_loadinglayout_recyclerview_scrollbar;
    }

    @Override
    public void initView(View mRootView) {
        mRecyclerView = findViewById(R.id.recyclerView);
        mSmartRefreshLayout = findViewById(R.id.smartRefreshLayout);
        mLoadingLayout = findViewById(R.id.loadingLayout);
    }

    @Override
    public void onFragmentVisibleChange(boolean isVisible) {

    }

    @Override
    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }

    @Override
    public SmartRefreshLayout getSmartRefreshLayout() {
        return mSmartRefreshLayout;
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        if (layoutManager == null) layoutManager = new LinearLayoutManager(getContext());
        return layoutManager;
    }

    @Override
    public RecyclerView.ItemDecoration getItemDecoration() {
        Paint paint = new Paint();
        paint.setStrokeWidth(getResources().getDimensionPixelSize(R.dimen._1sdp));//分割线宽度
        paint.setColor(ContextCompat.getColor(getActivity(), R.color.superui_gray_dividing_line_light));//分割线颜色
        //DashPathEffect作用是将Path的线段虚线化--
        // 第一个参数是数组，且它的长度必须>=2, 数组的数字就是控制实虚... 长度。
        // phase:为绘制时的偏移量
        // paint.setPathEffect(new DashPathEffect(new float[]{20f, 10f}, 0));//虚线
        HorizontalDividerItemDecoration build = new HorizontalDividerItemDecoration.Builder(getContext())//横向分割线
                .paint(paint)
                .margin(_12sdp, _12sdp)//分割线margin
                .showLastDivider()//显示最后一条
                //.startSkipCount(0)//跳过开头的*个分割线不展示
                //.endSkipCount(1)//跳过结尾的2个分割线不展示
                .build();
        return build;
    }

    /**
     * 设置加载状态
     */
    public void setLoadingStatus(LoadingStatus status) {
        mLoadingLayout.setStatus(status.getStatus());
//        //载入成功
//        loadingLayout.setStatus(LoadingLayout.Success);
////加载失败
//        loadingLayout.setStatus(LoadingLayout.Error);
////正在加载
//        loadingLayout.setStatus(LoadingLayout.Loading);
////没有数据
//        loadingLayout.setStatus(LoadingLayout.Empty);
////没有网络
//        loadingLayout.setStatus(LoadingLayout.No_Network);
    }


}
