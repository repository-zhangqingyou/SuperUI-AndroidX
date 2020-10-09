package com.zqy.sdk.utils;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.zqy.sdk.UtilsManage;
import com.zqy.sdk.glide.DrawableRequestBuilder;
import com.zqy.sdk.glide.Glide;
import com.zqy.sdk.glide.Priority;
import com.zqy.sdk.glide.signature.StringSignature;

/**
 * 加载图片工具类
 * Author: zhangqingyou
 * Date: 2020/4/7
 * Des:
 */
public class GlideUtil {

    /**
     * @param imageUrl  图片地址 加载
     * @param imageView 图片控件
     */
    public static void loadImg(ImageView imageView, Object imageUrl) {
        loadImg(imageView, imageUrl, true);
    }

    /**
     * @param imageUrl      图片地址
     * @param imageView     图片控件
     * @param isplaceholder 是否加载占位图
     */
    public static void loadImg(ImageView imageView, Object imageUrl, boolean isplaceholder) {
        loadImg(imageView, imageUrl, isplaceholder, new ColorDrawable(Color.parseColor("#F4F5F7")), new ColorDrawable(Color.parseColor("#E8E8E8")));
    }

    /**
     * @param imageUrl      图片地址
     * @param imageView     图片控件
     * @param isplaceholder 是否加载占位图
     * @param loadImg       加载中占位图
     * @param failureImg    加载失败占位图
     */
    public static void loadImg(ImageView imageView, Object imageUrl, boolean isplaceholder, Drawable loadImg, Drawable failureImg) {
        DrawableRequestBuilder<Object> builder = Glide.with(UtilsManage.getApplication())
                .load(imageUrl)
                .crossFade()//或者使用 dontAnimate() 关闭动画
                .fitCenter()//比例填充
                .priority(Priority.HIGH);//优先加载

        //设置占位图和加载错误图
        if (isplaceholder) {
            builder.placeholder(loadImg);
            builder.error(failureImg);
        }

        /**
         .signature(new StringSignature("01"))//增加签名
         这个api是增加签名，地址不变，改变这个签名参数也会不读取缓存重新请求。
         我就是用这个方法，传递url不变也可以重新请求，不读取缓存。这个参数可以绑定版本号，每次更新重新获取，或者请求后台
         强大的Glide
         */
        builder.signature(new StringSignature("1.0"));
        builder.into(imageView);
    }

}
