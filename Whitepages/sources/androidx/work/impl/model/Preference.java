package androidx.work.impl.model;

import kotlin.jvm.internal.Intrinsics;

public final class Preference {
    private final String key;
    private final Long value;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Preference)) {
            return false;
        }
        Preference preference = (Preference) obj;
        return Intrinsics.areEqual((Object) this.key, (Object) preference.key) && Intrinsics.areEqual((Object) this.value, (Object) preference.value);
    }

    public int hashCode() {
        int hashCode = this.key.hashCode() * 31;
        Long l = this.value;
        return hashCode + (l == null ? 0 : l.hashCode());
    }

    public String toString() {
        return "Preference(key=" + this.key + ", value=" + this.value + ')';
    }

    public Preference(String str, Long l) {
        Intrinsics.checkNotNullParameter(str, "key");
        this.key = str;
        this.value = l;
    }

    public final String getKey() {
        return this.key;
    }

    public final Long getValue() {
        return this.value;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public Preference(String str, boolean z) {
        this(str, Long.valueOf(z ? 1 : 0));
        Intrinsics.checkNotNullParameter(str, "key");
    }
}
