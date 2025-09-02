package kotlin.comparisons;

import java.util.Comparator;
import kotlin.jvm.internal.Intrinsics;

final class NaturalOrderComparator implements Comparator {
    public static final NaturalOrderComparator INSTANCE = new NaturalOrderComparator();

    private NaturalOrderComparator() {
    }

    public int compare(Comparable comparable, Comparable comparable2) {
        Intrinsics.checkNotNullParameter(comparable, "a");
        Intrinsics.checkNotNullParameter(comparable2, "b");
        return comparable.compareTo(comparable2);
    }

    public final Comparator reversed() {
        return ReverseOrderComparator.INSTANCE;
    }
}
