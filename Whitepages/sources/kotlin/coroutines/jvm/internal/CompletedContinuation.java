package kotlin.coroutines.jvm.internal;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;

public final class CompletedContinuation implements Continuation {
    public static final CompletedContinuation INSTANCE = new CompletedContinuation();

    private CompletedContinuation() {
    }

    public CoroutineContext getContext() {
        throw new IllegalStateException("This continuation is already complete");
    }

    public void resumeWith(Object obj) {
        throw new IllegalStateException("This continuation is already complete");
    }

    public String toString() {
        return "This continuation is already complete";
    }
}
