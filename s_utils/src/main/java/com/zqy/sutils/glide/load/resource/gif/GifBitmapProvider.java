package com.zqy.sutils.glide.load.resource.gif;


import android.graphics.Bitmap;

import com.zqy.sutils.glide.gifdecoder.GifDecoder;
import com.zqy.sutils.glide.load.engine.bitmap_recycle.ArrayPool;
import com.zqy.sutils.glide.load.engine.bitmap_recycle.BitmapPool;

/**
 * Implements {@link GifDecoder.BitmapProvider} by wrapping Glide's
 * {@link BitmapPool}.
 */
public final class GifBitmapProvider implements GifDecoder.BitmapProvider {
  private final BitmapPool bitmapPool;
  private final ArrayPool arrayPool;

  /**
   * Constructs an instance without a shared byte array pool. Byte arrays will be always constructed
   * when requested.
   */
  public GifBitmapProvider(BitmapPool bitmapPool) {
    this(bitmapPool, /*arrayPool=*/ null);
  }

  /**
   * Constructs an instance with a shared array pool. Arrays will be reused where
   * possible.
   */
  // Public API.
  @SuppressWarnings("WeakerAccess")
  public GifBitmapProvider(BitmapPool bitmapPool, ArrayPool arrayPool) {
    this.bitmapPool = bitmapPool;
    this.arrayPool = arrayPool;
  }


  @Override
  public Bitmap obtain(int width, int height,  Bitmap.Config config) {
    return bitmapPool.getDirty(width, height, config);
  }

  @Override
  public void release( Bitmap bitmap) {
    bitmapPool.put(bitmap);
  }


  @Override
  public byte[] obtainByteArray(int size) {
    if (arrayPool == null) {
      return new byte[size];
    }
    return arrayPool.get(size, byte[].class);
  }

  @Override
  public void release( byte[] bytes) {
    if (arrayPool == null) {
      return;
    }
    arrayPool.put(bytes);
  }


  @Override
  public int[] obtainIntArray(int size) {
    if (arrayPool == null) {
      return new int[size];
    }
    return arrayPool.get(size, int[].class);
  }

  @SuppressWarnings("PMD.UseVarargs")
  @Override
  public void release( int[] array) {
    if (arrayPool == null) {
      return;
    }
    arrayPool.put(array);
  }
}
