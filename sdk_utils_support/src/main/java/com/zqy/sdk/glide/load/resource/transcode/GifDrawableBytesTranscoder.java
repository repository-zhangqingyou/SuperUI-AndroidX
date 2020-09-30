package com.zqy.sdk.glide.load.resource.transcode;

import com.zqy.sdk.glide.load.engine.Resource;
import com.zqy.sdk.glide.load.resource.bytes.BytesResource;
import com.zqy.sdk.glide.load.resource.gif.GifDrawable;

/**
 * An {@linkcom.zqy.sutils.glide.load.resource.transcode.ResourceTranscoder} that converts
 * {@linkcom.zqy.sutils.glide.load.resource.gif.GifDrawable} into bytes by obtaining the original bytes of the GIF from
 * the {@linkcom.zqy.sutils.glide.load.resource.gif.GifDrawable}.
 */
public class GifDrawableBytesTranscoder implements ResourceTranscoder<GifDrawable, byte[]> {
    @Override
    public Resource<byte[]> transcode(Resource<GifDrawable> toTranscode) {
        GifDrawable gifData = toTranscode.get();
        return new BytesResource(gifData.getData());
    }

    @Override
    public String getId() {
        return "GifDrawableBytesTranscoder.com.bumptech.glide.load.resource.transcode";
    }
}
