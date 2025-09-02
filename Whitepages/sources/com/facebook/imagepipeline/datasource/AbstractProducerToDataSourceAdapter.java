package com.facebook.imagepipeline.datasource;

import com.facebook.common.internal.Preconditions;
import com.facebook.datasource.AbstractDataSource;
import com.facebook.imagepipeline.listener.RequestListener2;
import com.facebook.imagepipeline.producers.BaseConsumer;
import com.facebook.imagepipeline.producers.Consumer;
import com.facebook.imagepipeline.producers.Producer;
import com.facebook.imagepipeline.producers.ProducerContext;
import com.facebook.imagepipeline.producers.SettableProducerContext;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

public abstract class AbstractProducerToDataSourceAdapter extends AbstractDataSource {
    private final RequestListener2 requestListener;
    private final SettableProducerContext settableProducerContext;

    public final SettableProducerContext getSettableProducerContext() {
        return this.settableProducerContext;
    }

    protected AbstractProducerToDataSourceAdapter(Producer producer, SettableProducerContext settableProducerContext2, RequestListener2 requestListener2) {
        Intrinsics.checkNotNullParameter(producer, "producer");
        Intrinsics.checkNotNullParameter(settableProducerContext2, "settableProducerContext");
        Intrinsics.checkNotNullParameter(requestListener2, "requestListener");
        this.settableProducerContext = settableProducerContext2;
        this.requestListener = requestListener2;
        if (!FrescoSystrace.isTracing()) {
            setExtras(settableProducerContext2.getExtras());
            if (!FrescoSystrace.isTracing()) {
                requestListener2.onRequestStart(settableProducerContext2);
            } else {
                FrescoSystrace.beginSection("AbstractProducerToDataSourceAdapter()->onRequestStart");
                try {
                    requestListener2.onRequestStart(settableProducerContext2);
                    Unit unit = Unit.INSTANCE;
                } finally {
                    FrescoSystrace.endSection();
                }
            }
            if (!FrescoSystrace.isTracing()) {
                producer.produceResults(createConsumer(), settableProducerContext2);
                return;
            }
            FrescoSystrace.beginSection("AbstractProducerToDataSourceAdapter()->produceResult");
            try {
                producer.produceResults(createConsumer(), settableProducerContext2);
                Unit unit2 = Unit.INSTANCE;
            } finally {
                FrescoSystrace.endSection();
            }
        } else {
            FrescoSystrace.beginSection("AbstractProducerToDataSourceAdapter()");
            try {
                setExtras(settableProducerContext2.getExtras());
                if (!FrescoSystrace.isTracing()) {
                    requestListener2.onRequestStart(settableProducerContext2);
                } else {
                    FrescoSystrace.beginSection("AbstractProducerToDataSourceAdapter()->onRequestStart");
                    requestListener2.onRequestStart(settableProducerContext2);
                    Unit unit3 = Unit.INSTANCE;
                }
                if (!FrescoSystrace.isTracing()) {
                    producer.produceResults(createConsumer(), settableProducerContext2);
                } else {
                    FrescoSystrace.beginSection("AbstractProducerToDataSourceAdapter()->produceResult");
                    producer.produceResults(createConsumer(), settableProducerContext2);
                    Unit unit4 = Unit.INSTANCE;
                }
                Unit unit5 = Unit.INSTANCE;
            } catch (Throwable th) {
                throw th;
            } finally {
                FrescoSystrace.endSection();
            }
        }
    }

    private final Consumer createConsumer() {
        return new AbstractProducerToDataSourceAdapter$createConsumer$1(this);
    }

    /* access modifiers changed from: protected */
    public void onNewResultImpl(Object obj, int i, ProducerContext producerContext) {
        Intrinsics.checkNotNullParameter(producerContext, "producerContext");
        boolean isLast = BaseConsumer.isLast(i);
        if (super.setResult(obj, isLast, getExtras(producerContext)) && isLast) {
            this.requestListener.onRequestSuccess(this.settableProducerContext);
        }
    }

    /* access modifiers changed from: protected */
    public final Map getExtras(ProducerContext producerContext) {
        Intrinsics.checkNotNullParameter(producerContext, "producerContext");
        return producerContext.getExtras();
    }

    /* access modifiers changed from: private */
    public final void onFailureImpl(Throwable th) {
        if (super.setFailure(th, getExtras(this.settableProducerContext))) {
            this.requestListener.onRequestFailure(this.settableProducerContext, th);
        }
    }

    /* access modifiers changed from: private */
    public final synchronized void onCancellationImpl() {
        Preconditions.checkState(isClosed());
    }

    public boolean close() {
        if (!super.close()) {
            return false;
        }
        if (super.isFinished()) {
            return true;
        }
        this.requestListener.onRequestCancellation(this.settableProducerContext);
        this.settableProducerContext.cancel();
        return true;
    }
}
