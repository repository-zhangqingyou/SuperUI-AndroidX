package com.zqy.sdk.utils.utils.code;

import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * 作者: zhangqingyou
 * 时间: 2020/6/3 9:03
 * 描述:
 */
public class URLCode {

    public static String toURLEncoded(String paramString) {
        if (paramString == null || paramString.equals("")) {
            return "";
        }

        try {
            String str = new String(paramString.getBytes(), "UTF-8");
            str = URLEncoder.encode(str, "UTF-8");
            return str;
        } catch (Exception localException) {
        }
        return "";

    }


    public static String toURLDecoded(String paramString) {
        if (paramString == null || paramString.equals("")) {
            return "";
        }

        try {
            String str = new String(paramString.getBytes(), "UTF-8");
            str = URLDecoder.decode(str, "UTF-8");
            return str;
        } catch (Exception localException) {
        }

        return "";
    }

}
