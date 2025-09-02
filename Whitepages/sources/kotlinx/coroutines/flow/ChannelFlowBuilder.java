package kotlinx.coroutines.flow;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.flow.internal.ChannelFlow;

abstract class ChannelFlowBuilder extends ChannelFlow {
    private final Function2 block;

    /* access modifiers changed from: protected */
    public Object collectTo(ProducerScope producerScope, Continuation continuation) {
        return collectTo$suspendImpl(this, producerScope, continuation);
    }

    public ChannelFlowBuilder(Function2 function2, CoroutineContext coroutineContext, int i, BufferOverflow bufferOverflow) {
        super(coroutineContext, i, bufferOverflow);
        this.block = function2;
    }

    static /* synthetic */ Object collectTo$suspendImpl(ChannelFlowBuilder channelFlowBuilder, ProducerScope producerScope, Continuation continuation) {
        Object invoke = channelFlowBuilder.block.invoke(producerScope, continuation);
        return invoke == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? invoke : Unit.INSTANCE;
    }

    public String toString() {
        return "block[" + this.block + "] -> " + super.toString();
    }
}
