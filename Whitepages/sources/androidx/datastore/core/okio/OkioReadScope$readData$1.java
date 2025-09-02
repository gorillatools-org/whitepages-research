package androidx.datastore.core.okio;

import androidx.customview.widget.ExploreByTouchHelper;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

final class OkioReadScope$readData$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ OkioReadScope this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    OkioReadScope$readData$1(OkioReadScope okioReadScope, Continuation continuation) {
        super(continuation);
        this.this$0 = okioReadScope;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= ExploreByTouchHelper.INVALID_ID;
        return OkioReadScope.readData$suspendImpl(this.this$0, this);
    }
}
