package com.facebook.imagepipeline.core;

import android.net.Uri;
import android.os.StrictMode;
import com.facebook.cache.common.CacheKey;
import com.facebook.callercontext.CallerContextVerifier;
import com.facebook.common.internal.Predicate;
import com.facebook.common.internal.Supplier;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.util.UriUtil;
import com.facebook.datasource.DataSource;
import com.facebook.datasource.DataSources;
import com.facebook.fresco.urimod.UriModifier;
import com.facebook.imagepipeline.cache.BufferedDiskCache;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.cache.MemoryCache;
import com.facebook.imagepipeline.common.Priority;
import com.facebook.imagepipeline.datasource.CloseableProducerToDataSourceAdapter;
import com.facebook.imagepipeline.datasource.ProducerToDataSourceAdapter;
import com.facebook.imagepipeline.listener.ForwardingRequestListener;
import com.facebook.imagepipeline.listener.ForwardingRequestListener2;
import com.facebook.imagepipeline.listener.RequestListener;
import com.facebook.imagepipeline.listener.RequestListener2;
import com.facebook.imagepipeline.producers.InternalRequestListener;
import com.facebook.imagepipeline.producers.Producer;
import com.facebook.imagepipeline.producers.SettableProducerContext;
import com.facebook.imagepipeline.producers.ThreadHandoffProducerQueue;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicLong;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class ImagePipeline {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final CancellationException MODIFIED_URL_IS_NULL = new CancellationException("Modified URL is null");
    private static final CancellationException NULL_IMAGEREQUEST_EXCEPTION = new CancellationException("ImageRequest is null");
    private static final CancellationException PREFETCH_EXCEPTION = new CancellationException("Prefetching is not enabled");
    private final MemoryCache bitmapMemoryCache;
    private final CacheKeyFactory cacheKeyFactory;
    private final ImagePipelineConfigInterface config;
    private final Supplier diskCachesStoreSupplier;
    private final MemoryCache encodedMemoryCache;
    private final AtomicLong idCounter = new AtomicLong();
    private final Supplier isLazyDataSource;
    private final Supplier isPrefetchEnabledSupplier;
    private final ProducerSequenceFactory producerSequenceFactory;
    private final RequestListener requestListener;
    private final RequestListener2 requestListener2;
    private final Supplier suppressBitmapPrefetchingSupplier;
    private final ThreadHandoffProducerQueue threadHandoffProducerQueue;

    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(9:0|1|2|3|4|5|6|7|9) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        static {
            /*
                com.facebook.imagepipeline.request.ImageRequest$CacheChoice[] r0 = com.facebook.imagepipeline.request.ImageRequest.CacheChoice.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.facebook.imagepipeline.request.ImageRequest$CacheChoice r1 = com.facebook.imagepipeline.request.ImageRequest.CacheChoice.DEFAULT     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                com.facebook.imagepipeline.request.ImageRequest$CacheChoice r1 = com.facebook.imagepipeline.request.ImageRequest.CacheChoice.SMALL     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                com.facebook.imagepipeline.request.ImageRequest$CacheChoice r1 = com.facebook.imagepipeline.request.ImageRequest.CacheChoice.DYNAMIC     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.core.ImagePipeline.WhenMappings.<clinit>():void");
        }
    }

    /* access modifiers changed from: private */
    public static final boolean clearMemoryCaches$lambda$3(CacheKey cacheKey) {
        Intrinsics.checkNotNullParameter(cacheKey, "it");
        return true;
    }

    private final DataSource submitFetchRequest(Producer producer, ImageRequest imageRequest, ImageRequest.RequestLevel requestLevel, Object obj, RequestListener requestListener3, String str, Map map) {
        DataSource dataSource;
        boolean z;
        boolean z2;
        Producer producer2 = producer;
        ImageRequest imageRequest2 = imageRequest;
        ImageRequest.RequestLevel requestLevel2 = requestLevel;
        RequestListener requestListener4 = requestListener3;
        Map map2 = map;
        if (!FrescoSystrace.isTracing()) {
            InternalRequestListener internalRequestListener = new InternalRequestListener(getRequestListenerForRequest(imageRequest2, requestListener4), this.requestListener2);
            try {
                ImageRequest.RequestLevel max = ImageRequest.RequestLevel.getMax(imageRequest.getLowestPermittedRequestLevel(), requestLevel2);
                Intrinsics.checkNotNullExpressionValue(max, "getMax(...)");
                String generateUniqueFutureId = generateUniqueFutureId();
                if (!imageRequest.getProgressiveRenderingEnabled()) {
                    if (UriUtil.isNetworkUri(imageRequest.getSourceUri())) {
                        z2 = false;
                        SettableProducerContext settableProducerContext = new SettableProducerContext(imageRequest, generateUniqueFutureId, str, internalRequestListener, obj, max, false, z2, imageRequest.getPriority(), this.config);
                        settableProducerContext.putExtras(map2);
                        return CloseableProducerToDataSourceAdapter.create(producer2, settableProducerContext, internalRequestListener);
                    }
                }
                z2 = true;
                SettableProducerContext settableProducerContext2 = new SettableProducerContext(imageRequest, generateUniqueFutureId, str, internalRequestListener, obj, max, false, z2, imageRequest.getPriority(), this.config);
                settableProducerContext2.putExtras(map2);
                return CloseableProducerToDataSourceAdapter.create(producer2, settableProducerContext2, internalRequestListener);
            } catch (Exception e) {
                return DataSources.immediateFailedDataSource(e);
            }
        } else {
            FrescoSystrace.beginSection("ImagePipeline#submitFetchRequest");
            try {
                InternalRequestListener internalRequestListener2 = new InternalRequestListener(getRequestListenerForRequest(imageRequest2, requestListener4), this.requestListener2);
                ImageRequest.RequestLevel max2 = ImageRequest.RequestLevel.getMax(imageRequest.getLowestPermittedRequestLevel(), requestLevel2);
                Intrinsics.checkNotNullExpressionValue(max2, "getMax(...)");
                String generateUniqueFutureId2 = generateUniqueFutureId();
                if (!imageRequest.getProgressiveRenderingEnabled()) {
                    if (UriUtil.isNetworkUri(imageRequest.getSourceUri())) {
                        z = false;
                        SettableProducerContext settableProducerContext3 = new SettableProducerContext(imageRequest, generateUniqueFutureId2, str, internalRequestListener2, obj, max2, false, z, imageRequest.getPriority(), this.config);
                        settableProducerContext3.putExtras(map2);
                        dataSource = CloseableProducerToDataSourceAdapter.create(producer2, settableProducerContext3, internalRequestListener2);
                        FrescoSystrace.endSection();
                        return dataSource;
                    }
                }
                z = true;
                SettableProducerContext settableProducerContext32 = new SettableProducerContext(imageRequest, generateUniqueFutureId2, str, internalRequestListener2, obj, max2, false, z, imageRequest.getPriority(), this.config);
                settableProducerContext32.putExtras(map2);
                dataSource = CloseableProducerToDataSourceAdapter.create(producer2, settableProducerContext32, internalRequestListener2);
            } catch (Exception e2) {
                dataSource = DataSources.immediateFailedDataSource(e2);
            } catch (Throwable th) {
                FrescoSystrace.endSection();
                throw th;
            }
            FrescoSystrace.endSection();
            return dataSource;
        }
    }

    public ImagePipeline(ProducerSequenceFactory producerSequenceFactory2, Set set, Set set2, Supplier supplier, MemoryCache memoryCache, MemoryCache memoryCache2, Supplier supplier2, CacheKeyFactory cacheKeyFactory2, ThreadHandoffProducerQueue threadHandoffProducerQueue2, Supplier supplier3, Supplier supplier4, CallerContextVerifier callerContextVerifier, ImagePipelineConfigInterface imagePipelineConfigInterface) {
        Intrinsics.checkNotNullParameter(producerSequenceFactory2, "producerSequenceFactory");
        Intrinsics.checkNotNullParameter(set, "requestListeners");
        Intrinsics.checkNotNullParameter(set2, "requestListener2s");
        Intrinsics.checkNotNullParameter(supplier, "isPrefetchEnabledSupplier");
        Intrinsics.checkNotNullParameter(memoryCache, "bitmapMemoryCache");
        Intrinsics.checkNotNullParameter(memoryCache2, "encodedMemoryCache");
        Intrinsics.checkNotNullParameter(supplier2, "diskCachesStoreSupplier");
        Intrinsics.checkNotNullParameter(cacheKeyFactory2, "cacheKeyFactory");
        Intrinsics.checkNotNullParameter(threadHandoffProducerQueue2, "threadHandoffProducerQueue");
        Intrinsics.checkNotNullParameter(supplier3, "suppressBitmapPrefetchingSupplier");
        Intrinsics.checkNotNullParameter(supplier4, "lazyDataSource");
        Intrinsics.checkNotNullParameter(imagePipelineConfigInterface, "config");
        this.producerSequenceFactory = producerSequenceFactory2;
        this.isPrefetchEnabledSupplier = supplier;
        this.diskCachesStoreSupplier = supplier2;
        this.requestListener = new ForwardingRequestListener(set);
        this.requestListener2 = new ForwardingRequestListener2(set2);
        this.bitmapMemoryCache = memoryCache;
        this.encodedMemoryCache = memoryCache2;
        this.cacheKeyFactory = cacheKeyFactory2;
        this.threadHandoffProducerQueue = threadHandoffProducerQueue2;
        this.suppressBitmapPrefetchingSupplier = supplier3;
        this.isLazyDataSource = supplier4;
        this.config = imagePipelineConfigInterface;
    }

    public final MemoryCache getBitmapMemoryCache() {
        return this.bitmapMemoryCache;
    }

    public final CacheKeyFactory getCacheKeyFactory() {
        return this.cacheKeyFactory;
    }

    public final String generateUniqueFutureId() {
        return String.valueOf(this.idCounter.getAndIncrement());
    }

    public final DataSource fetchImageFromBitmapCache(ImageRequest imageRequest, Object obj) {
        Intrinsics.checkNotNullParameter(imageRequest, "imageRequest");
        return fetchDecodedImage(imageRequest, obj, ImageRequest.RequestLevel.BITMAP_MEMORY_CACHE);
    }

    public static /* synthetic */ DataSource fetchDecodedImage$default(ImagePipeline imagePipeline, ImageRequest imageRequest, Object obj, ImageRequest.RequestLevel requestLevel, RequestListener requestListener3, String str, int i, Object obj2) {
        return imagePipeline.fetchDecodedImage(imageRequest, obj, (i & 4) != 0 ? null : requestLevel, (i & 8) != 0 ? null : requestListener3, (i & 16) != 0 ? null : str);
    }

    public final DataSource fetchDecodedImage(ImageRequest imageRequest, Object obj, ImageRequest.RequestLevel requestLevel, RequestListener requestListener3, String str) {
        if (imageRequest == null) {
            DataSource immediateFailedDataSource = DataSources.immediateFailedDataSource(new NullPointerException());
            Intrinsics.checkNotNullExpressionValue(immediateFailedDataSource, "immediateFailedDataSource(...)");
            return immediateFailedDataSource;
        }
        try {
            Producer decodedImageProducerSequence = this.producerSequenceFactory.getDecodedImageProducerSequence(imageRequest);
            if (requestLevel == null) {
                requestLevel = ImageRequest.RequestLevel.FULL_FETCH;
            }
            return submitFetchRequest(decodedImageProducerSequence, imageRequest, requestLevel, obj, requestListener3, str);
        } catch (Exception e) {
            return DataSources.immediateFailedDataSource(e);
        }
    }

    public final DataSource fetchDecodedImage(ImageRequest imageRequest, Object obj) {
        return fetchDecodedImage$default(this, imageRequest, obj, (ImageRequest.RequestLevel) null, (RequestListener) null, (String) null, 24, (Object) null);
    }

    public final DataSource fetchDecodedImage(ImageRequest imageRequest, Object obj, ImageRequest.RequestLevel requestLevel) {
        Intrinsics.checkNotNullParameter(requestLevel, "lowestPermittedRequestLevelOnSubmit");
        return fetchDecodedImage$default(this, imageRequest, obj, requestLevel, (RequestListener) null, (String) null, 16, (Object) null);
    }

    public final DataSource prefetchToDiskCache(ImageRequest imageRequest, Object obj) {
        return prefetchToDiskCache(imageRequest, obj, Priority.MEDIUM, (RequestListener) null);
    }

    public final DataSource prefetchToDiskCache(ImageRequest imageRequest, Object obj, Priority priority, RequestListener requestListener3) {
        Intrinsics.checkNotNullParameter(priority, "priority");
        if (!((Boolean) this.isPrefetchEnabledSupplier.get()).booleanValue()) {
            DataSource immediateFailedDataSource = DataSources.immediateFailedDataSource(PREFETCH_EXCEPTION);
            Intrinsics.checkNotNullExpressionValue(immediateFailedDataSource, "immediateFailedDataSource(...)");
            return immediateFailedDataSource;
        } else if (imageRequest == null) {
            DataSource immediateFailedDataSource2 = DataSources.immediateFailedDataSource(new NullPointerException("imageRequest is null"));
            Intrinsics.checkNotNull(immediateFailedDataSource2);
            return immediateFailedDataSource2;
        } else {
            try {
                return submitPrefetchRequest(this.producerSequenceFactory.getEncodedImagePrefetchProducerSequence(imageRequest), imageRequest, ImageRequest.RequestLevel.FULL_FETCH, obj, priority, requestListener3);
            } catch (Exception e) {
                return DataSources.immediateFailedDataSource(e);
            }
        }
    }

    public final void evictFromMemoryCache(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        Predicate predicateForUri = predicateForUri(uri);
        this.bitmapMemoryCache.removeAll(predicateForUri);
        this.encodedMemoryCache.removeAll(predicateForUri);
    }

    public final void evictFromDiskCache(Uri uri) {
        ImageRequest fromUri = ImageRequest.fromUri(uri);
        if (fromUri != null) {
            evictFromDiskCache(fromUri);
            return;
        }
        throw new IllegalStateException("Required value was null.");
    }

    public final void evictFromDiskCache(ImageRequest imageRequest) {
        if (imageRequest != null) {
            CacheKey encodedCacheKey = this.cacheKeyFactory.getEncodedCacheKey(imageRequest, (Object) null);
            Object obj = this.diskCachesStoreSupplier.get();
            Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
            DiskCachesStore diskCachesStore = (DiskCachesStore) obj;
            BufferedDiskCache mainBufferedDiskCache = diskCachesStore.getMainBufferedDiskCache();
            Intrinsics.checkNotNull(encodedCacheKey);
            mainBufferedDiskCache.remove(encodedCacheKey);
            diskCachesStore.getSmallImageBufferedDiskCache().remove(encodedCacheKey);
            for (Map.Entry value : diskCachesStore.getDynamicBufferedDiskCaches().entrySet()) {
                ((BufferedDiskCache) value.getValue()).remove(encodedCacheKey);
            }
        }
    }

    public final void evictFromCache(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        evictFromMemoryCache(uri);
        evictFromDiskCache(uri);
    }

    public final void clearMemoryCaches() {
        ImagePipeline$$ExternalSyntheticLambda1 imagePipeline$$ExternalSyntheticLambda1 = new ImagePipeline$$ExternalSyntheticLambda1();
        this.bitmapMemoryCache.removeAll(imagePipeline$$ExternalSyntheticLambda1);
        this.encodedMemoryCache.removeAll(imagePipeline$$ExternalSyntheticLambda1);
    }

    public final void clearDiskCaches() {
        Object obj = this.diskCachesStoreSupplier.get();
        Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
        DiskCachesStore diskCachesStore = (DiskCachesStore) obj;
        diskCachesStore.getMainBufferedDiskCache().clearAll();
        diskCachesStore.getSmallImageBufferedDiskCache().clearAll();
        for (Map.Entry value : diskCachesStore.getDynamicBufferedDiskCaches().entrySet()) {
            ((BufferedDiskCache) value.getValue()).clearAll();
        }
    }

    public final void clearCaches() {
        clearMemoryCaches();
        clearDiskCaches();
    }

    public final boolean isInBitmapMemoryCache(Uri uri) {
        if (uri == null) {
            return false;
        }
        return this.bitmapMemoryCache.contains(predicateForUri(uri));
    }

    public final boolean isInBitmapMemoryCache(ImageRequest imageRequest) {
        if (imageRequest == null) {
            return false;
        }
        CacheKey bitmapCacheKey = this.cacheKeyFactory.getBitmapCacheKey(imageRequest, (Object) null);
        MemoryCache memoryCache = this.bitmapMemoryCache;
        Intrinsics.checkNotNull(bitmapCacheKey);
        CloseableReference closeableReference = memoryCache.get(bitmapCacheKey);
        try {
            return CloseableReference.isValid(closeableReference);
        } finally {
            CloseableReference.closeSafely(closeableReference);
        }
    }

    public final boolean isInDiskCacheSync(Uri uri) {
        return isInDiskCacheSync(uri, ImageRequest.CacheChoice.SMALL) || isInDiskCacheSync(uri, ImageRequest.CacheChoice.DEFAULT) || isInDiskCacheSync(uri, ImageRequest.CacheChoice.DYNAMIC);
    }

    public final boolean isInDiskCacheSync(Uri uri, ImageRequest.CacheChoice cacheChoice) {
        ImageRequest build = ImageRequestBuilder.newBuilderWithSource(uri).setCacheChoice(cacheChoice).build();
        Intrinsics.checkNotNull(build);
        return isInDiskCacheSync(build);
    }

    private final boolean isInDynamicDiskCachesSync(ImageRequest imageRequest) {
        Object obj = this.diskCachesStoreSupplier.get();
        Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
        DiskCachesStore diskCachesStore = (DiskCachesStore) obj;
        CacheKey encodedCacheKey = this.cacheKeyFactory.getEncodedCacheKey(imageRequest, (Object) null);
        String diskCacheId = imageRequest.getDiskCacheId();
        if (diskCacheId != null) {
            BufferedDiskCache bufferedDiskCache = (BufferedDiskCache) diskCachesStore.getDynamicBufferedDiskCaches().get(diskCacheId);
            if (bufferedDiskCache == null) {
                return false;
            }
            Intrinsics.checkNotNull(encodedCacheKey);
            return bufferedDiskCache.diskCheckSync(encodedCacheKey);
        }
        for (Map.Entry value : diskCachesStore.getDynamicBufferedDiskCaches().entrySet()) {
            Intrinsics.checkNotNull(encodedCacheKey);
            if (((BufferedDiskCache) value.getValue()).diskCheckSync(encodedCacheKey)) {
                return true;
            }
        }
        return false;
    }

    public final boolean isInDiskCacheSync(ImageRequest imageRequest) {
        boolean z;
        Intrinsics.checkNotNullParameter(imageRequest, "imageRequest");
        Object obj = this.diskCachesStoreSupplier.get();
        Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
        DiskCachesStore diskCachesStore = (DiskCachesStore) obj;
        CacheKey encodedCacheKey = this.cacheKeyFactory.getEncodedCacheKey(imageRequest, (Object) null);
        ImageRequest.CacheChoice cacheChoice = imageRequest.getCacheChoice();
        Intrinsics.checkNotNullExpressionValue(cacheChoice, "getCacheChoice(...)");
        StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            int i = WhenMappings.$EnumSwitchMapping$0[cacheChoice.ordinal()];
            if (i == 1) {
                BufferedDiskCache mainBufferedDiskCache = diskCachesStore.getMainBufferedDiskCache();
                Intrinsics.checkNotNull(encodedCacheKey);
                z = mainBufferedDiskCache.diskCheckSync(encodedCacheKey);
            } else if (i == 2) {
                BufferedDiskCache smallImageBufferedDiskCache = diskCachesStore.getSmallImageBufferedDiskCache();
                Intrinsics.checkNotNull(encodedCacheKey);
                z = smallImageBufferedDiskCache.diskCheckSync(encodedCacheKey);
            } else if (i == 3) {
                z = isInDynamicDiskCachesSync(imageRequest);
            } else {
                throw new NoWhenBranchMatchedException();
            }
            return z;
        } finally {
            StrictMode.setThreadPolicy(allowThreadDiskReads);
        }
    }

    private final DataSource submitFetchRequest(Producer producer, ImageRequest imageRequest, ImageRequest.RequestLevel requestLevel, Object obj, RequestListener requestListener3, String str) {
        return submitFetchRequest(producer, imageRequest, requestLevel, obj, requestListener3, str, (Map) null);
    }

    private final DataSource submitPrefetchRequest(Producer producer, ImageRequest imageRequest, ImageRequest.RequestLevel requestLevel, Object obj, Priority priority, RequestListener requestListener3) {
        ImageRequest imageRequest2 = imageRequest;
        InternalRequestListener internalRequestListener = new InternalRequestListener(getRequestListenerForRequest(imageRequest, requestListener3), this.requestListener2);
        Uri sourceUri = imageRequest.getSourceUri();
        Intrinsics.checkNotNullExpressionValue(sourceUri, "getSourceUri(...)");
        Uri modifyPrefetchUri = UriModifier.f0INSTANCE.modifyPrefetchUri(sourceUri, obj);
        if (modifyPrefetchUri == null) {
            DataSource immediateFailedDataSource = DataSources.immediateFailedDataSource(MODIFIED_URL_IS_NULL);
            Intrinsics.checkNotNullExpressionValue(immediateFailedDataSource, "immediateFailedDataSource(...)");
            return immediateFailedDataSource;
        }
        if (!Intrinsics.areEqual((Object) sourceUri, (Object) modifyPrefetchUri)) {
            imageRequest2 = ImageRequestBuilder.fromRequest(imageRequest).setSource(modifyPrefetchUri).build();
        }
        ImageRequest imageRequest3 = imageRequest2;
        try {
            ImageRequest.RequestLevel max = ImageRequest.RequestLevel.getMax(imageRequest3.getLowestPermittedRequestLevel(), requestLevel);
            Intrinsics.checkNotNullExpressionValue(max, "getMax(...)");
            String generateUniqueFutureId = generateUniqueFutureId();
            ImagePipelineExperiments experiments = this.config.getExperiments();
            SettableProducerContext settableProducerContext = new SettableProducerContext(imageRequest3, generateUniqueFutureId, internalRequestListener, obj, max, true, experiments != null && experiments.getAllowProgressiveOnPrefetch() && imageRequest3.getProgressiveRenderingEnabled(), priority, this.config);
            Producer producer2 = producer;
            return ProducerToDataSourceAdapter.Companion.create(producer, settableProducerContext, internalRequestListener);
        } catch (Exception e) {
            return DataSources.immediateFailedDataSource(e);
        }
    }

    public final RequestListener getRequestListenerForRequest(ImageRequest imageRequest, RequestListener requestListener3) {
        if (imageRequest == null) {
            throw new IllegalStateException("Required value was null.");
        } else if (requestListener3 == null) {
            if (imageRequest.getRequestListener() == null) {
                return this.requestListener;
            }
            return new ForwardingRequestListener(this.requestListener, imageRequest.getRequestListener());
        } else if (imageRequest.getRequestListener() == null) {
            return new ForwardingRequestListener(this.requestListener, requestListener3);
        } else {
            return new ForwardingRequestListener(this.requestListener, requestListener3, imageRequest.getRequestListener());
        }
    }

    private final Predicate predicateForUri(Uri uri) {
        return new ImagePipeline$$ExternalSyntheticLambda0(uri);
    }

    /* access modifiers changed from: private */
    public static final boolean predicateForUri$lambda$16(Uri uri, CacheKey cacheKey) {
        Intrinsics.checkNotNullParameter(uri, "$uri");
        Intrinsics.checkNotNullParameter(cacheKey, "key");
        return cacheKey.containsUri(uri);
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
