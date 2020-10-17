package com.zqy.sdk.utils.glide.load.resource.gif;


import android.graphics.Bitmap;

import com.zqy.sdk.utils.glide.gifdecoder.GifDecoder;
import com.zqy.sdk.utils.glide.load.engine.bitmap_recycle.BitmapPool;

class GifBitmapProvider implements GifDecoder.BitmapProvider {
    private final BitmapPool bitmapPool;

    public GifBitmapProvider(BitmapPool bitmapPool) {
        this.bitmapPool = bitmapPool;
    }

    @Override
    public Bitmap obtain(int width, int height, Bitmap.Config config) {
        return bitmapPool.getDirty(width, height, config);
    }

    @Override
    public void release(Bitmap bitmap) {
        if (!bitmapPool.put(bitmap)) {
            bitmap.recycle();
        }
    }
}
