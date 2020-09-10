package com.zqy.baseui.ui.view.button;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import androidx.annotation.ColorInt;
import androidx.annotation.FloatRange;
import androidx.appcompat.widget.AppCompatButton;

import com.blankj.utilcode.util.ColorUtils;

/**
 * Author: zhangqingyou
 * Date: 2020/4/28 11:32
 * Des:
 */
public class SuperButton extends AppCompatButton {
    public SuperButton(Context context) {
        super(context);
        init(context, null);
    }

    public SuperButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public SuperButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }


    private void init(Context context, AttributeSet attrs) {
        SuperButtonDrawable superButtonDrawable = new SuperButtonDrawable();
        Drawable bg = superButtonDrawable.initStateListDrawable(context, attrs);
        setBackgroundDrawable(bg);

        setOnClickTextAlpha(superButtonDrawable.getClickAlpha());
    }


    /**
     * 设置字体按下颜色
     *
     * @param clickTextColor
     */
    public void setOnClickTextColor(@ColorInt int clickTextColor) {
        setOnClickTextAlpha(clickTextColor, 1.0f);
    }

    /**
     * 设置字体按下后颜色透明度
     *
     * @param clickTextAlpha 值 只能在0-1之间
     */
    public void setOnClickTextAlpha(@FloatRange(from = 0, to = 1) float clickTextAlpha) {
        setOnClickTextAlpha(getTextColors().getDefaultColor(), clickTextAlpha);
    }


    /**
     * 设置字体按下颜色透明值
     *
     * @param clickTextAlpha 值 只能在0-1之间
     */

    public void setOnClickTextAlpha(@ColorInt int clickTextColor, @FloatRange(from = 0, to = 1) float clickTextAlpha) {
        int defaultColor = getTextColors().getDefaultColor();
        int alphaComponent = ColorUtils.setAlphaComponent(clickTextColor, clickTextAlpha);
        int pressed = android.R.attr.state_pressed;
        int[][] states = new int[][]
                {
                        new int[]{-pressed},//未点击
                        new int[]{pressed},//点击
                        new int[]{}//默认
                };

        int[] colors = new int[]
                {
                        defaultColor,
                        alphaComponent,
                        defaultColor,
                };
        ColorStateList colorStateList = new ColorStateList(states, colors);

        setTextColor(colorStateList);
    }
}
