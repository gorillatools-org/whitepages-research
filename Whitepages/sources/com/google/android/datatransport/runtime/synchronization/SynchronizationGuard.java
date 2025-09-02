package com.google.android.datatransport.runtime.synchronization;

public interface SynchronizationGuard {

    public interface CriticalSection {
        Object execute();
    }

    Object runCriticalSection(CriticalSection criticalSection);
}
