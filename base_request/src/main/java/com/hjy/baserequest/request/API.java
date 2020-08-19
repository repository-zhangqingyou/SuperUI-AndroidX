package com.hjy.baserequest.request;


/**
 * Author: zhangqingyou
 * Date: 2020/4/7
 * Des:
 */
public class API {
    /**
     * 统一协议
     */
    public static final String SYSTEM_URL_DEFAULT = "http://192.168.5.108:8080";//app统一协议


    /**
     * 系统
     */
    public static final String test = SYSTEM_URL_DEFAULT + "/test/test";//获取公告
    public static final String notice = SYSTEM_URL_DEFAULT + "/xxx/xxx/xxxxx";//获取公告
    public static final String messagePush = SYSTEM_URL_DEFAULT + "/xxx/xxx/xxxxx";//消息推送
    public static final String deviceInformation = SYSTEM_URL_DEFAULT + "/xxx/xxx.do";//搜集设备信息
    public static final String checkFile = SYSTEM_URL_DEFAULT + "/upload/xxx/checkFile";//检查服务器是否有该图片
//    public static final String uploadImg = SYSTEM_URL_DEFAULT + "/upload/xxx/upload_dos";//传图接口

}
