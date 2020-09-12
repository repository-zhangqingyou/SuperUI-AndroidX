package com.zqy.baseui.ui.view.textview;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

import com.zqy.baseui.ui.drawable.DrawableImpl;
import com.zqy.baseui.ui.drawable.SuperGradientDrawable;

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
        ininView(context, attrs);
    }

    public SuperTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        ininView(context, attrs);
    }

    private void ininView(Context context, AttributeSet attrs) {
        superGradientDrawable = new SuperGradientDrawable();
        superGradientDrawable.initTypedArray(this, context, attrs);
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
        superGradientDrawable.setNormalTextColor(normalTextColor);
    }

    @Override
    public void setClickTextColor(int clickTextColor) {
        superGradientDrawable.setClickTextColor(clickTextColor);
    }

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
//        superGradientDrawable.onTouchEvent(event);
//        return super.onTouchEvent(event);
//    }
}
