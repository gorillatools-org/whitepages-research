package kotlinx.coroutines.flow;

import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.internal.ChannelFlowOperatorImpl;
import kotlinx.coroutines.internal.Symbol;

public abstract class SharedFlowKt {
    public static final Symbol NO_VALUE = new Symbol("NO_VALUE");

    public static final Flow fuseSharedFlow(SharedFlow sharedFlow, CoroutineContext coroutineContext, int i, BufferOverflow bufferOverflow) {
        if ((i == 0 || i == -3) && bufferOverflow == BufferOverflow.SUSPEND) {
            return sharedFlow;
        }
        return new ChannelFlowOperatorImpl(sharedFlow, coroutineContext, i, bufferOverflow);
    }
}
