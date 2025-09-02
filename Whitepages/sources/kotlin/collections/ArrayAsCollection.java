package kotlin.collections;

import java.util.Collection;
import java.util.Iterator;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.jvm.internal.CollectionToArray;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;

final class ArrayAsCollection implements Collection, KMappedMarker {
    private final boolean isVarargs;
    private final Object[] values;

    public boolean add(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean addAll(Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean remove(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean removeAll(Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean retainAll(Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public Object[] toArray(Object[] objArr) {
        Intrinsics.checkNotNullParameter(objArr, "array");
        return CollectionToArray.toArray(this, objArr);
    }

    public ArrayAsCollection(Object[] objArr, boolean z) {
        Intrinsics.checkNotNullParameter(objArr, "values");
        this.values = objArr;
        this.isVarargs = z;
    }

    public final /* bridge */ int size() {
        return getSize();
    }

    public int getSize() {
        return this.values.length;
    }

    public boolean isEmpty() {
        return this.values.length == 0;
    }

    public boolean contains(Object obj) {
        return ArraysKt.contains(this.values, obj);
    }

    public boolean containsAll(Collection collection) {
        Intrinsics.checkNotNullParameter(collection, "elements");
        Iterable<Object> iterable = collection;
        if (((Collection) iterable).isEmpty()) {
            return true;
        }
        for (Object contains : iterable) {
            if (!contains(contains)) {
                return false;
            }
        }
        return true;
    }

    public Iterator iterator() {
        return ArrayIteratorKt.iterator(this.values);
    }

    public final Object[] toArray() {
        return CollectionsKt__CollectionsJVMKt.copyToArrayOfAny(this.values, this.isVarargs);
    }
}
