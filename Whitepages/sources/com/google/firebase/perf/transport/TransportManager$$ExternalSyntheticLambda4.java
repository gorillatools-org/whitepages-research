package com.google.firebase.perf.transport;

import com.google.firebase.perf.v1.ApplicationProcessState;
import com.google.firebase.perf.v1.NetworkRequestMetric;

public final /* synthetic */ class TransportManager$$ExternalSyntheticLambda4 implements Runnable {
    public final /* synthetic */ TransportManager f$0;
    public final /* synthetic */ NetworkRequestMetric f$1;
    public final /* synthetic */ ApplicationProcessState f$2;

    public /* synthetic */ TransportManager$$ExternalSyntheticLambda4(TransportManager transportManager, NetworkRequestMetric networkRequestMetric, ApplicationProcessState applicationProcessState) {
        this.f$0 = transportManager;
        this.f$1 = networkRequestMetric;
        this.f$2 = applicationProcessState;
    }

    public final void run() {
        this.f$0.lambda$log$3(this.f$1, this.f$2);
    }
}
