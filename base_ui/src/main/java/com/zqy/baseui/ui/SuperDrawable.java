package com.zqy.baseui.ui;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;

import androidx.annotation.FloatRange;
import androidx.annotation.Nullable;

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
public class SuperDrawable extends GradientDrawable {
    private @FloatRange(from = 0, to = 1)
    float clickAlpha = 0;// //设置按下背景色的透明值
    private boolean clickEffect = true;//设置是否有按下效果 默认有
    private ColorStateList colorBg, colorBorder, clickColorBg, clickColorBorder;
    private int gradient;//设置线性渐变，除此之外还有：GradientDrawable.SWEEP_GRADIENT（扫描式渐变），GradientDrawable.RADIAL_GRADIENT（圆形渐变）
    private int[] colors;//设置渐变颜色
    private Orientation orientation;//设置渐变方向
    private int borderWidth, radius, radiusTopLeft, radiusTopRight, radiusBottomLeft, radiusBottomRight;
    private boolean isRadiusAdjustBounds;


    /**
     * 设置按钮的背景色(只支持纯色,不支持 Bitmap 或 Drawable)
     */
    private void setBgData(@Nullable ColorStateList colors) {
        if (hasNativeStateListAPI()) {
            super.setColor(colors);
        } else {
            colorBg = colors;
            final int currentColor;
            if (colorBg == null) {
                currentColor = Color.TRANSPARENT;
            } else {
                currentColor = colors.getColorForState(getState(), 0);
            }
            setColor(currentColor);
        }
    }

    /**
     * 设置按钮的描边粗细和颜色
     */
    private void setStrokeData(int width, @Nullable ColorStateList colors) {
        if (hasNativeStateListAPI()) {
            super.setStroke(width, colors);
        } else {
            borderWidth = width;
            colorBorder = colors;
            final int currentColor;
            if (colors == null) {
                currentColor = Color.TRANSPARENT;
            } else {
                currentColor = colors.getColorForState(getState(), 0);
            }
            setStroke(width, currentColor);
        }
    }

