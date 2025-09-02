package com.facebook.imagepipeline.producers;

import com.facebook.imagepipeline.common.ResizeOptions;

public interface ThumbnailProducer extends Producer {
    boolean canProvideImageForSize(ResizeOptions resizeOptions);
}
