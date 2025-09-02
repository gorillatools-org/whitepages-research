package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import com.google.android.datatransport.runtime.TransportContext;
import com.google.android.datatransport.runtime.synchronization.SynchronizationGuard;

public final /* synthetic */ class Uploader$$ExternalSyntheticLambda2 implements SynchronizationGuard.CriticalSection {
    public final /* synthetic */ Uploader f$0;
    public final /* synthetic */ Iterable f$1;
    public final /* synthetic */ TransportContext f$2;
    public final /* synthetic */ long f$3;

    public /* synthetic */ Uploader$$ExternalSyntheticLambda2(Uploader uploader, Iterable iterable, TransportContext transportContext, long j) {
        this.f$0 = uploader;
        this.f$1 = iterable;
        this.f$2 = transportContext;
        this.f$3 = j;
    }

    public final Object execute() {
        return this.f$0.lambda$logAndUpdateState$4(this.f$1, this.f$2, this.f$3);
    }
}
