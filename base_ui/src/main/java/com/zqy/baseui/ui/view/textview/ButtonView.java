package com.zqy.baseui.ui.view.textview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.ColorInt;
import androidx.annotation.FloatRange;

import com.blankj.utilcode.util.ColorUtils;
import com.zqy.baseui.R;

/**
 * 作者: zhangqingyou
 * 时间: 2020/9/11 12:14
 * 描述:
 */
public class ButtonView extends com.xuexiang.xui.widget.button.ButtonView {
    private GradientDrawable.Orientation gradientOrientation = GradientDrawable.Orientation.LEFT_RIGHT;
    private float clickAlpha;//按下时 背景颜色和字体颜色 透明度
    private int clickStrokeColor;//按现时 边框颜色
    private int strokeColor;//
    private int normalTextColor;
    private int selectedTextColor;
    private int strokeWidth;
    private int startColor;
    private int endColor;
    private int gradient;
    private int orientation;

    public ButtonView(Context context) {
        super(context);
    }

    public ButtonView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        ininView(context, attrs);
    }

    public ButtonView(Context context, AttributeSet attrs) {
        super(context, attrs);
        ininView(context, attrs);
    }

    private void ininView(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs,
                R.styleable.ButtonView);
        clickAlpha = typedArray.getFloat(R.styleable.ButtonView_clickAlpha, 0.7f);
        clickStrokeColor = typedArray.getColor(R.styleable.ButtonView_clickStrokeColor, Color.TRANSPARENT);
        strokeColor = typedArray.getColor(
                com.xuexiang.xui.R.styleable.ButtonView_textStrokeColor, Color.TRANSPARENT);
        strokeWidth = typedArray.getDimensionPixelSize(
                com.xuexiang.xui.R.styleable.ButtonView_textStrokeWidth, 0);
        normalTextColor = typedArray.getColor(
                com.xuexiang.xui.R.styleable.ButtonView_textNormalTextColor,
                Color.TRANSPARENT);
        selectedTextColor = typedArray.getColor(
                com.xuexiang.xui.R.styleable.ButtonView_textSelectedTextColor,
                Color.TRANSPARENT);
        startColor = typedArray.getColor(R.styleable.ButtonView_zqy_startColor, Color.TRANSPARENT);
        endColor = typedArray.getColor(R.styleable.ButtonView_zqy_endColor, Color.TRANSPARENT);
        gradient = typedArray.getInt(R.styleable.ButtonView_zqy_gradient, 0);//默认线性
        orientation = typedArray.getInt(R.styleable.ButtonView_zqy_orientation, 6);//默认从左到右

        typedArray.recycle();//回收

        GradientDrawable.Orientation[] values = GradientDrawable.Orientation.values();
        for (GradientDrawable.Orientation value : values) {
            if (value.ordinal() == orientation) {
                gradientOrientation = value;
            }
        }

        /**
         * 设置透明度会初始化 填充颜色 边框颜色 字体颜色
         */
        if (startColor != Color.TRANSPARENT || endColor != Color.TRANSPARENT) {
            setGradient(startColor, endColor, gradient, gradientOrientation);
        } else {
            setSolidColor(mNormalSolidColor);
        }
        setStrokeColorAndWidth(strokeWidth, strokeColor);
        setNormalTextColor(normalTextColor);
    }


    /**
     * 设置按下透明值
     *
     * @param clickAlpha
     */
    public ButtonView setClickAlpha(@FloatRange(from = 0, to = 1) float clickAlpha) {
        //(设置透明值时需重设各属性方生效)
        this.clickAlpha = clickAlpha;

        return this;
    }

    /**
     * 设置填充颜色
     *
     * @param color 颜色
     */
    public ButtonView setSolidColor(@ColorInt int color) {
        gradientDrawable.setColor(color);
        return this;
    }


    /**
     * 设置点击填充颜色
     *
     * @param selectedSolidColor 颜色
     */
    public ButtonView setClickSolidColor(@ColorInt int selectedSolidColor) {
        mSelectedSolidColor = selectedSolidColor;
        return this;
    }

    /**
     * 设置边框颜色及宽度
     *
     * @param strokeWidth 边框宽度
     * @param color       边框颜色
     */
    public ButtonView setStrokeColorAndWidth(int strokeWidth, int color) {
        gradientDrawable.setStroke(strokeWidth, color);
        return this;
    }

    /**
     * 设置点击边框颜色
     *
     * @param clickStrokeColor 点击时边框颜色
     */
    public ButtonView setClickStrokeColor(@ColorInt int clickStrokeColor) {
        this.clickStrokeColor = clickStrokeColor;
        return this;
    }

    /**
     * 设置渐变颜色
     *
     * @param starColor
     * @param endColor
     * @return
     */
    public ButtonView setGradient(@ColorInt int starColor, @ColorInt int endColor, int gradient, GradientDrawable.Orientation orientation) {
        gradientDrawable.setColors(new int[]{starColor, endColor});//设置渐变颜色
        gradientDrawable.setGradientType(gradient);//设置线性渐变，除此之外还有：GradientDrawable.SWEEP_GRADIENT（扫描式渐变），GradientDrawable.RADIAL_GRADIENT（圆形渐变）
        gradientDrawable.setOrientation(orientation);//设置渐变方向
        return this;
    }


    /**
     * 设置textView颜色
     *
     * @param normalTextColor 正常状态颜色
     */
    public ButtonView setNormalTextColor(@ColorInt int normalTextColor) {
        setTextColor(normalTextColor);
        return this;
    }


    /**
     * 设置textView选中状态颜色
     *
     * @param clickTextColor 按下状态颜色
     */
    public ButtonView setClickTextColor(@ColorInt int clickTextColor) {
        selectedTextColor = clickTextColor;
        return this;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {

            if (startColor != Color.TRANSPARENT || endColor != Color.TRANSPARENT) {
                //先设置背景才有效--设置了渐变色 点击有透明效果
                if (startColor != Color.TRANSPARENT && endColor == Color.TRANSPARENT) {
                    int alphaComponent = ColorUtils.setAlphaComponent(startColor, clickAlpha);
                    setGradient(alphaComponent, alphaComponent, gradient, gradientOrientation);
                } else if (startColor == Color.TRANSPARENT && endColor != Color.TRANSPARENT) {
                    int alphaComponent = ColorUtils.setAlphaComponent(endColor, clickAlpha);
                    setGradient(alphaComponent, alphaComponent, gradient, gradientOrientation);
                }

            } else if (mNormalSolidColor != Color.TRANSPARENT) {
                //按下背景色（设置了渐变色）
                if (mSelectedSolidColor == Color.TRANSPARENT) {
                    int alphaComponent = ColorUtils.setAlphaComponent(mNormalSolidColor, clickAlpha);
                    setSolidColor(alphaComponent);
                } else {
                    int alphaComponent = ColorUtils.setAlphaComponent(mSelectedSolidColor, clickAlpha);
                    setSolidColor(alphaComponent);
                }
            }

            //设置边框颜色及宽度
            if (strokeColor != Color.TRANSPARENT) {
                if (clickStrokeColor == Color.TRANSPARENT) {
                    int alphaComponent = ColorUtils.setAlphaComponent(strokeColor, clickAlpha);
                    setStrokeColorAndWidth(strokeWidth, alphaComponent);
                } else {
                    int alphaComponent = ColorUtils.setAlphaComponent(clickStrokeColor, clickAlpha);
                    setStrokeColorAndWidth(strokeWidth, alphaComponent);
                }
            }

            //设置字体颜色
            if (normalTextColor != Color.TRANSPARENT) {
                if (selectedTextColor == Color.TRANSPARENT) {
                    int alphaComponent = ColorUtils.setAlphaComponent(normalTextColor, clickAlpha);
                    setNormalTextColor(alphaComponent);
                } else {
                    int alphaComponent = ColorUtils.setAlphaComponent(selectedTextColor, clickAlpha);
                    setNormalTextColor(alphaComponent);
                }
            }

            setBackgroundDrawable(gradientDrawable);

//            if (startColor != Color.TRANSPARENT || endColor != Color.TRANSPARENT) {
//                //先设置背景才有效--设置了渐变色 点击有透明效果
//                ClickUtils.applyPressedBgAlpha(this, clickAlpha);//点击才会生效，放开自动还原
//                ToastUtil.toast("点击有透明效果" + clickAlpha);
//            }
            //postInvalidate();//刷新
        } else if (event.getAction() == MotionEvent.ACTION_UP
                || event.getAction() == MotionEvent.ACTION_CANCEL) {

            if (startColor == Color.TRANSPARENT && endColor == Color.TRANSPARENT) {
                //还原填充背景色
                setSolidColor(mNormalSolidColor);
            } else {
                //还原渐变色
                setGradient(startColor, endColor, gradient, gradientOrientation);
            }
            //还原边框颜色及宽度
            setStrokeColorAndWidth(strokeWidth, strokeColor);
            //字体颜色
            setNormalTextColor(normalTextColor);

            setBackgroundDrawable(gradientDrawable);
        }
        return true;
    }
}
