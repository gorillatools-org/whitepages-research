package com.facebook.imagepipeline.core;

import android.content.Context;
import com.facebook.callercontext.CallerContextVerifier;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Supplier;
import com.facebook.common.logging.FLog;
import com.facebook.common.memory.MemoryTrimmableRegistry;
import com.facebook.imageformat.ImageFormatChecker;
import com.facebook.imagepipeline.animated.factory.AnimatedFactory;
import com.facebook.imagepipeline.animated.factory.AnimatedFactoryProvider;
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory;
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactoryProvider;
import com.facebook.imagepipeline.cache.BitmapMemoryCacheFactory;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.cache.CountingMemoryCache;
import com.facebook.imagepipeline.cache.EncodedCountingMemoryCacheFactory;
import com.facebook.imagepipeline.cache.EncodedMemoryCacheFactory;
import com.facebook.imagepipeline.cache.InstrumentedMemoryCache;
import com.facebook.imagepipeline.cache.InstrumentedMemoryCacheBitmapMemoryCacheFactory;
import com.facebook.imagepipeline.cache.MemoryCache;
import com.facebook.imagepipeline.decoder.DefaultImageDecoder;
import com.facebook.imagepipeline.decoder.ImageDecoder;
import com.facebook.imagepipeline.drawable.DrawableFactory;
import com.facebook.imagepipeline.platform.PlatformDecoder;
import com.facebook.imagepipeline.platform.PlatformDecoderFactory;
import com.facebook.imagepipeline.producers.ExperimentalThreadHandoffProducerQueueImpl;
import com.facebook.imagepipeline.producers.ThreadHandoffProducerQueue;
import com.facebook.imagepipeline.producers.ThreadHandoffProducerQueueImpl;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import com.facebook.imagepipeline.transcoder.ImageTranscoderFactory;
import com.facebook.imagepipeline.transcoder.MultiImageTranscoderFactory;
import com.facebook.imagepipeline.transcoder.SimpleImageTranscoderFactory;
import com.facebook.imagepipeline.xml.XmlDrawableFactory;
import com.facebook.imagepipeline.xml.XmlFormatDecoder;
import java.util.Set;
import java.util.concurrent.ExecutorService;

public class ImagePipelineFactory {
    private static final Class TAG = ImagePipelineFactory.class;
    private static boolean sForceSingleInstance;
    private static ImagePipeline sImagePipeline;
    private static ImagePipelineFactory sInstance;
    private CountingMemoryCache mBitmapCountingMemoryCache;
    private InstrumentedMemoryCache mBitmapMemoryCache;
    private final CloseableReferenceFactory mCloseableReferenceFactory;
    private final ImagePipelineConfigInterface mConfig;
    private final Supplier mDiskCachesStoreSupplier;
    private CountingMemoryCache mEncodedCountingMemoryCache;
    private InstrumentedMemoryCache mEncodedMemoryCache;
    private ImageDecoder mImageDecoder;
    private ImageTranscoderFactory mImageTranscoderFactory;
    private PlatformBitmapFactory mPlatformBitmapFactory;
    private PlatformDecoder mPlatformDecoder;
    private ProducerFactory mProducerFactory;
    private ProducerSequenceFactory mProducerSequenceFactory;
    private final ThreadHandoffProducerQueue mThreadHandoffProducerQueue;

    public static ImagePipelineFactory getInstance() {
        return (ImagePipelineFactory) Preconditions.checkNotNull(sInstance, "ImagePipelineFactory was not initialized!");
    }

