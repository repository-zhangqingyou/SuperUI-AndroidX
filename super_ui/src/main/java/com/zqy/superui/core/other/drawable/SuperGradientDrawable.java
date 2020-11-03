package com.zqy.superui.core.other.drawable;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.FloatRange;

import com.blankj.utilcode.util.ColorUtils;
import com.blankj.utilcode.util.SizeUtils;
import com.zqy.superui.core.enums.Gradient;
import com.zqy.superui.core.module.SuperDrawableData;

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
public class SuperGradientDrawable extends GradientDrawable {
    private boolean clickEffect = true;//设置是否有按下效果 默认有
    private float clickAlpha = 0.7f;//按下时 背景颜色和字体颜色 透明度
    private int normalSolidColor = Color.TRANSPARENT;//填充颜色
    private int clickSolidColor = Color.TRANSPARENT;//按下时的填充颜色
    private int normalStrokeColor = Color.TRANSPARENT;//边框颜色
    private int clickStrokeColor = Color.TRANSPARENT;//按现时 边框颜色
    private int normalTextColor = Color.GRAY;//正常字体颜色
    private int clickTextColor = Color.TRANSPARENT;//按下时 字体颜色
    private int strokeWidth = 0;//边框宽度
    private int startColor = Color.TRANSPARENT;//渐变开始颜色
    private int endColor = Color.TRANSPARENT;//渐变结束颜色
    private int gradient = Gradient.LINEAR_GRADIENT.ordinal();//渐变模式   默认线性
    private int gradientOrientation = Orientation.LEFT_RIGHT.ordinal();//渐变方向 默认从左到右
    private int radius = SizeUtils.dp2px(5),
            topLeftRadius = 0,
            topRightRadius = 0,
            bottomLeftRadius = 0,
            bottomRightRadius = 0;//圆角

//    /**
//     * 此方法耗时
//     * 获取attrs标签值初始化（反射获取，此方法无法预览，运行才有效果）
//     *
//     * @param view
//     * @param attrs
//     */
//
//    public void initTypedArray(View view, Context context, AttributeSet attrs) {
//
//
//        String styleableName = view.getClass().getSimpleName();//"SuperTextView"
//        String packageName = "com.zqy.superui";
//
//        int[] ary = StyleaUtils.getStyleableArryId(packageName, styleableName);
//
//        TypedArray typedArray = context.obtainStyledAttributes(attrs, ary);
//
//
//        clickEffect = typedArray.getBoolean(StyleaUtils.getStyleableFieldId(packageName, styleableName, "zqy_click_effect"), true);//设置是否有按下效果 默认有
//        clickAlpha = typedArray.getFloat(StyleaUtils.getStyleableFieldId(packageName, styleableName, "zqy_click_alpha"), 0.7f);
//
//        solidColor = typedArray.getColor(StyleaUtils.getStyleableFieldId(packageName, styleableName, "zqy_solid_color"), Color.TRANSPARENT);
//        strokeColor = typedArray.getColor(StyleaUtils.getStyleableFieldId(packageName, styleableName, "zqy_stroke_color"), Color.TRANSPARENT);
//        startColor = typedArray.getColor(StyleaUtils.getStyleableFieldId(packageName, styleableName, "zqy_start_color"), Color.TRANSPARENT);
//        endColor = typedArray.getColor(StyleaUtils.getStyleableFieldId(packageName, styleableName, "zqy_end_color"), Color.TRANSPARENT);
//        clickSolidColor = typedArray.getColor(StyleaUtils.getStyleableFieldId(packageName, styleableName, "zqy_click_solid_color"), Color.TRANSPARENT);
//        clickStrokeColor = typedArray.getColor(StyleaUtils.getStyleableFieldId(packageName, styleableName, "zqy_click_stroke_color"), Color.TRANSPARENT);
//
//        textColor = typedArray.getColor(StyleaUtils.getStyleableFieldId(packageName, styleableName, "zqy_text_color"), Color.GRAY);//默认灰色
//        clickTextColor = typedArray.getColor(StyleaUtils.getStyleableFieldId(packageName, styleableName, "zqy_click_text_color"), Color.TRANSPARENT);
//
//        gradient = typedArray.getInt(StyleaUtils.getStyleableFieldId(packageName, styleableName, "zqy_gradient"), Gradient.LINEAR_GRADIENT.ordinal());//默认线性
//        gradientOrientation = typedArray.getInt(StyleaUtils.getStyleableFieldId(packageName, styleableName, "zqy_orientation"), Orientation.LEFT_RIGHT.ordinal());//默认从左到右
//
//        strokeWidth = typedArray.getDimensionPixelSize(StyleaUtils.getStyleableFieldId(packageName, styleableName, "zqy_stroke_width"), 0);
//        radius = typedArray.getDimensionPixelSize(StyleaUtils.getStyleableFieldId(packageName, styleableName, "zqy_radius"), SizeUtils.dp2px(5));
//        topLeftRadius = typedArray.getDimensionPixelSize(StyleaUtils.getStyleableFieldId(packageName, styleableName, "zqy_top_left_radius"), 0);
//        topRightRadius = typedArray.getDimensionPixelSize(StyleaUtils.getStyleableFieldId(packageName, styleableName, "zqy_top_right_radius"), 0);
//        bottomLeftRadius = typedArray.getDimensionPixelSize(StyleaUtils.getStyleableFieldId(packageName, styleableName, "zqy_bottom_left_radius"), 0);
//        bottomRightRadius = typedArray.getDimensionPixelSize(StyleaUtils.getStyleableFieldId(packageName, styleableName, "zqy_bottom_right_radius"), 0);
//
//        typedArray.recycle();
//
//        init();
//    }


