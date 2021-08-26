package com.zqy.superui.core.ui.activity.erro;

import android.view.View;

import com.blankj.utilcode.util.AppUtils;
import com.zqy.superui.R;
import com.zqy.superui.core.widget.TitleTextListView;
import com.zqy.superui.core.widget.textview.SuperTextView;
import com.zqy.superutils.model.Crash;

/**
 * 作者: zhangqingyou
 * 时间: 2021/8/23
 * 描述: 异常捕获
 */
public class SimpleErrorActivity extends SuperUIErrorActivity {
    private TitleTextListView mTtListView;
    private SuperTextView mTvRestart;


    @Override
    public Object getContentLayout() {
        return R.layout.superui_error_activity;
    }

    @Override
    public int getToolbarMenu() {
        return 0;
    }

    @Override
    public void initView() {
        super.initView();
        mTtListView = findViewById(R.id.tt_list_view);
        mTvRestart = findViewById(R.id.tv_restart);
    }

    @Override
    public void initData() {
        super.initData();
        getToolbar().setTitle("");
        getTbText().setText("出错啦");
        getToolbar().setNavigationIcon(null);
    }

    @Override
    public void onCrash(Crash crash) {
        mTtListView.addItem("CPU架构支持", crash.getCpuAbi());
        mTtListView.addItem("手机品牌", crash.getBrand());
        mTtListView.addItem("手机型号", crash.getModel());
        mTtListView.addItem("安卓API", crash.getSdkVersionCode());
        mTtListView.addItem("安卓版本", crash.getSdkVersionName());
        mTtListView.addItem("异常类型", crash.getCrashType().name());
        mTtListView.addItem("错误类型", crash.getErrorType());
        mTtListView.addItem("错误消息", crash.getErrorMessage());
        mTtListView.addItem("堆栈信息", crash.getErrorStack());
    }

    @Override
    public void listener() {
        super.listener();
        mTvRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppUtils.relaunchApp(true);
            }
        });
    }
}
