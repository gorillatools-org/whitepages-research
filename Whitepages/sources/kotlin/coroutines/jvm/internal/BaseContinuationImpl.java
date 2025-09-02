package kotlin.coroutines.jvm.internal;

import java.io.Serializable;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.Intrinsics;

public abstract class BaseContinuationImpl implements Continuation, CoroutineStackFrame, Serializable {
    private final Continuation completion;

    /* access modifiers changed from: protected */
    public abstract Object invokeSuspend(Object obj);

    /* access modifiers changed from: protected */
    public void releaseIntercepted() {
    }

    public BaseContinuationImpl(Continuation continuation) {
        this.completion = continuation;
    }

    public final Continuation getCompletion() {
        return this.completion;
    }

    public final void resumeWith(Object obj) {
        Continuation continuation = this;
        while (true) {
            DebugProbesKt.probeCoroutineResumed(continuation);
            BaseContinuationImpl baseContinuationImpl = (BaseContinuationImpl) continuation;
            Continuation continuation2 = baseContinuationImpl.completion;
            Intrinsics.checkNotNull(continuation2);
            try {
                Object invokeSuspend = baseContinuationImpl.invokeSuspend(obj);
                if (invokeSuspend != IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                    obj = Result.m866constructorimpl(invokeSuspend);
                    baseContinuationImpl.releaseIntercepted();
                    if (continuation2 instanceof BaseContinuationImpl) {
                        continuation = continuation2;
                    } else {
                        continuation2.resumeWith(obj);
                        return;
                    }
                } else {
                    return;
                }
            } catch (Throwable th) {
                Result.Companion companion = Result.Companion;
                obj = Result.m866constructorimpl(ResultKt.createFailure(th));
            }
        }
    }

    public Continuation create(Continuation continuation) {
        Intrinsics.checkNotNullParameter(continuation, "completion");
        throw new UnsupportedOperationException("create(Continuation) has not been overridden");
    }

    public Continuation create(Object obj, Continuation continuation) {
        Intrinsics.checkNotNullParameter(continuation, "completion");
        throw new UnsupportedOperationException("create(Any?;Continuation) has not been overridden");
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Continuation at ");
        Object stackTraceElement = getStackTraceElement();
        if (stackTraceElement == null) {
            stackTraceElement = getClass().getName();
        }
        sb.append(stackTraceElement);
        return sb.toString();
    }

    public CoroutineStackFrame getCallerFrame() {
        Continuation continuation = this.completion;
        if (continuation instanceof CoroutineStackFrame) {
            return (CoroutineStackFrame) continuation;
        }
        return null;
    }

    public StackTraceElement getStackTraceElement() {
        return DebugMetadataKt.getStackTraceElement(this);
    }
}
