package kotlin.sequences;

import java.util.Iterator;
import kotlin.jvm.internal.markers.KMappedMarker;

public final class DropWhileSequence$iterator$1 implements Iterator, KMappedMarker {
    private int dropState = -1;
    private final Iterator iterator;
    private Object nextItem;
    final /* synthetic */ DropWhileSequence this$0;

    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    DropWhileSequence$iterator$1(DropWhileSequence dropWhileSequence) {
        this.this$0 = dropWhileSequence;
        this.iterator = dropWhileSequence.sequence.iterator();
    }

    private final void drop() {
        while (this.iterator.hasNext()) {
            Object next = this.iterator.next();
            if (!((Boolean) this.this$0.predicate.invoke(next)).booleanValue()) {
                this.nextItem = next;
                this.dropState = 1;
                return;
            }
        }
        this.dropState = 0;
    }

    public Object next() {
        if (this.dropState == -1) {
            drop();
        }
        if (this.dropState != 1) {
            return this.iterator.next();
        }
        Object obj = this.nextItem;
        this.nextItem = null;
        this.dropState = 0;
        return obj;
    }

    public boolean hasNext() {
        if (this.dropState == -1) {
            drop();
        }
        return this.dropState == 1 || this.iterator.hasNext();
    }
}
