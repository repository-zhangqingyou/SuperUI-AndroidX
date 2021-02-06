package com.zqy.superui.core.other.drawable;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.FloatRange;

import com.blankj.utilcode.util.ColorUtils;
import com.zqy.superui.R;
import com.zqy.superui.SuperUIManager;
import com.zqy.superui.core.enums.Gradient;


/**
 * 作者: zhangqingyou
 * 时间: 2021/2/6 9:44
 * 描述: 水波纹Drawable
 * 支持：
 * 1.支持设置控件圆角 及 水波纹颜色和圆角
 * 2.支持设置边框宽度及颜色
 * 3.支持设置填充颜色
 * 4.支持设置填充颜色及渐变方向
 */
public class SuperRippleDrawable {
    private float clickAlpha = 0.7f;// //设置按下的透明值

    private int rippleColor;//正常波纹颜色
    private int maskColor;//<!--(面具)长按蒙面颜色-->

    private int normalStrokeColor;//正常边框
    private int strokeWidth;//边框宽度

    private int[] colors;//设置渐变颜色
    private Gradient gradient = Gradient.LINEAR_GRADIENT;// 渐变模式 GradientDrawable.LINEAR_GRADIENT（线性渐变） ， GradientDrawable.SWEEP_GRADIENT（扫描式渐变），GradientDrawable.RADIAL_GRADIENT（圆形渐变）
    private GradientDrawable.Orientation orientation = GradientDrawable.Orientation.LEFT_RIGHT;//设置渐变方向
    private float topLeftRadius, topRightRadius, bottomLeftRadius, bottomRightRadius;//圆角


    public SuperRippleDrawable() {
        topLeftRadius = topRightRadius = bottomLeftRadius = bottomRightRadius = SuperUIManager.getApplication().getResources().getDimension(R.dimen._5sdp);
    }

//    /**
//     * 创建具有指定波纹颜色和*可选内容和蒙版可绘制对象的新可绘制对象。
//     *
//     * @param color   颜色
//     * @param content 可绘制的内容，可以为{@code null}  内容Drawable
//     * @param mask    可绘制的蒙版，可以为{@code null}  水波纹Drawable
//     */
//
//    public SuperRippleDrawable(@NonNull ColorStateList color, @Nullable Drawable content, @Nullable Drawable mask) {
//        super(color, content, mask);
//    }


    /**
     * 设置按下透明值(包括按下时的 《字体》 颜色)
     *
     * @param clickAlpha
     */
    public SuperRippleDrawable setClickAlpha(@FloatRange(from = 0, to = 1) float clickAlpha) {
        this.clickAlpha = clickAlpha;
        return this;
    }

    /**
     * 四角圆形度数
     */
    public SuperRippleDrawable setRadius(float radius) {
        setRadius(radius, radius, radius, radius);
        return this;
    }

    /**
     * 四角圆形度数（单位px）
     */
    public SuperRippleDrawable setRadius(float topLeftRadius, float topRightRadius, float bottomLeftRadius, float bottomRightRadius) {
        this.topLeftRadius = topLeftRadius;
        this.topRightRadius = topRightRadius;
        this.bottomLeftRadius = bottomLeftRadius;
        this.bottomRightRadius = bottomRightRadius;
        return this;
    }

    /**
     * 设置字体颜色
     *
     * @param normalTextColor 正常字体颜色
     */
    public SuperRippleDrawable setTextColorState(TextView textView, @ColorInt int normalTextColor) {
        return setTextColorState(textView, normalTextColor, normalTextColor);
    }

    /**
     * 设置字体颜色
     *
     * @param normalTextColor 正常字体颜色
     * @param clickTextColor  按下字体颜色
     */
    public SuperRippleDrawable setTextColorState(TextView textView, @ColorInt int normalTextColor, @ColorInt int clickTextColor) {
        if (clickTextColor != Color.TRANSPARENT) {
            int alphaComponent = ColorUtils.setAlphaComponent(clickTextColor, clickAlpha);
            textView.setTextColor(getColorStateList(normalTextColor, alphaComponent));
        } else {
            textView.setTextColor(getColorStateList(normalTextColor, Color.TRANSPARENT));
        }
        return this;
    }

