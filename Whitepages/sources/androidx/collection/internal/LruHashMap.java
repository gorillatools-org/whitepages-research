package androidx.collection.internal;

import java.util.LinkedHashMap;
import java.util.Set;
import kotlin.jvm.internal.Intrinsics;

public final class LruHashMap {
    private final LinkedHashMap map;

    public LruHashMap(int i, float f) {
        this.map = new LinkedHashMap(i, f, true);
    }

    public final boolean isEmpty() {
        return this.map.isEmpty();
    }

    public final Set getEntries() {
        Set entrySet = this.map.entrySet();
        Intrinsics.checkNotNullExpressionValue(entrySet, "map.entries");
        return entrySet;
    }

    public final Object get(Object obj) {
        Intrinsics.checkNotNullParameter(obj, "key");
        return this.map.get(obj);
    }

    public final Object put(Object obj, Object obj2) {
        Intrinsics.checkNotNullParameter(obj, "key");
        Intrinsics.checkNotNullParameter(obj2, "value");
        return this.map.put(obj, obj2);
    }

    public final Object remove(Object obj) {
        Intrinsics.checkNotNullParameter(obj, "key");
        return this.map.remove(obj);
    }
}
