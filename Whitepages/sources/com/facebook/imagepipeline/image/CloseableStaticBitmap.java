package com.facebook.imagepipeline.image;

import android.graphics.Bitmap;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.references.ResourceReleaser;

public interface CloseableStaticBitmap extends CloseableBitmap {
    int getExifOrientation();

    int getRotationAngle();

    static CloseableStaticBitmap of(Bitmap bitmap, ResourceReleaser resourceReleaser, QualityInfo qualityInfo, int i) {
        return of(bitmap, resourceReleaser, qualityInfo, i, 0);
    }

    static CloseableStaticBitmap of(Bitmap bitmap, ResourceReleaser resourceReleaser, QualityInfo qualityInfo, int i, int i2) {
        if (BaseCloseableStaticBitmap.shouldUseSimpleCloseableStaticBitmap()) {
            return new BaseCloseableStaticBitmap(bitmap, resourceReleaser, qualityInfo, i, i2);
        }
        return new DefaultCloseableStaticBitmap(bitmap, resourceReleaser, qualityInfo, i, i2);
    }

    static CloseableStaticBitmap of(CloseableReference closeableReference, QualityInfo qualityInfo, int i, int i2) {
        if (BaseCloseableStaticBitmap.shouldUseSimpleCloseableStaticBitmap()) {
            return new BaseCloseableStaticBitmap(closeableReference, qualityInfo, i, i2);
        }
        return new DefaultCloseableStaticBitmap(closeableReference, qualityInfo, i, i2);
    }
}
