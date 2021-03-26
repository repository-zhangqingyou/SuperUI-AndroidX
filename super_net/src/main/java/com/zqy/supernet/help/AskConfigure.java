package com.zqy.supernet.help;

/**
 * 作者: zhangqingyou
 * 时间: 2021/3/26
 * 描述: 请求框架配置参数
 */
public class AskConfigure {

    private int timeout;//请求超时时间（单位秒）
    private String baseUrl;//请求地址前缀

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }
}
