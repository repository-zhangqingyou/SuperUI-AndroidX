package com.zqy.sutils.glide.load.resource.transcode;

import android.graphics.Bitmap;

import com.zqy.sutils.glide.load.Options;
import com.zqy.sutils.glide.load.engine.Resource;
import com.zqy.sutils.glide.load.resource.bytes.BytesResource;

import java.io.ByteArrayOutputStream;

/**
 * An {@link com.zqy.sutils.glide.load.resource.transcode.ResourceTranscoder} that converts {@link
 * Bitmap}s into byte arrays using {@link Bitmap#compress
 * (android.graphics.Bitmap.CompressFormat,
 * int, java.io.OutputStream)}.
 */
public class BitmapBytesTranscoder implements ResourceTranscoder<Bitmap, byte[]> {
  private final Bitmap.CompressFormat compressFormat;
  private final int quality;

  public BitmapBytesTranscoder() {
    this(Bitmap.CompressFormat.JPEG, 100);
  }

  // Public API.
  @SuppressWarnings("WeakerAccess")
  public BitmapBytesTranscoder( Bitmap.CompressFormat compressFormat, int quality) {
    this.compressFormat = compressFormat;
    this.quality = quality;
  }


  @Override
  public Resource<byte[]> transcode( Resource<Bitmap> toTranscode,
       Options options) {
    ByteArrayOutputStream os = new ByteArrayOutputStream();
    toTranscode.get().compress(compressFormat, quality, os);
    toTranscode.recycle();
    return new BytesResource(os.toByteArray());
  }
}
