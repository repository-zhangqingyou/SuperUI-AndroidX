package com.zqy.sutils.glide.load.engine;

import com.zqy.sutils.glide.load.Key;
import com.zqy.sutils.glide.load.Options;
import com.zqy.sutils.glide.load.Transformation;

import java.util.Map;

class EngineKeyFactory {

  @SuppressWarnings("rawtypes")
  com.zqy.sutils.glide.load.engine.EngineKey buildKey(Object model, Key signature, int width, int height,
                                                    Map<Class<?>, Transformation<?>> transformations, Class<?> resourceClass,
                                                    Class<?> transcodeClass, Options options) {
    return new com.zqy.sutils.glide.load.engine.EngineKey(model, signature, width, height, transformations, resourceClass,
        transcodeClass, options);
  }
}
