package com.zqy.sui.core.widget.superlayout;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;

import androidx.annotation.ColorInt;
import androidx.appcompat.widget.AppCompatTextView;

import com.zqy.sui.core.other.drawable.DrawableImpl;
import com.zqy.sui.core.other.drawable.SuperGradientDrawable;

/**
 * 作者: zhangqingyou
 * 时间: 2020/9/11 12:14
 * 描述:
 * 代码调用示例：
 * SuperTextView buttonView=new SuperTextView(getContext())
 * .setClickEffect(true)
 * .setClickAlpha(0.7f)
 * .setSolidColor(ContextCompat.getColor(getContext(),R.color.xui_config_color_red))
 * .buid();
 */
public class SuperTextView extends AppCompatTextView implements DrawableImpl {
    private SuperGradientDrawable superGradientDrawable;

    public SuperTextView(Context context) {
        super(context);
    }

    public SuperTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public SuperTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
//        Map<Object, Object> attrMap = new HashMap<>();
//
//        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SuperTextView);
//        attrMap.put(SuperAttr.是否有按下效果, typedArray.getBoolean(R.styleable.SuperTextView_zqy_click_effect, true));
//        attrMap.put(SuperAttr.按下时透明度, typedArray.getFloat(R.styleable.SuperTextView_zqy_click_alpha, 0.7f));
//        attrMap.put(SuperAttr.填充颜色, typedArray.getColor(R.styleable.SuperTextView_zqy_solid_color, Color.TRANSPARENT));
//        attrMap.put(SuperAttr.边框颜色, typedArray.getColor(R.styleable.SuperTextView_zqy_stroke_color, Color.TRANSPARENT));
//        attrMap.put(SuperAttr.渐变开始颜色, typedArray.getColor(R.styleable.SuperTextView_zqy_start_color, Color.TRANSPARENT));
//        attrMap.put(SuperAttr.渐变结束颜色, typedArray.getColor(R.styleable.SuperTextView_zqy_end_color, Color.TRANSPARENT));
//        attrMap.put(SuperAttr.按下时填充颜色, typedArray.getColor(R.styleable.SuperTextView_zqy_click_solid_color, Color.TRANSPARENT));
//        attrMap.put(SuperAttr.按下时边框颜色, typedArray.getColor(R.styleable.SuperTextView_zqy_click_stroke_color, Color.TRANSPARENT));
//        attrMap.put(SuperAttr.字体颜色, typedArray.getColor(R.styleable.SuperTextView_zqy_text_color, Color.GRAY));
//        attrMap.put(SuperAttr.按下时字体颜色, typedArray.getColor(R.styleable.SuperTextView_zqy_click_text_color, Color.TRANSPARENT));
//        attrMap.put(SuperAttr.渐变模式, typedArray.getInt(R.styleable.SuperTextView_zqy_gradient, 0));
//        attrMap.put(SuperAttr.渐变方向, typedArray.getInt(R.styleable.SuperTextView_zqy_orientation, 6));
//        attrMap.put(SuperAttr.边框宽度, typedArray.getDimensionPixelSize(R.styleable.SuperTextView_zqy_stroke_width, 0));
//        attrMap.put(SuperAttr.四圆角, typedArray.getDimensionPixelSize(R.styleable.SuperTextView_zqy_radius, 5));
//        attrMap.put(SuperAttr.左上圆角, typedArray.getDimensionPixelSize(R.styleable.SuperTextView_zqy_top_left_radius, 0));
//        attrMap.put(SuperAttr.右上圆角, typedArray.getDimensionPixelSize(R.styleable.SuperTextView_zqy_top_right_radius, 0));
//        attrMap.put(SuperAttr.左下圆角, typedArray.getDimensionPixelSize(R.styleable.SuperTextView_zqy_bottom_left_radius, 0));
//        attrMap.put(SuperAttr.右下圆角, typedArray.getDimensionPixelSize(R.styleable.SuperTextView_zqy_bottom_right_radius, 0));
//
//
//        typedArray.recycle();


        superGradientDrawable = new SuperGradientDrawable();
        superGradientDrawable.initTypedArray(this, context, attrs);//attrs标签值初始化（反射获取，此方法无法预览，运行才有效果）
        // superGradientDrawable.initTypedArray(this, attrMap);
    }

    @Override
    public void setBackground(Drawable background) {
        superGradientDrawable.setClickEffect(false);//必须关闭才有效
        super.setBackground(background);
    }

    @Override
    public void setBackgroundColor(int color) {
        superGradientDrawable.setClickEffect(false);//必须关闭才有效
        super.setBackgroundColor(color);
    }

    @Override
    public void setBackgroundDrawable(Drawable background) {
        superGradientDrawable.setClickEffect(false);//必须关闭才有效
        super.setBackgroundDrawable(background);
    }

    @Override
    public void setBackgroundResource(int resId) {
        superGradientDrawable.setClickEffect(false);//必须关闭才有效
        super.setBackgroundResource(resId);
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
    public void setSolidColor(@ColorInt int color) {
        superGradientDrawable.setSolidColor(color);
    }

    @Override
    public void setClickSolidColor(@ColorInt int clickSolidColor) {
        superGradientDrawable.setClickSolidColor(clickSolidColor);
    }

    @Override
    public void setStrokeColorAndWidth(int strokeWidth,@ColorInt  int color) {
        superGradientDrawable.setStrokeColorAndWidth(strokeWidth, color);
    }

    @Override
    public void setClickStrokeColor(@ColorInt int clickStrokeColor) {
        superGradientDrawable.setClickStrokeColor(clickStrokeColor);
    }

    @Override
    public void setGradient(@ColorInt int starColor,@ColorInt  int endColor, int gradient, GradientDrawable.Orientation orientation) {
        superGradientDrawable.setGradient(starColor, endColor, gradient, orientation);
    }

    @Override
    public void setNormalTextColor(@ColorInt int normalTextColor) {
        superGradientDrawable.setNormalTextColor(normalTextColor);
    }

    @Override
    public void setClickTextColor(@ColorInt int clickTextColor) {
        superGradientDrawable.setClickTextColor(clickTextColor);
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

}
