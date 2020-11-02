package com.zqy.sutils;


public class ResourcesUtil {

    public static int getLayoutId(String paramString) {
        return SuperUtilsManage.getApplication().getResources().getIdentifier(paramString, "layout", SuperUtilsManage.getApplication().getPackageName());
    }

    public static int getStringId(String paramString) {
        return SuperUtilsManage.getApplication().getResources().getIdentifier(paramString, "string", SuperUtilsManage.getApplication().getPackageName());
    }

    public static int getDrawableId(String paramString) {
        return SuperUtilsManage.getApplication().getResources().getIdentifier(paramString, "drawable", SuperUtilsManage.getApplication().getPackageName());
    }

    public static int getMipmap(String paramString) {
        return SuperUtilsManage.getApplication().getResources().getIdentifier(paramString, "mipmap", SuperUtilsManage.getApplication().getPackageName());
    }

    public static int getStyleId(String paramString) {
        return SuperUtilsManage.getApplication().getResources().getIdentifier(paramString, "style", SuperUtilsManage.getApplication().getPackageName());
    }

    public static int getStyleableId(String paramString) {
        return SuperUtilsManage.getApplication().getResources().getIdentifier(paramString, "styleable", SuperUtilsManage.getApplication().getPackageName());
    }

    public static int getId(String paramString) {
        return SuperUtilsManage.getApplication().getResources().getIdentifier(paramString, "id", SuperUtilsManage.getApplication().getPackageName());
    }

    public static int getColorId(String paramString) {
        return SuperUtilsManage.getApplication().getResources().getIdentifier(paramString, "color", SuperUtilsManage.getApplication().getPackageName());
    }

    public static int getRawId(String paramString) {
        return SuperUtilsManage.getApplication().getResources().getIdentifier(paramString, "raw", SuperUtilsManage.getApplication().getPackageName());
    }

    public static int getAnimId(String paramString) {
        return SuperUtilsManage.getApplication().getResources().getIdentifier(paramString, "anim", SuperUtilsManage.getApplication().getPackageName());
    }

    public static int getDimen(String paramString) {
        return SuperUtilsManage.getApplication().getResources().getIdentifier(paramString, "dimen", SuperUtilsManage.getApplication().getPackageName());
    }


    public static int getAttr(String paramString) {
        return SuperUtilsManage.getApplication().getResources().getIdentifier(paramString, "attr", SuperUtilsManage.getApplication().getPackageName());
    }

    public static int getBool(String paramString) {
        return SuperUtilsManage.getApplication().getResources().getIdentifier(paramString, "bool", SuperUtilsManage.getApplication().getPackageName());
    }

    public static int getInteger(String paramString) {
        return SuperUtilsManage.getApplication().getResources().getIdentifier(paramString, "integer", SuperUtilsManage.getApplication().getPackageName());
    }

    public static int getInterpolator(String paramString) {
        return SuperUtilsManage.getApplication().getResources().getIdentifier(paramString, "interpolator", SuperUtilsManage.getApplication().getPackageName());
    }

    public static int getXml(String paramString) {
        return SuperUtilsManage.getApplication().getResources().getIdentifier(paramString, "xml", SuperUtilsManage.getApplication().getPackageName());
    }

    public static int getPlurals(String paramString) {
        return SuperUtilsManage.getApplication().getResources().getIdentifier(paramString, "plurals", SuperUtilsManage.getApplication().getPackageName());
    }

    public static int getAnimator(String paramString) {
        return SuperUtilsManage.getApplication().getResources().getIdentifier(paramString, "animator", SuperUtilsManage.getApplication().getPackageName());
    }
}