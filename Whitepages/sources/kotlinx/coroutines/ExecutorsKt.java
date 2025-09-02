package kotlinx.coroutines;

import java.util.concurrent.Executor;

public abstract class ExecutorsKt {
    public static final CoroutineDispatcher from(Executor executor) {
        return new ExecutorCoroutineDispatcherImpl(executor);
    }
}
