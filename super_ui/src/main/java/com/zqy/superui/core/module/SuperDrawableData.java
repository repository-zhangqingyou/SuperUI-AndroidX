package com.zqy.superui.core.module;

import java.io.Serializable;

/**
 * 作者: zhangqingyou
 * 时间: 2020/11/3 16:01
 * 描述:
 */
public class SuperDrawableData implements Serializable {
    private boolean clickEffect;//设置是否有按下效果 默认有
    private float clickAlpha;//按下时 背景颜色和字体颜色 透明度
    private int normalSolidColor;//填充颜色
    private int clickSolidColor;//按下时的填充颜色
    private int clickStrokeColor;//按现时 边框颜色
    private int strokeColor;//边框颜色
    private int normalTextColor;//正常字体颜色
    private int clickTextColor;//按下时 字体颜色
    private int strokeWidth;//边框宽度
    private int startColor;//渐变开始颜色
    private int endColor;//渐变结束颜色
    private int gradient;//渐变模式   默认线性
    private int gradientOrientation;//渐变方向 默认从左到右
    private int radius,
            topLeftRadius,
            topRightRadius,
            bottomLeftRadius,
            bottomRightRadius;//圆角

    public boolean isClickEffect() {
        return clickEffect;
    }

    public void setClickEffect(boolean clickEffect) {
        this.clickEffect = clickEffect;
    }

    public float getClickAlpha() {
        return clickAlpha;
    }

    public void setClickAlpha(float clickAlpha) {
        this.clickAlpha = clickAlpha;
    }

    public int getNormalSolidColor() {
        return normalSolidColor;
    }

    public void setNormalSolidColor(int normalSolidColor) {
        this.normalSolidColor = normalSolidColor;
    }

    public int getClickSolidColor() {
        return clickSolidColor;
    }

    public void setClickSolidColor(int clickSolidColor) {
        this.clickSolidColor = clickSolidColor;
    }

    public int getClickStrokeColor() {
        return clickStrokeColor;
    }

    public void setClickStrokeColor(int clickStrokeColor) {
        this.clickStrokeColor = clickStrokeColor;
    }

    public int getStrokeColor() {
        return strokeColor;
    }

    public void setStrokeColor(int strokeColor) {
        this.strokeColor = strokeColor;
    }

    public int getNormalTextColor() {
        return normalTextColor;
    }

    public void setNormalTextColor(int normalTextColor) {
        this.normalTextColor = normalTextColor;
    }

    public int getClickTextColor() {
        return clickTextColor;
    }

    public void setClickTextColor(int clickTextColor) {
        this.clickTextColor = clickTextColor;
    }

    public int getStrokeWidth() {
        return strokeWidth;
    }

    public void setStrokeWidth(int strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    public int getStartColor() {
        return startColor;
    }

    public void setStartColor(int startColor) {
        this.startColor = startColor;
    }

    public int getEndColor() {
        return endColor;
    }

    public void setEndColor(int endColor) {
        this.endColor = endColor;
    }

    public int getGradient() {
        return gradient;
    }

    public void setGradient(int gradient) {
        this.gradient = gradient;
    }

    public int getGradientOrientation() {
        return gradientOrientation;
    }

    public void setGradientOrientation(int gradientOrientation) {
        this.gradientOrientation = gradientOrientation;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getTopLeftRadius() {
        return topLeftRadius;
    }

    public void setTopLeftRadius(int topLeftRadius) {
        this.topLeftRadius = topLeftRadius;
    }

    public int getTopRightRadius() {
        return topRightRadius;
    }

    public void setTopRightRadius(int topRightRadius) {
        this.topRightRadius = topRightRadius;
    }

    public int getBottomLeftRadius() {
        return bottomLeftRadius;
    }

    public void setBottomLeftRadius(int bottomLeftRadius) {
        this.bottomLeftRadius = bottomLeftRadius;
    }

    public int getBottomRightRadius() {
        return bottomRightRadius;
    }

    public void setBottomRightRadius(int bottomRightRadius) {
        this.bottomRightRadius = bottomRightRadius;
    }
}
