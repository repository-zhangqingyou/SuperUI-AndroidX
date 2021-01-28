package com.zqy.superui.core.widget.imageview;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

/**
 * 作者: zhangqingyou
 * 时间: 2021/1/28 9:14
 * 描述: 圆角ImageView
 */
public class SuperCircleImageView extends AppCompatImageView {

    public SuperCircleImageView(Context context) {
        this(context, null);
    }

    public SuperCircleImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SuperCircleImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }
}
