package com.facebook.imagepipeline.producers;

import java.util.Map;

public interface ProducerListener2 {
    void onProducerEvent(ProducerContext producerContext, String str, String str2);

    void onProducerFinishWithCancellation(ProducerContext producerContext, String str, Map map);

    void onProducerFinishWithFailure(ProducerContext producerContext, String str, Throwable th, Map map);

    void onProducerFinishWithSuccess(ProducerContext producerContext, String str, Map map);

    void onProducerStart(ProducerContext producerContext, String str);

    void onUltimateProducerReached(ProducerContext producerContext, String str, boolean z);

    boolean requiresExtraMap(ProducerContext producerContext, String str);
}
