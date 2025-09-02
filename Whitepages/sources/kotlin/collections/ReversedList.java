package kotlin.collections;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import kotlin.jvm.internal.Intrinsics;

final class ReversedList extends AbstractMutableList {
    /* access modifiers changed from: private */
    public final List delegate;

    public ReversedList(List list) {
        Intrinsics.checkNotNullParameter(list, "delegate");
        this.delegate = list;
    }

    public int getSize() {
        return this.delegate.size();
    }

    public Object get(int i) {
        return this.delegate.get(CollectionsKt__ReversedViewsKt.reverseElementIndex$CollectionsKt__ReversedViewsKt(this, i));
    }

    public void clear() {
        this.delegate.clear();
    }

    public Object removeAt(int i) {
        return this.delegate.remove(CollectionsKt__ReversedViewsKt.reverseElementIndex$CollectionsKt__ReversedViewsKt(this, i));
    }

    public Object set(int i, Object obj) {
        return this.delegate.set(CollectionsKt__ReversedViewsKt.reverseElementIndex$CollectionsKt__ReversedViewsKt(this, i), obj);
    }

    public void add(int i, Object obj) {
        this.delegate.add(CollectionsKt__ReversedViewsKt.reversePositionIndex$CollectionsKt__ReversedViewsKt(this, i), obj);
    }

    public Iterator iterator() {
        return listIterator(0);
    }

    public ListIterator listIterator() {
        return listIterator(0);
    }

    public ListIterator listIterator(int i) {
        return new ReversedList$listIterator$1(this, i);
    }
}
