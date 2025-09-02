package kotlin.collections;

import java.util.Collection;
import kotlin.jvm.internal.CollectionToArray;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;

public abstract class AbstractCollection implements Collection, KMappedMarker {
    public boolean add(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean addAll(Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public abstract int getSize();

    public boolean remove(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean removeAll(Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean retainAll(Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    protected AbstractCollection() {
    }

    public final /* bridge */ int size() {
        return getSize();
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

    public boolean isEmpty() {
        return size() == 0;
    }

    public String toString() {
        return CollectionsKt.joinToString$default(this, ", ", "[", "]", 0, (CharSequence) null, new AbstractCollection$toString$1(this), 24, (Object) null);
    }

    public Object[] toArray() {
        return CollectionToArray.toArray(this);
    }

    public Object[] toArray(Object[] objArr) {
        Intrinsics.checkNotNullParameter(objArr, "array");
        return CollectionToArray.toArray(this, objArr);
    }

    public boolean contains(Object obj) {
        if (isEmpty()) {
            return false;
        }
        for (Object areEqual : this) {
            if (Intrinsics.areEqual(areEqual, obj)) {
                return true;
            }
        }
        return false;
    }
}
