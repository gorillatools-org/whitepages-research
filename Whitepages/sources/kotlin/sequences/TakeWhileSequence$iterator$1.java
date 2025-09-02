package kotlin.sequences;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.jvm.internal.markers.KMappedMarker;

public final class TakeWhileSequence$iterator$1 implements Iterator, KMappedMarker {
    private final Iterator iterator;
    private Object nextItem;
    private int nextState = -1;
    final /* synthetic */ TakeWhileSequence this$0;

    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    TakeWhileSequence$iterator$1(TakeWhileSequence takeWhileSequence) {
        this.this$0 = takeWhileSequence;
        this.iterator = takeWhileSequence.sequence.iterator();
    }

    private final void calcNext() {
        if (this.iterator.hasNext()) {
            Object next = this.iterator.next();
            if (((Boolean) this.this$0.predicate.invoke(next)).booleanValue()) {
                this.nextState = 1;
                this.nextItem = next;
                return;
            }
        }
        this.nextState = 0;
    }

    public Object next() {
        if (this.nextState == -1) {
            calcNext();
        }
        if (this.nextState != 0) {
            Object obj = this.nextItem;
            this.nextItem = null;
            this.nextState = -1;
            return obj;
        }
        throw new NoSuchElementException();
    }

    public boolean hasNext() {
        if (this.nextState == -1) {
            calcNext();
        }
        return this.nextState == 1;
    }
}
