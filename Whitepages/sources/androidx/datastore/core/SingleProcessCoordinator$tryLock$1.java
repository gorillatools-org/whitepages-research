package androidx.datastore.core;

import androidx.customview.widget.ExploreByTouchHelper;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function2;

final class SingleProcessCoordinator$tryLock$1 extends ContinuationImpl {
    Object L$0;
    boolean Z$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ SingleProcessCoordinator this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SingleProcessCoordinator$tryLock$1(SingleProcessCoordinator singleProcessCoordinator, Continuation continuation) {
        super(continuation);
        this.this$0 = singleProcessCoordinator;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= ExploreByTouchHelper.INVALID_ID;
        return this.this$0.tryLock((Function2) null, this);
    }
}
