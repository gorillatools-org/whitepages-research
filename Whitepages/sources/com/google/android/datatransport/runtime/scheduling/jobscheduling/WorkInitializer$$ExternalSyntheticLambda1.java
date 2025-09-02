package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import com.google.android.datatransport.runtime.synchronization.SynchronizationGuard;

public final /* synthetic */ class WorkInitializer$$ExternalSyntheticLambda1 implements SynchronizationGuard.CriticalSection {
    public final /* synthetic */ WorkInitializer f$0;

    public /* synthetic */ WorkInitializer$$ExternalSyntheticLambda1(WorkInitializer workInitializer) {
        this.f$0 = workInitializer;
    }

    public final Object execute() {
        return this.f$0.lambda$ensureContextsScheduled$0();
    }
}
