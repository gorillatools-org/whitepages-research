package kotlinx.coroutines.flow;

import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.channels.ReceiveChannel;

public abstract class FlowKt {
    public static final Flow buffer(Flow flow, int i, BufferOverflow bufferOverflow) {
        return FlowKt__ContextKt.buffer(flow, i, bufferOverflow);
    }

    public static final Flow callbackFlow(Function2 function2) {
        return FlowKt__BuildersKt.callbackFlow(function2);
    }

    /* renamed from: catch  reason: not valid java name */
    public static final Flow m924catch(Flow flow, Function3 function3) {
        return FlowKt__ErrorsKt.m925catch(flow, function3);
    }

    public static final Object catchImpl(Flow flow, FlowCollector flowCollector, Continuation continuation) {
        return FlowKt__ErrorsKt.catchImpl(flow, flowCollector, continuation);
    }

    public static final Flow conflate(Flow flow) {
        return FlowKt__ContextKt.conflate(flow);
    }

    public static final Flow dropWhile(Flow flow, Function2 function2) {
        return FlowKt__LimitKt.dropWhile(flow, function2);
    }

    public static final Object emitAll(FlowCollector flowCollector, ReceiveChannel receiveChannel, Continuation continuation) {
        return FlowKt__ChannelsKt.emitAll(flowCollector, receiveChannel, continuation);
    }

    public static final Object emitAll(FlowCollector flowCollector, Flow flow, Continuation continuation) {
        return FlowKt__CollectKt.emitAll(flowCollector, flow, continuation);
    }

    public static final void ensureActive(FlowCollector flowCollector) {
        FlowKt__EmittersKt.ensureActive(flowCollector);
    }

    public static final Object first(Flow flow, Continuation continuation) {
        return FlowKt__ReduceKt.first(flow, continuation);
    }

    public static final Flow flow(Function2 function2) {
        return FlowKt__BuildersKt.flow(function2);
    }

    public static final Flow onCompletion(Flow flow, Function3 function3) {
        return FlowKt__EmittersKt.onCompletion(flow, function3);
    }

    public static final Flow onStart(Flow flow, Function2 function2) {
        return FlowKt__EmittersKt.onStart(flow, function2);
    }

    public static final Flow takeWhile(Flow flow, Function2 function2) {
        return FlowKt__LimitKt.takeWhile(flow, function2);
    }
}
