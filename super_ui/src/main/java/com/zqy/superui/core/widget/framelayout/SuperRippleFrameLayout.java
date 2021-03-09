package com.zqy.superui.core.widget.framelayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import androidx.annotation.ColorInt;

import com.zqy.superui.R;
import com.zqy.superui.SuperUIManager;
import com.zqy.superui.core.enums.Gradient;
import com.zqy.superui.core.other.drawable.SuperRippleDrawable;

/**
 * 作者: zhangqingyou
 * 时间: 2021/2/6 17:09
 * 描述:
 */
public class SuperRippleFrameLayout extends FrameLayout {

    private SuperRippleDrawable superRippleDrawable;

    public SuperRippleFrameLayout(Context context) {
        super(context);
        init(context, null);
    }

    public SuperRippleFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public SuperRippleFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        superRippleDrawable = new SuperRippleDrawable();
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SuperRippleFrameLayout);
            int 填充颜色 = typedArray.getColor(R.styleable.SuperRippleFrameLayout_zqy_solid_color, Color.TRANSPARENT);
            int 边框颜色 = typedArray.getColor(R.styleable.SuperRippleFrameLayout_zqy_stroke_color, Color.TRANSPARENT);
            int 渐变开始颜色 = typedArray.getColor(R.styleable.SuperRippleFrameLayout_zqy_start_color, Color.TRANSPARENT);
            int 渐变结束颜色 = typedArray.getColor(R.styleable.SuperRippleFrameLayout_zqy_end_color, Color.TRANSPARENT);
            int 渐变模式 = typedArray.getInt(R.styleable.SuperRippleFrameLayout_zqy_gradient, Gradient.LINEAR_GRADIENT.ordinal());//默认 线性梯度(线性渐变)
            int 渐变方向 = typedArray.getInt(R.styleable.SuperRippleFrameLayout_zqy_orientation, GradientDrawable.Orientation.LEFT_RIGHT.ordinal());//默认从左到右
            int 边框宽度 = typedArray.getDimensionPixelSize(R.styleable.SuperRippleFrameLayout_zqy_stroke_width, 0);
            int 四圆角 = typedArray.getDimensionPixelSize(R.styleable.SuperRippleFrameLayout_zqy_radius, (int) SuperUIManager.getApplication().getResources().getDimension(R.dimen._5sdp));//默认圆角5dp
            int 左上圆角 = typedArray.getDimensionPixelSize(R.styleable.SuperRippleFrameLayout_zqy_top_left_radius, 0);
            int 右上圆角 = typedArray.getDimensionPixelSize(R.styleable.SuperRippleFrameLayout_zqy_top_right_radius, 0);
            int 左下圆角 = typedArray.getDimensionPixelSize(R.styleable.SuperRippleFrameLayout_zqy_bottom_left_radius, 0);
            int 右下圆角 = typedArray.getDimensionPixelSize(R.styleable.SuperRippleFrameLayout_zqy_bottom_right_radius, 0);

            int 正常波纹颜色 = typedArray.getColor(R.styleable.SuperRippleFrameLayout_zqy_ripple_color, Color.TRANSPARENT);//正常波纹颜色
            int mask = typedArray.getColor(R.styleable.SuperRippleLinearLayout_zqy_mask_color, Color.TRANSPARENT);//<!--(面具)长按蒙面颜色-->
            boolean 是否前背景 = typedArray.getBoolean(R.styleable.SuperRippleFrameLayout_zqy_is_foreground, false);//是否前背景
            typedArray.recycle();


            if (四圆角 > 0)
                superRippleDrawable.setRadius(四圆角);
            else
                superRippleDrawable.setRadius(左上圆角, 右上圆角, 左下圆角, 右下圆角);

            superRippleDrawable.setStrokeColorState(边框宽度, 边框颜色);

            Gradient gradient = Gradient.LINEAR_GRADIENT;
            for (Gradient value : Gradient.values()) {
                if (渐变模式 == value.ordinal()) {
                    gradient = value;
                    break;
                }
            }
            GradientDrawable.Orientation orientation = GradientDrawable.Orientation.LEFT_RIGHT;
            for (GradientDrawable.Orientation value : GradientDrawable.Orientation.values()) {
                if (渐变方向 == value.ordinal()) {
                    orientation = value;
                    break;
                }
            }
            if (渐变开始颜色 != Color.TRANSPARENT || 渐变结束颜色 != Color.TRANSPARENT)
                superRippleDrawable.setGradient(渐变开始颜色, 渐变结束颜色, gradient, orientation);
            else {
                if (填充颜色 != Color.TRANSPARENT)
                    superRippleDrawable.setGradient(填充颜色, 填充颜色, gradient, orientation);
            }



            if (正常波纹颜色 != Color.TRANSPARENT) {
                if (mask == Color.TRANSPARENT) {
                    superRippleDrawable.setRipple(正常波纹颜色);
                } else {
                    superRippleDrawable.setRipple(正常波纹颜色, mask);
                }
            }

            if (是否前背景 && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                setForeground(superRippleDrawable.buid());
            else
                setBackground(superRippleDrawable.buid());

        }

    }


    /**
     * 设置按下透明值(包括按下时的 《字体，边框，填充》 颜色)
     *
     * @param clickAlpha
     */
    public void setClickAlpha(float clickAlpha) {
        superRippleDrawable.setClickAlpha(clickAlpha);
        // superGradientDrawable.setPressed(false);//true：按下时，false:抬起时(正常时)
    }

    /**
     * 四角圆形度数（单位dp）
     */
    public void setRadius(int radius) {
        if (radius > 0) {
            superRippleDrawable.setRadius(radius, radius, radius, radius);
        } else {
            superRippleDrawable.setRadius(0, 0, 0, 0);
        }

        // superGradientDrawable.setPressed(false);//true：按下时，false:抬起时(正常时)
    }

    /**
     * 四角圆形度数（单位dp）
     */
    public void setRadius(int radiusTopLeft, int radiusTopRight, int radiusBottomLeft, int radiusBottomRight) {
        superRippleDrawable.setRadius(radiusTopLeft < 1 ? 0 : radiusTopLeft,
                radiusTopRight < 1 ? 0 : radiusTopRight,
                radiusBottomLeft < 1 ? 0 : radiusBottomLeft,
                radiusBottomRight < 1 ? 0 : radiusBottomRight);
        // superGradientDrawable.setPressed(false);//true：按下时，false:抬起时(正常时)
    }

    public void setSolidColorState(int normalSolidColor) {
        setSolidColorState(normalSolidColor, normalSolidColor);
    }

    /**
     * 设置填充颜色
     *
     * @param normalSolidColor 正常（抬起）填充颜色
     * @param clickSolidColor  点击（按下）填充颜色
     */
    public void setSolidColorState(@ColorInt int normalSolidColor, @ColorInt int clickSolidColor) {
        superRippleDrawable.setGradient(normalSolidColor, clickSolidColor, Gradient.LINEAR_GRADIENT, GradientDrawable.Orientation.LEFT_RIGHT);
    }


    /**
     * 设置边框颜色及宽度
     *
     * @param strokeWidth       边框宽度
     * @param normalStrokeColor 边框颜色
     */
    public void setStrokeColorState(int strokeWidth, @ColorInt int normalStrokeColor) {
        superRippleDrawable.setStrokeColorState(strokeWidth, normalStrokeColor);
    }

    public void setGradient(int starColor, int endColor) {
        setGradient(starColor, endColor, Gradient.LINEAR_GRADIENT, GradientDrawable.Orientation.LEFT_RIGHT);
    }


    /**
     * 设置渐变颜色
     *
     * @param starColor
     * @param endColor
     * @param gradient    * LINEAR_GRADIENT:线性梯度（默认，常用）
     *                    * RADIAL_GRADIENT:圆形渐变
     *                    * SWEEP_GRADIENT:扫描式渐变
     *                    * RING:环
     * @param orientation 渐变方向
     * @return
     */
    public void setGradient(@ColorInt int starColor, @ColorInt int endColor, Gradient gradient, GradientDrawable.Orientation orientation) {
        superRippleDrawable.setGradient(starColor, endColor, gradient, orientation);
    }
    /**
     * 设置水波纹
     *
     * @param rippleColor 水波纹颜色
     */
    public void setRipple(int rippleColor) {
        superRippleDrawable.setRipple(rippleColor);
    }


    /**
     * 设置水波纹
     *
     * @param rippleColor 水波纹颜色
     * @param maskColor   (面具)长按蒙面颜色
     */
    public void setRipple(int rippleColor, int maskColor) {
        superRippleDrawable.setRipple(rippleColor, maskColor);
    }

    /**
     * 设置完成之后必须调用 buid() ，否则不生效
     */
    public void buid(boolean is_foreground) {
        if (is_foreground && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            setForeground(superRippleDrawable.buid());
        else
            setBackground(superRippleDrawable.buid());
    }
}
