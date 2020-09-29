package com.zqy.sutils.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.util.Log;

import com.zqy.sutils.glide.load.EncodeStrategy;
import com.zqy.sutils.glide.load.Option;
import com.zqy.sutils.glide.load.Options;
import com.zqy.sutils.glide.load.ResourceEncoder;
import com.zqy.sutils.glide.load.data.BufferedOutputStream;
import com.zqy.sutils.glide.load.engine.Resource;
import com.zqy.sutils.glide.load.engine.bitmap_recycle.ArrayPool;
import com.zqy.sutils.glide.util.LogTime;
import com.zqy.sutils.glide.util.Util;
import com.zqy.sutils.glide.util.pool.GlideTrace;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * An {@link ResourceEncoder} that writes {@link Bitmap}s
 * to {@link OutputStream}s.
 *
 * <p> {@link Bitmap}s that return true from
 * {@link Bitmap#hasAlpha ()}} are written using
 * {@link Bitmap.CompressFormat#PNG}
 * to preserve alpha and all other bitmaps are written using
 * {@link Bitmap.CompressFormat#JPEG}. </p>
 *
 * @see Bitmap#compress(Bitmap.CompressFormat, int,
 * OutputStream)
 */
public class BitmapEncoder implements ResourceEncoder<Bitmap> {
  /**
   * An integer option between 0 and 100 that is used as the compression quality.
   *
   * <p> Defaults to 90. </p>
   */
  public static final Option<Integer> COMPRESSION_QUALITY = Option.memory(
      "com.zqy.sutils.glide.load.resource.bitmap.BitmapEncoder.CompressionQuality", 90);

  /**
   * An {@link Bitmap.CompressFormat} option used as the format to encode
   * the {@link Bitmap}.
   *
   * <p> Defaults to {@link Bitmap.CompressFormat#JPEG} for images without alpha
   * and {@link Bitmap.CompressFormat#PNG} for images with alpha. </p>
   */
  public static final Option<Bitmap.CompressFormat> COMPRESSION_FORMAT = Option.memory(
      "com.zqy.sutils.glide.load.resource.bitmap.BitmapEncoder.CompressionFormat");

  private static final String TAG = "BitmapEncoder";

  private final ArrayPool arrayPool;

  public BitmapEncoder( ArrayPool arrayPool) {
    this.arrayPool = arrayPool;
  }

  /**
   * @deprecated Use {@link #BitmapEncoder(ArrayPool)} instead.
   */
  @Deprecated
  public BitmapEncoder() {
    arrayPool = null;
  }

  @Override
  public boolean encode( Resource<Bitmap> resource,  File file,
       Options options) {
    final Bitmap bitmap = resource.get();
    Bitmap.CompressFormat format = getFormat(bitmap, options);
    GlideTrace.
        beginSectionFormat("encode: [%dx%d] %s", bitmap.getWidth(), bitmap.getHeight(), format);
    try {
      long start = LogTime.getLogTime();
      int quality = options.get(COMPRESSION_QUALITY);

      boolean success = false;
      OutputStream os = null;
      try {
        os = new FileOutputStream(file);
        if (arrayPool != null) {
          os = new BufferedOutputStream(os, arrayPool);
        }
        bitmap.compress(format, quality, os);
        os.close();
        success = true;
      } catch (IOException e) {
        if (Log.isLoggable(TAG, Log.DEBUG)) {
          Log.d(TAG, "Failed to encode Bitmap", e);
        }
      } finally {
        if (os != null) {
          try {
            os.close();
          } catch (IOException e) {
            // Do nothing.
          }
        }
      }

      if (Log.isLoggable(TAG, Log.VERBOSE)) {
        Log.v(TAG, "Compressed with type: " + format + " of size " + Util.getBitmapByteSize(bitmap)
            + " in " + LogTime.getElapsedMillis(start)
            + ", options format: " + options.get(COMPRESSION_FORMAT)
            + ", hasAlpha: " + bitmap.hasAlpha());
      }
      return success;
    } finally {
      GlideTrace.endSection();
    }
  }

  private Bitmap.CompressFormat getFormat(Bitmap bitmap, Options options) {
    Bitmap.CompressFormat format = options.get(COMPRESSION_FORMAT);
    if (format != null) {
      return format;
    } else if (bitmap.hasAlpha()) {
      return Bitmap.CompressFormat.PNG;
    } else {
      return Bitmap.CompressFormat.JPEG;
    }
  }


  @Override
  public EncodeStrategy getEncodeStrategy( Options options) {
    return EncodeStrategy.TRANSFORMED;
  }
}
