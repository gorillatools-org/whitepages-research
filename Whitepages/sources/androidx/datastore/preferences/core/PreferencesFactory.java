package androidx.datastore.preferences.core;

import androidx.datastore.preferences.core.Preferences;
import java.util.Arrays;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public abstract class PreferencesFactory {
    public static final Preferences createEmpty() {
        return new MutablePreferences((Map) null, true, 1, (DefaultConstructorMarker) null);
    }

    public static final MutablePreferences createMutable(Preferences.Pair... pairArr) {
        Intrinsics.checkNotNullParameter(pairArr, "pairs");
        MutablePreferences mutablePreferences = new MutablePreferences((Map) null, false, 1, (DefaultConstructorMarker) null);
        mutablePreferences.putAll((Preferences.Pair[]) Arrays.copyOf(pairArr, pairArr.length));
        return mutablePreferences;
    }
}
