package com.google.common.collect;

import com.google.common.base.Preconditions;

final class SingletonImmutableSet extends ImmutableSet {
    final transient Object element;

    public int size() {
        return 1;
    }

    SingletonImmutableSet(Object obj) {
        this.element = Preconditions.checkNotNull(obj);
    }

    public boolean contains(Object obj) {
        return this.element.equals(obj);
    }

    public UnmodifiableIterator iterator() {
        return Iterators.singletonIterator(this.element);
    }

    /* access modifiers changed from: package-private */
    public int copyIntoArray(Object[] objArr, int i) {
        objArr[i] = this.element;
        return i + 1;
    }

    public final int hashCode() {
        return this.element.hashCode();
    }

    public String toString() {
        String obj = this.element.toString();
        StringBuilder sb = new StringBuilder(String.valueOf(obj).length() + 2);
        sb.append('[');
        sb.append(obj);
        sb.append(']');
        return sb.toString();
    }
}
