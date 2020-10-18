package com.zqy.sdk.sui.other.adapter;

import android.view.View;

import com.zqy.sdk.sui.other.adapter.impl.AdapterImpl;
import com.zqy.sdk.sui.other.adapter.viewholder.BaseViewHolder;

import java.util.List;

/**
 * 自定义基准Adapter
 * <p>
 * Author: zhangqingyou
 * Date: 2020/5/14 13:07
 * Des:
 */
public abstract class AbsAdapter<T, V extends BaseViewHolder> extends DataAdapter<T, V> implements AdapterImpl<T, V> {
    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;
    private OnItemButtonClickListener onItemButtonClickListener;

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
        return getLayout(getDataList().get(position), position);
    }

    @Override
    public void onBindViewHolder(V viewHolder, int position) {
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


    public interface OnItemClickListener<T> {
        void onItemClick(View view, T item, int position);
    }

    public interface OnItemLongClickListener<T> {
        void onItemLongClick(View view, T item, int position);
    }

    public interface OnItemButtonClickListener<T> {
        void onItemClick(View view, T item, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        mOnItemLongClickListener = onItemLongClickListener;
    }

    public void setOnItemButtonClickListener(OnItemButtonClickListener onItemButtonClickListener) {
        this.onItemButtonClickListener = onItemButtonClickListener;
    }


    public OnItemClickListener getOnItemClickListener() {
        return mOnItemClickListener;
    }

    public OnItemLongClickListener getOnItemLongClickListener() {
        return mOnItemLongClickListener;
    }

    public OnItemButtonClickListener getOnItemButtonClickListener() {
        return onItemButtonClickListener;
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
