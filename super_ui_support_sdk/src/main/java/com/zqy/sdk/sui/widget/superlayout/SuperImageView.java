package com.zqy.sdk.sui.widget.superlayout;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.annotation.ColorInt;
import android.support.annotation.FloatRange;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import com.zqy.sdk.utils.utilcode.util.ColorUtils;

/**
 * 作者: zhangqingyou
 * 时间: 2020/6/9 11:17
 * 描述: 点击改变图标颜色的ImageView （注：只适用于纯色图标）
 */
public class SuperImageView extends AppCompatImageView {
    private float clickImgAlpha;//点击透明度
    private int imgColor;//未点击图标颜色
    private int clickImgColor;//点击后的图标颜色

    public SuperImageView(Context context) {
        super(context);
        init(context, null);
    }

    public SuperImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public SuperImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }


    @Override
    public void setPressed(boolean pressed) {
        super.setPressed(pressed);
        if (pressed) {//点击
            if (clickImgColor == Color.TRANSPARENT) {
                if (imgColor != Color.TRANSPARENT) {
                    //通过设置滤镜来改变图片亮度@minghao
                    setDrawingCacheEnabled(true);
                    //设置点击颜色和滤镜
                    int alphaComponent = ColorUtils.setAlphaComponent(imgColor, clickImgAlpha);
                    setColorFilter(alphaComponent, PorterDuff.Mode.MULTIPLY);
                }
            } else {
                int alphaComponent = ColorUtils.setAlphaComponent(clickImgColor, clickImgAlpha);
                setColorFilter(alphaComponent, PorterDuff.Mode.MULTIPLY);
            }
        } else {//未点击
            if (imgColor != Color.TRANSPARENT) {
                setColorFilter(imgColor);
            }
        }

    }


    private void init(Context context, AttributeSet attrs) {
//        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SuperImageView);
//        clickImgAlpha = typedArray.getFloat(R.styleable.SuperImageView_zqy_click_img_alpha, 0.7f);
//        imgColor = typedArray.getColor(R.styleable.SuperImageView_zqy_img_color, Color.TRANSPARENT);
//        clickImgColor = typedArray.getColor(R.styleable.SuperImageView_zqy_click_img_color, Color.TRANSPARENT);

        clickImgAlpha = 0.7f;
        imgColor = Color.TRANSPARENT;
        clickImgColor = Color.TRANSPARENT;

        if (imgColor != Color.TRANSPARENT) {
            setImgColor(imgColor);
        }
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
    public void setClickImgColor(@ColorInt int clickImgColor) {
        this.clickImgColor = clickImgColor;
    }


    /**
     * 设置图标按下颜色透明值
     *
     * @param clickImgAlpha 值 只能在0-1之间
     */

    public void setClickImgAlpha(@FloatRange(from = 0, to = 1) float clickImgAlpha) {
        this.clickImgAlpha = clickImgAlpha;//
    }
}
