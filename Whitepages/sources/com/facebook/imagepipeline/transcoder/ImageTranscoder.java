package com.facebook.imagepipeline.transcoder;

import android.graphics.ColorSpace;
import com.facebook.imageformat.ImageFormat;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.common.RotationOptions;
import com.facebook.imagepipeline.image.EncodedImage;
import java.io.OutputStream;

public interface ImageTranscoder {
    boolean canResize(EncodedImage encodedImage, RotationOptions rotationOptions, ResizeOptions resizeOptions);

    boolean canTranscode(ImageFormat imageFormat);

    String getIdentifier();

    ImageTranscodeResult transcode(EncodedImage encodedImage, OutputStream outputStream, RotationOptions rotationOptions, ResizeOptions resizeOptions, ImageFormat imageFormat, Integer num, ColorSpace colorSpace);
}