    /**
     * 设置字体颜色
     *
     * @param normalTextColor 正常字体颜色
     */
    public SuperRippleDrawable setTextColorState(Button button, @ColorInt int normalTextColor) {
        return setTextColorState(button, normalTextColor);
    }


    /**
     * 设置字体颜色
     *
     * @param normalTextColor 正常字体颜色
     * @param clickTextColor  按下字体颜色
     */
    public SuperRippleDrawable setTextColorState(Button button, @ColorInt int normalTextColor, @ColorInt int clickTextColor) {
        if (clickTextColor != Color.TRANSPARENT) {
            int alphaComponent = ColorUtils.setAlphaComponent(clickTextColor, clickAlpha);
            button.setTextColor(getColorStateList(normalTextColor, alphaComponent));
        } else {
            button.setTextColor(getColorStateList(normalTextColor, Color.TRANSPARENT));
        }
        return this;
    }


    /**
     * 设置边框颜色及宽度
     *
     * @param strokeWidth       边框宽度
     * @param normalStrokeColor 边框颜色
     */
    public SuperRippleDrawable setStrokeColorState(int strokeWidth, @ColorInt int normalStrokeColor) {
        this.strokeWidth = strokeWidth;//边框宽度
        this.normalStrokeColor = normalStrokeColor;//边框颜色
        return this;
    }


    /**
     * @return
     */
    public SuperRippleDrawable setSolidColor(@ColorInt int solidColor) {
        setGradient(solidColor, solidColor);
        return this;
    }


    /**
     * @param starColor 渐变开始颜色
     * @param endColor  渐变结束颜色
     * @return
     */
    public SuperRippleDrawable setGradient(@ColorInt int starColor, @ColorInt int endColor) {
        setGradient(starColor, endColor, Gradient.LINEAR_GRADIENT, GradientDrawable.Orientation.LEFT_RIGHT);
        return this;
    }


    /**
     * @param starColor   渐变开始颜色
     * @param endColor    渐变结束颜色
     * @param gradient    渐变模式 GradientDrawable.LINEAR_GRADIENT（线性渐变） ， GradientDrawable.SWEEP_GRADIENT（扫描式渐变），GradientDrawable.RADIAL_GRADIENT（圆形渐变）
     * @param orientation 渐变方向
     * @return
     */
    public SuperRippleDrawable setGradient(@ColorInt int starColor, @ColorInt int endColor, Gradient gradient, GradientDrawable.Orientation orientation) {
        if (starColor != Color.TRANSPARENT || endColor != Color.TRANSPARENT) {
            this.colors = new int[]{starColor, endColor};
        }
        this.gradient = gradient;
        this.orientation = orientation;
        return this;
    }

    /**
     * @param rippleColor //水波纹颜色
     * @return
     */
    public SuperRippleDrawable setRipple(@ColorInt int rippleColor) {
        this.rippleColor = rippleColor;
        this.maskColor = ColorUtils.setAlphaComponent(rippleColor, 0.53f);
        return this;
    }

    /**
     * @param rippleColor //水波纹颜色
     * @param maskColor   //<!--(面具)长按蒙面颜色-->
     * @return
     */
    public SuperRippleDrawable setRipple(@ColorInt int rippleColor, @ColorInt int maskColor) {
        this.rippleColor = rippleColor;
        this.maskColor = maskColor;
        return this;
    }

