package com.google.firebase.perf.session.gauges;

import com.google.firebase.perf.v1.ApplicationProcessState;

public final /* synthetic */ class GaugeManager$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ GaugeManager f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ ApplicationProcessState f$2;

    public /* synthetic */ GaugeManager$$ExternalSyntheticLambda1(GaugeManager gaugeManager, String str, ApplicationProcessState applicationProcessState) {
        this.f$0 = gaugeManager;
        this.f$1 = str;
        this.f$2 = applicationProcessState;
    }

    public final void run() {
        this.f$0.lambda$startCollectingGauges$2(this.f$1, this.f$2);
    }
}
