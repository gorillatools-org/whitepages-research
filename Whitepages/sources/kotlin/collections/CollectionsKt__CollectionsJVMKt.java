package kotlin.collections;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import kotlin.collections.builders.ListBuilder;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

abstract class CollectionsKt__CollectionsJVMKt {
    public static List listOf(Object obj) {
        List singletonList = Collections.singletonList(obj);
        Intrinsics.checkNotNullExpressionValue(singletonList, "singletonList(...)");
        return singletonList;
    }

    public static List createListBuilder() {
        return new ListBuilder(0, 1, (DefaultConstructorMarker) null);
    }

    public static List createListBuilder(int i) {
        return new ListBuilder(i);
    }

    public static List build(List list) {
        Intrinsics.checkNotNullParameter(list, "builder");
        return ((ListBuilder) list).build();
    }

    public static Object[] terminateCollectionToArray(int i, Object[] objArr) {
        Intrinsics.checkNotNullParameter(objArr, "array");
        if (i < objArr.length) {
            objArr[i] = null;
        }
        return objArr;
    }

    public static final Object[] copyToArrayOfAny(Object[] objArr, boolean z) {
        Intrinsics.checkNotNullParameter(objArr, "<this>");
        Class<Object[]> cls = Object[].class;
        if (z && Intrinsics.areEqual((Object) objArr.getClass(), (Object) cls)) {
            return objArr;
        }
        Object[] copyOf = Arrays.copyOf(objArr, objArr.length, cls);
        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(...)");
        return copyOf;
    }
}
