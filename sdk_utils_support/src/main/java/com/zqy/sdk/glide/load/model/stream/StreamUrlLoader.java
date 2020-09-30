package com.zqy.sdk.glide.load.model.stream;

import android.content.Context;

import com.zqy.sdk.glide.load.model.GenericLoaderFactory;
import com.zqy.sdk.glide.load.model.GlideUrl;
import com.zqy.sdk.glide.load.model.ModelLoader;
import com.zqy.sdk.glide.load.model.ModelLoaderFactory;
import com.zqy.sdk.glide.load.model.UrlLoader;

import java.io.InputStream;
import java.net.URL;

/**
 * A wrapper class that translates {@link java.net.URL} objects into {@linkcom.zqy.sutils.glide.load.model.GlideUrl}
 * objects and then uses the wrapped {@linkcom.zqy.sutils.glide.load.model.ModelLoader} for
 * {@linkcom.zqy.sutils.glide.load.model.GlideUrl}s to load the {@link java.io.InputStream} data.
 */
public class StreamUrlLoader extends UrlLoader<InputStream> {

    /**
     * The default factory for {@linkcom.zqy.sutils.glide.load.model.stream.StreamUrlLoader}s.
     */
    public static class Factory implements ModelLoaderFactory<URL, InputStream> {
        @Override
        public ModelLoader<URL, InputStream> build(Context context, GenericLoaderFactory factories) {
            return new StreamUrlLoader(factories.buildModelLoader(GlideUrl.class, InputStream.class));
        }

        @Override
        public void teardown() {
            // Do nothing.
        }
    }

    public StreamUrlLoader(ModelLoader<GlideUrl, InputStream> glideUrlLoader) {
        super(glideUrlLoader);
    }
}
