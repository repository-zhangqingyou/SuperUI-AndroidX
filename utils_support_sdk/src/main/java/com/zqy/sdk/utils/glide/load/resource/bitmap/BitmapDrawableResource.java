package com.zqy.sdk.utils.glide.load.resource.bitmap;

import android.graphics.drawable.BitmapDrawable;

import com.zqy.sdk.utils.glide.load.engine.bitmap_recycle.BitmapPool;
import com.zqy.sdk.utils.glide.load.resource.drawable.DrawableResource;
import com.zqy.sdk.utils.glide.util.Util;

/**
 * A {@linkcom.zqy.sutils.glide.load.engine.Resource} that wraps an {@link android.graphics.drawable.BitmapDrawable}
 * <p>
 *     This class ensures that every call to {@link #get()}} always returns a new
 *     {@link android.graphics.drawable.BitmapDrawable} to avoid rendering issues if used in multiple views and
 *     is also responsible for returning the underlying {@link android.graphics.Bitmap} to the given
 *     {@linkcom.zqy.sutils.glide.load.engine.bitmap_recycle.BitmapPool} when the resource is recycled.
 * </p>
 */
public class BitmapDrawableResource extends DrawableResource<BitmapDrawable> {
    private final BitmapPool bitmapPool;

    public BitmapDrawableResource(BitmapDrawable drawable, BitmapPool bitmapPool) {
        super(drawable);
        this.bitmapPool = bitmapPool;
    }

    @Override
    public int getSize() {
        return Util.getBitmapByteSize(drawable.getBitmap());
    }

    @Override
    public void recycle() {
        bitmapPool.put(drawable.getBitmap());
    }
}
