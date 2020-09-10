package com.zqy.baseui.adapter;

import android.view.View;

import androidx.annotation.LayoutRes;

import com.zqy.baseui.adapter.viewholder.BaseViewHolder;


/**
 * 作者: zhangqingyou
 * 时间: 2020/6/10 10:30
 * 描述: 可以添加 头部view 和 尾部view 的Adapter
 */
public abstract class HeadFootAdapter<T> extends BaseAdapter<T> {
    private int headLayoutId, footLayoutId;
    private View headView, footView;

    /**
     * 添加头部view
     *
     * @param headLayoutId
     */
    public void setHeadLayoutId(@LayoutRes int headLayoutId) {
        this.headLayoutId = headLayoutId;

    }

    /**
     * 添加尾部view
     *
     * @param footLayoutId
     */
    public void setFootLayoutId(@LayoutRes int footLayoutId) {
        this.footLayoutId = footLayoutId;
    }


    @Override
    public int getLayout(T item, int position) {
        int layoutId = 0;
        if (position == 0) {//头部
            if (headLayoutId != 0) {
                layoutId = headLayoutId;
            }
            layoutId = getCommonLayout(item, position);
        } else if (position == getItemCount() - 1) {//尾部
            if (footLayoutId != 0) {
                layoutId = footLayoutId;
            }
            layoutId = getCommonLayout(item, position);
        } else {
            layoutId = getCommonLayout(item, position);
        }
        return layoutId;

    }


    @Override
    public void onBindViewHolder(BaseViewHolder viewHolder, T item, int i) {
        if (getLayoutId(viewHolder) == headLayoutId) {
            onHeadView(viewHolder.itemView);
        } else if (getLayoutId(viewHolder) == footLayoutId) {
            onFootView(viewHolder.itemView);
        } else {
            if (headLayoutId != 0) {
                onCommonBindViewHolder(viewHolder, item, i - 1);
            } else {
                onCommonBindViewHolder(viewHolder, item, i);
            }
        }

    }

    @Override
    public void listener(BaseViewHolder  viewHolder, T item, int i) {

    }

    @Override
    public int getItemCount() {
        int itemCount = super.getItemCount();
        if (headLayoutId != 0) {
            itemCount = itemCount + 1;
        }
        if (footLayoutId != 0) {
            itemCount = itemCount + 1;
        }
        return itemCount;
    }


    /**
     * 常规
     *
     * @param item
     * @param position
     * @return
     */
    public abstract int getCommonLayout(T item, int position);

    public abstract void onCommonBindViewHolder(BaseViewHolder viewHolder, T item, int i);

    public abstract void onHeadView(View headView);

    public abstract void onFootView(View footView);

}
