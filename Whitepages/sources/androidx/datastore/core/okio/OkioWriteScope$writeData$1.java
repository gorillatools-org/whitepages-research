package androidx.datastore.core.okio;

import androidx.customview.widget.ExploreByTouchHelper;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

final class OkioWriteScope$writeData$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ OkioWriteScope this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    OkioWriteScope$writeData$1(OkioWriteScope okioWriteScope, Continuation continuation) {
        super(continuation);
        this.this$0 = okioWriteScope;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= ExploreByTouchHelper.INVALID_ID;
        return this.this$0.writeData((Object) null, this);
    }
}
