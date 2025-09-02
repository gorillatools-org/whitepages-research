package com.google.android.gms.internal.common;

import java.util.ListIterator;
import org.jspecify.annotations.NullMarked;

@NullMarked
public abstract class zzao extends zzan implements ListIterator {
    protected zzao() {
    }

    @Deprecated
    public final void add(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final void set(Object obj) {
        throw new UnsupportedOperationException();
    }
}
