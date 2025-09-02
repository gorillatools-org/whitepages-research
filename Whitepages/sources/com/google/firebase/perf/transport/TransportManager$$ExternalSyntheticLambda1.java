package com.google.firebase.perf.transport;

import com.google.firebase.perf.v1.ApplicationProcessState;
import com.google.firebase.perf.v1.GaugeMetric;

public final /* synthetic */ class TransportManager$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ TransportManager f$0;
    public final /* synthetic */ GaugeMetric f$1;
    public final /* synthetic */ ApplicationProcessState f$2;

    public /* synthetic */ TransportManager$$ExternalSyntheticLambda1(TransportManager transportManager, GaugeMetric gaugeMetric, ApplicationProcessState applicationProcessState) {
        this.f$0 = transportManager;
        this.f$1 = gaugeMetric;
        this.f$2 = applicationProcessState;
    }

    public final void run() {
        this.f$0.lambda$log$4(this.f$1, this.f$2);
    }
}
