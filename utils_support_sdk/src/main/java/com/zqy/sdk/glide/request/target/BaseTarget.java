package com.zqy.sdk.glide.request.target;

import android.graphics.drawable.Drawable;

import com.zqy.sdk.glide.request.Request;

/**
 * A base {@link Target} for loading {@linkcom.zqy.sutils.glide.load.engine.Resource}s that provides basic or empty
 * implementations for most methods.
 *
 * <p>
 *     For maximum efficiency, clear this target when you have finished using or displaying the
 *     {@linkcom.zqy.sutils.glide.load.engine.Resource} loaded into it using
 *     {@linkcom.zqy.sutils.glide.Glide#clear(Target)}.
 * </p>
 *
 * <p>
 *     For loading {@linkcom.zqy.sutils.glide.load.engine.Resource}s into {@link android.view.View}s,
 *     {@linkcom.zqy.sutils.glide.request.target.ViewTarget} or {@linkcom.zqy.sutils.glide.request.target.ImageViewTarget}
 *     are preferable.
 * </p>
 *
 * @param <Z> The type of resource that will be received by this target.
 */
public abstract class BaseTarget<Z> implements Target<Z> {

    private Request request;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRequest(Request request) {
        this.request = request;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Request getRequest() {
        return request;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onLoadCleared(Drawable placeholder) {
        // Do nothing.
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onLoadStarted(Drawable placeholder) {
        // Do nothing.
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onLoadFailed(Exception e, Drawable errorDrawable) {
        // Do nothing.
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onStart() {
        // Do nothing.
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onStop() {
        // Do nothing.
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onDestroy() {
        // Do nothing.
    }
}
