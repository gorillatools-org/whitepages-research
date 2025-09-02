package com.facebook.react.common;

import com.facebook.infer.annotation.Assertions;
import kotlin.jvm.internal.Intrinsics;

public final class SingleThreadAsserter {
    private Thread thread;

    public final void assertNow() {
        Thread currentThread = Thread.currentThread();
        if (this.thread == null) {
            this.thread = currentThread;
        }
        Assertions.assertCondition(Intrinsics.areEqual((Object) this.thread, (Object) currentThread));
    }
}
