package com.zqy.sutils.glide.load.model.stream;


import androidx.annotation.Nullable;

import com.zqy.sutils.glide.load.Option;
import com.zqy.sutils.glide.load.Options;
import com.zqy.sutils.glide.load.data.HttpUrlFetcher;
import com.zqy.sutils.glide.load.model.GlideUrl;
import com.zqy.sutils.glide.load.model.ModelCache;
import com.zqy.sutils.glide.load.model.ModelLoader;
import com.zqy.sutils.glide.load.model.ModelLoaderFactory;
import com.zqy.sutils.glide.load.model.MultiModelLoaderFactory;

import java.io.InputStream;

/**
 * An {@link ModelLoader} for translating {@link
 * GlideUrl} (http/https URLS) into {@link InputStream} data.
 */
// Public API.
@SuppressWarnings("WeakerAccess")
public class HttpGlideUrlLoader implements ModelLoader<GlideUrl, InputStream> {
  /**
   * An integer option that is used to determine the maximum connect and read timeout durations (in
   * milliseconds) for network connections.
   *
   * <p>Defaults to 2500ms.
   */
  public static final Option<Integer> TIMEOUT = Option.memory(
      "com.zqy.sutils.glide.load.model.stream.HttpGlideUrlLoader.Timeout", 2500);

  private final ModelCache<GlideUrl, GlideUrl> modelCache;

  public HttpGlideUrlLoader() {
    this(null);
  }

  public HttpGlideUrlLoader(@Nullable ModelCache<GlideUrl, GlideUrl> modelCache) {
    this.modelCache = modelCache;
  }

  @Override
  public LoadData<InputStream> buildLoadData( GlideUrl model, int width, int height,
       Options options) {
    // GlideUrls memoize parsed URLs so caching them saves a few object instantiations and time
    // spent parsing urls.
    GlideUrl url = model;
    if (modelCache != null) {
      url = modelCache.get(model, 0, 0);
      if (url == null) {
        modelCache.put(model, 0, 0, model);
        url = model;
      }
    }
    int timeout = options.get(TIMEOUT);
    return new LoadData<>(url, new HttpUrlFetcher(url, timeout));
  }

  @Override
  public boolean handles( GlideUrl model) {
    return true;
  }

  /**
   * The default factory for {@link HttpGlideUrlLoader}s.
   */
  public static class Factory implements ModelLoaderFactory<GlideUrl, InputStream> {
    private final ModelCache<GlideUrl, GlideUrl> modelCache = new ModelCache<>(500);


    @Override
    public ModelLoader<GlideUrl, InputStream> build(MultiModelLoaderFactory multiFactory) {
      return new HttpGlideUrlLoader(modelCache);
    }

    @Override
    public void teardown() {
      // Do nothing.
    }
  }
}
