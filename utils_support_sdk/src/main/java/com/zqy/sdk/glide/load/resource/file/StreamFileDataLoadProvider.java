package com.zqy.sdk.glide.load.resource.file;

import com.zqy.sdk.glide.load.Encoder;
import com.zqy.sdk.glide.load.ResourceDecoder;
import com.zqy.sdk.glide.load.ResourceEncoder;
import com.zqy.sdk.glide.load.engine.Resource;
import com.zqy.sdk.glide.load.model.StreamEncoder;
import com.zqy.sdk.glide.load.resource.NullResourceEncoder;
import com.zqy.sdk.glide.provider.DataLoadProvider;

import java.io.File;
import java.io.InputStream;

/**
 * An {@linkcom.zqy.sutils.glide.provider.DataLoadProvider} that provides encoders and decoders for for obtaining a
 * cache file from {@link java.io.InputStream} data.
 */
public class StreamFileDataLoadProvider implements DataLoadProvider<InputStream, File> {
    private static final ErrorSourceDecoder ERROR_DECODER = new ErrorSourceDecoder();

    private final ResourceDecoder<File, File> cacheDecoder;
    private final Encoder<InputStream> encoder;

    public StreamFileDataLoadProvider() {
        cacheDecoder = new FileDecoder();
        encoder = new StreamEncoder();
    }

    @Override
    public ResourceDecoder<File, File> getCacheDecoder() {
        return cacheDecoder;
    }

    @Override
    public ResourceDecoder<InputStream, File> getSourceDecoder() {
        return ERROR_DECODER;
    }

    @Override
    public Encoder<InputStream> getSourceEncoder() {
        return encoder;
    }

    @Override
    public ResourceEncoder<File> getEncoder() {
        return NullResourceEncoder.get();
    }

    private static class ErrorSourceDecoder implements ResourceDecoder<InputStream, File> {
        @Override
        public Resource<File> decode(InputStream source, int width, int height) {
            throw new Error("You cannot decode a File from an InputStream by default,"
                    + " try either #diskCacheStratey(DiskCacheStrategy.SOURCE) to avoid this call or"
                    + " #decoder(ResourceDecoder) to replace this Decoder");
        }

        @Override
        public String getId() {
            return "";
        }
    }
}
