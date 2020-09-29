package com.zqy.sutils.glide.load.model;

import android.content.Context;

/**
 * An interface for creating a {@link com.zqy.sutils.glide.load.model.ModelLoader} for a given model type. Will be retained
 * statically so should not retain {@link Context} or any other objects that cannot be retained for
 * the life of the application. ModelLoaders will not be retained statically so it is safe for any
 * ModelLoader built by this factory to retain a reference to a {@link Context}.
 *
 * @param <T> The type of the model the {@link com.zqy.sutils.glide.load.model.ModelLoader}s built by
 *            this factory can handle
 * @param <Y> The type of data the {@link com.zqy.sutils.glide.load.model.ModelLoader}s built by this
 *            factory can load.
 */
public interface ModelLoaderFactory<T, Y> {

  /**
   * Build a concrete ModelLoader for this model type.
   *
   * @param multiFactory A map of classes to factories that can be used to construct additional
   *                     {@link com.zqy.sutils.glide.load.model.ModelLoader}s that this factory's {@link com.zqy.sutils.glide.load.model.ModelLoader} may depend on
   * @return A new {@link com.zqy.sutils.glide.load.model.ModelLoader}
   */

  ModelLoader<T, Y> build( MultiModelLoaderFactory multiFactory);

  /**
   * A lifecycle method that will be called when this factory is about to replaced.
   */
  void teardown();
}
