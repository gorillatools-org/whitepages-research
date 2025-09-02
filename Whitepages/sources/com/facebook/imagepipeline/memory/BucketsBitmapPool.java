package com.facebook.imagepipeline.memory;

import android.graphics.Bitmap;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.memory.MemoryTrimmableRegistry;

public class BucketsBitmapPool extends BasePool implements BitmapPool {
    /* access modifiers changed from: protected */
    public int getBucketedSize(int i) {
        return i;
    }

    /* access modifiers changed from: protected */
    public int getSizeInBytes(int i) {
        return i;
    }

    public BucketsBitmapPool(MemoryTrimmableRegistry memoryTrimmableRegistry, PoolParams poolParams, PoolStatsTracker poolStatsTracker, boolean z) {
        super(memoryTrimmableRegistry, poolParams, poolStatsTracker, z);
        initialize();
    }

    /* access modifiers changed from: protected */
    public Bitmap alloc(int i) {
        return Bitmap.createBitmap(1, (int) Math.ceil(((double) i) / 2.0d), Bitmap.Config.RGB_565);
    }

    /* access modifiers changed from: protected */
    public void free(Bitmap bitmap) {
        Preconditions.checkNotNull(bitmap);
        bitmap.recycle();
    }

    /* access modifiers changed from: protected */
    public int getBucketedSizeForValue(Bitmap bitmap) {
        Preconditions.checkNotNull(bitmap);
        return bitmap.getAllocationByteCount();
    }

    /* access modifiers changed from: protected */
    public boolean isReusable(Bitmap bitmap) {
        Preconditions.checkNotNull(bitmap);
        return !bitmap.isRecycled() && bitmap.isMutable();
    }

    /* access modifiers changed from: protected */
    public Bitmap getValue(Bucket bucket) {
        Bitmap bitmap = (Bitmap) super.getValue(bucket);
        if (bitmap != null) {
            bitmap.eraseColor(0);
        }
        return bitmap;
    }
}
