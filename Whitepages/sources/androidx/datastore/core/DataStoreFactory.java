package androidx.datastore.core;

import androidx.datastore.core.handlers.NoOpCorruptionHandler;
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

public final class DataStoreFactory {
    public static final DataStoreFactory INSTANCE = new DataStoreFactory();

    private DataStoreFactory() {
    }

    public final DataStore create(Storage storage, ReplaceFileCorruptionHandler replaceFileCorruptionHandler, List list, CoroutineScope coroutineScope) {
        Intrinsics.checkNotNullParameter(storage, "storage");
        Intrinsics.checkNotNullParameter(list, "migrations");
        Intrinsics.checkNotNullParameter(coroutineScope, "scope");
        CorruptionHandler corruptionHandler = replaceFileCorruptionHandler;
        if (replaceFileCorruptionHandler == null) {
            corruptionHandler = new NoOpCorruptionHandler();
        }
        return new DataStoreImpl(storage, CollectionsKt.listOf(DataMigrationInitializer.Companion.getInitializer(list)), corruptionHandler, coroutineScope);
    }
}
