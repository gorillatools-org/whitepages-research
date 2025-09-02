package com.google.firebase.perf.session.gauges;

import com.google.firebase.perf.util.Timer;

public final /* synthetic */ class MemoryGaugeCollector$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ MemoryGaugeCollector f$0;
    public final /* synthetic */ Timer f$1;

    public /* synthetic */ MemoryGaugeCollector$$ExternalSyntheticLambda0(MemoryGaugeCollector memoryGaugeCollector, Timer timer) {
        this.f$0 = memoryGaugeCollector;
        this.f$1 = timer;
    }

    public final void run() {
        this.f$0.lambda$scheduleMemoryMetricCollectionWithRate$0(this.f$1);
    }
}
