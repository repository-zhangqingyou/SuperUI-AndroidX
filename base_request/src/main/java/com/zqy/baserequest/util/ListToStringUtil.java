package com.zqy.baserequest.util;

import java.util.Arrays;
import java.util.List;

/**
 * Author: zhangqingyou
 * Date: 2020/4/7 16:14
 * Des:
 */
public class ListToStringUtil {


    /**
     * 集合转string
     *
     * @param stringList
     * @return
     */
    public static String listToString(List<String> stringList) {
        return Arrays.toString(stringList.toArray())
                .replace("[", "")
                .replace("]", "")
                .replace(" ", "");
    }
}
