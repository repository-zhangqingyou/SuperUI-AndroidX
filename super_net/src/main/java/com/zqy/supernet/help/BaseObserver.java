package com.zqy.supernet.help;

import com.zqy.supernet.response.BaseResult;
import com.zqy.supernet.response.Result;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;


/**
 * 数据返回统一处理  参考https://www.jianshu.com/p/ff619fea7e22
 *
 * @param <T>
 */
public abstract class BaseObserver<T> implements Observer<BaseResult<T>> {

    @Override
    public void onNext(BaseResult<T> response) {
        //在这边对 基础数据 进行统一处理  举个例子：
        if (response instanceof Result) {
            Result result = (Result) response;
            if (result.getCode() == 200) {
                onSuccess(response.getData());
            } else {
                onFailure(null, result.getMsg());
            }
        }else {
            onSuccess(response.getData());
        }

    }

    @Override
    public void onError(Throwable e) {//服务器错误信息处理
        onFailure(e, RxExceptionUtil.exceptionHandler(e));
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onSubscribe(Disposable d) {
    }

    public abstract void onSuccess(T result);

    public abstract void onFailure(Throwable e, String errorMsg);

}
