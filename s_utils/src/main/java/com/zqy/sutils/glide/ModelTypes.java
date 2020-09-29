package com.zqy.sutils.glide;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;

import androidx.annotation.CheckResult;
import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import androidx.annotation.RawRes;

import java.io.File;
import java.net.URL;

/**
 * Ensures that the set of explicitly supported model types remains consistent across Glide's
 * API surface.
 */
interface ModelTypes<T> {

  @CheckResult
  T load(@Nullable Bitmap bitmap);


  @CheckResult
  T load(@Nullable Drawable drawable);


  @CheckResult
  T load(@Nullable String string);


  @CheckResult
  T load(@Nullable Uri uri);


  @CheckResult
  T load(@Nullable File file);


  @CheckResult
  T load(@RawRes @DrawableRes Integer resourceId);

  @Deprecated
  @CheckResult
  T load(@Nullable URL url);


  @CheckResult
  T load(@Nullable byte[] model);


  @CheckResult
  @SuppressWarnings("unchecked")
  T load(@Nullable Object model);
}
