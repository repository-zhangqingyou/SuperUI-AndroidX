package com.zqy.sdk.utilcode.util;

import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.RawRes;
import android.support.v4.content.ContextCompat;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.List;

/**
 * <pre>
 *     author: Blankj
 *     blog  : http://blankj.com
 *     time  : 2018/05/07
 *     desc  : utils about resource
 * </pre>
 */
public final class ResourceUtils {

    private static final int BUFFER_SIZE = 8192;

    private ResourceUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * Return the drawable by identifier.
     *
     * @param id The identifier.
     * @return the drawable by identifier
     */
    public static Drawable getDrawable(@DrawableRes int id) {
        return ContextCompat.getDrawable(com.zqy.sdk.utilcode.util.Utils.getApp(), id);
    }

    /**
     * Return the id identifier by name.
     *
     * @param name The name of id.
     * @return the id identifier by name
     */
    public static int getIdByName(String name) {
        return com.zqy.sdk.utilcode.util.Utils.getApp().getResources().getIdentifier(name, "id", com.zqy.sdk.utilcode.util.Utils.getApp().getPackageName());
    }

    /**
     * Return the string identifier by name.
     *
     * @param name The name of string.
     * @return the string identifier by name
     */
    public static int getStringIdByName(String name) {
        return com.zqy.sdk.utilcode.util.Utils.getApp().getResources().getIdentifier(name, "string", com.zqy.sdk.utilcode.util.Utils.getApp().getPackageName());
    }

    /**
     * Return the color identifier by name.
     *
     * @param name The name of color.
     * @return the color identifier by name
     */
    public static int getColorIdByName(String name) {
        return com.zqy.sdk.utilcode.util.Utils.getApp().getResources().getIdentifier(name, "color", com.zqy.sdk.utilcode.util.Utils.getApp().getPackageName());
    }

    /**
     * Return the dimen identifier by name.
     *
     * @param name The name of dimen.
     * @return the dimen identifier by name
     */
    public static int getDimenIdByName(String name) {
        return com.zqy.sdk.utilcode.util.Utils.getApp().getResources().getIdentifier(name, "dimen", com.zqy.sdk.utilcode.util.Utils.getApp().getPackageName());
    }

    /**
     * Return the drawable identifier by name.
     *
     * @param name The name of drawable.
     * @return the drawable identifier by name
     */
    public static int getDrawableIdByName(String name) {
        return com.zqy.sdk.utilcode.util.Utils.getApp().getResources().getIdentifier(name, "drawable", com.zqy.sdk.utilcode.util.Utils.getApp().getPackageName());
    }

    /**
     * Return the mipmap identifier by name.
     *
     * @param name The name of mipmap.
     * @return the mipmap identifier by name
     */
    public static int getMipmapIdByName(String name) {
        return com.zqy.sdk.utilcode.util.Utils.getApp().getResources().getIdentifier(name, "mipmap", com.zqy.sdk.utilcode.util.Utils.getApp().getPackageName());
    }

    /**
     * Return the layout identifier by name.
     *
     * @param name The name of layout.
     * @return the layout identifier by name
     */
    public static int getLayoutIdByName(String name) {
        return com.zqy.sdk.utilcode.util.Utils.getApp().getResources().getIdentifier(name, "layout", com.zqy.sdk.utilcode.util.Utils.getApp().getPackageName());
    }

    /**
     * Return the style identifier by name.
     *
     * @param name The name of style.
     * @return the style identifier by name
     */
    public static int getStyleIdByName(String name) {
        return com.zqy.sdk.utilcode.util.Utils.getApp().getResources().getIdentifier(name, "style", com.zqy.sdk.utilcode.util.Utils.getApp().getPackageName());
    }

    /**
     * Return the anim identifier by name.
     *
     * @param name The name of anim.
     * @return the anim identifier by name
     */
    public static int getAnimIdByName(String name) {
        return com.zqy.sdk.utilcode.util.Utils.getApp().getResources().getIdentifier(name, "anim", com.zqy.sdk.utilcode.util.Utils.getApp().getPackageName());
    }

    /**
     * Return the menu identifier by name.
     *
     * @param name The name of menu.
     * @return the menu identifier by name
     */
    public static int getMenuIdByName(String name) {
        return com.zqy.sdk.utilcode.util.Utils.getApp().getResources().getIdentifier(name, "menu", com.zqy.sdk.utilcode.util.Utils.getApp().getPackageName());
    }

