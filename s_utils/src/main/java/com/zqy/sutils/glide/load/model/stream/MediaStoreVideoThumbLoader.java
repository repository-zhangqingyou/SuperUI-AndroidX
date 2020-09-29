package com.zqy.sutils.glide.load.model.stream;

import android.content.Context;
import android.net.Uri;

import com.zqy.sutils.glide.load.Options;
import com.zqy.sutils.glide.load.data.mediastore.MediaStoreUtil;
import com.zqy.sutils.glide.load.data.mediastore.ThumbFetcher;
import com.zqy.sutils.glide.load.model.ModelLoader;
import com.zqy.sutils.glide.load.model.ModelLoaderFactory;
import com.zqy.sutils.glide.load.model.MultiModelLoaderFactory;
import com.zqy.sutils.glide.load.resource.bitmap.VideoDecoder;
import com.zqy.sutils.glide.signature.ObjectKey;

import java.io.InputStream;

/**
 * Loads {@link InputStream}s from media store video {@link Uri}s that point to pre-generated
 * thumbnails for those {@link Uri}s in the media store.
 *
 * <p>If {@link VideoDecoder#TARGET_FRAME} is set with a non-null value that is not equal to
 * {@link VideoDecoder#DEFAULT_FRAME}, this loader will always return {@code null}. The media
 * store does not use a defined frame to generate the thumbnail, so we cannot accurately fulfill
 * requests for specific frames.
 */
public class MediaStoreVideoThumbLoader implements ModelLoader<Uri, InputStream> {
  private final Context context;

  // Public API.
  @SuppressWarnings("WeakerAccess")
  public MediaStoreVideoThumbLoader(Context context) {
    this.context = context.getApplicationContext();
  }

  @Override

  public LoadData<InputStream> buildLoadData( Uri model, int width, int height,
       Options options) {
    if (MediaStoreUtil.isThumbnailSize(width, height) && isRequestingDefaultFrame(options)) {
      return new LoadData<>(new ObjectKey(model), ThumbFetcher.buildVideoFetcher(context, model));
    } else {
      return null;
    }
  }

  private boolean isRequestingDefaultFrame(Options options) {
    Long specifiedFrame = options.get(VideoDecoder.TARGET_FRAME);
    return specifiedFrame != null && specifiedFrame == VideoDecoder.DEFAULT_FRAME;
  }

  @Override
  public boolean handles( Uri model) {
    return MediaStoreUtil.isMediaStoreVideoUri(model);
  }

  /**
   * Loads {@link InputStream}s from media store image {@link Uri}s that point to pre-generated
   * thumbnails for those {@link Uri}s in the media store.
   */
  public static class Factory implements ModelLoaderFactory<Uri, InputStream> {

    private final Context context;

    public Factory(Context context) {
      this.context = context;
    }


    @Override
    public ModelLoader<Uri, InputStream> build(MultiModelLoaderFactory multiFactory) {
      return new MediaStoreVideoThumbLoader(context);
    }

    @Override
    public void teardown() {
      // Do nothing.
    }
  }
}
