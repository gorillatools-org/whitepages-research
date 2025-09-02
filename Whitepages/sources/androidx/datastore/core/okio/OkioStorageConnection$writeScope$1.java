package androidx.datastore.core.okio;

import androidx.customview.widget.ExploreByTouchHelper;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function2;

final class OkioStorageConnection$writeScope$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ OkioStorageConnection this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    OkioStorageConnection$writeScope$1(OkioStorageConnection okioStorageConnection, Continuation continuation) {
        super(continuation);
        this.this$0 = okioStorageConnection;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= ExploreByTouchHelper.INVALID_ID;
        return this.this$0.writeScope((Function2) null, this);
    }
}
