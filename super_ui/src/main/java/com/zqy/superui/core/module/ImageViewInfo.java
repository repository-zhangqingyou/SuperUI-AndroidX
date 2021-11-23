package com.zqy.superui.core.module;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.Parcel;
import android.view.View;

import androidx.annotation.Nullable;

import com.xuexiang.xui.widget.imageview.preview.enitity.IPreviewInfo;

import java.io.ByteArrayOutputStream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 作者: zhangqingyou
 * 时间: 2021/3/11
 * 描述:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageViewInfo implements IPreviewInfo {
    private Object img;  //图片地址
    private byte[] bitmap;  //
    private Rect mBounds; // 记录坐标
    private String mVideoUrl;

    private String mDescription = "描述信息";

    public ImageViewInfo(Object img, Rect mBounds) {
        this.img = img;
        this.mBounds = mBounds;
    }

    public ImageViewInfo(Object img, Rect mBounds, String mVideoUrl) {
        this.img = img;
        this.mBounds = mBounds;
        this.mVideoUrl = mVideoUrl;
    }

    public ImageViewInfo(Object img, View view) {
        this.img = img;
        Rect bounds = new Rect();
        view.getGlobalVisibleRect(bounds);//获取坐标
        this.mBounds = bounds;
    }

    public ImageViewInfo(Object img, View view, String mVideoUrl) {
        this.img = img;
        this.mVideoUrl = mVideoUrl;
        Rect bounds = new Rect();
        view.getGlobalVisibleRect(bounds);//获取坐标
        this.mBounds = bounds;
    }


    @Override
    public String getUrl() {
        if (this.img instanceof String)
            return (String) this.img;
        return null;
    }

    public Bitmap getImgBitmap() {
        return BitmapFactory.decodeByteArray(this.bitmap, 0, this.bitmap.length);//从字节数组解码位图 ;
    }

    public void setBitmap(Bitmap bitmap) {
        //实例化字节数组输出流
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, baos);//压缩位图
        this.bitmap = baos.toByteArray();//创建分配字节数组
    }

    public Object getImg() {
        return this.img;
    }

    @Override
    public Rect getBounds() {
        return mBounds;
    }

    @Nullable
    @Override
    public String getVideoUrl() {
        return mVideoUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getUrl());
        dest.writeParcelable(getBounds(), flags);
        dest.writeString("描述信息");
        dest.writeString(getVideoUrl());
    }

    protected ImageViewInfo(Parcel in) {
        img = in.readString();
        mBounds = in.readParcelable(Rect.class.getClassLoader());
        mDescription = in.readString();
        mVideoUrl = in.readString();
    }

    public static final Creator<ImageViewInfo> CREATOR = new Creator<ImageViewInfo>() {
        @Override
        public ImageViewInfo createFromParcel(Parcel source) {
            return new ImageViewInfo(source);
        }

        @Override
        public ImageViewInfo[] newArray(int size) {
            return new ImageViewInfo[size];
        }
    };
}
