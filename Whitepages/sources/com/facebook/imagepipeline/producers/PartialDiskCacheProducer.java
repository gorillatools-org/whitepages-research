package com.facebook.imagepipeline.producers;

import android.net.Uri;
import bolts.Continuation;
import bolts.Task;
import com.facebook.cache.common.CacheKey;
import com.facebook.common.internal.ImmutableMap;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Supplier;
import com.facebook.common.logging.FLog;
import com.facebook.common.memory.ByteArrayPool;
import com.facebook.common.memory.PooledByteBufferFactory;
import com.facebook.common.memory.PooledByteBufferOutputStream;
import com.facebook.common.references.CloseableReference;
import com.facebook.imageformat.ImageFormat;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.common.BytesRange;
import com.facebook.imagepipeline.core.DiskCachesStore;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicBoolean;
import okhttp3.internal.http2.Http2;

public class PartialDiskCacheProducer implements Producer {
    private final ByteArrayPool mByteArrayPool;
    private final CacheKeyFactory mCacheKeyFactory;
    private final Supplier mDiskCachesStoreSupplier;
    private final Producer mInputProducer;
    private final PooledByteBufferFactory mPooledByteBufferFactory;

    public PartialDiskCacheProducer(Supplier supplier, CacheKeyFactory cacheKeyFactory, PooledByteBufferFactory pooledByteBufferFactory, ByteArrayPool byteArrayPool, Producer producer) {
        this.mDiskCachesStoreSupplier = supplier;
        this.mCacheKeyFactory = cacheKeyFactory;
        this.mPooledByteBufferFactory = pooledByteBufferFactory;
        this.mByteArrayPool = byteArrayPool;
        this.mInputProducer = producer;
    }

    public void produceResults(Consumer consumer, ProducerContext producerContext) {
        ImageRequest imageRequest = producerContext.getImageRequest();
        boolean isCacheEnabled = producerContext.getImageRequest().isCacheEnabled(16);
        boolean isCacheEnabled2 = producerContext.getImageRequest().isCacheEnabled(32);
        if (isCacheEnabled || isCacheEnabled2) {
            ProducerListener2 producerListener = producerContext.getProducerListener();
            producerListener.onProducerStart(producerContext, "PartialDiskCacheProducer");
            CacheKey encodedCacheKey = this.mCacheKeyFactory.getEncodedCacheKey(imageRequest, createUriForPartialCacheKey(imageRequest), producerContext.getCallerContext());
            if (!isCacheEnabled) {
                producerListener.onProducerFinishWithSuccess(producerContext, "PartialDiskCacheProducer", getExtraMap(producerListener, producerContext, false, 0));
                startInputProducer(consumer, producerContext, encodedCacheKey, (EncodedImage) null);
                return;
            }
            AtomicBoolean atomicBoolean = new AtomicBoolean(false);
            ((DiskCachesStore) this.mDiskCachesStoreSupplier.get()).getMainBufferedDiskCache().get(encodedCacheKey, atomicBoolean).continueWith(onFinishDiskReads(consumer, producerContext, encodedCacheKey));
            subscribeTaskForRequestCancellation(atomicBoolean, producerContext);
            return;
        }
        this.mInputProducer.produceResults(consumer, producerContext);
    }

