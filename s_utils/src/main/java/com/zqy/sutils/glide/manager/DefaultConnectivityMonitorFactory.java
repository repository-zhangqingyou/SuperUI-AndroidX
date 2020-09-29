package com.zqy.sutils.glide.manager;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;
import android.util.Log;

/**
 * A factory class that produces a functional {@link com.zqy.sutils.glide.manager.ConnectivityMonitor}
 * if the application has the {@code android.permission.ACCESS_NETWORK_STATE} permission and a no-op
 * non functional {@link com.zqy.sutils.glide.manager.ConnectivityMonitor} if the app does not have
 * the required permission.
 */
public class DefaultConnectivityMonitorFactory implements ConnectivityMonitorFactory {
  private static final String TAG = "ConnectivityMonitor";
  private static final String NETWORK_PERMISSION = "android.permission.ACCESS_NETWORK_STATE";


  @Override
  public com.zqy.sutils.glide.manager.ConnectivityMonitor build(
       Context context,
       ConnectivityMonitor.ConnectivityListener listener) {
    int permissionResult = ContextCompat.checkSelfPermission(context, NETWORK_PERMISSION);
    boolean hasPermission = permissionResult == PackageManager.PERMISSION_GRANTED;
    if (Log.isLoggable(TAG, Log.DEBUG)) {
      Log.d(
          TAG,
          hasPermission
              ? "ACCESS_NETWORK_STATE permission granted, registering connectivity monitor"
              : "ACCESS_NETWORK_STATE permission missing, cannot register connectivity monitor");
    }
    return hasPermission
        ? new com.zqy.sutils.glide.manager.DefaultConnectivityMonitor(context, listener) : new com.zqy.sutils.glide.manager.NullConnectivityMonitor();
  }
}
