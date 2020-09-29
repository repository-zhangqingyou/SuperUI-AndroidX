package com.zqy.sutils.glide.request;

/**
 * An interface for coordinating multiple requests with the same {@link
 * com.zqy.sutils.glide.request.target.Target}.
 */
public interface RequestCoordinator {

  /**
   * Returns true if the {@link com.zqy.sutils.glide.request.Request} can display a loaded bitmap.
   *
   * @param request The {@link com.zqy.sutils.glide.request.Request} requesting permission to display a bitmap.
   */
  boolean canSetImage(com.zqy.sutils.glide.request.Request request);

  /**
   * Returns true if the {@link com.zqy.sutils.glide.request.Request} can display a placeholder.
   *
   * @param request The {@link com.zqy.sutils.glide.request.Request} requesting permission to display a placeholder.
   */
  boolean canNotifyStatusChanged(com.zqy.sutils.glide.request.Request request);

  /**
   * Returns {@code true} if the {@link com.zqy.sutils.glide.request.Request} can clear the
   * {@link com.zqy.sutils.glide.request.target.Target}.
   */
  boolean canNotifyCleared(com.zqy.sutils.glide.request.Request request);

  /**
   * Returns true if any coordinated {@link com.zqy.sutils.glide.request.Request} has successfully completed.
   *
   * @see com.zqy.sutils.glide.request.Request#isComplete()
   */
  boolean isAnyResourceSet();

  /**
   * Must be called when a {@link com.zqy.sutils.glide.request.Request} coordinated by this object completes successfully.
   */
  void onRequestSuccess(com.zqy.sutils.glide.request.Request request);

  /** Must be called when a {@link com.zqy.sutils.glide.request.Request} coordinated by this object fails. */
  void onRequestFailed(Request request);
}
