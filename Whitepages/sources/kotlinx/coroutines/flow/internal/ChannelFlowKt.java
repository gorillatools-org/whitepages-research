package kotlinx.coroutines.flow.internal;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.internal.ThreadContextKt;

public abstract class ChannelFlowKt {
    /* JADX INFO: finally extract failed */
    public static final Object withContextUndispatched(CoroutineContext coroutineContext, Object obj, Object obj2, Function2 function2, Continuation continuation) {
        Object updateThreadContext = ThreadContextKt.updateThreadContext(coroutineContext, obj2);
        try {
            Object invoke = ((Function2) TypeIntrinsics.beforeCheckcastToFunctionOfArity(function2, 2)).invoke(obj, new StackFrameContinuation(continuation, coroutineContext));
            ThreadContextKt.restoreThreadContext(coroutineContext, updateThreadContext);
            if (invoke == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                DebugProbesKt.probeCoroutineSuspended(continuation);
            }
            return invoke;
        } catch (Throwable th) {
            ThreadContextKt.restoreThreadContext(coroutineContext, updateThreadContext);
            throw th;
        }
    }

    /* access modifiers changed from: private */
    public static final FlowCollector withUndispatchedContextCollector(FlowCollector flowCollector, CoroutineContext coroutineContext) {
        return flowCollector instanceof SendingCollector ? flowCollector : new UndispatchedContextCollector(flowCollector, coroutineContext);
    }

    public static /* synthetic */ Object withContextUndispatched$default(CoroutineContext coroutineContext, Object obj, Object obj2, Function2 function2, Continuation continuation, int i, Object obj3) {
        if ((i & 4) != 0) {
            obj2 = ThreadContextKt.threadContextElements(coroutineContext);
        }
        return withContextUndispatched(coroutineContext, obj, obj2, function2, continuation);
    }
}
