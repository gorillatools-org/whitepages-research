package androidx.datastore.preferences;

import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.properties.ReadOnlyProperty;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;

public abstract class PreferenceDataStoreDelegateKt {
    public static /* synthetic */ ReadOnlyProperty preferencesDataStore$default(String str, ReplaceFileCorruptionHandler replaceFileCorruptionHandler, Function1 function1, CoroutineScope coroutineScope, int i, Object obj) {
        if ((i & 2) != 0) {
            replaceFileCorruptionHandler = null;
        }
        if ((i & 4) != 0) {
            function1 = PreferenceDataStoreDelegateKt$preferencesDataStore$1.INSTANCE;
        }
        if ((i & 8) != 0) {
            coroutineScope = CoroutineScopeKt.CoroutineScope(Dispatchers.getIO().plus(SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null)));
        }
        return preferencesDataStore(str, replaceFileCorruptionHandler, function1, coroutineScope);
    }

    public static final ReadOnlyProperty preferencesDataStore(String str, ReplaceFileCorruptionHandler replaceFileCorruptionHandler, Function1 function1, CoroutineScope coroutineScope) {
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(function1, "produceMigrations");
        Intrinsics.checkNotNullParameter(coroutineScope, "scope");
        return new PreferenceDataStoreSingletonDelegate(str, replaceFileCorruptionHandler, function1, coroutineScope);
    }
}
