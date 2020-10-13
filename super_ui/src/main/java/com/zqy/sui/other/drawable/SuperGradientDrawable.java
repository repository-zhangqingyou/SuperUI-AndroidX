package com.zqy.sui.other.drawable;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.FloatRange;

import com.blankj.utilcode.util.ColorUtils;
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

    /**
     * 获取attrs标签值初始化（反射获取，此方法无法预览，运行才有效果）
     *
     * @param view
     * @param attrs
     */

    public void initTypedArray(View view, Context context, AttributeSet attrs) {
        this.view = view;

        String styleableName = view.getClass().getSimpleName();//"SuperTextView"
        String packageName = "com.zqy.sui";

        int[] ary = StyleaUtils.getStyleableArryId(packageName, styleableName);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, ary);

//        clickEffect = typedArray.getBoolean(StyleaUtils.getStyleableId(styleableName + "_zqy_click_effect"), true);//设置是否有按下效果 默认有
//        clickAlpha = typedArray.getFloat(StyleaUtils.getStyleableId(styleableName + "_zqy_click_alpha"), 0.7f);
//
//        solidColor = typedArray.getColor(StyleaUtils.getStyleableId(styleableName + "_zqy_solid_color"), Color.TRANSPARENT);
//        strokeColor = typedArray.getColor(StyleaUtils.getStyleableId(styleableName + "_zqy_stroke_color"), Color.TRANSPARENT);
//        startColor = typedArray.getColor(StyleaUtils.getStyleableId(styleableName + "_zqy_start_color"), Color.TRANSPARENT);
//        endColor = typedArray.getColor(StyleaUtils.getStyleableId(styleableName + "_zqy_end_color"), Color.TRANSPARENT);
//        clickSolidColor = typedArray.getColor(StyleaUtils.getStyleableId(styleableName + "_zqy_click_solid_color"), Color.TRANSPARENT);
//        clickStrokeColor = typedArray.getColor(StyleaUtils.getStyleableId(styleableName + "_zqy_click_stroke_color"), Color.TRANSPARENT);

