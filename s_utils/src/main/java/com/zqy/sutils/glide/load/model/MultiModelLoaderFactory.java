package com.zqy.sutils.glide.load.model;


import android.support.v4.util.Pools.Pool;

import androidx.annotation.VisibleForTesting;

import com.zqy.sutils.glide.Registry.NoModelLoaderAvailableException;
import com.zqy.sutils.glide.load.Options;
import com.zqy.sutils.glide.util.Preconditions;
import com.zqy.sutils.glide.util.Synthetic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Capable of building an {@link com.zqy.sutils.glide.load.model.ModelLoader} that wraps one or more other {@link com.zqy.sutils.glide.load.model.ModelLoader}s for
 * a given model and data class.
 */
// Hides Model throughout.
@SuppressWarnings("TypeParameterHidesVisibleType")
public class MultiModelLoaderFactory {
  private static final Factory DEFAULT_FACTORY = new Factory();
  private static final com.zqy.sutils.glide.load.model.ModelLoader<Object, Object> EMPTY_MODEL_LOADER = new EmptyModelLoader();
  private final List<Entry<?, ?>> entries = new ArrayList<>();
  private final Factory factory;
  private final Set<Entry<?, ?>> alreadyUsedEntries = new HashSet<>();
  private final Pool<List<Throwable>> throwableListPool;

  public MultiModelLoaderFactory( Pool<List<Throwable>> throwableListPool) {
    this(throwableListPool, DEFAULT_FACTORY);
  }

  @VisibleForTesting
  MultiModelLoaderFactory( Pool<List<Throwable>> throwableListPool,
       Factory factory) {
    this.throwableListPool = throwableListPool;
    this.factory = factory;
  }

  synchronized <Model, Data> void append(
       Class<Model> modelClass,
       Class<Data> dataClass,
       com.zqy.sutils.glide.load.model.ModelLoaderFactory<? extends Model, ? extends Data> factory) {
    add(modelClass, dataClass, factory, /*append=*/ true);
  }

  synchronized <Model, Data> void prepend(
       Class<Model> modelClass,
       Class<Data> dataClass,
       com.zqy.sutils.glide.load.model.ModelLoaderFactory<? extends Model, ? extends Data> factory) {
    add(modelClass, dataClass, factory, /*append=*/ false);
  }

  private <Model, Data> void add(
       Class<Model> modelClass,
       Class<Data> dataClass,
       com.zqy.sutils.glide.load.model.ModelLoaderFactory<? extends Model, ? extends Data> factory,
      boolean append) {
    Entry<Model, Data> entry = new Entry<>(modelClass, dataClass, factory);
    entries.add(append ? entries.size() : 0, entry);
  }


  synchronized <Model, Data> List<com.zqy.sutils.glide.load.model.ModelLoaderFactory<? extends Model, ? extends Data>> replace(
       Class<Model> modelClass,
       Class<Data> dataClass,
       com.zqy.sutils.glide.load.model.ModelLoaderFactory<? extends Model, ? extends Data> factory) {
    List<com.zqy.sutils.glide.load.model.ModelLoaderFactory<? extends Model, ? extends Data>> removed =
        remove(modelClass, dataClass);
    append(modelClass, dataClass, factory);
    return removed;
  }


  synchronized <Model, Data> List<com.zqy.sutils.glide.load.model.ModelLoaderFactory<? extends Model, ? extends Data>> remove(
       Class<Model> modelClass,
       Class<Data> dataClass) {
    List<com.zqy.sutils.glide.load.model.ModelLoaderFactory<? extends Model, ? extends Data>> factories = new ArrayList<>();
    for (Iterator<Entry<?, ?>> iterator = entries.iterator(); iterator.hasNext(); ) {
      Entry<?, ?> entry = iterator.next();
      if (entry.handles(modelClass, dataClass)) {
        iterator.remove();
        factories.add(this.<Model, Data>getFactory(entry));
      }
    }
    return factories;
  }


  synchronized <Model> List<com.zqy.sutils.glide.load.model.ModelLoader<Model, ?>> build( Class<Model> modelClass) {
    try {
      List<com.zqy.sutils.glide.load.model.ModelLoader<Model, ?>> loaders = new ArrayList<>();
      for (Entry<?, ?> entry : entries) {
        // Avoid stack overflow recursively creating model loaders by only creating loaders in
        // recursive requests if they haven't been created earlier in the chain. For example:
        // A Uri loader may translate to another model, which in turn may translate back to a Uri.
        // The original Uri loader won't be provided to the intermediate model loader, although
        // other Uri loaders will be.
        if (alreadyUsedEntries.contains(entry)) {
          continue;
        }
        if (entry.handles(modelClass)) {
          alreadyUsedEntries.add(entry);
          loaders.add(this.<Model, Object>build(entry));
          alreadyUsedEntries.remove(entry);
        }
      }
      return loaders;
    } catch (Throwable t) {
      alreadyUsedEntries.clear();
      throw t;
    }
  }


