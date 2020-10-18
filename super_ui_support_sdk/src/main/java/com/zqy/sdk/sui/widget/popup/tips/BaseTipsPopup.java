package com.zqy.sdk.sui.widget.popup.tips;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import com.zqy.sdk.sui.other.drawable.SuperStateListDrawable;
import com.zqy.sdk.sui.widget.popup.BasePopup;
import com.zqy.sdk.utils.utilcode.util.ConvertUtils;

/**
 * 作者: zhangqingyou
 * 时间: 2020/6/5 9:03
 * 描述: 提示基准PopupWindow
 */
public abstract class BaseTipsPopup extends BasePopup {

    public BaseTipsPopup(Activity activity) {
        super(activity, ConvertUtils.dp2px(216), ConvertUtils.dp2px(216));
        init();
    }

    public BaseTipsPopup(Activity activity, int width, int height) {
        super(activity, width, height);
        init();
    }

    private void init() {
        setPopupWindowAlpha(0);//设置PopupWindow透明度
        setRootViewBackground(getStateListDrawable(5, Color.parseColor("#666666")));//设置PopupWindow圆角和背景色
    }


    /**
     * 单背景样式
     *
     * @return
     */
    private Drawable getStateListDrawable(int radius, int colorId) {
        Drawable stateListDrawable = new SuperStateListDrawable().setClickAlpha(0.9f)//设置点击后透明度
                .setRadius(radius, radius, radius, radius)//圆角
                .setSolidColor(ContextCompat.getColor(getRootView().getContext(), colorId))//背景颜色
                .buid();
        return stateListDrawable;
    }


}
