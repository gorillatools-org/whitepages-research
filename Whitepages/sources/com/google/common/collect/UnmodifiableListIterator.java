package com.google.common.collect;

import java.util.ListIterator;

public abstract class UnmodifiableListIterator extends UnmodifiableIterator implements ListIterator {
    protected UnmodifiableListIterator() {
    }

    public final void add(Object obj) {
        throw new UnsupportedOperationException();
    }

    public final void set(Object obj) {
        throw new UnsupportedOperationException();
    }
}
