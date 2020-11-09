package com.zqy.superutils.database;

import java.util.List;

/**
 * 作者: zhangqingyou
 * 时间: 2020/7/3 10:25
 * 描述:
 */
public interface DataListener<T> {
    void onResult(List<T> tList);
}
