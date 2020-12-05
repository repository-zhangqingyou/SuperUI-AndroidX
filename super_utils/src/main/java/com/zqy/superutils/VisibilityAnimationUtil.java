package com.zqy.superutils;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

/**
 * 作者: zhangqingyou
 * 时间: 2020/12/5 9:36
 * 描述:  View带动画显示与隐藏
 */
public class VisibilityAnimationUtil {

    /**
     * @param topView  顶部View
     * @param duration 持续时间
     * @param isShow   显示与隐藏(含动画)
     */
    public static void setVisibilityTop(View topView, int duration, boolean isShow) {
        if (isShow) {
            if (topView.getVisibility() == View.VISIBLE) return;
            topView.setVisibility(View.VISIBLE);
        } else {
            if (topView.getVisibility() == View.GONE) return;
            topView.setVisibility(View.GONE);
        }
//        1.fromYDelta  ：
//        a.数字50，表示当前View左上角的Y轴坐标+50px。
//        b.百分比50%，表示当前View的左上角Y轴坐标+此View的长度的50%。
//        c.百分数p 50%p，当前View左上角Y轴坐标+父控件长度的50%。
//        2.fromXDelta：动画开始的X轴坐标
//        3.toYDelta动画结束Y轴坐标。
//        4.toXDelta动画结束的X轴坐标。
        TranslateAnimation animation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, isShow ? -1.0f : -0.0f, Animation.RELATIVE_TO_SELF, isShow ? -0.0f : -1.0f
        );
        visibilityAnimation(topView, animation, duration);
    }


    /**
     * @param bottomView 底部View
     * @param duration   持续时间
     * @param isShow     显示与隐藏(含动画)
     */
    public static void setVisibilityBottom(View bottomView, int duration, boolean isShow) {
        if (isShow) {
            if (bottomView.getVisibility() == View.VISIBLE) return;
            bottomView.setVisibility(View.VISIBLE);
        } else {
            if (bottomView.getVisibility() == View.GONE) return;
            bottomView.setVisibility(View.GONE);
        }
        TranslateAnimation animation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, isShow ? 1.0f : 0, Animation.RELATIVE_TO_SELF, isShow ? 0.0f : 1.0f
        );
        visibilityAnimation(bottomView, animation, duration);
    }

    /**
     * @param leftView 左边View
     * @param duration 持续时间
     * @param isShow   显示与隐藏(含动画)
     */
    public static void setVisibilityLeft(View leftView, int duration, boolean isShow) {
        if (isShow) {
            if (leftView.getVisibility() == View.VISIBLE) return;
            leftView.setVisibility(View.VISIBLE);
        } else {
            if (leftView.getVisibility() == View.GONE) return;
            leftView.setVisibility(View.GONE);
        }
        TranslateAnimation animation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, isShow ? -1.0f : -0.0f, Animation.RELATIVE_TO_SELF, isShow ? -0.0f : -1.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f
        );
        visibilityAnimation(leftView, animation, duration);
    }

    /**
     * @param rightView 右边View
     * @param duration  持续时间
     * @param isShow    显示与隐藏(含动画)
     */
    public static void setVisibilityRight(View rightView, int duration, boolean isShow) {
        if (isShow) {
            if (rightView.getVisibility() == View.VISIBLE) return;
            rightView.setVisibility(View.VISIBLE);
        } else {
            if (rightView.getVisibility() == View.GONE) return;
            rightView.setVisibility(View.GONE);
        }
        TranslateAnimation animation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, isShow ? 1.0f : 0.0f, Animation.RELATIVE_TO_SELF, isShow ? 0.0f : 1.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f
        );

        visibilityAnimation(rightView, animation, duration);
    }

    private static void visibilityAnimation(View view, TranslateAnimation translateAnimation, int duration) {
        translateAnimation.setRepeatMode(Animation.REVERSE);
        translateAnimation.setDuration(duration);
        view.startAnimation(translateAnimation);
    }
}
