package com.zqy.baseui.ui.view.framelayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.zqy.baseui.R;
import com.zqy.baseui.ui.drawable.DrawableImpl;
import com.zqy.baseui.ui.drawable.SuperAttr;
import com.zqy.baseui.ui.drawable.SuperGradientDrawable;

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
        Map<Object, Object> attrMap = new HashMap<>();

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SuperFrameLayout);
        attrMap.put(SuperAttr.是否有按下效果, typedArray.getBoolean(R.styleable.SuperFrameLayout_zqy_click_effect, true));
        attrMap.put(SuperAttr.按下时透明度, typedArray.getFloat(R.styleable.SuperFrameLayout_zqy_click_alpha, 0.7f));
        attrMap.put(SuperAttr.填充颜色, typedArray.getColor(R.styleable.SuperFrameLayout_zqy_solid_color, Color.TRANSPARENT));
        attrMap.put(SuperAttr.边框颜色, typedArray.getColor(R.styleable.SuperFrameLayout_zqy_stroke_color, Color.TRANSPARENT));
        attrMap.put(SuperAttr.渐变开始颜色, typedArray.getColor(R.styleable.SuperFrameLayout_zqy_start_color, Color.TRANSPARENT));
        attrMap.put(SuperAttr.渐变结束颜色, typedArray.getColor(R.styleable.SuperFrameLayout_zqy_end_color, Color.TRANSPARENT));
        attrMap.put(SuperAttr.按下时填充颜色, typedArray.getColor(R.styleable.SuperFrameLayout_zqy_click_solid_color, Color.TRANSPARENT));
        attrMap.put(SuperAttr.按下时边框颜色, typedArray.getColor(R.styleable.SuperFrameLayout_zqy_click_stroke_color, Color.TRANSPARENT));
        attrMap.put(SuperAttr.字体颜色, typedArray.getColor(R.styleable.SuperFrameLayout_zqy_text_color, Color.GRAY));
        attrMap.put(SuperAttr.按下时字体颜色, typedArray.getColor(R.styleable.SuperFrameLayout_zqy_click_text_color, Color.TRANSPARENT));
        attrMap.put(SuperAttr.渐变模式, typedArray.getInt(R.styleable.SuperFrameLayout_zqy_gradient, 0));
        attrMap.put(SuperAttr.渐变方向, typedArray.getInt(R.styleable.SuperFrameLayout_zqy_orientation, 6));
        attrMap.put(SuperAttr.边框宽度, typedArray.getDimensionPixelSize(R.styleable.SuperFrameLayout_zqy_stroke_width, 0));
        attrMap.put(SuperAttr.四圆角, typedArray.getDimensionPixelSize(R.styleable.SuperFrameLayout_zqy_radius, 5));
        attrMap.put(SuperAttr.左上圆角, typedArray.getDimensionPixelSize(R.styleable.SuperFrameLayout_zqy_top_left_radius, 0));
        attrMap.put(SuperAttr.右上圆角, typedArray.getDimensionPixelSize(R.styleable.SuperFrameLayout_zqy_top_right_radius, 0));
        attrMap.put(SuperAttr.左下圆角, typedArray.getDimensionPixelSize(R.styleable.SuperFrameLayout_zqy_bottom_left_radius, 0));
        attrMap.put(SuperAttr.右下圆角, typedArray.getDimensionPixelSize(R.styleable.SuperFrameLayout_zqy_bottom_right_radius, 0));


        typedArray.recycle();
        
        superGradientDrawable = new SuperGradientDrawable();
        //superGradientDrawable.initTypedArray(this, context, attrs);//attrs标签值初始化（反射获取，此方法无法预览，运行才有效果）
        superGradientDrawable.initTypedArray(this, attrMap);
    }



    @Override
    public void setRadius(int radiusTopLeft, int radiusTopRight, int radiusBottomLeft, int radiusBottomRight) {
        superGradientDrawable.setRadius(radiusTopLeft, radiusTopRight, radiusBottomLeft, radiusBottomRight);
    }

    @Override
    public void setClickEffect(boolean clickEffect) {
        superGradientDrawable.setClickEffect(clickEffect);
    }

    @Override
    public void setClickAlpha(float clickAlpha) {
        superGradientDrawable.setClickAlpha(clickAlpha);
    }

    @Override
    public void setSolidColor(int color) {
        superGradientDrawable.setSolidColor(color);
    }

    @Override
    public void setClickSolidColor(int clickSolidColor) {
        superGradientDrawable.setClickSolidColor(clickSolidColor);
    }

    @Override
    public void setStrokeColorAndWidth(int strokeWidth, int color) {
        superGradientDrawable.setStrokeColorAndWidth(strokeWidth, color);
    }

    @Override
    public void setClickStrokeColor(int clickStrokeColor) {
        superGradientDrawable.setClickStrokeColor(clickStrokeColor);
    }

    @Override
    public void setGradient(int starColor, int endColor, int gradient, GradientDrawable.Orientation orientation) {
        superGradientDrawable.setGradient(starColor, endColor, gradient, orientation);
    }

    @Override
    public void setNormalTextColor(int normalTextColor) {
    }

    @Override
    public void setClickTextColor(int clickTextColor) {
    }
    /**
     * 设置完成之后必须调用 buid() ，否则不生效
     */
    @Override
    public void buid() {
        superGradientDrawable.buid();
    }

    @Override
    public void setPressed(boolean pressed) {
        super.setPressed(pressed);
        superGradientDrawable.setPressed(pressed);
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        int action = event.getAction();
//        superGradientDrawable.onTouchEvent(event);
//        return super.onTouchEvent(event);
//    }
}
