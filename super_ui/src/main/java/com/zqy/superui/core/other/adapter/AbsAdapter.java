package com.zqy.superui.core.other.adapter;

import android.util.Log;
import android.view.View;

import com.zqy.superui.core.other.adapter.impl.AdapterImpl;
import com.zqy.superui.core.other.adapter.impl.ItemListener;
import com.zqy.superui.core.other.adapter.viewholder.BaseViewHolder;

import java.util.List;

/**
 * 自定义基准Adapter
 * <p>
 * Author: zhangqingyou
 * Date: 2020/5/14 13:07
 * Des:
 */
public abstract class AbsAdapter<T, V extends BaseViewHolder> extends DataAdapter<T, V> implements AdapterImpl<T, V> {
    private ItemListener.OnItemClickListener mOnItemClickListener;
    private ItemListener.OnItemLongClickListener mOnItemLongClickListener;
    private ItemListener.OnItemViewClickListener onItemViewClickListener;

    public AbsAdapter() {
        super();
    }


    public AbsAdapter(List<T> beanList) {
        super(beanList);
    }


    /**
     * 共享Item。其实RecycledViewPool的内部维护了一个Map，
     * <p>
     * 里面以不同的viewType为Key存储了各自对应的ViewHolder集合。可以通过提供的方法来修改内部缓存的Viewholder
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
//        T t = mList.get(position);
//        int key = t.hashCode();
//        int value = getLayout(t, position);
//        viewTypeLayoutIdMap.put(key, value);
        int layout = getLayout(getDataList().get(position), position);
        Log.d(TAG, "getItemViewType:" + layout);
        return layout;
    }


    @Override
    public void onBindViewHolder(V viewHolder, int position) {
        Log.d(TAG, "onBindViewHolder:position:" + position);
        if (getDataList() != null) {
            onBindViewHolder(viewHolder, getDataList().get(position), position);
            //绑定监听事件
            onBindItemClickListener(viewHolder, position);
            //自定义监听事件
            listener(viewHolder, getDataList().get(position), position);
        }

    }

    @Override
    public int getItemCount() {
        if (getDataList() != null)
            return getDataList().size();
        return 0;
    }


    public void setOnItemClickListener(ItemListener.OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(ItemListener.OnItemLongClickListener onItemLongClickListener) {
        this.mOnItemLongClickListener = onItemLongClickListener;
    }

    public void setOnItemViewClickListener(ItemListener.OnItemViewClickListener onItemViewClickListener) {
        this.onItemViewClickListener = onItemViewClickListener;
    }

    public ItemListener.OnItemClickListener getOnItemClickListener() {
        return mOnItemClickListener;
    }

    public ItemListener.OnItemLongClickListener getOnItemLongClickListener() {
        return mOnItemLongClickListener;
    }

    public ItemListener.OnItemViewClickListener getOnItemViewClickListener() {
        return onItemViewClickListener;
    }

    /**
     * 注册item点击、长按事件
     *
     * @param holder
     * @param position
     */
    public void onBindItemClickListener(final V holder, final int position) {
        if (mOnItemClickListener != null)
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickListener.onItemClick(view, getDataList().get(position), position);
                }
            });

        if (mOnItemLongClickListener != null)
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mOnItemLongClickListener.onItemLongClick(v, getDataList().get(position), position);
                    return true;
                }
            });
    }


}
