package kotlinx.coroutines.flow;

import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.channels.BufferOverflow;

abstract /* synthetic */ class FlowKt__BuildersKt {
    public static final Flow flow(Function2 function2) {
        return new SafeFlow(function2);
    }

    public static final Flow callbackFlow(Function2 function2) {
        return new CallbackFlowBuilder(function2, (CoroutineContext) null, 0, (BufferOverflow) null, 14, (DefaultConstructorMarker) null);
    }
}
