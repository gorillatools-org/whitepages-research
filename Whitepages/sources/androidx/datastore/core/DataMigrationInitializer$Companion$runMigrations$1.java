package androidx.datastore.core;

import androidx.customview.widget.ExploreByTouchHelper;
import androidx.datastore.core.DataMigrationInitializer;
import java.util.List;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

final class DataMigrationInitializer$Companion$runMigrations$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ DataMigrationInitializer.Companion this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    DataMigrationInitializer$Companion$runMigrations$1(DataMigrationInitializer.Companion companion, Continuation continuation) {
        super(continuation);
        this.this$0 = companion;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= ExploreByTouchHelper.INVALID_ID;
        return this.this$0.runMigrations((List) null, (InitializerApi) null, this);
    }
}
