package androidx.datastore.core.okio;

import androidx.customview.widget.ExploreByTouchHelper;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function3;

final class OkioStorageConnection$readScope$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    boolean Z$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ OkioStorageConnection this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    OkioStorageConnection$readScope$1(OkioStorageConnection okioStorageConnection, Continuation continuation) {
        super(continuation);
        this.this$0 = okioStorageConnection;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= ExploreByTouchHelper.INVALID_ID;
        return this.this$0.readScope((Function3) null, this);
    }
}
