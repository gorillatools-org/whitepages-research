package androidx.datastore.preferences;

import android.content.Context;
import androidx.datastore.core.DataStore;
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler;
import androidx.datastore.preferences.core.PreferenceDataStoreFactory;
import java.util.List;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.properties.ReadOnlyProperty;
import kotlin.reflect.KProperty;
import kotlinx.coroutines.CoroutineScope;

public final class PreferenceDataStoreSingletonDelegate implements ReadOnlyProperty {
    private volatile DataStore INSTANCE;
    private final ReplaceFileCorruptionHandler corruptionHandler;
    private final Object lock = new Object();
    /* access modifiers changed from: private */
    public final String name;
    private final Function1 produceMigrations;
    private final CoroutineScope scope;

    public PreferenceDataStoreSingletonDelegate(String str, ReplaceFileCorruptionHandler replaceFileCorruptionHandler, Function1 function1, CoroutineScope coroutineScope) {
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(function1, "produceMigrations");
        Intrinsics.checkNotNullParameter(coroutineScope, "scope");
        this.name = str;
        this.corruptionHandler = replaceFileCorruptionHandler;
        this.produceMigrations = function1;
        this.scope = coroutineScope;
    }

    public DataStore getValue(Context context, KProperty kProperty) {
        DataStore dataStore;
        Intrinsics.checkNotNullParameter(context, "thisRef");
        Intrinsics.checkNotNullParameter(kProperty, "property");
        DataStore dataStore2 = this.INSTANCE;
        if (dataStore2 != null) {
            return dataStore2;
        }
        synchronized (this.lock) {
            try {
                if (this.INSTANCE == null) {
                    Context applicationContext = context.getApplicationContext();
                    PreferenceDataStoreFactory preferenceDataStoreFactory = PreferenceDataStoreFactory.INSTANCE;
                    ReplaceFileCorruptionHandler replaceFileCorruptionHandler = this.corruptionHandler;
                    Function1 function1 = this.produceMigrations;
                    Intrinsics.checkNotNullExpressionValue(applicationContext, "applicationContext");
                    this.INSTANCE = preferenceDataStoreFactory.create(replaceFileCorruptionHandler, (List) function1.invoke(applicationContext), this.scope, (Function0) new PreferenceDataStoreSingletonDelegate$getValue$1$1(applicationContext, this));
                }
                dataStore = this.INSTANCE;
                Intrinsics.checkNotNull(dataStore);
            } catch (Throwable th) {
                throw th;
            }
        }
        return dataStore;
    }
}
