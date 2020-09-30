package com.zqy.sdk.utils;


import com.zqy.sdk.UtilsManage;

public class ResourcesUtil {

    public static int getLayoutId(String paramString) {
        return UtilsManage.getApplication().getResources().getIdentifier(paramString, "layout", UtilsManage.getApplication().getPackageName());
    }

    public static int getStringId(String paramString) {
        return UtilsManage.getApplication().getResources().getIdentifier(paramString, "string", UtilsManage.getApplication().getPackageName());
    }

    public static int getDrawableId(String paramString) {
        return UtilsManage.getApplication().getResources().getIdentifier(paramString, "drawable", UtilsManage.getApplication().getPackageName());
    }

    public static int getMipmap(String paramString) {
        return UtilsManage.getApplication().getResources().getIdentifier(paramString, "mipmap", UtilsManage.getApplication().getPackageName());
    }

    public static int getStyleId(String paramString) {
        return UtilsManage.getApplication().getResources().getIdentifier(paramString, "style", UtilsManage.getApplication().getPackageName());
    }

    public static int getStyleableId(String paramString) {
        return UtilsManage.getApplication().getResources().getIdentifier(paramString, "styleable", UtilsManage.getApplication().getPackageName());
    }

    public static int getId(String paramString) {
        return UtilsManage.getApplication().getResources().getIdentifier(paramString, "id", UtilsManage.getApplication().getPackageName());
    }

    public static int getColorId(String paramString) {
        return UtilsManage.getApplication().getResources().getIdentifier(paramString, "color", UtilsManage.getApplication().getPackageName());
    }

    public static int getRawId(String paramString) {
        return UtilsManage.getApplication().getResources().getIdentifier(paramString, "raw", UtilsManage.getApplication().getPackageName());
    }

    public static int getAnimId(String paramString) {
        return UtilsManage.getApplication().getResources().getIdentifier(paramString, "anim", UtilsManage.getApplication().getPackageName());
    }

    public static int getDimen(String paramString) {
        return UtilsManage.getApplication().getResources().getIdentifier(paramString, "dimen", UtilsManage.getApplication().getPackageName());
    }


    public static int getAttr(String paramString) {
        return UtilsManage.getApplication().getResources().getIdentifier(paramString, "attr", UtilsManage.getApplication().getPackageName());
    }

    public static int getBool(String paramString) {
        return UtilsManage.getApplication().getResources().getIdentifier(paramString, "bool", UtilsManage.getApplication().getPackageName());
    }

    public static int getInteger(String paramString) {
        return UtilsManage.getApplication().getResources().getIdentifier(paramString, "integer", UtilsManage.getApplication().getPackageName());
    }

    public static int getInterpolator(String paramString) {
        return UtilsManage.getApplication().getResources().getIdentifier(paramString, "interpolator", UtilsManage.getApplication().getPackageName());
    }

    public static int getXml(String paramString) {
        return UtilsManage.getApplication().getResources().getIdentifier(paramString, "xml", UtilsManage.getApplication().getPackageName());
    }

    public static int getPlurals(String paramString) {
        return UtilsManage.getApplication().getResources().getIdentifier(paramString, "plurals", UtilsManage.getApplication().getPackageName());
    }

    public static int getAnimator(String paramString) {
        return UtilsManage.getApplication().getResources().getIdentifier(paramString, "animator", UtilsManage.getApplication().getPackageName());
    }
}