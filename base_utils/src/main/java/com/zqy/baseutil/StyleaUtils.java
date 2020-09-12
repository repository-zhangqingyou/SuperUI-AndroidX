package com.zqy.baseutil;

import android.content.Context;

import java.lang.reflect.Field;

/**
 * 作者: zhangqingyou
 * 时间: 2020/9/12 13:26
 * 描述:
 */
public class StyleaUtils {

    public static int[] getStyleableArryId(String styleableName) {
        Context mContext = UtilsManage.getApplication();
        try {
            Class<?> loadClass = mContext.getClassLoader().loadClass(mContext.getPackageName() + ".R");
            Class<?>[] classes = loadClass.getClasses();
            for (int i = 0; i < classes.length; i++) {
                Class<?> resClass = classes[i];
//            Log.e("399", resClass.getName());
                if (resClass.getName().equals(mContext.getPackageName() + ".R$styleable")) {
//                resClass.getDeclaredField("DayPickerView");
                    Field[] fields = resClass.getFields();
                    for (int j = 0; j < fields.length; j++) {
//                    Log.e("399", fields[j].getName());
                        if (fields[j].getName().equals(styleableName)) {
                            int[] styleable = (int[]) fields[j].get(null);
                            //Log.e("399", styleable+"");
                            return styleable;
                        }
                    }
                }
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }


    public static int getStyleableId(String styleableName) {
        Context mContext = UtilsManage.getApplication();
        try {
            Class<?> loadClass = mContext.getClassLoader().loadClass(mContext.getPackageName() + ".R");
            Class<?>[] classes = loadClass.getClasses();
            for (int i = 0; i < classes.length; i++) {
                if (classes[i].getName().equals(mContext.getPackageName() + ".R$styleable")) {
                    Field field = classes[i].getField(styleableName);
                    int attrId = field.getInt(null);
                    return attrId;
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return 0;
    }


    public static int getAttrId(String attrName) {
        Context mContext = UtilsManage.getApplication();
        try {
            Class<?> loadClass = mContext.getClassLoader().loadClass(mContext.getPackageName() + ".R");
            Class<?>[] classes = loadClass.getClasses();
            for (int i = 0; i < classes.length; i++) {
                if (classes[i].getName().equals(mContext.getPackageName() + ".R$attr")) {
                    Field field = classes[i].getField(attrName);
                    int attrId = field.getInt(null);
                    return attrId;
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return 0;
    }
}
