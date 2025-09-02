package kotlin.collections;

import java.util.Collections;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import kotlin.Pair;
import kotlin.collections.builders.MapBuilder;
import kotlin.jvm.internal.Intrinsics;

abstract class MapsKt__MapsJVMKt extends MapsKt__MapWithDefaultKt {
    public static int mapCapacity(int i) {
        if (i < 0) {
            return i;
        }
        if (i < 3) {
            return i + 1;
        }
        if (i < 1073741824) {
            return (int) ((((float) i) / 0.75f) + 1.0f);
        }
        return Integer.MAX_VALUE;
    }

    public static Map mapOf(Pair pair) {
        Intrinsics.checkNotNullParameter(pair, "pair");
        Map singletonMap = Collections.singletonMap(pair.getFirst(), pair.getSecond());
        Intrinsics.checkNotNullExpressionValue(singletonMap, "singletonMap(...)");
        return singletonMap;
    }

    public static Map createMapBuilder() {
        return new MapBuilder();
    }

    public static Map createMapBuilder(int i) {
        return new MapBuilder(i);
    }

    public static Map build(Map map) {
        Intrinsics.checkNotNullParameter(map, "builder");
        return ((MapBuilder) map).build();
    }

    public static SortedMap toSortedMap(Map map) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        return new TreeMap(map);
    }

    public static final Map toSingletonMap(Map map) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        Map.Entry entry = (Map.Entry) map.entrySet().iterator().next();
        Map singletonMap = Collections.singletonMap(entry.getKey(), entry.getValue());
        Intrinsics.checkNotNullExpressionValue(singletonMap, "with(...)");
        return singletonMap;
    }
}
