package com.zqy.googlelib;

import android.app.Application;

public class ResourcesUtil {
    private static Application application;

    public static void init(Application application) {
        ResourcesUtil.application = application;
    }


    public static int getLayoutId(String paramString) {
        return application.getResources().getIdentifier(paramString, "layout", application.getPackageName());
    }

    public static int getStringId(String paramString) {
        return application.getResources().getIdentifier(paramString, "string", application.getPackageName());
    }

    public static int getDrawableId(String paramString) {
        return application.getResources().getIdentifier(paramString, "drawable", application.getPackageName());
    }

    public static int getMipmap(String paramString) {
        return application.getResources().getIdentifier(paramString, "mipmap", application.getPackageName());
    }

    public static int getStyleId(String paramString) {
        return application.getResources().getIdentifier(paramString, "style", application.getPackageName());
    }

    public static int getStyleableId(String paramString) {
        return application.getResources().getIdentifier(paramString, "styleable", application.getPackageName());
    }

    public static int getId(String paramString) {
        return application.getResources().getIdentifier(paramString, "id", application.getPackageName());
    }

    public static int getColorId(String paramString) {
        return application.getResources().getIdentifier(paramString, "color", application.getPackageName());
    }

    public static int getRawId(String paramString) {
        return application.getResources().getIdentifier(paramString, "raw", application.getPackageName());
    }

    public static int getAnimId(String paramString) {
        return application.getResources().getIdentifier(paramString, "anim", application.getPackageName());
    }

    public static int getDimen(String paramString) {
        return application.getResources().getIdentifier(paramString, "dimen", application.getPackageName());
    }


    public static int getAttr(String paramString) {
        return application.getResources().getIdentifier(paramString, "attr", application.getPackageName());
    }

    public static int getBool(String paramString) {
        return application.getResources().getIdentifier(paramString, "bool", application.getPackageName());
    }

    public static int getInteger(String paramString) {
        return application.getResources().getIdentifier(paramString, "integer", application.getPackageName());
    }

    public static int getInterpolator(String paramString) {
        return application.getResources().getIdentifier(paramString, "interpolator", application.getPackageName());
    }

    public static int getXml(String paramString) {
        return application.getResources().getIdentifier(paramString, "xml", application.getPackageName());
    }

    public static int getPlurals(String paramString) {
        return application.getResources().getIdentifier(paramString, "plurals", application.getPackageName());
    }

    public static int getAnimator(String paramString) {
        return application.getResources().getIdentifier(paramString, "animator", application.getPackageName());
    }
}