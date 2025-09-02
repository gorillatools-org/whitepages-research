package kotlinx.coroutines.internal;

import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.ThreadContextElement;

final class ThreadState {
    public final CoroutineContext context;
    private final ThreadContextElement[] elements;
    private int i;
    private final Object[] values;

    public ThreadState(CoroutineContext coroutineContext, int i2) {
        this.context = coroutineContext;
        this.values = new Object[i2];
        this.elements = new ThreadContextElement[i2];
    }

    public final void append(ThreadContextElement threadContextElement, Object obj) {
        Object[] objArr = this.values;
        int i2 = this.i;
        objArr[i2] = obj;
        ThreadContextElement[] threadContextElementArr = this.elements;
        this.i = i2 + 1;
        Intrinsics.checkNotNull(threadContextElement, "null cannot be cast to non-null type kotlinx.coroutines.ThreadContextElement<kotlin.Any?>");
        threadContextElementArr[i2] = threadContextElement;
    }

    public final void restore(CoroutineContext coroutineContext) {
        int length = this.elements.length - 1;
        if (length >= 0) {
            while (true) {
                int i2 = length - 1;
                ThreadContextElement threadContextElement = this.elements[length];
                Intrinsics.checkNotNull(threadContextElement);
                threadContextElement.restoreThreadContext(coroutineContext, this.values[length]);
                if (i2 >= 0) {
                    length = i2;
                } else {
                    return;
                }
            }
        }
    }
}
