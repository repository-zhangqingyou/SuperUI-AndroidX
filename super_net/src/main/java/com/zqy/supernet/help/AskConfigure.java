package com.zqy.supernet.help;

import okhttp3.Interceptor;

/**
 * 作者: zhangqingyou
 * 时间: 2021/3/26
 * 描述: 请求框架配置参数
 */
public class AskConfigure {
    private int timeout;//请求超时时间（单位秒）
    private Interceptor interceptor;//可添加公共请求参数的拦截器

    public AskConfigure() {
    }

    public AskConfigure(int timeout, Interceptor interceptor) {
        this.timeout = timeout;
        this.interceptor = interceptor;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }


    public Interceptor getInterceptor() {
        return interceptor;
    }

    public void setInterceptor(Interceptor interceptor) {
        this.interceptor = interceptor;
    }
}
