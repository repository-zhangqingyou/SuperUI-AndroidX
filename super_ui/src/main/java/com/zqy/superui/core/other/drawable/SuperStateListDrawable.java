package com.zqy.superui.core.other.drawable;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.FloatRange;

import com.blankj.utilcode.util.ColorUtils;
import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.SizeUtils;
import com.zqy.superui.core.enums.Gradient;


/**
 * 万能样式 SuperDrawable
 * <p>
 * 1.支持圆角 四个角可以单独设置
 * 2.支持描边大小、描边颜色
 * 3.支持点击颜色变化设置
 * <p>
 * Author: zhangqingyou
 * Date: 2020/4/28 9:21
 * Des:
 */
public class SuperStateListDrawable extends GradientDrawable {
    private float clickAlpha = 0.7f;// //设置按下背景色的透明值
    private boolean clickEffect = true;//设置是否有按下效果 默认有

    private int normalSolidColor;//正常填充颜色
    private int clickSolidColor;//点击填充颜色


    private int normalStrokeColor;//正常边框
    private int clickStrokeColor;//点击边框颜色
    private int strokeWidth;//边框宽度

    private int[] colors;//设置渐变颜色
    private Gradient gradient = Gradient.LINEAR_GRADIENT;// 渐变模式 GradientDrawable.LINEAR_GRADIENT（线性渐变） ， GradientDrawable.SWEEP_GRADIENT（扫描式渐变），GradientDrawable.RADIAL_GRADIENT（圆形渐变）
    private Orientation orientation = Orientation.LEFT_RIGHT;//设置渐变方向
    private int topLeftRadius, topRightRadius, bottomLeftRadius, bottomRightRadius;//圆角


    /**
     * 设置是否有按下效果
     *
     * @param clickEffect
     */
    public SuperStateListDrawable setClickEffect(boolean clickEffect) {
        this.clickEffect = clickEffect;
        return this;
    }


    /**
     * 设置按下透明值(包括按下时的 《字体，边框，填充》 颜色)
     *
     * @param clickAlpha
     */
    public SuperStateListDrawable setClickAlpha(@FloatRange(from = 0, to = 1) float clickAlpha) {
        this.clickAlpha = clickAlpha;
        return this;
    }

    /**
     * 四角圆形度数
     */
    public SuperStateListDrawable setRadius(int radius) {
        setRadius(radius, radius, radius, radius);
        return this;
    }

    /**
     * 四角圆形度数
     */
    public SuperStateListDrawable setRadius(int topLeftRadius, int topRightRadius, int bottomLeftRadius, int bottomRightRadius) {
        this.topLeftRadius = ConvertUtils.dp2px(topLeftRadius);
        this.topRightRadius = ConvertUtils.dp2px(topRightRadius);
        this.bottomLeftRadius = ConvertUtils.dp2px(bottomLeftRadius);
        this.bottomRightRadius = ConvertUtils.dp2px(bottomRightRadius);
        return this;
    }

    /**
     * 设置字体颜色
     *
     * @param normalTextColor 正常字体颜色
     */
    public SuperStateListDrawable setTextColorState(TextView textView, @ColorInt int normalTextColor) {
        return setTextColorState(textView, normalTextColor, normalTextColor);
    }

    /**
     * 设置字体颜色
     *
     * @param normalTextColor 正常字体颜色
     * @param clickTextColor  按下字体颜色
     */
    public SuperStateListDrawable setTextColorState(TextView textView, @ColorInt int normalTextColor, @ColorInt int clickTextColor) {
        if (clickTextColor != Color.TRANSPARENT) {
            int alphaComponent = ColorUtils.setAlphaComponent(clickTextColor, clickAlpha);
            textView.setTextColor(getColorStateList(normalTextColor, alphaComponent));
        } else {
            textView.setTextColor(getColorStateList(normalTextColor, Color.TRANSPARENT));
        }
        return this;
    }

    /**
     * 设置字体颜色
     *
     * @param normalTextColor 正常字体颜色
     */
    public SuperStateListDrawable setTextColorState(Button button, @ColorInt int normalTextColor) {
        return setTextColorState(button, normalTextColor);
    }


    /**
     * 设置字体颜色
     *
     * @param normalTextColor 正常字体颜色
     * @param clickTextColor  按下字体颜色
     */
    public SuperStateListDrawable setTextColorState(Button button, @ColorInt int normalTextColor, @ColorInt int clickTextColor) {
        if (clickTextColor != Color.TRANSPARENT) {
            int alphaComponent = ColorUtils.setAlphaComponent(clickTextColor, clickAlpha);
            button.setTextColor(getColorStateList(normalTextColor, alphaComponent));
        } else {
            button.setTextColor(getColorStateList(normalTextColor, Color.TRANSPARENT));
        }
        return this;
    }

