package com.zqy.sutils.glide.load.engine;


import android.support.v4.util.Pools;

import com.zqy.sutils.glide.util.Preconditions;
import com.zqy.sutils.glide.util.Synthetic;
import com.zqy.sutils.glide.util.pool.FactoryPools;
import com.zqy.sutils.glide.util.pool.StateVerifier;

/**
 * A resource that defers any calls to {@link com.zqy.sutils.glide.load.engine.Resource#recycle()} until after {@link #unlock()} is
 * called.
 *
 * <p>If the resource was recycled prior to {@link #unlock()}, then {@link #unlock()} will also
 * recycle the resource.
 */
final class LockedResource<Z> implements com.zqy.sutils.glide.load.engine.Resource<Z>,
    FactoryPools.Poolable {
  private static final Pools.Pool<LockedResource<?>> POOL = FactoryPools.threadSafe(20,
      new FactoryPools.Factory<LockedResource<?>>() {
        @Override
        public LockedResource<?> create() {
          return new LockedResource<Object>();
        }
      });
  private final StateVerifier stateVerifier = StateVerifier.newInstance();
  private com.zqy.sutils.glide.load.engine.Resource<Z> toWrap;
  private boolean isLocked;
  private boolean isRecycled;

  @SuppressWarnings("unchecked")

  static <Z> LockedResource<Z> obtain(com.zqy.sutils.glide.load.engine.Resource<Z> resource) {
    LockedResource<Z> result = Preconditions.checkNotNull((LockedResource<Z>) POOL.acquire());
    result.init(resource);
    return result;
  }

  @SuppressWarnings("WeakerAccess")
  @Synthetic
  LockedResource() { }

  private void init(Resource<Z> toWrap) {
    isRecycled = false;
    isLocked = true;
    this.toWrap = toWrap;
  }

  private void release() {
    toWrap = null;
    POOL.release(this);
  }

  synchronized void unlock() {
    stateVerifier.throwIfRecycled();

    if (!isLocked) {
      throw new IllegalStateException("Already unlocked");
    }
    this.isLocked = false;
    if (isRecycled) {
      recycle();
    }
  }


  @Override
  public Class<Z> getResourceClass() {
    return toWrap.getResourceClass();
  }


  @Override
  public Z get() {
    return toWrap.get();
  }

  @Override
  public int getSize() {
    return toWrap.getSize();
  }

  @Override
  public synchronized void recycle() {
    stateVerifier.throwIfRecycled();

    this.isRecycled = true;
    if (!isLocked) {
      toWrap.recycle();
      release();
    }
  }


  @Override
  public StateVerifier getVerifier() {
    return stateVerifier;
  }
}
