package androidx.datastore.preferences.core;

import androidx.datastore.core.DataStore;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;

public abstract class PreferencesKt {
    public static final Object edit(DataStore dataStore, Function2 function2, Continuation continuation) {
        return dataStore.updateData(new PreferencesKt$edit$2(function2, (Continuation) null), continuation);
    }
}
