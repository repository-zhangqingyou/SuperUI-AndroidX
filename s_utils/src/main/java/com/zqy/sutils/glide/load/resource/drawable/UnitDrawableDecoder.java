package com.zqy.sutils.glide.load.resource.drawable;

import android.graphics.drawable.Drawable;

import com.zqy.sutils.glide.load.Options;
import com.zqy.sutils.glide.load.ResourceDecoder;
import com.zqy.sutils.glide.load.engine.Resource;

/**
 * Passes through a {@link Drawable} as a {@link Drawable} based {@link Resource}.
 */
public class UnitDrawableDecoder implements ResourceDecoder<Drawable, Drawable> {
  @Override
  public boolean handles( Drawable source,  Options options) {
    return true;
  }


  @Override
  public Resource<Drawable> decode( Drawable source, int width, int height,
       Options options) {
    return NonOwnedDrawableResource.newInstance(source);
  }
}
