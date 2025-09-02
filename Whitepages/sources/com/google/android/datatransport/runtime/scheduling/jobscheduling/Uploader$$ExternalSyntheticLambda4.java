package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import com.google.android.datatransport.runtime.synchronization.SynchronizationGuard;

public final /* synthetic */ class Uploader$$ExternalSyntheticLambda4 implements SynchronizationGuard.CriticalSection {
    public final /* synthetic */ Uploader f$0;

    public /* synthetic */ Uploader$$ExternalSyntheticLambda4(Uploader uploader) {
        this.f$0 = uploader;
    }

    public final Object execute() {
        return this.f$0.lambda$logAndUpdateState$6();
    }
}
