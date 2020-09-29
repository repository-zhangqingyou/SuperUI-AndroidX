package com.zqy.sutils.glide;


import com.zqy.sutils.glide.manager.RequestManagerRetriever;
import com.zqy.sutils.glide.module.AppGlideModule;

import java.util.Set;

/**
 * Allows {@link AppGlideModule}s to exclude {@link com.zqy.sutils.glide.annotation.GlideModule}s to
 * ease the migration from {@link com.zqy.sutils.glide.annotation.GlideModule}s to Glide's annotation
 * processing system and optionally provides a
 * {@link RequestManagerRetriever.RequestManagerFactory} impl.
 */
abstract class GeneratedAppGlideModule extends AppGlideModule {
  /**
   * This method can be removed when manifest parsing is no longer supported.
   */

  abstract Set<Class<?>> getExcludedModuleClasses();


  RequestManagerRetriever.RequestManagerFactory getRequestManagerFactory() {
    return null;
  }
}
