package kotlinx.coroutines.flow.internal;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineContextKt;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

public abstract class ChannelFlowOperator extends ChannelFlow {
    protected final Flow flow;

    public Object collect(FlowCollector flowCollector, Continuation continuation) {
        return collect$suspendImpl(this, flowCollector, continuation);
    }

    /* access modifiers changed from: protected */
    public Object collectTo(ProducerScope producerScope, Continuation continuation) {
        return collectTo$suspendImpl(this, producerScope, continuation);
    }

    /* access modifiers changed from: protected */
    public abstract Object flowCollect(FlowCollector flowCollector, Continuation continuation);

    public ChannelFlowOperator(Flow flow2, CoroutineContext coroutineContext, int i, BufferOverflow bufferOverflow) {
        super(coroutineContext, i, bufferOverflow);
        this.flow = flow2;
    }

    private final Object collectWithContextUndispatched(FlowCollector flowCollector, CoroutineContext coroutineContext, Continuation continuation) {
        Object withContextUndispatched$default = ChannelFlowKt.withContextUndispatched$default(coroutineContext, ChannelFlowKt.withUndispatchedContextCollector(flowCollector, continuation.getContext()), (Object) null, new ChannelFlowOperator$collectWithContextUndispatched$2(this, (Continuation) null), continuation, 4, (Object) null);
        return withContextUndispatched$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? withContextUndispatched$default : Unit.INSTANCE;
    }

    static /* synthetic */ Object collectTo$suspendImpl(ChannelFlowOperator channelFlowOperator, ProducerScope producerScope, Continuation continuation) {
        Object flowCollect = channelFlowOperator.flowCollect(new SendingCollector(producerScope), continuation);
        return flowCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? flowCollect : Unit.INSTANCE;
    }

    static /* synthetic */ Object collect$suspendImpl(ChannelFlowOperator channelFlowOperator, FlowCollector flowCollector, Continuation continuation) {
        if (channelFlowOperator.capacity == -3) {
            CoroutineContext context = continuation.getContext();
            CoroutineContext newCoroutineContext = CoroutineContextKt.newCoroutineContext(context, channelFlowOperator.context);
            if (Intrinsics.areEqual((Object) newCoroutineContext, (Object) context)) {
                Object flowCollect = channelFlowOperator.flowCollect(flowCollector, continuation);
                return flowCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? flowCollect : Unit.INSTANCE;
            }
            ContinuationInterceptor.Key key = ContinuationInterceptor.Key;
            if (Intrinsics.areEqual((Object) newCoroutineContext.get(key), (Object) context.get(key))) {
                Object collectWithContextUndispatched = channelFlowOperator.collectWithContextUndispatched(flowCollector, newCoroutineContext, continuation);
                return collectWithContextUndispatched == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? collectWithContextUndispatched : Unit.INSTANCE;
            }
        }
        Object collect = super.collect(flowCollector, continuation);
        return collect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? collect : Unit.INSTANCE;
    }

    public String toString() {
        return this.flow + " -> " + super.toString();
    }
}
