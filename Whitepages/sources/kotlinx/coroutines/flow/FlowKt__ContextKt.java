package kotlinx.coroutines.flow;

import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.internal.ChannelFlowOperatorImpl;
import kotlinx.coroutines.flow.internal.FusibleFlow;

abstract /* synthetic */ class FlowKt__ContextKt {
    public static /* synthetic */ Flow buffer$default(Flow flow, int i, BufferOverflow bufferOverflow, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = -2;
        }
        if ((i2 & 2) != 0) {
            bufferOverflow = BufferOverflow.SUSPEND;
        }
        return FlowKt.buffer(flow, i, bufferOverflow);
    }

    public static final Flow buffer(Flow flow, int i, BufferOverflow bufferOverflow) {
        if (i < 0 && i != -2 && i != -1) {
            throw new IllegalArgumentException(("Buffer size should be non-negative, BUFFERED, or CONFLATED, but was " + i).toString());
        } else if (i != -1 || bufferOverflow == BufferOverflow.SUSPEND) {
            if (i == -1) {
                bufferOverflow = BufferOverflow.DROP_OLDEST;
                i = 0;
            }
            int i2 = i;
            BufferOverflow bufferOverflow2 = bufferOverflow;
            if (flow instanceof FusibleFlow) {
                return FusibleFlow.DefaultImpls.fuse$default((FusibleFlow) flow, (CoroutineContext) null, i2, bufferOverflow2, 1, (Object) null);
            }
            return new ChannelFlowOperatorImpl(flow, (CoroutineContext) null, i2, bufferOverflow2, 2, (DefaultConstructorMarker) null);
        } else {
            throw new IllegalArgumentException("CONFLATED capacity cannot be used with non-default onBufferOverflow");
        }
    }

    public static final Flow conflate(Flow flow) {
        return buffer$default(flow, -1, (BufferOverflow) null, 2, (Object) null);
    }
}
