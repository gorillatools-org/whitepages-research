package kotlin.sequences;

import java.util.Iterator;
import kotlin.jvm.internal.markers.KMappedMarker;

public final class DropSequence$iterator$1 implements Iterator, KMappedMarker {
    private final Iterator iterator;
    private int left;

    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    DropSequence$iterator$1(DropSequence dropSequence) {
        this.iterator = dropSequence.sequence.iterator();
        this.left = dropSequence.count;
    }

    private final void drop() {
        while (this.left > 0 && this.iterator.hasNext()) {
            this.iterator.next();
            this.left--;
        }
    }

    public Object next() {
        drop();
        return this.iterator.next();
    }

    public boolean hasNext() {
        drop();
        return this.iterator.hasNext();
    }
}
