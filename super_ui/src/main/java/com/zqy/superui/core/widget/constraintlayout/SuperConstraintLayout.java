package com.zqy.superui.core.widget.constraintlayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;

import androidx.annotation.ColorInt;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.blankj.utilcode.util.SizeUtils;
import com.zqy.superui.R;
import com.zqy.superui.core.other.drawable.DrawableImpl;
import com.zqy.superui.core.other.drawable.Gradient;
import com.zqy.superui.core.other.drawable.SuperAttr;
import com.zqy.superui.core.other.drawable.SuperGradientDrawable;

import java.util.HashMap;
import java.util.Map;

/**
 * 作者: zhangqingyou
 * 时间: 2020/11/1 21:39
 * 描述:
 */
public class SuperConstraintLayout extends ConstraintLayout implements DrawableImpl {
    private SuperGradientDrawable superGradientDrawable;

    public SuperConstraintLayout(Context context) {
        super(context);
        init(context, null);
    }

    public SuperConstraintLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public SuperConstraintLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        Map<Object, Object> attrMap = new HashMap<>();

        TypedArray typedArray = null;
        if (attrs != null)
            typedArray = context.obtainStyledAttributes(attrs, R.styleable.SuperConstraintLayout);

        attrMap.put(SuperAttr.是否有按下效果, typedArray == null ? true : typedArray.getBoolean(R.styleable.SuperConstraintLayout_zqy_click_effect, true));//默认有点击效果
        attrMap.put(SuperAttr.按下时透明度, typedArray == null ? 0.7f : typedArray.getFloat(R.styleable.SuperConstraintLayout_zqy_click_alpha, 0.7f));//默认点击透明度
        attrMap.put(SuperAttr.填充颜色, typedArray == null ? Color.TRANSPARENT : typedArray.getColor(R.styleable.SuperConstraintLayout_zqy_solid_color, Color.TRANSPARENT));
        attrMap.put(SuperAttr.边框颜色, typedArray == null ? Color.TRANSPARENT : typedArray.getColor(R.styleable.SuperConstraintLayout_zqy_stroke_color, Color.TRANSPARENT));
        attrMap.put(SuperAttr.渐变开始颜色, typedArray == null ? Color.TRANSPARENT : typedArray.getColor(R.styleable.SuperConstraintLayout_zqy_start_color, Color.TRANSPARENT));
        attrMap.put(SuperAttr.渐变结束颜色, typedArray == null ? Color.TRANSPARENT : typedArray.getColor(R.styleable.SuperConstraintLayout_zqy_end_color, Color.TRANSPARENT));
        attrMap.put(SuperAttr.按下时填充颜色, typedArray == null ? Color.TRANSPARENT : typedArray.getColor(R.styleable.SuperConstraintLayout_zqy_click_solid_color, Color.TRANSPARENT));
        attrMap.put(SuperAttr.按下时边框颜色, typedArray == null ? Color.TRANSPARENT : typedArray.getColor(R.styleable.SuperConstraintLayout_zqy_click_stroke_color, Color.TRANSPARENT));
        attrMap.put(SuperAttr.字体颜色, typedArray == null ? Color.GRAY : typedArray.getColor(R.styleable.SuperConstraintLayout_zqy_text_color, Color.GRAY));
        attrMap.put(SuperAttr.按下时字体颜色, typedArray == null ? Color.TRANSPARENT : typedArray.getColor(R.styleable.SuperConstraintLayout_zqy_click_text_color, Color.TRANSPARENT));
        attrMap.put(SuperAttr.渐变模式, typedArray == null ? Gradient.LINEAR_GRADIENT.ordinal() : typedArray.getInt(R.styleable.SuperConstraintLayout_zqy_gradient, Gradient.LINEAR_GRADIENT.ordinal()));//默认 线性梯度(线性渐变)
        attrMap.put(SuperAttr.渐变方向, typedArray == null ? GradientDrawable.Orientation.LEFT_RIGHT.ordinal() : typedArray.getInt(R.styleable.SuperConstraintLayout_zqy_orientation, GradientDrawable.Orientation.LEFT_RIGHT.ordinal()));//默认从左到右
        attrMap.put(SuperAttr.边框宽度, typedArray == null ? 0 : typedArray.getDimensionPixelSize(R.styleable.SuperConstraintLayout_zqy_stroke_width, 0));
        attrMap.put(SuperAttr.四圆角, typedArray == null ? SizeUtils.dp2px(5) : typedArray.getDimensionPixelSize(R.styleable.SuperConstraintLayout_zqy_radius, SizeUtils.dp2px(5)));//默认圆角5dp
        attrMap.put(SuperAttr.左上圆角, typedArray == null ? 0 : typedArray.getDimensionPixelSize(R.styleable.SuperConstraintLayout_zqy_top_left_radius, 0));
        attrMap.put(SuperAttr.右上圆角, typedArray == null ? 0 : typedArray.getDimensionPixelSize(R.styleable.SuperConstraintLayout_zqy_top_right_radius, 0));
        attrMap.put(SuperAttr.左下圆角, typedArray == null ? 0 : typedArray.getDimensionPixelSize(R.styleable.SuperConstraintLayout_zqy_bottom_left_radius, 0));
        attrMap.put(SuperAttr.右下圆角, typedArray == null ? 0 : typedArray.getDimensionPixelSize(R.styleable.SuperConstraintLayout_zqy_bottom_right_radius, 0));
        typedArray.recycle();
        superGradientDrawable = new SuperGradientDrawable();
        superGradientDrawable.initTypedArray(this, attrMap);
        //此方法耗时
//        superGradientDrawable = new SuperGradientDrawable();
//        superGradientDrawable.initTypedArray(this, context, attrs);//attrs标签值初始化（反射获取，此方法无法预览，运行才有效果）
        setBackground(superGradientDrawable);
    }

    @Override
    public void setBackground(Drawable background) {
        if (superGradientDrawable != null) {
            if (!(background instanceof SuperGradientDrawable)) {
                //不属于SuperGradientDrawable则关闭点击效果
                superGradientDrawable.setClickEffect(false);//必须关闭才有效
            }
        }
        super.setBackground(background);
    }

    @Override
    public void setBackgroundColor(@ColorInt int color) {
        if (superGradientDrawable != null)
            superGradientDrawable.setClickEffect(false);//必须关闭才有效
        super.setBackgroundColor(color);
    }

    @Override
    public void setBackgroundDrawable(Drawable background) {
        if (superGradientDrawable != null) {
            if (!(background instanceof SuperGradientDrawable)) {
                //不属于SuperGradientDrawable则关闭点击效果
                superGradientDrawable.setClickEffect(false);//必须关闭才有效
            }
        }
        super.setBackgroundDrawable(background);
    }

    @Override
    public void setBackgroundResource(int resId) {
        if (superGradientDrawable != null)
            superGradientDrawable.setClickEffect(false);//必须关闭才有效
        super.setBackgroundResource(resId);
    }


    @Override
    public void setClickEffect(boolean clickEffect) {
        superGradientDrawable.setClickEffect(clickEffect);
        superGradientDrawable.setPressed(false);//true：按下时，false:抬起时(正常时)
    }

    /**
     * 设置按下透明值(包括按下时的 《字体，边框，填充》 颜色)
     *
     * @param clickAlpha
     */
    @Override
    public void setClickAlpha(float clickAlpha) {
        superGradientDrawable.setClickAlpha(clickAlpha);
        superGradientDrawable.setPressed(false);//true：按下时，false:抬起时(正常时)
    }

    /**
     * 四角圆形度数（单位dp）
     */
    @Override
    public void setRadius(int radius) {
        if (radius > 0) {
            int dp2px = SizeUtils.dp2px(radius);
            superGradientDrawable.setRadius(dp2px, dp2px, dp2px, dp2px);
        } else {
            superGradientDrawable.setRadius(0, 0, 0, 0);
        }

        superGradientDrawable.setPressed(false);//true：按下时，false:抬起时(正常时)
    }

    /**
     * 四角圆形度数（单位dp）
     */
    @Override
    public void setRadius(int radiusTopLeft, int radiusTopRight, int radiusBottomLeft, int radiusBottomRight) {
        superGradientDrawable.setRadius(radiusTopLeft < 1 ? 0 : SizeUtils.dp2px(radiusTopLeft),
                radiusTopRight < 1 ? 0 : SizeUtils.dp2px(radiusTopRight),
                radiusBottomLeft < 1 ? 0 : SizeUtils.dp2px(radiusBottomLeft),
                radiusBottomRight < 1 ? 0 : SizeUtils.dp2px(radiusBottomRight));
        superGradientDrawable.setPressed(false);//true：按下时，false:抬起时(正常时)
    }

    /**
     * 设置填充颜色
     *
     * @param normalSolidColor 正常（抬起）填充颜色
     * @param clickSolidColor  点击（按下）填充颜色
     */
    @Override
    public void setSolidColorState(@ColorInt int normalSolidColor, @ColorInt int clickSolidColor) {
        superGradientDrawable.setSolidColorState(normalSolidColor, clickSolidColor);
        superGradientDrawable.setPressed(false);//true：按下时，false:抬起时(正常时)
    }

    /**
     * 设置边框颜色及宽度
     *
     * @param strokeWidth       边框宽度
     * @param normalStrokeColor 正常（抬起）边框颜色
     * @param clickStrokeColor  点击（按下）边框颜色
     */
    @Override
    public void setStrokeColorState(int strokeWidth, @ColorInt int normalStrokeColor, @ColorInt int clickStrokeColor) {
        superGradientDrawable.setStrokeColorState(strokeWidth, normalStrokeColor, clickStrokeColor);
        superGradientDrawable.setPressed(false);//true：按下时，false:抬起时(正常时)
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
    @Override
    public void setGradient(@ColorInt int starColor, @ColorInt int endColor, Gradient gradient, GradientDrawable.Orientation orientation) {
        superGradientDrawable.setGradient(starColor, endColor, gradient, orientation);
        superGradientDrawable.setPressed(false);//true：按下时，false:抬起时(正常时)
    }

    /**
     * 设置字体颜色
     *
     * @param normalTextColor 正常字体颜色
     * @param clickTextColor  按下字体颜色
     */
    @Override
    public void setTextColorState(@ColorInt int normalTextColor, @ColorInt int clickTextColor) {
//        superGradientDrawable.setTextColorState(this, normalTextColor, clickTextColor);
//        superGradientDrawable.setPressed(false);//true：按下时，false:抬起时(正常时)
    }

    /**
     * 按下 抬起
     *
     * @param pressed true：按下时，false:抬起时(正常时)
     */
    @Override
    public void setPressed(boolean pressed) {
        super.setPressed(pressed);
        superGradientDrawable.setPressed(pressed);
    }
}
