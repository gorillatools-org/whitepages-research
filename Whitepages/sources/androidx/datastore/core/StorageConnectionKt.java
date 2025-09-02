package androidx.datastore.core;

import kotlin.coroutines.Continuation;

public abstract class StorageConnectionKt {
    public static final Object readData(StorageConnection storageConnection, Continuation continuation) {
        return storageConnection.readScope(new StorageConnectionKt$readData$2((Continuation) null), continuation);
    }
}
