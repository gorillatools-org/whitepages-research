package androidx.datastore.core;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

final class DataStoreImpl$storageConnectionDelegate$1 extends Lambda implements Function0 {
    final /* synthetic */ DataStoreImpl this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    DataStoreImpl$storageConnectionDelegate$1(DataStoreImpl dataStoreImpl) {
        super(0);
        this.this$0 = dataStoreImpl;
    }

    public final StorageConnection invoke() {
        return this.this$0.storage.createConnection();
    }
}
