package com.zqy.baseui.ui.view.framelayout;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.zqy.baseui.ui.drawable.DrawableImpl;
import com.zqy.baseui.ui.drawable.SuperGradientDrawable;

/**
 * 作者: zhangqingyou
 * 时间: 2020/9/12
 * 描述:
 * 代码调用示例：
 */
public class SuperFrameLayout extends FrameLayout implements DrawableImpl {
    private SuperGradientDrawable superGradientDrawable;

    public SuperFrameLayout(Context context) {
        super(context);
    }

    public SuperFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        ininView(context, attrs);
    }

    public SuperFrameLayout(Context context, AttributeSet attrs) {
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
    }

    @Override
    public void setClickTextColor(int clickTextColor) {
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
//        int action = event.getAction();
//        superGradientDrawable.onTouchEvent(event);
//        return super.onTouchEvent(event);
//    }
}
