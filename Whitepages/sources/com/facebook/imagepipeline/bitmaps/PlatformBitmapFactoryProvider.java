package com.facebook.imagepipeline.bitmaps;

import com.facebook.imagepipeline.core.CloseableReferenceFactory;
import com.facebook.imagepipeline.memory.BitmapPool;
import com.facebook.imagepipeline.memory.PoolFactory;
import com.facebook.imagepipeline.platform.PlatformDecoder;
import kotlin.jvm.internal.Intrinsics;

public final class PlatformBitmapFactoryProvider {
    public static final PlatformBitmapFactoryProvider INSTANCE = new PlatformBitmapFactoryProvider();

    private PlatformBitmapFactoryProvider() {
    }

    public static final PlatformBitmapFactory buildPlatformBitmapFactory(PoolFactory poolFactory, PlatformDecoder platformDecoder, CloseableReferenceFactory closeableReferenceFactory) {
        Intrinsics.checkNotNullParameter(poolFactory, "poolFactory");
        Intrinsics.checkNotNullParameter(platformDecoder, "platformDecoder");
        Intrinsics.checkNotNullParameter(closeableReferenceFactory, "closeableReferenceFactory");
        BitmapPool bitmapPool = poolFactory.getBitmapPool();
        Intrinsics.checkNotNullExpressionValue(bitmapPool, "getBitmapPool(...)");
        return new ArtBitmapFactory(bitmapPool, closeableReferenceFactory);
    }
}
