package com.zqy.baseui;

import android.app.Application;

import com.xuexiang.xui.XUI;

/**
 * Author: zhangqingyou
 * Date: 2020/4/7
 * Des:
 */
public class BaseUIManage {
    private static Application application;

    public static void init(Application application) {
        BaseUIManage.application = application;


//        LoadingLayout.getConfig()
//                .setErrorText("出错啦~请稍后重试！")
//                .setEmptyText("暂无数据~请稍后重试！")
//                .setNoNetworkText("无网络连接，请检查您的网络···")
//                .setErrorImage(R.mipmap.define_error)
//                .setEmptyImage(R.mipmap.define_empty)
//                .setNoNetworkImage(R.mipmap.define_nonetwork)
//                .setAllTipTextColor(ResourceUtils.getColorIdByName("colorPrimary"))
//                .setAllTipTextSize(14)
//                .setReloadButtonText("点我重试哦")
//                .setReloadButtonTextSize(14)
//                .setReloadButtonTextColor(ResourceUtils.getColorIdByName("colorPrimary"))
//                .setReloadButtonWidthAndHeight(150, 40);

        XUI.init(application); //初始化UI框架
        XUI.debug(true);  //开启UI框架调试日志
        //设置默认字体为华文行楷，这里写你的字体库
      //  XUI.getInstance().initFontStyle("fonts/hwxk.ttf");
    }
}
