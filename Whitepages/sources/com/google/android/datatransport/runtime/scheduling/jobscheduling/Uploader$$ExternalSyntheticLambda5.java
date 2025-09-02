package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import com.google.android.datatransport.runtime.synchronization.SynchronizationGuard;
import java.util.Map;

public final /* synthetic */ class Uploader$$ExternalSyntheticLambda5 implements SynchronizationGuard.CriticalSection {
    public final /* synthetic */ Uploader f$0;
    public final /* synthetic */ Map f$1;

    public /* synthetic */ Uploader$$ExternalSyntheticLambda5(Uploader uploader, Map map) {
        this.f$0 = uploader;
        this.f$1 = map;
    }

    public final Object execute() {
        return this.f$0.lambda$logAndUpdateState$7(this.f$1);
    }
}
