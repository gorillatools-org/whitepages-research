package kotlinx.coroutines;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

final class CompletableDeferredImpl extends JobSupport implements CompletableDeferred {
    public boolean getOnCancelComplete$kotlinx_coroutines_core() {
        return true;
    }

    public CompletableDeferredImpl(Job job) {
        super(true);
        initParentJob(job);
    }

    public Object getCompleted() {
        return getCompletedInternal$kotlinx_coroutines_core();
    }

    public Object await(Continuation continuation) {
        Object awaitInternal = awaitInternal(continuation);
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        return awaitInternal;
    }

    public boolean complete(Object obj) {
        return makeCompleting$kotlinx_coroutines_core(obj);
    }

    public boolean completeExceptionally(Throwable th) {
        return makeCompleting$kotlinx_coroutines_core(new CompletedExceptionally(th, false, 2, (DefaultConstructorMarker) null));
    }
}
