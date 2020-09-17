package com.zqy.googlelib;

import android.app.Application;

public class UtilResources {
    private static Application application;

    public static void init(Application application) {
        UtilResources.application = application;
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

}
