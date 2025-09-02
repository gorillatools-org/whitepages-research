package com.facebook.imagepipeline.core;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import com.facebook.common.internal.Supplier;
import com.facebook.common.internal.Suppliers;
import com.facebook.common.memory.ByteArrayPool;
import com.facebook.common.memory.PooledByteBufferFactory;
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory;
import com.facebook.imagepipeline.cache.BoundedLinkedHashSet;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.cache.MemoryCache;
import com.facebook.imagepipeline.decoder.ImageDecoder;
import com.facebook.imagepipeline.decoder.ProgressiveJpegConfig;
import com.facebook.imagepipeline.producers.AddImageTransformMetaDataProducer;
import com.facebook.imagepipeline.producers.BitmapMemoryCacheGetProducer;
import com.facebook.imagepipeline.producers.BitmapMemoryCacheKeyMultiplexProducer;
import com.facebook.imagepipeline.producers.BitmapMemoryCacheProducer;
import com.facebook.imagepipeline.producers.BitmapPrepareProducer;
import com.facebook.imagepipeline.producers.BitmapProbeProducer;
import com.facebook.imagepipeline.producers.BranchOnSeparateImagesProducer;
import com.facebook.imagepipeline.producers.DataFetchProducer;
import com.facebook.imagepipeline.producers.DecodeProducer;
import com.facebook.imagepipeline.producers.DelayProducer;
import com.facebook.imagepipeline.producers.DiskCacheReadProducer;
import com.facebook.imagepipeline.producers.DiskCacheWriteProducer;
import com.facebook.imagepipeline.producers.EncodedCacheKeyMultiplexProducer;
import com.facebook.imagepipeline.producers.EncodedMemoryCacheProducer;
import com.facebook.imagepipeline.producers.EncodedProbeProducer;
import com.facebook.imagepipeline.producers.LocalAssetFetchProducer;
import com.facebook.imagepipeline.producers.LocalContentUriFetchProducer;
import com.facebook.imagepipeline.producers.LocalContentUriThumbnailFetchProducer;
import com.facebook.imagepipeline.producers.LocalExifThumbnailProducer;
import com.facebook.imagepipeline.producers.LocalFileFetchProducer;
import com.facebook.imagepipeline.producers.LocalResourceFetchProducer;
import com.facebook.imagepipeline.producers.LocalThumbnailBitmapSdk29Producer;
import com.facebook.imagepipeline.producers.LocalVideoThumbnailProducer;
import com.facebook.imagepipeline.producers.NetworkFetchProducer;
import com.facebook.imagepipeline.producers.NetworkFetcher;
import com.facebook.imagepipeline.producers.PartialDiskCacheProducer;
import com.facebook.imagepipeline.producers.PostprocessedBitmapMemoryCacheProducer;
import com.facebook.imagepipeline.producers.PostprocessorProducer;
import com.facebook.imagepipeline.producers.Producer;
import com.facebook.imagepipeline.producers.QualifiedResourceFetchProducer;
import com.facebook.imagepipeline.producers.ResizeAndRotateProducer;
import com.facebook.imagepipeline.producers.SwallowResultProducer;
import com.facebook.imagepipeline.producers.ThreadHandoffProducer;
import com.facebook.imagepipeline.producers.ThreadHandoffProducerQueue;
import com.facebook.imagepipeline.producers.ThrottlingProducer;
import com.facebook.imagepipeline.producers.ThumbnailBranchProducer;
import com.facebook.imagepipeline.producers.ThumbnailProducer;
import com.facebook.imagepipeline.transcoder.ImageTranscoderFactory;

public class ProducerFactory {
    protected AssetManager mAssetManager;
    protected final MemoryCache mBitmapMemoryCache;
    protected boolean mBitmapPrepareToDrawForPrefetch;
    protected final int mBitmapPrepareToDrawMaxSizeBytes;
    protected final int mBitmapPrepareToDrawMinSizeBytes;
    protected final ByteArrayPool mByteArrayPool;
    protected final CacheKeyFactory mCacheKeyFactory;
    protected final CloseableReferenceFactory mCloseableReferenceFactory;
    protected ContentResolver mContentResolver;
    protected final boolean mDecodeCancellationEnabled;
    protected final BoundedLinkedHashSet mDiskCacheHistory;
    protected final Supplier mDiskCachesStoreSupplier;
    protected final DownsampleMode mDownsampleMode;
    protected final MemoryCache mEncodedMemoryCache;
    protected final BoundedLinkedHashSet mEncodedMemoryCacheHistory;
    protected final ExecutorSupplier mExecutorSupplier;
    protected final ImageDecoder mImageDecoder;
    protected final boolean mKeepCancelledFetchAsLowPriority;
    protected final int mMaxBitmapSize;
    protected final PlatformBitmapFactory mPlatformBitmapFactory;
    protected final PooledByteBufferFactory mPooledByteBufferFactory;
    protected final ProgressiveJpegConfig mProgressiveJpegConfig;
    protected final boolean mResizeAndRotateEnabledForNetwork;
    protected Resources mResources;

