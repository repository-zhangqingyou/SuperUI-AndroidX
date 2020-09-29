package com.zqy.sutils.glide.load.resource.bitmap;

import android.graphics.Bitmap;

import com.zqy.sutils.glide.load.Options;
import com.zqy.sutils.glide.load.ResourceDecoder;
import com.zqy.sutils.glide.load.engine.Resource;
import com.zqy.sutils.glide.util.Util;

/**
 * Passes through a (hopefully) non-owned {@link Bitmap} as a {@link Bitmap} based {@link Resource}
 * so that the given {@link Bitmap} is not recycled.
 */
public final class UnitBitmapDecoder implements ResourceDecoder<Bitmap, Bitmap> {

  @Override
  public boolean handles( Bitmap source,  Options options) {
    return true;
  }

  @Override
  public Resource<Bitmap> decode( Bitmap source, int width, int height,
       Options options) {
    return new NonOwnedBitmapResource(source);
  }

  private static final class NonOwnedBitmapResource implements Resource<Bitmap> {

    private final Bitmap bitmap;

    NonOwnedBitmapResource( Bitmap bitmap) {
      this.bitmap = bitmap;
    }


    @Override
    public Class<Bitmap> getResourceClass() {
      return Bitmap.class;
    }


    @Override
    public Bitmap get() {
      return bitmap;
    }

    @Override
    public int getSize() {
      return Util.getBitmapByteSize(bitmap);
    }

    @Override
    public void recycle() {
      // Do nothing.
    }
  }
}
