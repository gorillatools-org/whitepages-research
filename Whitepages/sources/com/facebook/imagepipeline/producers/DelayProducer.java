package com.facebook.imagepipeline.producers;

import com.facebook.imagepipeline.request.ImageRequest;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import kotlin.jvm.internal.Intrinsics;

public final class DelayProducer implements Producer {
    private final ScheduledExecutorService backgroundTasksExecutor;
    private final Producer inputProducer;

    public DelayProducer(Producer producer, ScheduledExecutorService scheduledExecutorService) {
        Intrinsics.checkNotNullParameter(producer, "inputProducer");
        this.inputProducer = producer;
        this.backgroundTasksExecutor = scheduledExecutorService;
    }

    public void produceResults(Consumer consumer, ProducerContext producerContext) {
        Intrinsics.checkNotNullParameter(consumer, "consumer");
        Intrinsics.checkNotNullParameter(producerContext, "context");
        ImageRequest imageRequest = producerContext.getImageRequest();
        ScheduledExecutorService scheduledExecutorService = this.backgroundTasksExecutor;
        if (scheduledExecutorService != null) {
            scheduledExecutorService.schedule(new DelayProducer$$ExternalSyntheticLambda0(this, consumer, producerContext), (long) imageRequest.getDelayMs(), TimeUnit.MILLISECONDS);
        } else {
            this.inputProducer.produceResults(consumer, producerContext);
        }
    }

    /* access modifiers changed from: private */
    public static final void produceResults$lambda$0(DelayProducer delayProducer, Consumer consumer, ProducerContext producerContext) {
        Intrinsics.checkNotNullParameter(delayProducer, "this$0");
        Intrinsics.checkNotNullParameter(consumer, "$consumer");
        Intrinsics.checkNotNullParameter(producerContext, "$context");
        delayProducer.inputProducer.produceResults(consumer, producerContext);
    }
}
