package androidx.work.impl.utils.taskexecutor;

import java.util.concurrent.Executor;

public interface TaskExecutor {
    Executor getMainThreadExecutor();

    SerialExecutor getSerialTaskExecutor();

    void executeOnTaskThread(Runnable runnable) {
        getSerialTaskExecutor().execute(runnable);
    }
}
