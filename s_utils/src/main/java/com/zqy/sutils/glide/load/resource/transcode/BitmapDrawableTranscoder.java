package com.zqy.sutils.glide.load.resource.transcode;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

import com.zqy.sutils.glide.load.Options;
import com.zqy.sutils.glide.load.engine.Resource;
import com.zqy.sutils.glide.load.engine.bitmap_recycle.BitmapPool;
import com.zqy.sutils.glide.load.resource.bitmap.LazyBitmapDrawableResource;
import com.zqy.sutils.glide.util.Preconditions;

/**
 * An {@link com.zqy.sutils.glide.load.resource.transcode.ResourceTranscoder} that converts {@link
 * Bitmap}s into {@link BitmapDrawable}s.
 */
public class BitmapDrawableTranscoder implements ResourceTranscoder<Bitmap, BitmapDrawable> {
  private final Resources resources;

  // Public API.
  @SuppressWarnings("unused")
  public BitmapDrawableTranscoder( Context context) {
    this(context.getResources());
  }

  /**
   * @deprecated Use {@link #BitmapDrawableTranscoder(Resources)}, {@code bitmapPool} is unused.
   */
  @Deprecated
  public BitmapDrawableTranscoder(
       Resources resources, @SuppressWarnings("unused") BitmapPool bitmapPool) {
    this(resources);
  }

  public BitmapDrawableTranscoder( Resources resources) {
    this.resources = Preconditions.checkNotNull(resources);
  }


  @Override
  public Resource<BitmapDrawable> transcode( Resource<Bitmap> toTranscode,
       Options options) {
    return LazyBitmapDrawableResource.obtain(resources, toTranscode);
  }
}
