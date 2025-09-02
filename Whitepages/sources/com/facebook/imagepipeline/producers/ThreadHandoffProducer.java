package com.facebook.imagepipeline.producers;

import android.os.Looper;
import com.facebook.imagepipeline.instrumentation.FrescoInstrumenter;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class ThreadHandoffProducer implements Producer {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final Producer inputProducer;
    private final ThreadHandoffProducerQueue threadHandoffProducerQueue;

    public ThreadHandoffProducer(Producer producer, ThreadHandoffProducerQueue threadHandoffProducerQueue2) {
        Intrinsics.checkNotNullParameter(producer, "inputProducer");
        Intrinsics.checkNotNullParameter(threadHandoffProducerQueue2, "threadHandoffProducerQueue");
        this.inputProducer = producer;
        this.threadHandoffProducerQueue = threadHandoffProducerQueue2;
    }

    public final Producer getInputProducer() {
        return this.inputProducer;
    }

    public final ThreadHandoffProducerQueue getThreadHandoffProducerQueue() {
        return this.threadHandoffProducerQueue;
    }

    public void produceResults(Consumer consumer, ProducerContext producerContext) {
        Intrinsics.checkNotNullParameter(consumer, "consumer");
        Intrinsics.checkNotNullParameter(producerContext, "context");
        if (!FrescoSystrace.isTracing()) {
            ProducerListener2 producerListener = producerContext.getProducerListener();
            Companion companion = Companion;
            if (companion.shouldRunImmediately(producerContext)) {
                producerListener.onProducerStart(producerContext, "BackgroundThreadHandoffProducer");
                producerListener.onProducerFinishWithSuccess(producerContext, "BackgroundThreadHandoffProducer", (Map) null);
                this.inputProducer.produceResults(consumer, producerContext);
                return;
            }
            ThreadHandoffProducer$produceResults$1$statefulRunnable$1 threadHandoffProducer$produceResults$1$statefulRunnable$1 = new ThreadHandoffProducer$produceResults$1$statefulRunnable$1(consumer, producerListener, producerContext, this);
            producerContext.addCallbacks(new ThreadHandoffProducer$produceResults$1$1(threadHandoffProducer$produceResults$1$statefulRunnable$1, this));
            this.threadHandoffProducerQueue.addToQueueOrExecute(FrescoInstrumenter.decorateRunnable(threadHandoffProducer$produceResults$1$statefulRunnable$1, companion.getInstrumentationTag(producerContext)));
            return;
        }
        FrescoSystrace.beginSection("ThreadHandoffProducer#produceResults");
        try {
            ProducerListener2 producerListener2 = producerContext.getProducerListener();
            Companion companion2 = Companion;
            if (companion2.shouldRunImmediately(producerContext)) {
                producerListener2.onProducerStart(producerContext, "BackgroundThreadHandoffProducer");
                producerListener2.onProducerFinishWithSuccess(producerContext, "BackgroundThreadHandoffProducer", (Map) null);
                this.inputProducer.produceResults(consumer, producerContext);
                return;
            }
            ThreadHandoffProducer$produceResults$1$statefulRunnable$1 threadHandoffProducer$produceResults$1$statefulRunnable$12 = new ThreadHandoffProducer$produceResults$1$statefulRunnable$1(consumer, producerListener2, producerContext, this);
            producerContext.addCallbacks(new ThreadHandoffProducer$produceResults$1$1(threadHandoffProducer$produceResults$1$statefulRunnable$12, this));
            this.threadHandoffProducerQueue.addToQueueOrExecute(FrescoInstrumenter.decorateRunnable(threadHandoffProducer$produceResults$1$statefulRunnable$12, companion2.getInstrumentationTag(producerContext)));
            Unit unit = Unit.INSTANCE;
            FrescoSystrace.endSection();
        } finally {
            FrescoSystrace.endSection();
        }
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* access modifiers changed from: private */
        public final String getInstrumentationTag(ProducerContext producerContext) {
            if (!FrescoInstrumenter.isTracing()) {
                return null;
            }
            String id = producerContext.getId();
            return "ThreadHandoffProducer_produceResults_" + id;
        }

        /* access modifiers changed from: private */
        public final boolean shouldRunImmediately(ProducerContext producerContext) {
            if (producerContext.getImagePipelineConfig().getExperiments().getHandOffOnUiThreadOnly() && Looper.getMainLooper().getThread() != Thread.currentThread()) {
                return true;
            }
            return false;
        }
    }
}
