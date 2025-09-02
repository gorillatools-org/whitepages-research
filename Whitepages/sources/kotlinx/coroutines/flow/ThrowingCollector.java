package kotlinx.coroutines.flow;

import kotlin.coroutines.Continuation;

public final class ThrowingCollector implements FlowCollector {
    public final Throwable e;

    public ThrowingCollector(Throwable th) {
        this.e = th;
    }

    public Object emit(Object obj, Continuation continuation) {
        throw this.e;
    }
}
