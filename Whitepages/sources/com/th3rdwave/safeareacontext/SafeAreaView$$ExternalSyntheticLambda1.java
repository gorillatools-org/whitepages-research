package com.th3rdwave.safeareacontext;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.jvm.internal.Ref$BooleanRef;

public final /* synthetic */ class SafeAreaView$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ ReentrantLock f$0;
    public final /* synthetic */ Ref$BooleanRef f$1;
    public final /* synthetic */ Condition f$2;

    public /* synthetic */ SafeAreaView$$ExternalSyntheticLambda1(ReentrantLock reentrantLock, Ref$BooleanRef ref$BooleanRef, Condition condition) {
        this.f$0 = reentrantLock;
        this.f$1 = ref$BooleanRef;
        this.f$2 = condition;
    }

    public final void run() {
        SafeAreaView.waitForReactLayout$lambda$2(this.f$0, this.f$1, this.f$2);
    }
}
