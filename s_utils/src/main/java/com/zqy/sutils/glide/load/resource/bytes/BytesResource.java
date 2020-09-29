package com.zqy.sutils.glide.load.resource.bytes;


import com.zqy.sutils.glide.load.engine.Resource;
import com.zqy.sutils.glide.util.Preconditions;

/**
 * An {@link Resource} wrapping a byte array.
 */
public class BytesResource implements Resource<byte[]> {
  private final byte[] bytes;

  public BytesResource(byte[] bytes) {
    this.bytes = Preconditions.checkNotNull(bytes);
  }


  @Override
  public Class<byte[]> getResourceClass() {
    return byte[].class;
  }

  /**
   * In most cases it will only be retrieved once (see linked methods).
   *
   * @return the same array every time, do not mutate the contents. Not a copy returned, because
   * copying the array can be prohibitively expensive and/or lead to OOMs.
   * @see com.zqy.sutils.glide.load.ResourceEncoder
   * @see com.zqy.sutils.glide.load.resource.transcode.ResourceTranscoder
   * @see com.zqy.sutils.glide.request.SingleRequest#onResourceReady
   */

  @Override
  @SuppressWarnings("PMD.MethodReturnsInternalArray")
  public byte[] get() {
    return bytes;
  }

  @Override
  public int getSize() {
    return bytes.length;
  }

  @Override
  public void recycle() {
    // Do nothing.
  }
}
