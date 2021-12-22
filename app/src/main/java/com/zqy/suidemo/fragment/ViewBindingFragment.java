package com.zqy.suidemo.fragment;

import android.view.View;

import com.zqy.suidemo.databinding.ViewBindingFragmentBinding;
import com.zqy.superui.core.ui.fragment.SuperUIFragment;

/**
 * 作者: zhangqingyou
 * 时间: 2021/12/22
 * 描述:
 */
public class ViewBindingFragment extends SuperUIFragment {
    private ViewBindingFragmentBinding binding;
    @Override
    public Object getLayoutId() {
        binding = ViewBindingFragmentBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void initView(View mRootView) {
        binding.includeView2.textView.setText("我是include");
        binding.tvText.setText("ViewBindingFragment");
    }

    @Override
    public void onFragmentVisibleChange(boolean isVisible) {

    }

    @Override
    public void initData() {

    }

    @Override
    public void listener() {

    }
}
