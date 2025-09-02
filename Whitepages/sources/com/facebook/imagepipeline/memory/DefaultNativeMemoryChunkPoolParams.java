package com.facebook.imagepipeline.memory;

import android.util.SparseIntArray;
import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import com.salesforce.marketingcloud.b;
import okhttp3.internal.http2.Http2;

public final class DefaultNativeMemoryChunkPoolParams {
    public static final DefaultNativeMemoryChunkPoolParams INSTANCE = new DefaultNativeMemoryChunkPoolParams();

    private DefaultNativeMemoryChunkPoolParams() {
    }

    public static final PoolParams get() {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sparseIntArray.put(1024, 5);
        sparseIntArray.put(b.u, 5);
        sparseIntArray.put(b.v, 5);
        sparseIntArray.put(UserMetadata.MAX_INTERNAL_KEY_SIZE, 5);
        sparseIntArray.put(Http2.INITIAL_MAX_FRAME_SIZE, 5);
        sparseIntArray.put(32768, 5);
        sparseIntArray.put(65536, 5);
        sparseIntArray.put(131072, 5);
        sparseIntArray.put(262144, 2);
        sparseIntArray.put(524288, 2);
        sparseIntArray.put(1048576, 2);
        DefaultNativeMemoryChunkPoolParams defaultNativeMemoryChunkPoolParams = INSTANCE;
        return new PoolParams(defaultNativeMemoryChunkPoolParams.getMaxSizeSoftCap(), defaultNativeMemoryChunkPoolParams.getMaxSizeHardCap(), sparseIntArray);
    }

    private final int getMaxSizeSoftCap() {
        int min = (int) Math.min(Runtime.getRuntime().maxMemory(), 2147483647L);
        if (min < 16777216) {
            return 3145728;
        }
        return min < 33554432 ? 6291456 : 12582912;
    }

    private final int getMaxSizeHardCap() {
        int min = (int) Math.min(Runtime.getRuntime().maxMemory(), 2147483647L);
        if (min < 16777216) {
            return min / 2;
        }
        return (min / 4) * 3;
    }
}
