package com.zqy.sdk.utils.glide.load.resource.gif;

import android.graphics.Bitmap;

import com.zqy.sdk.utils.glide.gifdecoder.GifDecoder;
import com.zqy.sdk.utils.glide.load.ResourceDecoder;
import com.zqy.sdk.utils.glide.load.engine.Resource;
import com.zqy.sdk.utils.glide.load.engine.bitmap_recycle.BitmapPool;
import com.zqy.sdk.utils.glide.load.resource.bitmap.BitmapResource;

class GifFrameResourceDecoder implements ResourceDecoder<GifDecoder, Bitmap> {
    private final BitmapPool bitmapPool;

    public GifFrameResourceDecoder(BitmapPool bitmapPool) {
        this.bitmapPool = bitmapPool;
    }

    @Override
    public Resource<Bitmap> decode(GifDecoder source, int width, int height) {
        Bitmap bitmap = source.getNextFrame();
        return BitmapResource.obtain(bitmap, bitmapPool);
    }

    @Override
    public String getId() {
        return "GifFrameResourceDecoder.com.bumptech.glide.load.resource.gif";
    }
}
