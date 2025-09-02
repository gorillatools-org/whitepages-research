package com.google.firebase.crashlytics.internal.common;

import java.util.concurrent.atomic.AtomicInteger;

public final class OnDemandCounter {
    private final AtomicInteger droppedOnDemandExceptions = new AtomicInteger();
    private final AtomicInteger recordedOnDemandExceptions = new AtomicInteger();

    public int getRecordedOnDemandExceptions() {
        return this.recordedOnDemandExceptions.get();
    }

    public void incrementRecordedOnDemandExceptions() {
        this.recordedOnDemandExceptions.getAndIncrement();
    }

    public int getDroppedOnDemandExceptions() {
        return this.droppedOnDemandExceptions.get();
    }

    public void incrementDroppedOnDemandExceptions() {
        this.droppedOnDemandExceptions.getAndIncrement();
    }

    public void resetDroppedOnDemandExceptions() {
        this.droppedOnDemandExceptions.set(0);
    }
}
