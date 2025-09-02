package androidx.datastore.core;

import androidx.customview.widget.ExploreByTouchHelper;
import androidx.datastore.core.Message;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

final class DataStoreImpl$handleUpdate$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ DataStoreImpl this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    DataStoreImpl$handleUpdate$1(DataStoreImpl dataStoreImpl, Continuation continuation) {
        super(continuation);
        this.this$0 = dataStoreImpl;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= ExploreByTouchHelper.INVALID_ID;
        return this.this$0.handleUpdate((Message.Update) null, this);
    }
}
