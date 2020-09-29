package com.zqy.sutils.glide.load.engine.cache;


import com.zqy.sutils.glide.load.Key;
import com.zqy.sutils.glide.load.engine.Resource;

/**
 * A simple class that ignores all puts and returns null for all gets.
 */
public class MemoryCacheAdapter implements MemoryCache {

  private ResourceRemovedListener listener;

  @Override
  public long getCurrentSize() {
    return 0;
  }

  @Override
  public long getMaxSize() {
    return 0;
  }

  @Override
  public void setSizeMultiplier(float multiplier) {
    // Do nothing.
  }


  @Override
  public Resource<?> remove( Key key) {
    return null;
  }


  @Override
  public Resource<?> put( Key key, Resource<?> resource) {
    if (resource != null) {
      listener.onResourceRemoved(resource);
    }
    return null;
  }

  @Override
  public void setResourceRemovedListener( ResourceRemovedListener listener) {
    this.listener = listener;
  }

  @Override
  public void clearMemory() {
    // Do nothing.
  }

  @Override
  public void trimMemory(int level) {
    // Do nothing.
  }
}