    public ProducerFactory(Context context, ByteArrayPool byteArrayPool, ImageDecoder imageDecoder, ProgressiveJpegConfig progressiveJpegConfig, DownsampleMode downsampleMode, boolean z, boolean z2, ExecutorSupplier executorSupplier, PooledByteBufferFactory pooledByteBufferFactory, MemoryCache memoryCache, MemoryCache memoryCache2, Supplier supplier, CacheKeyFactory cacheKeyFactory, PlatformBitmapFactory platformBitmapFactory, int i, int i2, boolean z3, int i3, CloseableReferenceFactory closeableReferenceFactory, boolean z4, int i4) {
        int i5 = i4;
        this.mContentResolver = context.getApplicationContext().getContentResolver();
        this.mResources = context.getApplicationContext().getResources();
        this.mAssetManager = context.getApplicationContext().getAssets();
        this.mByteArrayPool = byteArrayPool;
        this.mImageDecoder = imageDecoder;
        this.mProgressiveJpegConfig = progressiveJpegConfig;
        this.mDownsampleMode = downsampleMode;
        this.mResizeAndRotateEnabledForNetwork = z;
        this.mDecodeCancellationEnabled = z2;
        this.mExecutorSupplier = executorSupplier;
        this.mPooledByteBufferFactory = pooledByteBufferFactory;
        this.mBitmapMemoryCache = memoryCache;
        this.mEncodedMemoryCache = memoryCache2;
        this.mDiskCachesStoreSupplier = supplier;
        this.mCacheKeyFactory = cacheKeyFactory;
        this.mPlatformBitmapFactory = platformBitmapFactory;
        this.mEncodedMemoryCacheHistory = new BoundedLinkedHashSet(i5);
        this.mDiskCacheHistory = new BoundedLinkedHashSet(i5);
        this.mBitmapPrepareToDrawMinSizeBytes = i;
        this.mBitmapPrepareToDrawMaxSizeBytes = i2;
        this.mBitmapPrepareToDrawForPrefetch = z3;
        this.mMaxBitmapSize = i3;
        this.mCloseableReferenceFactory = closeableReferenceFactory;
        this.mKeepCancelledFetchAsLowPriority = z4;
    }

    public static AddImageTransformMetaDataProducer newAddImageTransformMetaDataProducer(Producer producer) {
        return new AddImageTransformMetaDataProducer(producer);
    }

    public BitmapMemoryCacheGetProducer newBitmapMemoryCacheGetProducer(Producer producer) {
        return new BitmapMemoryCacheGetProducer(this.mBitmapMemoryCache, this.mCacheKeyFactory, producer);
    }

    public BitmapMemoryCacheKeyMultiplexProducer newBitmapMemoryCacheKeyMultiplexProducer(Producer producer) {
        return new BitmapMemoryCacheKeyMultiplexProducer(this.mCacheKeyFactory, producer);
    }

    public BitmapMemoryCacheProducer newBitmapMemoryCacheProducer(Producer producer) {
        return new BitmapMemoryCacheProducer(this.mBitmapMemoryCache, this.mCacheKeyFactory, producer);
    }

    public static BranchOnSeparateImagesProducer newBranchOnSeparateImagesProducer(Producer producer, Producer producer2) {
        return new BranchOnSeparateImagesProducer(producer, producer2);
    }

    public DataFetchProducer newDataFetchProducer() {
        return new DataFetchProducer(this.mPooledByteBufferFactory);
    }

    public DecodeProducer newDecodeProducer(Producer producer) {
        return new DecodeProducer(this.mByteArrayPool, this.mExecutorSupplier.forDecode(), this.mImageDecoder, this.mProgressiveJpegConfig, this.mDownsampleMode, this.mResizeAndRotateEnabledForNetwork, this.mDecodeCancellationEnabled, producer, this.mMaxBitmapSize, this.mCloseableReferenceFactory, (Runnable) null, Suppliers.BOOLEAN_FALSE);
    }

    public DiskCacheReadProducer newDiskCacheReadProducer(Producer producer) {
        return new DiskCacheReadProducer(this.mDiskCachesStoreSupplier, this.mCacheKeyFactory, producer);
    }

    public DiskCacheWriteProducer newDiskCacheWriteProducer(Producer producer) {
        return new DiskCacheWriteProducer(this.mDiskCachesStoreSupplier, this.mCacheKeyFactory, producer);
    }

    public PartialDiskCacheProducer newPartialDiskCacheProducer(Producer producer) {
        return new PartialDiskCacheProducer(this.mDiskCachesStoreSupplier, this.mCacheKeyFactory, this.mPooledByteBufferFactory, this.mByteArrayPool, producer);
    }

    public EncodedCacheKeyMultiplexProducer newEncodedCacheKeyMultiplexProducer(Producer producer) {
        return new EncodedCacheKeyMultiplexProducer(this.mCacheKeyFactory, this.mKeepCancelledFetchAsLowPriority, producer);
    }

    public BitmapProbeProducer newBitmapProbeProducer(Producer producer) {
        return new BitmapProbeProducer(this.mEncodedMemoryCache, this.mDiskCachesStoreSupplier, this.mCacheKeyFactory, this.mEncodedMemoryCacheHistory, this.mDiskCacheHistory, producer);
    }

