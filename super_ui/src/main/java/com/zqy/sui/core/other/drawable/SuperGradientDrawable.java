package com.zqy.sui.core.other.drawable;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.FloatRange;

import com.blankj.utilcode.util.ColorUtils;
import com.blankj.utilcode.util.SizeUtils;
import com.zqy.sutils.StyleaUtils;

import java.util.Map;

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
    private View view;//需要设置Drawable的View
    private GradientDrawable.Orientation gradientOrientation = Orientation.LEFT_RIGHT;//渐变方向
    private boolean clickEffect = true;//设置是否有按下效果 默认有
    private float clickAlpha = 0.7f;//按下时 背景颜色和字体颜色 透明度
    private int solidColor = Color.TRANSPARENT;//填充颜色
    private int clickSolidColor = Color.TRANSPARENT;//按下时的填充颜色
    private int clickStrokeColor = Color.TRANSPARENT;//按现时 边框颜色
    private int strokeColor = Color.TRANSPARENT;//边框颜色
    private int textColor = Color.GRAY;//正常字体颜色
    private int clickTextColor = Color.TRANSPARENT;//按下时 字体颜色
    private int strokeWidth = 0;//边框宽度
    private int startColor = Color.TRANSPARENT;//渐变开始颜色
    private int endColor = Color.TRANSPARENT;//渐变结束颜色
    private Gradient gradient = Gradient.LINEAR_GRADIENT;//渐变模式
    private int radius = SizeUtils.dp2px(5),
            topLeftRadius = 0,
            topRightRadius = 0,
            bottomLeftRadius = 0,
            bottomRightRadius = 0;//圆角

    /**
     * 此方法耗时
     * 获取attrs标签值初始化（反射获取，此方法无法预览，运行才有效果）
     *
     * @param view
     * @param attrs
     */

    public void initTypedArray(View view, Context context, AttributeSet attrs) {
        this.view = view;

        String styleableName = view.getClass().getSimpleName();//"SuperTextView"
        String packageName = "com.zqy.superui";

        int[] ary = StyleaUtils.getStyleableArryId(packageName, styleableName);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, ary);


        clickEffect = typedArray.getBoolean(StyleaUtils.getStyleableFieldId(packageName, styleableName, "zqy_click_effect"), true);//设置是否有按下效果 默认有
        clickAlpha = typedArray.getFloat(StyleaUtils.getStyleableFieldId(packageName, styleableName, "zqy_click_alpha"), 0.7f);

        solidColor = typedArray.getColor(StyleaUtils.getStyleableFieldId(packageName, styleableName, "zqy_solid_color"), Color.TRANSPARENT);
        strokeColor = typedArray.getColor(StyleaUtils.getStyleableFieldId(packageName, styleableName, "zqy_stroke_color"), Color.TRANSPARENT);
        startColor = typedArray.getColor(StyleaUtils.getStyleableFieldId(packageName, styleableName, "zqy_start_color"), Color.TRANSPARENT);
        endColor = typedArray.getColor(StyleaUtils.getStyleableFieldId(packageName, styleableName, "zqy_end_color"), Color.TRANSPARENT);
        clickSolidColor = typedArray.getColor(StyleaUtils.getStyleableFieldId(packageName, styleableName, "zqy_click_solid_color"), Color.TRANSPARENT);
        clickStrokeColor = typedArray.getColor(StyleaUtils.getStyleableFieldId(packageName, styleableName, "zqy_click_stroke_color"), Color.TRANSPARENT);

        textColor = typedArray.getColor(StyleaUtils.getStyleableFieldId(packageName, styleableName, "zqy_text_color"), Color.GRAY);//默认灰色
        clickTextColor = typedArray.getColor(StyleaUtils.getStyleableFieldId(packageName, styleableName, "zqy_click_text_color"), Color.TRANSPARENT);

        int zqy_gradient = typedArray.getInt(StyleaUtils.getStyleableFieldId(packageName, styleableName, "zqy_gradient"), 0);//默认线性
        Gradient[] valuesGradient = Gradient.values();
        for (Gradient value : valuesGradient) {
            if (value.ordinal() == zqy_gradient) {
                gradient = value;
            }
        }

        int orientation = typedArray.getInt(StyleaUtils.getStyleableFieldId(packageName, styleableName, "zqy_orientation"), 6);//默认从左到右
        Orientation[] valuesOrientation = Orientation.values();
        for (GradientDrawable.Orientation value : valuesOrientation) {
            if (value.ordinal() == orientation) {
                gradientOrientation = value;
            }
        }

        strokeWidth = typedArray.getDimensionPixelSize(StyleaUtils.getStyleableFieldId(packageName, styleableName, "zqy_stroke_width"), 0);
        radius = typedArray.getDimensionPixelSize(StyleaUtils.getStyleableFieldId(packageName, styleableName, "zqy_radius"), SizeUtils.dp2px(5));
        topLeftRadius = typedArray.getDimensionPixelSize(StyleaUtils.getStyleableFieldId(packageName, styleableName, "zqy_top_left_radius"), 0);
        topRightRadius = typedArray.getDimensionPixelSize(StyleaUtils.getStyleableFieldId(packageName, styleableName, "zqy_top_right_radius"), 0);
        bottomLeftRadius = typedArray.getDimensionPixelSize(StyleaUtils.getStyleableFieldId(packageName, styleableName, "zqy_bottom_left_radius"), 0);
        bottomRightRadius = typedArray.getDimensionPixelSize(StyleaUtils.getStyleableFieldId(packageName, styleableName, "zqy_bottom_right_radius"), 0);

        typedArray.recycle();

        init();
    }


    /**
     * 获取attrs标签值初始化
     *
     * @param view
     * @param attrs
     */
    public void initTypedArray(View view, Map<Object, Object> attrs) {
        this.view = view;
        clickEffect = (boolean) attrs.get(SuperAttr.是否有按下效果);//设置是否有按下效果 默认有
        clickAlpha = (float) attrs.get(SuperAttr.按下时透明度);
        solidColor = (int) attrs.get(SuperAttr.填充颜色);
        strokeColor = (int) attrs.get(SuperAttr.边框颜色);
        strokeWidth = (int) attrs.get(SuperAttr.边框宽度);

        startColor = (int) attrs.get(SuperAttr.渐变开始颜色);
        endColor = (int) attrs.get(SuperAttr.渐变结束颜色);
        clickSolidColor = (int) attrs.get(SuperAttr.按下时填充颜色);
        clickStrokeColor = (int) attrs.get(SuperAttr.按下时边框颜色);

        textColor = (int) attrs.get(SuperAttr.字体颜色);
        clickTextColor = (int) attrs.get(SuperAttr.按下时字体颜色);

        int zqy_gradient = (int) attrs.get(SuperAttr.渐变模式);
        Gradient[] valuesGradient = Gradient.values();
        for (Gradient value : valuesGradient) {
            if (value.ordinal() == zqy_gradient) {
                gradient = value;
            }
        }
        int orientation = (int) attrs.get(SuperAttr.渐变方向);
        Orientation[] valuesOrientation = Orientation.values();
        for (GradientDrawable.Orientation value : valuesOrientation) {
            if (value.ordinal() == orientation) {
                gradientOrientation = value;
            }
        }

        radius = (int) attrs.get(SuperAttr.四圆角);
        topLeftRadius = (int) attrs.get(SuperAttr.左上圆角);
        topRightRadius = (int) attrs.get(SuperAttr.右上圆角);
        bottomLeftRadius = (int) attrs.get(SuperAttr.左下圆角);
        bottomRightRadius = (int) attrs.get(SuperAttr.右下圆角);

        init();

    }


    private void init() {
        /**
         * 设置透明度会初始化 填充颜色 边框颜色 字体颜色
         */
        if (startColor != Color.TRANSPARENT || endColor != Color.TRANSPARENT) {
            //设置渐变
            setGradient(startColor, endColor, gradient, gradientOrientation);
            setColors(new int[]{startColor, endColor});//设置渐变颜色
        } else {
            //设置填充
            setColor(solidColor);
        }
        //设置圆角
        if (topLeftRadius > 0 || topRightRadius > 0 || bottomLeftRadius > 0 || bottomRightRadius > 0) {
            setRadius(topLeftRadius, topRightRadius, bottomLeftRadius, bottomRightRadius);
        } else {
            setRadius(radius, radius, radius, radius);
        }
        //设置描边
        setStroke(strokeWidth, strokeColor);

        //设置字体颜色及按下颜色
        if (view instanceof TextView) {
            TextView textView = (TextView) view;
            if (clickTextColor == Color.TRANSPARENT)
                setTextColorState(textView, textColor, textColor);
            else
                setTextColorState(textView, textColor, clickTextColor);
        } else if (view instanceof Button) {
            Button button = (Button) view;
            if (clickTextColor == Color.TRANSPARENT)
                setTextColorState(button, textColor, textColor);
            else
                setTextColorState(button, textColor, clickTextColor);
        }

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
        this.solidColor = normalSolidColor;
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
    public SuperGradientDrawable setStrokeColorState(int strokeWidth, int normalStrokeColor, @ColorInt int clickStrokeColor) {
        this.strokeWidth = strokeWidth;//边框宽度
        this.strokeColor = normalStrokeColor;//边框颜色
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
    public SuperGradientDrawable setTextColorState(TextView textView, int normalTextColor, @ColorInt int clickTextColor) {
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
    public SuperGradientDrawable setTextColorState(Button button, int normalTextColor, @ColorInt int clickTextColor) {
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
        Log.d("SuperGradientDrawable", "clickEffect:" + clickEffect);
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

                } else if (solidColor != Color.TRANSPARENT) {
                    //按下背景色（设置了渐变色）
                    if (clickSolidColor == Color.TRANSPARENT) {
                        int alphaComponent = ColorUtils.setAlphaComponent(solidColor, clickAlpha);
                        setColor(alphaComponent);
                    } else {
                        int alphaComponent = ColorUtils.setAlphaComponent(clickSolidColor, clickAlpha);
                        setColor(alphaComponent);
                    }
                }


                //设置边框颜色及宽度
                if (strokeColor != Color.TRANSPARENT) {
                    if (clickStrokeColor == Color.TRANSPARENT) {
                        int alphaComponent = ColorUtils.setAlphaComponent(strokeColor, clickAlpha);
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
                    setColor(solidColor);
                } else {
                    //还原渐变色
                    setColors(new int[]{startColor, endColor});//设置渐变颜色
                }
                //还原边框颜色及宽度
                setStroke(strokeWidth, strokeColor);
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
