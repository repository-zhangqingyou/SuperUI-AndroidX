package com.zqy.superui.core.module;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;

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
public class ImageViewInfo implements Parcelable {
    private String urlImg;  //网络图片地址
    private String urlVideo;//网络视频地址
    private Bitmap bitmap;//图片Bitmap
    private Rect bounds; // 记录坐标
    private String mDescription = "描述信息";

    public ImageViewInfo(String urlImg, Rect bounds) {
        this.urlImg = urlImg;
        this.bounds = bounds;
    }

    public ImageViewInfo(String urlImg, Rect bounds, String urlVideo) {
        this.urlImg = urlImg;
        this.bounds = bounds;
        this.urlVideo = urlVideo;
    }

    public ImageViewInfo(String urlImg, View view) {
        this.urlImg = urlImg;
        Rect bounds = new Rect();
        view.getGlobalVisibleRect(bounds);//获取坐标
        this.bounds = bounds;
    }

    public ImageViewInfo(String urlImg, View view, String urlVideo) {
        this.urlImg = urlImg;
        this.urlVideo = urlVideo;
        Rect bounds = new Rect();
        view.getGlobalVisibleRect(bounds);//获取坐标
        this.bounds = bounds;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getUrlImg());
        dest.writeString(getUrlVideo());
        dest.writeParcelable(getBitmap(), flags);
        dest.writeParcelable(getBounds(), flags);
        dest.writeString("描述信息");

    }

    protected ImageViewInfo(Parcel in) {
        urlImg = in.readString();
        urlVideo = in.readString();
        bitmap = in.readParcelable(Bitmap.class.getClassLoader());
        bounds = in.readParcelable(Rect.class.getClassLoader());
        mDescription = in.readString();

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
