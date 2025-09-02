package kotlin.sequences;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.jvm.internal.markers.KMappedMarker;

public final class FilteringSequence$iterator$1 implements Iterator, KMappedMarker {
    private final Iterator iterator;
    private Object nextItem;
    private int nextState = -1;
    final /* synthetic */ FilteringSequence this$0;

    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    FilteringSequence$iterator$1(FilteringSequence filteringSequence) {
        this.this$0 = filteringSequence;
        this.iterator = filteringSequence.sequence.iterator();
    }

    private final void calcNext() {
        while (this.iterator.hasNext()) {
            Object next = this.iterator.next();
            if (((Boolean) this.this$0.predicate.invoke(next)).booleanValue() == this.this$0.sendWhen) {
                this.nextItem = next;
                this.nextState = 1;
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
