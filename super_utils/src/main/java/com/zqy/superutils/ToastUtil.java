package com.zqy.superutils;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.Gravity;

import com.blankj.utilcode.util.ToastUtils;

/**
 * 作者: zhangqingyou
 * 时间: 2020/6/12 10:28
 * 描述:
 */
public class ToastUtil {

    /**
     * 消息提示
     *
     * @param text
     */
    public static void toast(final String text) {
        toast(Gravity.CENTER, true, text, 0, 0);
    }

    public static void toast(int gravity, final String text) {
        toast(gravity, true, text, 0, 0);
    }

    public static void toastBottom(final String text, int xOffset, int yOffset) {
        toast(Gravity.BOTTOM, false, text, xOffset, yOffset);
    }

    public static void toastTop(final String text, int xOffset, int yOffset) {
        toast(Gravity.TOP, false, text, xOffset, yOffset);
    }

    /**
     * 消息提示
     *
     * @param text
     */
    public static void toastLong(final String text) {
        toast(Gravity.CENTER, false, text, 0, 0);
    }

    /**
     * 消息提示
     *
     * @param text
     */
    public static void toastLong(int gravity, String text) {
        toast(gravity, false, text, 0, 0);
    }

    /**
     * 消息提示
     *
     * @param text
     */
    public static void toast(final int gravity, final boolean isShort, final String text, final int xOffset, final int yOffset) {
        if (!TextUtils.isEmpty(text)) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    ToastUtils.setGravity(gravity, xOffset, yOffset);
                    if (isShort)
                        ToastUtils.showShort(text);
                    else
                        ToastUtils.showLong(text);
                }
            });
        }
    }
}
