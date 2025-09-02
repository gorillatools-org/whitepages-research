package com.google.firebase.crashlytics.internal.common;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import java.util.concurrent.CountDownLatch;

public final /* synthetic */ class Utils$$ExternalSyntheticLambda0 implements Continuation {
    public final /* synthetic */ CountDownLatch f$0;

    public /* synthetic */ Utils$$ExternalSyntheticLambda0(CountDownLatch countDownLatch) {
        this.f$0 = countDownLatch;
    }

    public final Object then(Task task) {
        return this.f$0.countDown();
    }
}
