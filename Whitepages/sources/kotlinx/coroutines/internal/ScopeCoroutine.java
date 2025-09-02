package kotlinx.coroutines.internal;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.AbstractCoroutine;
import kotlinx.coroutines.CompletionStateKt;

public class ScopeCoroutine extends AbstractCoroutine implements CoroutineStackFrame {
    public final Continuation uCont;

    /* access modifiers changed from: protected */
    public final boolean isScopedCoroutine() {
        return true;
    }

    public ScopeCoroutine(CoroutineContext coroutineContext, Continuation continuation) {
        super(coroutineContext, true, true);
        this.uCont = continuation;
    }

    public final CoroutineStackFrame getCallerFrame() {
        Continuation continuation = this.uCont;
        if (continuation instanceof CoroutineStackFrame) {
            return (CoroutineStackFrame) continuation;
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public void afterCompletion(Object obj) {
        DispatchedContinuationKt.resumeCancellableWith$default(IntrinsicsKt.intercepted(this.uCont), CompletionStateKt.recoverResult(obj, this.uCont), (Function1) null, 2, (Object) null);
    }

    /* access modifiers changed from: protected */
    public void afterResume(Object obj) {
        Continuation continuation = this.uCont;
        continuation.resumeWith(CompletionStateKt.recoverResult(obj, continuation));
    }
}
