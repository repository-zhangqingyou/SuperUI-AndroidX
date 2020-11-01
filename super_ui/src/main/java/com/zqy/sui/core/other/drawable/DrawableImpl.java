package com.zqy.sui.core.other.drawable;

import android.graphics.drawable.GradientDrawable;

import androidx.annotation.ColorInt;
import androidx.annotation.FloatRange;

/**
 * 作者: zhangqingyou
 * 时间: 2020/9/12 14:15
 * 描述:
 */
public interface DrawableImpl {
    /**
     * 四角圆形度数
     */
    void setRadius(int radiusTopLeft, int radiusTopRight, int radiusBottomLeft, int radiusBottomRight);


    /**
     * 设置是否有按下效果
     *
     * @param clickEffect
     */
    void setClickEffect(boolean clickEffect);


    /**
     * 设置按下透明值
     *
     * @param clickAlpha
     */
    void setClickAlpha(@FloatRange(from = 0, to = 1) float clickAlpha);

    /**
     * 设置填充颜色
     *
     * @param color 颜色
     */
    void setSolidColor(@ColorInt int color);


    /**
     * 设置点击填充颜色
     *
     * @param clickSolidColor 颜色
     */
    void setClickSolidColor(@ColorInt int clickSolidColor);

    /**
     * 设置边框颜色及宽度
     *
     * @param strokeWidth 边框宽度
     * @param color       边框颜色
     */
    void setStrokeColorAndWidth(int strokeWidth, int color);

    /**
     * 设置点击边框颜色
     *
     * @param clickStrokeColor 点击时边框颜色
     */
    void setClickStrokeColor(@ColorInt int clickStrokeColor);

    /**
     * 设置渐变颜色
     *
     * @param starColor
     * @param endColor
     * @return
     */
    void setGradient(@ColorInt int starColor, @ColorInt int endColor, Gradient gradient, GradientDrawable.Orientation orientation);


    /**
     * 设置textView颜色
     *
     * @param normalTextColor 正常状态颜色
     */
    void setNormalTextColor(@ColorInt int normalTextColor);


    /**
     * 设置textView选中状态颜色
     *
     * @param clickTextColor 按下状态颜色
     */
    void setClickTextColor(@ColorInt int clickTextColor);

    /**
     * 设置完成之后必须调用 buid() ，否则不生效
     */
    void buid();



}
