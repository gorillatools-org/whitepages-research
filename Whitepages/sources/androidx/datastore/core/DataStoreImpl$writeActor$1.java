package androidx.datastore.core;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

final class DataStoreImpl$writeActor$1 extends Lambda implements Function1 {
    final /* synthetic */ DataStoreImpl this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    DataStoreImpl$writeActor$1(DataStoreImpl dataStoreImpl) {
        super(1);
        this.this$0 = dataStoreImpl;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Throwable) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(Throwable th) {
        if (th != null) {
            this.this$0.inMemoryCache.tryUpdate(new Final(th));
        }
        if (this.this$0.storageConnectionDelegate.isInitialized()) {
            this.this$0.getStorageConnection$datastore_core_release().close();
        }
    }
}
