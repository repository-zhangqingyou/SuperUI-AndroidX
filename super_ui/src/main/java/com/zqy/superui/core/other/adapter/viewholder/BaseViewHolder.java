package com.zqy.superui.core.other.adapter.viewholder;


import android.view.View;

import androidx.annotation.LayoutRes;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 作者: zhangqingyou
 * 时间: 2020/7/8 10:24
 * 描述:
 */
public class BaseViewHolder extends RecyclerView.ViewHolder {
    private int layoutId;

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public BaseViewHolder(View itemView, @LayoutRes int layoutId) {
        this(itemView);
        this.layoutId = layoutId;
    }




    public <T extends View> T findViewById(int id) {
        return itemView.findViewById(id);
    }

    public int getLayoutId() {
        return layoutId;
    }
}
