package com.zqy.superui.core.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import com.zqy.superui.R;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 作者: zhangqingyou
 * 时间: 2021/3/9
 * 描述:
 */
public class TitleTextListView extends LinearLayout {

    public TitleTextListView(Context context) {
        super(context);
        init();
    }

    public TitleTextListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TitleTextListView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        initView();
        initData();
        listener();
    }

    private void initView() {
        setOrientation(VERTICAL);
    }

    private void initData() {


    }

    private void listener() {

    }

    /**
     * @param
     */
    public void addItem(String title, String text) {
        addItem(title, text, true);
    }

    /**
     * @param
     * @param isAddDivider 是否添加分割线
     */
    public void addItem(String title, String text, boolean isAddDivider) {
        View inflate = View.inflate(getContext(), R.layout.superui_title_text_layout, null);
        inflate.setTag(title);
        AppCompatTextView mTvTitle = inflate.findViewById(R.id.tv_title);
        AppCompatTextView mTvText = inflate.findViewById(R.id.tv_text);
        View mVLine = inflate.findViewById(R.id.v_line);


        mTvTitle.setText(title != null ? title : "");
        mTvText.setText(text != null ? text : "");
        mVLine.setVisibility(isAddDivider ? VISIBLE : GONE);

        addView(inflate, getChildCount());

    }

    public View getItem(int childAt) {
        if (getChildCount() > childAt) return getChildAt(childAt);
        return null;
    }

    public void setItemText(String title, String text) {
        AppCompatTextView itemTextView = getItemTextView(title);
        if (itemTextView != null) {
            itemTextView.setText(text != null ? text : "");
        }

    }

    public AppCompatTextView getItemTextView(String title) {
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            AppCompatTextView mTvTitle = childAt.findViewById(R.id.tv_title);
            if (mTvTitle.getText().toString().equals(title)) {
                AppCompatTextView mTvText = childAt.findViewById(R.id.tv_text);
                return mTvText;
            }
        }
        return null;
    }

    public Map<String, TextView> getItems() {
        Map<String, TextView> stringEditTextMap = new LinkedHashMap<>();
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            AppCompatTextView mTvTitle = childAt.findViewById(R.id.tv_title);
            AppCompatTextView mTvText = childAt.findViewById(R.id.tv_text);

            stringEditTextMap.put(mTvTitle.getText().toString(), mTvText);
        }
        return stringEditTextMap;
    }


    //清除
    public void clearItem() {
        removeAllViews();
    }


}
