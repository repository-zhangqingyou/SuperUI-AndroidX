package com.zqy.superutils.enums;

/**
 * 作者: zhangqingyou
 * 时间: 2020/11/2 23:13
 * 描述:
 */
public enum ByteEnum {
    BYTE(1),

    KB(1024),

    MB(1048576),

    GB(1073741824);

    private int v;//自定义属性

    /**
     * 构造函数，枚举类型只能为私有
     */
    ByteEnum(int v) {
        this.v = v;
    }

    //自定义方法
    public int getV() {
        return v;
    }
}
