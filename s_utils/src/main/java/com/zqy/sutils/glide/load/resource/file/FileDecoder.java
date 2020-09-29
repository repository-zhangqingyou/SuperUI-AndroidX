package com.zqy.sutils.glide.load.resource.file;


import com.zqy.sutils.glide.load.Options;
import com.zqy.sutils.glide.load.ResourceDecoder;
import com.zqy.sutils.glide.load.engine.Resource;

import java.io.File;

/**
 * A simple {@link ResourceDecoder} that creates resource for a given {@link
 * File}.
 */
public class FileDecoder implements ResourceDecoder<File, File> {

  @Override
  public boolean handles( File source,  Options options) {
    return true;
  }

  @Override
  public Resource<File> decode( File source, int width, int height,
       Options options) {
    return new FileResource(source);
  }
}
