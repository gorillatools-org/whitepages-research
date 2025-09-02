package com.facebook.imagepipeline.producers;

import kotlin.jvm.internal.Intrinsics;

public abstract class DelegatingConsumer extends BaseConsumer {
    private final Consumer consumer;

    public DelegatingConsumer(Consumer consumer2) {
        Intrinsics.checkNotNullParameter(consumer2, "consumer");
        this.consumer = consumer2;
    }

    public final Consumer getConsumer() {
        return this.consumer;
    }

    /* access modifiers changed from: protected */
    public void onFailureImpl(Throwable th) {
        Intrinsics.checkNotNullParameter(th, "t");
        this.consumer.onFailure(th);
    }

    /* access modifiers changed from: protected */
    public void onCancellationImpl() {
        this.consumer.onCancellation();
    }

    /* access modifiers changed from: protected */
    public void onProgressUpdateImpl(float f) {
        this.consumer.onProgressUpdate(f);
    }
}
