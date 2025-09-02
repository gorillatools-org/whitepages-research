package com.facebook.imagepipeline.producers;

import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.image.EncodedImage;

public final class ThumbnailSizeChecker {
    public static final ThumbnailSizeChecker INSTANCE = new ThumbnailSizeChecker();

    public static final int getAcceptableSize(int i) {
        return (int) (((float) i) * 1.3333334f);
    }

    private ThumbnailSizeChecker() {
    }

    public static final boolean isImageBigEnough(int i, int i2, ResizeOptions resizeOptions) {
        if (resizeOptions == null) {
            if (((float) getAcceptableSize(i)) < 2048.0f || getAcceptableSize(i2) < 2048) {
                return false;
            }
        } else if (getAcceptableSize(i) < resizeOptions.width || getAcceptableSize(i2) < resizeOptions.height) {
            return false;
        }
        return true;
    }

    public static final boolean isImageBigEnough(EncodedImage encodedImage, ResizeOptions resizeOptions) {
        if (encodedImage == null) {
            return false;
        }
        int rotationAngle = encodedImage.getRotationAngle();
        if (rotationAngle == 90 || rotationAngle == 270) {
            return isImageBigEnough(encodedImage.getHeight(), encodedImage.getWidth(), resizeOptions);
        }
        return isImageBigEnough(encodedImage.getWidth(), encodedImage.getHeight(), resizeOptions);
    }
}
