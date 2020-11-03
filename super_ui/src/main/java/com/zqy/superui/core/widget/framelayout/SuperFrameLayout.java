package com.zqy.superui.core.widget.framelayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

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
 * 时间: 2020/9/12
 * 描述:
 * 代码调用示例：
 */
public class SuperFrameLayout extends FrameLayout implements DrawableImpl {
    private SuperGradientDrawable superGradientDrawable;

    public SuperFrameLayout(@NonNull Context context) {
        super(context);
        init(context, null);
    }

    public SuperFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public SuperFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        boolean 是否有按下效果 = true;
        float 按下时透明度 = 0.7f;
        int 填充颜色 = Color.TRANSPARENT;
        int 边框颜色 = Color.TRANSPARENT;
        int 渐变开始颜色 = Color.TRANSPARENT;
        int 渐变结束颜色 = Color.TRANSPARENT;
        int 按下时填充颜色 = Color.TRANSPARENT;
        int 按下时边框颜色 = Color.TRANSPARENT;
        int 字体颜色 = Color.GRAY;
        int 按下时字体颜色 = Color.TRANSPARENT;
        int 渐变模式 = Gradient.LINEAR_GRADIENT.ordinal();
        int 渐变方向 = GradientDrawable.Orientation.LEFT_RIGHT.ordinal();
        int 边框宽度 = 0;
        int 四圆角 = SizeUtils.dp2px(5);
        int 左上圆角 = 0;
        int 右上圆角 = 0;
        int 左下圆角 = 0;
        int 右下圆角 = 0;
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SuperFrameLayout);
            是否有按下效果 = typedArray.getBoolean(R.styleable.SuperFrameLayout_zqy_click_effect, true);//默认有点击效果
            按下时透明度 = typedArray.getFloat(R.styleable.SuperFrameLayout_zqy_click_alpha, 0.7f);//默认点击透明度
            填充颜色 = typedArray.getColor(R.styleable.SuperFrameLayout_zqy_solid_color, Color.TRANSPARENT);
            边框颜色 = typedArray.getColor(R.styleable.SuperFrameLayout_zqy_stroke_color, Color.TRANSPARENT);
            渐变开始颜色 = typedArray.getColor(R.styleable.SuperFrameLayout_zqy_start_color, Color.TRANSPARENT);
            渐变结束颜色 = typedArray.getColor(R.styleable.SuperFrameLayout_zqy_end_color, Color.TRANSPARENT);
            按下时填充颜色 = typedArray.getColor(R.styleable.SuperFrameLayout_zqy_click_solid_color, Color.TRANSPARENT);
            按下时边框颜色 = typedArray.getColor(R.styleable.SuperFrameLayout_zqy_click_stroke_color, Color.TRANSPARENT);
            字体颜色 = typedArray.getColor(R.styleable.SuperFrameLayout_zqy_text_color, Color.GRAY);
            按下时字体颜色 = typedArray.getColor(R.styleable.SuperFrameLayout_zqy_click_text_color, Color.TRANSPARENT);
            渐变模式 = typedArray.getInt(R.styleable.SuperFrameLayout_zqy_gradient, Gradient.LINEAR_GRADIENT.ordinal());//默认 线性梯度(线性渐变)
            渐变方向 = typedArray.getInt(R.styleable.SuperFrameLayout_zqy_orientation, GradientDrawable.Orientation.LEFT_RIGHT.ordinal());//默认从左到右
            边框宽度 = typedArray.getDimensionPixelSize(R.styleable.SuperFrameLayout_zqy_stroke_width, 0);
            四圆角 = typedArray.getDimensionPixelSize(R.styleable.SuperFrameLayout_zqy_radius, SizeUtils.dp2px(5));//默认圆角5dp
            左上圆角 = typedArray.getDimensionPixelSize(R.styleable.SuperFrameLayout_zqy_top_left_radius, 0);
            右上圆角 = typedArray.getDimensionPixelSize(R.styleable.SuperFrameLayout_zqy_top_right_radius, 0);
            左下圆角 = typedArray.getDimensionPixelSize(R.styleable.SuperFrameLayout_zqy_bottom_left_radius, 0);
            右下圆角 = typedArray.getDimensionPixelSize(R.styleable.SuperFrameLayout_zqy_bottom_right_radius, 0);
            typedArray.recycle();
        }
        Map<Object, Object> attrMap = new HashMap<>();
        attrMap.put(SuperAttr.是否有按下效果, 是否有按下效果);
        attrMap.put(SuperAttr.按下时透明度, 按下时透明度);
        attrMap.put(SuperAttr.填充颜色, 填充颜色);
        attrMap.put(SuperAttr.边框颜色, 边框颜色);
        attrMap.put(SuperAttr.渐变开始颜色, 渐变开始颜色);
        attrMap.put(SuperAttr.渐变结束颜色, 渐变结束颜色);
        attrMap.put(SuperAttr.按下时填充颜色, 按下时填充颜色);
        attrMap.put(SuperAttr.按下时边框颜色, 按下时边框颜色);
        attrMap.put(SuperAttr.字体颜色, 字体颜色);
        attrMap.put(SuperAttr.按下时字体颜色, 按下时字体颜色);
        attrMap.put(SuperAttr.渐变模式, 渐变模式);
        attrMap.put(SuperAttr.渐变方向, 渐变方向);
        attrMap.put(SuperAttr.边框宽度, 边框宽度);
        attrMap.put(SuperAttr.四圆角, 四圆角);
        attrMap.put(SuperAttr.左上圆角, 左上圆角);
        attrMap.put(SuperAttr.右上圆角, 右上圆角);
        attrMap.put(SuperAttr.左下圆角, 左下圆角);
        attrMap.put(SuperAttr.右下圆角, 右下圆角);



        superGradientDrawable = new SuperGradientDrawable();
        superGradientDrawable.initTypedArray(attrMap);
        //此方法耗时
//        superGradientDrawable = new SuperGradientDrawable();
//        superGradientDrawable.initTypedArray(this, context, attrs);//attrs标签值初始化（反射获取，此方法无法预览，运行才有效果）
        setBackground(superGradientDrawable);

        //设置字体颜色及按下颜色
        if (按下时字体颜色 == Color.TRANSPARENT)
            setTextColorState(字体颜色, 字体颜色);
        else
            setTextColorState(字体颜色, 按下时字体颜色);
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
        //superGradientDrawable.setPressed(false);//true：按下时，false:抬起时(正常时)
    }

    /**
     * 设置按下透明值(包括按下时的 《字体，边框，填充》 颜色)
     *
     * @param clickAlpha
     */
    @Override
    public void setClickAlpha(float clickAlpha) {
        superGradientDrawable.setClickAlpha(clickAlpha);
        // superGradientDrawable.setPressed(false);//true：按下时，false:抬起时(正常时)
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

        // superGradientDrawable.setPressed(false);//true：按下时，false:抬起时(正常时)
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
        // superGradientDrawable.setPressed(false);//true：按下时，false:抬起时(正常时)
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
