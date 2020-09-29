package com.zqy.sutils.glide.load.resource.transcode;


import com.zqy.sutils.glide.load.Options;
import com.zqy.sutils.glide.load.engine.Resource;
import com.zqy.sutils.glide.load.resource.bytes.BytesResource;
import com.zqy.sutils.glide.load.resource.gif.GifDrawable;
import com.zqy.sutils.glide.util.ByteBufferUtil;

import java.nio.ByteBuffer;

/**
 * An {@link com.zqy.sutils.glide.load.resource.transcode.ResourceTranscoder} that converts {@link
 * GifDrawable} into bytes by obtaining the original bytes of
 * the GIF from the {@link GifDrawable}.
 */
public class GifDrawableBytesTranscoder implements ResourceTranscoder<GifDrawable, byte[]> {

  @Override
  public Resource<byte[]> transcode( Resource<GifDrawable> toTranscode,
       Options options) {
    GifDrawable gifData = toTranscode.get();
    ByteBuffer byteBuffer = gifData.getBuffer();
    return new BytesResource(ByteBufferUtil.toBytes(byteBuffer));
  }
}