    /**
     * Copy the file from assets.
     *
     * @param assetsFilePath The path of file in assets.
     * @param destFilePath   The path of destination file.
     * @return {@code true}: success<br>{@code false}: fail
     */
    public static boolean copyFileFromAssets(final String assetsFilePath, final String destFilePath) {
        boolean res = true;
        try {
            String[] assets = com.zqy.sdk.utilcode.util.Utils.getApp().getAssets().list(assetsFilePath);
            if (assets != null && assets.length > 0) {
                for (String asset : assets) {
                    res &= copyFileFromAssets(assetsFilePath + "/" + asset, destFilePath + "/" + asset);
                }
            } else {
                res = com.zqy.sdk.utilcode.util.UtilsBridge.writeFileFromIS(
                        destFilePath,
                        com.zqy.sdk.utilcode.util.Utils.getApp().getAssets().open(assetsFilePath)
                );
            }
        } catch (IOException e) {
            e.printStackTrace();
            res = false;
        }
        return res;
    }

    /**
     * Return the content of assets.
     *
     * @param assetsFilePath The path of file in assets.
     * @return the content of assets
     */
    public static String readAssets2String(final String assetsFilePath) {
        return readAssets2String(assetsFilePath, null);
    }

    /**
     * Return the content of assets.
     *
     * @param assetsFilePath The path of file in assets.
     * @param charsetName    The name of charset.
     * @return the content of assets
     */
    public static String readAssets2String(final String assetsFilePath, final String charsetName) {
        try {
            InputStream is = com.zqy.sdk.utilcode.util.Utils.getApp().getAssets().open(assetsFilePath);
            byte[] bytes = com.zqy.sdk.utilcode.util.UtilsBridge.inputStream2Bytes(is);
            if (bytes == null) return "";
            if (com.zqy.sdk.utilcode.util.UtilsBridge.isSpace(charsetName)) {
                return new String(bytes);
            } else {
                try {
                    return new String(bytes, charsetName);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    return "";
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * Return the content of file in assets.
     *
     * @param assetsPath The path of file in assets.
     * @return the content of file in assets
     */
    public static List<String> readAssets2List(final String assetsPath) {
        return readAssets2List(assetsPath, "");
    }

    /**
     * Return the content of file in assets.
     *
     * @param assetsPath  The path of file in assets.
     * @param charsetName The name of charset.
     * @return the content of file in assets
     */
    public static List<String> readAssets2List(final String assetsPath,
                                               final String charsetName) {
        try {
            return com.zqy.sdk.utilcode.util.UtilsBridge.inputStream2Lines(com.zqy.sdk.utilcode.util.Utils.getApp().getResources().getAssets().open(assetsPath), charsetName);
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }


    /**
     * Copy the file from raw.
     *
     * @param resId        The resource id.
     * @param destFilePath The path of destination file.
     * @return {@code true}: success<br>{@code false}: fail
     */
    public static boolean copyFileFromRaw(@RawRes final int resId, final String destFilePath) {
        return com.zqy.sdk.utilcode.util.UtilsBridge.writeFileFromIS(
                destFilePath,
                com.zqy.sdk.utilcode.util.Utils.getApp().getResources().openRawResource(resId)
        );
    }

    /**
     * Return the content of resource in raw.
     *
     * @param resId The resource id.
     * @return the content of resource in raw
     */
    public static String readRaw2String(@RawRes final int resId) {
        return readRaw2String(resId, null);
    }

    /**
     * Return the content of resource in raw.
     *
     * @param resId       The resource id.
     * @param charsetName The name of charset.
     * @return the content of resource in raw
     */
    public static String readRaw2String(@RawRes final int resId, final String charsetName) {
        InputStream is = com.zqy.sdk.utilcode.util.Utils.getApp().getResources().openRawResource(resId);
        byte[] bytes = com.zqy.sdk.utilcode.util.UtilsBridge.inputStream2Bytes(is);
        if (bytes == null) return null;
        if (com.zqy.sdk.utilcode.util.UtilsBridge.isSpace(charsetName)) {
            return new String(bytes);
        } else {
            try {
                return new String(bytes, charsetName);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return "";
            }
        }
    }

    /**
     * Return the content of resource in raw.
     *
     * @param resId The resource id.
     * @return the content of file in assets
     */
    public static List<String> readRaw2List(@RawRes final int resId) {
        return readRaw2List(resId, "");
    }

    /**
     * Return the content of resource in raw.
     *
     * @param resId       The resource id.
     * @param charsetName The name of charset.
     * @return the content of file in assets
     */
    public static List<String> readRaw2List(@RawRes final int resId,
                                            final String charsetName) {
        return com.zqy.sdk.utilcode.util.UtilsBridge.inputStream2Lines(Utils.getApp().getResources().openRawResource(resId), charsetName);
    }
}
