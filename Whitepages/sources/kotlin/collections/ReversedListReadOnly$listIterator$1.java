package kotlin.collections;

import java.util.ListIterator;
import kotlin.jvm.internal.markers.KMappedMarker;

public final class ReversedListReadOnly$listIterator$1 implements ListIterator, KMappedMarker {
    private final ListIterator delegateIterator;
    final /* synthetic */ ReversedListReadOnly this$0;

    public void add(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public void set(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    ReversedListReadOnly$listIterator$1(ReversedListReadOnly reversedListReadOnly, int i) {
        this.this$0 = reversedListReadOnly;
        this.delegateIterator = reversedListReadOnly.delegate.listIterator(CollectionsKt__ReversedViewsKt.reversePositionIndex$CollectionsKt__ReversedViewsKt(reversedListReadOnly, i));
    }

    public boolean hasNext() {
        return this.delegateIterator.hasPrevious();
    }

    public boolean hasPrevious() {
        return this.delegateIterator.hasNext();
    }

    public Object next() {
        return this.delegateIterator.previous();
    }

    public int nextIndex() {
        return CollectionsKt__ReversedViewsKt.reverseIteratorIndex$CollectionsKt__ReversedViewsKt(this.this$0, this.delegateIterator.previousIndex());
    }

    public Object previous() {
        return this.delegateIterator.next();
    }

    public int previousIndex() {
        return CollectionsKt__ReversedViewsKt.reverseIteratorIndex$CollectionsKt__ReversedViewsKt(this.this$0, this.delegateIterator.nextIndex());
    }
}
