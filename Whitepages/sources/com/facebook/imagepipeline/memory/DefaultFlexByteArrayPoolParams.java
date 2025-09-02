package com.facebook.imagepipeline.memory;

import android.util.SparseIntArray;

public final class DefaultFlexByteArrayPoolParams {
    private static final int DEFAULT_MAX_NUM_THREADS = Runtime.getRuntime().availableProcessors();
    public static final DefaultFlexByteArrayPoolParams INSTANCE = new DefaultFlexByteArrayPoolParams();

    private DefaultFlexByteArrayPoolParams() {
    }

    public static final SparseIntArray generateBuckets(int i, int i2, int i3) {
        SparseIntArray sparseIntArray = new SparseIntArray();
        while (i <= i2) {
            sparseIntArray.put(i, i3);
            i *= 2;
        }
        return sparseIntArray;
    }

    public static final PoolParams get() {
        int i = DEFAULT_MAX_NUM_THREADS;
        return new PoolParams(4194304, i * 4194304, generateBuckets(131072, 4194304, i), 131072, 4194304, i);
    }
}
