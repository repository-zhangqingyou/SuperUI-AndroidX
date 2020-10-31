package com.zqy.sui.core.widget.popup.tips;

import android.app.Activity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zqy.superui.R;


/**
 * 作者: zhangqingyou
 * 时间: 2020/6/5 9:03
 * 描述: 加载中PopupWindow
 */
public class LoadPopup extends BaseTipsPopup {

    private TextView mToastMsg;
    private LinearLayout mLlRootView;

    public LoadPopup(Activity activity) {
        super(activity);

    }

    public LoadPopup(Activity activity, int width, int height) {
        super(activity, width, height);
    }

    @Override
    public Object getLayout() {
        return R.layout.superui_load_popup;
    }

    @Override
    public void initView(View rootView) {
        mToastMsg = rootView.findViewById(R.id.toast_msg);
        mLlRootView = rootView.findViewById(R.id.ll_RootView);
    }

    @Override
    public void initData() {

    }

    @Override
    public void listener() {

    }

    public void setText(String text) {
        mToastMsg.setText(text);
    }


}
