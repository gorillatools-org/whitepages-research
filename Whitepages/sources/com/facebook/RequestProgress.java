package com.facebook;

import android.os.Handler;
import kotlin.jvm.internal.Intrinsics;

public final class RequestProgress {
    private final Handler callbackHandler;
    private long lastReportedProgress;
    private long maxProgress;
    private long progress;
    private final GraphRequest request;
    private final long threshold = FacebookSdk.getOnProgressThreshold();

    public RequestProgress(Handler handler, GraphRequest graphRequest) {
        Intrinsics.checkNotNullParameter(graphRequest, "request");
        this.callbackHandler = handler;
        this.request = graphRequest;
    }

    public final void addProgress(long j) {
        long j2 = this.progress + j;
        this.progress = j2;
        if (j2 >= this.lastReportedProgress + this.threshold || j2 >= this.maxProgress) {
            reportProgress();
        }
    }

    public final void addToMax(long j) {
        this.maxProgress += j;
    }

    public final void reportProgress() {
        if (this.progress > this.lastReportedProgress) {
            this.request.getCallback();
        }
    }
}
