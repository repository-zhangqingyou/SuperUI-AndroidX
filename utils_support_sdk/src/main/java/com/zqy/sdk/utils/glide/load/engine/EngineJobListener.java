package com.zqy.sdk.utils.glide.load.engine;

import com.zqy.sdk.utils.glide.load.Key;

interface EngineJobListener {

    void onEngineJobComplete(Key key, EngineResource<?> resource);

    void onEngineJobCancelled(EngineJob engineJob, Key key);
}
