package com.zqy.sutils.glide.load.model;


import android.util.Log;

import com.zqy.sutils.glide.Priority;
import com.zqy.sutils.glide.load.DataSource;
import com.zqy.sutils.glide.load.Options;
import com.zqy.sutils.glide.load.data.DataFetcher;
import com.zqy.sutils.glide.signature.ObjectKey;
import com.zqy.sutils.glide.util.ByteBufferUtil;
import com.zqy.sutils.glide.util.Synthetic;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * Loads {@link ByteBuffer}s using NIO for {@link File}.
 */
public class ByteBufferFileLoader implements com.zqy.sutils.glide.load.model.ModelLoader<File, ByteBuffer> {
  private static final String TAG = "ByteBufferFileLoader";

  @Override
  public LoadData<ByteBuffer> buildLoadData( File file, int width, int height,
       Options options) {
    return new LoadData<>(new ObjectKey(file), new ByteBufferFetcher(file));
  }

  @Override
  public boolean handles( File file) {
    return true;
  }

  /**
   * Factory for {@link com.zqy.sutils.glide.load.model.ByteBufferFileLoader}.
   */
  public static class Factory implements ModelLoaderFactory<File, ByteBuffer> {


    @Override
    public ModelLoader<File, ByteBuffer> build( MultiModelLoaderFactory multiFactory) {
      return new ByteBufferFileLoader();
    }

    @Override
    public void teardown() {
      // Do nothing.
    }
  }

  private static final class ByteBufferFetcher implements DataFetcher<ByteBuffer> {

    private final File file;

    @Synthetic
    @SuppressWarnings("WeakerAccess")
    ByteBufferFetcher(File file) {
      this.file = file;
    }

    @Override
    public void loadData( Priority priority,
         DataCallback<? super ByteBuffer> callback) {
      ByteBuffer result;
      try {
        result = ByteBufferUtil.fromFile(file);
      } catch (IOException e) {
        if (Log.isLoggable(TAG, Log.DEBUG)) {
          Log.d(TAG, "Failed to obtain ByteBuffer for file", e);
        }
        callback.onLoadFailed(e);
        return;
      }

      callback.onDataReady(result);
    }

    @Override
    public void cleanup() {
      // Do nothing.
    }

    @Override
    public void cancel() {
      // Do nothing.
    }


    @Override
    public Class<ByteBuffer> getDataClass() {
      return ByteBuffer.class;
    }


    @Override
    public DataSource getDataSource() {
      return DataSource.LOCAL;
    }
  }
}
