package com.facebook.imagepipeline.memory;

import android.util.SparseIntArray;
import com.facebook.common.memory.ByteArrayPool;
import com.facebook.common.memory.MemoryTrimmableRegistry;
import com.facebook.imagepipeline.memory.BasePool;
import kotlin.jvm.internal.Intrinsics;

public class GenericByteArrayPool extends BasePool implements ByteArrayPool {
    private final int[] bucketSizes;

    /* access modifiers changed from: protected */
    public void free(byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "value");
    }

    /* access modifiers changed from: protected */
    public int getSizeInBytes(int i) {
        return i;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public GenericByteArrayPool(MemoryTrimmableRegistry memoryTrimmableRegistry, PoolParams poolParams, PoolStatsTracker poolStatsTracker) {
        super(memoryTrimmableRegistry, poolParams, poolStatsTracker);
        Intrinsics.checkNotNullParameter(memoryTrimmableRegistry, "memoryTrimmableRegistry");
        Intrinsics.checkNotNullParameter(poolParams, "poolParams");
        Intrinsics.checkNotNullParameter(poolStatsTracker, "poolStatsTracker");
        SparseIntArray sparseIntArray = poolParams.bucketSizes;
        if (sparseIntArray != null) {
            this.bucketSizes = new int[sparseIntArray.size()];
            int size = sparseIntArray.size();
            for (int i = 0; i < size; i++) {
                this.bucketSizes[i] = sparseIntArray.keyAt(i);
            }
        } else {
            this.bucketSizes = new int[0];
        }
        initialize();
    }

    /* access modifiers changed from: protected */
    public byte[] alloc(int i) {
        return new byte[i];
    }

    /* access modifiers changed from: protected */
    public int getBucketedSize(int i) {
        if (i > 0) {
            for (int i2 : this.bucketSizes) {
                if (i2 >= i) {
                    return i2;
                }
            }
            return i;
        }
        throw new BasePool.InvalidSizeException(Integer.valueOf(i));
    }

    /* access modifiers changed from: protected */
    public int getBucketedSizeForValue(byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "value");
        return bArr.length;
    }
}