    /**
     * 设置填充颜色
     *
     * @param normalSolidColor 正常（抬起）填充颜色
     */
    public SuperStateListDrawable setSolidColorState(@ColorInt int normalSolidColor) {
        return setSolidColorState(normalSolidColor, normalSolidColor);
    }


    /**
     * 设置填充颜色
     *
     * @param normalSolidColor 正常（抬起）填充颜色
     * @param clickSolidColor  点击（按下）填充颜色
     */
    public SuperStateListDrawable setSolidColorState(@ColorInt int normalSolidColor, @ColorInt int clickSolidColor) {
        this.normalSolidColor = normalSolidColor;
        this.clickSolidColor = clickSolidColor;
        return this;
    }

    /**
     * 设置边框颜色及宽度
     *
     * @param strokeWidth       边框宽度
     * @param normalStrokeColor 正常（抬起）边框颜色
     */
    public SuperStateListDrawable setStrokeColorState(int strokeWidth, @ColorInt int normalStrokeColor) {
        return setStrokeColorState(strokeWidth, normalStrokeColor, normalStrokeColor);
    }

    /**
     * 设置边框颜色及宽度
     *
     * @param strokeWidth       边框宽度
     * @param normalStrokeColor 正常（抬起）边框颜色
     * @param clickStrokeColor  点击（按下）边框颜色
     */
    public SuperStateListDrawable setStrokeColorState(int strokeWidth, @ColorInt int normalStrokeColor, @ColorInt int clickStrokeColor) {
        this.strokeWidth = SizeUtils.dp2px(strokeWidth);//边框宽度
        this.normalStrokeColor = normalStrokeColor;//边框颜色
        this.clickStrokeColor = clickStrokeColor;
        return this;
    }


    /**
     * @param starColor   渐变开始颜色
     * @param endColor    渐变结束颜色
     * @param gradient    渐变模式 GradientDrawable.LINEAR_GRADIENT（线性渐变） ， GradientDrawable.SWEEP_GRADIENT（扫描式渐变），GradientDrawable.RADIAL_GRADIENT（圆形渐变）
     * @param orientation 渐变方向
     * @return
     */
    public SuperStateListDrawable setGradient(@ColorInt int starColor, @ColorInt int endColor, Gradient gradient, GradientDrawable.Orientation orientation) {
//        if (starColor != Color.TRANSPARENT && endColor != Color.TRANSPARENT) {
//            this.colors = new int[]{starColor, endColor};
//        } else if (starColor != Color.TRANSPARENT && endColor == Color.TRANSPARENT) {
//            this.colors = new int[]{starColor, starColor};
//        } else if (starColor == Color.TRANSPARENT && endColor != Color.TRANSPARENT) {
//            this.colors = new int[]{endColor, endColor};
//        }
        if (starColor != Color.TRANSPARENT || endColor != Color.TRANSPARENT) {
            this.colors = new int[]{starColor, endColor};
        }
        this.gradient = gradient;
        this.orientation = orientation;
        return this;
    }


    private ColorStateList getColorStateList(@ColorInt int normalStrokeColor, @ColorInt int clickStrokeColor) {
        int pressed = android.R.attr.state_pressed;
        int[][] states = new int[][]
                {
                        new int[]{-pressed},//未点击
                        new int[]{pressed},//点击
                        new int[]{}//默认
                };

        int[] colors = new int[]
                {
                        normalStrokeColor,
                        clickStrokeColor,
                        normalStrokeColor,
                };
        ColorStateList colorStateList = new ColorStateList(states, colors);
        return colorStateList;
    }

    /**
     * 四角圆形度数
     */
    private void setCornerRadii(int radiusTopLeft, int radiusTopRight, int radiusBottomLeft, int radiusBottomRight) {
        setCornerRadii(new float[]{radiusTopLeft,
                radiusTopLeft, radiusTopRight, radiusTopRight,
                radiusBottomRight, radiusBottomRight, radiusBottomLeft,
                radiusBottomLeft});
    }


    /**
     * 设置完成之后必须调用 buid() ，否则不生效
     */
    public Drawable buid() {
        if (clickEffect) {
            return initStateListDrawable();
        } else {
            return initNormalDrawable();
        }

    }

    /**
     * 初始化样式Drawable 不带点击效果
     *
     * @return
     */
    private Drawable initNormalDrawable() {
        //创建drawable
        //SuperStateListDrawable normalGb = new SuperStateListDrawable();//未点击
        //设置类型
        // normalGb.setShape(GradientDrawable.OVAL);
        //渐变颜色 和 单背景色 不能同时设置
        if (colors != null) {
            setColors(colors);//设置渐变颜色
            setGradientType(gradient.ordinal());
            setOrientation(orientation);//设置渐变方向
        } else {
            if (normalSolidColor != Color.TRANSPARENT)
                setColor(normalSolidColor);//设置背景色(只支持纯色,不支持 Bitmap 或 Drawable)
        }

        //设置圆角
        setCornerRadii(topLeftRadius, topRightRadius, bottomLeftRadius, bottomRightRadius);
        //设置按钮的描边粗细和颜色
        setStroke(normalStrokeColor, strokeWidth);
        return this;
    }


