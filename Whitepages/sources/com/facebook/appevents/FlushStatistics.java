package com.facebook.appevents;

import kotlin.jvm.internal.Intrinsics;

public final class FlushStatistics {
    private int numEvents;
    private FlushResult result = FlushResult.SUCCESS;

    public final int getNumEvents() {
        return this.numEvents;
    }

    public final void setNumEvents(int i) {
        this.numEvents = i;
    }

    public final FlushResult getResult() {
        return this.result;
    }

    public final void setResult(FlushResult flushResult) {
        Intrinsics.checkNotNullParameter(flushResult, "<set-?>");
        this.result = flushResult;
    }
}
