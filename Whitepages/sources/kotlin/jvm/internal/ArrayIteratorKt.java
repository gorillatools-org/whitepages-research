package kotlin.jvm.internal;

import java.util.Iterator;

public abstract class ArrayIteratorKt {
    public static final Iterator iterator(Object[] objArr) {
        Intrinsics.checkNotNullParameter(objArr, "array");
        return new ArrayIterator(objArr);
    }
}
