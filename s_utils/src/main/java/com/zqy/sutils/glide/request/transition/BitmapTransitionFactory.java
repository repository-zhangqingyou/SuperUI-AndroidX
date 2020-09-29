package com.zqy.sutils.glide.request.transition;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

/**
 * A {@link com.zqy.sutils.glide.request.transition.TransitionFactory} for {@link Bitmap}s that uses a Drawable transition
 * factory to transition from an existing drawable already visible on the target to the new bitmap.
 *
 * @see BitmapContainerTransitionFactory
 */
public class BitmapTransitionFactory extends BitmapContainerTransitionFactory<Bitmap> {
  public BitmapTransitionFactory( TransitionFactory<Drawable> realFactory) {
    super(realFactory);
  }

  @Override

  protected Bitmap getBitmap( Bitmap current) {
    return current;
  }
}
