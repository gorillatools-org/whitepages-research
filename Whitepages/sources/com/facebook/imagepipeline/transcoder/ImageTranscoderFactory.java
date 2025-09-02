package com.facebook.imagepipeline.transcoder;

import com.facebook.imageformat.ImageFormat;

public interface ImageTranscoderFactory {
    ImageTranscoder createImageTranscoder(ImageFormat imageFormat, boolean z);
}
