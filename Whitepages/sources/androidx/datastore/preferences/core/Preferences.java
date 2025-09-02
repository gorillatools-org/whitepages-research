package androidx.datastore.preferences.core;

import java.util.Map;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

public abstract class Preferences {

    public static final class Pair {
    }

    public abstract Map asMap();

    public abstract Object get(Key key);

    public static final class Key {
        private final String name;

        public Key(String str) {
            Intrinsics.checkNotNullParameter(str, "name");
            this.name = str;
        }

        public final String getName() {
            return this.name;
        }

        public boolean equals(Object obj) {
            if (obj instanceof Key) {
                return Intrinsics.areEqual((Object) this.name, (Object) ((Key) obj).name);
            }
            return false;
        }

        public int hashCode() {
            return this.name.hashCode();
        }

        public String toString() {
            return this.name;
        }
    }

    public final MutablePreferences toMutablePreferences() {
        return new MutablePreferences(MapsKt.toMutableMap(asMap()), false);
    }

    public final Preferences toPreferences() {
        return new MutablePreferences(MapsKt.toMutableMap(asMap()), true);
    }
}
