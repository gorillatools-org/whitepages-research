package kotlinx.coroutines;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

public abstract class AbstractCoroutine extends JobSupport implements Job, Continuation, CoroutineScope {
    private final CoroutineContext context;

    /* access modifiers changed from: protected */
    public void onCancelled(Throwable th, boolean z) {
    }

    /* access modifiers changed from: protected */
    public void onCompleted(Object obj) {
    }

    public AbstractCoroutine(CoroutineContext coroutineContext, boolean z, boolean z2) {
        super(z2);
        if (z) {
            initParentJob((Job) coroutineContext.get(Job.Key));
        }
        this.context = coroutineContext.plus(this);
    }

    public final CoroutineContext getContext() {
        return this.context;
    }

    public CoroutineContext getCoroutineContext() {
        return this.context;
    }

    public boolean isActive() {
        return super.isActive();
    }

    /* access modifiers changed from: protected */
    public String cancellationExceptionMessage() {
        return DebugStringsKt.getClassSimpleName(this) + " was cancelled";
    }

    /* access modifiers changed from: protected */
    public final void onCompletionInternal(Object obj) {
        if (obj instanceof CompletedExceptionally) {
            CompletedExceptionally completedExceptionally = (CompletedExceptionally) obj;
            onCancelled(completedExceptionally.cause, completedExceptionally.getHandled());
            return;
        }
        onCompleted(obj);
    }

    public final void resumeWith(Object obj) {
        Object makeCompletingOnce$kotlinx_coroutines_core = makeCompletingOnce$kotlinx_coroutines_core(CompletionStateKt.toState$default(obj, (Function1) null, 1, (Object) null));
        if (makeCompletingOnce$kotlinx_coroutines_core != JobSupportKt.COMPLETING_WAITING_CHILDREN) {
            afterResume(makeCompletingOnce$kotlinx_coroutines_core);
        }
    }

    /* access modifiers changed from: protected */
    public void afterResume(Object obj) {
        afterCompletion(obj);
    }

    public final void handleOnCompletionException$kotlinx_coroutines_core(Throwable th) {
        CoroutineExceptionHandlerKt.handleCoroutineException(this.context, th);
    }

    public String nameString$kotlinx_coroutines_core() {
        String coroutineName = CoroutineContextKt.getCoroutineName(this.context);
        if (coroutineName == null) {
            return super.nameString$kotlinx_coroutines_core();
        }
        return '\"' + coroutineName + "\":" + super.nameString$kotlinx_coroutines_core();
    }

    public final void start(CoroutineStart coroutineStart, Object obj, Function2 function2) {
        coroutineStart.invoke(function2, obj, this);
    }
}
