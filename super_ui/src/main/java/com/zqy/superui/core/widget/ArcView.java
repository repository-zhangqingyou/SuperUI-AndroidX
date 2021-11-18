package com.zqy.superui.core.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;

import com.zqy.superui.R;


/**
 * 作者: zhangqingyou
 * 时间: 2021/11/18
 * 描述:圆弧View
 */
public class ArcView extends View {
    private Paint mPaint;
    private RectF mRect = new RectF();
    private Point mCircleCenter;
    private int mHeight;
    private int mWidth;
    private float mRadius;
    private float arc_radius_times;//圆弧半径是屏幕宽度的几倍
    private int mStartColor;
    private int mEndColor;
    private LinearGradient mLinearGradient;


    public ArcView(Context context) {
        super(context);
        init(null);
    }

    public ArcView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public ArcView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ArcView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        mPaint = new Paint();
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        //  mBitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.splash);
        mCircleCenter = new Point();

        if (attrs != null) {
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.ArcView);
            mStartColor = typedArray.getColor(R.styleable.ArcView_zqy_start_color, ContextCompat.getColor(getContext(), R.color.colorAccent));
            mEndColor = typedArray.getColor(R.styleable.ArcView_zqy_end_color, ContextCompat.getColor(getContext(), R.color.colorPrimaryDark));
            arc_radius_times = typedArray.getFloat(R.styleable.ArcView_zqy_arc_radius_times, 2);
        } else {
            mStartColor = ContextCompat.getColor(getContext(), R.color.colorAccent);
            mEndColor = ContextCompat.getColor(getContext(), R.color.colorPrimaryDark);
            arc_radius_times = 2;// 圆弧高度
        }


    }


    /**
     * @param startColor
     * @param endColor
     */
    public void setColor(@ColorInt int startColor, @ColorInt int endColor) {
        mStartColor = startColor;
        mEndColor = endColor;
        /*
        (0,0)->(0,400)从上到下
(0,400)->(0,0) 从下到上

0,0）->(getMeasuredWidth(),0) 表示从左到右
(getMeasuredWidth(),0)->(0,0) 表示从右到左

0,0）-> (getMeasuredWidth(),getMeasuredHeight()) 斜角，从左上角到右下角

         */
        mLinearGradient = new LinearGradient(0, 0, getMeasuredWidth(), 0, mStartColor, mEndColor, Shader.TileMode.MIRROR);
        invalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mHeight = h;
        mWidth = w;
        // 半径
        mRadius = w * arc_radius_times;
        // 矩形
        mRect.left = 0;
        mRect.top = 0;
        mRect.right = w;
        mRect.bottom = mHeight;
        // 圆心坐标
        mCircleCenter.x = w / 2;
        mCircleCenter.y = -(int) (mRadius - h);//mCircleCenter.y 正数圆弧向上，负数圆弧向下
        /*
        (0,0)->(0,400)从上到下
(0,400)->(0,0) 从下到上

0,0）->(getMeasuredWidth(),0) 表示从左到右
(getMeasuredWidth(),0)->(0,0) 表示从右到左

0,0）-> (getMeasuredWidth(),getMeasuredHeight()) 斜角，从左上角到右下角

         */
        mLinearGradient = new LinearGradient(0, 0, getMeasuredWidth(), 0, mStartColor, mEndColor, Shader.TileMode.MIRROR);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        int canvasWidth = canvas.getWidth();
        int canvasHeight = canvas.getHeight();
        int layerId = canvas.saveLayer(0, 0, canvasWidth, canvasHeight, null, Canvas.ALL_SAVE_FLAG);
        canvas.drawCircle(mCircleCenter.x, mCircleCenter.y, mRadius, mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        mPaint.setShader(mLinearGradient);//绘制渐变色
        canvas.drawRect(mRect, mPaint);

        mPaint.setXfermode(null);
        canvas.restoreToCount(layerId);
    }
}
