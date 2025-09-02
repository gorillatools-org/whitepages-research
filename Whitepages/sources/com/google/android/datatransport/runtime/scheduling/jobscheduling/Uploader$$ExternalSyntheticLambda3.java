package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import com.google.android.datatransport.runtime.synchronization.SynchronizationGuard;

public final /* synthetic */ class Uploader$$ExternalSyntheticLambda3 implements SynchronizationGuard.CriticalSection {
    public final /* synthetic */ Uploader f$0;
    public final /* synthetic */ Iterable f$1;

    public /* synthetic */ Uploader$$ExternalSyntheticLambda3(Uploader uploader, Iterable iterable) {
        this.f$0 = uploader;
        this.f$1 = iterable;
    }

    public final Object execute() {
        return this.f$0.lambda$logAndUpdateState$5(this.f$1);
    }
}