    private Continuation onFinishDiskReads(Consumer consumer, ProducerContext producerContext, CacheKey cacheKey) {
        final ProducerListener2 producerListener = producerContext.getProducerListener();
        final ProducerContext producerContext2 = producerContext;
        final Consumer consumer2 = consumer;
        final CacheKey cacheKey2 = cacheKey;
        return new Continuation() {
            public Void then(Task task) {
                if (PartialDiskCacheProducer.isTaskCancelled(task)) {
                    producerListener.onProducerFinishWithCancellation(producerContext2, "PartialDiskCacheProducer", (Map) null);
                    consumer2.onCancellation();
                } else if (task.isFaulted()) {
                    producerListener.onProducerFinishWithFailure(producerContext2, "PartialDiskCacheProducer", task.getError(), (Map) null);
                    PartialDiskCacheProducer.this.startInputProducer(consumer2, producerContext2, cacheKey2, (EncodedImage) null);
                } else {
                    EncodedImage encodedImage = (EncodedImage) task.getResult();
                    if (encodedImage != null) {
                        ProducerListener2 producerListener2 = producerListener;
                        ProducerContext producerContext = producerContext2;
                        producerListener2.onProducerFinishWithSuccess(producerContext, "PartialDiskCacheProducer", PartialDiskCacheProducer.getExtraMap(producerListener2, producerContext, true, encodedImage.getSize()));
                        BytesRange max = BytesRange.toMax(encodedImage.getSize() - 1);
                        encodedImage.setBytesRange(max);
                        int size = encodedImage.getSize();
                        ImageRequest imageRequest = producerContext2.getImageRequest();
                        if (max.contains(imageRequest.getBytesRange())) {
                            producerContext2.putOriginExtra("disk", "partial");
                            producerListener.onUltimateProducerReached(producerContext2, "PartialDiskCacheProducer", true);
                            consumer2.onNewResult(encodedImage, 9);
                        } else {
                            consumer2.onNewResult(encodedImage, 8);
                            PartialDiskCacheProducer.this.startInputProducer(consumer2, new SettableProducerContext(ImageRequestBuilder.fromRequest(imageRequest).setBytesRange(BytesRange.from(size - 1)).build(), producerContext2), cacheKey2, encodedImage);
                        }
                    } else {
                        ProducerListener2 producerListener22 = producerListener;
                        ProducerContext producerContext2 = producerContext2;
                        producerListener22.onProducerFinishWithSuccess(producerContext2, "PartialDiskCacheProducer", PartialDiskCacheProducer.getExtraMap(producerListener22, producerContext2, false, 0));
                        PartialDiskCacheProducer.this.startInputProducer(consumer2, producerContext2, cacheKey2, encodedImage);
                    }
                }
                return null;
            }
        };
    }

    /* access modifiers changed from: private */
    public void startInputProducer(Consumer consumer, ProducerContext producerContext, CacheKey cacheKey, EncodedImage encodedImage) {
        this.mInputProducer.produceResults(new PartialDiskCacheConsumer(consumer, this.mDiskCachesStoreSupplier, cacheKey, this.mPooledByteBufferFactory, this.mByteArrayPool, encodedImage, producerContext.getImageRequest().isCacheEnabled(32)), producerContext);
    }

    /* access modifiers changed from: private */
    public static boolean isTaskCancelled(Task task) {
        return task.isCancelled() || (task.isFaulted() && (task.getError() instanceof CancellationException));
    }

    static Map getExtraMap(ProducerListener2 producerListener2, ProducerContext producerContext, boolean z, int i) {
        if (!producerListener2.requiresExtraMap(producerContext, "PartialDiskCacheProducer")) {
            return null;
        }
        if (z) {
            return ImmutableMap.of("cached_value_found", String.valueOf(z), "encodedImageSize", String.valueOf(i));
        }
        return ImmutableMap.of("cached_value_found", String.valueOf(z));
    }

    private void subscribeTaskForRequestCancellation(final AtomicBoolean atomicBoolean, ProducerContext producerContext) {
        producerContext.addCallbacks(new BaseProducerContextCallbacks() {
            public void onCancellationRequested() {
                atomicBoolean.set(true);
            }
        });
    }

    private static Uri createUriForPartialCacheKey(ImageRequest imageRequest) {
        return imageRequest.getSourceUri().buildUpon().appendQueryParameter("fresco_partial", "true").build();
    }

    private static class PartialDiskCacheConsumer extends DelegatingConsumer {
        private final ByteArrayPool mByteArrayPool;
        private final Supplier mDiskCachesStoreSupplier;
        private final boolean mIsDiskCacheEnabledForWrite;
        private final EncodedImage mPartialEncodedImageFromCache;
        private final CacheKey mPartialImageCacheKey;
        private final PooledByteBufferFactory mPooledByteBufferFactory;

        private PartialDiskCacheConsumer(Consumer consumer, Supplier supplier, CacheKey cacheKey, PooledByteBufferFactory pooledByteBufferFactory, ByteArrayPool byteArrayPool, EncodedImage encodedImage, boolean z) {
            super(consumer);
            this.mDiskCachesStoreSupplier = supplier;
            this.mPartialImageCacheKey = cacheKey;
            this.mPooledByteBufferFactory = pooledByteBufferFactory;
            this.mByteArrayPool = byteArrayPool;
            this.mPartialEncodedImageFromCache = encodedImage;
            this.mIsDiskCacheEnabledForWrite = z;
        }

