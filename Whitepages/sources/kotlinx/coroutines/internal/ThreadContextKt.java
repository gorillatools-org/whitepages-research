package kotlinx.coroutines.internal;

import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.ThreadContextElement;

public abstract class ThreadContextKt {
    public static final Symbol NO_THREAD_ELEMENTS = new Symbol("NO_THREAD_ELEMENTS");
    private static final Function2 countAll = ThreadContextKt$countAll$1.INSTANCE;
    private static final Function2 findOne = ThreadContextKt$findOne$1.INSTANCE;
    private static final Function2 updateState = ThreadContextKt$updateState$1.INSTANCE;

    public static final Object threadContextElements(CoroutineContext coroutineContext) {
        Object fold = coroutineContext.fold(0, countAll);
        Intrinsics.checkNotNull(fold);
        return fold;
    }

    public static final Object updateThreadContext(CoroutineContext coroutineContext, Object obj) {
        if (obj == null) {
            obj = threadContextElements(coroutineContext);
        }
        if (obj == 0) {
            return NO_THREAD_ELEMENTS;
        }
        if (obj instanceof Integer) {
            return coroutineContext.fold(new ThreadState(coroutineContext, ((Number) obj).intValue()), updateState);
        }
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlinx.coroutines.ThreadContextElement<kotlin.Any?>");
        return ((ThreadContextElement) obj).updateThreadContext(coroutineContext);
    }

    public static final void restoreThreadContext(CoroutineContext coroutineContext, Object obj) {
        if (obj != NO_THREAD_ELEMENTS) {
            if (obj instanceof ThreadState) {
                ((ThreadState) obj).restore(coroutineContext);
                return;
            }
            Object fold = coroutineContext.fold((Object) null, findOne);
            Intrinsics.checkNotNull(fold, "null cannot be cast to non-null type kotlinx.coroutines.ThreadContextElement<kotlin.Any?>");
            ((ThreadContextElement) fold).restoreThreadContext(coroutineContext, obj);
        }
    }
}
