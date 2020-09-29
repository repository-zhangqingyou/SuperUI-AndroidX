package com.zqy.sutils.glide.load.resource.gif;


import android.util.Log;

import com.zqy.sutils.glide.load.EncodeStrategy;
import com.zqy.sutils.glide.load.Options;
import com.zqy.sutils.glide.load.ResourceEncoder;
import com.zqy.sutils.glide.load.engine.Resource;
import com.zqy.sutils.glide.util.ByteBufferUtil;

import java.io.File;
import java.io.IOException;

/**
 * Writes the original bytes of a {@link com.zqy.sutils.glide.load.resource.gif.GifDrawable} to an
 * {@link java.io.OutputStream}.
 */
public class GifDrawableEncoder implements ResourceEncoder<com.zqy.sutils.glide.load.resource.gif.GifDrawable> {
  private static final String TAG = "GifEncoder";


  @Override
  public EncodeStrategy getEncodeStrategy( Options options) {
    return EncodeStrategy.SOURCE;
  }

  @Override
  public boolean encode( Resource<com.zqy.sutils.glide.load.resource.gif.GifDrawable> data,  File file,
                         Options options) {
    GifDrawable drawable = data.get();
    boolean success = false;
    try {
      ByteBufferUtil.toFile(drawable.getBuffer(), file);
      success = true;
    } catch (IOException e) {
      if (Log.isLoggable(TAG, Log.WARN)) {
        Log.w(TAG, "Failed to encode GIF drawable data", e);
      }
    }
    return success;
  }
}
