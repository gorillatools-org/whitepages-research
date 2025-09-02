package androidx.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.jvm.internal.markers.KMappedMarker;

public abstract class IndexBasedArrayIterator implements Iterator, KMappedMarker {
    private boolean canRemove;
    private int index;
    private int size;

    /* access modifiers changed from: protected */
    public abstract Object elementAt(int i);

    /* access modifiers changed from: protected */
    public abstract void removeAt(int i);

    public IndexBasedArrayIterator(int i) {
        this.size = i;
    }

    public boolean hasNext() {
        return this.index < this.size;
    }

    public Object next() {
        if (hasNext()) {
            Object elementAt = elementAt(this.index);
            this.index++;
            this.canRemove = true;
            return elementAt;
        }
        throw new NoSuchElementException();
    }

    public void remove() {
        if (this.canRemove) {
            int i = this.index - 1;
            this.index = i;
            removeAt(i);
            this.size--;
            this.canRemove = false;
            return;
        }
        throw new IllegalStateException("Call next() before removing an element.");
    }
}
