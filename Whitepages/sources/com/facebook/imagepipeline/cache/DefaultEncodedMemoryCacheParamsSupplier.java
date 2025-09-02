package com.facebook.imagepipeline.cache;

import com.facebook.common.internal.Supplier;
import java.util.concurrent.TimeUnit;

public class DefaultEncodedMemoryCacheParamsSupplier implements Supplier {
    private static final long PARAMS_CHECK_INTERVAL_MS = TimeUnit.MINUTES.toMillis(5);

    public MemoryCacheParams get() {
        int maxCacheSize = getMaxCacheSize();
        return new MemoryCacheParams(maxCacheSize, Integer.MAX_VALUE, maxCacheSize, Integer.MAX_VALUE, maxCacheSize / 8, PARAMS_CHECK_INTERVAL_MS);
    }

    private int getMaxCacheSize() {
        int min = (int) Math.min(Runtime.getRuntime().maxMemory(), 2147483647L);
        if (min < 16777216) {
            return 1048576;
        }
        return min < 33554432 ? 2097152 : 4194304;
    }
}
