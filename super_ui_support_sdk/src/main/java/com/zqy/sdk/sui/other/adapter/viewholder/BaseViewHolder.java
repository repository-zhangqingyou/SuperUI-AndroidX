package com.zqy.sdk.sui.other.adapter.viewholder;


import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;


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
