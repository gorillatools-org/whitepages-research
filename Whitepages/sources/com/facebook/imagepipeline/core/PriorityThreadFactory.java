package com.facebook.imagepipeline.core;

import android.os.Process;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.jvm.internal.Intrinsics;

public final class PriorityThreadFactory implements ThreadFactory {
    private final boolean addThreadNumber;
    private final String prefix;
    private final AtomicInteger threadNumber = new AtomicInteger(1);
    private final int threadPriority;

    public PriorityThreadFactory(int i, String str, boolean z) {
        Intrinsics.checkNotNullParameter(str, "prefix");
        this.threadPriority = i;
        this.prefix = str;
        this.addThreadNumber = z;
    }

    public Thread newThread(Runnable runnable) {
        String str;
        Intrinsics.checkNotNullParameter(runnable, "runnable");
        PriorityThreadFactory$$ExternalSyntheticLambda0 priorityThreadFactory$$ExternalSyntheticLambda0 = new PriorityThreadFactory$$ExternalSyntheticLambda0(this, runnable);
        if (this.addThreadNumber) {
            str = this.prefix + "-" + this.threadNumber.getAndIncrement();
        } else {
            str = this.prefix;
        }
        return new Thread(priorityThreadFactory$$ExternalSyntheticLambda0, str);
    }

    /* access modifiers changed from: private */
    public static final void newThread$lambda$0(PriorityThreadFactory priorityThreadFactory, Runnable runnable) {
        Intrinsics.checkNotNullParameter(priorityThreadFactory, "this$0");
        Intrinsics.checkNotNullParameter(runnable, "$runnable");
        try {
            Process.setThreadPriority(priorityThreadFactory.threadPriority);
        } catch (Throwable unused) {
        }
        runnable.run();
    }
}
