package com.zqy.baseui.ui.divider;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

import com.zqy.baseui.R;


/**
 * 作者: zhangqingyou
 * 时间: 2020/9/7 17:34
 * 描述: 虚线View
 */
public class DividerView extends View {
    private int orientation;
    private Paint mPaint;

    public DividerView(Context context) {
        super(context);
    }

    public DividerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        int dashGap, dashLength, dashThickness;
        int color;

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.DividerView, 0, 0);

        try {
            dashGap = a.getDimensionPixelSize(R.styleable.DividerView_zqy_dashGap, 5);
            dashLength = a.getDimensionPixelSize(R.styleable.DividerView_zqy_dashLength, 5);
            dashThickness = a.getDimensionPixelSize(R.styleable.DividerView_zqy_dashThickness, 3);
            color = a.getColor(R.styleable.DividerView_zqy_divider_line_color, 0xff000000);
            orientation = a.getInt(R.styleable.DividerView_zqy_divider_orientation, LinearLayout.HORIZONTAL);
        } finally {
            a.recycle();
        }

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(color);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(dashThickness);
        mPaint.setPathEffect(new DashPathEffect(new float[]{dashGap, dashLength,}, 0));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (orientation ==  LinearLayout.HORIZONTAL) {
            float center = getHeight() * 0.5f;
            canvas.drawLine(0, center, getWidth(), center, mPaint);
        } else {
            float center = getWidth() * 0.5f;
            canvas.drawLine(center, 0, center, getHeight(), mPaint);
        }
    }

    public void setBgColor(@NonNull int color) {
        mPaint.setColor(color);
        invalidate();
    }
}