    public EncodedProbeProducer newEncodedProbeProducer(Producer producer) {
        return new EncodedProbeProducer(this.mDiskCachesStoreSupplier, this.mCacheKeyFactory, this.mEncodedMemoryCacheHistory, this.mDiskCacheHistory, producer);
    }

    public Producer newEncodedMemoryCacheProducer(Producer producer) {
        return new EncodedMemoryCacheProducer(this.mEncodedMemoryCache, this.mCacheKeyFactory, producer);
    }

    public LocalAssetFetchProducer newLocalAssetFetchProducer() {
        return new LocalAssetFetchProducer(this.mExecutorSupplier.forLocalStorageRead(), this.mPooledByteBufferFactory, this.mAssetManager);
    }

    public LocalContentUriFetchProducer newLocalContentUriFetchProducer() {
        return new LocalContentUriFetchProducer(this.mExecutorSupplier.forLocalStorageRead(), this.mPooledByteBufferFactory, this.mContentResolver);
    }

    public LocalContentUriThumbnailFetchProducer newLocalContentUriThumbnailFetchProducer() {
        return new LocalContentUriThumbnailFetchProducer(this.mExecutorSupplier.forLocalStorageRead(), this.mPooledByteBufferFactory, this.mContentResolver);
    }

    public LocalExifThumbnailProducer newLocalExifThumbnailProducer() {
        return new LocalExifThumbnailProducer(this.mExecutorSupplier.forThumbnailProducer(), this.mPooledByteBufferFactory, this.mContentResolver);
    }

    public ThumbnailBranchProducer newThumbnailBranchProducer(ThumbnailProducer[] thumbnailProducerArr) {
        return new ThumbnailBranchProducer(thumbnailProducerArr);
    }

    public LocalFileFetchProducer newLocalFileFetchProducer() {
        return new LocalFileFetchProducer(this.mExecutorSupplier.forLocalStorageRead(), this.mPooledByteBufferFactory);
    }

    public QualifiedResourceFetchProducer newQualifiedResourceFetchProducer() {
        return new QualifiedResourceFetchProducer(this.mExecutorSupplier.forLocalStorageRead(), this.mPooledByteBufferFactory, this.mContentResolver);
    }

    public LocalResourceFetchProducer newLocalResourceFetchProducer() {
        return new LocalResourceFetchProducer(this.mExecutorSupplier.forLocalStorageRead(), this.mPooledByteBufferFactory, this.mResources);
    }

    public LocalVideoThumbnailProducer newLocalVideoThumbnailProducer() {
        return new LocalVideoThumbnailProducer(this.mExecutorSupplier.forLocalStorageRead(), this.mContentResolver);
    }

    public Producer newNetworkFetchProducer(NetworkFetcher networkFetcher) {
        return new NetworkFetchProducer(this.mPooledByteBufferFactory, this.mByteArrayPool, networkFetcher);
    }

    public PostprocessedBitmapMemoryCacheProducer newPostprocessorBitmapMemoryCacheProducer(Producer producer) {
        return new PostprocessedBitmapMemoryCacheProducer(this.mBitmapMemoryCache, this.mCacheKeyFactory, producer);
    }

    public PostprocessorProducer newPostprocessorProducer(Producer producer) {
        return new PostprocessorProducer(producer, this.mPlatformBitmapFactory, this.mExecutorSupplier.forBackgroundTasks());
    }

    public ResizeAndRotateProducer newResizeAndRotateProducer(Producer producer, boolean z, ImageTranscoderFactory imageTranscoderFactory) {
        return new ResizeAndRotateProducer(this.mExecutorSupplier.forBackgroundTasks(), this.mPooledByteBufferFactory, producer, z, imageTranscoderFactory);
    }

    public SwallowResultProducer newSwallowResultProducer(Producer producer) {
        return new SwallowResultProducer(producer);
    }

    public Producer newBackgroundThreadHandoffProducer(Producer producer, ThreadHandoffProducerQueue threadHandoffProducerQueue) {
        return new ThreadHandoffProducer(producer, threadHandoffProducerQueue);
    }

    public ThrottlingProducer newThrottlingProducer(Producer producer) {
        return new ThrottlingProducer(5, this.mExecutorSupplier.forLightweightBackgroundTasks(), producer);
    }

    public BitmapPrepareProducer newBitmapPrepareProducer(Producer producer) {
        return new BitmapPrepareProducer(producer, this.mBitmapPrepareToDrawMinSizeBytes, this.mBitmapPrepareToDrawMaxSizeBytes, this.mBitmapPrepareToDrawForPrefetch);
    }

    public DelayProducer newDelayProducer(Producer producer) {
        return new DelayProducer(producer, this.mExecutorSupplier.scheduledExecutorServiceForBackgroundTasks());
    }

    public LocalThumbnailBitmapSdk29Producer newLocalThumbnailBitmapSdk29Producer() {
        return new LocalThumbnailBitmapSdk29Producer(this.mExecutorSupplier.forBackgroundTasks(), this.mContentResolver);
    }
}
