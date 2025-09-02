package androidx.datastore.core;

import androidx.customview.widget.ExploreByTouchHelper;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function2;

final class DataStoreImpl$InitDataStore$doRun$initData$1$api$1$updateData$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ DataStoreImpl$InitDataStore$doRun$initData$1$api$1 this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    DataStoreImpl$InitDataStore$doRun$initData$1$api$1$updateData$1(DataStoreImpl$InitDataStore$doRun$initData$1$api$1 dataStoreImpl$InitDataStore$doRun$initData$1$api$1, Continuation continuation) {
        super(continuation);
        this.this$0 = dataStoreImpl$InitDataStore$doRun$initData$1$api$1;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= ExploreByTouchHelper.INVALID_ID;
        return this.this$0.updateData((Function2) null, this);
    }
}
