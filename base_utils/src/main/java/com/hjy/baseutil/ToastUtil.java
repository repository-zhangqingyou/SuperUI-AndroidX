package com.hjy.baseutil;

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
        if (!TextUtils.isEmpty(text)) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    ToastUtils.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
                    ToastUtils.showShort(text);
                }
            });
        }
    }

    /**
     * 消息提示
     *
     * @param text
     */
    public static void toastLong(final String text) {
        if (!TextUtils.isEmpty(text)) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    ToastUtils.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
                    ToastUtils.showLong(text);
                }
            });
        }
    }
}
