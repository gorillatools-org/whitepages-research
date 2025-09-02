package com.facebook.imagepipeline.transcoder;

import com.facebook.common.logging.FLog;
import com.facebook.imageformat.DefaultImageFormats;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.common.RotationOptions;
import com.facebook.imagepipeline.image.EncodedImage;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

public final class DownsampleUtil {
    public static final DownsampleUtil INSTANCE = new DownsampleUtil();

    public static final int ratioToSampleSizeJPEG(float f) {
        if (f > 0.6666667f) {
            return 1;
        }
        int i = 2;
        while (true) {
            int i2 = i * 2;
            double d = 1.0d / ((double) i2);
            if (d + (((double) 0.33333334f) * d) <= ((double) f)) {
                return i;
            }
            i = i2;
        }
    }

    private DownsampleUtil() {
    }

    public static final int determineSampleSize(RotationOptions rotationOptions, ResizeOptions resizeOptions, EncodedImage encodedImage, int i) {
        int i2;
        Intrinsics.checkNotNullParameter(rotationOptions, "rotationOptions");
        Intrinsics.checkNotNullParameter(encodedImage, "encodedImage");
        if (!EncodedImage.isMetaDataAvailable(encodedImage)) {
            return 1;
        }
        float determineDownsampleRatio = determineDownsampleRatio(rotationOptions, resizeOptions, encodedImage);
        if (encodedImage.getImageFormat() == DefaultImageFormats.JPEG) {
            i2 = ratioToSampleSizeJPEG(determineDownsampleRatio);
        } else {
            i2 = ratioToSampleSize(determineDownsampleRatio);
        }
        int max = Math.max(encodedImage.getHeight(), encodedImage.getWidth());
        float f = resizeOptions != null ? resizeOptions.maxBitmapDimension : (float) i;
        while (((float) (max / i2)) > f) {
            i2 = encodedImage.getImageFormat() == DefaultImageFormats.JPEG ? i2 * 2 : i2 + 1;
        }
        return i2;
    }

    public static final int determineSampleSizeJPEG(EncodedImage encodedImage, int i, int i2) {
        Intrinsics.checkNotNullParameter(encodedImage, "encodedImage");
        int sampleSize = encodedImage.getSampleSize();
        while ((((encodedImage.getWidth() * encodedImage.getHeight()) * i) / sampleSize) / sampleSize > i2) {
            sampleSize *= 2;
        }
        return sampleSize;
    }

    public static final float determineDownsampleRatio(RotationOptions rotationOptions, ResizeOptions resizeOptions, EncodedImage encodedImage) {
        Intrinsics.checkNotNullParameter(rotationOptions, "rotationOptions");
        Intrinsics.checkNotNullParameter(encodedImage, "encodedImage");
        if (!EncodedImage.isMetaDataAvailable(encodedImage)) {
            throw new IllegalStateException("Check failed.");
        } else if (resizeOptions == null || resizeOptions.height <= 0 || resizeOptions.width <= 0 || encodedImage.getWidth() == 0 || encodedImage.getHeight() == 0) {
            return 1.0f;
        } else {
            int rotationAngle = INSTANCE.getRotationAngle(rotationOptions, encodedImage);
            boolean z = rotationAngle == 90 || rotationAngle == 270;
            int height = z ? encodedImage.getHeight() : encodedImage.getWidth();
            int width = z ? encodedImage.getWidth() : encodedImage.getHeight();
            float f = ((float) resizeOptions.width) / ((float) height);
            float f2 = ((float) resizeOptions.height) / ((float) width);
            float coerceAtLeast = RangesKt.coerceAtLeast(f, f2);
            FLog.v("DownsampleUtil", "Downsample - Specified size: %dx%d, image size: %dx%d ratio: %.1f x %.1f, ratio: %.3f", Integer.valueOf(resizeOptions.width), Integer.valueOf(resizeOptions.height), Integer.valueOf(height), Integer.valueOf(width), Float.valueOf(f), Float.valueOf(f2), Float.valueOf(coerceAtLeast));
            return coerceAtLeast;
        }
    }

    public static final int ratioToSampleSize(float f) {
        if (f > 0.6666667f) {
            return 1;
        }
        int i = 2;
        while (true) {
            double d = (double) i;
            if ((1.0d / d) + ((1.0d / (Math.pow(d, 2.0d) - d)) * ((double) 0.33333334f)) <= ((double) f)) {
                return i - 1;
            }
            i++;
        }
    }

    private final int getRotationAngle(RotationOptions rotationOptions, EncodedImage encodedImage) {
        if (!rotationOptions.useImageMetadata()) {
            return 0;
        }
        int rotationAngle = encodedImage.getRotationAngle();
        if (rotationAngle == 0 || rotationAngle == 90 || rotationAngle == 180 || rotationAngle == 270) {
            return rotationAngle;
        }
        throw new IllegalStateException("Check failed.");
    }
}
