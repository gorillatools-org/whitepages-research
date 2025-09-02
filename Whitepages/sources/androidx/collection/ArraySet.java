package androidx.collection;

import androidx.collection.internal.ContainerHelpersKt;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Set;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMutableCollection;

public final class ArraySet implements Collection, Set, KMutableCollection {
    private int _size;
    private Object[] array;
    private int[] hashes;

    public ArraySet() {
        this(0, 1, (DefaultConstructorMarker) null);
    }

    public ArraySet(int i) {
        this.hashes = ContainerHelpersKt.EMPTY_INTS;
        this.array = ContainerHelpersKt.EMPTY_OBJECTS;
        if (i > 0) {
            ArraySetKt.allocArrays(this, i);
        }
    }

    public final /* bridge */ int size() {
        return getSize();
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ArraySet(int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 0 : i);
    }

    public final int[] getHashes$collection() {
        return this.hashes;
    }

    public final void setHashes$collection(int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<set-?>");
        this.hashes = iArr;
    }

    public final Object[] getArray$collection() {
        return this.array;
    }

    public final void setArray$collection(Object[] objArr) {
        Intrinsics.checkNotNullParameter(objArr, "<set-?>");
        this.array = objArr;
    }

    public final int get_size$collection() {
        return this._size;
    }

    public final void set_size$collection(int i) {
        this._size = i;
    }

    public int getSize() {
        return this._size;
    }

    public final Object[] toArray() {
        return ArraysKt.copyOfRange(this.array, 0, this._size);
    }

    public final Object[] toArray(Object[] objArr) {
        Intrinsics.checkNotNullParameter(objArr, "array");
        Object[] resizeForToArray = ArraySetJvmUtil.resizeForToArray(objArr, this._size);
        ArraysKt.copyInto(this.array, resizeForToArray, 0, 0, this._size);
        Intrinsics.checkNotNullExpressionValue(resizeForToArray, "result");
        return resizeForToArray;
    }

    public Iterator iterator() {
        return new ElementIterator();
    }

    private final class ElementIterator extends IndexBasedArrayIterator {
        public ElementIterator() {
            super(ArraySet.this.get_size$collection());
        }

        /* access modifiers changed from: protected */
        public Object elementAt(int i) {
            return ArraySet.this.valueAt(i);
        }

        /* access modifiers changed from: protected */
        public void removeAt(int i) {
            ArraySet.this.removeAt(i);
        }
    }

    public void clear() {
        if (get_size$collection() != 0) {
            setHashes$collection(ContainerHelpersKt.EMPTY_INTS);
            setArray$collection(ContainerHelpersKt.EMPTY_OBJECTS);
            set_size$collection(0);
        }
        if (get_size$collection() != 0) {
            throw new ConcurrentModificationException();
        }
    }

    public final void ensureCapacity(int i) {
        int i2 = get_size$collection();
        if (getHashes$collection().length < i) {
            int[] hashes$collection = getHashes$collection();
            Object[] array$collection = getArray$collection();
            ArraySetKt.allocArrays(this, i);
            if (get_size$collection() > 0) {
                ArraysKt.copyInto$default(hashes$collection, getHashes$collection(), 0, 0, get_size$collection(), 6, (Object) null);
                ArraysKt.copyInto$default(array$collection, getArray$collection(), 0, 0, get_size$collection(), 6, (Object) null);
            }
        }
        if (get_size$collection() != i2) {
            throw new ConcurrentModificationException();
        }
    }

    public boolean contains(Object obj) {
        return indexOf(obj) >= 0;
    }

    public final int indexOf(Object obj) {
        return obj == null ? ArraySetKt.indexOfNull(this) : ArraySetKt.indexOf(this, obj, obj.hashCode());
    }

    public final Object valueAt(int i) {
        return getArray$collection()[i];
    }

    public boolean isEmpty() {
        return get_size$collection() <= 0;
    }

    public boolean add(Object obj) {
        int i;
        int i2;
        Object obj2 = obj;
        int i3 = get_size$collection();
        boolean z = false;
        if (obj2 == null) {
            i2 = ArraySetKt.indexOfNull(this);
            i = 0;
        } else {
            int hashCode = obj.hashCode();
            i = hashCode;
            i2 = ArraySetKt.indexOf(this, obj2, hashCode);
        }
        if (i2 >= 0) {
            return false;
        }
        int i4 = ~i2;
        if (i3 >= getHashes$collection().length) {
            int i5 = 8;
            if (i3 >= 8) {
                i5 = (i3 >> 1) + i3;
            } else if (i3 < 4) {
                i5 = 4;
            }
            int[] hashes$collection = getHashes$collection();
            Object[] array$collection = getArray$collection();
            ArraySetKt.allocArrays(this, i5);
            if (i3 == get_size$collection()) {
                if (getHashes$collection().length == 0) {
                    z = true;
                }
                if (!z) {
                    ArraysKt.copyInto$default(hashes$collection, getHashes$collection(), 0, 0, hashes$collection.length, 6, (Object) null);
                    ArraysKt.copyInto$default(array$collection, getArray$collection(), 0, 0, array$collection.length, 6, (Object) null);
                }
            } else {
                throw new ConcurrentModificationException();
            }
        }
        if (i4 < i3) {
            int i6 = i4 + 1;
            ArraysKt.copyInto(getHashes$collection(), getHashes$collection(), i6, i4, i3);
            ArraysKt.copyInto(getArray$collection(), getArray$collection(), i6, i4, i3);
        }
        if (i3 != get_size$collection() || i4 >= getHashes$collection().length) {
            throw new ConcurrentModificationException();
        }
        getHashes$collection()[i4] = i;
        getArray$collection()[i4] = obj2;
        set_size$collection(get_size$collection() + 1);
        return true;
    }

