package com.zqy.supernet.help;


import androidx.annotation.NonNull;

import com.zqy.supernet.SuperNetManager;
import com.zqy.supernet.help.interceptor.LogInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;

/**
 * Retrofit封装
 */
public class RetrofitHelp {

    /**
     * 初始化Retrofit
     */
    @NonNull
    public Retrofit getRetrofit(String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(getOkHttpClient())
                //添加GSON解析：返回数据转换成GSON类型
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(CustomGsonConverterFactory.create())
                .build();

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
                .addInterceptor(SuperNetManager.getAskConfigure().getInterceptor() != null ? SuperNetManager.getAskConfigure().getInterceptor() : new LogInterceptor())//添加打印拦截器
                .retryOnConnectionFailure(true)//设置出现错误进行重新连接。
                .build();
    }


}

