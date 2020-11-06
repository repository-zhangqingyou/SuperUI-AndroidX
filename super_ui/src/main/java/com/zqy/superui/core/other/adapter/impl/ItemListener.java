package com.zqy.superui.core.other.adapter.impl;

import android.view.View;

/**
 * 作者: zhangqingyou
 * 时间: 2020/11/6 9:14
 * 描述: item相关view监听
 */
public interface ItemListener {

    public interface OnItemClickListener<T> {
        /**
         * item点击监听
         *
         * @param view
         * @param item
         * @param position
         */
        void onItemClick(View view, T item, int position);
    }

    public interface OnItemLongClickListener<T> {
        /**
         * item长按监听
         *
         * @param view
         * @param item
         * @param position
         */
        void onItemLongClick(View view, T item, int position);
    }

    public interface OnItemViewClickListener<T> {
        /**
         * item中某个view点击监听
         *
         * @param view
         * @param item
         * @param position
         */
        void onItemViewClick(View view, T item, int position);
    }

}
