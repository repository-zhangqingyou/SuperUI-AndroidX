package com.zqy.support.app.config;

/**
 * 平台配置信息
 */
public class Config {
    /**
     * QQ开发者平台
     */
    public static class QQ {
        public static String appId = "101885481";
        public static String appKey = "d4aa2797c3c24df9703b1ad886b0adb9";

    }

    /**
     * 微信开发者后台
     */
    public static class Wechat {
        public static String appId = "wx00d06d72053b151c";
        public static String appSecret = "a875ad8e6317510173fcb9740d94ac57";
    }

    /**
     * 微博开发者后台
     */
    public static class SinaWeibo {
        public static String appKey = "2586249081";
        public static String appSecret = "c974835bca091f213fb9c2afb749fbac";
        public static String redirectUrl = "http://sns.whalecloud.com";

    }

    /**
     * bugly
     */
    public static class BugLy {
        public static String buglyAppId = "eb4d356ac8";
        public static String buglyAppKey = "b55d14c4-fe33-4f07-b285-c9e112158719";
    }

    /**
     * 即时通讯
     */
    public static class IM {
        /**
         * 腾讯云 SDKAppId，需要替换为您自己账号下的 SDKAppId。
         * <p>
         * 进入腾讯云云通信[控制台](https://console.cloud.tencent.com/avc ) 创建应用，即可看到 SDKAppId，
         * 它是腾讯云用于区分客户的唯一标识。
         */
        public static final int SDKAPPID = 1400391844;
        /**
         * 计算签名用的加密密钥，获取步骤如下：
         * <p>
         * step1. 进入腾讯云云通信[控制台](https://console.cloud.tencent.com/avc ) ，如果还没有应用就创建一个，
         * step2. 单击“应用配置”进入基础配置页面，并进一步找到“帐号体系集成”部分。
         * step3. 点击“查看密钥”按钮，就可以看到计算 UserSig 使用的加密的密钥了，请将其拷贝并复制到如下的变量中
         * <p>
         * 注意：该方案仅适用于调试Demo，正式上线前请将 UserSig 计算代码和密钥迁移到您的后台服务器上，以避免加密密钥泄露导致的流量盗用。
         * 文档：https://cloud.tencent.com/document/product/269/32688#Server
         */
        public static final String SECRETKEY = "c35c42193a03e75b895d9bda3aee25e6435a6a05697fdf11c281022f1e7993c6";


    }

    /**
     * 阿里云反馈
     */
    public static class ALiYunFeedBack {
        public static String AppKey = "27764230";
        public static String AppSecret = "edfa13bfd1ba76b18ab9d8553ae1e82e";
    }


}
