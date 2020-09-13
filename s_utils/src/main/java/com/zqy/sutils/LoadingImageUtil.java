package com.zqy.sutils;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.RequestOptions;

/**
 * 加载图片工具类
 * Author: zhangqingyou
 * Date: 2020/4/7
 * Des:
 */
public class LoadingImageUtil {

    /**
     * @param imageUrl  图片地址
     * @param imageView 图片控件
     */
    public static void loadingImag(ImageView imageView, Object imageUrl) {
        loadingImag(imageView, imageUrl, true);
    }

    /**
     * @param imageUrl      图片地址
     * @param imageView     图片控件
     * @param isplaceholder 是否加载占位图
     */
    public static void loadingImag(ImageView imageView, Object imageUrl, boolean isplaceholder) {
        loadingImag(imageView, imageUrl, isplaceholder, new ColorDrawable(Color.parseColor("F4F5F7")), new ColorDrawable(Color.parseColor("E8E8E8")));
    }

    /**
     * @param imageUrl           图片地址
     * @param imageView          图片控件
     * @param isplaceholder      是否加载占位图
     * @param image_loading_item 加载中占位图
     * @param image_failure_item 加载失败占位图
     */
    public static void loadingImag(ImageView imageView, Object imageUrl, boolean isplaceholder, Drawable image_loading_item, Drawable image_failure_item) {
        RequestBuilder<Drawable> objectDrawableRequestBuilder = Glide.with(UtilsManage.getApplication())
                .load(imageUrl);
        RequestOptions requestOptions = new RequestOptions();
        // requestOptions.bitmapTransform(new RoundedCorners(roundingRadius));//设置图片圆角
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

}