//        textColor = typedArray.getColor(StyleaUtils.getStyleableId(styleableName + "_zqy_text_color"), Color.GRAY);//默认灰色
//        clickTextColor = typedArray.getColor(StyleaUtils.getStyleableId(styleableName + "_zqy_click_text_color"), Color.TRANSPARENT);
//
//        gradient = typedArray.getInt(StyleaUtils.getStyleableId(styleableName + "_zqy_gradient"), 0);//默认线性
//        orientation = typedArray.getInt(StyleaUtils.getStyleableId(styleableName + "_zqy_orientation"), 6);//默认从左到右
//        strokeWidth = typedArray.getDimensionPixelSize(StyleaUtils.getStyleableId(styleableName + "_zqy_stroke_width"), 0);
//        radius = typedArray.getDimensionPixelSize(StyleaUtils.getStyleableId(styleableName + "_zqy_radius"), 5);
//        topLeftRadius = typedArray.getDimensionPixelSize(StyleaUtils.getStyleableId(styleableName + "_zqy_top_left_radius"), 0);
//        topRightRadius = typedArray.getDimensionPixelSize(StyleaUtils.getStyleableId(styleableName + "_zqy_top_right_radius"), 0);
//        bottomLeftRadius = typedArray.getDimensionPixelSize(StyleaUtils.getStyleableId(styleableName + "_zqy_bottom_left_radius"), 0);
//        bottomRightRadius = typedArray.getDimensionPixelSize(StyleaUtils.getStyleableId(styleableName + "_zqy_bottom_right_radius"), 0);

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

        gradient = typedArray.getInt(StyleaUtils.getStyleableFieldId(packageName, styleableName, "zqy_gradient"), 0);//默认线性
        orientation = typedArray.getInt(StyleaUtils.getStyleableFieldId(packageName, styleableName, "zqy_orientation"), 6);//默认从左到右
        strokeWidth = typedArray.getDimensionPixelSize(StyleaUtils.getStyleableFieldId(packageName, styleableName, "zqy_stroke_width"), 0);
        radius = typedArray.getDimensionPixelSize(StyleaUtils.getStyleableFieldId(packageName, styleableName, "zqy_radius"), 5);
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

        gradient = (int) attrs.get(SuperAttr.渐变模式);
        orientation = (int) attrs.get(SuperAttr.渐变方向);
        radius = (int) attrs.get(SuperAttr.四圆角);
        topLeftRadius = (int) attrs.get(SuperAttr.左上圆角);
        topRightRadius = (int) attrs.get(SuperAttr.右上圆角);
        bottomLeftRadius = (int) attrs.get(SuperAttr.左下圆角);
        bottomRightRadius = (int) attrs.get(SuperAttr.右下圆角);

        init();

    }


    private void init() {
        Orientation[] values = Orientation.values();
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
     * 设置按下透明值
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
     * @param color 颜色
     */
    public SuperGradientDrawable setSolidColor(@ColorInt int color) {
        setColor(color);
        return this;
    }


    /**
     * 设置点击填充颜色
     *
     * @param clickSolidColor 颜色
     */
    public SuperGradientDrawable setClickSolidColor(@ColorInt int clickSolidColor) {
        this.clickSolidColor = clickSolidColor;
        return this;
    }

    /**
     * 设置边框颜色及宽度
     *
     * @param strokeWidth 边框宽度
     * @param color       边框颜色
     */
    public SuperGradientDrawable setStrokeColorAndWidth(int strokeWidth, int color) {
        setStroke(strokeWidth, color);
        return this;
    }

    /**
     * 设置点击边框颜色
     *
     * @param clickStrokeColor 点击时边框颜色
     */
    public SuperGradientDrawable setClickStrokeColor(@ColorInt int clickStrokeColor) {
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
    public SuperGradientDrawable setGradient(@ColorInt int starColor, @ColorInt int endColor, int gradient, Orientation orientation) {
        setColors(new int[]{starColor, endColor});//设置渐变颜色
        setGradientType(gradient);//设置线性渐变，除此之外还有：GradientDrawable.SWEEP_GRADIENT（扫描式渐变），GradientDrawable.RADIAL_GRADIENT（圆形渐变）
        setOrientation(orientation);//设置渐变方向
        return this;
    }


    /**
     * 设置textView颜色
     *
     * @param normalTextColor 正常状态颜色
     */
    public SuperGradientDrawable setNormalTextColor(@ColorInt int normalTextColor) {
        if (view instanceof TextView) {
            TextView textView = (TextView) view;
            textView.setTextColor(normalTextColor);
        } else if (view instanceof Button) {
            Button button = (Button) view;
            button.setTextColor(normalTextColor);
        }
        return this;
    }


    /**
     * 设置textView选中状态颜色
     *
     * @param clickTextColor 按下状态颜色
     */
    public SuperGradientDrawable setClickTextColor(@ColorInt int clickTextColor) {
        this.clickTextColor = clickTextColor;
        return this;
    }

    /**
     * 设置完成之后必须调用 buid() ，否则不生效
     */
    public SuperGradientDrawable buid() {
        view.setBackground(this);
        return this;
    }

    /**
     * 按下 抬起
     *
     * @param pressed
     */
    public void setPressed(boolean pressed) {
        if (clickEffect) {
            if (pressed) {
                if (startColor != Color.TRANSPARENT || endColor != Color.TRANSPARENT) {

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

                buid();

//            if (startColor != Color.TRANSPARENT || endColor != Color.TRANSPARENT) {
//                //先设置背景才有效--设置了渐变色 点击有透明效果
//                ClickUtils.applyPressedBgAlpha(this, clickAlpha);//点击才会生效，放开自动还原
//                ToastUtil.toast("点击有透明效果" + clickAlpha);
//            }
                // postInvalidate();//刷新
            } else {
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

                buid();
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
