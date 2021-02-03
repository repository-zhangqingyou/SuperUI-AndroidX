package com.zqy.superutils;


import com.zqy.superutils.manager.SuperUtilsManager;

public class ResourcesUtil {

    public static int getLayoutId(String paramString) {
        return SuperUtilsManager.getApplication().getResources().getIdentifier(paramString, "layout", SuperUtilsManager.getApplication().getPackageName());
    }

    public static int getStringId(String paramString) {
        return SuperUtilsManager.getApplication().getResources().getIdentifier(paramString, "string", SuperUtilsManager.getApplication().getPackageName());
    }

    public static int getDrawableId(String paramString) {
        return SuperUtilsManager.getApplication().getResources().getIdentifier(paramString, "drawable", SuperUtilsManager.getApplication().getPackageName());
    }

    public static int getMipmap(String paramString) {
        return SuperUtilsManager.getApplication().getResources().getIdentifier(paramString, "mipmap", SuperUtilsManager.getApplication().getPackageName());
    }

    public static int getStyleId(String paramString) {
        return SuperUtilsManager.getApplication().getResources().getIdentifier(paramString, "style", SuperUtilsManager.getApplication().getPackageName());
    }

    public static int getStyleableId(String paramString) {
        return SuperUtilsManager.getApplication().getResources().getIdentifier(paramString, "styleable", SuperUtilsManager.getApplication().getPackageName());
    }

    public static int getId(String paramString) {
        return SuperUtilsManager.getApplication().getResources().getIdentifier(paramString, "id", SuperUtilsManager.getApplication().getPackageName());
    }

    public static int getColorId(String paramString) {
        return SuperUtilsManager.getApplication().getResources().getIdentifier(paramString, "color", SuperUtilsManager.getApplication().getPackageName());
    }

    public static int getRawId(String paramString) {
        return SuperUtilsManager.getApplication().getResources().getIdentifier(paramString, "raw", SuperUtilsManager.getApplication().getPackageName());
    }

    public static int getAnimId(String paramString) {
        return SuperUtilsManager.getApplication().getResources().getIdentifier(paramString, "anim", SuperUtilsManager.getApplication().getPackageName());
    }

    public static int getDimen(String paramString) {
        return SuperUtilsManager.getApplication().getResources().getIdentifier(paramString, "dimen", SuperUtilsManager.getApplication().getPackageName());
    }


    public static int getAttr(String paramString) {
        return SuperUtilsManager.getApplication().getResources().getIdentifier(paramString, "attr", SuperUtilsManager.getApplication().getPackageName());
    }

    public static int getBool(String paramString) {
        return SuperUtilsManager.getApplication().getResources().getIdentifier(paramString, "bool", SuperUtilsManager.getApplication().getPackageName());
    }

    public static int getInteger(String paramString) {
        return SuperUtilsManager.getApplication().getResources().getIdentifier(paramString, "integer", SuperUtilsManager.getApplication().getPackageName());
    }

    public static int getInterpolator(String paramString) {
        return SuperUtilsManager.getApplication().getResources().getIdentifier(paramString, "interpolator", SuperUtilsManager.getApplication().getPackageName());
    }

    public static int getXml(String paramString) {
        return SuperUtilsManager.getApplication().getResources().getIdentifier(paramString, "xml", SuperUtilsManager.getApplication().getPackageName());
    }

    public static int getPlurals(String paramString) {
        return SuperUtilsManager.getApplication().getResources().getIdentifier(paramString, "plurals", SuperUtilsManager.getApplication().getPackageName());
    }

    public static int getAnimator(String paramString) {
        return SuperUtilsManager.getApplication().getResources().getIdentifier(paramString, "animator", SuperUtilsManager.getApplication().getPackageName());
    }
}