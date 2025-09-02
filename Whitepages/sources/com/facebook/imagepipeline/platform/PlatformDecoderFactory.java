package com.facebook.imagepipeline.platform;

import androidx.core.util.Pools$Pool;
import androidx.core.util.Pools$SynchronizedPool;
import com.facebook.common.memory.DecodeBufferHelper;
import com.facebook.imagepipeline.memory.BitmapPool;
import com.facebook.imagepipeline.memory.PoolFactory;
import java.nio.ByteBuffer;
import kotlin.jvm.internal.Intrinsics;

public final class PlatformDecoderFactory {
    public static final PlatformDecoderFactory INSTANCE = new PlatformDecoderFactory();

    private PlatformDecoderFactory() {
    }

    public static final PlatformDecoder buildPlatformDecoder(PoolFactory poolFactory, boolean z, boolean z2, PlatformDecoderOptions platformDecoderOptions) {
        Intrinsics.checkNotNullParameter(poolFactory, "poolFactory");
        Intrinsics.checkNotNullParameter(platformDecoderOptions, "platformDecoderOptions");
        BitmapPool bitmapPool = poolFactory.getBitmapPool();
        Intrinsics.checkNotNullExpressionValue(bitmapPool, "getBitmapPool(...)");
        return new OreoDecoder(bitmapPool, createPool(poolFactory, z2), platformDecoderOptions);
    }

    public static final Pools$Pool createPool(PoolFactory poolFactory, boolean z) {
        Intrinsics.checkNotNullParameter(poolFactory, "poolFactory");
        if (z) {
            DecodeBufferHelper decodeBufferHelper = DecodeBufferHelper.INSTANCE;
            Intrinsics.checkNotNullExpressionValue(decodeBufferHelper, "INSTANCE");
            return decodeBufferHelper;
        }
        int flexByteArrayPoolMaxNumThreads = poolFactory.getFlexByteArrayPoolMaxNumThreads();
        Pools$SynchronizedPool pools$SynchronizedPool = new Pools$SynchronizedPool(flexByteArrayPoolMaxNumThreads);
        for (int i = 0; i < flexByteArrayPoolMaxNumThreads; i++) {
            ByteBuffer allocate = ByteBuffer.allocate(DecodeBufferHelper.getRecommendedDecodeBufferSize());
            Intrinsics.checkNotNullExpressionValue(allocate, "allocate(...)");
            pools$SynchronizedPool.release(allocate);
        }
        return pools$SynchronizedPool;
    }
}
