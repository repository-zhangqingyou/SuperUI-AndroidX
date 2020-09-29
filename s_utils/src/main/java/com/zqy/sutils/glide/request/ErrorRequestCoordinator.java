package com.zqy.sutils.glide.request;

import androidx.annotation.Nullable;

/**
 * Runs a single primary {@link com.zqy.sutils.glide.request.Request} until it completes and then a fallback error request only
 * if the single primary request fails.
 */
public final class ErrorRequestCoordinator implements com.zqy.sutils.glide.request.RequestCoordinator,
        com.zqy.sutils.glide.request.Request {


  private final com.zqy.sutils.glide.request.RequestCoordinator parent;
  private com.zqy.sutils.glide.request.Request primary;
  private com.zqy.sutils.glide.request.Request error;

  public ErrorRequestCoordinator(@Nullable RequestCoordinator parent) {
    this.parent = parent;
  }

  public void setRequests(com.zqy.sutils.glide.request.Request primary, com.zqy.sutils.glide.request.Request error) {
    this.primary = primary;
    this.error = error;
  }

  @Override
  public void begin() {
    if (!primary.isRunning()) {
      primary.begin();
    }
  }

  @Override
  public void pause() {
    if (!primary.isFailed()) {
      primary.pause();
    }
    if (error.isRunning()) {
      error.pause();
    }
  }

  @Override
  public void clear() {
    primary.clear();
    // Don't check primary.isFailed() here because it will have been reset by the clear call
    // immediately before this.
    if (error.isRunning()) {
      error.clear();
    }
  }

  @Override
  public boolean isPaused() {
    return primary.isFailed() ? error.isPaused() : primary.isPaused();
  }

  @Override
  public boolean isRunning() {
    return primary.isFailed() ? error.isRunning() : primary.isRunning();
  }

  @Override
  public boolean isComplete() {
    return primary.isFailed() ? error.isComplete() : primary.isComplete();
  }

  @Override
  public boolean isResourceSet() {
    return primary.isFailed() ? error.isResourceSet() : primary.isResourceSet();
  }

  @Override
  public boolean isCancelled() {
    return primary.isFailed() ? error.isCancelled() : primary.isCancelled();
  }

  @Override
  public boolean isFailed() {
    return primary.isFailed() && error.isFailed();
  }

  @Override
  public void recycle() {
    primary.recycle();
    error.recycle();
  }

  @Override
  public boolean isEquivalentTo(com.zqy.sutils.glide.request.Request o) {
    if (o instanceof ErrorRequestCoordinator) {
      ErrorRequestCoordinator other = (ErrorRequestCoordinator) o;
      return primary.isEquivalentTo(other.primary) && error.isEquivalentTo(other.error);
    }
    return false;
  }

  @Override
  public boolean canSetImage(com.zqy.sutils.glide.request.Request request) {
    return parentCanSetImage() && isValidRequest(request);
  }

  private boolean parentCanSetImage() {
    return parent == null || parent.canSetImage(this);
  }

  @Override
  public boolean canNotifyStatusChanged(com.zqy.sutils.glide.request.Request request) {
    return parentCanNotifyStatusChanged() && isValidRequest(request);
  }

  @Override
  public boolean canNotifyCleared(com.zqy.sutils.glide.request.Request request) {
    return parentCanNotifyCleared() && isValidRequest(request);
  }

  private boolean parentCanNotifyCleared() {
    return parent == null || parent.canNotifyCleared(this);
  }

  private boolean parentCanNotifyStatusChanged() {
    return parent == null || parent.canNotifyStatusChanged(this);
  }

  private boolean isValidRequest(com.zqy.sutils.glide.request.Request request) {
    return request.equals(primary) || (primary.isFailed() && request.equals(error));
  }

  @Override
  public boolean isAnyResourceSet() {
    return parentIsAnyResourceSet() || isResourceSet();
  }

  private boolean parentIsAnyResourceSet() {
    return parent != null && parent.isAnyResourceSet();
  }

  @Override
  public void onRequestSuccess(com.zqy.sutils.glide.request.Request request) {
    if (parent != null) {
      parent.onRequestSuccess(this);
    }
  }

  @Override
  public void onRequestFailed(Request request) {
    if (!request.equals(error)) {
      if (!error.isRunning()) {
        error.begin();
      }
      return;
    }

    if (parent != null) {
      parent.onRequestFailed(this);
    }
  }
}
