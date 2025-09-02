package kotlinx.coroutines.flow;

import androidx.customview.widget.ExploreByTouchHelper;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

final class FlowKt__ErrorsKt$catchImpl$2$emit$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ FlowKt__ErrorsKt$catchImpl$2 this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    FlowKt__ErrorsKt$catchImpl$2$emit$1(FlowKt__ErrorsKt$catchImpl$2 flowKt__ErrorsKt$catchImpl$2, Continuation continuation) {
        super(continuation);
        this.this$0 = flowKt__ErrorsKt$catchImpl$2;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= ExploreByTouchHelper.INVALID_ID;
        return this.this$0.emit((Object) null, this);
    }
}
