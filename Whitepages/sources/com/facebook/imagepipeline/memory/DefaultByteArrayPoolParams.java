package com.facebook.imagepipeline.memory;

import android.util.SparseIntArray;
import okhttp3.internal.http2.Http2;

public final class DefaultByteArrayPoolParams {
    public static final DefaultByteArrayPoolParams INSTANCE = new DefaultByteArrayPoolParams();

    private DefaultByteArrayPoolParams() {
    }

    public static final PoolParams get() {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sparseIntArray.put(Http2.INITIAL_MAX_FRAME_SIZE, 5);
        return new PoolParams(81920, 1048576, sparseIntArray);
    }
}
