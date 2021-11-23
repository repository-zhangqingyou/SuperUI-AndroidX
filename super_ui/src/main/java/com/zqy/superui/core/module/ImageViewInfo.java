package com.zqy.superui.core.module;

import android.graphics.Rect;
import android.os.Parcel;
import android.view.View;

import androidx.annotation.Nullable;

import com.xuexiang.xui.widget.imageview.preview.enitity.IPreviewInfo;

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
    private String mUrl;  //图片地址
    private Rect mBounds; // 记录坐标
    private String mVideoUrl;

    private String mDescription = "描述信息";

    public ImageViewInfo(String mUrl, Rect mBounds) {
        this.mUrl = mUrl;
        this.mBounds = mBounds;
    }

    public ImageViewInfo(String mUrl, Rect mBounds, String mVideoUrl) {
        this.mUrl = mUrl;
        this.mBounds = mBounds;
        this.mVideoUrl = mVideoUrl;
    }

    public ImageViewInfo(String mUrl, View view) {
        this.mUrl = mUrl;
        Rect bounds = new Rect();
        view.getGlobalVisibleRect(bounds);//获取坐标
        this.mBounds = bounds;
    }

    public ImageViewInfo(String mUrl, View view, String mVideoUrl) {
        this.mUrl = mUrl;
        this.mVideoUrl = mVideoUrl;
        Rect bounds = new Rect();
        view.getGlobalVisibleRect(bounds);//获取坐标
        this.mBounds = bounds;
    }

    @Override
    public String getUrl() {
        return mUrl;
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
        mUrl = in.readString();
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
