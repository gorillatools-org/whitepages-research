package com.google.firebase.perf.transport;

public final /* synthetic */ class TransportManager$$ExternalSyntheticLambda3 implements Runnable {
    public final /* synthetic */ TransportManager f$0;
    public final /* synthetic */ PendingPerfEvent f$1;

    public /* synthetic */ TransportManager$$ExternalSyntheticLambda3(TransportManager transportManager, PendingPerfEvent pendingPerfEvent) {
        this.f$0 = transportManager;
        this.f$1 = pendingPerfEvent;
    }

    public final void run() {
        this.f$0.lambda$finishInitialization$0(this.f$1);
    }
}
