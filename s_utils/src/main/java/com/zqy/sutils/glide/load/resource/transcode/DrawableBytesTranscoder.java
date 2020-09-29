package com.zqy.sutils.glide.load.resource.transcode;


import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.zqy.sutils.glide.load.Options;
import com.zqy.sutils.glide.load.engine.Resource;
import com.zqy.sutils.glide.load.engine.bitmap_recycle.BitmapPool;
import com.zqy.sutils.glide.load.resource.bitmap.BitmapResource;
import com.zqy.sutils.glide.load.resource.gif.GifDrawable;

/**
 * Obtains {@code byte[]} from {@link BitmapDrawable}s by delegating to a
 * {@link com.zqy.sutils.glide.load.resource.transcode.ResourceTranscoder} for {@link Bitmap}s to {@code byte[]}s.
 */
public final class DrawableBytesTranscoder implements com.zqy.sutils.glide.load.resource.transcode.ResourceTranscoder<Drawable, byte[]> {
  private final BitmapPool bitmapPool;
  private final com.zqy.sutils.glide.load.resource.transcode.ResourceTranscoder<Bitmap, byte[]> bitmapBytesTranscoder;
  private final com.zqy.sutils.glide.load.resource.transcode.ResourceTranscoder<GifDrawable, byte[]> gifDrawableBytesTranscoder;

  public DrawableBytesTranscoder(
       BitmapPool bitmapPool,
       com.zqy.sutils.glide.load.resource.transcode.ResourceTranscoder<Bitmap, byte[]> bitmapBytesTranscoder,
       ResourceTranscoder<GifDrawable, byte[]> gifDrawableBytesTranscoder) {
    this.bitmapPool = bitmapPool;
    this.bitmapBytesTranscoder = bitmapBytesTranscoder;
    this.gifDrawableBytesTranscoder = gifDrawableBytesTranscoder;
  }


  @Override
  public Resource<byte[]> transcode( Resource<Drawable> toTranscode,
       Options options) {
    Drawable drawable = toTranscode.get();
    if (drawable instanceof BitmapDrawable) {
      return bitmapBytesTranscoder.transcode(
          BitmapResource.obtain(((BitmapDrawable) drawable).getBitmap(), bitmapPool), options);
    } else if (drawable instanceof GifDrawable) {
      return gifDrawableBytesTranscoder.transcode(toGifDrawableResource(toTranscode), options);
    }
    return null;
  }

  @SuppressWarnings("unchecked")

  private static Resource<GifDrawable> toGifDrawableResource( Resource<Drawable> resource) {
    return (Resource<GifDrawable>) (Resource<?>) resource;
  }
}