        public void onNewResultImpl(EncodedImage encodedImage, int i) {
            if (!BaseConsumer.isNotLast(i)) {
                if (this.mPartialEncodedImageFromCache != null && encodedImage != null && encodedImage.getBytesRange() != null) {
                    try {
                        sendFinalResultToConsumer(merge(this.mPartialEncodedImageFromCache, encodedImage));
                    } catch (IOException e) {
                        FLog.e("PartialDiskCacheProducer", "Error while merging image data", (Throwable) e);
                        getConsumer().onFailure(e);
                    } catch (Throwable th) {
                        encodedImage.close();
                        this.mPartialEncodedImageFromCache.close();
                        throw th;
                    }
                    encodedImage.close();
                    this.mPartialEncodedImageFromCache.close();
                    ((DiskCachesStore) this.mDiskCachesStoreSupplier.get()).getMainBufferedDiskCache().remove(this.mPartialImageCacheKey);
                } else if (!this.mIsDiskCacheEnabledForWrite || !BaseConsumer.statusHasFlag(i, 8) || !BaseConsumer.isLast(i) || encodedImage == null || encodedImage.getImageFormat() == ImageFormat.UNKNOWN) {
                    getConsumer().onNewResult(encodedImage, i);
                } else {
                    ((DiskCachesStore) this.mDiskCachesStoreSupplier.get()).getMainBufferedDiskCache().put(this.mPartialImageCacheKey, encodedImage);
                    getConsumer().onNewResult(encodedImage, i);
                }
            }
        }

        private PooledByteBufferOutputStream merge(EncodedImage encodedImage, EncodedImage encodedImage2) {
            int i = ((BytesRange) Preconditions.checkNotNull(encodedImage2.getBytesRange())).from;
            PooledByteBufferOutputStream newOutputStream = this.mPooledByteBufferFactory.newOutputStream(encodedImage2.getSize() + i);
            copy(encodedImage.getInputStreamOrThrow(), newOutputStream, i);
            copy(encodedImage2.getInputStreamOrThrow(), newOutputStream, encodedImage2.getSize());
            return newOutputStream;
        }

        private void copy(InputStream inputStream, OutputStream outputStream, int i) {
            byte[] bArr = (byte[]) this.mByteArrayPool.get(Http2.INITIAL_MAX_FRAME_SIZE);
            int i2 = i;
            while (i2 > 0) {
                try {
                    int read = inputStream.read(bArr, 0, Math.min(Http2.INITIAL_MAX_FRAME_SIZE, i2));
                    if (read < 0) {
                        break;
                    } else if (read > 0) {
                        outputStream.write(bArr, 0, read);
                        i2 -= read;
                    }
                } catch (Throwable th) {
                    this.mByteArrayPool.release(bArr);
                    throw th;
                }
            }
            this.mByteArrayPool.release(bArr);
            if (i2 > 0) {
                throw new IOException(String.format((Locale) null, "Failed to read %d bytes - finished %d short", new Object[]{Integer.valueOf(i), Integer.valueOf(i2)}));
            }
        }

        private void sendFinalResultToConsumer(PooledByteBufferOutputStream pooledByteBufferOutputStream) {
            EncodedImage encodedImage;
            Throwable th;
            CloseableReference of = CloseableReference.of(pooledByteBufferOutputStream.toByteBuffer());
            try {
                encodedImage = new EncodedImage(of);
                try {
                    encodedImage.parseMetaData();
                    getConsumer().onNewResult(encodedImage, 1);
                    EncodedImage.closeSafely(encodedImage);
                    CloseableReference.closeSafely(of);
                } catch (Throwable th2) {
                    th = th2;
                    EncodedImage.closeSafely(encodedImage);
                    CloseableReference.closeSafely(of);
                    throw th;
                }
            } catch (Throwable th3) {
                Throwable th4 = th3;
                encodedImage = null;
                th = th4;
                EncodedImage.closeSafely(encodedImage);
                CloseableReference.closeSafely(of);
                throw th;
            }
        }
    }
}
