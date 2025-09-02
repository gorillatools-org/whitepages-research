package com.facebook.imagepipeline.datasource;

import com.facebook.imagepipeline.producers.BaseConsumer;
import kotlin.jvm.internal.Intrinsics;

public final class AbstractProducerToDataSourceAdapter$createConsumer$1 extends BaseConsumer {
    final /* synthetic */ AbstractProducerToDataSourceAdapter this$0;

    AbstractProducerToDataSourceAdapter$createConsumer$1(AbstractProducerToDataSourceAdapter abstractProducerToDataSourceAdapter) {
        this.this$0 = abstractProducerToDataSourceAdapter;
    }

    /* access modifiers changed from: protected */
    public void onNewResultImpl(Object obj, int i) {
        AbstractProducerToDataSourceAdapter abstractProducerToDataSourceAdapter = this.this$0;
        abstractProducerToDataSourceAdapter.onNewResultImpl(obj, i, abstractProducerToDataSourceAdapter.getSettableProducerContext());
    }

    /* access modifiers changed from: protected */
    public void onFailureImpl(Throwable th) {
        Intrinsics.checkNotNullParameter(th, "throwable");
        this.this$0.onFailureImpl(th);
    }

    /* access modifiers changed from: protected */
    public void onCancellationImpl() {
        this.this$0.onCancellationImpl();
    }

    /* access modifiers changed from: protected */
    public void onProgressUpdateImpl(float f) {
        boolean unused = this.this$0.setProgress(f);
    }
}
