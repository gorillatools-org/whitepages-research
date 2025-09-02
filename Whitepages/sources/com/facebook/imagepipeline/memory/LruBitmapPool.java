package com.facebook.imagepipeline.memory;

import android.graphics.Bitmap;
import com.facebook.common.memory.MemoryTrimmableRegistry;

public class LruBitmapPool implements BitmapPool {
    private int mCurrentSize;
    private int mMaxBitmapSize;
    private final int mMaxPoolSize;
    private final PoolStatsTracker mPoolStatsTracker;
    protected final PoolBackend mStrategy = new BitmapPoolBackend();

    public LruBitmapPool(int i, int i2, PoolStatsTracker poolStatsTracker, MemoryTrimmableRegistry memoryTrimmableRegistry) {
        this.mMaxPoolSize = i;
        this.mMaxBitmapSize = i2;
        this.mPoolStatsTracker = poolStatsTracker;
        if (memoryTrimmableRegistry != null) {
            memoryTrimmableRegistry.registerMemoryTrimmable(this);
        }
    }

    private synchronized void trimTo(int i) {
        while (true) {
            if (this.mCurrentSize <= i) {
                break;
            }
            Bitmap bitmap = (Bitmap) this.mStrategy.pop();
            if (bitmap == null) {
                break;
            }
            int size = this.mStrategy.getSize(bitmap);
            this.mCurrentSize -= size;
            this.mPoolStatsTracker.onFree(size);
        }
    }

    public synchronized Bitmap get(int i) {
        try {
            int i2 = this.mCurrentSize;
            int i3 = this.mMaxPoolSize;
            if (i2 > i3) {
                trimTo(i3);
            }
            Bitmap bitmap = (Bitmap) this.mStrategy.get(i);
            if (bitmap != null) {
                int size = this.mStrategy.getSize(bitmap);
                this.mCurrentSize -= size;
                this.mPoolStatsTracker.onValueReuse(size);
                return bitmap;
            }
            return alloc(i);
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }

    private Bitmap alloc(int i) {
        this.mPoolStatsTracker.onAlloc(i);
        return Bitmap.createBitmap(1, i, Bitmap.Config.ALPHA_8);
    }

    public void release(Bitmap bitmap) {
        int size = this.mStrategy.getSize(bitmap);
        if (size <= this.mMaxBitmapSize) {
            this.mPoolStatsTracker.onValueRelease(size);
            this.mStrategy.put(bitmap);
            synchronized (this) {
                this.mCurrentSize += size;
            }
        }
    }
}
