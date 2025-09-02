package kotlinx.coroutines;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.internal.ConcurrentKt;

public final class ExecutorCoroutineDispatcherImpl extends ExecutorCoroutineDispatcher implements Delay {
    private final Executor executor;

    public ExecutorCoroutineDispatcherImpl(Executor executor2) {
        this.executor = executor2;
        ConcurrentKt.removeFutureOnCancel(getExecutor());
    }

    public Executor getExecutor() {
        return this.executor;
    }

    public void dispatch(CoroutineContext coroutineContext, Runnable runnable) {
        try {
            Executor executor2 = getExecutor();
            AbstractTimeSourceKt.getTimeSource();
            executor2.execute(runnable);
        } catch (RejectedExecutionException e) {
            AbstractTimeSourceKt.getTimeSource();
            cancelJobOnRejection(coroutineContext, e);
            Dispatchers.getIO().dispatch(coroutineContext, runnable);
        }
    }

    private final void cancelJobOnRejection(CoroutineContext coroutineContext, RejectedExecutionException rejectedExecutionException) {
        JobKt.cancel(coroutineContext, ExceptionsKt.CancellationException("The task was rejected", rejectedExecutionException));
    }

    public void close() {
        Executor executor2 = getExecutor();
        ExecutorService executorService = executor2 instanceof ExecutorService ? (ExecutorService) executor2 : null;
        if (executorService != null) {
            executorService.shutdown();
        }
    }

    public String toString() {
        return getExecutor().toString();
    }

    public boolean equals(Object obj) {
        return (obj instanceof ExecutorCoroutineDispatcherImpl) && ((ExecutorCoroutineDispatcherImpl) obj).getExecutor() == getExecutor();
    }

    public int hashCode() {
        return System.identityHashCode(getExecutor());
    }
}
