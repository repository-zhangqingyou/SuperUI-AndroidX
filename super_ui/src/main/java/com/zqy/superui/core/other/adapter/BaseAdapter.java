package com.zqy.superui.core.other.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zqy.superui.core.other.adapter.viewholder.BaseViewHolder;

import java.util.List;

/**
 * 自定义基准BaseAdapter
 * <p>
 * Author: zhangqingyou
 * Date: 2020/5/14 13:07
 * Des:
 */
public abstract class BaseAdapter<T> extends AbsAdapter<T, BaseViewHolder> {
    private Context context;

    public BaseAdapter() {
        super();
    }

    public BaseAdapter(List<T> beanList) {
        super(beanList);
    }

    public Context getContext() {
        return context;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        context = viewGroup.getContext();
        BaseViewHolder baseViewHolder = null;
        if (getDataList() != null) {
            if (viewType != 0) {
                View inflate = LayoutInflater.from(context).inflate(viewType, viewGroup, false);
                baseViewHolder = new BaseViewHolder(inflate, viewType);
            }
        }
        return baseViewHolder;
    }


}
