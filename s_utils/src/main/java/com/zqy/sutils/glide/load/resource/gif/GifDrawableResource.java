package com.zqy.sutils.glide.load.resource.gif;


import com.zqy.sutils.glide.load.engine.Initializable;
import com.zqy.sutils.glide.load.resource.drawable.DrawableResource;

/**
 * A resource wrapping an {@link com.zqy.sutils.glide.load.resource.gif.GifDrawable}.
 */
public class GifDrawableResource extends DrawableResource<com.zqy.sutils.glide.load.resource.gif.GifDrawable>
    implements Initializable {
  // Public API.
  @SuppressWarnings("WeakerAccess")
  public GifDrawableResource(com.zqy.sutils.glide.load.resource.gif.GifDrawable drawable) {
    super(drawable);
  }


  @Override
  public Class<com.zqy.sutils.glide.load.resource.gif.GifDrawable> getResourceClass() {
    return GifDrawable.class;
  }

  @Override
  public int getSize() {
    return drawable.getSize();
  }

  @Override
  public void recycle() {
    drawable.stop();
    drawable.recycle();
  }

  @Override
  public void initialize() {
    drawable.getFirstFrame().prepareToDraw();
  }
}
