package com.zqy.sui.other.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.ColorRes;

import com.xuexiang.xui.widget.button.RippleView;
import com.zqy.sui.R;
import com.zqy.sui.other.adapter.impl.RippleViewImpl;
import com.zqy.sui.other.adapter.viewholder.BaseViewHolder;

import java.util.List;

/**
 * 作者: zhangqingyou
 * 时间: 2020/7/10 9:31
 * 描述:
 */
public abstract class WaterRippleAdapter<T> extends AbsAdapter<T, BaseViewHolder> implements RippleViewImpl {
    public WaterRippleAdapter() {
        super();
    }

    public WaterRippleAdapter(List<T> beanList) {
        super(beanList);
    }

    private Context context;

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
                inflate.setClickable(false);
                RippleView waterRippleView = getWaterRippleView(inflate);
                baseViewHolder = new BaseViewHolder(waterRippleView, viewType);
            }
        }
        return baseViewHolder;
    }

    /**
     * 注册item点击、长按事件
     *
     * @param holder
     * @param position
     */
    public void onBindItemClickListener(final BaseViewHolder holder, final int position) {
        if (getOnItemClickListener() != null)
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View view) {
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.postDelayed(new Runnable() {//延迟才有水波纹效果
                        @Override
                        public void run() {
                            getOnItemClickListener().onItemClick(view, getDataList().get(position), position);
                        }
                    }, 300);

                }
            });

        if (getOnItemLongClickListener() != null)
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(final View v) {
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            getOnItemLongClickListener().onItemLongClick(v, getDataList().get(position), position);
                        }
                    }, 300);

                    return true;
                }
            });
    }


    /**
     * 设置水波纹
     */
    private RippleView getWaterRippleView(View itemView) {
        RippleView rippleView = new RippleView(itemView.getContext());
        rippleView.setLayoutParams(new ViewGroup.LayoutParams(getRippleViewWidth(), getRippleViewheight()));
        rippleView.setRippleType(getRippleType());
        rippleView.setRippleColor(getRippleColor());//设置纹波色，默认为#FFFFFF
        rippleView.addView(itemView);
        rippleView.setFrameRate(getFrameRate());//设置帧率纹波动画 新的帧率值，默认为10
        rippleView.setRippleAlpha(getRippleAlpha());//对于连锁反应颜色设置阿尔法  Alpha值介于0到255之间，默认值为90
        rippleView.setRippleDuration(getRippleDuration());//在MS纹波动画的持续时间 400ms
        rippleView.setZoomDuration(getZoomDuration());//结束动画的持续时间（以毫秒为单位 默认200ms
        return rippleView;
    }

    public RippleView getRippleView(BaseViewHolder holder) {
        if (holder.itemView instanceof RippleView)
            return (RippleView) holder.itemView;
        return null;
    }

    /**
     * 波纹样式
     *
     * @return
     */
    @Override
    public RippleView.RippleType getRippleType() {
        return RippleView.RippleType.RECTANGLE;
    }


    /**
     * 波纹颜色
     *
     * @return
     */
    @Override
    @ColorRes
    public int getRippleColor() {
        return R.color.bui_gray;
    }


    /**
     * 波纹View的宽
     *
     * @return
     */
    @Override
    public int getRippleViewWidth() {
        return ViewGroup.LayoutParams.MATCH_PARENT;
    }


    /**
     * 波纹View的高
     *
     * @return
     */
    @Override
    public int getRippleViewheight() {
        return ViewGroup.LayoutParams.WRAP_CONTENT;
    }

    /**
     * 设置帧率纹波动画 新的帧率值，默认为10
     *
     * @return
     */
    @Override
    public int getFrameRate() {
        return 10;
    }

    /**
     * 对于连锁反应颜色设置阿尔法  Alpha值介于0到255之间，默认值为90
     *
     * @return
     */
    @Override
    public int getRippleAlpha() {
        return 90;
    }

    /**
     * 在MS纹波动画的持续时间 400ms
     *
     * @return
     */
    @Override
    public int getRippleDuration() {
        return 300;
    }

    /**
     * 结束动画的持续时间（以毫秒为单位 默认200ms
     *
     * @return
     */
    @Override
    public int getZoomDuration() {
        return 200;
    }
}
