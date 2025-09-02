package kotlinx.coroutines;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;

class DeferredCoroutine extends AbstractCoroutine implements Deferred {
    public Object await(Continuation continuation) {
        return await$suspendImpl(this, continuation);
    }

    public DeferredCoroutine(CoroutineContext coroutineContext, boolean z) {
        super(coroutineContext, true, z);
    }

    public Object getCompleted() {
        return getCompletedInternal$kotlinx_coroutines_core();
    }

    static /* synthetic */ Object await$suspendImpl(DeferredCoroutine deferredCoroutine, Continuation continuation) {
        Object awaitInternal = deferredCoroutine.awaitInternal(continuation);
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        return awaitInternal;
    }
}
