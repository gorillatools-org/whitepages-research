package androidx.datastore.preferences.core;

import androidx.datastore.core.DataStore;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.Flow;

public final class PreferenceDataStore implements DataStore {
    private final DataStore delegate;

    public Flow getData() {
        return this.delegate.getData();
    }

    public PreferenceDataStore(DataStore dataStore) {
        Intrinsics.checkNotNullParameter(dataStore, "delegate");
        this.delegate = dataStore;
    }

    public Object updateData(Function2 function2, Continuation continuation) {
        return this.delegate.updateData(new PreferenceDataStore$updateData$2(function2, (Continuation) null), continuation);
    }
}
