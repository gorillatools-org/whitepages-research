package com.facebook.internal;

import com.facebook.FacebookSdk;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.FutureTask;
import kotlin.jvm.internal.Intrinsics;

public final class LockOnGetVariable {
    private CountDownLatch initLatch = new CountDownLatch(1);
    private Object storedValue;

    public LockOnGetVariable(Callable callable) {
        Intrinsics.checkNotNullParameter(callable, "callable");
        FacebookSdk.getExecutor().execute(new FutureTask(new LockOnGetVariable$$ExternalSyntheticLambda0(this, callable)));
    }

    /* access modifiers changed from: private */
    public static final Void _init_$lambda$0(LockOnGetVariable lockOnGetVariable, Callable callable) {
        Intrinsics.checkNotNullParameter(lockOnGetVariable, "this$0");
        Intrinsics.checkNotNullParameter(callable, "$callable");
        try {
            lockOnGetVariable.storedValue = callable.call();
        } finally {
            CountDownLatch countDownLatch = lockOnGetVariable.initLatch;
            if (countDownLatch != null) {
                countDownLatch.countDown();
            }
        }
    }
}
