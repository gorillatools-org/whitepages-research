package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import com.google.android.datatransport.runtime.TransportContext;
import com.google.android.datatransport.runtime.synchronization.SynchronizationGuard;

public final /* synthetic */ class Uploader$$ExternalSyntheticLambda6 implements SynchronizationGuard.CriticalSection {
    public final /* synthetic */ Uploader f$0;
    public final /* synthetic */ TransportContext f$1;
    public final /* synthetic */ long f$2;

    public /* synthetic */ Uploader$$ExternalSyntheticLambda6(Uploader uploader, TransportContext transportContext, long j) {
        this.f$0 = uploader;
        this.f$1 = transportContext;
        this.f$2 = j;
    }

    public final Object execute() {
        return this.f$0.lambda$logAndUpdateState$8(this.f$1, this.f$2);
    }
}
