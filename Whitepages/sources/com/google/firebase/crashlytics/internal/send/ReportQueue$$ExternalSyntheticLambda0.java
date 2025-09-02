package com.google.firebase.crashlytics.internal.send;

import java.util.concurrent.CountDownLatch;

public final /* synthetic */ class ReportQueue$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ ReportQueue f$0;
    public final /* synthetic */ CountDownLatch f$1;

    public /* synthetic */ ReportQueue$$ExternalSyntheticLambda0(ReportQueue reportQueue, CountDownLatch countDownLatch) {
        this.f$0 = reportQueue;
        this.f$1 = countDownLatch;
    }

    public final void run() {
        this.f$0.lambda$flushScheduledReportsIfAble$0(this.f$1);
    }
}
