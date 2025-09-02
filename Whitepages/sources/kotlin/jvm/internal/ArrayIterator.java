package kotlin.jvm.internal;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.jvm.internal.markers.KMappedMarker;

final class ArrayIterator implements Iterator, KMappedMarker {
    private final Object[] array;
    private int index;

    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public ArrayIterator(Object[] objArr) {
        Intrinsics.checkNotNullParameter(objArr, "array");
        this.array = objArr;
    }

    public boolean hasNext() {
        return this.index < this.array.length;
    }

    public Object next() {
        try {
            Object[] objArr = this.array;
            int i = this.index;
            this.index = i + 1;
            return objArr[i];
        } catch (ArrayIndexOutOfBoundsException e) {
            this.index--;
            throw new NoSuchElementException(e.getMessage());
        }
    }
}
