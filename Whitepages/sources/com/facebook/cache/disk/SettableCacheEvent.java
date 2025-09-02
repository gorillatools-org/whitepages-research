package com.facebook.cache.disk;

import com.facebook.cache.common.CacheEvent;
import com.facebook.cache.common.CacheEventListener;
import com.facebook.cache.common.CacheKey;
import java.io.IOException;

public class SettableCacheEvent implements CacheEvent {
    private static final Object RECYCLER_LOCK = new Object();
    private static SettableCacheEvent sFirstRecycledEvent;
    private static int sRecycledCount;
    private CacheKey mCacheKey;
    private long mCacheLimit;
    private long mCacheSize;
    private CacheEventListener.EvictionReason mEvictionReason;
    private IOException mException;
    private long mItemSize;
    private SettableCacheEvent mNextRecycledEvent;
    private String mResourceId;

    public static SettableCacheEvent obtain() {
        synchronized (RECYCLER_LOCK) {
            try {
                SettableCacheEvent settableCacheEvent = sFirstRecycledEvent;
                if (settableCacheEvent == null) {
                    return new SettableCacheEvent();
                }
                sFirstRecycledEvent = settableCacheEvent.mNextRecycledEvent;
                settableCacheEvent.mNextRecycledEvent = null;
                sRecycledCount--;
                return settableCacheEvent;
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
    }

    private SettableCacheEvent() {
    }

    public SettableCacheEvent setCacheKey(CacheKey cacheKey) {
        this.mCacheKey = cacheKey;
        return this;
    }

    public SettableCacheEvent setResourceId(String str) {
        this.mResourceId = str;
        return this;
    }

    public SettableCacheEvent setItemSize(long j) {
        this.mItemSize = j;
        return this;
    }

    public SettableCacheEvent setCacheSize(long j) {
        this.mCacheSize = j;
        return this;
    }

    public SettableCacheEvent setCacheLimit(long j) {
        this.mCacheLimit = j;
        return this;
    }

    public SettableCacheEvent setException(IOException iOException) {
        this.mException = iOException;
        return this;
    }

    public SettableCacheEvent setEvictionReason(CacheEventListener.EvictionReason evictionReason) {
        this.mEvictionReason = evictionReason;
        return this;
    }

    public void recycle() {
        synchronized (RECYCLER_LOCK) {
            try {
                if (sRecycledCount < 5) {
                    reset();
                    sRecycledCount++;
                    SettableCacheEvent settableCacheEvent = sFirstRecycledEvent;
                    if (settableCacheEvent != null) {
                        this.mNextRecycledEvent = settableCacheEvent;
                    }
                    sFirstRecycledEvent = this;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private void reset() {
        this.mCacheKey = null;
        this.mResourceId = null;
        this.mItemSize = 0;
        this.mCacheLimit = 0;
        this.mCacheSize = 0;
        this.mException = null;
        this.mEvictionReason = null;
    }
}