    private boolean hasNativeStateListAPI() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }


    @Override
    protected boolean onStateChange(int[] stateSet) {
        boolean superRet = super.onStateChange(stateSet);
        if (colorBg != null) {
            int color = colorBg.getColorForState(stateSet, 0);
            setColor(color);
            superRet = true;
        }
        if (colorBorder != null) {
            int color = colorBorder.getColorForState(stateSet, 0);
            setStroke(borderWidth, color);
            superRet = true;
        }
        return superRet;
    }

    @Override
    public boolean isStateful() {
        return (colorBg != null && colorBg.isStateful())
                || (colorBorder != null && colorBorder.isStateful())
                || super.isStateful();
    }

    @Override
    protected void onBoundsChange(Rect r) {
        super.onBoundsChange(r);
        if (isRadiusAdjustBounds) {
            // 修改圆角为短边的一半
            setCornerRadius(Math.min(r.width(), r.height()) / 2);
        }
    }

    /**
     * 设置是否有按下效果
     *
     * @param clickEffect
     */
    public SuperDrawable setClickEffect(boolean clickEffect) {
        this.clickEffect = clickEffect;
        return this;
    }

    /**
     * 给某个颜色值加上透明度
     *
     * @return
     */
    private int getAlphaColor(int color, @FloatRange(from = 0, to = 1) float clickAlpha) {
        int alphaComponent = ColorUtils.setAlphaComponent(color, clickAlpha);
        //设置按下背景色为 未选中状态背景色 的指定透明值
        return alphaComponent;
    }


    /**
     * 设置按下透明值
     *
     * @param clickAlpha
     */
    public SuperDrawable setClickAlpha(@FloatRange(from = 0, to = 1) float clickAlpha) {
        this.clickAlpha = clickAlpha;
        return this;
    }


    /**
     * 未点击状态背景色
     *
     * @param colorBg
     */
    public SuperDrawable setColorBg(int colorBg) {
        ColorStateList colorStateList = ColorStateList.valueOf(colorBg);
        this.colorBg = colorStateList;
        return this;
    }


    /**
     * 未点击状态的描边色
     *
     * @param colorBorder
     */

    public SuperDrawable setColorBorder(int colorBorder) {
        ColorStateList colorStateList = ColorStateList.valueOf(colorBorder);
        this.colorBorder = colorStateList;
        return this;
    }


    /**
     * 选中状态背景色
     *
     * @param clickColorBg
     */
    public SuperDrawable setClickColorBg(int clickColorBg) {

        ColorStateList colorStateList = ColorStateList.valueOf(clickColorBg);
        this.clickColorBg = colorStateList;

        return this;
    }


    /**
     * 选中状态的描边色
     *
     * @param clickColorBorder
     */
    public SuperDrawable setClickColorBorder(int clickColorBorder) {
        ColorStateList colorStateList = ColorStateList.valueOf(clickColorBorder);
        this.clickColorBorder = colorStateList;

        return this;
    }

    /**
     * 设置线性渐变，除此之外还有：GradientDrawable.SWEEP_GRADIENT（扫描式渐变），GradientDrawable.RADIAL_GRADIENT（圆形渐变）
     *
     * @param gradient
     * @return
     */
    public SuperDrawable setSGradientType(int gradient) {
        this.gradient = gradient;
        return this;
    }

    /**
     * 设置渐变颜色
     *
     * @param colors
     * @return
     */
    public SuperDrawable setSColors(int[] colors) {
        this.colors = colors;
        return this;
    }

    /**
     * 设置渐变方向
     *
     * @param orientation
     * @return
     */
    public SuperDrawable setSOrientation(Orientation orientation) {
        this.orientation = orientation;

        return this;
    }


    /**
     * 边框宽度
     *
     * @param borderWidth
     */
    public SuperDrawable setBorderWidth(int borderWidth) {
        this.borderWidth = ConvertUtils.dp2px(borderWidth);
        return this;
    }

    /**
     * 四角圆形度数
     *
     * @param radius
     */
    public SuperDrawable setRadius(int radius) {
        this.radius = ConvertUtils.dp2px(radius);
        return this;
    }

    /**
     * 左上圆形度数
     *
     * @param radiusTopLeft
     */
    public SuperDrawable setRadiusTopLeft(int radiusTopLeft) {
        this.radiusTopLeft = ConvertUtils.dp2px(radiusTopLeft);
        return this;
    }

    /**
     * 右上圆形度数
     *
     * @param radiusTopRight
     */
    public SuperDrawable setRadiusTopRight(int radiusTopRight) {
        this.radiusTopRight = ConvertUtils.dp2px(radiusTopRight);
        return this;
    }

    /**
     * 左下圆形度数
     *
     * @param radiusBottomLeft
     */
    public SuperDrawable setRadiusBottomLeft(int radiusBottomLeft) {
        this.radiusBottomLeft = ConvertUtils.dp2px(radiusBottomLeft);
        return this;
    }

    /**
     * 右下圆形度数
     *
     * @param radiusBottomRight
     */
    public SuperDrawable setRadiusBottomRight(int radiusBottomRight) {
        this.radiusBottomRight = ConvertUtils.dp2px(radiusBottomRight);
        return this;
    }

    /**
     * 设置圆角大小是否自动适应为 View 的高度的一半
     *
     * @param radiusAdjustBounds
     */
    public SuperDrawable setRadiusAdjustBounds(boolean radiusAdjustBounds) {
        isRadiusAdjustBounds = radiusAdjustBounds;
        return this;
    }

    public Drawable buid() {

        if (clickColorBg == null) {
            if (colorBg != null) {
                /**
                 * 如果未设置按下状态下的颜色 默认给背景色加上透明值
                 */
                int alphaColor = getAlphaColor(colorBg.getDefaultColor(), clickAlpha);
                clickColorBg = ColorStateList.valueOf(alphaColor);//选中状态下的背景色
            }

        } else {
            int defaultColor = clickColorBg.getDefaultColor();
            if (defaultColor != Color.TRANSPARENT) {
                int alphaColor = getAlphaColor(defaultColor, clickAlpha);
                clickColorBg = ColorStateList.valueOf(alphaColor);//选中状态下的背景色
            }

        }
        if (clickColorBorder == null) {
            if (colorBorder != null) {
                /**
                 * 如果未设置按下状态下的边框颜色 默认给边框色加上透明值
                 */
                int alphaColor = getAlphaColor(colorBorder.getDefaultColor(), clickAlpha);
                clickColorBorder = ColorStateList.valueOf(alphaColor);//选中状态下的边框色
            }

        } else {
            int defaultColor = clickColorBorder.getDefaultColor();
            if (defaultColor != Color.TRANSPARENT) {
                int alphaColor = getAlphaColor(defaultColor, clickAlpha);
                clickColorBorder = ColorStateList.valueOf(alphaColor);//选中状态下的边框色
            }

        }

        if (clickEffect) {
            return initStateListDrawable();
        } else {
            return initShapeDrawable();
        }

    }

    /**
     * 初始化样式Drawable 不带点击效果
     *
     * @return
     */
    private Drawable initShapeDrawable() {

        //创建drawable
        SuperDrawable normalGb = new SuperDrawable();//未点击
        //设置类型
        // normalGb.setShape(GradientDrawable.OVAL);
        //渐变颜色 和 单背景色 不能同时设置
        if (colors != null) {
            normalGb.setColors(colors);//设置渐变颜色
            normalGb.setGradientType(gradient);//设置线性渐变，除此之外还有：GradientDrawable.SWEEP_GRADIENT（扫描式渐变），GradientDrawable.RADIAL_GRADIENT（圆形渐变）
            normalGb.setOrientation(orientation);//设置渐变方向
        } else {
            if (colorBg != null)
                normalGb.setBgData(colorBg);//设置背景色(只支持纯色,不支持 Bitmap 或 Drawable)
        }


        normalGb.setStrokeData(borderWidth, colorBorder);//设置按钮的描边粗细和颜色
        normalGb.setRadiusAdjustBounds(isRadiusAdjustBounds);//设置圆角大小是否自动适应为 View 的高度的一半
        if (radiusTopLeft > 0 || radiusTopRight > 0 || radiusBottomLeft > 0 || radiusBottomRight > 0) {
            float[] radii = new float[]{
                    radiusTopLeft, radiusTopLeft,
                    radiusTopRight, radiusTopRight,
                    radiusBottomRight, radiusBottomRight,
                    radiusBottomLeft, radiusBottomLeft
            };
            normalGb.setCornerRadii(radii);
        } else {
            normalGb.setCornerRadius(radius);
        }

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
        SuperDrawable normalGb = new SuperDrawable();//未点击
        //设置类型
        // normalGb.setShape(GradientDrawable.OVAL);
        //渐变颜色 和 单背景色 不能同时设置
        if (colors != null) {
            normalGb.setColors(colors);//设置渐变颜色
            normalGb.setGradientType(gradient);//设置线性渐变，除此之外还有：GradientDrawable.SWEEP_GRADIENT（扫描式渐变），GradientDrawable.RADIAL_GRADIENT（圆形渐变）
            normalGb.setOrientation(orientation);//设置渐变方向
        } else {
            if (colorBg != null)
                normalGb.setBgData(colorBg);//设置背景色(只支持纯色,不支持 Bitmap 或 Drawable)
        }


        normalGb.setStrokeData(borderWidth, colorBorder);//设置按钮的描边粗细和颜色
        normalGb.setRadiusAdjustBounds(isRadiusAdjustBounds);//设置圆角大小是否自动适应为 View 的高度的一半
        if (radiusTopLeft > 0 || radiusTopRight > 0 || radiusBottomLeft > 0 || radiusBottomRight > 0) {
            float[] radii = new float[]{
                    radiusTopLeft, radiusTopLeft,
                    radiusTopRight, radiusTopRight,
                    radiusBottomRight, radiusBottomRight,
                    radiusBottomLeft, radiusBottomLeft
            };
            normalGb.setCornerRadii(radii);
        } else {
            normalGb.setCornerRadius(radius);
        }

        SuperDrawable pressedGb = new SuperDrawable();//点击

        //pressedGb.setShape(GradientDrawable.OVAL);
        //渐变颜色 和 单背景色 不能同时设置
        if (colors != null) {
            int[] alphacolor = new int[colors.length];
            for (int i = 0; i < colors.length; i++) {
                alphacolor[i] = getAlphaColor(colors[i], clickAlpha);
            }
            pressedGb.setColors(alphacolor);//设置渐变颜色
            pressedGb.setGradientType(gradient);//设置线性渐变，除此之外还有：GradientDrawable.SWEEP_GRADIENT（扫描式渐变），GradientDrawable.RADIAL_GRADIENT（圆形渐变）
            pressedGb.setOrientation(orientation);//设置渐变方向
        } else {
            if (clickColorBg != null)
                pressedGb.setBgData(clickColorBg);//设置背景色(只支持纯色,不支持 Bitmap 或 Drawable)
        }
        pressedGb.setStrokeData(borderWidth, clickColorBorder);//设置按钮的描边粗细和颜色
        pressedGb.setRadiusAdjustBounds(isRadiusAdjustBounds);
        if (radiusTopLeft > 0 || radiusTopRight > 0 || radiusBottomLeft > 0 || radiusBottomRight > 0) {
            float[] radii = new float[]{
                    radiusTopLeft, radiusTopLeft,
                    radiusTopRight, radiusTopRight,
                    radiusBottomRight, radiusBottomRight,
                    radiusBottomLeft, radiusBottomLeft
            };
            pressedGb.setCornerRadii(radii);
        } else {
            pressedGb.setCornerRadius(radius);
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
