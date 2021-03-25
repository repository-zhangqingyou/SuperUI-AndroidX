package com.zqy.superui.core.enums;

import com.zqy.superui.loadinglayout.LoadingLayout;

/**
 * 作者: zhangqingyou
 * 时间: 2021/3/16
 * 描述:
 * //载入成功
 * loadingLayout.setStatus(LoadingLayout.Success);
 * //加载失败
 * loadingLayout.setStatus(LoadingLayout.Error);
 * //正在加载
 * loadingLayout.setStatus(LoadingLayout.Loading);
 * //没有数据
 * loadingLayout.setStatus(LoadingLayout.Empty);
 * //没有网络
 * loadingLayout.setStatus(LoadingLayout.No_Network);
 */
public enum LoadingStatus {
    Success(LoadingLayout.Success),
    Error(LoadingLayout.Error),
    Loading(LoadingLayout.Loading),
    Empty(LoadingLayout.Empty),
    No_Network(LoadingLayout.No_Network);

    private int status;

    LoadingStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
