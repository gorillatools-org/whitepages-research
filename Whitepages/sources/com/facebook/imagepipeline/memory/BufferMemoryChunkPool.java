package com.facebook.imagepipeline.memory;

import com.facebook.common.internal.DoNotStrip;
import com.facebook.common.memory.MemoryTrimmableRegistry;

@DoNotStrip
public class BufferMemoryChunkPool extends MemoryChunkPool {
    @DoNotStrip
    public BufferMemoryChunkPool(MemoryTrimmableRegistry memoryTrimmableRegistry, PoolParams poolParams, PoolStatsTracker poolStatsTracker) {
        super(memoryTrimmableRegistry, poolParams, poolStatsTracker);
    }

    /* access modifiers changed from: protected */
    public BufferMemoryChunk alloc(int i) {
        return new BufferMemoryChunk(i);
    }
}
