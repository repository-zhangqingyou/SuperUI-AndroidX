package com.zqy.sdk.utils.glide.load.model.stream;

import android.content.Context;
import android.net.Uri;

import com.zqy.sdk.utils.glide.Glide;
import com.zqy.sdk.utils.glide.load.model.GenericLoaderFactory;
import com.zqy.sdk.utils.glide.load.model.ModelLoader;
import com.zqy.sdk.utils.glide.load.model.ModelLoaderFactory;
import com.zqy.sdk.utils.glide.load.model.StringLoader;

import java.io.InputStream;

/**
 * A {@link ModelLoader} for translating {@link String} models, such as file paths or remote urls, into
 * {@link InputStream} data.
 */
public class StreamStringLoader extends StringLoader<InputStream> implements StreamModelLoader<String> {

    /**
     * The default factory for {@linkcom.zqy.sutils.glide.load.model.stream.StreamStringLoader}s.
     */
    public static class Factory implements ModelLoaderFactory<String, InputStream> {
        @Override
        public ModelLoader<String, InputStream> build(Context context, GenericLoaderFactory factories) {
            return new StreamStringLoader(factories.buildModelLoader(Uri.class, InputStream.class));
        }

        @Override
        public void teardown() {
            // Do nothing.
        }
    }

    public StreamStringLoader(Context context) {
        this(Glide.buildStreamModelLoader(Uri.class, context));
    }

    public StreamStringLoader(ModelLoader<Uri, InputStream> uriLoader) {
        super(uriLoader);
    }
}
