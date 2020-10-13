package com.zqy.sui.other.adapter.viewholder;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.ColorRes;

import com.xuexiang.xui.widget.button.RippleView;
import com.zqy.sui.R;

/**
 * 作者: zhangqingyou
 * 时间: 2020/7/10 10:06
 * 描述:
 */
public class WaterRippleViewHolder extends BaseViewHolder {
    public RippleView rippleView;

    public WaterRippleViewHolder(View itemView) {
        super(itemView);
        setWaterRipple();
    }

    public WaterRippleViewHolder(View itemView, int layoutId) {
        super(itemView, layoutId);
        setWaterRipple();
    }

    /**
     * 设置水波纹背景 (itemView未设置Background才有效)
     */
//    public void setSystemWaterRipple() {
//        if (itemView.getBackground() == null) {
//            TypedValue typedValue = new TypedValue();
//            Resources.Theme theme = itemView.getContext().getTheme();
//            int top = itemView.getPaddingTop();
//            int bottom = itemView.getPaddingBottom();
//            int left = itemView.getPaddingLeft();
//            int right = itemView.getPaddingRight();
//            if (theme.resolveAttribute(android.R.attr.selectableItemBackground, typedValue, true)) {
//                itemView.setBackgroundResource(typedValue.resourceId);
//            }
//            itemView.setPadding(left, top, right, bottom);
//        }
//    }

    /**
     * 设置水波纹
     */
    public void setWaterRipple() {
        setWaterRipple(RippleView.RippleType.SIMPLE, R.color.sui_gray);
    }

    /**
     * 设置水波纹
     */
    public void setWaterRipple(RippleView.RippleType rippleType, @ColorRes int rippleColor) {
        setWaterRipple(rippleType, rippleColor, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    /**
     * 设置水波纹
     */
    public void setWaterRipple(RippleView.RippleType rippleType, @ColorRes int rippleColor, int width, int height) {
        RippleView  rippleView = new RippleView(itemView.getContext());
        rippleView.setLayoutParams(new ViewGroup.LayoutParams(width, height));
        rippleView.setRippleType(rippleType);
        rippleView.setRippleColor(rippleColor);//设置纹波色，默认为#FFFFFF
        rippleView.addView(itemView);
    }
}
