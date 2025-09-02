package com.facebook.imagepipeline.cache;

import bolts.Task;
import com.facebook.binaryresource.BinaryResource;
import com.facebook.cache.common.CacheKey;
import com.facebook.cache.disk.FileCache;
import com.facebook.common.logging.FLog;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.common.memory.PooledByteBufferFactory;
import com.facebook.common.memory.PooledByteStreams;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.instrumentation.FrescoInstrumenter;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class BufferedDiskCache {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final Class TAG = BufferedDiskCache.class;
    private final FileCache fileCache;
    private final ImageCacheStatsTracker imageCacheStatsTracker;
    private final PooledByteBufferFactory pooledByteBufferFactory;
    private final PooledByteStreams pooledByteStreams;
    private final Executor readExecutor;
    private final StagingArea stagingArea;
    private final Executor writeExecutor;

    public BufferedDiskCache(FileCache fileCache2, PooledByteBufferFactory pooledByteBufferFactory2, PooledByteStreams pooledByteStreams2, Executor executor, Executor executor2, ImageCacheStatsTracker imageCacheStatsTracker2) {
        Intrinsics.checkNotNullParameter(fileCache2, "fileCache");
        Intrinsics.checkNotNullParameter(pooledByteBufferFactory2, "pooledByteBufferFactory");
        Intrinsics.checkNotNullParameter(pooledByteStreams2, "pooledByteStreams");
        Intrinsics.checkNotNullParameter(executor, "readExecutor");
        Intrinsics.checkNotNullParameter(executor2, "writeExecutor");
        Intrinsics.checkNotNullParameter(imageCacheStatsTracker2, "imageCacheStatsTracker");
        this.fileCache = fileCache2;
        this.pooledByteBufferFactory = pooledByteBufferFactory2;
        this.pooledByteStreams = pooledByteStreams2;
        this.readExecutor = executor;
        this.writeExecutor = executor2;
        this.imageCacheStatsTracker = imageCacheStatsTracker2;
        StagingArea instance = StagingArea.getInstance();
        Intrinsics.checkNotNullExpressionValue(instance, "getInstance(...)");
        this.stagingArea = instance;
    }

    public final Task get(CacheKey cacheKey, AtomicBoolean atomicBoolean) {
        Task task;
        Task foundPinnedImage;
        Intrinsics.checkNotNullParameter(cacheKey, "key");
        Intrinsics.checkNotNullParameter(atomicBoolean, "isCancelled");
        if (!FrescoSystrace.isTracing()) {
            EncodedImage encodedImage = this.stagingArea.get(cacheKey);
            if (encodedImage == null || (foundPinnedImage = foundPinnedImage(cacheKey, encodedImage)) == null) {
                return getAsync(cacheKey, atomicBoolean);
            }
            return foundPinnedImage;
        }
        FrescoSystrace.beginSection("BufferedDiskCache#get");
        try {
            EncodedImage encodedImage2 = this.stagingArea.get(cacheKey);
            if (encodedImage2 != null) {
                task = foundPinnedImage(cacheKey, encodedImage2);
                if (task == null) {
                }
                FrescoSystrace.endSection();
                return task;
            }
            task = getAsync(cacheKey, atomicBoolean);
            FrescoSystrace.endSection();
            return task;
        } catch (Throwable th) {
            FrescoSystrace.endSection();
            throw th;
        }
    }

    public final void put(CacheKey cacheKey, EncodedImage encodedImage) {
        EncodedImage cloneOrNull;
        Intrinsics.checkNotNullParameter(cacheKey, "key");
        Intrinsics.checkNotNullParameter(encodedImage, "encodedImage");
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.beginSection("BufferedDiskCache#put");
            try {
                if (EncodedImage.isValid(encodedImage)) {
                    this.stagingArea.put(cacheKey, encodedImage);
                    cloneOrNull = EncodedImage.cloneOrNull(encodedImage);
                    this.writeExecutor.execute(new BufferedDiskCache$$ExternalSyntheticLambda0(FrescoInstrumenter.onBeforeSubmitWork("BufferedDiskCache_putAsync"), this, cacheKey, cloneOrNull));
                    Unit unit = Unit.INSTANCE;
                    FrescoSystrace.endSection();
                    return;
                }
                throw new IllegalStateException("Check failed.");
            } catch (Exception e) {
                FLog.w(TAG, (Throwable) e, "Failed to schedule disk-cache write for %s", cacheKey.getUriString());
                this.stagingArea.remove(cacheKey, encodedImage);
                EncodedImage.closeSafely(cloneOrNull);
            } catch (Throwable th) {
                FrescoSystrace.endSection();
                throw th;
            }
        } else if (EncodedImage.isValid(encodedImage)) {
            this.stagingArea.put(cacheKey, encodedImage);
            EncodedImage cloneOrNull2 = EncodedImage.cloneOrNull(encodedImage);
            try {
                this.writeExecutor.execute(new BufferedDiskCache$$ExternalSyntheticLambda0(FrescoInstrumenter.onBeforeSubmitWork("BufferedDiskCache_putAsync"), this, cacheKey, cloneOrNull2));
            } catch (Exception e2) {
                FLog.w(TAG, (Throwable) e2, "Failed to schedule disk-cache write for %s", cacheKey.getUriString());
                this.stagingArea.remove(cacheKey, encodedImage);
                EncodedImage.closeSafely(cloneOrNull2);
            }
        } else {
            throw new IllegalStateException("Check failed.");
        }
    }

    public final boolean containsSync(CacheKey cacheKey) {
        Intrinsics.checkNotNullParameter(cacheKey, "key");
        return this.stagingArea.containsKey(cacheKey) || this.fileCache.hasKeySync(cacheKey);
    }

    public final boolean diskCheckSync(CacheKey cacheKey) {
        Intrinsics.checkNotNullParameter(cacheKey, "key");
        if (containsSync(cacheKey)) {
            return true;
        }
        return checkInStagingAreaAndFileCache(cacheKey);
    }

    public final void addKeyForAsyncProbing(CacheKey cacheKey) {
        Intrinsics.checkNotNullParameter(cacheKey, "key");
        this.fileCache.probe(cacheKey);
    }

    private final boolean checkInStagingAreaAndFileCache(CacheKey cacheKey) {
        EncodedImage encodedImage = this.stagingArea.get(cacheKey);
        if (encodedImage != null) {
            encodedImage.close();
            FLog.v(TAG, "Found image for %s in staging area", (Object) cacheKey.getUriString());
            this.imageCacheStatsTracker.onStagingAreaHit(cacheKey);
            return true;
        }
        FLog.v(TAG, "Did not find image for %s in staging area", (Object) cacheKey.getUriString());
        this.imageCacheStatsTracker.onStagingAreaMiss(cacheKey);
        try {
            return this.fileCache.hasKey(cacheKey);
        } catch (Exception unused) {
            return false;
        }
    }

    private final Task getAsync(CacheKey cacheKey, AtomicBoolean atomicBoolean) {
        try {
            return Task.call(new BufferedDiskCache$$ExternalSyntheticLambda1(FrescoInstrumenter.onBeforeSubmitWork("BufferedDiskCache_getAsync"), atomicBoolean, this, cacheKey), this.readExecutor);
        } catch (Exception e) {
            FLog.w(TAG, (Throwable) e, "Failed to schedule disk-cache read for %s", cacheKey.getUriString());
            return Task.forError(e);
        }
    }

    /* access modifiers changed from: private */
    public static final EncodedImage getAsync$lambda$4(Object obj, AtomicBoolean atomicBoolean, BufferedDiskCache bufferedDiskCache, CacheKey cacheKey) {
        CloseableReference of;
        Intrinsics.checkNotNullParameter(atomicBoolean, "$isCancelled");
        Intrinsics.checkNotNullParameter(bufferedDiskCache, "this$0");
        Intrinsics.checkNotNullParameter(cacheKey, "$key");
        Object onBeginWork = FrescoInstrumenter.onBeginWork(obj, (String) null);
        try {
            if (!atomicBoolean.get()) {
                EncodedImage encodedImage = bufferedDiskCache.stagingArea.get(cacheKey);
                if (encodedImage != null) {
                    FLog.v(TAG, "Found image for %s in staging area", (Object) cacheKey.getUriString());
                    bufferedDiskCache.imageCacheStatsTracker.onStagingAreaHit(cacheKey);
                } else {
                    FLog.v(TAG, "Did not find image for %s in staging area", (Object) cacheKey.getUriString());
                    bufferedDiskCache.imageCacheStatsTracker.onStagingAreaMiss(cacheKey);
                    try {
                        PooledByteBuffer readFromDiskCache = bufferedDiskCache.readFromDiskCache(cacheKey);
                        if (readFromDiskCache == null) {
                            FrescoInstrumenter.onEndWork(onBeginWork);
                            return null;
                        }
                        of = CloseableReference.of(readFromDiskCache);
                        Intrinsics.checkNotNullExpressionValue(of, "of(...)");
                        EncodedImage encodedImage2 = new EncodedImage(of);
                        CloseableReference.closeSafely(of);
                        encodedImage = encodedImage2;
                    } catch (Exception unused) {
                        FrescoInstrumenter.onEndWork(onBeginWork);
                        return null;
                    } catch (Throwable th) {
                        CloseableReference.closeSafely(of);
                        throw th;
                    }
                }
                if (!Thread.interrupted()) {
                    FrescoInstrumenter.onEndWork(onBeginWork);
                    return encodedImage;
                }
                FLog.v(TAG, "Host thread was interrupted, decreasing reference count");
                encodedImage.close();
                throw new InterruptedException();
            }
            throw new CancellationException();
        } catch (Throwable th2) {
            FrescoInstrumenter.onEndWork(onBeginWork);
            throw th2;
        }
    }

    /* access modifiers changed from: private */
    public static final void put$lambda$6$lambda$5(Object obj, BufferedDiskCache bufferedDiskCache, CacheKey cacheKey, EncodedImage encodedImage) {
        Intrinsics.checkNotNullParameter(bufferedDiskCache, "this$0");
        Intrinsics.checkNotNullParameter(cacheKey, "$key");
        Object onBeginWork = FrescoInstrumenter.onBeginWork(obj, (String) null);
        try {
            bufferedDiskCache.writeToDiskCache(cacheKey, encodedImage);
            StagingArea stagingArea2 = bufferedDiskCache.stagingArea;
            Intrinsics.checkNotNull(encodedImage);
            stagingArea2.remove(cacheKey, encodedImage);
            EncodedImage.closeSafely(encodedImage);
            FrescoInstrumenter.onEndWork(onBeginWork);
        } catch (Throwable th) {
            StagingArea stagingArea3 = bufferedDiskCache.stagingArea;
            Intrinsics.checkNotNull(encodedImage);
            stagingArea3.remove(cacheKey, encodedImage);
            EncodedImage.closeSafely(encodedImage);
            FrescoInstrumenter.onEndWork(onBeginWork);
            throw th;
        }
    }

    public final Task remove(CacheKey cacheKey) {
        Intrinsics.checkNotNullParameter(cacheKey, "key");
        this.stagingArea.remove(cacheKey);
        try {
            return Task.call(new BufferedDiskCache$$ExternalSyntheticLambda2(FrescoInstrumenter.onBeforeSubmitWork("BufferedDiskCache_remove"), this, cacheKey), this.writeExecutor);
        } catch (Exception e) {
            FLog.w(TAG, (Throwable) e, "Failed to schedule disk-cache remove for %s", cacheKey.getUriString());
            return Task.forError(e);
        }
    }

    /* access modifiers changed from: private */
    public static final Void remove$lambda$7(Object obj, BufferedDiskCache bufferedDiskCache, CacheKey cacheKey) {
        Intrinsics.checkNotNullParameter(bufferedDiskCache, "this$0");
        Intrinsics.checkNotNullParameter(cacheKey, "$key");
        Object onBeginWork = FrescoInstrumenter.onBeginWork(obj, (String) null);
        try {
            bufferedDiskCache.stagingArea.remove(cacheKey);
            bufferedDiskCache.fileCache.remove(cacheKey);
            FrescoInstrumenter.onEndWork(onBeginWork);
            return null;
        } catch (Throwable th) {
            FrescoInstrumenter.onEndWork(onBeginWork);
            throw th;
        }
    }

    public final Task clearAll() {
        this.stagingArea.clearAll();
        try {
            return Task.call(new BufferedDiskCache$$ExternalSyntheticLambda3(FrescoInstrumenter.onBeforeSubmitWork("BufferedDiskCache_clearAll"), this), this.writeExecutor);
        } catch (Exception e) {
            FLog.w(TAG, (Throwable) e, "Failed to schedule disk-cache clear", new Object[0]);
            return Task.forError(e);
        }
    }

    /* access modifiers changed from: private */
    public static final Void clearAll$lambda$8(Object obj, BufferedDiskCache bufferedDiskCache) {
        Intrinsics.checkNotNullParameter(bufferedDiskCache, "this$0");
        Object onBeginWork = FrescoInstrumenter.onBeginWork(obj, (String) null);
        try {
            bufferedDiskCache.stagingArea.clearAll();
            bufferedDiskCache.fileCache.clearAll();
            FrescoInstrumenter.onEndWork(onBeginWork);
            return null;
        } catch (Throwable th) {
            FrescoInstrumenter.onEndWork(onBeginWork);
            throw th;
        }
    }

    private final Task foundPinnedImage(CacheKey cacheKey, EncodedImage encodedImage) {
        FLog.v(TAG, "Found image for %s in staging area", (Object) cacheKey.getUriString());
        this.imageCacheStatsTracker.onStagingAreaHit(cacheKey);
        Task forResult = Task.forResult(encodedImage);
        Intrinsics.checkNotNullExpressionValue(forResult, "forResult(...)");
        return forResult;
    }

    private final PooledByteBuffer readFromDiskCache(CacheKey cacheKey) {
        InputStream openStream;
        try {
            Class cls = TAG;
            FLog.v(cls, "Disk cache read for %s", (Object) cacheKey.getUriString());
            BinaryResource resource = this.fileCache.getResource(cacheKey);
            if (resource == null) {
                FLog.v(cls, "Disk cache miss for %s", (Object) cacheKey.getUriString());
                this.imageCacheStatsTracker.onDiskCacheMiss(cacheKey);
                return null;
            }
            FLog.v(cls, "Found entry in disk cache for %s", (Object) cacheKey.getUriString());
            this.imageCacheStatsTracker.onDiskCacheHit(cacheKey);
            openStream = resource.openStream();
            PooledByteBuffer newByteBuffer = this.pooledByteBufferFactory.newByteBuffer(openStream, (int) resource.size());
            openStream.close();
            FLog.v(cls, "Successful read from disk cache for %s", (Object) cacheKey.getUriString());
            return newByteBuffer;
        } catch (IOException e) {
            FLog.w(TAG, (Throwable) e, "Exception reading from cache for %s", cacheKey.getUriString());
            this.imageCacheStatsTracker.onDiskCacheGetFail(cacheKey);
            throw e;
        } catch (Throwable th) {
            openStream.close();
            throw th;
        }
    }

    private final void writeToDiskCache(CacheKey cacheKey, EncodedImage encodedImage) {
        Class cls = TAG;
        FLog.v(cls, "About to write to disk-cache for key %s", (Object) cacheKey.getUriString());
        try {
            this.fileCache.insert(cacheKey, new BufferedDiskCache$$ExternalSyntheticLambda4(encodedImage, this));
            this.imageCacheStatsTracker.onDiskCachePut(cacheKey);
            FLog.v(cls, "Successful disk-cache write for key %s", (Object) cacheKey.getUriString());
        } catch (IOException e) {
            FLog.w(TAG, (Throwable) e, "Failed to write to disk-cache for key %s", cacheKey.getUriString());
        }
    }

    /* access modifiers changed from: private */
    public static final void writeToDiskCache$lambda$9(EncodedImage encodedImage, BufferedDiskCache bufferedDiskCache, OutputStream outputStream) {
        Intrinsics.checkNotNullParameter(bufferedDiskCache, "this$0");
        Intrinsics.checkNotNullParameter(outputStream, "os");
        Intrinsics.checkNotNull(encodedImage);
        InputStream inputStream = encodedImage.getInputStream();
        if (inputStream != null) {
            bufferedDiskCache.pooledByteStreams.copy(inputStream, outputStream);
            return;
        }
        throw new IllegalStateException("Required value was null.");
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
