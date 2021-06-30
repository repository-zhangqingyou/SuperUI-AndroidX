package com.zqy.supernet.help;

import android.content.Context;

import com.trello.rxlifecycle4.android.ActivityEvent;
import com.trello.rxlifecycle4.components.RxActivity;
import com.trello.rxlifecycle4.components.support.RxAppCompatActivity;
import com.trello.rxlifecycle4.components.support.RxFragment;
import com.trello.rxlifecycle4.components.support.RxFragmentActivity;

import org.reactivestreams.Publisher;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableTransformer;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.schedulers.Schedulers;


/**
 * 作者: zhangqingyou
 * 时间: 2021/3/26
 * 描述: 调度类
 * <p>
 * 当使用RxJava订阅并执行耗时任务后，当Activity被finish时，
 * 如果耗时任务还未完成，没有及时取消订阅，就会导致Activity无法被回收，
 * 从而引发内存泄漏。为了解决这个问题，就产生了RxLifecycle，让RxJava变得有生命周期感知，
 * 使得其能及时取消订阅，避免出现内存泄漏问题。
 */
public class RxHelper {
    /**
     * @param context 必须为Activity 或 Fragment
     * @param <T>     返回数据对象
     * @return
     */
    public static <T> io.reactivex.rxjava3.core.ObservableTransformer<T, T> observableIO2Main(final Context context) {
        return new io.reactivex.rxjava3.core.ObservableTransformer<T, T>() {
            @Override
            public io.reactivex.rxjava3.core.ObservableSource<T> apply(Observable<T> upstream) {
                Observable<T> observable = upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
                return composeContext(context, observable);
            }
        };
    }

    public static <T> io.reactivex.rxjava3.core.ObservableTransformer<T, T> observableIO2Main(final RxFragment fragment) {
        return upstream -> upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).compose(fragment.<T>bindToLifecycle());
    }

    public static <T> io.reactivex.rxjava3.core.FlowableTransformer<T, T> flowableIO2Main() {
        return new FlowableTransformer<T, T>() {
            @Override
            public Publisher<T> apply(Flowable<T> upstream) {
                return upstream
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    private static <T> ObservableSource<T> composeContext(Context context, Observable<T> observable) {
        if (context instanceof RxActivity) {
            return observable.compose(((RxActivity) context).bindUntilEvent(ActivityEvent.DESTROY));
        } else if (context instanceof RxFragmentActivity) {
            return observable.compose(((RxFragmentActivity) context).bindUntilEvent(ActivityEvent.DESTROY));
        } else if (context instanceof RxAppCompatActivity) {
            return observable.compose(((RxAppCompatActivity) context).bindUntilEvent(ActivityEvent.DESTROY));
        } else {
            return observable;
        }
    }
}
