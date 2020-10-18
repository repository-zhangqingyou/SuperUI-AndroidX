package com.zqy.sdk.sui.other.divider;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.LinearLayout;

import com.zqy.sdk.utils.utilcode.util.SizeUtils;


/**
 * 作者: zhangqingyou
 * 时间: 2020/9/7 17:34
 * 描述: 虚线View
 */
public class DividerView extends View {
    private int orientation;//虚线朝向
    private Paint mPaint;
    int dashGap = SizeUtils.dp2px(5);//虚线dash间隔
    int dashLength = SizeUtils.dp2px(5);//虚线dash宽度
    int dashThickness = SizeUtils.dp2px(3);//虚线宽度
    int color = 0xff000000;//虚线颜色


    public DividerView(Context context) {
        super(context);
        build();
    }


    public void setBgColor(@NonNull int color) {
        mPaint.setColor(color);
        invalidate();
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    public void setDashGap(int dashGap) {
        this.dashGap = dashGap;
    }

    public void setDashLength(int dashLength) {
        this.dashLength = dashLength;
    }

    public void setDashThickness(int dashThickness) {
        this.dashThickness = dashThickness;
    }

    public void build() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(color);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(dashThickness);
        mPaint.setPathEffect(new DashPathEffect(new float[]{dashGap, dashLength,}, 0));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (orientation == LinearLayout.HORIZONTAL) {
            float center = getHeight() * 0.5f;
            canvas.drawLine(0, center, getWidth(), center, mPaint);
        } else {
            float center = getWidth() * 0.5f;
            canvas.drawLine(center, 0, center, getHeight(), mPaint);
        }
    }


}
