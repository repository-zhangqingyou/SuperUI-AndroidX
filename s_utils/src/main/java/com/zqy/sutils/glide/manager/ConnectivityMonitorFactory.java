package com.zqy.sutils.glide.manager;

import android.content.Context;

/**
 * A factory class that produces a functional
 * {@link com.zqy.sutils.glide.manager.ConnectivityMonitor}.
 */
public interface ConnectivityMonitorFactory {


  com.zqy.sutils.glide.manager.ConnectivityMonitor build(
           Context context,
           ConnectivityMonitor.ConnectivityListener listener);
}