  synchronized List<Class<?>> getDataClasses( Class<?> modelClass) {
    List<Class<?>> result = new ArrayList<>();
    for (Entry<?, ?> entry : entries) {
      if (!result.contains(entry.dataClass) && entry.handles(modelClass)) {
        result.add(entry.dataClass);
      }
    }
    return result;
  }


  public synchronized <Model, Data> com.zqy.sutils.glide.load.model.ModelLoader<Model, Data> build( Class<Model> modelClass,
                                                                                                  Class<Data> dataClass) {
    try {
      List<com.zqy.sutils.glide.load.model.ModelLoader<Model, Data>> loaders = new ArrayList<>();
      boolean ignoredAnyEntries = false;
      for (Entry<?, ?> entry : entries) {
        // Avoid stack overflow recursively creating model loaders by only creating loaders in
        // recursive requests if they haven't been created earlier in the chain. For example:
        // A Uri loader may translate to another model, which in turn may translate back to a Uri.
        // The original Uri loader won't be provided to the intermediate model loader, although
        // other Uri loaders will be.
        if (alreadyUsedEntries.contains(entry)) {
          ignoredAnyEntries = true;
          continue;
        }
        if (entry.handles(modelClass, dataClass)) {
          alreadyUsedEntries.add(entry);
          loaders.add(this.<Model, Data>build(entry));
          alreadyUsedEntries.remove(entry);
        }
      }
      if (loaders.size() > 1) {
        return factory.build(loaders, throwableListPool);
      } else if (loaders.size() == 1) {
        return loaders.get(0);
      } else {
        // Avoid crashing if recursion results in no loaders available. The assertion is supposed to
        // catch completely unhandled types, recursion may mean a subtype isn't handled somewhere
        // down the stack, which is often ok.
        if (ignoredAnyEntries) {
          return emptyModelLoader();
        } else {
          throw new NoModelLoaderAvailableException(modelClass, dataClass);
        }
      }
    } catch (Throwable t) {
      alreadyUsedEntries.clear();
      throw t;
    }
  }


  @SuppressWarnings("unchecked")
  private <Model, Data> com.zqy.sutils.glide.load.model.ModelLoaderFactory<Model, Data> getFactory( Entry<?, ?> entry) {
    return (com.zqy.sutils.glide.load.model.ModelLoaderFactory<Model, Data>) entry.factory;
  }


  @SuppressWarnings("unchecked")
  private <Model, Data> com.zqy.sutils.glide.load.model.ModelLoader<Model, Data> build( Entry<?, ?> entry) {
    return (com.zqy.sutils.glide.load.model.ModelLoader<Model, Data>) Preconditions.checkNotNull(entry.factory.build(this));
  }


  @SuppressWarnings("unchecked")
  private static <Model, Data> com.zqy.sutils.glide.load.model.ModelLoader<Model, Data> emptyModelLoader() {
    return (com.zqy.sutils.glide.load.model.ModelLoader<Model, Data>) EMPTY_MODEL_LOADER;
  }

  private static class Entry<Model, Data> {
    private final Class<Model> modelClass;
    @Synthetic final Class<Data> dataClass;
    @Synthetic final com.zqy.sutils.glide.load.model.ModelLoaderFactory<? extends Model, ? extends Data> factory;

    public Entry(
         Class<Model> modelClass,
         Class<Data> dataClass,
         ModelLoaderFactory<? extends Model, ? extends Data> factory) {
      this.modelClass = modelClass;
      this.dataClass = dataClass;
      this.factory = factory;
    }

    public boolean handles( Class<?> modelClass,  Class<?> dataClass) {
      return handles(modelClass) && this.dataClass.isAssignableFrom(dataClass);
    }

    public boolean handles( Class<?> modelClass) {
      return this.modelClass.isAssignableFrom(modelClass);
    }
  }

  static class Factory {

    public <Model, Data> com.zqy.sutils.glide.load.model.MultiModelLoader<Model, Data> build(
         List<com.zqy.sutils.glide.load.model.ModelLoader<Model, Data>> modelLoaders,
         Pool<List<Throwable>> throwableListPool) {
      return new com.zqy.sutils.glide.load.model.MultiModelLoader<>(modelLoaders, throwableListPool);
    }
  }

  private static class EmptyModelLoader implements ModelLoader<Object, Object> {
    @Synthetic
    EmptyModelLoader() { }


    @Override
    public LoadData<Object> buildLoadData( Object o, int width, int height,
         Options options) {
      return null;
    }

    @Override
    public boolean handles( Object o) {
      return false;
    }
  }
}
