package kotlin.collections;

import java.util.Collections;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

abstract class CollectionsKt___CollectionsJvmKt extends CollectionsKt__ReversedViewsKt {
    public static final void reverse(List list) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        Collections.reverse(list);
    }
}
