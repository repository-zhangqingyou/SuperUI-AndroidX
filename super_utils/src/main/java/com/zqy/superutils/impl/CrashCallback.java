package com.zqy.superutils.impl;

import com.zqy.superutils.model.Crash;

/**
 * 作者: zhangqingyou
 * 时间: 2021/8/23
 * 描述: 崩溃回调 模型
 */
public interface CrashCallback {
    void onCrash(Crash crash);
}
