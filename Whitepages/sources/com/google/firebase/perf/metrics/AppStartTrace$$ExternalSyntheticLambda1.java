package com.google.firebase.perf.metrics;

public final /* synthetic */ class AppStartTrace$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ AppStartTrace f$0;

    public /* synthetic */ AppStartTrace$$ExternalSyntheticLambda1(AppStartTrace appStartTrace) {
        this.f$0 = appStartTrace;
    }

    public final void run() {
        this.f$0.recordPreDraw();
    }
}
