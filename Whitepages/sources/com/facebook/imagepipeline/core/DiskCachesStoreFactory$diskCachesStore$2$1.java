package com.facebook.imagepipeline.core;

import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.cache.disk.FileCache;
import com.facebook.common.internal.ImmutableMap;
import com.facebook.common.memory.PooledByteBufferFactory;
import com.facebook.common.memory.PooledByteStreams;
import com.facebook.imagepipeline.cache.BufferedDiskCache;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

public final class DiskCachesStoreFactory$diskCachesStore$2$1 implements DiskCachesStore {
    private final Lazy dynamicBufferedDiskCaches$delegate;
    private final Lazy dynamicFileCaches$delegate;
    private final Lazy mainBufferedDiskCache$delegate;
    private final Lazy mainFileCache$delegate;
    private final Lazy smallImageBufferedDiskCache$delegate;
    private final Lazy smallImageFileCache$delegate;

    DiskCachesStoreFactory$diskCachesStore$2$1(DiskCachesStoreFactory diskCachesStoreFactory) {
        LazyThreadSafetyMode lazyThreadSafetyMode = LazyThreadSafetyMode.SYNCHRONIZED;
        this.mainFileCache$delegate = LazyKt.lazy(lazyThreadSafetyMode, new DiskCachesStoreFactory$diskCachesStore$2$1$$ExternalSyntheticLambda0(diskCachesStoreFactory));
        this.mainBufferedDiskCache$delegate = LazyKt.lazy(lazyThreadSafetyMode, new DiskCachesStoreFactory$diskCachesStore$2$1$$ExternalSyntheticLambda1(this, diskCachesStoreFactory));
        this.smallImageFileCache$delegate = LazyKt.lazy(lazyThreadSafetyMode, new DiskCachesStoreFactory$diskCachesStore$2$1$$ExternalSyntheticLambda2(diskCachesStoreFactory));
        this.smallImageBufferedDiskCache$delegate = LazyKt.lazy(lazyThreadSafetyMode, new DiskCachesStoreFactory$diskCachesStore$2$1$$ExternalSyntheticLambda3(this, diskCachesStoreFactory));
        this.dynamicFileCaches$delegate = LazyKt.lazy(lazyThreadSafetyMode, new DiskCachesStoreFactory$diskCachesStore$2$1$$ExternalSyntheticLambda4(diskCachesStoreFactory, this));
        this.dynamicBufferedDiskCaches$delegate = LazyKt.lazy(lazyThreadSafetyMode, new DiskCachesStoreFactory$diskCachesStore$2$1$$ExternalSyntheticLambda5(this, diskCachesStoreFactory));
    }

    /* access modifiers changed from: private */
    public static final FileCache mainFileCache_delegate$lambda$0(DiskCachesStoreFactory diskCachesStoreFactory) {
        Intrinsics.checkNotNullParameter(diskCachesStoreFactory, "this$0");
        return diskCachesStoreFactory.fileCacheFactory.get(diskCachesStoreFactory.mainDiskCacheConfig);
    }

    public FileCache getMainFileCache() {
        return (FileCache) this.mainFileCache$delegate.getValue();
    }

    public BufferedDiskCache getMainBufferedDiskCache() {
        return (BufferedDiskCache) this.mainBufferedDiskCache$delegate.getValue();
    }

    /* access modifiers changed from: private */
    public static final BufferedDiskCache mainBufferedDiskCache_delegate$lambda$1(DiskCachesStoreFactory$diskCachesStore$2$1 diskCachesStoreFactory$diskCachesStore$2$1, DiskCachesStoreFactory diskCachesStoreFactory) {
        Intrinsics.checkNotNullParameter(diskCachesStoreFactory$diskCachesStore$2$1, "this$0");
        Intrinsics.checkNotNullParameter(diskCachesStoreFactory, "this$1");
        FileCache mainFileCache = diskCachesStoreFactory$diskCachesStore$2$1.getMainFileCache();
        PooledByteBufferFactory pooledByteBufferFactory = diskCachesStoreFactory.poolFactory.getPooledByteBufferFactory(diskCachesStoreFactory.memoryChunkType);
        Intrinsics.checkNotNullExpressionValue(pooledByteBufferFactory, "getPooledByteBufferFactory(...)");
        PooledByteStreams pooledByteStreams = diskCachesStoreFactory.poolFactory.getPooledByteStreams();
        Intrinsics.checkNotNullExpressionValue(pooledByteStreams, "getPooledByteStreams(...)");
        Executor forLocalStorageRead = diskCachesStoreFactory.executorSupplier.forLocalStorageRead();
        Intrinsics.checkNotNullExpressionValue(forLocalStorageRead, "forLocalStorageRead(...)");
        Executor forLocalStorageWrite = diskCachesStoreFactory.executorSupplier.forLocalStorageWrite();
        Intrinsics.checkNotNullExpressionValue(forLocalStorageWrite, "forLocalStorageWrite(...)");
        return new BufferedDiskCache(mainFileCache, pooledByteBufferFactory, pooledByteStreams, forLocalStorageRead, forLocalStorageWrite, diskCachesStoreFactory.imageCacheStatsTracker);
    }

    public FileCache getSmallImageFileCache() {
        return (FileCache) this.smallImageFileCache$delegate.getValue();
    }

    /* access modifiers changed from: private */
    public static final FileCache smallImageFileCache_delegate$lambda$2(DiskCachesStoreFactory diskCachesStoreFactory) {
        Intrinsics.checkNotNullParameter(diskCachesStoreFactory, "this$0");
        return diskCachesStoreFactory.fileCacheFactory.get(diskCachesStoreFactory.smallImageDiskCacheConfig);
    }