    public boolean remove(Object obj) {
        int indexOf = indexOf(obj);
        if (indexOf < 0) {
            return false;
        }
        removeAt(indexOf);
        return true;
    }

    public final Object removeAt(int i) {
        int i2 = get_size$collection();
        Object obj = getArray$collection()[i];
        if (i2 <= 1) {
            clear();
        } else {
            int i3 = i2 - 1;
            int i4 = 8;
            if (getHashes$collection().length <= 8 || get_size$collection() >= getHashes$collection().length / 3) {
                if (i < i3) {
                    int i5 = i + 1;
                    ArraysKt.copyInto(getHashes$collection(), getHashes$collection(), i, i5, i2);
                    ArraysKt.copyInto(getArray$collection(), getArray$collection(), i, i5, i2);
                }
                getArray$collection()[i3] = null;
            } else {
                if (get_size$collection() > 8) {
                    i4 = get_size$collection() + (get_size$collection() >> 1);
                }
                int[] hashes$collection = getHashes$collection();
                Object[] array$collection = getArray$collection();
                ArraySetKt.allocArrays(this, i4);
                if (i > 0) {
                    int i6 = i;
                    ArraysKt.copyInto$default(hashes$collection, getHashes$collection(), 0, 0, i6, 6, (Object) null);
                    ArraysKt.copyInto$default(array$collection, getArray$collection(), 0, 0, i6, 6, (Object) null);
                }
                if (i < i3) {
                    int i7 = i + 1;
                    ArraysKt.copyInto(hashes$collection, getHashes$collection(), i, i7, i2);
                    ArraysKt.copyInto(array$collection, getArray$collection(), i, i7, i2);
                }
            }
            if (i2 == get_size$collection()) {
                set_size$collection(i3);
            } else {
                throw new ConcurrentModificationException();
            }
        }
        return obj;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj instanceof Set) && size() == ((Set) obj).size()) {
            try {
                int i = get_size$collection();
                int i2 = 0;
                while (i2 < i) {
                    if (((Set) obj).contains(valueAt(i2))) {
                        i2++;
                    }
                }
                return true;
            } catch (ClassCastException | NullPointerException unused) {
            }
        }
        return false;
    }

    public int hashCode() {
        int[] hashes$collection = getHashes$collection();
        int i = get_size$collection();
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            i2 += hashes$collection[i3];
        }
        return i2;
    }

    public String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(get_size$collection() * 14);
        sb.append('{');
        int i = get_size$collection();
        for (int i2 = 0; i2 < i; i2++) {
            if (i2 > 0) {
                sb.append(", ");
            }
            Object valueAt = valueAt(i2);
            if (valueAt != this) {
                sb.append(valueAt);
            } else {
                sb.append("(this Set)");
            }
        }
        sb.append('}');
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "StringBuilder(capacity).…builderAction).toString()");
        return sb2;
    }

    public boolean containsAll(Collection collection) {
        Intrinsics.checkNotNullParameter(collection, "elements");
        for (Object contains : collection) {
            if (!contains(contains)) {
                return false;
            }
        }
        return true;
    }

    public boolean addAll(Collection collection) {
        Intrinsics.checkNotNullParameter(collection, "elements");
        ensureCapacity(get_size$collection() + collection.size());
        boolean z = false;
        for (Object add : collection) {
            z |= add(add);
        }
        return z;
    }

    public boolean removeAll(Collection collection) {
        Intrinsics.checkNotNullParameter(collection, "elements");
        boolean z = false;
        for (Object remove : collection) {
            z |= remove(remove);
        }
        return z;
    }

    public boolean retainAll(Collection collection) {
        Intrinsics.checkNotNullParameter(collection, "elements");
        boolean z = false;
        for (int i = get_size$collection() - 1; -1 < i; i--) {
            if (!CollectionsKt.contains(collection, getArray$collection()[i])) {
                removeAt(i);
                z = true;
            }
        }
        return z;
    }
}
