package com.zqy.superui.core.widget.textview;

import android.content.Context;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.view.Gravity;

import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;

/**
 * 带颜色渐变和缩放的指示器标题
 * 博客: http://hackware.lucode.net
 * Created by hackware on 2016/6/26.
 */
public class ScaleTransitionPagerTitleView extends ColorTransitionPagerTitleView {
    private float mMinScale = 0.75f;
    private boolean isBold;//选中是否加粗

    public ScaleTransitionPagerTitleView(Context context) {
        super(context);
        int padding = UIUtil.dip2px(context, 10);

        init(context, padding);
    }

    public ScaleTransitionPagerTitleView(Context context, int padding) {
        super(context);
        init(context, padding);
    }

    /**
     * 设置左右边距
     *
     * @param context
     * @param padding
     */
    private void init(Context context, int padding) {
        setGravity(Gravity.CENTER);
        setPadding(padding, 0, padding, 0);
        setSingleLine();
        setEllipsize(TextUtils.TruncateAt.END);
    }

    /**
     * 设置选中是否加粗
     *
     * @param isBold
     */
    public void setSelectedBold(boolean isBold) {
        //  mTvTextBar.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));//加粗
        this.isBold = isBold;
    }

    @Override
    public void onSelected(int index, int totalCount) {
        setTextColor(mSelectedColor);
        if (isBold) setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));//加粗
    }

    @Override
    public void onDeselected(int index, int totalCount) {
        setTextColor(mNormalColor);
        if (isBold) setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));//取消加粗
    }

    @Override
    public void onEnter(int index, int totalCount, float enterPercent, boolean leftToRight) {
        super.onEnter(index, totalCount, enterPercent, leftToRight);    // 实现颜色渐变
        setScaleX(mMinScale + (1.0f - mMinScale) * enterPercent);
        setScaleY(mMinScale + (1.0f - mMinScale) * enterPercent);
    }

    @Override
    public void onLeave(int index, int totalCount, float leavePercent, boolean leftToRight) {
        super.onLeave(index, totalCount, leavePercent, leftToRight);    // 实现颜色渐变
        setScaleX(1.0f + (mMinScale - 1.0f) * leavePercent);
        setScaleY(1.0f + (mMinScale - 1.0f) * leavePercent);
    }

    public float getMinScale() {
        return mMinScale;
    }

    public void setMinScale(float minScale) {
        mMinScale = minScale;
    }
}
