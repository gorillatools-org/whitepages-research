package kotlinx.coroutines.flow.internal;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;

final class StackFrameContinuation implements Continuation, CoroutineStackFrame {
    private final CoroutineContext context;
    private final Continuation uCont;

    public StackFrameContinuation(Continuation continuation, CoroutineContext coroutineContext) {
        this.uCont = continuation;
        this.context = coroutineContext;
    }

    public CoroutineContext getContext() {
        return this.context;
    }

    public CoroutineStackFrame getCallerFrame() {
        Continuation continuation = this.uCont;
        if (continuation instanceof CoroutineStackFrame) {
            return (CoroutineStackFrame) continuation;
        }
        return null;
    }

    public void resumeWith(Object obj) {
        this.uCont.resumeWith(obj);
    }
}
