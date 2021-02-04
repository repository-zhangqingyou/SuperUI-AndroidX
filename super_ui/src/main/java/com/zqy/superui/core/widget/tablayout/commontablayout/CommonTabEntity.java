package com.zqy.superui.core.widget.tablayout.commontablayout;

import androidx.annotation.DrawableRes;

/**
 * 作者: zhangqingyou
 * 时间: 2021/2/3 21:22
 * 描述:
 */
public interface CommonTabEntity {
    String getTabTitle();

    @DrawableRes
    int getTabSelectedIcon();

    @DrawableRes
    int getTabUnselectedIcon();

    float getWeight();//当前tab所占宽度权重
}
