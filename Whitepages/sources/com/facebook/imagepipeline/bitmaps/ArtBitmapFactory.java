package com.facebook.imagepipeline.bitmaps;

import android.graphics.Bitmap;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.core.CloseableReferenceFactory;
import com.facebook.imagepipeline.memory.BitmapPool;
import com.facebook.imageutils.BitmapUtil;
import kotlin.jvm.internal.Intrinsics;

public final class ArtBitmapFactory extends PlatformBitmapFactory {
    private final BitmapPool bitmapPool;
    private final CloseableReferenceFactory closeableReferenceFactory;

    public ArtBitmapFactory(BitmapPool bitmapPool2, CloseableReferenceFactory closeableReferenceFactory2) {
        Intrinsics.checkNotNullParameter(bitmapPool2, "bitmapPool");
        Intrinsics.checkNotNullParameter(closeableReferenceFactory2, "closeableReferenceFactory");
        this.bitmapPool = bitmapPool2;
        this.closeableReferenceFactory = closeableReferenceFactory2;
    }

    public CloseableReference createBitmapInternal(int i, int i2, Bitmap.Config config) {
        Intrinsics.checkNotNullParameter(config, "bitmapConfig");
        Bitmap bitmap = (Bitmap) this.bitmapPool.get(BitmapUtil.getSizeInByteForBitmap(i, i2, config));
        if (bitmap.getAllocationByteCount() >= i * i2 * BitmapUtil.getPixelSizeForBitmapConfig(config)) {
            bitmap.reconfigure(i, i2, config);
            CloseableReference create = this.closeableReferenceFactory.create(bitmap, this.bitmapPool);
            Intrinsics.checkNotNullExpressionValue(create, "create(...)");
            return create;
        }
        throw new IllegalStateException("Check failed.");
    }
}
