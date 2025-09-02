package com.facebook.react.views.debuggingoverlay;

public final /* synthetic */ class DebuggingOverlay$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ DebuggingOverlay f$0;
    public final /* synthetic */ int f$1;

    public /* synthetic */ DebuggingOverlay$$ExternalSyntheticLambda0(DebuggingOverlay debuggingOverlay, int i) {
        this.f$0 = debuggingOverlay;
        this.f$1 = i;
    }

    public final void run() {
        DebuggingOverlay.onDraw$lambda$0(this.f$0, this.f$1);
    }
}
