package kotlin.collections;

import java.util.ListIterator;
import kotlin.jvm.internal.markers.KMappedMarker;

public final class ReversedList$listIterator$1 implements ListIterator, KMappedMarker {
    private final ListIterator delegateIterator;
    final /* synthetic */ ReversedList this$0;

    ReversedList$listIterator$1(ReversedList reversedList, int i) {
        this.this$0 = reversedList;
        this.delegateIterator = reversedList.delegate.listIterator(CollectionsKt__ReversedViewsKt.reversePositionIndex$CollectionsKt__ReversedViewsKt(reversedList, i));
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

    public void add(Object obj) {
        this.delegateIterator.add(obj);
        this.delegateIterator.previous();
    }

    public void remove() {
        this.delegateIterator.remove();
    }

    public void set(Object obj) {
        this.delegateIterator.set(obj);
    }
}
