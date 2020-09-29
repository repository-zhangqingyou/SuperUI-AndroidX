package com.zqy.sutils.glide.load.resource.bitmap;

import android.graphics.Bitmap;

import com.zqy.sutils.glide.load.engine.bitmap_recycle.BitmapPool;

import java.security.MessageDigest;

/**
 * Scales the image uniformly (maintaining the image's aspect ratio) so that one of the dimensions
 * of the image will be equal to the given dimension and the other will be less than the given
 * dimension.
 */
public class FitCenter extends BitmapTransformation {
  private static final String ID = "com.zqy.sutils.glide.load.resource.bitmap.FitCenter";
  private static final byte[] ID_BYTES = ID.getBytes(CHARSET);

  @Override
  protected Bitmap transform( BitmapPool pool,  Bitmap toTransform, int outWidth,
      int outHeight) {
    return TransformationUtils.fitCenter(pool, toTransform, outWidth, outHeight);
  }

  @Override
  public boolean equals(Object o) {
    return o instanceof FitCenter;
  }

  @Override
  public int hashCode() {
    return ID.hashCode();
  }

  @Override
  public void updateDiskCacheKey( MessageDigest messageDigest) {
    messageDigest.update(ID_BYTES);
  }
}


