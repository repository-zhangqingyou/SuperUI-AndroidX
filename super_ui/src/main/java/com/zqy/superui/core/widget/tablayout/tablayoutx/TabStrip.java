package com.zqy.superui.core.widget.tablayout.tablayoutx;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Scroller;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;

/**
 * 作者: zhangqingyou
 * 时间: 2020/7/27 15:17
 * 描述:
 */
public class TabStrip extends View {
    private Paint mPaint;
    private Scroller mScroller;

    private int mIndicatorCenterX;//实时x位置
    private int mIndicatorWidth = 25;//指示器宽度
    private int mIndicatorHeight = 2;//指示器高度
    private RectF mIndicatorRect;
    private int ANIM_DURATION = 500;//动画时间
    private int mRadius = 5;//指示器圆角

    public TabStrip(Context context) {
        this(context, null);
    }

    public TabStrip(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TabStrip(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStrokeWidth(5);
        //  mPaint.setColor(0xFFFF5777);
        mScroller = new Scroller(getContext());
        mIndicatorRect = new RectF();
    }

    /**
     * 设置指示器颜色
     *
     * @param color
     */
    public void setColor(@ColorInt int color) {
        mPaint.setColor(color);
    }

    /**
     *
     */
    @ColorInt
    private int startColor;
    @ColorInt
    private int endColor;

    public void setGradientColor(@ColorInt int startColor, @ColorInt int endColor) {
        this.startColor = startColor;
        this.endColor = endColor;


    }

    /**
     * 指示器宽度
     *
     * @param mIndicatorWidth
     */
    public void setIndicatorWidth(int mIndicatorWidth) {
        this.mIndicatorWidth = mIndicatorWidth;
    }

    /**
     * 指示器高度
     *
     * @param mIndicatorHeight
     */
    public void setIndicatorHeight(int mIndicatorHeight) {
        this.mIndicatorHeight = mIndicatorHeight;
    }

    /**
     * 动画时间
     *
     * @param animDuration
     */
    public void setAnimDuration(int animDuration) {
        this.ANIM_DURATION = animDuration;
    }

    /**
     * 指示器圆角
     *
     * @param mRadius
     */
    public void setRadius(int mRadius) {
        this.mRadius = mRadius;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(dp2px(mIndicatorHeight), MeasureSpec.EXACTLY));
    }


    public void smoothScrollTo(int destX) {
        smoothScrollTo(destX, ANIM_DURATION);
    }


    public void smoothScrollTo(int destX, int duration) {
        int finalX = mScroller.getFinalX();
        int deltaX = destX - finalX;
        mScroller.startScroll(finalX, 0, deltaX, 0, duration);
        invalidate();
    }

    public void setIndicatorPosition(int x) {
        smoothScrollTo(x, 1);
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            mIndicatorCenterX = mScroller.getCurrX();
            invalidate();
        }
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dp, getContext().getResources().getDisplayMetrics()
        );
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 画指示器
        Log.d("TabStrip", "mIndicatorCenterX:" + mIndicatorCenterX);
        mIndicatorRect.left = mIndicatorCenterX;
        mIndicatorRect.right = mIndicatorCenterX + dp2px(mIndicatorWidth);
        mIndicatorRect.top = 0;
        mIndicatorRect.bottom = dp2px(mIndicatorHeight);

        //这是LinearGradient最简单的一个构造方法，参数虽多其实很好理解（x0，y0）表示渐变的起点坐标而（x1，y1）则表示渐变的终点坐标，
        // 这两点都是相对于屏幕坐标系而言的，而color0和color1则表示起点的颜色和终点的颜色。TileMode和上面讲的完全一致
        Shader shader = new LinearGradient(mIndicatorRect.left, mIndicatorRect.top, mIndicatorRect.right, mIndicatorRect.bottom, startColor, endColor, Shader.TileMode.MIRROR);
        // 设置shader
        mPaint.setShader(shader);
        canvas.drawRoundRect(mIndicatorRect, dp2px(mRadius), dp2px(mRadius), mPaint);

    }
}
