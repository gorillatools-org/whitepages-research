package kotlinx.coroutines.flow;

import androidx.customview.widget.ExploreByTouchHelper;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

final class StateFlowImpl$collect$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ StateFlowImpl this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    StateFlowImpl$collect$1(StateFlowImpl stateFlowImpl, Continuation continuation) {
        super(continuation);
        this.this$0 = stateFlowImpl;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= ExploreByTouchHelper.INVALID_ID;
        return this.this$0.collect((FlowCollector) null, this);
    }
}
