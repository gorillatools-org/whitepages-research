package androidx.collection;

import androidx.collection.internal.ContainerHelpersKt;
import java.util.ConcurrentModificationException;
import kotlin.jvm.internal.Intrinsics;

public abstract class ArraySetKt {
    public static final int binarySearchInternal(ArraySet arraySet, int i) {
        Intrinsics.checkNotNullParameter(arraySet, "<this>");
        try {
            return ContainerHelpersKt.binarySearch(arraySet.getHashes$collection(), arraySet.get_size$collection(), i);
        } catch (IndexOutOfBoundsException unused) {
            throw new ConcurrentModificationException();
        }
    }

    public static final int indexOf(ArraySet arraySet, Object obj, int i) {
        Intrinsics.checkNotNullParameter(arraySet, "<this>");
        int i2 = arraySet.get_size$collection();
        if (i2 == 0) {
            return -1;
        }
        int binarySearchInternal = binarySearchInternal(arraySet, i);
        if (binarySearchInternal < 0 || Intrinsics.areEqual(obj, arraySet.getArray$collection()[binarySearchInternal])) {
            return binarySearchInternal;
        }
        int i3 = binarySearchInternal + 1;
        while (i3 < i2 && arraySet.getHashes$collection()[i3] == i) {
            if (Intrinsics.areEqual(obj, arraySet.getArray$collection()[i3])) {
                return i3;
            }
            i3++;
        }
        int i4 = binarySearchInternal - 1;
        while (i4 >= 0 && arraySet.getHashes$collection()[i4] == i) {
            if (Intrinsics.areEqual(obj, arraySet.getArray$collection()[i4])) {
                return i4;
            }
            i4--;
        }
        return ~i3;
    }

    public static final int indexOfNull(ArraySet arraySet) {
        Intrinsics.checkNotNullParameter(arraySet, "<this>");
        return indexOf(arraySet, (Object) null, 0);
    }

    public static final void allocArrays(ArraySet arraySet, int i) {
        Intrinsics.checkNotNullParameter(arraySet, "<this>");
        arraySet.setHashes$collection(new int[i]);
        arraySet.setArray$collection(new Object[i]);
    }
}
