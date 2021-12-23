package com.zqy.supernet.help.factory;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * 作者: zhangqingyou
 * 时间: 2021/3/26
 * 描述: 响应转换器
 */
public class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private Gson gson;
    private String json;
    private TypeAdapter<T> adapter;
    private Type type;

    public GsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    public GsonResponseBodyConverter(Gson gson, Type type) {
        this.gson = gson;
        this.type = type;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        json = value.string();
        if (!TextUtils.isEmpty(json)) {
            try {
                return gson.fromJson(json, type);
            } catch (JsonSyntaxException e) {
                e.printStackTrace();
                Log.d("JsonUtils", "json数据格式错误:" + e.getMessage());
            } finally {
                value.close();
            }
        }
        return null;

//        Reader reader = value.charStream();
//        JsonReader jsonReader = gson.newJsonReader(reader);
//
//        try {
//            T result = adapter.read(jsonReader);
//            if (jsonReader.peek() != JsonToken.END_DOCUMENT) {
//                throw new JsonIOException("JSON document was not fully consumed.");
//            }
//            return result;
//        } finally {
//            value.close();
//        }

    }

    public String getJson() {
        return json;
    }
}
