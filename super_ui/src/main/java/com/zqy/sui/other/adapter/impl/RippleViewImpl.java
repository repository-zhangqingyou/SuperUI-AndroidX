package com.zqy.sui.other.adapter.impl;


import androidx.annotation.ColorRes;

import com.xuexiang.xui.widget.button.RippleView;

/**
 * 作者: zhangqingyou
 * 时间: 2020/7/10 12:30
 * 描述:
 */
public interface RippleViewImpl {
    /**
     * 波纹样式
     *
     * @return
     */
    RippleView.RippleType getRippleType();

    /**
     * 波纹颜色
     *
     * @return
     */
    @ColorRes
    int getRippleColor();

    /**
     * 波纹View的宽
     *
     * @return
     */
    int getRippleViewWidth();

    /**
     * 波纹View的高
     *
     * @return
     */
    int getRippleViewheight();

    /**
     * 设置帧率纹波动画 新的帧率值，默认为10
     *
     * @return
     */
    int getFrameRate();

    /**
     * 对于连锁反应颜色设置阿尔法  Alpha值介于0到255之间，默认值为90
     *
     * @return
     */
    int getRippleAlpha();//

    /**
     * 在MS纹波动画的持续时间 400ms
     *
     * @return
     */
    int getRippleDuration();//

    /**
     * 结束动画的持续时间（以毫秒为单位 默认200ms
     *
     * @return
     */
    int getZoomDuration();//
}