    public BufferedDiskCache getSmallImageBufferedDiskCache() {
        return (BufferedDiskCache) this.smallImageBufferedDiskCache$delegate.getValue();
    }

    /* access modifiers changed from: private */
    public static final BufferedDiskCache smallImageBufferedDiskCache_delegate$lambda$3(DiskCachesStoreFactory$diskCachesStore$2$1 diskCachesStoreFactory$diskCachesStore$2$1, DiskCachesStoreFactory diskCachesStoreFactory) {
        Intrinsics.checkNotNullParameter(diskCachesStoreFactory$diskCachesStore$2$1, "this$0");
        Intrinsics.checkNotNullParameter(diskCachesStoreFactory, "this$1");
        FileCache smallImageFileCache = diskCachesStoreFactory$diskCachesStore$2$1.getSmallImageFileCache();
        PooledByteBufferFactory pooledByteBufferFactory = diskCachesStoreFactory.poolFactory.getPooledByteBufferFactory(diskCachesStoreFactory.memoryChunkType);
        Intrinsics.checkNotNullExpressionValue(pooledByteBufferFactory, "getPooledByteBufferFactory(...)");
        PooledByteStreams pooledByteStreams = diskCachesStoreFactory.poolFactory.getPooledByteStreams();
        Intrinsics.checkNotNullExpressionValue(pooledByteStreams, "getPooledByteStreams(...)");
        Executor forLocalStorageRead = diskCachesStoreFactory.executorSupplier.forLocalStorageRead();
        Intrinsics.checkNotNullExpressionValue(forLocalStorageRead, "forLocalStorageRead(...)");
        Executor forLocalStorageWrite = diskCachesStoreFactory.executorSupplier.forLocalStorageWrite();
        Intrinsics.checkNotNullExpressionValue(forLocalStorageWrite, "forLocalStorageWrite(...)");
        return new BufferedDiskCache(smallImageFileCache, pooledByteBufferFactory, pooledByteStreams, forLocalStorageRead, forLocalStorageWrite, diskCachesStoreFactory.imageCacheStatsTracker);
    }

    public Map getDynamicFileCaches() {
        return (Map) this.dynamicFileCaches$delegate.getValue();
    }

    /* access modifiers changed from: private */
    public static final Map dynamicFileCaches_delegate$lambda$7(DiskCachesStoreFactory diskCachesStoreFactory, DiskCachesStoreFactory$diskCachesStore$2$1 diskCachesStoreFactory$diskCachesStore$2$1) {
        Intrinsics.checkNotNullParameter(diskCachesStoreFactory, "this$0");
        Intrinsics.checkNotNullParameter(diskCachesStoreFactory$diskCachesStore$2$1, "this$1");
        Map access$getDynamicDiskCacheConfigMap$p = diskCachesStoreFactory.dynamicDiskCacheConfigMap;
        if (access$getDynamicDiskCacheConfigMap$p == null) {
            return MapsKt.emptyMap();
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(MapsKt.mapCapacity(access$getDynamicDiskCacheConfigMap$p.size()));
        for (Map.Entry entry : access$getDynamicDiskCacheConfigMap$p.entrySet()) {
            linkedHashMap.put(entry.getKey(), diskCachesStoreFactory.fileCacheFactory.get((DiskCacheConfig) entry.getValue()));
        }
        return linkedHashMap;
    }

    public ImmutableMap getDynamicBufferedDiskCaches() {
        Object value = this.dynamicBufferedDiskCaches$delegate.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
        return (ImmutableMap) value;
    }

    /* access modifiers changed from: private */
    public static final ImmutableMap dynamicBufferedDiskCaches_delegate$lambda$9(DiskCachesStoreFactory$diskCachesStore$2$1 diskCachesStoreFactory$diskCachesStore$2$1, DiskCachesStoreFactory diskCachesStoreFactory) {
        Intrinsics.checkNotNullParameter(diskCachesStoreFactory$diskCachesStore$2$1, "this$0");
        Intrinsics.checkNotNullParameter(diskCachesStoreFactory, "this$1");
        Map dynamicFileCaches = diskCachesStoreFactory$diskCachesStore$2$1.getDynamicFileCaches();
        LinkedHashMap linkedHashMap = new LinkedHashMap(MapsKt.mapCapacity(dynamicFileCaches.size()));
        for (Map.Entry entry : dynamicFileCaches.entrySet()) {
            Object key = entry.getKey();
            PooledByteBufferFactory pooledByteBufferFactory = diskCachesStoreFactory.poolFactory.getPooledByteBufferFactory(diskCachesStoreFactory.memoryChunkType);
            Intrinsics.checkNotNullExpressionValue(pooledByteBufferFactory, "getPooledByteBufferFactory(...)");
            PooledByteStreams pooledByteStreams = diskCachesStoreFactory.poolFactory.getPooledByteStreams();
            Intrinsics.checkNotNullExpressionValue(pooledByteStreams, "getPooledByteStreams(...)");
            Executor forLocalStorageRead = diskCachesStoreFactory.executorSupplier.forLocalStorageRead();
            Intrinsics.checkNotNullExpressionValue(forLocalStorageRead, "forLocalStorageRead(...)");
            Executor forLocalStorageWrite = diskCachesStoreFactory.executorSupplier.forLocalStorageWrite();
            Intrinsics.checkNotNullExpressionValue(forLocalStorageWrite, "forLocalStorageWrite(...)");
            linkedHashMap.put(key, new BufferedDiskCache((FileCache) entry.getValue(), pooledByteBufferFactory, pooledByteStreams, forLocalStorageRead, forLocalStorageWrite, diskCachesStoreFactory.imageCacheStatsTracker));
        }
        return ImmutableMap.copyOf(linkedHashMap);
    }
}
