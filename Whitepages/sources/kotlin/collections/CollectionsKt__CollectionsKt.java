package kotlin.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;

abstract class CollectionsKt__CollectionsKt extends CollectionsKt__CollectionsJVMKt {
    public static final Collection asCollection(Object[] objArr) {
        Intrinsics.checkNotNullParameter(objArr, "<this>");
        return new ArrayAsCollection(objArr, false);
    }

    public static List emptyList() {
        return EmptyList.INSTANCE;
    }

    public static List listOf(Object... objArr) {
        Intrinsics.checkNotNullParameter(objArr, "elements");
        return objArr.length > 0 ? ArraysKt.asList(objArr) : CollectionsKt.emptyList();
    }

    public static List mutableListOf(Object... objArr) {
        Intrinsics.checkNotNullParameter(objArr, "elements");
        return objArr.length == 0 ? new ArrayList() : new ArrayList(new ArrayAsCollection(objArr, true));
    }

    public static ArrayList arrayListOf(Object... objArr) {
        Intrinsics.checkNotNullParameter(objArr, "elements");
        return objArr.length == 0 ? new ArrayList() : new ArrayList(new ArrayAsCollection(objArr, true));
    }

    public static List listOfNotNull(Object... objArr) {
        Intrinsics.checkNotNullParameter(objArr, "elements");
        return ArraysKt.filterNotNull(objArr);
    }

    public static IntRange getIndices(Collection collection) {
        Intrinsics.checkNotNullParameter(collection, "<this>");
        return new IntRange(0, collection.size() - 1);
    }

    public static int getLastIndex(List list) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        return list.size() - 1;
    }

    public static final List optimizeReadOnlyList(List list) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        int size = list.size();
        if (size == 0) {
            return CollectionsKt.emptyList();
        }
        if (size != 1) {
            return list;
        }
        return CollectionsKt.listOf(list.get(0));
    }

    public static /* synthetic */ int binarySearch$default(List list, Comparable comparable, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = list.size();
        }
        return binarySearch(list, comparable, i, i2);
    }

    public static final int binarySearch(List list, Comparable comparable, int i, int i2) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        rangeCheck$CollectionsKt__CollectionsKt(list.size(), i, i2);
        int i3 = i2 - 1;
        while (i <= i3) {
            int i4 = (i + i3) >>> 1;
            int compareValues = ComparisonsKt.compareValues((Comparable) list.get(i4), comparable);
            if (compareValues < 0) {
                i = i4 + 1;
            } else if (compareValues <= 0) {
                return i4;
            } else {
                i3 = i4 - 1;
            }
        }
        return -(i + 1);
    }

    private static final void rangeCheck$CollectionsKt__CollectionsKt(int i, int i2, int i3) {
        if (i2 > i3) {
            throw new IllegalArgumentException("fromIndex (" + i2 + ") is greater than toIndex (" + i3 + ").");
        } else if (i2 < 0) {
            throw new IndexOutOfBoundsException("fromIndex (" + i2 + ") is less than zero.");
        } else if (i3 > i) {
            throw new IndexOutOfBoundsException("toIndex (" + i3 + ") is greater than size (" + i + ").");
        }
    }

    public static void throwIndexOverflow() {
        throw new ArithmeticException("Index overflow has happened.");
    }

    public static void throwCountOverflow() {
        throw new ArithmeticException("Count overflow has happened.");
    }
}
