package kotlin.collections;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;

abstract class MapsKt__MapsKt extends MapsKt__MapsJVMKt {
    public static Map emptyMap() {
        EmptyMap emptyMap = EmptyMap.INSTANCE;
        Intrinsics.checkNotNull(emptyMap, "null cannot be cast to non-null type kotlin.collections.Map<K of kotlin.collections.MapsKt__MapsKt.emptyMap, V of kotlin.collections.MapsKt__MapsKt.emptyMap>");
        return emptyMap;
    }

    public static Map mapOf(Pair... pairArr) {
        Intrinsics.checkNotNullParameter(pairArr, "pairs");
        return pairArr.length > 0 ? toMap(pairArr, (Map) new LinkedHashMap(MapsKt.mapCapacity(pairArr.length))) : MapsKt.emptyMap();
    }

    public static Map mutableMapOf(Pair... pairArr) {
        Intrinsics.checkNotNullParameter(pairArr, "pairs");
        LinkedHashMap linkedHashMap = new LinkedHashMap(MapsKt.mapCapacity(pairArr.length));
        putAll((Map) linkedHashMap, pairArr);
        return linkedHashMap;
    }

    public static HashMap hashMapOf(Pair... pairArr) {
        Intrinsics.checkNotNullParameter(pairArr, "pairs");
        HashMap hashMap = new HashMap(MapsKt.mapCapacity(pairArr.length));
        putAll((Map) hashMap, pairArr);
        return hashMap;
    }

    public static Object getValue(Map map, Object obj) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        return MapsKt__MapWithDefaultKt.getOrImplicitDefaultNullable(map, obj);
    }

    public static final void putAll(Map map, Pair[] pairArr) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        Intrinsics.checkNotNullParameter(pairArr, "pairs");
        for (Pair pair : pairArr) {
            map.put(pair.component1(), pair.component2());
        }
    }

    public static final void putAll(Map map, Iterable iterable) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        Intrinsics.checkNotNullParameter(iterable, "pairs");
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            Pair pair = (Pair) it.next();
            map.put(pair.component1(), pair.component2());
        }
    }

    public static Map toMap(Iterable iterable) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        if (!(iterable instanceof Collection)) {
            return optimizeReadOnlyMap(toMap(iterable, (Map) new LinkedHashMap()));
        }
        Collection collection = (Collection) iterable;
        int size = collection.size();
        if (size == 0) {
            return MapsKt.emptyMap();
        }
        if (size != 1) {
            return toMap(iterable, (Map) new LinkedHashMap(MapsKt.mapCapacity(collection.size())));
        }
        return MapsKt.mapOf((Pair) (iterable instanceof List ? ((List) iterable).get(0) : iterable.iterator().next()));
    }

    public static final Map toMap(Iterable iterable, Map map) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(map, FirebaseAnalytics.Param.DESTINATION);
        putAll(map, iterable);
        return map;
    }

    public static final Map toMap(Pair[] pairArr, Map map) {
        Intrinsics.checkNotNullParameter(pairArr, "<this>");
        Intrinsics.checkNotNullParameter(map, FirebaseAnalytics.Param.DESTINATION);
        putAll(map, pairArr);
        return map;
    }

    public static Map toMap(Map map) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        int size = map.size();
        if (size == 0) {
            return MapsKt.emptyMap();
        }
        if (size != 1) {
            return MapsKt.toMutableMap(map);
        }
        return MapsKt__MapsJVMKt.toSingletonMap(map);
    }

    public static Map toMutableMap(Map map) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        return new LinkedHashMap(map);
    }

    public static Map plus(Map map, Pair pair) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        Intrinsics.checkNotNullParameter(pair, "pair");
        if (map.isEmpty()) {
            return MapsKt.mapOf(pair);
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(map);
        linkedHashMap.put(pair.getFirst(), pair.getSecond());
        return linkedHashMap;
    }

    public static final Map optimizeReadOnlyMap(Map map) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        int size = map.size();
        if (size == 0) {
            return MapsKt.emptyMap();
        }
        if (size != 1) {
            return map;
        }
        return MapsKt__MapsJVMKt.toSingletonMap(map);
    }
}
