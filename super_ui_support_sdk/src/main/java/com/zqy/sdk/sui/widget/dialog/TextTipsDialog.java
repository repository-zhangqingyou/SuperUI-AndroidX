package com.zqy.sdk.sui.widget.dialog;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.zqy.sdk.sui.widget.superlayout.SuperTextView;
import com.zqy.sdk.utils.core.ResourcesUtil;

/**
 * 作者: zhangqingyou
 * 时间: 2020/6/5 14:03
 * 描述: 文本提示Dialog
 */
/*
  调用示例：
        TextTipsDialog textTipsDialog = new TextTipsDialog(getActivity())
                .setTitle("标题")
                .setText("内容")
                .setOnLeftButtonClickListener("取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                })
                .setOnRightButtonClickListener("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });
        textTipsDialog.show();
 */

public class TextTipsDialog extends BaseDialog {

    private TextView mTvTitle;
    private TextView mTvText;
    private SuperTextView mTvButton1;
    private View mVDividingLine;
    private SuperTextView mTvButton2;

    public TextTipsDialog(Activity activity) {
        super(activity);

    }

    public TextTipsDialog(Activity activity, int width, int height) {
        super(activity, width, height);
    }


    @Override
    public Object getLayout() {
        int sui_tips_dialog = ResourcesUtil.getLayoutId("sui_tips_dialog");
        return sui_tips_dialog;
    }

    @Override
    public void initView(View view) {
        mTvTitle = view.findViewById(ResourcesUtil.getId("tv_title"));
        mTvText = view.findViewById(ResourcesUtil.getId("tv_text"));
        mTvButton1 = view.findViewById(ResourcesUtil.getId("tv_Button1"));
        mVDividingLine = view.findViewById(ResourcesUtil.getId("v_DividingLine"));
        mTvButton2 = view.findViewById(ResourcesUtil.getId("tv_Button2"));
    }

    @SuppressLint("ResourceType")
    @Override
    public void initData() {
        setDialogRadiusColos(5, Color.WHITE);
        mTvButton1.setTextSize(18);
        mTvButton2.setTextSize(18);
        mTvButton1.setTextColor(ContextCompat.getColor(getContext(), Color.parseColor("#333333")));
        mTvButton1.setClickAlpha(0.5f);
        mTvButton2.setTextColor(ContextCompat.getColor(getContext(), Color.parseColor("#EA534E")));
        mTvButton2.setClickAlpha(0.5f);
        mTvButton1.setText("");
        mTvButton2.setText("");
    }

    @Override
    public void listener() {

    }

    public TextView getTitleView() {
        return mTvTitle;
    }

    public TextView getTextView() {
        return mTvText;
    }

    public TextView getLeftButtonView() {
        return mTvButton1;
    }

    public TextView getRightButtonView() {
        return mTvButton2;
    }

    public TextTipsDialog setTitle(String mTvTitle) {
        this.mTvTitle.setText(mTvTitle);
        return this;
    }

    public TextTipsDialog setTitleSize(float size) {
        this.mTvTitle.setTextSize(size);
        return this;
    }

    public TextTipsDialog setTitleColor(@ColorInt int color) {
        this.mTvTitle.setTextColor(color);
        return this;
    }

    public TextTipsDialog setText(String mTvText) {
        this.mTvText.setText(mTvText);
        return this;
    }

    public TextTipsDialog setTextSize(float size) {
        this.mTvText.setTextSize(size);
        return this;
    }

    public TextTipsDialog setTextColor(@ColorInt int color) {
        this.mTvText.setTextColor(color);
        return this;
    }

    public TextTipsDialog setLeftButtonSize(float size) {
        this.mTvButton1.setTextSize(size);
        return this;
    }

    public TextTipsDialog setLeftButtonColor(@ColorInt int color) {
        this.mTvButton1.setTextColor(color);
        this.mTvButton1.setClickAlpha(0.5f);
        return this;
    }

    public TextTipsDialog setRightButtonSize(float size) {
        this.mTvButton2.setTextSize(size);
        return this;
    }

    public TextTipsDialog setRightButtonColor(@ColorInt int color) {
        this.mTvButton2.setTextColor(color);
        this.mTvButton2.setClickAlpha(0.5f);
        return this;
    }

    public TextTipsDialog setOnLeftButtonClickListener(String text, final OnClickListener onNoClickListener) {
        mTvButton1.setText(text);
        mTvButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onNoClickListener != null)
                    onNoClickListener.onClick(TextTipsDialog.this, v);
            }
        });
        return this;
    }

    public TextTipsDialog setOnRightButtonClickListener(String text, final OnClickListener onYesClickListener) {
        mTvButton2.setText(text);
        mTvButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onYesClickListener != null)
                    onYesClickListener.onClick(TextTipsDialog.this, v);
            }
        });
        return this;
    }


    @Override
    public void show() {
        String mTvButton1String = mTvButton1.getText().toString();
        String mTvButton2String = mTvButton2.getText().toString();
        if (TextUtils.isEmpty(mTvButton1String)) {
            mTvButton1.setVisibility(View.GONE);
        } else {
            mTvButton1.setVisibility(View.VISIBLE);
        }
        if (TextUtils.isEmpty(mTvButton2String)) {
            mTvButton2.setVisibility(View.GONE);
        } else {
            mTvButton2.setVisibility(View.VISIBLE);
        }

        if (TextUtils.isEmpty(mTvButton1String) || TextUtils.isEmpty(mTvButton2String)) {
            mVDividingLine.setVisibility(View.GONE);
        } else {
            mVDividingLine.setVisibility(View.VISIBLE);
        }

        super.show();
    }


    public interface OnClickListener {
        void onClick(TextTipsDialog textTipsDialog, View v);
    }

}
