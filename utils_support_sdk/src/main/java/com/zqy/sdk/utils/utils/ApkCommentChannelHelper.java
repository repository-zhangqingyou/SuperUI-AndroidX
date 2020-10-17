package com.zqy.sdk.utils.utils;

import android.text.TextUtils;

import com.zqy.sdk.utils.utils.code.Md5;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 获取 APK Comment中渠道号的辅助类
 * Created by wanggang on 2016/4/6
 * E-Mail: wanggang@6lapp.com
 */
public final class ApkCommentChannelHelper {
    private static final String ENCRYPT_KEY = "K9R*F#64K@1O8GF!U6%";
    private static final String ENCRYPT_SEPARATOR = "_";

    private static final String EMPTY_STRING = "";
    private static String sCachedChannel;

    public static String getChannel(final Object context) {
        return getChannel(context, EMPTY_STRING);
    }

    public static String getChannel(final Object context, final String defaultValue) {
        if (sCachedChannel == null) {
            synchronized (ApkCommentChannelHelper.class) {
                if (sCachedChannel == null) {
                    sCachedChannel = getChannelInternal(context, defaultValue);
                }
            }
        }

        return sCachedChannel;
    }

    private static String getChannelInternal(final Object context, final String defaultValue) {
        String channel;
        try {
            final String sourceDir = getSourceDir(context);
            channel = verifyAndGetChannel(extractZipComment(new File(sourceDir)));
        } catch (Exception e) {
            channel = null;
        }

        return channel == null ? defaultValue : channel;
    }

    /**
     * 校验渠道
     *
     * @return
     */
    private static String verifyAndGetChannel(String channel) throws Exception {
        String finalChannel;
        if (!TextUtils.isEmpty(channel) && channel.contains(ENCRYPT_SEPARATOR)) {
            int lastIndexSeparator = channel.lastIndexOf(ENCRYPT_SEPARATOR);
            String originalChannel = channel.substring(0, lastIndexSeparator);
            String channelFlag = channel.substring(lastIndexSeparator + ENCRYPT_SEPARATOR.length()).trim();
            int lastIndexSeparatorMd5 = channelFlag.lastIndexOf("&&");
            String channelMD5 = channelFlag.substring(0, lastIndexSeparatorMd5);
            finalChannel = Md5.md5(originalChannel + ENCRYPT_KEY).equals(channelMD5) ? originalChannel : null;
        } else {
            finalChannel = null;
        }

        return finalChannel;
    }

    private static String getSourceDir(final Object context)
            throws ClassNotFoundException, IllegalAccessException, InvocationTargetException, NoSuchFieldException, NoSuchMethodException {
        final Class<?> contextClass = Class.forName("android.content.Context");
        final Class<?> applicationInfoClass = Class.forName("android.content.pm.ApplicationInfo");
        final Method getApplicationInfoMethod = contextClass.getMethod("getApplicationInfo");
        final Object appInfo = getApplicationInfoMethod.invoke(context);
        // try ApplicationInfo.publicSourceDir
        final Field publicSourceDirField = applicationInfoClass.getField("publicSourceDir");
        String sourceDir = (String) publicSourceDirField.get(appInfo);
        if (sourceDir == null) {
            // try ApplicationInfo.sourceDir
            final Field sourceDirField = applicationInfoClass.getField("sourceDir");
            sourceDir = (String) sourceDirField.get(appInfo);
        }
        if (sourceDir == null) {
            // try Context.getPackageCodePath()
            final Method getPackageCodePathMethod = contextClass.getMethod("getPackageCodePath");
            sourceDir = (String) getPackageCodePathMethod.invoke(context);
        }
        return sourceDir;
    }

    public static String extractZipComment(File file) {
        String retStr = null;
        try {
            int fileLen = (int) file.length();

            FileInputStream in = new FileInputStream(file);

            /* The whole ZIP comment (including the magic byte sequence)
             * MUST fit in the buffer
             * otherwise, the comment will not be recognized correctly
             *
             * You can safely increase the buffer size if you like
             */
            byte[] buffer = new byte[Math.min(fileLen, 8192)];
            int len;

            in.skip(fileLen - buffer.length);

            if ((len = in.read(buffer)) > 0) {
                retStr = getZipCommentFromBuffer(buffer, len);
            }

            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retStr;
    }

    private static String getZipCommentFromBuffer(byte[] buffer, int len) {
        byte[] magicDirEnd = {0x50, 0x4b, 0x05, 0x06};
        int buffLen = Math.min(buffer.length, len);
        // Check the buffer from the end
        for (int i = buffLen - magicDirEnd.length - 22; i >= 0; i--) {
            boolean isMagicStart = true;
            for (int k = 0; k < magicDirEnd.length; k++) {
                if (buffer[i + k] != magicDirEnd[k]) {
                    isMagicStart = false;
                    break;
                }
            }
            if (isMagicStart) {
                // Magic Start found!
                int commentLen = buffer[i + 20] + buffer[i + 21] * 256;
                int realLen = buffLen - i - 22;
                System.out.println("ZIP comment found at buffer position " + (i + 22) + " with len=" + commentLen + ", good!");
                if (commentLen != realLen) {
                    System.out.println("WARNING! ZIP comment size mismatch: directory says len is " +
                            commentLen + ", but file ends after " + realLen + " bytes!");
                }
                return new String(buffer, i + 22, Math.min(commentLen, realLen));
            }
        }
        System.out.println("ERROR! ZIP comment NOT found!");
        return null;
    }
}