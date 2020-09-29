package com.zqy.sutils.glide.load.engine;

import com.zqy.sutils.glide.load.Key;

interface EngineJobListener {

  void onEngineJobComplete(com.zqy.sutils.glide.load.engine.EngineJob<?> engineJob, Key key, com.zqy.sutils.glide.load.engine.EngineResource<?> resource);

  void onEngineJobCancelled(com.zqy.sutils.glide.load.engine.EngineJob<?> engineJob, Key key);
}
