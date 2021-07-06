package com.zqy.supernet.help.observer;

import com.zqy.supernet.SuperNetManager;
import com.zqy.superutils.JsonUtils;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;


/**
 * 数据返回统一处理  参考https://www.jianshu.com/p/ff619fea7e22
 */
public abstract class BaseObserver<T> implements Observer<T> {


    @Override
    public void onNext(T response) {
        if (SuperNetManager.getOnApiListener() != null && response != null) {
            SuperNetManager.getOnApiListener().onSuccess(response.getClass().getCanonicalName(), JsonUtils.objectToJson(response));
        }
        onSuccess(response);
    }

    @Override
    public void onError(Throwable e) {//服务器错误信息处理
        if (SuperNetManager.getOnApiListener() != null)
            SuperNetManager.getOnApiListener().onError(e);

        onFailure(e);

    }

    /**
     * 完成时
     */
    @Override
    public void onComplete() {
        if (SuperNetManager.getOnApiListener() != null)
            SuperNetManager.getOnApiListener().onFinish();
    }

    /**
     * 订阅
     *
     * @param d
     */
    @Override
    public void onSubscribe(Disposable d) {
    }

    public abstract void onSuccess(T res);

    public abstract void onFailure(Throwable e);

}
