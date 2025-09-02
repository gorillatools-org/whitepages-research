package androidx.datastore.core;

import androidx.customview.widget.ExploreByTouchHelper;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

final class RunOnce$runIfNeeded$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ RunOnce this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RunOnce$runIfNeeded$1(RunOnce runOnce, Continuation continuation) {
        super(continuation);
        this.this$0 = runOnce;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= ExploreByTouchHelper.INVALID_ID;
        return this.this$0.runIfNeeded(this);
    }
}
