package com.google.firebase.perf.session.gauges;

import com.google.firebase.perf.util.Timer;

public final /* synthetic */ class CpuGaugeCollector$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ CpuGaugeCollector f$0;
    public final /* synthetic */ Timer f$1;

    public /* synthetic */ CpuGaugeCollector$$ExternalSyntheticLambda0(CpuGaugeCollector cpuGaugeCollector, Timer timer) {
        this.f$0 = cpuGaugeCollector;
        this.f$1 = timer;
    }

    public final void run() {
        this.f$0.lambda$scheduleCpuMetricCollectionWithRate$0(this.f$1);
    }
}