    /**
     * 获取attrs标签值初始化
     */
    public void init(SuperDrawableData superDrawableData) {
        clickEffect = superDrawableData.isClickEffect();//设置是否有按下效果 默认有
        clickAlpha = superDrawableData.getClickAlpha();

        normalSolidColor = superDrawableData.getNormalSolidColor();
        clickSolidColor = superDrawableData.getClickSolidColor();

        normalStrokeColor = superDrawableData.getStrokeColor();
        clickStrokeColor = superDrawableData.getClickStrokeColor();
        strokeWidth = superDrawableData.getStrokeWidth();

        normalTextColor = superDrawableData.getNormalTextColor();
        clickTextColor = superDrawableData.getClickTextColor();

        startColor = superDrawableData.getStartColor();
        endColor = superDrawableData.getEndColor();
        gradient = superDrawableData.getGradient();
        gradientOrientation = superDrawableData.getGradientOrientation();

        radius = superDrawableData.getRadius();
        topLeftRadius = superDrawableData.getTopLeftRadius();
        topRightRadius = superDrawableData.getTopRightRadius();
        bottomLeftRadius = superDrawableData.getBottomLeftRadius();
        bottomRightRadius = superDrawableData.getBottomRightRadius();

        /**
         * 设置透明度会初始化 填充颜色 边框颜色 字体颜色
         */
        if (startColor != Color.TRANSPARENT || endColor != Color.TRANSPARENT) {
            for (Orientation value : Orientation.values()) {
                if (value.ordinal() == gradientOrientation) {
                    setOrientation(value);//设置渐变方向
                    break;
                }
            }
            setGradientType(gradient);//设置线性渐变，除此之外还有：GradientDrawable.SWEEP_GRADIENT（扫描式渐变），GradientDrawable.RADIAL_GRADIENT（圆形渐变）
            setColors(new int[]{startColor, endColor});//设置渐变颜色
        } else {
            //设置填充
            setColor(normalSolidColor);
        }
        //设置圆角
        if (topLeftRadius > 0 || topRightRadius > 0 || bottomLeftRadius > 0 || bottomRightRadius > 0) {
            setRadius(topLeftRadius, topRightRadius, bottomLeftRadius, bottomRightRadius);
        } else {
            setRadius(radius, radius, radius, radius);
        }
        //设置描边
        setStroke(strokeWidth, normalStrokeColor);

    }


