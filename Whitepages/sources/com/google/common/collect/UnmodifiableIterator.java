package com.google.common.collect;

import java.util.Iterator;

public abstract class UnmodifiableIterator implements Iterator {
    protected UnmodifiableIterator() {
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
