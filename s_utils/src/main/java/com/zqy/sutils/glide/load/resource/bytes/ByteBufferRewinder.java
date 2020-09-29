package com.zqy.sutils.glide.load.resource.bytes;


import com.zqy.sutils.glide.load.data.DataRewinder;

import java.nio.ByteBuffer;

/**
 * Rewinds {@link ByteBuffer}s.
 */
public class ByteBufferRewinder implements DataRewinder<ByteBuffer> {
  private final ByteBuffer buffer;

  // Public API.
  @SuppressWarnings("WeakerAccess")
  public ByteBufferRewinder(ByteBuffer buffer) {
    this.buffer = buffer;
  }


  @Override
  public ByteBuffer rewindAndGet() {
    buffer.position(0);
    return buffer;
  }

  @Override
  public void cleanup() {
    // Do nothing.
  }

  /**
   * Factory for {@link com.zqy.sutils.glide.load.resource.bytes.ByteBufferRewinder}.
   */
  public static class Factory implements DataRewinder.Factory<ByteBuffer> {


    @Override
    public DataRewinder<ByteBuffer> build(ByteBuffer data) {
      return new ByteBufferRewinder(data);
    }


    @Override
    public Class<ByteBuffer> getDataClass() {
      return ByteBuffer.class;
    }
  }
}