    public static synchronized void initialize(Context context) {
        synchronized (ImagePipelineFactory.class) {
            try {
                if (FrescoSystrace.isTracing()) {
                    FrescoSystrace.beginSection("ImagePipelineFactory#initialize");
                }
                initialize((ImagePipelineConfigInterface) ImagePipelineConfig.newBuilder(context).build());
                if (FrescoSystrace.isTracing()) {
                    FrescoSystrace.endSection();
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
    }

    public static synchronized void initialize(ImagePipelineConfigInterface imagePipelineConfigInterface) {
        synchronized (ImagePipelineFactory.class) {
            if (sInstance != null) {
                FLog.w(TAG, "ImagePipelineFactory has already been initialized! `ImagePipelineFactory.initialize(...)` should only be called once to avoid unexpected behavior.");
                if (sForceSingleInstance) {
                    return;
                }
            }
            sInstance = new ImagePipelineFactory(imagePipelineConfigInterface);
        }
    }

    public ImagePipelineFactory(ImagePipelineConfigInterface imagePipelineConfigInterface) {
        ThreadHandoffProducerQueue threadHandoffProducerQueue;
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.beginSection("ImagePipelineConfig()");
        }
        ImagePipelineConfigInterface imagePipelineConfigInterface2 = (ImagePipelineConfigInterface) Preconditions.checkNotNull(imagePipelineConfigInterface);
        this.mConfig = imagePipelineConfigInterface2;
        if (imagePipelineConfigInterface2.getExperiments().isExperimentalThreadHandoffQueueEnabled()) {
            threadHandoffProducerQueue = new ExperimentalThreadHandoffProducerQueueImpl(imagePipelineConfigInterface.getExecutorSupplier().forLightweightBackgroundTasks());
        } else {
            threadHandoffProducerQueue = new ThreadHandoffProducerQueueImpl(imagePipelineConfigInterface.getExecutorSupplier().forLightweightBackgroundTasks());
        }
        this.mThreadHandoffProducerQueue = threadHandoffProducerQueue;
        this.mCloseableReferenceFactory = new CloseableReferenceFactory(imagePipelineConfigInterface.getCloseableReferenceLeakTracker());
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.endSection();
        }
        this.mDiskCachesStoreSupplier = imagePipelineConfigInterface2.getDiskCachesStoreSupplier();
        if (imagePipelineConfigInterface2.getExperiments().isBinaryXmlEnabled()) {
            ImageFormatChecker.getInstance().setBinaryXmlEnabled(true);
        }
    }

    private AnimatedFactory getAnimatedFactory() {
        PlatformBitmapFactory platformBitmapFactory = getPlatformBitmapFactory();
        ExecutorSupplier executorSupplier = this.mConfig.getExecutorSupplier();
        CountingMemoryCache bitmapCountingMemoryCache = getBitmapCountingMemoryCache();
        boolean downscaleFrameToDrawableDimensions = this.mConfig.getExperiments().getDownscaleFrameToDrawableDimensions();
        boolean useBalancedAnimationStrategy = this.mConfig.getExperiments().getUseBalancedAnimationStrategy();
        int animationRenderFpsLimit = this.mConfig.getExperiments().getAnimationRenderFpsLimit();
        int animationStrategyBufferLengthMilliseconds = this.mConfig.getExperiments().getAnimationStrategyBufferLengthMilliseconds();
        this.mConfig.getExecutorServiceForAnimatedImages();
        AnimatedFactoryProvider.getAnimatedFactory(platformBitmapFactory, executorSupplier, bitmapCountingMemoryCache, downscaleFrameToDrawableDimensions, useBalancedAnimationStrategy, animationRenderFpsLimit, animationStrategyBufferLengthMilliseconds, (ExecutorService) null);
        return null;
    }

    public DrawableFactory getAnimatedDrawableFactory(Context context) {
        getAnimatedFactory();
        return null;
    }

    public CountingMemoryCache getBitmapCountingMemoryCache() {
        if (this.mBitmapCountingMemoryCache == null) {
            BitmapMemoryCacheFactory bitmapMemoryCacheFactory = this.mConfig.getBitmapMemoryCacheFactory();
            Supplier bitmapMemoryCacheParamsSupplier = this.mConfig.getBitmapMemoryCacheParamsSupplier();
            MemoryTrimmableRegistry memoryTrimmableRegistry = this.mConfig.getMemoryTrimmableRegistry();
            MemoryCache.CacheTrimStrategy bitmapMemoryCacheTrimStrategy = this.mConfig.getBitmapMemoryCacheTrimStrategy();
            boolean shouldStoreCacheEntrySize = this.mConfig.getExperiments().getShouldStoreCacheEntrySize();
            boolean shouldIgnoreCacheSizeMismatch = this.mConfig.getExperiments().getShouldIgnoreCacheSizeMismatch();
            this.mConfig.getBitmapMemoryCacheEntryStateObserver();
            this.mBitmapCountingMemoryCache = bitmapMemoryCacheFactory.create(bitmapMemoryCacheParamsSupplier, memoryTrimmableRegistry, bitmapMemoryCacheTrimStrategy, shouldStoreCacheEntrySize, shouldIgnoreCacheSizeMismatch, (CountingMemoryCache.EntryStateObserver) null);
        }
        return this.mBitmapCountingMemoryCache;
    }

    public InstrumentedMemoryCache getBitmapMemoryCache() {
        if (this.mBitmapMemoryCache == null) {
            this.mBitmapMemoryCache = InstrumentedMemoryCacheBitmapMemoryCacheFactory.get(getBitmapCountingMemoryCache(), this.mConfig.getImageCacheStatsTracker());
        }
        return this.mBitmapMemoryCache;
    }

    public CountingMemoryCache getEncodedCountingMemoryCache() {
        if (this.mEncodedCountingMemoryCache == null) {
            this.mEncodedCountingMemoryCache = EncodedCountingMemoryCacheFactory.get(this.mConfig.getEncodedMemoryCacheParamsSupplier(), this.mConfig.getMemoryTrimmableRegistry(), this.mConfig.getEncodedMemoryCacheTrimStrategy());
        }
        return this.mEncodedCountingMemoryCache;
    }

    public InstrumentedMemoryCache getEncodedMemoryCache() {
        MemoryCache memoryCache;
        if (this.mEncodedMemoryCache == null) {
            if (this.mConfig.getEncodedMemoryCacheOverride() != null) {
                memoryCache = this.mConfig.getEncodedMemoryCacheOverride();
            } else {
                memoryCache = getEncodedCountingMemoryCache();
            }
            this.mEncodedMemoryCache = EncodedMemoryCacheFactory.get(memoryCache, this.mConfig.getImageCacheStatsTracker());
        }
        return this.mEncodedMemoryCache;
    }

    private ImageDecoder getImageDecoder() {
        if (this.mImageDecoder == null) {
            if (this.mConfig.getImageDecoder() != null) {
                this.mImageDecoder = this.mConfig.getImageDecoder();
            } else {
                getAnimatedFactory();
                ImageDecoder xmlImageDecoder = getXmlImageDecoder();
                this.mConfig.getImageDecoderConfig();
                this.mImageDecoder = new DefaultImageDecoder((ImageDecoder) null, (ImageDecoder) null, xmlImageDecoder, getPlatformDecoder());
            }
        }
        return this.mImageDecoder;
    }

    public ImagePipeline getImagePipeline() {
        if (sImagePipeline == null) {
            sImagePipeline = createImagePipeline();
        }
        return sImagePipeline;
    }

    private ImagePipeline createImagePipeline() {
        ProducerSequenceFactory producerSequenceFactory = getProducerSequenceFactory();
        Set requestListeners = this.mConfig.getRequestListeners();
        Set requestListener2s = this.mConfig.getRequestListener2s();
        Supplier isPrefetchEnabledSupplier = this.mConfig.isPrefetchEnabledSupplier();
        InstrumentedMemoryCache bitmapMemoryCache = getBitmapMemoryCache();
        InstrumentedMemoryCache encodedMemoryCache = getEncodedMemoryCache();
        Supplier supplier = this.mDiskCachesStoreSupplier;
        CacheKeyFactory cacheKeyFactory = this.mConfig.getCacheKeyFactory();
        ThreadHandoffProducerQueue threadHandoffProducerQueue = this.mThreadHandoffProducerQueue;
        Supplier suppressBitmapPrefetchingSupplier = this.mConfig.getExperiments().getSuppressBitmapPrefetchingSupplier();
        Supplier isLazyDataSource = this.mConfig.getExperiments().isLazyDataSource();
        this.mConfig.getCallerContextVerifier();
        return new ImagePipeline(producerSequenceFactory, requestListeners, requestListener2s, isPrefetchEnabledSupplier, bitmapMemoryCache, encodedMemoryCache, supplier, cacheKeyFactory, threadHandoffProducerQueue, suppressBitmapPrefetchingSupplier, isLazyDataSource, (CallerContextVerifier) null, this.mConfig);
    }

    public PlatformBitmapFactory getPlatformBitmapFactory() {
        if (this.mPlatformBitmapFactory == null) {
            this.mPlatformBitmapFactory = PlatformBitmapFactoryProvider.buildPlatformBitmapFactory(this.mConfig.getPoolFactory(), getPlatformDecoder(), getCloseableReferenceFactory());
        }
        return this.mPlatformBitmapFactory;
    }

    public PlatformDecoder getPlatformDecoder() {
        if (this.mPlatformDecoder == null) {
            this.mPlatformDecoder = PlatformDecoderFactory.buildPlatformDecoder(this.mConfig.getPoolFactory(), this.mConfig.getExperiments().isGingerbreadDecoderEnabled(), this.mConfig.getExperiments().getShouldUseDecodingBufferHelper(), this.mConfig.getExperiments().getPlatformDecoderOptions());
        }
        return this.mPlatformDecoder;
    }

    private ProducerFactory getProducerFactory() {
        if (this.mProducerFactory == null) {
            this.mProducerFactory = this.mConfig.getExperiments().getProducerFactoryMethod().createProducerFactory(this.mConfig.getContext(), this.mConfig.getPoolFactory().getSmallByteArrayPool(), getImageDecoder(), this.mConfig.getProgressiveJpegConfig(), this.mConfig.getDownsampleMode(), this.mConfig.isResizeAndRotateEnabledForNetwork(), this.mConfig.getExperiments().isDecodeCancellationEnabled(), this.mConfig.getExecutorSupplier(), this.mConfig.getPoolFactory().getPooledByteBufferFactory(this.mConfig.getMemoryChunkType()), this.mConfig.getPoolFactory().getPooledByteStreams(), getBitmapMemoryCache(), getEncodedMemoryCache(), this.mDiskCachesStoreSupplier, this.mConfig.getCacheKeyFactory(), getPlatformBitmapFactory(), this.mConfig.getExperiments().getBitmapPrepareToDrawMinSizeBytes(), this.mConfig.getExperiments().getBitmapPrepareToDrawMaxSizeBytes(), this.mConfig.getExperiments().getBitmapPrepareToDrawForPrefetch(), this.mConfig.getExperiments().getMaxBitmapDimension(), getCloseableReferenceFactory(), this.mConfig.getExperiments().getKeepCancelledFetchAsLowPriority(), this.mConfig.getExperiments().getTrackedKeysSize());
        }
        return this.mProducerFactory;
    }

    private ProducerSequenceFactory getProducerSequenceFactory() {
        boolean useBitmapPrepareToDraw = this.mConfig.getExperiments().getUseBitmapPrepareToDraw();
        if (this.mProducerSequenceFactory == null) {
            this.mProducerSequenceFactory = new ProducerSequenceFactory(this.mConfig.getContext().getApplicationContext().getContentResolver(), getProducerFactory(), this.mConfig.getNetworkFetcher(), this.mConfig.isResizeAndRotateEnabledForNetwork(), this.mConfig.getExperiments().isWebpSupportEnabled(), this.mThreadHandoffProducerQueue, this.mConfig.getDownsampleMode(), useBitmapPrepareToDraw, this.mConfig.getExperiments().isPartialImageCachingEnabled(), this.mConfig.isDiskCacheEnabled(), getImageTranscoderFactory(), this.mConfig.getExperiments().isEncodedMemoryCacheProbingEnabled(), this.mConfig.getExperiments().isDiskCacheProbingEnabled(), this.mConfig.getExperiments().getAllowDelay(), this.mConfig.getCustomProducerSequenceFactories());
        }
        return this.mProducerSequenceFactory;
    }

    public CloseableReferenceFactory getCloseableReferenceFactory() {
        return this.mCloseableReferenceFactory;
    }

    private ImageTranscoderFactory getImageTranscoderFactory() {
        if (this.mImageTranscoderFactory == null) {
            if (this.mConfig.getImageTranscoderFactory() == null && this.mConfig.getImageTranscoderType() == null && this.mConfig.getExperiments().isNativeCodeDisabled()) {
                this.mImageTranscoderFactory = new SimpleImageTranscoderFactory(this.mConfig.getExperiments().getMaxBitmapDimension());
            } else {
                this.mImageTranscoderFactory = new MultiImageTranscoderFactory(this.mConfig.getExperiments().getMaxBitmapDimension(), this.mConfig.getExperiments().getUseDownsamplingRatioForResizing(), this.mConfig.getImageTranscoderFactory(), this.mConfig.getImageTranscoderType(), this.mConfig.getExperiments().isEnsureTranscoderLibraryLoaded());
            }
        }
        return this.mImageTranscoderFactory;
    }

    public ImageDecoder getXmlImageDecoder() {
        if (this.mConfig.getExperiments().isBinaryXmlEnabled()) {
            return new XmlFormatDecoder(this.mConfig.getContext().getApplicationContext().getResources());
        }
        return null;
    }

    public DrawableFactory getXmlDrawableFactory() {
        if (this.mConfig.getExperiments().isBinaryXmlEnabled()) {
            return new XmlDrawableFactory();
        }
        return null;
    }
}
