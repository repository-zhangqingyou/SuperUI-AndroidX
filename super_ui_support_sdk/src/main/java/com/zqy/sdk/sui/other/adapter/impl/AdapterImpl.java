package com.zqy.sdk.sui.other.adapter.impl;

import android.support.annotation.LayoutRes;

import com.zqy.sdk.sui.other.adapter.viewholder.BaseViewHolder;

/**
 * 作者: zhangqingyou
 * 时间: 2020/7/8 9:17
 * 描述:
 */
public interface AdapterImpl<T, V extends BaseViewHolder> {
    /**
     * 加载哪个布局id
     *
     * @return
     */
    @LayoutRes
    int getLayout(T item, int position);


    /**
     * 绑定视图
     *
     * @param viewHolder
     * @param item
     * @param position
     */
    void onBindViewHolder(V viewHolder, T item, int position);

    /**
     * view监听写在这里面
     */
    void listener(V viewHolder, T item, int position);
}
