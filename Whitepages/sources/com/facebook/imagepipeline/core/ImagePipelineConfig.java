package com.facebook.imagepipeline.core;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.callercontext.CallerContextVerifier;
import com.facebook.common.executors.SerialExecutorService;
import com.facebook.common.internal.Supplier;
import com.facebook.common.internal.Suppliers;
import com.facebook.common.memory.MemoryTrimmableRegistry;
import com.facebook.common.memory.NoOpMemoryTrimmableRegistry;
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory;
import com.facebook.imagepipeline.cache.BitmapMemoryCacheFactory;
import com.facebook.imagepipeline.cache.BitmapMemoryCacheTrimStrategy;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.cache.CountingLruBitmapMemoryCacheFactory;
import com.facebook.imagepipeline.cache.CountingMemoryCache;
import com.facebook.imagepipeline.cache.DefaultBitmapMemoryCacheParamsSupplier;
import com.facebook.imagepipeline.cache.DefaultCacheKeyFactory;
import com.facebook.imagepipeline.cache.DefaultEncodedMemoryCacheParamsSupplier;
import com.facebook.imagepipeline.cache.ImageCacheStatsTracker;
import com.facebook.imagepipeline.cache.MemoryCache;
import com.facebook.imagepipeline.cache.NativeMemoryCacheTrimStrategy;
import com.facebook.imagepipeline.cache.NoOpImageCacheStatsTracker;
import com.facebook.imagepipeline.core.ImagePipelineExperiments;
import com.facebook.imagepipeline.debug.CloseableReferenceLeakTracker;
import com.facebook.imagepipeline.debug.NoOpCloseableReferenceLeakTracker;
import com.facebook.imagepipeline.decoder.ImageDecoder;
import com.facebook.imagepipeline.decoder.ImageDecoderConfig;
import com.facebook.imagepipeline.decoder.ProgressiveJpegConfig;
import com.facebook.imagepipeline.decoder.SimpleProgressiveJpegConfig;
import com.facebook.imagepipeline.memory.PoolConfig;
import com.facebook.imagepipeline.memory.PoolFactory;
import com.facebook.imagepipeline.producers.HttpUrlConnectionNetworkFetcher;
import com.facebook.imagepipeline.producers.NetworkFetcher;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import com.facebook.imagepipeline.transcoder.ImageTranscoderFactory;
import java.util.Map;
import java.util.Set;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class ImagePipelineConfig implements ImagePipelineConfigInterface {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static DefaultImageRequestConfig defaultImageRequestConfig = new DefaultImageRequestConfig();
    private final MemoryCache bitmapCacheOverride;
    private final Bitmap.Config bitmapConfig;
    private final BitmapMemoryCacheFactory bitmapMemoryCacheFactory;
    private final Supplier bitmapMemoryCacheParamsSupplier;
    private final MemoryCache.CacheTrimStrategy bitmapMemoryCacheTrimStrategy;
    private final CacheKeyFactory cacheKeyFactory;
    private final CloseableReferenceLeakTracker closeableReferenceLeakTracker;
    private final Context context;
    private final Set customProducerSequenceFactories;
    private final Supplier diskCachesStoreSupplier;
    private final DownsampleMode downsampleMode;
    private final Map dynamicDiskCacheConfigMap;
    private final Supplier enableEncodedImageColorSpaceUsage;
    private final MemoryCache encodedMemoryCacheOverride;
    private final Supplier encodedMemoryCacheParamsSupplier;
    private final MemoryCache.CacheTrimStrategy encodedMemoryCacheTrimStrategy;
    private final ExecutorSupplier executorSupplier;
    private final ImagePipelineExperiments experiments;
    private final int httpNetworkTimeout;
    private final ImageCacheStatsTracker imageCacheStatsTracker;
    private final ImageDecoder imageDecoder;
    private final ImageTranscoderFactory imageTranscoderFactory;
    private final Integer imageTranscoderType;
    private final boolean isDiskCacheEnabled;
    private final Supplier isPrefetchEnabledSupplier;
    private final boolean isResizeAndRotateEnabledForNetwork;
    private final DiskCacheConfig mainDiskCacheConfig;
    private final int memoryChunkType;
    private final MemoryTrimmableRegistry memoryTrimmableRegistry;
    private final NetworkFetcher networkFetcher;
    private final PlatformBitmapFactory platformBitmapFactory;
    private final PoolFactory poolFactory;
    private final ProgressiveJpegConfig progressiveJpegConfig;
    private final Set requestListener2s;
    private final Set requestListeners;
    private final DiskCacheConfig smallImageDiskCacheConfig;

    public /* synthetic */ ImagePipelineConfig(Builder builder, DefaultConstructorMarker defaultConstructorMarker) {
        this(builder);
    }

    public static final DefaultImageRequestConfig getDefaultImageRequestConfig() {
        return Companion.getDefaultImageRequestConfig();
    }

    public static final Builder newBuilder(Context context2) {
        return Companion.newBuilder(context2);
    }

    public CountingMemoryCache.EntryStateObserver getBitmapMemoryCacheEntryStateObserver() {
        return null;
    }

    public CallerContextVerifier getCallerContextVerifier() {
        return null;
    }

    public SerialExecutorService getExecutorServiceForAnimatedImages() {
        return null;
    }

    public ImageDecoderConfig getImageDecoderConfig() {
        return null;
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* access modifiers changed from: private */
        public final DiskCacheConfig getDefaultMainDiskCacheConfig(Context context) {
            DiskCacheConfig diskCacheConfig;
            if (!FrescoSystrace.isTracing()) {
                diskCacheConfig = DiskCacheConfig.newBuilder(context).build();
            } else {
                FrescoSystrace.beginSection("DiskCacheConfig.getDefaultMainDiskCacheConfig");
                try {
                    diskCacheConfig = DiskCacheConfig.newBuilder(context).build();
                } finally {
                    FrescoSystrace.endSection();
                }
            }
            Intrinsics.checkNotNullExpressionValue(diskCacheConfig, "traceSection(...)");
            return diskCacheConfig;
        }

        private Companion() {
        }

        public final DefaultImageRequestConfig getDefaultImageRequestConfig() {
            return ImagePipelineConfig.defaultImageRequestConfig;
        }

        public final Builder newBuilder(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return new Builder(context);
        }

        /* access modifiers changed from: private */
        public final ImageTranscoderFactory getImageTranscoderFactory(Builder builder) {
            if (builder.getImageTranscoderFactory() == null || builder.getImageTranscoderType() == null) {
                return builder.getImageTranscoderFactory();
            }
            throw new IllegalStateException("You can't define a custom ImageTranscoderFactory and provide an ImageTranscoderType");
        }

        /* access modifiers changed from: private */
        public final int getMemoryChunkType(Builder builder, ImagePipelineExperiments imagePipelineExperiments) {
            Integer memoryChunkType = builder.getMemoryChunkType();
            if (memoryChunkType != null) {
                return memoryChunkType.intValue();
            }
            if (imagePipelineExperiments.getMemoryType() == 2 && Build.VERSION.SDK_INT >= 27) {
                return 2;
            }
            if (imagePipelineExperiments.getMemoryType() == 1) {
                return 1;
            }
            imagePipelineExperiments.getMemoryType();
            return 0;
        }
    }

    private ImagePipelineConfig(Builder builder) {
        int i;
        NetworkFetcher networkFetcher2;
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.beginSection("ImagePipelineConfig()");
        }
        this.experiments = builder.getExperimentsBuilder().build();
        Supplier bitmapMemoryCacheParamsSupplier2 = builder.getBitmapMemoryCacheParamsSupplier();
        if (bitmapMemoryCacheParamsSupplier2 == null) {
            Object systemService = builder.getContext().getSystemService("activity");
            if (systemService != null) {
                Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.ActivityManager");
                bitmapMemoryCacheParamsSupplier2 = new DefaultBitmapMemoryCacheParamsSupplier((ActivityManager) systemService);
            } else {
                throw new IllegalStateException("Required value was null.");
            }
        }
        this.bitmapMemoryCacheParamsSupplier = bitmapMemoryCacheParamsSupplier2;
        MemoryCache.CacheTrimStrategy bitmapMemoryCacheTrimStrategy2 = builder.getBitmapMemoryCacheTrimStrategy();
        this.bitmapMemoryCacheTrimStrategy = bitmapMemoryCacheTrimStrategy2 == null ? new BitmapMemoryCacheTrimStrategy() : bitmapMemoryCacheTrimStrategy2;
        MemoryCache.CacheTrimStrategy encodedMemoryCacheTrimStrategy2 = builder.getEncodedMemoryCacheTrimStrategy();
        this.encodedMemoryCacheTrimStrategy = encodedMemoryCacheTrimStrategy2 == null ? new NativeMemoryCacheTrimStrategy() : encodedMemoryCacheTrimStrategy2;
        builder.getBitmapMemoryCacheEntryStateObserver();
        Bitmap.Config bitmapConfig2 = builder.getBitmapConfig();
        this.bitmapConfig = bitmapConfig2 == null ? Bitmap.Config.ARGB_8888 : bitmapConfig2;
        CacheKeyFactory cacheKeyFactory2 = builder.getCacheKeyFactory();
        if (cacheKeyFactory2 == null) {
            cacheKeyFactory2 = DefaultCacheKeyFactory.getInstance();
            Intrinsics.checkNotNullExpressionValue(cacheKeyFactory2, "getInstance(...)");
        }
        this.cacheKeyFactory = cacheKeyFactory2;
        Context context2 = builder.getContext();
        if (context2 != null) {
            this.context = context2;
            this.downsampleMode = builder.getDownsampleMode();
            Supplier encodedMemoryCacheParamsSupplier2 = builder.getEncodedMemoryCacheParamsSupplier();
            this.encodedMemoryCacheParamsSupplier = encodedMemoryCacheParamsSupplier2 == null ? new DefaultEncodedMemoryCacheParamsSupplier() : encodedMemoryCacheParamsSupplier2;
            ImageCacheStatsTracker imageCacheStatsTracker2 = builder.getImageCacheStatsTracker();
            if (imageCacheStatsTracker2 == null) {
                imageCacheStatsTracker2 = NoOpImageCacheStatsTracker.getInstance();
                Intrinsics.checkNotNullExpressionValue(imageCacheStatsTracker2, "getInstance(...)");
            }
            this.imageCacheStatsTracker = imageCacheStatsTracker2;
            this.imageDecoder = builder.getImageDecoder();
            Supplier enableEncodedImageColorSpaceUsage2 = builder.getEnableEncodedImageColorSpaceUsage();
            if (enableEncodedImageColorSpaceUsage2 == null) {
                enableEncodedImageColorSpaceUsage2 = Suppliers.BOOLEAN_FALSE;
                Intrinsics.checkNotNullExpressionValue(enableEncodedImageColorSpaceUsage2, "BOOLEAN_FALSE");
            }
            this.enableEncodedImageColorSpaceUsage = enableEncodedImageColorSpaceUsage2;
            Companion companion = Companion;
            this.imageTranscoderFactory = companion.getImageTranscoderFactory(builder);
            this.imageTranscoderType = builder.getImageTranscoderType();
            Supplier isPrefetchEnabledSupplier2 = builder.isPrefetchEnabledSupplier();
            if (isPrefetchEnabledSupplier2 == null) {
                isPrefetchEnabledSupplier2 = Suppliers.BOOLEAN_TRUE;
                Intrinsics.checkNotNullExpressionValue(isPrefetchEnabledSupplier2, "BOOLEAN_TRUE");
            }
            this.isPrefetchEnabledSupplier = isPrefetchEnabledSupplier2;
            DiskCacheConfig mainDiskCacheConfig2 = builder.getMainDiskCacheConfig();
            this.mainDiskCacheConfig = mainDiskCacheConfig2 == null ? companion.getDefaultMainDiskCacheConfig(builder.getContext()) : mainDiskCacheConfig2;
            MemoryTrimmableRegistry memoryTrimmableRegistry2 = builder.getMemoryTrimmableRegistry();
            if (memoryTrimmableRegistry2 == null) {
                memoryTrimmableRegistry2 = NoOpMemoryTrimmableRegistry.getInstance();
                Intrinsics.checkNotNullExpressionValue(memoryTrimmableRegistry2, "getInstance(...)");
            }
            this.memoryTrimmableRegistry = memoryTrimmableRegistry2;
            this.memoryChunkType = companion.getMemoryChunkType(builder, getExperiments());
            if (builder.getHttpConnectionTimeout() < 0) {
                i = 30000;
            } else {
                i = builder.getHttpConnectionTimeout();
            }
            this.httpNetworkTimeout = i;
            if (!FrescoSystrace.isTracing()) {
                networkFetcher2 = builder.getNetworkFetcher();
                if (networkFetcher2 == null) {
                    networkFetcher2 = new HttpUrlConnectionNetworkFetcher(i);
                }
            } else {
                FrescoSystrace.beginSection("ImagePipelineConfig->mNetworkFetcher");
                try {
                    networkFetcher2 = builder.getNetworkFetcher();
                    networkFetcher2 = networkFetcher2 == null ? new HttpUrlConnectionNetworkFetcher(i) : networkFetcher2;
                } finally {
                    FrescoSystrace.endSection();
                }
            }
            this.networkFetcher = networkFetcher2;
            this.platformBitmapFactory = builder.getPlatformBitmapFactory();
            PoolFactory poolFactory2 = builder.getPoolFactory();
            this.poolFactory = poolFactory2 == null ? new PoolFactory(PoolConfig.newBuilder().build()) : poolFactory2;
            ProgressiveJpegConfig progressiveJpegConfig2 = builder.getProgressiveJpegConfig();
            this.progressiveJpegConfig = progressiveJpegConfig2 == null ? new SimpleProgressiveJpegConfig() : progressiveJpegConfig2;
            Set requestListeners2 = builder.getRequestListeners();
            this.requestListeners = requestListeners2 == null ? SetsKt.emptySet() : requestListeners2;
            Set requestListener2s2 = builder.getRequestListener2s();
            this.requestListener2s = requestListener2s2 == null ? SetsKt.emptySet() : requestListener2s2;
            Set customProducerSequenceFactories2 = builder.getCustomProducerSequenceFactories();
            this.customProducerSequenceFactories = customProducerSequenceFactories2 == null ? SetsKt.emptySet() : customProducerSequenceFactories2;
            this.isResizeAndRotateEnabledForNetwork = builder.getResizeAndRotateEnabledForNetwork();
            DiskCacheConfig smallImageDiskCacheConfig2 = builder.getSmallImageDiskCacheConfig();
            this.smallImageDiskCacheConfig = smallImageDiskCacheConfig2 == null ? getMainDiskCacheConfig() : smallImageDiskCacheConfig2;
            builder.getImageDecoderConfig();
            int flexByteArrayPoolMaxNumThreads = getPoolFactory().getFlexByteArrayPoolMaxNumThreads();
            ExecutorSupplier executorSupplier2 = builder.getExecutorSupplier();
            this.executorSupplier = executorSupplier2 == null ? new DefaultExecutorSupplier(flexByteArrayPoolMaxNumThreads) : executorSupplier2;
            this.isDiskCacheEnabled = builder.getDiskCacheEnabled();
            builder.getCallerContextVerifier();
            this.closeableReferenceLeakTracker = builder.getCloseableReferenceLeakTracker();
            this.bitmapCacheOverride = builder.getBitmapMemoryCache();
            BitmapMemoryCacheFactory bitmapMemoryCacheFactory2 = builder.getBitmapMemoryCacheFactory();
            this.bitmapMemoryCacheFactory = bitmapMemoryCacheFactory2 == null ? new CountingLruBitmapMemoryCacheFactory() : bitmapMemoryCacheFactory2;
            this.encodedMemoryCacheOverride = builder.getEncodedMemoryCache();
            builder.getSerialExecutorServiceForAnimatedImages();
            this.dynamicDiskCacheConfigMap = builder.getDynamicDiskCacheConfigMap();
            Supplier diskCachesStoreSupplier2 = builder.getDiskCachesStoreSupplier();
            if (diskCachesStoreSupplier2 == null) {
                FileCacheFactory fileCacheFactory = builder.getFileCacheFactory();
                diskCachesStoreSupplier2 = new DiskCachesStoreFactory(fileCacheFactory == null ? new DiskStorageCacheFactory(new DynamicDefaultDiskStorageFactory()) : fileCacheFactory, this);
            }
            this.diskCachesStoreSupplier = diskCachesStoreSupplier2;
            getExperiments().getWebpBitmapFactory();
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.endSection();
                return;
            }
            return;
        }
        throw new IllegalStateException("Required value was null.");
    }

    public Supplier getBitmapMemoryCacheParamsSupplier() {
        return this.bitmapMemoryCacheParamsSupplier;
    }

    public MemoryCache.CacheTrimStrategy getBitmapMemoryCacheTrimStrategy() {
        return this.bitmapMemoryCacheTrimStrategy;
    }

    public MemoryCache.CacheTrimStrategy getEncodedMemoryCacheTrimStrategy() {
        return this.encodedMemoryCacheTrimStrategy;
    }

    public CacheKeyFactory getCacheKeyFactory() {
        return this.cacheKeyFactory;
    }

    public Context getContext() {
        return this.context;
    }

    public DownsampleMode getDownsampleMode() {
        return this.downsampleMode;
    }

    public Supplier getDiskCachesStoreSupplier() {
        return this.diskCachesStoreSupplier;
    }

    public Supplier getEncodedMemoryCacheParamsSupplier() {
        return this.encodedMemoryCacheParamsSupplier;
    }

    public ExecutorSupplier getExecutorSupplier() {
        return this.executorSupplier;
    }

    public ImageCacheStatsTracker getImageCacheStatsTracker() {
        return this.imageCacheStatsTracker;
    }

    public ImageDecoder getImageDecoder() {
        return this.imageDecoder;
    }

    public ImageTranscoderFactory getImageTranscoderFactory() {
        return this.imageTranscoderFactory;
    }

    public Integer getImageTranscoderType() {
        return this.imageTranscoderType;
    }

    public Supplier isPrefetchEnabledSupplier() {
        return this.isPrefetchEnabledSupplier;
    }

    public DiskCacheConfig getMainDiskCacheConfig() {
        return this.mainDiskCacheConfig;
    }

    public MemoryTrimmableRegistry getMemoryTrimmableRegistry() {
        return this.memoryTrimmableRegistry;
    }

    public int getMemoryChunkType() {
        return this.memoryChunkType;
    }

    public NetworkFetcher getNetworkFetcher() {
        return this.networkFetcher;
    }

    public PoolFactory getPoolFactory() {
        return this.poolFactory;
    }

    public ProgressiveJpegConfig getProgressiveJpegConfig() {
        return this.progressiveJpegConfig;
    }

    public Set getRequestListeners() {
        return this.requestListeners;
    }

    public Set getRequestListener2s() {
        return this.requestListener2s;
    }

    public Set getCustomProducerSequenceFactories() {
        return this.customProducerSequenceFactories;
    }

    public boolean isResizeAndRotateEnabledForNetwork() {
        return this.isResizeAndRotateEnabledForNetwork;
    }

    public DiskCacheConfig getSmallImageDiskCacheConfig() {
        return this.smallImageDiskCacheConfig;
    }

    public ImagePipelineExperiments getExperiments() {
        return this.experiments;
    }

    public boolean isDiskCacheEnabled() {
        return this.isDiskCacheEnabled;
    }

    public CloseableReferenceLeakTracker getCloseableReferenceLeakTracker() {
        return this.closeableReferenceLeakTracker;
    }

    public MemoryCache getEncodedMemoryCacheOverride() {
        return this.encodedMemoryCacheOverride;
    }

    public BitmapMemoryCacheFactory getBitmapMemoryCacheFactory() {
        return this.bitmapMemoryCacheFactory;
    }

    public Map getDynamicDiskCacheConfigMap() {
        return this.dynamicDiskCacheConfigMap;
    }

    public static final class DefaultImageRequestConfig {
        private boolean isProgressiveRenderingEnabled;

        public final boolean isProgressiveRenderingEnabled() {
            return this.isProgressiveRenderingEnabled;
        }
    }

    public static final class Builder {
        private Bitmap.Config bitmapConfig;
        private MemoryCache bitmapMemoryCache;
        private BitmapMemoryCacheFactory bitmapMemoryCacheFactory;
        private Supplier bitmapMemoryCacheParamsSupplier;
        private MemoryCache.CacheTrimStrategy bitmapMemoryCacheTrimStrategy;
        private CacheKeyFactory cacheKeyFactory;
        private CloseableReferenceLeakTracker closeableReferenceLeakTracker = new NoOpCloseableReferenceLeakTracker();
        private final Context context;
        private Set customProducerSequenceFactories;
        private boolean diskCacheEnabled = true;
        private Supplier diskCachesStoreSupplier;
        private DownsampleMode downsampleMode = DownsampleMode.AUTO;
        private Map dynamicDiskCacheConfigMap;
        private Supplier enableEncodedImageColorSpaceUsage;
        private MemoryCache encodedMemoryCache;
        private Supplier encodedMemoryCacheParamsSupplier;
        private MemoryCache.CacheTrimStrategy encodedMemoryCacheTrimStrategy;
        private ExecutorSupplier executorSupplier;
        private final ImagePipelineExperiments.Builder experimentsBuilder = new ImagePipelineExperiments.Builder(this);
        private FileCacheFactory fileCacheFactory;
        private int httpConnectionTimeout = -1;
        private ImageCacheStatsTracker imageCacheStatsTracker;
        private ImageDecoder imageDecoder;
        private ImageTranscoderFactory imageTranscoderFactory;
        private Integer imageTranscoderType;
        private Supplier isPrefetchEnabledSupplier;
        private DiskCacheConfig mainDiskCacheConfig;
        private Integer memoryChunkType;
        private MemoryTrimmableRegistry memoryTrimmableRegistry;
        private NetworkFetcher networkFetcher;
        private PlatformBitmapFactory platformBitmapFactory;
        private PoolFactory poolFactory;
        private ProgressiveJpegConfig progressiveJpegConfig;
        private Set requestListener2s;
        private Set requestListeners;
        private boolean resizeAndRotateEnabledForNetwork = true;
        private DiskCacheConfig smallImageDiskCacheConfig;

        public final CountingMemoryCache.EntryStateObserver getBitmapMemoryCacheEntryStateObserver() {
            return null;
        }

        public final CallerContextVerifier getCallerContextVerifier() {
            return null;
        }

        public final ImageDecoderConfig getImageDecoderConfig() {
            return null;
        }

        public final SerialExecutorService getSerialExecutorServiceForAnimatedImages() {
            return null;
        }

        public Builder(Context context2) {
            Intrinsics.checkNotNullParameter(context2, "context");
            this.context = context2;
        }

        public final Bitmap.Config getBitmapConfig() {
            return this.bitmapConfig;
        }

        public final Supplier getBitmapMemoryCacheParamsSupplier() {
            return this.bitmapMemoryCacheParamsSupplier;
        }

        public final MemoryCache.CacheTrimStrategy getBitmapMemoryCacheTrimStrategy() {
            return this.bitmapMemoryCacheTrimStrategy;
        }

        public final MemoryCache.CacheTrimStrategy getEncodedMemoryCacheTrimStrategy() {
            return this.encodedMemoryCacheTrimStrategy;
        }

        public final CacheKeyFactory getCacheKeyFactory() {
            return this.cacheKeyFactory;
        }

        public final Context getContext() {
            return this.context;
        }

        public final DownsampleMode getDownsampleMode() {
            return this.downsampleMode;
        }

        public final Supplier getEncodedMemoryCacheParamsSupplier() {
            return this.encodedMemoryCacheParamsSupplier;
        }

        public final ExecutorSupplier getExecutorSupplier() {
            return this.executorSupplier;
        }

        public final ImageCacheStatsTracker getImageCacheStatsTracker() {
            return this.imageCacheStatsTracker;
        }

        public final ImageDecoder getImageDecoder() {
            return this.imageDecoder;
        }

        public final Supplier getEnableEncodedImageColorSpaceUsage() {
            return this.enableEncodedImageColorSpaceUsage;
        }

        public final ImageTranscoderFactory getImageTranscoderFactory() {
            return this.imageTranscoderFactory;
        }

        public final Integer getImageTranscoderType() {
            return this.imageTranscoderType;
        }

        public final Supplier isPrefetchEnabledSupplier() {
            return this.isPrefetchEnabledSupplier;
        }

        public final DiskCacheConfig getMainDiskCacheConfig() {
            return this.mainDiskCacheConfig;
        }

        public final MemoryTrimmableRegistry getMemoryTrimmableRegistry() {
            return this.memoryTrimmableRegistry;
        }

        public final Integer getMemoryChunkType() {
            return this.memoryChunkType;
        }

        public final NetworkFetcher getNetworkFetcher() {
            return this.networkFetcher;
        }

        public final PlatformBitmapFactory getPlatformBitmapFactory() {
            return this.platformBitmapFactory;
        }

        public final PoolFactory getPoolFactory() {
            return this.poolFactory;
        }

        public final ProgressiveJpegConfig getProgressiveJpegConfig() {
            return this.progressiveJpegConfig;
        }

        public final Set getRequestListeners() {
            return this.requestListeners;
        }

        public final Set getRequestListener2s() {
            return this.requestListener2s;
        }

        public final Set getCustomProducerSequenceFactories() {
            return this.customProducerSequenceFactories;
        }

        public final boolean getResizeAndRotateEnabledForNetwork() {
            return this.resizeAndRotateEnabledForNetwork;
        }

        public final DiskCacheConfig getSmallImageDiskCacheConfig() {
            return this.smallImageDiskCacheConfig;
        }

        public final FileCacheFactory getFileCacheFactory() {
            return this.fileCacheFactory;
        }

        public final Supplier getDiskCachesStoreSupplier() {
            return this.diskCachesStoreSupplier;
        }

        public final int getHttpConnectionTimeout() {
            return this.httpConnectionTimeout;
        }

        public final ImagePipelineExperiments.Builder getExperimentsBuilder() {
            return this.experimentsBuilder;
        }

        public final boolean getDiskCacheEnabled() {
            return this.diskCacheEnabled;
        }

        public final CloseableReferenceLeakTracker getCloseableReferenceLeakTracker() {
            return this.closeableReferenceLeakTracker;
        }

        public final MemoryCache getBitmapMemoryCache() {
            return this.bitmapMemoryCache;
        }

        public final MemoryCache getEncodedMemoryCache() {
            return this.encodedMemoryCache;
        }

        public final BitmapMemoryCacheFactory getBitmapMemoryCacheFactory() {
            return this.bitmapMemoryCacheFactory;
        }

        public final Map getDynamicDiskCacheConfigMap() {
            return this.dynamicDiskCacheConfigMap;
        }

        public final Builder setDownsampleMode(DownsampleMode downsampleMode2) {
            Intrinsics.checkNotNullParameter(downsampleMode2, "downsampleMode");
            this.downsampleMode = downsampleMode2;
            return this;
        }

        public final Builder setNetworkFetcher(NetworkFetcher networkFetcher2) {
            this.networkFetcher = networkFetcher2;
            return this;
        }

        public final Builder setRequestListeners(Set set) {
            this.requestListeners = set;
            return this;
        }

        public final ImagePipelineExperiments.Builder experiment() {
            return this.experimentsBuilder;
        }

        public final ImagePipelineConfig build() {
            return new ImagePipelineConfig(this, (DefaultConstructorMarker) null);
        }
    }
}