    /**
     * @param normalColor //正常颜色
     * @param clickColor  //点击颜色
     * @return
     */
    private ColorStateList getColorStateList(@ColorInt int normalColor, @ColorInt int clickColor) {
        int pressed = android.R.attr.state_pressed;
        int[][] states = new int[][]
                {
                        new int[]{-pressed},//未点击
                        new int[]{pressed},//点击
                        new int[]{}//默认
                };

        int[] colors = new int[]
                {
                        normalColor,
                        clickColor,
                        normalColor,
                };
        ColorStateList colorStateList = new ColorStateList(states, colors);
        return colorStateList;
    }

    /**
     * 水波纹专用
     *
     * @param rippleColor //水波纹颜色
     * @param pressColor  //长按颜色
     * @return
     */
    private ColorStateList getRippleColorStateList(@ColorInt int rippleColor, @ColorInt int pressColor) {
        int[][] stateList = new int[][]{
                new int[]{android.R.attr.state_pressed},//布尔值。true指当用户点击或者触摸该控件的状态。默认为false；一般用于设置按钮颜色/图片的设置。
                new int[]{android.R.attr.state_focused},//布尔值。ture指当前控件获得焦点时的状态。默认为false；一般用于EdiText
                new int[]{android.R.attr.state_activated},//尔值。true表示当前控件出于最前端时，应用窗口获得焦点的状态
                new int[]{}
        };

        int[] stateColorList = new int[]{
                pressColor,
                pressColor,
                pressColor,
                rippleColor
        };
        ColorStateList colorStateList = new ColorStateList(stateList, stateColorList);
        return colorStateList;
    }


    /**
     * 四角圆形度数
     */
    private float[] getCornerRadii(float radiusTopLeft, float radiusTopRight, float radiusBottomLeft, float radiusBottomRight) {
        //一个包含8个弧度值，指定外部圆角矩形的 4个角部的弧度及 ：new float[] {l, l, t, t, r, r, b, b};
        // 前2个 左上角， 3 4 ， 右上角， 56， 右下， 78 ，左下，如果没弧度的话，传入null即可。
        return new float[]{radiusTopLeft,
                radiusTopLeft, radiusTopRight, radiusTopRight,
                radiusBottomRight, radiusBottomRight, radiusBottomLeft,
                radiusBottomLeft};
    }


    /**
     * @return
     */
    private Drawable initStateListDrawable() {
        float[] radii = getCornerRadii(topLeftRadius, topRightRadius, bottomLeftRadius, bottomRightRadius);
        RoundRectShape roundRectShape = new RoundRectShape(radii, null, null);
//        ShapeDrawable contentDrawable = new ShapeDrawable();
//        contentDrawable.setShape(roundRectShape);
//        contentDrawable.getPaint().setColor(Color.parseColor("#f7c653"));
//        contentDrawable.getPaint().setStyle(Paint.Style.FILL);

        GradientDrawable contentDrawable = new GradientDrawable();
        //渐变颜色 和 单背景色 不能同时设置
        if (colors != null) {
            contentDrawable.setColors(colors);//设置渐变颜色
            contentDrawable.setGradientType(gradient.ordinal());
            contentDrawable.setOrientation(orientation);//设置渐变方向
        }
        //设置圆角
        contentDrawable.setCornerRadii(radii);
        //设置按钮的描边粗细和颜色
        contentDrawable.setStroke(strokeWidth, normalStrokeColor);


        ShapeDrawable maskDrawable = new ShapeDrawable();
        maskDrawable.setShape(roundRectShape);
        maskDrawable.getPaint().setColor(maskColor);//maskColor   //<!--(面具)长按蒙面颜色-->
        maskDrawable.getPaint().setStyle(Paint.Style.FILL);

        //contentDrawable实际是默认初始化时展示的；maskDrawable 控制了rippleDrawable的范围
        RippleDrawable rippleDrawable = new RippleDrawable(getRippleColorStateList(rippleColor, rippleColor), contentDrawable, maskDrawable);
        return rippleDrawable;

    }


    /**
     * 设置完成之后必须调用 buid() ，否则不生效
     */
    public Drawable buid() {
        return initStateListDrawable();
    }


}
