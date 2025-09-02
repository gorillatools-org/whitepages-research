package com.facebook.imagepipeline.core;

import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.common.internal.Supplier;
import com.facebook.imagepipeline.cache.ImageCacheStatsTracker;
import com.facebook.imagepipeline.memory.PoolFactory;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.jvm.internal.Intrinsics;

public final class DiskCachesStoreFactory implements Supplier {
    private final Lazy diskCachesStore$delegate;
    /* access modifiers changed from: private */
    public final Map dynamicDiskCacheConfigMap;
    /* access modifiers changed from: private */
    public final ExecutorSupplier executorSupplier;
    /* access modifiers changed from: private */
    public final FileCacheFactory fileCacheFactory;
    /* access modifiers changed from: private */
    public final ImageCacheStatsTracker imageCacheStatsTracker;
    /* access modifiers changed from: private */
    public final DiskCacheConfig mainDiskCacheConfig;
    /* access modifiers changed from: private */
    public final int memoryChunkType;
    /* access modifiers changed from: private */
    public final PoolFactory poolFactory;
    /* access modifiers changed from: private */
    public final DiskCacheConfig smallImageDiskCacheConfig;

    public DiskCachesStoreFactory(FileCacheFactory fileCacheFactory2, PoolFactory poolFactory2, ExecutorSupplier executorSupplier2, ImageCacheStatsTracker imageCacheStatsTracker2, int i, DiskCacheConfig diskCacheConfig, DiskCacheConfig diskCacheConfig2, Map map) {
        Intrinsics.checkNotNullParameter(fileCacheFactory2, "fileCacheFactory");
        Intrinsics.checkNotNullParameter(poolFactory2, "poolFactory");
        Intrinsics.checkNotNullParameter(executorSupplier2, "executorSupplier");
        Intrinsics.checkNotNullParameter(imageCacheStatsTracker2, "imageCacheStatsTracker");
        Intrinsics.checkNotNullParameter(diskCacheConfig, "mainDiskCacheConfig");
        Intrinsics.checkNotNullParameter(diskCacheConfig2, "smallImageDiskCacheConfig");
        this.fileCacheFactory = fileCacheFactory2;
        this.poolFactory = poolFactory2;
        this.executorSupplier = executorSupplier2;
        this.imageCacheStatsTracker = imageCacheStatsTracker2;
        this.memoryChunkType = i;
        this.mainDiskCacheConfig = diskCacheConfig;
        this.smallImageDiskCacheConfig = diskCacheConfig2;
        this.dynamicDiskCacheConfigMap = map;
        this.diskCachesStore$delegate = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, new DiskCachesStoreFactory$$ExternalSyntheticLambda0(this));
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public DiskCachesStoreFactory(FileCacheFactory fileCacheFactory2, ImagePipelineConfigInterface imagePipelineConfigInterface) {
        this(fileCacheFactory2, imagePipelineConfigInterface.getPoolFactory(), imagePipelineConfigInterface.getExecutorSupplier(), imagePipelineConfigInterface.getImageCacheStatsTracker(), imagePipelineConfigInterface.getMemoryChunkType(), imagePipelineConfigInterface.getMainDiskCacheConfig(), imagePipelineConfigInterface.getSmallImageDiskCacheConfig(), imagePipelineConfigInterface.getDynamicDiskCacheConfigMap());
        Intrinsics.checkNotNullParameter(fileCacheFactory2, "fileCacheFactory");
        Intrinsics.checkNotNullParameter(imagePipelineConfigInterface, "config");
    }

    private final DiskCachesStore getDiskCachesStore() {
        return (DiskCachesStore) this.diskCachesStore$delegate.getValue();
    }

    /* access modifiers changed from: private */
    public static final DiskCachesStoreFactory$diskCachesStore$2$1 diskCachesStore_delegate$lambda$0(DiskCachesStoreFactory diskCachesStoreFactory) {
        Intrinsics.checkNotNullParameter(diskCachesStoreFactory, "this$0");
        return new DiskCachesStoreFactory$diskCachesStore$2$1(diskCachesStoreFactory);
    }

    public DiskCachesStore get() {
        return getDiskCachesStore();
    }
}
