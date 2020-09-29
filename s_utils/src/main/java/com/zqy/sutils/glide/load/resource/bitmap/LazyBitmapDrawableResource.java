package com.zqy.sutils.glide.load.resource.bitmap;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

import com.zqy.sutils.glide.Glide;
import com.zqy.sutils.glide.load.engine.Initializable;
import com.zqy.sutils.glide.load.engine.Resource;
import com.zqy.sutils.glide.load.engine.bitmap_recycle.BitmapPool;
import com.zqy.sutils.glide.util.Preconditions;

/**
 * Lazily allocates a {@link BitmapDrawable} from a given
 * {@link Bitmap} on the first call to {@link #get()}.
 */
public final class LazyBitmapDrawableResource implements Resource<BitmapDrawable>,
    Initializable {

  private final Resources resources;
  private final Resource<Bitmap> bitmapResource;

  /**
   * @deprecated Use {@link #obtain(Resources, Resource)} instead, it can be unsafe to extract
   * {@link Bitmap}s from their wrapped {@link Resource}.
   */
  @Deprecated
  public static LazyBitmapDrawableResource obtain(Context context, Bitmap bitmap) {
    return
        (LazyBitmapDrawableResource)
            obtain(
                context.getResources(),
                com.zqy.sutils.glide.load.resource.bitmap.BitmapResource.obtain(bitmap, Glide.get(context).getBitmapPool()));
  }

  /**
   * @deprecated Use {@link #obtain(Resources, Resource)} instead, it can be unsafe to extract
   * {@link Bitmap}s from their wrapped {@link Resource}.
   */
  @Deprecated
  public static LazyBitmapDrawableResource obtain(Resources resources, BitmapPool bitmapPool,
      Bitmap bitmap) {
    return
        (LazyBitmapDrawableResource) obtain(resources, BitmapResource.obtain(bitmap, bitmapPool));
  }


  public static Resource<BitmapDrawable> obtain(
       Resources resources, Resource<Bitmap> bitmapResource) {
    if (bitmapResource == null) {
      return null;
    }
    return new LazyBitmapDrawableResource(resources, bitmapResource);

  }

  private LazyBitmapDrawableResource( Resources resources,
       Resource<Bitmap> bitmapResource) {
    this.resources = Preconditions.checkNotNull(resources);
    this.bitmapResource = Preconditions.checkNotNull(bitmapResource);
  }


  @Override
  public Class<BitmapDrawable> getResourceClass() {
    return BitmapDrawable.class;
  }


  @Override
  public BitmapDrawable get() {
    return new BitmapDrawable(resources, bitmapResource.get());
  }

  @Override
  public int getSize() {
    return bitmapResource.getSize();
  }

  @Override
  public void recycle() {
    bitmapResource.recycle();
  }

  @Override
  public void initialize() {
    if (bitmapResource instanceof Initializable) {
      ((Initializable) bitmapResource).initialize();
    }
  }
}
