package com.zqy.sutils.glide.manager;


import com.zqy.sutils.glide.util.Util;

import java.util.Collections;
import java.util.Set;
import java.util.WeakHashMap;

/**
 * A {@link com.zqy.sutils.glide.manager.Lifecycle} implementation for tracking and notifying
 * listeners of {@link android.app.Fragment} and {@link android.app.Activity} lifecycle events.
 */
class ActivityFragmentLifecycle implements Lifecycle {
  private final Set<com.zqy.sutils.glide.manager.LifecycleListener> lifecycleListeners =
      Collections.newSetFromMap(new WeakHashMap<com.zqy.sutils.glide.manager.LifecycleListener, Boolean>());
  private boolean isStarted;
  private boolean isDestroyed;

  /**
   * Adds the given listener to the list of listeners to be notified on each lifecycle event.
   *
   * <p> The latest lifecycle event will be called on the given listener synchronously in this
   * method. If the activity or fragment is stopped, {@link com.zqy.sutils.glide.manager.LifecycleListener#onStop()}} will be
   * called, and same for onStart and onDestroy. </p>
   *
   * <p> Note - {@link com.zqy.sutils.glide.manager.LifecycleListener}s that are added more than once
   * will have their lifecycle methods called more than once. It is the caller's responsibility to
   * avoid adding listeners multiple times. </p>
   */
  @Override
  public void addListener( com.zqy.sutils.glide.manager.LifecycleListener listener) {
    lifecycleListeners.add(listener);

    if (isDestroyed) {
      listener.onDestroy();
    } else if (isStarted) {
      listener.onStart();
    } else {
      listener.onStop();
    }
  }

  @Override
  public void removeListener( com.zqy.sutils.glide.manager.LifecycleListener listener) {
    lifecycleListeners.remove(listener);
  }

  void onStart() {
    isStarted = true;
    for (com.zqy.sutils.glide.manager.LifecycleListener lifecycleListener : Util.getSnapshot(lifecycleListeners)) {
      lifecycleListener.onStart();
    }
  }

  void onStop() {
    isStarted = false;
    for (com.zqy.sutils.glide.manager.LifecycleListener lifecycleListener : Util.getSnapshot(lifecycleListeners)) {
      lifecycleListener.onStop();
    }
  }

  void onDestroy() {
    isDestroyed = true;
    for (LifecycleListener lifecycleListener : Util.getSnapshot(lifecycleListeners)) {
      lifecycleListener.onDestroy();
    }
  }
}
