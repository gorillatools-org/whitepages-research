package com.facebook.imagepipeline.producers;

import com.facebook.common.internal.Closeables;
import com.facebook.common.memory.PooledByteBufferFactory;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.request.ImageRequest;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.firebase.remoteconfig.RemoteConfigComponent;
import java.io.InputStream;
import java.util.concurrent.Executor;

public abstract class LocalFetchProducer implements Producer {
    private final Executor mExecutor;
    private final PooledByteBufferFactory mPooledByteBufferFactory;

    /* access modifiers changed from: protected */
    public abstract EncodedImage getEncodedImage(ImageRequest imageRequest);

    /* access modifiers changed from: protected */
    public abstract String getProducerName();

    protected LocalFetchProducer(Executor executor, PooledByteBufferFactory pooledByteBufferFactory) {
        this.mExecutor = executor;
        this.mPooledByteBufferFactory = pooledByteBufferFactory;
    }

    public void produceResults(Consumer consumer, ProducerContext producerContext) {
        final ProducerListener2 producerListener = producerContext.getProducerListener();
        final ImageRequest imageRequest = producerContext.getImageRequest();
        producerContext.putOriginExtra(ImagesContract.LOCAL, RemoteConfigComponent.FETCH_FILE_NAME);
        final ProducerContext producerContext2 = producerContext;
        final AnonymousClass1 r0 = new StatefulProducerRunnable(consumer, producerListener, producerContext, getProducerName()) {
            /* access modifiers changed from: protected */
            public EncodedImage getResult() {
                EncodedImage encodedImage = LocalFetchProducer.this.getEncodedImage(imageRequest);
                if (encodedImage == null) {
                    producerListener.onUltimateProducerReached(producerContext2, LocalFetchProducer.this.getProducerName(), false);
                    producerContext2.putOriginExtra(ImagesContract.LOCAL, RemoteConfigComponent.FETCH_FILE_NAME);
                    return null;
                }
                encodedImage.parseMetaData();
                producerListener.onUltimateProducerReached(producerContext2, LocalFetchProducer.this.getProducerName(), true);
                producerContext2.putOriginExtra(ImagesContract.LOCAL, RemoteConfigComponent.FETCH_FILE_NAME);
                producerContext2.putExtra("image_color_space", encodedImage.getColorSpace());
                return encodedImage;
            }

            /* access modifiers changed from: protected */
            public void disposeResult(EncodedImage encodedImage) {
                EncodedImage.closeSafely(encodedImage);
            }
        };
        producerContext.addCallbacks(new BaseProducerContextCallbacks() {
            public void onCancellationRequested() {
                r0.cancel();
            }
        });
        this.mExecutor.execute(r0);
    }

    /* access modifiers changed from: protected */
    public EncodedImage getByteBufferBackedEncodedImage(InputStream inputStream, int i) {
        CloseableReference of;
        if (i <= 0) {
            try {
                of = CloseableReference.of(this.mPooledByteBufferFactory.newByteBuffer(inputStream));
            } catch (Throwable th) {
                Closeables.closeQuietly(inputStream);
                CloseableReference.closeSafely((CloseableReference) null);
                throw th;
            }
        } else {
            of = CloseableReference.of(this.mPooledByteBufferFactory.newByteBuffer(inputStream, i));
        }
        CloseableReference closeableReference = of;
        EncodedImage encodedImage = new EncodedImage(closeableReference);
        Closeables.closeQuietly(inputStream);
        CloseableReference.closeSafely(closeableReference);
        return encodedImage;
    }

    /* access modifiers changed from: protected */
    public EncodedImage getEncodedImage(InputStream inputStream, int i) {
        return getByteBufferBackedEncodedImage(inputStream, i);
    }
}
