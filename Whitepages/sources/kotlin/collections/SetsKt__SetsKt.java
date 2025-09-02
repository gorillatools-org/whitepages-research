package kotlin.collections;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.jvm.internal.Intrinsics;

abstract class SetsKt__SetsKt extends SetsKt__SetsJVMKt {
    public static Set emptySet() {
        return EmptySet.INSTANCE;
    }

    public static Set setOf(Object... objArr) {
        Intrinsics.checkNotNullParameter(objArr, "elements");
        return ArraysKt___ArraysKt.toSet(objArr);
    }

    public static Set mutableSetOf(Object... objArr) {
        Intrinsics.checkNotNullParameter(objArr, "elements");
        return (Set) ArraysKt___ArraysKt.toCollection(objArr, new LinkedHashSet(MapsKt.mapCapacity(objArr.length)));
    }

    public static HashSet hashSetOf(Object... objArr) {
        Intrinsics.checkNotNullParameter(objArr, "elements");
        return (HashSet) ArraysKt___ArraysKt.toCollection(objArr, new HashSet(MapsKt.mapCapacity(objArr.length)));
    }

    public static final Set optimizeReadOnlySet(Set set) {
        Intrinsics.checkNotNullParameter(set, "<this>");
        int size = set.size();
        if (size == 0) {
            return SetsKt.emptySet();
        }
        if (size != 1) {
            return set;
        }
        return SetsKt.setOf(set.iterator().next());
    }
}
