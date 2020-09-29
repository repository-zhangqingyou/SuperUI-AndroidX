package com.zqy.sutils.glide.load.resource.bitmap;

import android.content.Context;
import android.os.ParcelFileDescriptor;

import com.zqy.sutils.glide.Glide;
import com.zqy.sutils.glide.load.engine.bitmap_recycle.BitmapPool;

/**
 * An {@link com.zqy.sutils.glide.load.ResourceDecoder} that can decode a thumbnail frame
 * {@link android.graphics.Bitmap} from a {@link ParcelFileDescriptor} containing a
 * video.
 *
 * @see android.media.MediaMetadataRetriever
 *
 * @deprecated Use {@link com.zqy.sutils.glide.load.resource.bitmap.VideoDecoder#parcel(BitmapPool)} instead. This class may be removed and
 * {@link com.zqy.sutils.glide.load.resource.bitmap.VideoDecoder} may become final in a future version of Glide.
 */
@Deprecated
public class VideoBitmapDecoder extends VideoDecoder<ParcelFileDescriptor> {

  @SuppressWarnings("unused")
  public VideoBitmapDecoder(Context context) {
    this(Glide.get(context).getBitmapPool());
  }

  // Public API
  @SuppressWarnings("WeakerAccess")
  public VideoBitmapDecoder(BitmapPool bitmapPool) {
    super(bitmapPool, new ParcelFileDescriptorInitializer());
  }
}
