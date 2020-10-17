package com.zqy.sdk.utils.virtual;

/**
 * Project Name:EasyProtector
 * Package Name:com.zqy.sdk.virtual
 * Created by lahm on 2018/5/14 下午9:58 .
 */
public interface LibLoader {
    void loadLibrary(String libName) throws UnsatisfiedLinkError, SecurityException;
}
