package com.zqy.sdk.utils.glide.load.resource.gif;

import com.zqy.sdk.utils.glide.load.resource.drawable.DrawableResource;
import com.zqy.sdk.utils.glide.util.Util;

/**
 * A resource wrapping an {@linkcom.zqy.sutils.glide.load.resource.gif.GifDrawable}.
 */
public class GifDrawableResource extends DrawableResource<GifDrawable> {
    public GifDrawableResource(GifDrawable drawable) {
        super(drawable);
    }

    @Override
    public int getSize() {
        return drawable.getData().length + Util.getBitmapByteSize(drawable.getFirstFrame());
    }

    @Override
    public void recycle() {
        drawable.stop();
        drawable.recycle();
    }
}
