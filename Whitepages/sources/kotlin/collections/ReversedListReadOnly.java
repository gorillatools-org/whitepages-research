package kotlin.collections;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import kotlin.jvm.internal.Intrinsics;

class ReversedListReadOnly extends AbstractList {
    /* access modifiers changed from: private */
    public final List delegate;

    public ReversedListReadOnly(List list) {
        Intrinsics.checkNotNullParameter(list, "delegate");
        this.delegate = list;
    }

    public int getSize() {
        return this.delegate.size();
    }

    public Object get(int i) {
        return this.delegate.get(CollectionsKt__ReversedViewsKt.reverseElementIndex$CollectionsKt__ReversedViewsKt(this, i));
    }

    public Iterator iterator() {
        return listIterator(0);
    }

    public ListIterator listIterator() {
        return listIterator(0);
    }

    public ListIterator listIterator(int i) {
        return new ReversedListReadOnly$listIterator$1(this, i);
    }
}
