package com.facebook.imagepipeline.core;

import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;

public interface ExecutorSupplier {
    Executor forBackgroundTasks();

    Executor forDecode();

    Executor forLightweightBackgroundTasks();

    Executor forLocalStorageRead();

    Executor forLocalStorageWrite();

    Executor forThumbnailProducer();

    ScheduledExecutorService scheduledExecutorServiceForBackgroundTasks();
}
