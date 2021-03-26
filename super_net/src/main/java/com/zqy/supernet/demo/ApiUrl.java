package com.zqy.supernet.demo;

import com.zqy.supernet.demo.bean.DemoBean;
import com.zqy.supernet.response.Result;

import java.util.List;
import java.util.Map;

import io.reactivex.rxjava3.core.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * 作者: zhangqingyou
 * 时间: 2021/3/26
 * 描述:
 *
 * @Body 实际上是将类转换成json实体作为请求体来请求网络，一时间没找到可以接受json格式的请求体作为参数
 * @Query或@QueryMap 一般用于Get，用于形如“?a=xxxx&b=xxxx&c=xxxx”；也可用于Query类型的Post请求
 * @Field或@FieldMap 对应POST请求，不过需要结合@FormUrlEncoded来使用 例如：
 * <p>
 * //@POST("/")
 * //@FormUrlEncoded
 * //Call<WeatherBeans> requestWeatherBeans(
 * //            @Field("app") String app,
 * //            @Field("weaid") String weaid,
 * //            @Field("appkey") String appkey,
 * //            @Field("sign") String sign,
 * //            @Field("format") String format);
 * /
 */
public interface ApiUrl {
    /**
     * 有效链接
     */
    @GET(Constans.retrofit)
    Call<Result> getRetrofit();

    @GET(Constans.retrofit)
    Observable<Result<DemoBean>> getDemo();

    @GET(Constans.retrofitList)
    Observable<Result<List<DemoBean>>> getDemoList();


    /**
     * TODO Get请求
     */
    //第一种方式：GET不带参数
    @GET("retrofit.txt")
    Observable<Result<DemoBean>> getUser();

    @GET
    Observable<DemoBean> getUser(@Url String url);

    @GET
    Observable<DemoBean> getUser1(@Url String url); //简洁方式   直接获取所需数据

    //第二种方式：GET带参数
    @GET("api/data/{type}/{count}/{page}")
    Observable<DemoBean> getUser(@Path("type") String type, @Path("count") int count, @Path("page") int page);

    //第三种方式：GET带请求参数：https://api.github.com/users/whatever?client_id=xxxx&client_secret=yyyy
    @GET("users/whatever")
    Observable<DemoBean> getUser(@Query("client_id") String id, @Query("client_secret") String secret);

    @GET("users/whatever")
    Observable<DemoBean> getUser(@QueryMap Map<String, String> info);

    /**
     * TODO POST请求
     */
    //第一种方式：@Body
    @Headers("Accept:application/json")
    @POST("login")
    Observable<DemoBean> postUser(@Body RequestBody body);
    //第二种方式：@Field

    @Headers("Accept:application/json")
    @POST("auth/login")
    @FormUrlEncoded
    Observable<DemoBean> postUser(@Field("username") String username, @Field("password") String password);

    //多个参数
    Observable<DemoBean> postUser(@FieldMap Map<String, String> map);

    /**
     * TODO DELETE
     */
    @DELETE("member_follow_member/{id}")
    Observable<DemoBean> delete(@Header("Authorization") String auth, @Path("id") int id);

    /**
     * TODO PUT
     */
    @PUT("member")
    Observable<DemoBean> put(@HeaderMap Map<String, String> headers,
                             @Query("nickname") String nickname);

    /**
     * TODO 文件上传
     */
    @Multipart
    @POST("upload")
    Observable<ResponseBody> upload(@Part("description") RequestBody description, @Part MultipartBody.Part file);

    //亲测可用
    @Multipart
    @POST("member/avatar")
    Observable<DemoBean> uploadImage(@HeaderMap Map<String, String> headers, @Part MultipartBody.Part file);

    /**
     * 多文件上传
     */
    @Multipart
    @POST("register")
    Observable<ResponseBody> upload(@PartMap Map<String, RequestBody> params, @Part("description") RequestBody description);
    //Observable<ResponseBody> upload(@Part() List<MultipartBody.Part> parts);

    @Multipart
    @POST("member/avatar")
    Observable<DemoBean> uploadImage1(@HeaderMap Map<String, String> headers, @Part List<MultipartBody.Part> file);

    /**
     * 来自https://blog.csdn.net/impure/article/details/79658098
     *
     * @Streaming 这个注解必须添加，否则文件全部写入内存，文件过大会造成内存溢出
     */
    @Streaming
    @GET
    Observable<ResponseBody> download(@Header("RANGE") String start, @Url String url);
}
