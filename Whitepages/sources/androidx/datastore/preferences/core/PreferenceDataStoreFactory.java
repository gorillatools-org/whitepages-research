package androidx.datastore.preferences.core;

import androidx.datastore.core.DataStore;
import androidx.datastore.core.DataStoreFactory;
import androidx.datastore.core.Storage;
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler;
import androidx.datastore.core.okio.OkioStorage;
import java.util.List;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import okio.FileSystem;

public final class PreferenceDataStoreFactory {
    public static final PreferenceDataStoreFactory INSTANCE = new PreferenceDataStoreFactory();

    private PreferenceDataStoreFactory() {
    }

    public final DataStore create(ReplaceFileCorruptionHandler replaceFileCorruptionHandler, List list, CoroutineScope coroutineScope, Function0 function0) {
        Intrinsics.checkNotNullParameter(list, "migrations");
        Intrinsics.checkNotNullParameter(coroutineScope, "scope");
        Intrinsics.checkNotNullParameter(function0, "produceFile");
        return new PreferenceDataStore(create((Storage) new OkioStorage(FileSystem.SYSTEM, PreferencesSerializer.INSTANCE, (Function2) null, new PreferenceDataStoreFactory$create$delegate$1(function0), 4, (DefaultConstructorMarker) null), replaceFileCorruptionHandler, list, coroutineScope));
    }

    public final DataStore create(Storage storage, ReplaceFileCorruptionHandler replaceFileCorruptionHandler, List list, CoroutineScope coroutineScope) {
        Intrinsics.checkNotNullParameter(storage, "storage");
        Intrinsics.checkNotNullParameter(list, "migrations");
        Intrinsics.checkNotNullParameter(coroutineScope, "scope");
        return new PreferenceDataStore(DataStoreFactory.INSTANCE.create(storage, replaceFileCorruptionHandler, list, coroutineScope));
    }
}
