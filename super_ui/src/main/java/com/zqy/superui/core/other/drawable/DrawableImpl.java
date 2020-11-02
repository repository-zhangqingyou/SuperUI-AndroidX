package com.zqy.superui.core.other.drawable;

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
     * 设置是否有按下效果
     *
     * @param clickEffect
     */
    void setClickEffect(boolean clickEffect);


    /**
     * 设置按下透明值(包括按下时的 《字体，边框，填充》 颜色)
     *
     * @param clickAlpha
     */
    void setClickAlpha(@FloatRange(from = 0, to = 1) float clickAlpha);

    /**
     * 四角圆形度数（单位dp）
     */
    void setRadius(int radius);

    /**
     * 四角圆形度数（单位dp）
     */
    void setRadius(int radiusTopLeft, int radiusTopRight, int radiusBottomLeft, int radiusBottomRight);

    /**
     * 设置填充颜色
     *
     * @param normalSolidColor 正常（抬起）填充颜色
     * @param clickSolidColor  点击（按下）填充颜色
     */
    void setSolidColorState(@ColorInt int normalSolidColor, @ColorInt int clickSolidColor);


    /**
     * 设置边框颜色及宽度
     *
     * @param strokeWidth       边框宽度
     * @param normalStrokeColor 正常（抬起）边框颜色
     * @param clickStrokeColor  点击（按下）边框颜色
     */
    void setStrokeColorState(int strokeWidth,@ColorInt int normalStrokeColor, @ColorInt int clickStrokeColor);


    /**
     * 设置渐变颜色
     *
     * @param starColor
     * @param endColor
     * @return
     */
    void setGradient(@ColorInt int starColor, @ColorInt int endColor, Gradient gradient, GradientDrawable.Orientation orientation);


    /**
     * 设置字体颜色
     *
     * @param normalTextColor 正常字体颜色
     * @param clickTextColor  按下字体颜色
     */
    void setTextColorState(@ColorInt int normalTextColor, @ColorInt int clickTextColor);



}
