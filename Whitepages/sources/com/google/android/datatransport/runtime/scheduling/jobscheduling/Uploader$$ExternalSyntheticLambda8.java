package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import com.google.android.datatransport.runtime.scheduling.persistence.ClientHealthMetricsStore;
import com.google.android.datatransport.runtime.synchronization.SynchronizationGuard;

public final /* synthetic */ class Uploader$$ExternalSyntheticLambda8 implements SynchronizationGuard.CriticalSection {
    public final /* synthetic */ ClientHealthMetricsStore f$0;

    public /* synthetic */ Uploader$$ExternalSyntheticLambda8(ClientHealthMetricsStore clientHealthMetricsStore) {
        this.f$0 = clientHealthMetricsStore;
    }

    public final Object execute() {
        return this.f$0.loadClientMetrics();
    }
}
