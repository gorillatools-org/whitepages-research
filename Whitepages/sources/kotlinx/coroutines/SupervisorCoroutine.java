package kotlinx.coroutines;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.internal.ScopeCoroutine;

final class SupervisorCoroutine extends ScopeCoroutine {
    public boolean childCancelled(Throwable th) {
        return false;
    }

    public SupervisorCoroutine(CoroutineContext coroutineContext, Continuation continuation) {
        super(coroutineContext, continuation);
    }
}
