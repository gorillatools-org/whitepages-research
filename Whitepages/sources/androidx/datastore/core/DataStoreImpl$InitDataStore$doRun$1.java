package androidx.datastore.core;

import androidx.customview.widget.ExploreByTouchHelper;
import androidx.datastore.core.DataStoreImpl;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

final class DataStoreImpl$InitDataStore$doRun$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ DataStoreImpl.InitDataStore this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    DataStoreImpl$InitDataStore$doRun$1(DataStoreImpl.InitDataStore initDataStore, Continuation continuation) {
        super(continuation);
        this.this$0 = initDataStore;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= ExploreByTouchHelper.INVALID_ID;
        return this.this$0.doRun(this);
    }
}
