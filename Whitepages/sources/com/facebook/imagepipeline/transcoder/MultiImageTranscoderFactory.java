package com.facebook.imagepipeline.transcoder;

import com.facebook.imageformat.ImageFormat;
import com.facebook.imagepipeline.core.NativeCodeSetup;
import com.facebook.imagepipeline.nativecode.NativeImageTranscoderFactory;
import kotlin.jvm.internal.Intrinsics;

public final class MultiImageTranscoderFactory implements ImageTranscoderFactory {
    private final boolean ensureTranscoderLibraryLoaded;
    private final Integer imageTranscoderType;
    private final int maxBitmapSize;
    private final ImageTranscoderFactory primaryImageTranscoderFactory;
    private final boolean useDownSamplingRatio;

    public MultiImageTranscoderFactory(int i, boolean z, ImageTranscoderFactory imageTranscoderFactory, Integer num, boolean z2) {
        this.maxBitmapSize = i;
        this.useDownSamplingRatio = z;
        this.primaryImageTranscoderFactory = imageTranscoderFactory;
        this.imageTranscoderType = num;
        this.ensureTranscoderLibraryLoaded = z2;
    }

    public ImageTranscoder createImageTranscoder(ImageFormat imageFormat, boolean z) {
        Intrinsics.checkNotNullParameter(imageFormat, "imageFormat");
        ImageTranscoder customImageTranscoder = getCustomImageTranscoder(imageFormat, z);
        if (customImageTranscoder == null) {
            customImageTranscoder = getImageTranscoderWithType(imageFormat, z);
        }
        if (customImageTranscoder == null && NativeCodeSetup.getUseNativeCode()) {
            customImageTranscoder = getNativeImageTranscoder(imageFormat, z);
        }
        return customImageTranscoder == null ? getSimpleImageTranscoder(imageFormat, z) : customImageTranscoder;
    }

    private final ImageTranscoder getCustomImageTranscoder(ImageFormat imageFormat, boolean z) {
        ImageTranscoderFactory imageTranscoderFactory = this.primaryImageTranscoderFactory;
        if (imageTranscoderFactory != null) {
            return imageTranscoderFactory.createImageTranscoder(imageFormat, z);
        }
        return null;
    }

    private final ImageTranscoder getNativeImageTranscoder(ImageFormat imageFormat, boolean z) {
        return NativeImageTranscoderFactory.getNativeImageTranscoderFactory(this.maxBitmapSize, this.useDownSamplingRatio, this.ensureTranscoderLibraryLoaded).createImageTranscoder(imageFormat, z);
    }

    private final ImageTranscoder getSimpleImageTranscoder(ImageFormat imageFormat, boolean z) {
        ImageTranscoder createImageTranscoder = new SimpleImageTranscoderFactory(this.maxBitmapSize).createImageTranscoder(imageFormat, z);
        Intrinsics.checkNotNullExpressionValue(createImageTranscoder, "createImageTranscoder(...)");
        return createImageTranscoder;
    }

    private final ImageTranscoder getImageTranscoderWithType(ImageFormat imageFormat, boolean z) {
        Integer num = this.imageTranscoderType;
        if (num == null) {
            return null;
        }
        if (num != null && num.intValue() == 0) {
            return getNativeImageTranscoder(imageFormat, z);
        }
        if (num != null && num.intValue() == 1) {
            return getSimpleImageTranscoder(imageFormat, z);
        }
        throw new IllegalArgumentException("Invalid ImageTranscoderType");
    }
}
