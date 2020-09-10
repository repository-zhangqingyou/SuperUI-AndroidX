package com.zqy.baseutil;

import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.ScreenUtils;

/**
 * 缓存工具类
 * Author: zhangqingyou
 * Date: 2020/4/7
 * Des:
 */
public class ViewSeting {

    private static int screenWidth;
    private static int screenHeight;

    public static int getScreenWidth() {
        if (screenWidth == 0) screenWidth = ScreenUtils.getScreenWidth();
        return screenWidth;
    }

    public static int getScreenHeight() {
        if (screenHeight == 0) screenHeight = ScreenUtils.getScreenHeight();
        return screenHeight;
    }

    /**
     * 设置view宽高
     *
     * @param view
     * @param width
     * @param height
     */
    public static void setWidthHeight(final View view, final int width, final int height) {
        view.post(new Runnable() {
            @Override
            public void run() {
                ViewGroup.LayoutParams lp = view.getLayoutParams();
                lp.width = width;
                lp.height = height;
                view.setLayoutParams(lp);
            }
        });
    }

    /**
     * 设置view宽高是屏幕的几倍
     *
     * @param view
     * @param widthTimes  当前view是屏幕宽的几倍
     * @param heightTimes 当前view是屏幕高的几倍
     */
    public static void setMeasuredWidthHeight(final ViewGroup view, final float widthTimes, final float heightTimes) {
        view.post(new Runnable() {
            @Override
            public void run() {
                int width1 = view.getWidth();
                int height1 = view.getHeight();
                ViewGroup.LayoutParams lp = view.getLayoutParams();
                lp.width = (int) (width1 * widthTimes);
                lp.height = (int) (height1 * heightTimes);
                view.setLayoutParams(lp);
            }
        });
    }


    /**
     * 设置view高是宽的几倍
     *
     * @param view
     * @param heightTimes 当前view是高是宽的几倍
     */
    public static void setMeasuredHeight(final View view, final float heightTimes) {
        view.post(new Runnable() {
            @Override
            public void run() {
                int width1 = view.getWidth();
                ViewGroup.LayoutParams lp = view.getLayoutParams();
                lp.width = width1;
                lp.height = (int) (width1 * heightTimes);
                view.setLayoutParams(lp);
            }
        });
    }

    /**
     * 设置view高是屏幕高的几倍
     *
     * @param view
     * @param heightTimes 当前view是高是宽的几倍
     */
    public static void setScreenHeight(final View view, final float heightTimes) {
        view.post(new Runnable() {
            @Override
            public void run() {
                int width1 = view.getWidth();
                ViewGroup.LayoutParams lp = view.getLayoutParams();
                lp.width = width1;
                lp.height = (int) (getScreenHeight() * heightTimes);
                view.setLayoutParams(lp);
            }
        });
    }
}
