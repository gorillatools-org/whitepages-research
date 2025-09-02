package com.facebook.imagepipeline.producers;

import android.os.SystemClock;
import com.facebook.common.memory.ByteArrayPool;
import com.facebook.common.memory.PooledByteBufferFactory;
import com.facebook.common.memory.PooledByteBufferOutputStream;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.common.BytesRange;
import com.facebook.imagepipeline.decoder.ProgressiveJpegConfig;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.producers.NetworkFetcher;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import java.io.InputStream;
import java.util.Map;
import okhttp3.internal.http2.Http2;

public class NetworkFetchProducer implements Producer {
    private final ByteArrayPool mByteArrayPool;
    private final NetworkFetcher mNetworkFetcher;
    protected final PooledByteBufferFactory mPooledByteBufferFactory;

    public NetworkFetchProducer(PooledByteBufferFactory pooledByteBufferFactory, ByteArrayPool byteArrayPool, NetworkFetcher networkFetcher) {
        this.mPooledByteBufferFactory = pooledByteBufferFactory;
        this.mByteArrayPool = byteArrayPool;
        this.mNetworkFetcher = networkFetcher;
    }

    public void produceResults(Consumer consumer, ProducerContext producerContext) {
        producerContext.getProducerListener().onProducerStart(producerContext, "NetworkFetchProducer");
        final FetchState createFetchState = this.mNetworkFetcher.createFetchState(consumer, producerContext);
        this.mNetworkFetcher.fetch(createFetchState, new NetworkFetcher.Callback() {
            public void onResponse(InputStream inputStream, int i) {
                if (FrescoSystrace.isTracing()) {
                    FrescoSystrace.beginSection("NetworkFetcher->onResponse");
                }
                NetworkFetchProducer.this.onResponse(createFetchState, inputStream, i);
                if (FrescoSystrace.isTracing()) {
                    FrescoSystrace.endSection();
                }
            }

            public void onFailure(Throwable th) {
                NetworkFetchProducer.this.onFailure(createFetchState, th);
            }

            public void onCancellation() {
                NetworkFetchProducer.this.onCancellation(createFetchState);
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onResponse(FetchState fetchState, InputStream inputStream, int i) {
        PooledByteBufferOutputStream pooledByteBufferOutputStream;
        if (i > 0) {
            pooledByteBufferOutputStream = this.mPooledByteBufferFactory.newOutputStream(i);
        } else {
            pooledByteBufferOutputStream = this.mPooledByteBufferFactory.newOutputStream();
        }
        byte[] bArr = (byte[]) this.mByteArrayPool.get(Http2.INITIAL_MAX_FRAME_SIZE);
        while (true) {
            try {
                int read = inputStream.read(bArr);
                if (read < 0) {
                    this.mNetworkFetcher.onFetchCompletion(fetchState, pooledByteBufferOutputStream.size());
                    handleFinalResult(pooledByteBufferOutputStream, fetchState);
                    this.mByteArrayPool.release(bArr);
                    pooledByteBufferOutputStream.close();
                    return;
                } else if (read > 0) {
                    pooledByteBufferOutputStream.write(bArr, 0, read);
                    maybeHandleIntermediateResult(pooledByteBufferOutputStream, fetchState);
                    fetchState.getConsumer().onProgressUpdate(calculateProgress(pooledByteBufferOutputStream.size(), i));
                }
            } catch (Throwable th) {
                this.mByteArrayPool.release(bArr);
                pooledByteBufferOutputStream.close();
                throw th;
            }
        }
    }

    protected static float calculateProgress(int i, int i2) {
        return i2 > 0 ? ((float) i) / ((float) i2) : 1.0f - ((float) Math.exp(((double) (-i)) / 50000.0d));
    }

    /* access modifiers changed from: protected */
    public void maybeHandleIntermediateResult(PooledByteBufferOutputStream pooledByteBufferOutputStream, FetchState fetchState) {
        if (shouldPropagateIntermediateResults(fetchState, fetchState.getContext())) {
            long systemUptime = getSystemUptime();
            if (systemUptime - fetchState.getLastIntermediateResultTimeMs() >= 100) {
                fetchState.setLastIntermediateResultTimeMs(systemUptime);
                fetchState.getListener().onProducerEvent(fetchState.getContext(), "NetworkFetchProducer", "intermediate_result");
                notifyConsumer(pooledByteBufferOutputStream, fetchState.getOnNewResultStatusFlags(), fetchState.getResponseBytesRange(), fetchState.getConsumer(), fetchState.getContext());
            }
        }
    }

    /* access modifiers changed from: protected */
    public void handleFinalResult(PooledByteBufferOutputStream pooledByteBufferOutputStream, FetchState fetchState) {
        Map extraMap = getExtraMap(fetchState, pooledByteBufferOutputStream.size());
        ProducerListener2 listener = fetchState.getListener();
        listener.onProducerFinishWithSuccess(fetchState.getContext(), "NetworkFetchProducer", extraMap);
        listener.onUltimateProducerReached(fetchState.getContext(), "NetworkFetchProducer", true);
        fetchState.getContext().putOriginExtra("network");
        notifyConsumer(pooledByteBufferOutputStream, fetchState.getOnNewResultStatusFlags() | 1, fetchState.getResponseBytesRange(), fetchState.getConsumer(), fetchState.getContext());
    }

    protected static void notifyConsumer(PooledByteBufferOutputStream pooledByteBufferOutputStream, int i, BytesRange bytesRange, Consumer consumer, ProducerContext producerContext) {
        CloseableReference of = CloseableReference.of(pooledByteBufferOutputStream.toByteBuffer());
        EncodedImage encodedImage = null;
        try {
            EncodedImage encodedImage2 = new EncodedImage(of);
            try {
                encodedImage2.setBytesRange(bytesRange);
                encodedImage2.parseMetaData();
                consumer.onNewResult(encodedImage2, i);
                EncodedImage.closeSafely(encodedImage2);
                CloseableReference.closeSafely(of);
            } catch (Throwable th) {
                th = th;
                encodedImage = encodedImage2;
                EncodedImage.closeSafely(encodedImage);
                CloseableReference.closeSafely(of);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            EncodedImage.closeSafely(encodedImage);
            CloseableReference.closeSafely(of);
            throw th;
        }
    }

    /* access modifiers changed from: private */
    public void onFailure(FetchState fetchState, Throwable th) {
        fetchState.getListener().onProducerFinishWithFailure(fetchState.getContext(), "NetworkFetchProducer", th, (Map) null);
        fetchState.getListener().onUltimateProducerReached(fetchState.getContext(), "NetworkFetchProducer", false);
        fetchState.getContext().putOriginExtra("network");
        fetchState.getConsumer().onFailure(th);
    }

    /* access modifiers changed from: private */
    public void onCancellation(FetchState fetchState) {
        fetchState.getListener().onProducerFinishWithCancellation(fetchState.getContext(), "NetworkFetchProducer", (Map) null);
        fetchState.getConsumer().onCancellation();
    }

    private boolean shouldPropagateIntermediateResults(FetchState fetchState, ProducerContext producerContext) {
        ProgressiveJpegConfig progressiveJpegConfig = producerContext.getImagePipelineConfig().getProgressiveJpegConfig();
        if (progressiveJpegConfig == null || !progressiveJpegConfig.decodeProgressively() || !fetchState.getContext().isIntermediateResultExpected()) {
            return false;
        }
        return this.mNetworkFetcher.shouldPropagate(fetchState);
    }

    private Map getExtraMap(FetchState fetchState, int i) {
        if (!fetchState.getListener().requiresExtraMap(fetchState.getContext(), "NetworkFetchProducer")) {
            return null;
        }
        return this.mNetworkFetcher.getExtraMap(fetchState, i);
    }

    /* access modifiers changed from: protected */
    public long getSystemUptime() {
        return SystemClock.uptimeMillis();
    }
}
