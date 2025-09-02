package kotlin.collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.RandomAccess;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;

public abstract class AbstractList extends AbstractCollection implements List, KMappedMarker {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);

    public void add(int i, Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean addAll(int i, Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public abstract Object get(int i);

    public Object remove(int i) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public Object set(int i, Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    protected AbstractList() {
    }

    public Iterator iterator() {
        return new IteratorImpl();
    }

    public ListIterator listIterator() {
        return new ListIteratorImpl(0);
    }

    public ListIterator listIterator(int i) {
        return new ListIteratorImpl(i);
    }

    public List subList(int i, int i2) {
        return new SubList(this, i, i2);
    }

    private static final class SubList extends AbstractList implements RandomAccess {
        private int _size;
        private final int fromIndex;
        private final AbstractList list;

        public SubList(AbstractList abstractList, int i, int i2) {
            Intrinsics.checkNotNullParameter(abstractList, "list");
            this.list = abstractList;
            this.fromIndex = i;
            AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(i, i2, abstractList.size());
            this._size = i2 - i;
        }

        public Object get(int i) {
            AbstractList.Companion.checkElementIndex$kotlin_stdlib(i, this._size);
            return this.list.get(this.fromIndex + i);
        }

        public int getSize() {
            return this._size;
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof List)) {
            return false;
        }
        return Companion.orderedEquals$kotlin_stdlib(this, (Collection) obj);
    }

    public int hashCode() {
        return Companion.orderedHashCode$kotlin_stdlib(this);
    }

    private class IteratorImpl implements Iterator, KMappedMarker {
        private int index;

        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        public IteratorImpl() {
        }

        /* access modifiers changed from: protected */
        public final int getIndex() {
            return this.index;
        }

        /* access modifiers changed from: protected */
        public final void setIndex(int i) {
            this.index = i;
        }

        public boolean hasNext() {
            return this.index < AbstractList.this.size();
        }

        public Object next() {
            if (hasNext()) {
                AbstractList abstractList = AbstractList.this;
                int i = this.index;
                this.index = i + 1;
                return abstractList.get(i);
            }
            throw new NoSuchElementException();
        }
    }

    private class ListIteratorImpl extends IteratorImpl implements ListIterator, KMappedMarker {
        public void add(Object obj) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        public void set(Object obj) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        public ListIteratorImpl(int i) {
            super();
            AbstractList.Companion.checkPositionIndex$kotlin_stdlib(i, AbstractList.this.size());
            setIndex(i);
        }

        public boolean hasPrevious() {
            return getIndex() > 0;
        }

        public int nextIndex() {
            return getIndex();
        }

        public Object previous() {
            if (hasPrevious()) {
                AbstractList abstractList = AbstractList.this;
                setIndex(getIndex() - 1);
                return abstractList.get(getIndex());
            }
            throw new NoSuchElementException();
        }

        public int previousIndex() {
            return getIndex() - 1;
        }
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final int newCapacity$kotlin_stdlib(int i, int i2) {
            int i3 = i + (i >> 1);
            if (i3 - i2 < 0) {
                i3 = i2;
            }
            return i3 - 2147483639 > 0 ? i2 > 2147483639 ? Integer.MAX_VALUE : 2147483639 : i3;
        }

        private Companion() {
        }

        public final void checkElementIndex$kotlin_stdlib(int i, int i2) {
            if (i < 0 || i >= i2) {
                throw new IndexOutOfBoundsException("index: " + i + ", size: " + i2);
            }
        }

        public final void checkPositionIndex$kotlin_stdlib(int i, int i2) {
            if (i < 0 || i > i2) {
                throw new IndexOutOfBoundsException("index: " + i + ", size: " + i2);
            }
        }

        public final void checkRangeIndexes$kotlin_stdlib(int i, int i2, int i3) {
            if (i < 0 || i2 > i3) {
                throw new IndexOutOfBoundsException("fromIndex: " + i + ", toIndex: " + i2 + ", size: " + i3);
            } else if (i > i2) {
                throw new IllegalArgumentException("fromIndex: " + i + " > toIndex: " + i2);
            }
        }

        public final void checkBoundsIndexes$kotlin_stdlib(int i, int i2, int i3) {
            if (i < 0 || i2 > i3) {
                throw new IndexOutOfBoundsException("startIndex: " + i + ", endIndex: " + i2 + ", size: " + i3);
            } else if (i > i2) {
                throw new IllegalArgumentException("startIndex: " + i + " > endIndex: " + i2);
            }
        }

        public final int orderedHashCode$kotlin_stdlib(Collection collection) {
            Intrinsics.checkNotNullParameter(collection, "c");
            Iterator it = collection.iterator();
            int i = 1;
            while (it.hasNext()) {
                Object next = it.next();
                i = (i * 31) + (next != null ? next.hashCode() : 0);
            }
            return i;
        }

        public final boolean orderedEquals$kotlin_stdlib(Collection collection, Collection collection2) {
            Intrinsics.checkNotNullParameter(collection, "c");
            Intrinsics.checkNotNullParameter(collection2, "other");
            if (collection.size() != collection2.size()) {
                return false;
            }
            Iterator it = collection2.iterator();
            for (Object areEqual : collection) {
                if (!Intrinsics.areEqual(areEqual, it.next())) {
                    return false;
                }
            }
            return true;
        }
    }

    public int indexOf(Object obj) {
        int i = 0;
        for (Object areEqual : this) {
            if (Intrinsics.areEqual(areEqual, obj)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public int lastIndexOf(Object obj) {
        ListIterator listIterator = listIterator(size());
        while (listIterator.hasPrevious()) {
            if (Intrinsics.areEqual(listIterator.previous(), obj)) {
                return listIterator.nextIndex();
            }
        }
        return -1;
    }
}
