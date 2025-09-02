package kotlin.comparisons;

import java.util.Comparator;
import kotlin.jvm.internal.Intrinsics;

final class ReverseOrderComparator implements Comparator {
    public static final ReverseOrderComparator INSTANCE = new ReverseOrderComparator();

    private ReverseOrderComparator() {
    }

    public int compare(Comparable comparable, Comparable comparable2) {
        Intrinsics.checkNotNullParameter(comparable, "a");
        Intrinsics.checkNotNullParameter(comparable2, "b");
        return comparable2.compareTo(comparable);
    }

    public final Comparator reversed() {
        return NaturalOrderComparator.INSTANCE;
    }
}
