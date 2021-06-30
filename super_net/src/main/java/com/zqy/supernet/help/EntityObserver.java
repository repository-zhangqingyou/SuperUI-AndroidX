package com.zqy.supernet.help;

import com.zqy.supernet.response.Result;

/**
 * 作者: zhangqingyou
 * 时间: 2021/6/30
 * 描述: 返回data里数据
 */
public abstract class EntityObserver<T> extends BaseObserver<Result<T>> {
    @Override
    public void onSuccess(Result<T> res) {
        if (res.getCode() == 200) {
            onEntity(res.getData());
        } else {
            onFailure(new Exception(res.getMsg()));
        }
    }

    public abstract void onEntity(T res);
}
