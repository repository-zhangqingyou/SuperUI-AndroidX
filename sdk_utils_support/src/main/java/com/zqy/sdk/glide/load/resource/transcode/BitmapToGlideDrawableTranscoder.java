package com.zqy.sdk.glide.load.resource.transcode;

import android.content.Context;
import android.graphics.Bitmap;

import com.zqy.sdk.glide.load.engine.Resource;
import com.zqy.sdk.glide.load.resource.drawable.GlideDrawable;

/**
 * A wrapper for {@linkcom.zqy.sutils.glide.load.resource.transcode.GlideBitmapDrawableTranscoder} that transcodes
 * to {@linkcom.zqy.sutils.glide.load.resource.drawable.GlideDrawable} rather than
 * {@linkcom.zqy.sutils.glide.load.resource.bitmap.GlideBitmapDrawable}.
 *
 * TODO: use ? extends GlideDrawable rather than GlideDrawable directly and remove this class.
 */
public class BitmapToGlideDrawableTranscoder implements ResourceTranscoder<Bitmap, GlideDrawable> {

    private final GlideBitmapDrawableTranscoder glideBitmapDrawableTranscoder;

    public BitmapToGlideDrawableTranscoder(Context context) {
        this(new GlideBitmapDrawableTranscoder(context));
    }

    public BitmapToGlideDrawableTranscoder(GlideBitmapDrawableTranscoder glideBitmapDrawableTranscoder) {
        this.glideBitmapDrawableTranscoder = glideBitmapDrawableTranscoder;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Resource<GlideDrawable> transcode(Resource<Bitmap> toTranscode) {
        return (Resource<GlideDrawable>) (Resource<? extends GlideDrawable>)
                glideBitmapDrawableTranscoder.transcode(toTranscode);
    }

    @Override
    public String getId() {
        return glideBitmapDrawableTranscoder.getId();
    }
}
