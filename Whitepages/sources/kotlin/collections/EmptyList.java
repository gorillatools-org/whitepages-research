package kotlin.collections;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;
import kotlin.jvm.internal.CollectionToArray;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import okhttp3.HttpUrl;

public final class EmptyList implements List, Serializable, RandomAccess, KMappedMarker {
    public static final EmptyList INSTANCE = new EmptyList();
    private static final long serialVersionUID = -7390468764508069838L;

    public /* bridge */ /* synthetic */ void add(int i, Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public /* bridge */ /* synthetic */ boolean add(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean addAll(int i, Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean addAll(Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean contains(Void voidR) {
        Intrinsics.checkNotNullParameter(voidR, "element");
        return false;
    }

    public int getSize() {
        return 0;
    }

    public int hashCode() {
        return 1;
    }

    public int indexOf(Void voidR) {
        Intrinsics.checkNotNullParameter(voidR, "element");
        return -1;
    }

    public boolean isEmpty() {
        return true;
    }

    public int lastIndexOf(Void voidR) {
        Intrinsics.checkNotNullParameter(voidR, "element");
        return -1;
    }

    public /* bridge */ /* synthetic */ Object remove(int i) {
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

    public /* bridge */ /* synthetic */ Object set(int i, Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public Object[] toArray() {
        return CollectionToArray.toArray(this);
    }

    public Object[] toArray(Object[] objArr) {
        Intrinsics.checkNotNullParameter(objArr, "array");
        return CollectionToArray.toArray(this, objArr);
    }

    private EmptyList() {
    }

    public final /* bridge */ boolean contains(Object obj) {
        if (!(obj instanceof Void)) {
            return false;
        }
        return contains((Void) obj);
    }

    public final /* bridge */ int indexOf(Object obj) {
        if (!(obj instanceof Void)) {
            return -1;
        }
        return indexOf((Void) obj);
    }

    public final /* bridge */ int lastIndexOf(Object obj) {
        if (!(obj instanceof Void)) {
            return -1;
        }
        return lastIndexOf((Void) obj);
    }

    public final /* bridge */ int size() {
        return getSize();
    }

    public boolean equals(Object obj) {
        return (obj instanceof List) && ((List) obj).isEmpty();
    }

    public String toString() {
        return HttpUrl.PATH_SEGMENT_ENCODE_SET_URI;
    }

    public boolean containsAll(Collection collection) {
        Intrinsics.checkNotNullParameter(collection, "elements");
        return collection.isEmpty();
    }

    public Void get(int i) {
        throw new IndexOutOfBoundsException("Empty list doesn't contain element at index " + i + '.');
    }

    public Iterator iterator() {
        return EmptyIterator.INSTANCE;
    }

    public ListIterator listIterator() {
        return EmptyIterator.INSTANCE;
    }

    public ListIterator listIterator(int i) {
        if (i == 0) {
            return EmptyIterator.INSTANCE;
        }
        throw new IndexOutOfBoundsException("Index: " + i);
    }

    public List subList(int i, int i2) {
        if (i == 0 && i2 == 0) {
            return this;
        }
        throw new IndexOutOfBoundsException("fromIndex: " + i + ", toIndex: " + i2);
    }

    private final Object readResolve() {
        return INSTANCE;
    }
}
