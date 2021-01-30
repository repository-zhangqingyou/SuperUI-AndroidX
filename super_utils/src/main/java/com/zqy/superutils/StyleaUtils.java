package com.zqy.superutils;

import android.content.Context;

import com.zqy.superutils.manager.SuperUtilsManage;

import java.lang.reflect.Field;

/**
 * 作者: zhangqingyou
 * 时间: 2020/9/12 13:26
 * 描述: 通过name获取Styleable
 */
public class StyleaUtils {
//
//    public static int[] getStyleableArryId(String styleableName) {
//        Context mContext = UtilsManage.getApplication();
//        try {
//            Class<?> loadClass = mContext.getClassLoader().loadClass(mContext.getPackageName() + ".R");
//            Class<?>[] classes = loadClass.getClasses();
//            for (int i = 0; i < classes.length; i++) {
//                Class<?> resClass = classes[i];
////            Log.e("399", resClass.getName());
//                if (resClass.getName().equals(mContext.getPackageName() + ".R$styleable")) {
////                resClass.getDeclaredField("DayPickerView");
//                    Field[] fields = resClass.getFields();
//                    for (int j = 0; j < fields.length; j++) {
////                    Log.e("399", fields[j].getName());
//                        if (fields[j].getName().equals(styleableName)) {
//                            int[] styleable = (int[]) fields[j].get(null);
//                            //Log.e("399", styleable+"");
//                            return styleable;
//                        }
//                    }
//                }
//            }
//
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        return null;
//    }
    //
//    public static int getStyleableId(String styleableName) {
//        Context mContext = UtilsManage.getApplication();
//        try {
//            Class<?> loadClass = mContext.getClassLoader().loadClass(mContext.getPackageName() + ".R");
//            Class<?>[] classes = loadClass.getClasses();
//            for (int i = 0; i < classes.length; i++) {
//                if (classes[i].getName().equals(mContext.getPackageName() + ".R$styleable")) {
//                    Field field = classes[i].getField(styleableName);
//                    int attrId = field.getInt(null);
//                    return attrId;
//                }
//            }
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        return 0;
//    }

    public static int[] getStyleableArryId(String packageName, String name) {
        //com.zqy.baseui
        try {
            Field[] fields = Class.forName(packageName + ".R$styleable").getFields();//.与$ difference,$表示R的子类
            for (Field field : fields) {
                if (field.getName().equals(name)) {
                    int ret[] = (int[]) field.get(null);
                    return ret;
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int[] getStyleableArryId(String name) {
        Context mContext = SuperUtilsManage.getApplication();
        return getStyleableArryId(mContext.getPackageName(), name);

    }

    /**
     * 遍历R类得到styleable数组资源下的子资源，1.先找到R类下的styleable子类，2.遍历styleable类获得字段值
     *
     * @param styleableName
     * @param styleableFieldName
     * @return
     */
    public static int getStyleableFieldId(String packageName, String styleableName, String styleableFieldName) {
        String className = packageName + ".R";
        String type = "styleable";
        String name = styleableName + "_" + styleableFieldName;
        try {
            Class<?> cla = Class.forName(className);
            for (Class<?> childClass : cla.getClasses()) {
                String simpleName = childClass.getSimpleName();
                if (simpleName.equals(type)) {
                    for (Field field : childClass.getFields()) {
                        String fieldName = field.getName();
                        if (fieldName.equals(name)) {
                            return (int) field.get(null);
                        }
                    }
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 遍历R类得到styleable数组资源下的子资源，1.先找到R类下的styleable子类，2.遍历styleable类获得字段值
     *
     * @param styleableName
     * @param styleableFieldName
     * @return
     */
    public static int getStyleableFieldId(String styleableName, String styleableFieldName) {
        Context context = SuperUtilsManage.getApplication();
        return getStyleableFieldId(context.getPackageName(), styleableName, styleableFieldName);
    }


    public static int getAttrId(String attrName) {
        Context mContext = SuperUtilsManage.getApplication();
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
