package com.zqy.sdk.sui.other.drawable;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;

import androidx.annotation.ColorInt;
import androidx.annotation.FloatRange;

import com.blankj.utilcode.util.ColorUtils;
import com.blankj.utilcode.util.ConvertUtils;


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
    private int strokeWidth;//边框宽度
    private int strokeColor;//正常边框
    private int clickStrokeColor;//点击边框颜色
    private int solidColor;//正常填充颜色
    private int clickSolidColor;//点击填充颜色
    private int gradient = GradientDrawable.LINEAR_GRADIENT;// 渐变模式 GradientDrawable.LINEAR_GRADIENT（线性渐变） ， GradientDrawable.SWEEP_GRADIENT（扫描式渐变），GradientDrawable.RADIAL_GRADIENT（圆形渐变）
    private int[] colors;//设置渐变颜色
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
     * 设置按下透明值
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
     * 正常状态背景色
     *
     * @param solidColor
     */
    public SuperStateListDrawable setSolidColor(@ColorInt int solidColor) {
        this.solidColor = solidColor;
        return this;
    }

    /**
     * 点击状态背景色
     *
     * @param clickSolidColor
     */
    public SuperStateListDrawable setClickSolidColor(@ColorInt int clickSolidColor) {
        this.clickSolidColor = clickSolidColor;
        return this;
    }

    /**
     * 正常状态的边框色
     */

    public SuperStateListDrawable setStrokeColorAndWidth(int strokeWidth, @ColorInt int color) {
        this.strokeWidth = ConvertUtils.dp2px(strokeWidth);
        this.strokeColor = color;
        return this;
    }


    /**
     * 点击状态的边框色
     *
     * @param clickColorBorder
     */
    public SuperStateListDrawable setClickStrokeColor(@ColorInt int clickColorBorder) {
        this.clickStrokeColor = clickColorBorder;
        return this;
    }

    /**
     * @param starColor   渐变开始颜色
     * @param endColor    渐变结束颜色
     * @param gradient    渐变模式 GradientDrawable.LINEAR_GRADIENT（线性渐变） ， GradientDrawable.SWEEP_GRADIENT（扫描式渐变），GradientDrawable.RADIAL_GRADIENT（圆形渐变）
     * @param orientation 渐变方向
     * @return
     */
    public SuperStateListDrawable setGradient(@ColorInt int starColor, @ColorInt int endColor, int gradient, GradientDrawable.Orientation orientation) {
        if (starColor != Color.TRANSPARENT && endColor != Color.TRANSPARENT) {
            this.colors = new int[]{starColor, endColor};
        } else if (starColor != Color.TRANSPARENT && endColor == Color.TRANSPARENT) {
            this.colors = new int[]{starColor, starColor};
        } else if (starColor == Color.TRANSPARENT && endColor != Color.TRANSPARENT) {
            this.colors = new int[]{endColor, endColor};
        }
        this.gradient = gradient;
        this.orientation = orientation;
        return this;
    }

    /**
     * 四角圆形度数
     */
    public void setCornerRadii(int radiusTopLeft, int radiusTopRight, int radiusBottomLeft, int radiusBottomRight) {
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
        SuperStateListDrawable normalGb = new SuperStateListDrawable();//未点击
        //设置类型
        // normalGb.setShape(GradientDrawable.OVAL);
        //渐变颜色 和 单背景色 不能同时设置
        if (colors != null) {
            normalGb.setColors(colors);//设置渐变颜色
            normalGb.setGradientType(gradient);
            normalGb.setOrientation(orientation);//设置渐变方向
        } else {
            if (solidColor != Color.TRANSPARENT)
                normalGb.setColor(solidColor);//设置背景色(只支持纯色,不支持 Bitmap 或 Drawable)
        }

        //设置圆角
        setCornerRadii(topLeftRadius, topRightRadius, bottomLeftRadius, bottomRightRadius);
        //设置按钮的描边粗细和颜色
        normalGb.setStroke(strokeColor, strokeWidth);
        return normalGb;
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
            normalGb.setGradientType(gradient);
            normalGb.setOrientation(orientation);//设置渐变方向
        } else {
            if (solidColor != Color.TRANSPARENT)
                normalGb.setColor(solidColor);//设置背景色(只支持纯色,不支持 Bitmap 或 Drawable)
        }

        //设置圆角

        normalGb.setCornerRadii(topLeftRadius, topRightRadius, bottomLeftRadius, bottomRightRadius);

        //设置按钮的描边粗细和颜色
        normalGb.setStroke(strokeWidth, strokeColor);

        //************************************************************************************************
        SuperStateListDrawable pressedGb = new SuperStateListDrawable();//点击
        //pressedGb.setShape(GradientDrawable.OVAL);
        //渐变颜色 和 单背景色 不能同时设置
        if (colors != null) {
            int[] alphacolor = new int[colors.length];
            for (int i = 0; i < colors.length; i++) {
                int alphaComponent = ColorUtils.setAlphaComponent(colors[i], clickAlpha);
                alphacolor[i] = alphaComponent;
            }
            pressedGb.setColors(alphacolor);//设置渐变颜色
            pressedGb.setGradientType(gradient);
            pressedGb.setOrientation(orientation);//设置渐变方向
        } else {
            if (clickSolidColor == Color.TRANSPARENT) {
                if (solidColor != Color.TRANSPARENT) {
                    int alphaComponent = ColorUtils.setAlphaComponent(solidColor, clickAlpha);
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
            if (strokeColor != Color.TRANSPARENT) {
                int alphaComponent = ColorUtils.setAlphaComponent(strokeColor, clickAlpha);
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
