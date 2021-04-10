package com.zqy.suidemo.activity;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.os.Bundle;

import com.airbnb.lottie.LottieAnimationView;
import com.blankj.utilcode.util.LogUtils;
import com.zqy.suidemo.R;
import com.zqy.superui.core.ui.activity.SuperUIActivity;

public class LottieActivity extends SuperUIActivity {

    private LottieAnimationView mAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public Object getLayout() {
        return R.layout.activity_lottie;
    }

    @Override
    public void initView() {

        mAnimationView = findViewById(R.id.animation_view);


    }

    @Override
    public void initData() {
        mAnimationView.playAnimation();
//        2.暂停/取消/播放
//        lottieAnimationView.pauseAnimation();
//        lottieAnimationView.cancelAnimation();
//        lottieAnimationView.playAnimation();


//        3. 循环/播放某个部分
//        lottieAnimationView.loop(true);


//        4.像ValueAnimator一样使用setRepeatMode(...) 或 setRepeatCount(...)
//        scaleType ：只支持centerCrop 和 centerInside
//                播放动画的某个部分
//        setMinFrame(...)
//        setMaxFrame(...)
//        setMinProgress(...)
//        setMaxProgress(...)
//        setMinAndMaxFrame(...)
//        setMinAndMaxProgress(...)

//        4. 硬件加速，解决lottie卡顿问题
//        lottieAnimationView.useHardwareAcceleration(true);
//        5. 缓存
//        /*
//         * Lottie内部有两个缓存map（强引用缓存，弱引用缓存），在动画文件加载完成后会根据设置的缓存策略缓存动画，方便下次使用。
//         */
//        lottieAnimationView.setAnimation(animation, LottieAnimationView.CacheStrategy.Strong);    //强缓存
//
//        lottieAnimationView.setAnimation(animation, LottieAnimationView.CacheStrategy.Weak);      //弱缓存
//        根据进度缓存，并为下次播放作准备
//
//        lottieAnimationView.setProgress(progress);        //设置当前进度
//        lottieAnimationView.buildDrawingCache();          //强制缓存绘制数据
//        Bitmap image = lottieAnimationView.getDrawingCache(); //获取当前绘制数据


    }

    @Override
    public void listener() {
        //动画状态监听
        mAnimationView.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                LogUtils.d("动画开始");
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                LogUtils.d("动画结束");
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                LogUtils.d("动画取消");
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                LogUtils.d("动画重复");
            }
        });

        ///监听动画进度 [0,1]
        mAnimationView.addAnimatorUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // 判断动画加载结束
                if (animation.getAnimatedFraction() == 1f) {
                    //结束

                }
            }
        });
    }
}