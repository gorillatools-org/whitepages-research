package kotlinx.coroutines.flow.internal;

import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;

public final class DownstreamExceptionContext implements CoroutineContext {
    private final /* synthetic */ CoroutineContext $$delegate_0;
    public final Throwable e;

    public Object fold(Object obj, Function2 function2) {
        return this.$$delegate_0.fold(obj, function2);
    }

    public CoroutineContext.Element get(CoroutineContext.Key key) {
        return this.$$delegate_0.get(key);
    }

    public CoroutineContext minusKey(CoroutineContext.Key key) {
        return this.$$delegate_0.minusKey(key);
    }

    public CoroutineContext plus(CoroutineContext coroutineContext) {
        return this.$$delegate_0.plus(coroutineContext);
    }

    public DownstreamExceptionContext(Throwable th, CoroutineContext coroutineContext) {
        this.e = th;
        this.$$delegate_0 = coroutineContext;
    }
}