    /**
     * 四角圆形度数(单位px)
     */
    public SuperGradientDrawable setRadius(int radiusTopLeft, int radiusTopRight, int radiusBottomLeft, int radiusBottomRight) {
        setCornerRadii(new float[]{radiusTopLeft,
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
    public SuperGradientDrawable setClickEffect(boolean clickEffect) {
        this.clickEffect = clickEffect;
        return this;
    }


    /**
     * 设置按下透明值(包括按下时的 《字体，边框，填充》 颜色)
     *
     * @param clickAlpha
     */
    public SuperGradientDrawable setClickAlpha(@FloatRange(from = 0, to = 1) float clickAlpha) {
        //(设置透明值时需重设各属性方生效)
        this.clickAlpha = clickAlpha;
        return this;
    }

    /**
     * 设置填充颜色
     *
     * @param normalSolidColor 正常（抬起）填充颜色
     * @param clickSolidColor  点击（按下）填充颜色
     */
    public SuperGradientDrawable setSolidColorState(@ColorInt int normalSolidColor, @ColorInt int clickSolidColor) {
        this.normalSolidColor = normalSolidColor;
        this.clickSolidColor = clickSolidColor;
        return this;
    }

    /**
     * 设置边框颜色及宽度
     *
     * @param strokeWidth       边框宽度
     * @param normalStrokeColor 正常（抬起）边框颜色
     * @param clickStrokeColor  点击（按下）边框颜色
     */
    public SuperGradientDrawable setStrokeColorState(int strokeWidth, @ColorInt int normalStrokeColor, @ColorInt int clickStrokeColor) {
        this.strokeWidth = strokeWidth;//边框宽度
        this.normalStrokeColor = normalStrokeColor;//边框颜色
        this.clickStrokeColor = clickStrokeColor;
        return this;
    }

    /**
     * 设置渐变颜色
     *
     * @param starColor
     * @param endColor
     * @param gradient    * LINEAR_GRADIENT:线性梯度
     *                    * RADIAL_GRADIENT:圆形渐变
     *                    * SWEEP_GRADIENT:扫描式渐变
     *                    * RING:环
     * @param orientation 渐变方向
     * @return
     */
    public SuperGradientDrawable setGradient(@ColorInt int starColor, @ColorInt int endColor, Gradient gradient, Orientation orientation) {
        this.startColor = starColor;//渐变开始颜色
        this.endColor = endColor;//渐变结束颜色
        setGradientType(gradient.ordinal());//设置线性渐变，除此之外还有：GradientDrawable.SWEEP_GRADIENT（扫描式渐变），GradientDrawable.RADIAL_GRADIENT（圆形渐变）
        setOrientation(orientation);//设置渐变方向
        return this;
    }


    /**
     * 设置字体颜色
     *
     * @param normalTextColor 正常字体颜色
     * @param clickTextColor  按下字体颜色
     */
    public SuperGradientDrawable setTextColorState(TextView textView, @ColorInt int normalTextColor, @ColorInt int clickTextColor) {
        int alphaComponent = ColorUtils.setAlphaComponent(clickTextColor, clickAlpha);
        int pressed = android.R.attr.state_pressed;
        int[][] states = new int[][]
                {
                        new int[]{-pressed},//未点击
                        new int[]{pressed},//点击
                        new int[]{}//默认
                };

        int[] colors = new int[]
                {
                        normalTextColor,
                        alphaComponent,
                        normalTextColor,
                };
        ColorStateList colorStateList = new ColorStateList(states, colors);

        textView.setTextColor(colorStateList);
        return this;
    }

    /**
     * 设置字体颜色
     *
     * @param normalTextColor 正常字体颜色
     * @param clickTextColor  按下字体颜色
     */
    public SuperGradientDrawable setTextColorState(Button button, @ColorInt int normalTextColor, @ColorInt int clickTextColor) {
        int alphaComponent = ColorUtils.setAlphaComponent(clickTextColor, clickAlpha);
        int pressed = android.R.attr.state_pressed;
        int[][] states = new int[][]
                {
                        new int[]{-pressed},//未点击
                        new int[]{pressed},//点击
                        new int[]{}//默认
                };

        int[] colors = new int[]
                {
                        normalTextColor,
                        alphaComponent,
                        normalTextColor,
                };
        ColorStateList colorStateList = new ColorStateList(states, colors);

        button.setTextColor(colorStateList);
        return this;
    }


    /**
     * 按下 抬起
     *
     * @param pressed true：按下时，false:抬起时(正常时)
     */
    public void setPressed(boolean pressed) {
       // Log.d("SuperGradientDrawable", "clickEffect:" + clickEffect);
        if (clickEffect) {
            if (pressed) {
                /**
                 * 按下时
                 */
                if (startColor != Color.TRANSPARENT || endColor != Color.TRANSPARENT) {
                    if (startColor != Color.TRANSPARENT && endColor != Color.TRANSPARENT) {
                        int startAlpha = ColorUtils.setAlphaComponent(startColor, clickAlpha);
                        int endAlpha = ColorUtils.setAlphaComponent(endColor, clickAlpha);
                        setColors(new int[]{startAlpha, endAlpha});//设置渐变颜色
                    } else if (startColor != Color.TRANSPARENT && endColor == Color.TRANSPARENT) {
                        int alphaComponent = ColorUtils.setAlphaComponent(startColor, clickAlpha);
                        setColors(new int[]{alphaComponent, endColor});//设置渐变颜色
                    } else if (startColor == Color.TRANSPARENT && endColor != Color.TRANSPARENT) {
                        int alphaComponent = ColorUtils.setAlphaComponent(endColor, clickAlpha);
                        setColors(new int[]{startColor, alphaComponent});//设置渐变颜色
                    }

                } else if (normalSolidColor != Color.TRANSPARENT) {
                    //按下背景色（设置了渐变色）
                    if (clickSolidColor == Color.TRANSPARENT) {
                        int alphaComponent = ColorUtils.setAlphaComponent(normalSolidColor, clickAlpha);
                        setColor(alphaComponent);
                    } else {
                        int alphaComponent = ColorUtils.setAlphaComponent(clickSolidColor, clickAlpha);
                        setColor(alphaComponent);
                    }
                }


                //设置边框颜色及宽度
                if (normalStrokeColor != Color.TRANSPARENT) {
                    if (clickStrokeColor == Color.TRANSPARENT) {
                        int alphaComponent = ColorUtils.setAlphaComponent(normalStrokeColor, clickAlpha);
                        setStroke(strokeWidth, alphaComponent);
                    } else {
                        int alphaComponent = ColorUtils.setAlphaComponent(clickStrokeColor, clickAlpha);
                        setStroke(strokeWidth, alphaComponent);
                    }
                }


            } else {
                /**
                 * 抬起时
                 */
                if (startColor == Color.TRANSPARENT && endColor == Color.TRANSPARENT) {
                    //还原填充背景色
                    setColor(normalSolidColor);
                } else {
                    //还原渐变色
                    setColors(new int[]{startColor, endColor});//设置渐变颜色
                }
                //还原边框颜色及宽度
                setStroke(strokeWidth, normalStrokeColor);
            }
        }
    }

    /**
     * 在view里调用 （有问题）
     *
     * @param event
     */
    public void onTouchEvent(MotionEvent event) {
        if (clickEffect) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                setPressed(true);
            } else if (event.getAction() == MotionEvent.ACTION_UP
                    || event.getAction() == MotionEvent.ACTION_CANCEL) {
                setPressed(false);
            }
        }
    }
}
