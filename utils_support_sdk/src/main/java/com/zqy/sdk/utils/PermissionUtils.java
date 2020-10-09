package com.zqy.sdk.utils;

import android.annotation.SuppressLint;
import android.util.Log;

import com.zqy.sdk.utilcode.constant.PermissionConstants;
import com.zqy.sdk.utilcode.util.UtilsTransActivity;


/**
 * 权限检查
 * Author: zhangqingyou
 * Date: 2020/4/7
 * Des: 权限检测工具
 */
public class PermissionUtils {

    public interface PermissionCallback {
        void onGranted();
    }

    private static String text;
    private static PermissionCallback permissionCallback;

    public static void request(String text, @PermissionConstants.Permission final String permission, PermissionCallback permissionCallback) {
        PermissionUtils.text = text;
        PermissionUtils.permissionCallback = permissionCallback;
        String[] permissions = PermissionConstants.getPermissions(permission);
        permission(permissions);
    }

    public static void request(String text, String[] permissions, PermissionCallback permissionCallback) {
        PermissionUtils.text = text;
        PermissionUtils.permissionCallback = permissionCallback;

        permission(permissions);
    }

    private static void permission(String[] permissions) {
        if (!com.zqy.sdk.utilcode.util.PermissionUtils.isGranted(permissions)) {
            @SuppressLint("WrongConstant")
            com.zqy.sdk.utilcode.util.PermissionUtils callback = com.zqy.sdk.utilcode.util.PermissionUtils
                    .permission(permissions)  // : 设置请求权限
                    .rationale(new com.zqy.sdk.utilcode.util.PermissionUtils.OnRationaleListener() {
                        @Override
                        public void rationale(UtilsTransActivity activity, ShouldRequest shouldRequest) {
                            Log.d("PermissionUtils", "设置拒绝权限后再次请求的回调接口");
                            shouldRequest.again(true);
                        }
                    })             // : 设置拒绝权限后再次请求的回调接口
                    .callback(new com.zqy.sdk.utilcode.util.PermissionUtils.SimpleCallback() {
                                  @Override
                                  public void onGranted() {
                                      Log.d("PermissionUtils", "onGranted");
                                      if (PermissionUtils.permissionCallback != null)
                                          PermissionUtils.permissionCallback.onGranted();
                                  }

                                  @Override
                                  public void onDenied() {
                                      Log.d("PermissionUtils", "onDenied");
                                      ToastUtil.toast(PermissionUtils.text);
                                      com.zqy.sdk.utilcode.util.PermissionUtils.launchAppDetailsSettings();

                                  }
                              }

                    );// : 设置回调
            callback.request();  // : 开始请求
        } else {
            if (PermissionUtils.permissionCallback != null)
                PermissionUtils.permissionCallback.onGranted();
        }
    }
}
