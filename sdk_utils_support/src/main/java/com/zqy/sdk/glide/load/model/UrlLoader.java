package com.zqy.sdk.glide.load.model;

import com.zqy.sdk.glide.load.data.DataFetcher;

import java.net.URL;

/**
 * A wrapper class that translates {@link java.net.URL} objects into {@linkcom.zqy.sutils.glide.load.model.GlideUrl}
 * objects and then uses the wrapped {@linkcom.zqy.sutils.glide.load.model.ModelLoader} for
 * {@linkcom.zqy.sutils.glide.load.model.GlideUrl}s to load the data.
 *
 * @param <T> The type of data that will be loaded from the {@link java.net.URL}s.
 */
public class UrlLoader<T> implements ModelLoader<URL, T> {
    private final ModelLoader<GlideUrl, T> glideUrlLoader;

    public UrlLoader(ModelLoader<GlideUrl, T> glideUrlLoader) {
        this.glideUrlLoader = glideUrlLoader;
    }

    @Override
    public DataFetcher<T> getResourceFetcher(URL model, int width, int height) {
        return glideUrlLoader.getResourceFetcher(new GlideUrl(model), width, height);
    }
}
