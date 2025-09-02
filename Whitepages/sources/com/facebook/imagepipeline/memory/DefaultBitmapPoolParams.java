package com.facebook.imagepipeline.memory;

import android.util.SparseIntArray;

public final class DefaultBitmapPoolParams {
    private static final SparseIntArray DEFAULT_BUCKETS = new SparseIntArray(0);
    public static final DefaultBitmapPoolParams INSTANCE = new DefaultBitmapPoolParams();

    private DefaultBitmapPoolParams() {
    }

    private final int getMaxSizeHardCap() {
        int min = (int) Math.min(Runtime.getRuntime().maxMemory(), 2147483647L);
        if (min > 16777216) {
            return (min / 4) * 3;
        }
        return min / 2;
    }

    public static final PoolParams get() {
        return new PoolParams(0, INSTANCE.getMaxSizeHardCap(), DEFAULT_BUCKETS);
    }
}
