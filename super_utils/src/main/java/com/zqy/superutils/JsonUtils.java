package com.zqy.superutils;

import android.annotation.SuppressLint;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonSyntaxException;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class JsonUtils {
    public final static String defaultPattern = "yyyy-MM-dd HH:mm:ss";//默认

    /**
     * 将对象转换成json字符串。
     * <p>Title: pojoToJson</p>
     * <p>Description: </p>
     *
     * @param data
     * @return
     */
    public static String objectToJson(Object data, String pattern) {
        return getGson(pattern).toJson(data);
    }

    /**
     * 将对象转换成json字符串。
     * <p>Title: pojoToJson</p>
     * <p>Description: </p>
     *
     * @param data
     * @return
     */
    public static String objectToJson(Object data) {
        return objectToJson(data, defaultPattern);
    }

    /**
     * 将json结果集转化为对象
     *
     * @param jsonData json数据
     * @param beanType 对象中的object类型
     * @return
     */
    public static <T> T jsonToObject(String jsonData, Class<T> beanType, String pattern) {
        try {
            return getGson(pattern).fromJson(jsonData, beanType);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            Log.d("JsonUtils", "json数据格式错误:" + e.getMessage());
        }
        return null;
    }

    /**
     * 将json结果集转化为对象
     *
     * @param jsonData json数据
     * @param beanType 对象中的object类型
     * @return
     */
    public static <T> T jsonToObject(String jsonData, Class<T> beanType) {
        return jsonToObject(jsonData, beanType, defaultPattern);
    }

    /**
     * 将json数据转换成pojo对象list
     * <p>Title: jsonToList</p>
     * <p>Description: </p>
     *
     * @param jsonData
     * @param beanType
     * @return
     */
    public static <T> List<T> jsonToList(String jsonData, Class<T> beanType, String pattern) {
        Type type = new ParameterizedTypeImpl(beanType);
        return getGson(pattern).fromJson(jsonData, type);
    }

    /**
     * 将json数据转换成pojo对象list
     * <p>Title: jsonToList</p>
     * <p>Description: </p>
     *
     * @param jsonData
     * @param beanType
     * @return
     */
    public static <T> List<T> jsonToList(String jsonData, Class<T> beanType) {
        Type type = new ParameterizedTypeImpl(beanType);
        return getGson(defaultPattern).fromJson(jsonData, type);
    }


    private static Gson getGson(final String pattern) {
        return new GsonBuilder()
                //Gson注册序列化适配器(date转string)
                .registerTypeAdapter(Date.class, new JsonSerializer<Date>() {
                    @Override
                    @SuppressLint("SimpleDateFormat")
                    public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
                        SimpleDateFormat format = new SimpleDateFormat(pattern);
                        String dfString = format.format(src);
                        return new JsonPrimitive(dfString);
                    }
                })
                //Gson注册反序列化适配器(string转date)
                .registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
                    @Override
                    @SuppressLint("SimpleDateFormat")
                    public Date deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                        String dateStr = json.getAsJsonPrimitive().getAsString();

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                        Date date = null;
                        try {
                            date = simpleDateFormat.parse(dateStr);
                        } catch (ParseException e) {
                            e.printStackTrace();
                           // Log.d("JsonUtils", "json数据格式错误:" + e.getMessage());
                        }
                        return date;
                    }
                })
                // .serializeNulls()
                .create();
    }


}
