package androidx.datastore.preferences.core;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

public abstract class Actual_jvmKt {
    public static final Map immutableMap(Map map) {
        Intrinsics.checkNotNullParameter(map, "map");
        Map unmodifiableMap = Collections.unmodifiableMap(map);
        Intrinsics.checkNotNullExpressionValue(unmodifiableMap, "unmodifiableMap(map)");
        return unmodifiableMap;
    }

    public static final Set immutableCopyOfSet(Set set) {
        Intrinsics.checkNotNullParameter(set, "set");
        Set unmodifiableSet = Collections.unmodifiableSet(CollectionsKt.toSet(set));
        Intrinsics.checkNotNullExpressionValue(unmodifiableSet, "unmodifiableSet(set.toSet())");
        return unmodifiableSet;
    }
}
