package com.google.android.gms.internal.location;

import java.util.Iterator;

public abstract class zzbu<E> implements Iterator<E> {
    protected zzbu() {
    }

    @Deprecated
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
