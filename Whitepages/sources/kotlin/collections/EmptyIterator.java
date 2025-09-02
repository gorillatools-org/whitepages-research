package kotlin.collections;

import java.util.ListIterator;
import java.util.NoSuchElementException;
import kotlin.jvm.internal.markers.KMappedMarker;

public final class EmptyIterator implements ListIterator, KMappedMarker {
    public static final EmptyIterator INSTANCE = new EmptyIterator();

    public /* bridge */ /* synthetic */ void add(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean hasNext() {
        return false;
    }

    public boolean hasPrevious() {
        return false;
    }

    public int nextIndex() {
        return 0;
    }

    public int previousIndex() {
        return -1;
    }

    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public /* bridge */ /* synthetic */ void set(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    private EmptyIterator() {
    }

    public Void next() {
        throw new NoSuchElementException();
    }

    public Void previous() {
        throw new NoSuchElementException();
    }
}
