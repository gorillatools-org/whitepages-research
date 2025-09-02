package kotlin.collections;

import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;

abstract class CollectionsKt__ReversedViewsKt extends CollectionsKt__MutableCollectionsKt {
    /* access modifiers changed from: private */
    public static final int reverseElementIndex$CollectionsKt__ReversedViewsKt(List list, int i) {
        if (i >= 0 && i <= CollectionsKt.getLastIndex(list)) {
            return CollectionsKt.getLastIndex(list) - i;
        }
        throw new IndexOutOfBoundsException("Element index " + i + " must be in range [" + new IntRange(0, CollectionsKt.getLastIndex(list)) + "].");
    }

    /* access modifiers changed from: private */
    public static final int reversePositionIndex$CollectionsKt__ReversedViewsKt(List list, int i) {
        if (i >= 0 && i <= list.size()) {
            return list.size() - i;
        }
        throw new IndexOutOfBoundsException("Position index " + i + " must be in range [" + new IntRange(0, list.size()) + "].");
    }

    /* access modifiers changed from: private */
    public static final int reverseIteratorIndex$CollectionsKt__ReversedViewsKt(List list, int i) {
        return CollectionsKt.getLastIndex(list) - i;
    }

    public static List asReversed(List list) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        return new ReversedListReadOnly(list);
    }

    public static List asReversedMutable(List list) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        return new ReversedList(list);
    }
}
