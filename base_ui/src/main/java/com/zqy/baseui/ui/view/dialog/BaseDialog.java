package com.zqy.baseui.ui.view.dialog;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatDialog;
import androidx.core.content.ContextCompat;

import com.zqy.baseui.R;
import com.zqy.baseui.ui.drawable.SuperStateListDrawable;
import com.zqy.baseutil.ViewSeting;


/**
 * 作者: zhangqingyou
 * 时间: 2020/6/5 14:03
 * 描述: 基准Dialog
 */
public abstract class BaseDialog extends AppCompatDialog {
    private Activity activity;

    protected BaseDialog(Activity activity) {
        super(activity, R.style.transparentDialog);
        this.activity = activity;
        int screenWidth = (int) (ViewSeting.getScreenWidth() * 0.8);
        init(screenWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
    }


    protected BaseDialog(Activity activity, int width, int height) {
        super(activity, R.style.transparentDialog);
        this.activity = activity;
        init(width, height);
    }

    /**
     * @return
     */
    public abstract Object getLayout();

    /**
     * 初始化view
     */
    public abstract void initView(View rootView);

    /**
     * 初始化数据
     */
    public abstract void initData();

    /**
     * view监听写在这里面
     */
    public abstract void listener();


    private LinearLayout rootView;
    private View mainView;

    private void init(int width, int height) {
        rootView = new LinearLayout(getContext());
        rootView.setOrientation(android.widget.LinearLayout.VERTICAL);

        if (getLayout() instanceof Integer) {
            mainView = View.inflate(getContext(), (Integer) getLayout(), null);
        } else if (getLayout() instanceof View) {
            mainView = (View) getLayout();
        }

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(width, height);
        mainView.setLayoutParams(lp);

        rootView.addView(mainView);

        setContentView(rootView);

        setCancelable(true);//外部可点击

        //设置弹窗背景透明度
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.dimAmount = 0.5f;
        getWindow().setAttributes(layoutParams);

        //设置窗口弹出动画
        getWindow().setWindowAnimations(android.R.style.Animation_Toast);


        initView(rootView);
        initData();
        listener();

    }

    /**
     * 单背景样式
     *
     * @return
     */
    private Drawable getStateListDrawable(int radius, int colorId) {
        Drawable stateListDrawable = new SuperStateListDrawable().setClickAlpha(1f)//设置点击后透明度
                .setRadius(radius)//圆角
                .setColorBg(ContextCompat.getColor(activity, colorId))//背景颜色
                .buid();
        return stateListDrawable;
    }


    /**
     * 设置Dialog圆角和背景颜色
     */
    public void setDialogRadiusColos(int radius, int colorId) {
        mainView.setBackground(getStateListDrawable(radius, colorId));
    }

    public View getRootView() {
        return mainView;
    }

    @Override
    public void show() {
        if (!activity.isFinishing() && !isShowing()) {
            super.show();
        }
    }

    @Override
    public void dismiss() {
        if (!activity.isFinishing() && isShowing()) {
            super.dismiss();
        }
    }


}
