package com.facebook.imagepipeline.producers;

import android.util.Pair;
import com.facebook.common.internal.Preconditions;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;

public class ThrottlingProducer implements Producer {
    /* access modifiers changed from: private */
    public final Executor mExecutor;
    private final Producer mInputProducer;
    private final int mMaxSimultaneousRequests;
    /* access modifiers changed from: private */
    public int mNumCurrentRequests = 0;
    /* access modifiers changed from: private */
    public final ConcurrentLinkedQueue mPendingRequests = new ConcurrentLinkedQueue();

    public ThrottlingProducer(int i, Executor executor, Producer producer) {
        this.mMaxSimultaneousRequests = i;
        this.mExecutor = (Executor) Preconditions.checkNotNull(executor);
        this.mInputProducer = (Producer) Preconditions.checkNotNull(producer);
    }

    public void produceResults(Consumer consumer, ProducerContext producerContext) {
        boolean z;
        producerContext.getProducerListener().onProducerStart(producerContext, "ThrottlingProducer");
        synchronized (this) {
            try {
                int i = this.mNumCurrentRequests;
                z = true;
                if (i >= this.mMaxSimultaneousRequests) {
                    this.mPendingRequests.add(Pair.create(consumer, producerContext));
                } else {
                    this.mNumCurrentRequests = i + 1;
                    z = false;
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        if (!z) {
            produceResultsInternal(consumer, producerContext);
        }
    }

    /* access modifiers changed from: package-private */
    public void produceResultsInternal(Consumer consumer, ProducerContext producerContext) {
        producerContext.getProducerListener().onProducerFinishWithSuccess(producerContext, "ThrottlingProducer", (Map) null);
        this.mInputProducer.produceResults(new ThrottlerConsumer(consumer), producerContext);
    }

    private class ThrottlerConsumer extends DelegatingConsumer {
        private ThrottlerConsumer(Consumer consumer) {
            super(consumer);
        }

        /* access modifiers changed from: protected */
        public void onNewResultImpl(Object obj, int i) {
            getConsumer().onNewResult(obj, i);
            if (BaseConsumer.isLast(i)) {
                onRequestFinished();
            }
        }

        /* access modifiers changed from: protected */
        public void onFailureImpl(Throwable th) {
            getConsumer().onFailure(th);
            onRequestFinished();
        }

        /* access modifiers changed from: protected */
        public void onCancellationImpl() {
            getConsumer().onCancellation();
            onRequestFinished();
        }

        private void onRequestFinished() {
            final Pair pair;
            synchronized (ThrottlingProducer.this) {
                try {
                    pair = (Pair) ThrottlingProducer.this.mPendingRequests.poll();
                    if (pair == null) {
                        ThrottlingProducer throttlingProducer = ThrottlingProducer.this;
                        throttlingProducer.mNumCurrentRequests = throttlingProducer.mNumCurrentRequests - 1;
                    }
                } catch (Throwable th) {
                    while (true) {
                        throw th;
                    }
                }
            }
            if (pair != null) {
                ThrottlingProducer.this.mExecutor.execute(new Runnable() {
                    public void run() {
                        ThrottlingProducer throttlingProducer = ThrottlingProducer.this;
                        Pair pair = pair;
                        throttlingProducer.produceResultsInternal((Consumer) pair.first, (ProducerContext) pair.second);
                    }
                });
            }
        }
    }
}
