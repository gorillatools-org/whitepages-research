package com.facebook.imagepipeline.memory;

import android.annotation.TargetApi;
import com.facebook.common.internal.DoNotStrip;
import com.facebook.common.memory.MemoryTrimmableRegistry;

@TargetApi(27)
@DoNotStrip
public class AshmemMemoryChunkPool extends MemoryChunkPool {
    @DoNotStrip
    public AshmemMemoryChunkPool(MemoryTrimmableRegistry memoryTrimmableRegistry, PoolParams poolParams, PoolStatsTracker poolStatsTracker) {
        super(memoryTrimmableRegistry, poolParams, poolStatsTracker);
    }

    public AshmemMemoryChunk alloc(int i) {
        return new AshmemMemoryChunk(i);
    }
}
