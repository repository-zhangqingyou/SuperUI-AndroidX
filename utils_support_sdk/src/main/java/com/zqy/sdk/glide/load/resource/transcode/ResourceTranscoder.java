package com.zqy.sdk.glide.load.resource.transcode;

import com.zqy.sdk.glide.load.engine.Resource;

/**
 * Transcodes a resource of one type to a resource of another type.
 *
 * @param <Z> The type of the resource that will be transcoded from.
 * @param <R> The type of the resource that will be transcoded to.
 */
public interface ResourceTranscoder<Z, R> {

    /**
     * Transcodes the given resource to the new resource type and returns the wew resource.
     *
     * @param toTranscode The resource to transcode.
     */
    Resource<R> transcode(Resource<Z> toTranscode);

    String getId();
}
