package com.google.firebase.perf.metrics;

import com.google.firebase.perf.v1.TraceMetric;

public final /* synthetic */ class AppStartTrace$$ExternalSyntheticLambda4 implements Runnable {
    public final /* synthetic */ AppStartTrace f$0;
    public final /* synthetic */ TraceMetric.Builder f$1;

    public /* synthetic */ AppStartTrace$$ExternalSyntheticLambda4(AppStartTrace appStartTrace, TraceMetric.Builder builder) {
        this.f$0 = appStartTrace;
        this.f$1 = builder;
    }

    public final void run() {
        this.f$0.lambda$logExperimentTrace$0(this.f$1);
    }
}
