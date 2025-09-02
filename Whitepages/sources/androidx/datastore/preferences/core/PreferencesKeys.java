package androidx.datastore.preferences.core;

import androidx.datastore.preferences.core.Preferences;
import kotlin.jvm.internal.Intrinsics;

public abstract class PreferencesKeys {
    public static final Preferences.Key intKey(String str) {
        Intrinsics.checkNotNullParameter(str, "name");
        return new Preferences.Key(str);
    }

    public static final Preferences.Key doubleKey(String str) {
        Intrinsics.checkNotNullParameter(str, "name");
        return new Preferences.Key(str);
    }

    public static final Preferences.Key stringKey(String str) {
        Intrinsics.checkNotNullParameter(str, "name");
        return new Preferences.Key(str);
    }

    public static final Preferences.Key booleanKey(String str) {
        Intrinsics.checkNotNullParameter(str, "name");
        return new Preferences.Key(str);
    }

    public static final Preferences.Key floatKey(String str) {
        Intrinsics.checkNotNullParameter(str, "name");
        return new Preferences.Key(str);
    }

    public static final Preferences.Key longKey(String str) {
        Intrinsics.checkNotNullParameter(str, "name");
        return new Preferences.Key(str);
    }

    public static final Preferences.Key stringSetKey(String str) {
        Intrinsics.checkNotNullParameter(str, "name");
        return new Preferences.Key(str);
    }

    public static final Preferences.Key byteArrayKey(String str) {
        Intrinsics.checkNotNullParameter(str, "name");
        return new Preferences.Key(str);
    }
}
