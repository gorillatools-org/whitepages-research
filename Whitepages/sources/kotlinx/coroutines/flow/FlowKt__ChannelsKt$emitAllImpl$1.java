package kotlinx.coroutines.flow;

import androidx.customview.widget.ExploreByTouchHelper;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.channels.ReceiveChannel;

final class FlowKt__ChannelsKt$emitAllImpl$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    boolean Z$0;
    int label;
    /* synthetic */ Object result;

    FlowKt__ChannelsKt$emitAllImpl$1(Continuation continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= ExploreByTouchHelper.INVALID_ID;
        return FlowKt__ChannelsKt.emitAllImpl$FlowKt__ChannelsKt((FlowCollector) null, (ReceiveChannel) null, false, this);
    }
}
