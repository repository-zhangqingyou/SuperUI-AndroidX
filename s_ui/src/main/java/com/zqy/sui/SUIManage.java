package com.zqy.sui;

import android.app.Application;

import com.xuexiang.xui.XUI;
import com.zqy.srequest.RequestManage;
import com.zqy.sutils.UtilsManage;

/**
 * Author: zhangqingyou
 * Date: 2020/4/7
 * Des:
 */
public class SUIManage {
    private static Application application;

    public static Application getApplication() {
        return application;
    }

    public static void init(Application application) {
        SUIManage.application = application;


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
        //工具模组初始化
        UtilsManage.init(application);
        //网络请求模组初始化
        RequestManage.init(application);
        RequestManage.setDEBUG(true);
    }
}
