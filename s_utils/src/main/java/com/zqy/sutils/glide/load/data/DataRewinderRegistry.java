package com.zqy.sutils.glide.load.data;


import com.zqy.sutils.glide.util.Preconditions;

import java.util.HashMap;
import java.util.Map;

/**
 * Stores a mapping of data class to {@link com.zqy.sutils.glide.load.data.DataRewinder.Factory} and
 * allows registration of new types and factories.
 */
public class DataRewinderRegistry {
  private final Map<Class<?>, com.zqy.sutils.glide.load.data.DataRewinder.Factory<?>> rewinders = new HashMap<>();
  private static final com.zqy.sutils.glide.load.data.DataRewinder.Factory<?> DEFAULT_FACTORY =
      new com.zqy.sutils.glide.load.data.DataRewinder.Factory<Object>() {

        @Override
        public com.zqy.sutils.glide.load.data.DataRewinder<Object> build( Object data) {
          return new DefaultRewinder(data);
        }


        @Override
        public Class<Object> getDataClass() {
          throw new UnsupportedOperationException("Not implemented");
        }
      };

  public synchronized void register( com.zqy.sutils.glide.load.data.DataRewinder.Factory<?> factory) {
    rewinders.put(factory.getDataClass(), factory);
  }


  @SuppressWarnings("unchecked")
  public synchronized <T> com.zqy.sutils.glide.load.data.DataRewinder<T> build( T data) {
    Preconditions.checkNotNull(data);
    com.zqy.sutils.glide.load.data.DataRewinder.Factory<T> result = (com.zqy.sutils.glide.load.data.DataRewinder.Factory<T>) rewinders.get(data.getClass());
    if (result == null) {
      for (com.zqy.sutils.glide.load.data.DataRewinder.Factory<?> registeredFactory : rewinders.values()) {
        if (registeredFactory.getDataClass().isAssignableFrom(data.getClass())) {
          result = (com.zqy.sutils.glide.load.data.DataRewinder.Factory<T>) registeredFactory;
          break;
        }
      }
    }

    if (result == null) {
      result = (com.zqy.sutils.glide.load.data.DataRewinder.Factory<T>) DEFAULT_FACTORY;
    }
    return result.build(data);
  }

  private static final class DefaultRewinder implements DataRewinder<Object> {
    private final Object data;

    DefaultRewinder( Object data) {
      this.data = data;
    }


    @Override
    public Object rewindAndGet() {
      return data;
    }

    @Override
    public void cleanup() {
      // Do nothing.
    }
  }
}