    /**
     * @param :colorBg              未点击状态背景色
     * @param :colorBorder          未点击状态的描边色
     * @param :clickColorBg         点击状态背景色
     * @param :clickColorBorder     点击状态的描边色
     * @param :borderWidth          边框宽度
     * @param :isRadiusAdjustBounds 设置圆角大小是否自动适应为 View 的高度的一半
     * @param :radius               四角圆形度数
     * @param :radiusTopLeft        左上圆形度数
     * @param :radiusTopRight       右上圆形度数
     * @param :radiusBottomLeft     左下圆形度数
     * @param :radiusBottomRight    右下圆形度数
     * @return
     */
    private StateListDrawable initStateListDrawable() {
        //创建drawable
        SuperStateListDrawable normalGb = new SuperStateListDrawable();//未点击
        //设置类型
        // normalGb.setShape(GradientDrawable.OVAL);
        //渐变颜色 和 单背景色 不能同时设置
        if (colors != null) {
            normalGb.setColors(colors);//设置渐变颜色
            normalGb.setGradientType(gradient.ordinal());
            normalGb.setOrientation(orientation);//设置渐变方向
        } else {
            if (normalSolidColor != Color.TRANSPARENT)
                normalGb.setColor(normalSolidColor);//设置背景色(只支持纯色,不支持 Bitmap 或 Drawable)
        }

        //设置圆角

        normalGb.setCornerRadii(topLeftRadius, topRightRadius, bottomLeftRadius, bottomRightRadius);

        //设置按钮的描边粗细和颜色
        normalGb.setStroke(strokeWidth, normalStrokeColor);

        //************************************************************************************************
        SuperStateListDrawable pressedGb = new SuperStateListDrawable();//点击
        //pressedGb.setShape(GradientDrawable.OVAL);
        //渐变颜色 和 单背景色 不能同时设置
        if (colors != null) {
            int[] alphacolor = new int[colors.length];
            for (int i = 0; i < colors.length; i++) {
                if (colors[i] != Color.TRANSPARENT) {
                    int alphaComponent = ColorUtils.setAlphaComponent(colors[i], clickAlpha);
                    alphacolor[i] = alphaComponent;
                }
            }
            pressedGb.setColors(alphacolor);//设置渐变颜色
            pressedGb.setGradientType(gradient.ordinal());
            pressedGb.setOrientation(orientation);//设置渐变方向
        } else {
            if (clickSolidColor == Color.TRANSPARENT) {
                if (normalSolidColor != Color.TRANSPARENT) {
                    int alphaComponent = ColorUtils.setAlphaComponent(normalSolidColor, clickAlpha);
                    pressedGb.setColor(alphaComponent);//设置背景色(只支持纯色,不支持 Bitmap 或 Drawable)
                }
            } else {
                int alphaComponent = ColorUtils.setAlphaComponent(clickSolidColor, clickAlpha);
                pressedGb.setColor(alphaComponent);//设置背景色(只支持纯色,不支持 Bitmap 或 Drawable)
            }

        }
        //设置圆角

        pressedGb.setCornerRadii(topLeftRadius, topRightRadius, bottomLeftRadius, bottomRightRadius);

        //设置按钮的描边粗细和颜色
        if (clickStrokeColor == Color.TRANSPARENT) {
            if (normalStrokeColor != Color.TRANSPARENT) {
                int alphaComponent = ColorUtils.setAlphaComponent(normalStrokeColor, clickAlpha);
                pressedGb.setStroke(strokeWidth, alphaComponent);//设置背景色(只支持纯色,不支持 Bitmap 或 Drawable)
            }
        } else {
            int alphaComponent = ColorUtils.setAlphaComponent(clickStrokeColor, clickAlpha);
            pressedGb.setStroke(strokeWidth, alphaComponent);//设置背景色(只支持纯色,不支持 Bitmap 或 Drawable)
        }


        StateListDrawable stalistDrawable = new StateListDrawable();
        int pressed = android.R.attr.state_pressed;//点击
        // //注意该处的顺序，只要有一个状态与之相配，背景就会被换掉
        //  所以不要把大范围放在前面了，如果sd.addState(new[]{},normal)放在第一个的话，就没有什么效果了
        stalistDrawable.addState(new int[]{-pressed}, normalGb);//未选中
        stalistDrawable.addState(new int[]{pressed}, pressedGb);//选中
        ////没有任何状态时显示的图片，就设置空集合，默认状态
        // stalistDrawable.addState(new int[]{}, normalGb);

        // GradientDrawable gradientDrawable=new GradientDrawable();
        return stalistDrawable;
    }


}
