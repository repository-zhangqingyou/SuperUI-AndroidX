package com.zqy.sui.core.widget.dialog;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.core.content.ContextCompat;

import com.zqy.sui.core.widget.textview.SuperTextView;
import com.zqy.superui.R;


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
        return R.layout.superui_tips_dialog;
    }

    @Override
    public void initView(View view) {
        mTvTitle = view.findViewById(R.id.tv_title);
        mTvText = view.findViewById(R.id.tv_text);
        mTvButton1 = view.findViewById(R.id.tv_Button1);
        mVDividingLine = view.findViewById(R.id.v_DividingLine);
        mTvButton2 = view.findViewById(R.id.tv_Button2);
    }

    @Override
    public void initData() {
        setDialogRadiusColos(5, R.color.superui_white);
        mTvButton1.setTextSize(18);
        mTvButton2.setTextSize(18);
        mTvButton1.setTextColorState(ContextCompat.getColor(getContext(), R.color.superui_black_light1), ContextCompat.getColor(getContext(), R.color.superui_black_light1));
        mTvButton1.setClickAlpha(0.5f);
        mTvButton2.setTextColorState(ContextCompat.getColor(getContext(), R.color.superui_red_light), ContextCompat.getColor(getContext(), R.color.superui_red_light));
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
        this.mTvButton1.setClickAlpha(0.5f);
        this.mTvButton1.setTextColorState(color, color);
        return this;
    }

    public TextTipsDialog setRightButtonSize(float size) {
        this.mTvButton2.setTextSize(size);
        return this;
    }

    public TextTipsDialog setRightButtonColor(@ColorInt int color) {
        this.mTvButton2.setClickAlpha(0.5f);
        this.mTvButton2.setTextColorState(color, color);
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
