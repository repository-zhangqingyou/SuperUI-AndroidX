package com.zqy.sutils.glide.load;


import android.support.v4.util.ArrayMap;
import android.support.v4.util.SimpleArrayMap;

import com.zqy.sutils.glide.util.CachedHashCodeArrayMap;

import java.security.MessageDigest;

/**
 * A set of {@link com.zqy.sutils.glide.load.Option Options} to apply to in memory and disk cache keys.
 */
public final class Options implements Key {
  private final ArrayMap<com.zqy.sutils.glide.load.Option<?>, Object> values = new CachedHashCodeArrayMap<>();

  public void putAll( Options other) {
    values.putAll((SimpleArrayMap<com.zqy.sutils.glide.load.Option<?>, Object>) other.values);
  }


  public <T> Options set( com.zqy.sutils.glide.load.Option<T> option,  T value) {
    values.put(option, value);
    return this;
  }


  @SuppressWarnings("unchecked")
  public <T> T get( com.zqy.sutils.glide.load.Option<T> option) {
    return values.containsKey(option) ? (T) values.get(option) : option.getDefaultValue();
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof Options) {
      Options other = (Options) o;
      return values.equals(other.values);
    }
    return false;
  }

  @Override
  public int hashCode() {
    return values.hashCode();
  }

  @Override
  public void updateDiskCacheKey( MessageDigest messageDigest) {
    for (int i = 0; i < values.size(); i++) {
      com.zqy.sutils.glide.load.Option<?> key = values.keyAt(i);
      Object value = values.valueAt(i);
      updateDiskCacheKey(key, value, messageDigest);
    }
  }

  @Override
  public String toString() {
    return "Options{"
        + "values=" + values
        + '}';
  }

  @SuppressWarnings("unchecked")
  private static <T> void updateDiskCacheKey( Option<T> option,  Object value,
                                              MessageDigest md) {
    option.update((T) value, md);
  }
}
