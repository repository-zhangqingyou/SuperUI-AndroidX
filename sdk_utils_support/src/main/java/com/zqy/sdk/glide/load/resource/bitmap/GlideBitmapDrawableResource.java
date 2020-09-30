package com.zqy.sdk.glide.load.resource.bitmap;

import com.zqy.sdk.glide.load.engine.bitmap_recycle.BitmapPool;
import com.zqy.sdk.glide.load.resource.drawable.DrawableResource;
import com.zqy.sdk.glide.util.Util;

/**
 * A resource wrapper for {@linkcom.zqy.sutils.glide.load.resource.bitmap.GlideBitmapDrawable}.
 */
public class GlideBitmapDrawableResource extends DrawableResource<GlideBitmapDrawable> {
    private final BitmapPool bitmapPool;

    public GlideBitmapDrawableResource(GlideBitmapDrawable drawable, BitmapPool bitmapPool) {
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
