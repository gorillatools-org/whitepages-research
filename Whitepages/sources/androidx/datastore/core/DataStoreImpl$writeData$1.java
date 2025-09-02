package androidx.datastore.core;

import androidx.customview.widget.ExploreByTouchHelper;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

final class DataStoreImpl$writeData$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ DataStoreImpl this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    DataStoreImpl$writeData$1(DataStoreImpl dataStoreImpl, Continuation continuation) {
        super(continuation);
        this.this$0 = dataStoreImpl;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= ExploreByTouchHelper.INVALID_ID;
        return this.this$0.writeData$datastore_core_release((Object) null, false, this);
    }
}
