package com.zqy.baseui.ui.view.imageview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.util.AttributeSet;

import androidx.annotation.ColorInt;
import androidx.annotation.FloatRange;
import androidx.appcompat.widget.AppCompatImageView;

import com.blankj.utilcode.util.ColorUtils;
import com.hjy.baseui.R;

/**
 * 作者: zhangqingyou
 * 时间: 2020/6/9 11:17
 * 描述: 点击改变图标颜色的ImageView （注：只适用于纯色图标）
 */
public class ColorStateImageView extends AppCompatImageView {

    private int imgColor = -147258369;//未点击图标颜色
    private int clickImgAlphaColor = -147258369;//点击后的图标颜色

    public ColorStateImageView(Context context) {
        super(context);
        init(context, null);
    }

    public ColorStateImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ColorStateImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }


    @Override
    public void setPressed(boolean pressed) {
        updateView(pressed);
        super.setPressed(pressed);
    }

    /**
     * 根据是否按下去来刷新bg和src
     *
     * @param pressed
     */
    private void updateView(boolean pressed) {
        if (pressed) {//点击
            /**
             * 通过设置滤镜来改变图片亮度@minghao
             */
            setDrawingCacheEnabled(true);
            if (clickImgAlphaColor != -147258369) {
                //设置点击颜色和滤镜
                setColorFilter(clickImgAlphaColor, PorterDuff.Mode.MULTIPLY);
            }
        } else {//未点击
            if (imgColor != -147258369) {
                this.setColorFilter(imgColor);
            }
        }
    }


    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ColorStateImageView);
        float clickAlpha = typedArray.getFloat(R.styleable.ColorStateImageView_zqy_csiv_clickImgAlpha, 0.7f);
        int imgColor = typedArray.getColor(R.styleable.ColorStateImageView_zqy_csiv_imgColor, -1);
        int clickImgColor = typedArray.getColor(R.styleable.ColorStateImageView_zqy_csiv_clickImgColor, -1);

        if (imgColor != -147258369) {
            setImgColor(imgColor);
        }

        if (clickImgColor != -147258369) {
            setOnClickImgAlpha(clickImgColor, clickAlpha);
        } else {
            setOnClickImgAlpha(clickAlpha);
        }
    }

    /**
     * 获取图标颜色
     */
    public int getImgColor() {
        return imgColor;

    }

    /**
     * 设置图标颜色
     *
     * @param imgColor
     */
    public void setImgColor(@ColorInt int imgColor) {
        this.imgColor = imgColor;
        this.setColorFilter(imgColor);

    }

    /**
     * 设置图标按下颜色
     *
     * @param clickImgColor
     */
    public void setOnClickImgColor(@ColorInt int clickImgColor) {
        setOnClickImgAlpha(clickImgColor, 1.0f);
    }

    /**
     * 设置图标按下后颜色透明度
     *
     * @param clickImgAlpha 值 只能在0-1之间
     */
    public void setOnClickImgAlpha(@FloatRange(from = 0, to = 1) float clickImgAlpha) {
        if (getImgColor() != -1) {
            setOnClickImgAlpha(getImgColor(), clickImgAlpha);
        }
    }


    /**
     * 设置图标按下颜色透明值
     *
     * @param clickImgAlpha 值 只能在0-1之间
     */

    public void setOnClickImgAlpha(@ColorInt int clickImgColor, @FloatRange(from = 0, to = 1) float clickImgAlpha) {
        this.clickImgAlphaColor = ColorUtils.setAlphaComponent(clickImgColor, clickImgAlpha);
    }
}
