package kotlinx.coroutines.tasks;

import com.google.android.gms.tasks.CancellationTokenSource;
import com.google.android.gms.tasks.Task;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlinx.coroutines.CancellableContinuationImpl;

public abstract class TasksKt {
    public static final Object await(Task task, Continuation continuation) {
        return awaitImpl(task, (CancellationTokenSource) null, continuation);
    }

    private static final Object awaitImpl(Task task, CancellationTokenSource cancellationTokenSource, Continuation continuation) {
        if (task.isComplete()) {
            Exception exception = task.getException();
            if (exception != null) {
                throw exception;
            } else if (!task.isCanceled()) {
                return task.getResult();
            } else {
                throw new CancellationException("Task " + task + " was cancelled normally.");
            }
        } else {
            CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
            cancellableContinuationImpl.initCancellability();
            task.addOnCompleteListener((Executor) DirectExecutor.INSTANCE, new TasksKt$awaitImpl$2$1(cancellableContinuationImpl));
            if (cancellationTokenSource != null) {
                cancellableContinuationImpl.invokeOnCancellation(new TasksKt$awaitImpl$2$2(cancellationTokenSource));
            }
            Object result = cancellableContinuationImpl.getResult();
            if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                DebugProbesKt.probeCoroutineSuspended(continuation);
            }
            return result;
        }
    }
}
