package com.facebook.imagepipeline.producers;

import com.facebook.common.executors.StatefulRunnable;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;

public abstract class StatefulProducerRunnable extends StatefulRunnable {
    private final Consumer consumer;
    private final ProducerContext producerContext;
    private final ProducerListener2 producerListener;
    private final String producerName;

    /* access modifiers changed from: protected */
    public Map getExtraMapOnCancellation() {
        return null;
    }

    /* access modifiers changed from: protected */
    public Map getExtraMapOnFailure(Exception exc) {
        return null;
    }

    /* access modifiers changed from: protected */
    public Map getExtraMapOnSuccess(Object obj) {
        return null;
    }

    public StatefulProducerRunnable(Consumer consumer2, ProducerListener2 producerListener2, ProducerContext producerContext2, String str) {
        Intrinsics.checkNotNullParameter(consumer2, "consumer");
        Intrinsics.checkNotNullParameter(producerListener2, "producerListener");
        Intrinsics.checkNotNullParameter(producerContext2, "producerContext");
        Intrinsics.checkNotNullParameter(str, "producerName");
        this.consumer = consumer2;
        this.producerListener = producerListener2;
        this.producerContext = producerContext2;
        this.producerName = str;
        producerListener2.onProducerStart(producerContext2, str);
    }

    /* access modifiers changed from: protected */
    public void onSuccess(Object obj) {
        ProducerListener2 producerListener2 = this.producerListener;
        ProducerContext producerContext2 = this.producerContext;
        String str = this.producerName;
        producerListener2.onProducerFinishWithSuccess(producerContext2, str, producerListener2.requiresExtraMap(producerContext2, str) ? getExtraMapOnSuccess(obj) : null);
        this.consumer.onNewResult(obj, 1);
    }

    /* access modifiers changed from: protected */
    public void onFailure(Exception exc) {
        Map map;
        Intrinsics.checkNotNullParameter(exc, "e");
        ProducerListener2 producerListener2 = this.producerListener;
        ProducerContext producerContext2 = this.producerContext;
        String str = this.producerName;
        if (producerListener2.requiresExtraMap(producerContext2, str)) {
            map = getExtraMapOnFailure(exc);
        } else {
            map = null;
        }
        producerListener2.onProducerFinishWithFailure(producerContext2, str, exc, map);
        this.consumer.onFailure(exc);
    }

    /* access modifiers changed from: protected */
    public void onCancellation() {
        ProducerListener2 producerListener2 = this.producerListener;
        ProducerContext producerContext2 = this.producerContext;
        String str = this.producerName;
        producerListener2.onProducerFinishWithCancellation(producerContext2, str, producerListener2.requiresExtraMap(producerContext2, str) ? getExtraMapOnCancellation() : null);
        this.consumer.onCancellation();
    }
}
