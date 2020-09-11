package com.zqy.baseui.ui.view.textview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.ColorInt;
import androidx.annotation.FloatRange;
import androidx.appcompat.widget.AppCompatTextView;

import com.blankj.utilcode.util.ColorUtils;
import com.zqy.baseui.R;

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
public class SuperTextView extends AppCompatTextView {
    private GradientDrawable gradientDrawable;
    private GradientDrawable.Orientation gradientOrientation = GradientDrawable.Orientation.LEFT_RIGHT;//渐变方向
    private boolean clickEffect = true;//设置是否有按下效果 默认有
    private float clickAlpha;//按下时 背景颜色和字体颜色 透明度
    private int solidColor;//填充颜色
    private int clickSolidColor;//按下时的填充颜色
    private int clickStrokeColor;//按现时 边框颜色
    private int strokeColor;//边框颜色
    private int textColor;//正常字体颜色
    private int clickTextColor;//按现时 字体颜色
    private int strokeWidth;//边框宽度
    private int startColor;//渐变开始颜色
    private int endColor;//渐变结束颜色
    private int gradient;//渐变模式
    private int orientation;//渐变方向
    private int radius,
            topLeftRadius,
            topRightRadius,
            bottomLeftRadius,
            bottomRightRadius;//圆角

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
        gradientDrawable = new GradientDrawable();


        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SuperTextView);
        clickAlpha = typedArray.getFloat(R.styleable.SuperTextView_zqy_click_alpha, 0.7f);

        solidColor = typedArray.getColor(R.styleable.SuperTextView_zqy_solid_color, Color.TRANSPARENT);
        strokeColor = typedArray.getColor(R.styleable.SuperTextView_zqy_stroke_color, Color.TRANSPARENT);
        startColor = typedArray.getColor(R.styleable.SuperTextView_zqy_start_color, Color.TRANSPARENT);
        endColor = typedArray.getColor(R.styleable.SuperTextView_zqy_end_color, Color.TRANSPARENT);
        clickSolidColor = typedArray.getColor(R.styleable.SuperTextView_zqy_click_solid_color, Color.TRANSPARENT);
        clickStrokeColor = typedArray.getColor(R.styleable.SuperTextView_zqy_click_stroke_color, Color.TRANSPARENT);

        textColor = typedArray.getColor(R.styleable.SuperTextView_zqy_text_color, Color.GRAY);//默认灰色
        clickTextColor = typedArray.getColor(R.styleable.SuperTextView_zqy_click_text_color, Color.TRANSPARENT);

        gradient = typedArray.getInt(R.styleable.SuperTextView_zqy_gradient, 0);//默认线性
        orientation = typedArray.getInt(R.styleable.SuperTextView_zqy_orientation, 6);//默认从左到右
        strokeWidth = typedArray.getDimensionPixelSize(R.styleable.SuperTextView_zqy_stroke_width, 0);
        radius = typedArray.getDimensionPixelSize(R.styleable.SuperTextView_zqy_radius, 5);
        topLeftRadius = typedArray.getDimensionPixelSize(R.styleable.SuperTextView_zqy_top_left_radius, 0);
        topRightRadius = typedArray.getDimensionPixelSize(R.styleable.SuperTextView_zqy_top_right_radius, 0);
        bottomLeftRadius = typedArray.getDimensionPixelSize(R.styleable.SuperTextView_zqy_bottom_left_radius, 0);
        bottomRightRadius = typedArray.getDimensionPixelSize(R.styleable.SuperTextView_zqy_bottom_right_radius, 0);

        typedArray.recycle();

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
            setSolidColor(solidColor);
        }

        if (radius > 0) {
            setRadius(radius, radius, radius, radius);
        } else {
            setRadius(topLeftRadius, topRightRadius, bottomLeftRadius, bottomRightRadius);
        }


        setStrokeColorAndWidth(strokeWidth, strokeColor)
                .setNormalTextColor(textColor)
                .buid();

    }


    /**
     * 四角圆形度数
     */
    public SuperTextView setRadius(int radiusTopLeft, int radiusTopRight, int radiusBottomLeft, int radiusBottomRight) {
        gradientDrawable.setCornerRadii(new float[]{radiusTopLeft,
                radiusTopLeft, radiusTopRight, radiusTopRight,
                radiusBottomRight, radiusBottomRight, radiusBottomLeft,
                radiusBottomLeft});
        return this;
    }


    /**
     * 设置是否有按下效果
     *
     * @param clickEffect
     */
    public SuperTextView setClickEffect(boolean clickEffect) {
        this.clickEffect = clickEffect;
        return this;
    }


    /**
     * 设置按下透明值
     *
     * @param clickAlpha
     */
    public SuperTextView setClickAlpha(@FloatRange(from = 0, to = 1) float clickAlpha) {
        //(设置透明值时需重设各属性方生效)
        this.clickAlpha = clickAlpha;

        return this;
    }

    /**
     * 设置填充颜色
     *
     * @param color 颜色
     */
    public SuperTextView setSolidColor(@ColorInt int color) {
        gradientDrawable.setColor(color);
        return this;
    }


    /**
     * 设置点击填充颜色
     *
     * @param clickSolidColor 颜色
     */
    public SuperTextView setClickSolidColor(@ColorInt int clickSolidColor) {
        this.clickSolidColor = clickSolidColor;
        return this;
    }

    /**
     * 设置边框颜色及宽度
     *
     * @param strokeWidth 边框宽度
     * @param color       边框颜色
     */
    public SuperTextView setStrokeColorAndWidth(int strokeWidth, int color) {
        gradientDrawable.setStroke(strokeWidth, color);
        return this;
    }

    /**
     * 设置点击边框颜色
     *
     * @param clickStrokeColor 点击时边框颜色
     */
    public SuperTextView setClickStrokeColor(@ColorInt int clickStrokeColor) {
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
    public SuperTextView setGradient(@ColorInt int starColor, @ColorInt int endColor, int gradient, GradientDrawable.Orientation orientation) {
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
    public SuperTextView setNormalTextColor(@ColorInt int normalTextColor) {
        setTextColor(normalTextColor);
        return this;
    }


    /**
     * 设置textView选中状态颜色
     *
     * @param clickTextColor 按下状态颜色
     */
    public SuperTextView setClickTextColor(@ColorInt int clickTextColor) {
        this.clickTextColor = clickTextColor;
        return this;


    }

    /**
     * 设置完成之后必须调用 buid() ，否则不生效
     */
    public SuperTextView buid() {
        setBackground(gradientDrawable);
        return this;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (clickEffect) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                if (startColor != Color.TRANSPARENT || endColor != Color.TRANSPARENT) {
                    //先设置背景才有效--设置了渐变色 点击有透明效果
                    if (startColor != Color.TRANSPARENT && endColor != Color.TRANSPARENT) {
                        int startAlpha = ColorUtils.setAlphaComponent(startColor, clickAlpha);
                        int endAlpha = ColorUtils.setAlphaComponent(endColor, clickAlpha);
                        setGradient(startAlpha, endAlpha, gradient, gradientOrientation);
                    } else if (startColor != Color.TRANSPARENT && endColor == Color.TRANSPARENT) {
                        int alphaComponent = ColorUtils.setAlphaComponent(startColor, clickAlpha);
                        setGradient(alphaComponent, endColor, gradient, gradientOrientation);
                    } else if (startColor == Color.TRANSPARENT && endColor != Color.TRANSPARENT) {
                        int alphaComponent = ColorUtils.setAlphaComponent(endColor, clickAlpha);
                        setGradient(startColor, alphaComponent, gradient, gradientOrientation);
                    }

                } else if (solidColor != Color.TRANSPARENT) {
                    //按下背景色（设置了渐变色）
                    if (clickSolidColor == Color.TRANSPARENT) {
                        int alphaComponent = ColorUtils.setAlphaComponent(solidColor, clickAlpha);
                        setSolidColor(alphaComponent);
                    } else {
                        int alphaComponent = ColorUtils.setAlphaComponent(clickSolidColor, clickAlpha);
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
                if (textColor != Color.TRANSPARENT) {
                    if (clickTextColor == Color.TRANSPARENT) {
                        int alphaComponent = ColorUtils.setAlphaComponent(textColor, clickAlpha);
                        setNormalTextColor(alphaComponent);
                    } else {
                        int alphaComponent = ColorUtils.setAlphaComponent(clickTextColor, clickAlpha);
                        setNormalTextColor(alphaComponent);
                    }
                }

                setBackground(gradientDrawable);

//            if (startColor != Color.TRANSPARENT || endColor != Color.TRANSPARENT) {
//                //先设置背景才有效--设置了渐变色 点击有透明效果
//                ClickUtils.applyPressedBgAlpha(this, clickAlpha);//点击才会生效，放开自动还原
//                ToastUtil.toast("点击有透明效果" + clickAlpha);
//            }
                // postInvalidate();//刷新
            } else if (event.getAction() == MotionEvent.ACTION_UP
                    || event.getAction() == MotionEvent.ACTION_CANCEL) {

                if (startColor == Color.TRANSPARENT && endColor == Color.TRANSPARENT) {
                    //还原填充背景色
                    setSolidColor(solidColor);
                } else {
                    //还原渐变色
                    setGradient(startColor, endColor, gradient, gradientOrientation);
                }
                //还原边框颜色及宽度
                setStrokeColorAndWidth(strokeWidth, strokeColor);
                //字体颜色
                setNormalTextColor(textColor);

                setBackground(gradientDrawable);
            }
        }

        return super.onTouchEvent(event);
    }
}
