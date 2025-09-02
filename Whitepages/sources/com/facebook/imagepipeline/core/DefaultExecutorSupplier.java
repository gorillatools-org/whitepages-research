package com.facebook.imagepipeline.core;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class DefaultExecutorSupplier implements ExecutorSupplier {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final Executor backgroundExecutor;
    private final ScheduledExecutorService backgroundScheduledExecutorService;
    private final Executor decodeExecutor;
    private final Executor ioBoundExecutor;
    private final Executor lightWeightBackgroundExecutor;

    public DefaultExecutorSupplier(int i) {
        ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(2, new PriorityThreadFactory(10, "FrescoIoBoundExecutor", true));
        Intrinsics.checkNotNullExpressionValue(newFixedThreadPool, "newFixedThreadPool(...)");
        this.ioBoundExecutor = newFixedThreadPool;
        ExecutorService newFixedThreadPool2 = Executors.newFixedThreadPool(i, new PriorityThreadFactory(10, "FrescoDecodeExecutor", true));
        Intrinsics.checkNotNullExpressionValue(newFixedThreadPool2, "newFixedThreadPool(...)");
        this.decodeExecutor = newFixedThreadPool2;
        ExecutorService newFixedThreadPool3 = Executors.newFixedThreadPool(i, new PriorityThreadFactory(10, "FrescoBackgroundExecutor", true));
        Intrinsics.checkNotNullExpressionValue(newFixedThreadPool3, "newFixedThreadPool(...)");
        this.backgroundExecutor = newFixedThreadPool3;
        ExecutorService newFixedThreadPool4 = Executors.newFixedThreadPool(1, new PriorityThreadFactory(10, "FrescoLightWeightBackgroundExecutor", true));
        Intrinsics.checkNotNullExpressionValue(newFixedThreadPool4, "newFixedThreadPool(...)");
        this.lightWeightBackgroundExecutor = newFixedThreadPool4;
        ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(i, new PriorityThreadFactory(10, "FrescoBackgroundExecutor", true));
        Intrinsics.checkNotNullExpressionValue(newScheduledThreadPool, "newScheduledThreadPool(...)");
        this.backgroundScheduledExecutorService = newScheduledThreadPool;
    }

    public Executor forLocalStorageRead() {
        return this.ioBoundExecutor;
    }

    public Executor forLocalStorageWrite() {
        return this.ioBoundExecutor;
    }

    public Executor forDecode() {
        return this.decodeExecutor;
    }

    public Executor forBackgroundTasks() {
        return this.backgroundExecutor;
    }

    public ScheduledExecutorService scheduledExecutorServiceForBackgroundTasks() {
        return this.backgroundScheduledExecutorService;
    }

    public Executor forLightweightBackgroundTasks() {
        return this.lightWeightBackgroundExecutor;
    }

    public Executor forThumbnailProducer() {
        return this.ioBoundExecutor;
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
