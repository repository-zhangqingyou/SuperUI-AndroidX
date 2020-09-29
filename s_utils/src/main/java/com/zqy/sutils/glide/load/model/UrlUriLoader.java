package com.zqy.sutils.glide.load.model;

import android.net.Uri;

import com.zqy.sutils.glide.load.Options;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Handles http/https Uris by delegating to the {@link com.zqy.sutils.glide.load.model.ModelLoader} for {@link
 * com.zqy.sutils.glide.load.model.GlideUrl GlideUrls}.
 *
 * @param <Data> The type of data this Loader will obtain for a {@link Uri}.
 */
public class UrlUriLoader<Data> implements com.zqy.sutils.glide.load.model.ModelLoader<Uri, Data> {
  private static final Set<String> SCHEMES = Collections.unmodifiableSet(
      new HashSet<>(
          Arrays.asList(
              "http",
              "https"
          )
      )
  );
  private final com.zqy.sutils.glide.load.model.ModelLoader<com.zqy.sutils.glide.load.model.GlideUrl, Data> urlLoader;

  // Public API.
  @SuppressWarnings("WeakerAccess")
  public UrlUriLoader(com.zqy.sutils.glide.load.model.ModelLoader<com.zqy.sutils.glide.load.model.GlideUrl, Data> urlLoader) {
    this.urlLoader = urlLoader;
  }

  @Override
  public LoadData<Data> buildLoadData( Uri uri, int width, int height,
       Options options) {
    com.zqy.sutils.glide.load.model.GlideUrl glideUrl = new com.zqy.sutils.glide.load.model.GlideUrl(uri.toString());
    return urlLoader.buildLoadData(glideUrl, width, height, options);
  }

  @Override
  public boolean handles( Uri uri) {
    return SCHEMES.contains(uri.getScheme());
  }

  /**
   * Loads {@link InputStream InputStreams} from {@link Uri Uris} with http
   * or https schemes.
   */
  public static class StreamFactory implements ModelLoaderFactory<Uri, InputStream> {


    @Override
    public ModelLoader<Uri, InputStream> build(MultiModelLoaderFactory multiFactory) {
      return new UrlUriLoader<>(multiFactory.build(GlideUrl.class, InputStream.class));
    }

    @Override
    public void teardown() {
      // Do nothing.
    }
  }
}
