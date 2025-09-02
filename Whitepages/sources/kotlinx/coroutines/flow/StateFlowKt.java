package kotlinx.coroutines.flow;

import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.internal.NullSurrogateKt;
import kotlinx.coroutines.internal.Symbol;

public abstract class StateFlowKt {
    /* access modifiers changed from: private */
    public static final Symbol NONE = new Symbol("NONE");
    /* access modifiers changed from: private */
    public static final Symbol PENDING = new Symbol("PENDING");

    public static final MutableStateFlow MutableStateFlow(Object obj) {
        if (obj == null) {
            obj = NullSurrogateKt.NULL;
        }
        return new StateFlowImpl(obj);
    }

    public static final Flow fuseStateFlow(StateFlow stateFlow, CoroutineContext coroutineContext, int i, BufferOverflow bufferOverflow) {
        if (((i < 0 || i >= 2) && i != -2) || bufferOverflow != BufferOverflow.DROP_OLDEST) {
            return SharedFlowKt.fuseSharedFlow(stateFlow, coroutineContext, i, bufferOverflow);
        }
        return stateFlow;
    }
}
