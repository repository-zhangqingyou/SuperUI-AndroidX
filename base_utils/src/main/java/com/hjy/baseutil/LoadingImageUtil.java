package com.hjy.baseutil;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.blankj.utilcode.util.ResourceUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import java.io.File;

/**
 * 加载图片工具类
 * Author: zhangqingyou
 * Date: 2020/4/7
 * Des:
 */
public class LoadingImageUtil {

    public static void loadingImag(Object imageUrl, ImageView imageView, boolean isplaceholder, int image_loading_item, int image_failure_item) {
        RequestBuilder<Drawable> objectDrawableRequestBuilder = Glide.with(UtilsManage.getApplication())
                .load(imageUrl);
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.fitCenter();//比例填充
        requestOptions.priority(Priority.HIGH);//优先加载

        /**
         * 第二种方法：
         .signature(new StringSignature("01"))//增加签名
         这个api是增加签名，地址不变，改变这个签名参数也会不读取缓存重新请求。
         我就是用这个方法，传递url不变也可以重新请求，不读取缓存。这个参数可以绑定版本号，每次更新重新获取，或者请求后台
         强大的Glide
         */
//        if (dataBean != null && imageUrl instanceof String) {
//            String imageUrl1 = (String) imageUrl;
//            String imageName = imageUrl1.substring(imageUrl1.lastIndexOf("/") + 1, imageUrl1.length());//截取N中，第0个位置到最后一个f之前的全部字符
//            if (asList.contains(imageName)) {//需要更新的图片
//                requestOptions.signature(new ObjectKey(dataBean.getVersion()));//增加签名
//            }
//        }

        if (isplaceholder)//是否加载占位图
        {

            requestOptions.placeholder(image_loading_item);//设置占位图
            requestOptions.error(image_failure_item);//设置加载错误图片
        }
        objectDrawableRequestBuilder.apply(requestOptions);//加载配置信息
        objectDrawableRequestBuilder.into(imageView);
    }

    public static void loadingImag(Object imageUrl, ImageView imageView, boolean isplaceholder) {
        RequestBuilder<Drawable> objectDrawableRequestBuilder = Glide.with(UtilsManage.getApplication())
                .load(imageUrl);
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.fitCenter();//比例填充
        requestOptions.priority(Priority.HIGH);//优先加载

        /**
         * 第二种方法：
         .signature(new StringSignature("01"))//增加签名
         这个api是增加签名，地址不变，改变这个签名参数也会不读取缓存重新请求。
         我就是用这个方法，传递url不变也可以重新请求，不读取缓存。这个参数可以绑定版本号，每次更新重新获取，或者请求后台
         强大的Glide
         */
//        if (dataBean != null && imageUrl instanceof String) {
//            String imageUrl1 = (String) imageUrl;
//            String imageName = imageUrl1.substring(imageUrl1.lastIndexOf("/") + 1, imageUrl1.length());//截取N中，第0个位置到最后一个f之前的全部字符
//            if (asList.contains(imageName)) {//需要更新的图片
//                requestOptions.signature(new ObjectKey(dataBean.getVersion()));//增加签名
//            }
//        }

        if (isplaceholder)//是否加载占位图
        {
            requestOptions.placeholder(ResourceUtils.getColorIdByName("bui_grayBar"));//设置加载中占位资源
            requestOptions.error(ResourceUtils.getColorIdByName("bui_graySegmentation"));//设置加载错误占位资源
        }
        objectDrawableRequestBuilder.apply(requestOptions);//加载配置信息
        objectDrawableRequestBuilder.into(imageView);
    }

    /**
     * 设置图片圆角角度（不一定有效）
     *
     * @param imageUrl
     * @param imageView
     * @param roundingRadius 设置图片圆角角度
     * @param isplaceholder
     */
    public static void loadingImag(Object imageUrl, ImageView imageView, int roundingRadius, boolean isplaceholder) {

        //设置图片圆角角度
        RoundedCorners roundedCorners = new RoundedCorners(roundingRadius);

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.bitmapTransform(roundedCorners);//
        requestOptions.fitCenter();//比例填充
        requestOptions.priority(Priority.HIGH);//优先加载

        RequestBuilder<Drawable> objectDrawableRequestBuilder = Glide.with(UtilsManage.getApplication())
                .load(imageUrl);

        /**
         * 第二种方法：
         .signature(new StringSignature("01"))//增加签名
         这个api是增加签名，地址不变，改变这个签名参数也会不读取缓存重新请求。
         我就是用这个方法，传递url不变也可以重新请求，不读取缓存。这个参数可以绑定版本号，每次更新重新获取，或者请求后台
         强大的Glide
         */
//        if (dataBean != null && imageUrl instanceof String) {
//            String imageUrl1 = (String) imageUrl;
//            String imageName = imageUrl1.substring(imageUrl1.lastIndexOf("/") + 1, imageUrl1.length());//截取N中，第0个位置到最后一个f之前的全部字符
//            if (asList.contains(imageName)) {//需要更新的图片
//                requestOptions.signature(new ObjectKey(dataBean.getVersion()));//增加签名
//            }
//        }

        if (isplaceholder)//是否加载占位图
        {
            requestOptions.placeholder(ResourceUtils.getColorIdByName("bui_grayBar"));//设置加载中占位资源
            requestOptions.error(ResourceUtils.getColorIdByName("bui_graySegmentation"));//设置加载错误占位资源
        }

        objectDrawableRequestBuilder.apply(requestOptions);//加载配置信息
        objectDrawableRequestBuilder.into(imageView);
    }

}
