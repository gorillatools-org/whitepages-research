package com.google.firebase.concurrent;

import android.os.Process;
import android.os.StrictMode;
import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

class CustomThreadFactory implements ThreadFactory {
    private static final ThreadFactory DEFAULT = Executors.defaultThreadFactory();
    private final String namePrefix;
    private final StrictMode.ThreadPolicy policy;
    private final int priority;
    private final AtomicLong threadCount = new AtomicLong();

    CustomThreadFactory(String str, int i, StrictMode.ThreadPolicy threadPolicy) {
        this.namePrefix = str;
        this.priority = i;
        this.policy = threadPolicy;
    }

    public Thread newThread(Runnable runnable) {
        Thread newThread = DEFAULT.newThread(new CustomThreadFactory$$ExternalSyntheticLambda0(this, runnable));
        newThread.setName(String.format(Locale.ROOT, "%s Thread #%d", new Object[]{this.namePrefix, Long.valueOf(this.threadCount.getAndIncrement())}));
        return newThread;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$newThread$0(Runnable runnable) {
        Process.setThreadPriority(this.priority);
        StrictMode.ThreadPolicy threadPolicy = this.policy;
        if (threadPolicy != null) {
            StrictMode.setThreadPolicy(threadPolicy);
        }
        runnable.run();
    }
}
