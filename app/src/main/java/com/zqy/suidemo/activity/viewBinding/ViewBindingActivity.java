package com.zqy.suidemo.activity.viewBinding;

import com.zqy.suidemo.R;
import com.zqy.suidemo.databinding.ViewBindingActivityBinding;
import com.zqy.suidemo.fragment.ViewBindingFragment;
import com.zqy.superui.core.ui.activity.SuperUIActivity;

/**
 * 作者: zhangqingyou
 * 时间: 2021/12/22
 * 描述:
 * View Binding是Android Studio 3.6推出的新特性，
 * 目的是为了替代findViewById(内部实现还是使用findViewById)。。
 * 在启动视图绑定后，系统会为改模块中的每个xml文件生成一个绑定类，
 * 绑定类的实例包含对在相应布局中具有 ID 的所有视图的直接引用
 */
public class ViewBindingActivity extends SuperUIActivity {
    private ViewBindingActivityBinding binding;
    @Override
    public Object getLayout() {
        //关键代码
        binding = ViewBindingActivityBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public boolean onStatusBarVisibility() {
        return true;
    }

    @Override
    public boolean onTransparentStatusBar() {
        return true;
    }

    @Override
    public boolean onStatusBarLightMode() {
        return true;
    }

    @Override
    public void initView() {
        binding.includeView.textView.setText("我是include");
        binding.textView2.setText("测试");
    }

    @Override
    public void initData() {
       showFragment(R.id.ll_container, ViewBindingFragment.class);
    }

    @Override
    public void listener() {

    }
}