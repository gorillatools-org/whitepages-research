package kotlin.collections;

import java.lang.reflect.Array;
import kotlin.jvm.internal.Intrinsics;

abstract class ArraysKt__ArraysJVMKt {
    public static final Object[] arrayOfNulls(Object[] objArr, int i) {
        Intrinsics.checkNotNullParameter(objArr, "reference");
        Object newInstance = Array.newInstance(objArr.getClass().getComponentType(), i);
        Intrinsics.checkNotNull(newInstance, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.arrayOfNulls>");
        return (Object[]) newInstance;
    }

    public static final void copyOfRangeToIndexCheck(int i, int i2) {
        if (i > i2) {
            throw new IndexOutOfBoundsException("toIndex (" + i + ") is greater than size (" + i2 + ").");
        }
    }
}
