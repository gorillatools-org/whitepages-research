package androidx.collection;

import androidx.collection.internal.ContainerHelpersKt;
import java.util.Arrays;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public class SparseArrayCompat implements Cloneable {
    public /* synthetic */ boolean garbage;
    public /* synthetic */ int[] keys;
    public /* synthetic */ int size;
    public /* synthetic */ Object[] values;

    public SparseArrayCompat() {
        this(0, 1, (DefaultConstructorMarker) null);
    }

    public SparseArrayCompat(int i) {
        if (i == 0) {
            this.keys = ContainerHelpersKt.EMPTY_INTS;
            this.values = ContainerHelpersKt.EMPTY_OBJECTS;
            return;
        }
        int idealIntArraySize = ContainerHelpersKt.idealIntArraySize(i);
        this.keys = new int[idealIntArraySize];
        this.values = new Object[idealIntArraySize];
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ SparseArrayCompat(int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 10 : i);
    }

    public SparseArrayCompat clone() {
        Object clone = super.clone();
        Intrinsics.checkNotNull(clone, "null cannot be cast to non-null type androidx.collection.SparseArrayCompat<E of androidx.collection.SparseArrayCompat>");
        SparseArrayCompat sparseArrayCompat = (SparseArrayCompat) clone;
        sparseArrayCompat.keys = (int[]) this.keys.clone();
        sparseArrayCompat.values = (Object[]) this.values.clone();
        return sparseArrayCompat;
    }

    public Object get(int i) {
        return SparseArrayCompatKt.commonGet(this, i);
    }

    public void put(int i, Object obj) {
        int binarySearch = ContainerHelpersKt.binarySearch(this.keys, this.size, i);
        if (binarySearch >= 0) {
            this.values[binarySearch] = obj;
            return;
        }
        int i2 = ~binarySearch;
        if (i2 >= this.size || this.values[i2] != SparseArrayCompatKt.DELETED) {
            if (this.garbage && this.size >= this.keys.length) {
                SparseArrayCompatKt.gc(this);
                i2 = ~ContainerHelpersKt.binarySearch(this.keys, this.size, i);
            }
            int i3 = this.size;
            if (i3 >= this.keys.length) {
                int idealIntArraySize = ContainerHelpersKt.idealIntArraySize(i3 + 1);
                int[] copyOf = Arrays.copyOf(this.keys, idealIntArraySize);
                Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, newSize)");
                this.keys = copyOf;
                Object[] copyOf2 = Arrays.copyOf(this.values, idealIntArraySize);
                Intrinsics.checkNotNullExpressionValue(copyOf2, "copyOf(this, newSize)");
                this.values = copyOf2;
            }
            int i4 = this.size;
            if (i4 - i2 != 0) {
                int[] iArr = this.keys;
                int i5 = i2 + 1;
                ArraysKt.copyInto(iArr, iArr, i5, i2, i4);
                Object[] objArr = this.values;
                ArraysKt.copyInto(objArr, objArr, i5, i2, this.size);
            }
            this.keys[i2] = i;
            this.values[i2] = obj;
            this.size++;
            return;
        }
        this.keys[i2] = i;
        this.values[i2] = obj;
    }

    public int size() {
        if (this.garbage) {
            SparseArrayCompatKt.gc(this);
        }
        return this.size;
    }

    public int keyAt(int i) {
        if (this.garbage) {
            SparseArrayCompatKt.gc(this);
        }
        return this.keys[i];
    }

    public Object valueAt(int i) {
        if (this.garbage) {
            SparseArrayCompatKt.gc(this);
        }
        return this.values[i];
    }

    public int indexOfKey(int i) {
        if (this.garbage) {
            SparseArrayCompatKt.gc(this);
        }
        return ContainerHelpersKt.binarySearch(this.keys, this.size, i);
    }

    public int indexOfValue(Object obj) {
        if (this.garbage) {
            SparseArrayCompatKt.gc(this);
        }
        int i = this.size;
        for (int i2 = 0; i2 < i; i2++) {
            if (this.values[i2] == obj) {
                return i2;
            }
        }
        return -1;
    }

    public boolean containsKey(int i) {
        return indexOfKey(i) >= 0;
    }

    public void clear() {
        int i = this.size;
        Object[] objArr = this.values;
        for (int i2 = 0; i2 < i; i2++) {
            objArr[i2] = null;
        }
        this.size = 0;
        this.garbage = false;
    }

    public void append(int i, Object obj) {
        int i2 = this.size;
        if (i2 == 0 || i > this.keys[i2 - 1]) {
            if (this.garbage && i2 >= this.keys.length) {
                SparseArrayCompatKt.gc(this);
            }
            int i3 = this.size;
            if (i3 >= this.keys.length) {
                int idealIntArraySize = ContainerHelpersKt.idealIntArraySize(i3 + 1);
                int[] copyOf = Arrays.copyOf(this.keys, idealIntArraySize);
                Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, newSize)");
                this.keys = copyOf;
                Object[] copyOf2 = Arrays.copyOf(this.values, idealIntArraySize);
                Intrinsics.checkNotNullExpressionValue(copyOf2, "copyOf(this, newSize)");
                this.values = copyOf2;
            }
            this.keys[i3] = i;
            this.values[i3] = obj;
            this.size = i3 + 1;
            return;
        }
        put(i, obj);
    }

    public String toString() {
        if (size() <= 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.size * 28);
        sb.append('{');
        int i = this.size;
        for (int i2 = 0; i2 < i; i2++) {
            if (i2 > 0) {
                sb.append(", ");
            }
            sb.append(keyAt(i2));
            sb.append('=');
            Object valueAt = valueAt(i2);
            if (valueAt != this) {
                sb.append(valueAt);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "buffer.toString()");
        return sb2;
    }
}
