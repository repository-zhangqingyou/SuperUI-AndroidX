package com.zqy.sdk.glide.load.model.file_descriptor;

import android.content.Context;
import android.net.Uri;
import android.os.ParcelFileDescriptor;

import com.zqy.sdk.glide.Glide;
import com.zqy.sdk.glide.load.model.GenericLoaderFactory;
import com.zqy.sdk.glide.load.model.ModelLoader;
import com.zqy.sdk.glide.load.model.ModelLoaderFactory;
import com.zqy.sdk.glide.load.model.ResourceLoader;

/**
 * A {@link ModelLoader} For translating android resource id models into {@link ParcelFileDescriptor} data.
 */
public class FileDescriptorResourceLoader extends ResourceLoader<ParcelFileDescriptor>
        implements FileDescriptorModelLoader<Integer> {

    /**
     * The default factory for {@linkcom.zqy.sutils.glide.load.model.file_descriptor.FileDescriptorResourceLoader}s.
     */
    public static class Factory implements ModelLoaderFactory<Integer, ParcelFileDescriptor> {

        @Override
        public ModelLoader<Integer, ParcelFileDescriptor> build(Context context, GenericLoaderFactory factories) {
            return new FileDescriptorResourceLoader(context, factories.buildModelLoader(Uri.class,
                    ParcelFileDescriptor.class));
        }

        @Override
        public void teardown() {
            // Do nothing.
        }
    }

    public FileDescriptorResourceLoader(Context context) {
        this(context, Glide.buildFileDescriptorModelLoader(Uri.class, context));
    }

    public FileDescriptorResourceLoader(Context context, ModelLoader<Uri, ParcelFileDescriptor> uriLoader) {
        super(context, uriLoader);
    }
}
