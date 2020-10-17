package com.zqy.sdk.utils.glide.load.resource.gifbitmap;

import android.graphics.Bitmap;

import com.zqy.sdk.utils.glide.load.Encoder;
import com.zqy.sdk.utils.glide.load.ResourceDecoder;
import com.zqy.sdk.utils.glide.load.ResourceEncoder;
import com.zqy.sdk.utils.glide.load.engine.bitmap_recycle.BitmapPool;
import com.zqy.sdk.utils.glide.load.model.ImageVideoWrapper;
import com.zqy.sdk.utils.glide.load.resource.file.FileToStreamDecoder;
import com.zqy.sdk.utils.glide.load.resource.gif.GifDrawable;
import com.zqy.sdk.utils.glide.provider.DataLoadProvider;

import java.io.File;
import java.io.InputStream;

/**
 * An {@linkcom.zqy.sutils.glide.provider.DataLoadProvider} that can load either an
 * {@linkcom.zqy.sutils.glide.load.resource.gif.GifDrawable} or an {@link Bitmap} from either an
 * {@link java.io.InputStream} or an {@link android.os.ParcelFileDescriptor}.
 */
public class ImageVideoGifDrawableLoadProvider implements DataLoadProvider<ImageVideoWrapper, GifBitmapWrapper> {
    private final ResourceDecoder<File, GifBitmapWrapper> cacheDecoder;
    private final ResourceDecoder<ImageVideoWrapper, GifBitmapWrapper> sourceDecoder;
    private final ResourceEncoder<GifBitmapWrapper> encoder;
    private final Encoder<ImageVideoWrapper> sourceEncoder;

    public ImageVideoGifDrawableLoadProvider(DataLoadProvider<ImageVideoWrapper, Bitmap> bitmapProvider,
                                             DataLoadProvider<InputStream, GifDrawable> gifProvider, BitmapPool bitmapPool) {

        final GifBitmapWrapperResourceDecoder decoder = new GifBitmapWrapperResourceDecoder(
                bitmapProvider.getSourceDecoder(),
                gifProvider.getSourceDecoder(),
                bitmapPool
        );
        cacheDecoder = new FileToStreamDecoder<GifBitmapWrapper>(new GifBitmapWrapperStreamResourceDecoder(decoder));
        sourceDecoder = decoder;
        encoder = new GifBitmapWrapperResourceEncoder(bitmapProvider.getEncoder(), gifProvider.getEncoder());

        //TODO: what about the gif provider?
        sourceEncoder = bitmapProvider.getSourceEncoder();
    }

    @Override
    public ResourceDecoder<File, GifBitmapWrapper> getCacheDecoder() {
        return cacheDecoder;
    }

    @Override
    public ResourceDecoder<ImageVideoWrapper, GifBitmapWrapper> getSourceDecoder() {
        return sourceDecoder;
    }

    @Override
    public Encoder<ImageVideoWrapper> getSourceEncoder() {
        return sourceEncoder;
    }

    @Override
    public ResourceEncoder<GifBitmapWrapper> getEncoder() {
        return encoder;
    }
}
