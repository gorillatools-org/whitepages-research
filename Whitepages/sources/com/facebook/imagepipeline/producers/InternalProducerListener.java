package com.facebook.imagepipeline.producers;

import java.util.Map;
import kotlin.jvm.internal.Intrinsics;

public abstract class InternalProducerListener implements ProducerListener2 {
    private final ProducerListener producerListener;
    private final ProducerListener2 producerListener2;

    public InternalProducerListener(ProducerListener producerListener3, ProducerListener2 producerListener22) {
        this.producerListener = producerListener3;
        this.producerListener2 = producerListener22;
    }

    public void onProducerStart(ProducerContext producerContext, String str) {
        Intrinsics.checkNotNullParameter(producerContext, "context");
        ProducerListener producerListener3 = this.producerListener;
        if (producerListener3 != null) {
            producerListener3.onProducerStart(producerContext.getId(), str);
        }
        ProducerListener2 producerListener22 = this.producerListener2;
        if (producerListener22 != null) {
            producerListener22.onProducerStart(producerContext, str);
        }
    }

    public void onProducerEvent(ProducerContext producerContext, String str, String str2) {
        Intrinsics.checkNotNullParameter(producerContext, "context");
        ProducerListener producerListener3 = this.producerListener;
        if (producerListener3 != null) {
            producerListener3.onProducerEvent(producerContext.getId(), str, str2);
        }
        ProducerListener2 producerListener22 = this.producerListener2;
        if (producerListener22 != null) {
            producerListener22.onProducerEvent(producerContext, str, str2);
        }
    }

    public void onProducerFinishWithSuccess(ProducerContext producerContext, String str, Map map) {
        Intrinsics.checkNotNullParameter(producerContext, "context");
        ProducerListener producerListener3 = this.producerListener;
        if (producerListener3 != null) {
            producerListener3.onProducerFinishWithSuccess(producerContext.getId(), str, map);
        }
        ProducerListener2 producerListener22 = this.producerListener2;
        if (producerListener22 != null) {
            producerListener22.onProducerFinishWithSuccess(producerContext, str, map);
        }
    }

    public void onProducerFinishWithFailure(ProducerContext producerContext, String str, Throwable th, Map map) {
        Intrinsics.checkNotNullParameter(producerContext, "context");
        ProducerListener producerListener3 = this.producerListener;
        if (producerListener3 != null) {
            producerListener3.onProducerFinishWithFailure(producerContext.getId(), str, th, map);
        }
        ProducerListener2 producerListener22 = this.producerListener2;
        if (producerListener22 != null) {
            producerListener22.onProducerFinishWithFailure(producerContext, str, th, map);
        }
    }

    public void onProducerFinishWithCancellation(ProducerContext producerContext, String str, Map map) {
        Intrinsics.checkNotNullParameter(producerContext, "context");
        ProducerListener producerListener3 = this.producerListener;
        if (producerListener3 != null) {
            producerListener3.onProducerFinishWithCancellation(producerContext.getId(), str, map);
        }
        ProducerListener2 producerListener22 = this.producerListener2;
        if (producerListener22 != null) {
            producerListener22.onProducerFinishWithCancellation(producerContext, str, map);
        }
    }

    public void onUltimateProducerReached(ProducerContext producerContext, String str, boolean z) {
        Intrinsics.checkNotNullParameter(producerContext, "context");
        ProducerListener producerListener3 = this.producerListener;
        if (producerListener3 != null) {
            producerListener3.onUltimateProducerReached(producerContext.getId(), str, z);
        }
        ProducerListener2 producerListener22 = this.producerListener2;
        if (producerListener22 != null) {
            producerListener22.onUltimateProducerReached(producerContext, str, z);
        }
    }

    public boolean requiresExtraMap(ProducerContext producerContext, String str) {
        Intrinsics.checkNotNullParameter(producerContext, "context");
        ProducerListener producerListener3 = this.producerListener;
        Boolean bool = null;
        Boolean valueOf = producerListener3 != null ? Boolean.valueOf(producerListener3.requiresExtraMap(producerContext.getId())) : null;
        if (!Intrinsics.areEqual((Object) valueOf, (Object) Boolean.TRUE)) {
            ProducerListener2 producerListener22 = this.producerListener2;
            if (producerListener22 != null) {
                bool = Boolean.valueOf(producerListener22.requiresExtraMap(producerContext, str));
            }
            valueOf = bool;
        }
        if (valueOf != null) {
            return valueOf.booleanValue();
        }
        return false;
    }
}
