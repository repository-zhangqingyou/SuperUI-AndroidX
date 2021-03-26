package com.zqy.supernet.help;


import androidx.annotation.NonNull;

import com.zqy.supernet.SuperNetManager;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

//import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Retrofit封装
 */
public class RetrofitUtils {
    private static RetrofitUtils mInstance;
    private Retrofit retrofit;
    // private S apiService;

    private RetrofitUtils() {
    }

    /**
     * 单例模式
     */
    public static RetrofitUtils getInstance() {
        if (mInstance == null) {
            synchronized (RetrofitUtils.class) {
                if (mInstance == null) {
                    mInstance = new RetrofitUtils();
                }
            }
        }
        return mInstance;
    }


//    public <S> S getApiService(Class<S> service) {
//        // 初始化Retrofit
//        if (apiService == null) apiService = getRetrofit().create(service);
//        return apiService;
//
//        // return getRetrofit().create(service);
//    }

    /**
     * 初始化Retrofit
     */
    @NonNull
    public Retrofit getRetrofit() {
        if (retrofit == null) new Retrofit.Builder()
                .baseUrl(SuperNetManager.getAskConfigure().getBaseUrl())
                .client(getOkHttpClient())
                //添加GSON解析：返回数据转换成GSON类型
                .addCallAdapterFactory(RxJava3CallAdapterFactory.createSynchronous())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;

    }

    /**
     * 初始化okhttp
     */
    @NonNull
    private OkHttpClient getOkHttpClient() {
        return new OkHttpClient().newBuilder()
                .readTimeout(SuperNetManager.getAskConfigure().getTimeout(), TimeUnit.SECONDS)//设置读取超时时间
                .connectTimeout(SuperNetManager.getAskConfigure().getTimeout(), TimeUnit.SECONDS)//设置请求超时时间
                .writeTimeout(SuperNetManager.getAskConfigure().getTimeout(), TimeUnit.SECONDS)//设置写入超时时间
                .addInterceptor(new LogInterceptor())//添加打印拦截器
                .retryOnConnectionFailure(true)//设置出现错误进行重新连接。
                .build();
    }


}

