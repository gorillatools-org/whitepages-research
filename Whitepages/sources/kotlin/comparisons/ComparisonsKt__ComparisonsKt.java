package kotlin.comparisons;

import java.util.Comparator;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

abstract class ComparisonsKt__ComparisonsKt {
    public static int compareValuesBy(Object obj, Object obj2, Function1... function1Arr) {
        Intrinsics.checkNotNullParameter(function1Arr, "selectors");
        if (function1Arr.length > 0) {
            return compareValuesByImpl$ComparisonsKt__ComparisonsKt(obj, obj2, function1Arr);
        }
        throw new IllegalArgumentException("Failed requirement.");
    }

    private static final int compareValuesByImpl$ComparisonsKt__ComparisonsKt(Object obj, Object obj2, Function1[] function1Arr) {
        for (Function1 function1 : function1Arr) {
            int compareValues = ComparisonsKt.compareValues((Comparable) function1.invoke(obj), (Comparable) function1.invoke(obj2));
            if (compareValues != 0) {
                return compareValues;
            }
        }
        return 0;
    }

    public static int compareValues(Comparable comparable, Comparable comparable2) {
        if (comparable == comparable2) {
            return 0;
        }
        if (comparable == null) {
            return -1;
        }
        if (comparable2 == null) {
            return 1;
        }
        return comparable.compareTo(comparable2);
    }

    public static Comparator naturalOrder() {
        NaturalOrderComparator naturalOrderComparator = NaturalOrderComparator.INSTANCE;
        Intrinsics.checkNotNull(naturalOrderComparator, "null cannot be cast to non-null type java.util.Comparator<T of kotlin.comparisons.ComparisonsKt__ComparisonsKt.naturalOrder>{ kotlin.TypeAliasesKt.Comparator<T of kotlin.comparisons.ComparisonsKt__ComparisonsKt.naturalOrder> }");
        return naturalOrderComparator;
    }

    public static Comparator reverseOrder() {
        ReverseOrderComparator reverseOrderComparator = ReverseOrderComparator.INSTANCE;
        Intrinsics.checkNotNull(reverseOrderComparator, "null cannot be cast to non-null type java.util.Comparator<T of kotlin.comparisons.ComparisonsKt__ComparisonsKt.reverseOrder>{ kotlin.TypeAliasesKt.Comparator<T of kotlin.comparisons.ComparisonsKt__ComparisonsKt.reverseOrder> }");
        return reverseOrderComparator;
    }
}
