package com.zqy.supernet.help;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.zqy.supernet.SuperNetManager;
import com.zqy.supernet.help.factory.GsonRequestBodyConverter;
import com.zqy.supernet.help.factory.GsonResponseBodyConverter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;


/**
 * 作者: zhangqingyou
 * 时间: 2021/3/26
 * 描述: 自定义 json字符串 转 对象
 */
public class CustomGsonConverterFactory extends Converter.Factory {
    /**
     * Create an instance using a default {@link Gson} instance for conversion. Encoding to JSON and
     * decoding from JSON (when no charset is specified by a header) will use UTF-8.
     */
    public static CustomGsonConverterFactory create() {
        return create(new Gson());
    }

    /**
     * Create an instance using {@code gson} for conversion. Encoding to JSON and decoding from JSON
     * (when no charset is specified by a header) will use UTF-8.
     */
    @SuppressWarnings("ConstantConditions") // Guarding public API nullability.
    public static CustomGsonConverterFactory create(Gson gson) {
        if (gson == null) throw new NullPointerException("gson == null");
        return new CustomGsonConverterFactory(gson);
    }

    private final Gson gson;

    private CustomGsonConverterFactory(Gson gson) {
        this.gson = gson;
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(
            Type type, Annotation[] annotations, Retrofit retrofit) {
       // TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        GsonResponseBodyConverter<?> objectGsonResponseBodyConverter = new GsonResponseBodyConverter<>(gson, type);
        String json = objectGsonResponseBodyConverter.getJson();

        if (SuperNetManager.getOnApiListener() != null) {
            SuperNetManager.getOnApiListener().onSuccess(retrofit.baseUrl().url().toString(), json);
        }
        return objectGsonResponseBodyConverter;
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(
            Type type,
            Annotation[] parameterAnnotations,
            Annotation[] methodAnnotations,
            Retrofit retrofit) {
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new GsonRequestBodyConverter<>(gson, adapter);
    }
}

