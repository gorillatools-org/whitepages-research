package com.google.common.collect;

import com.google.common.base.Preconditions;
import java.util.Objects;

class RegularImmutableList extends ImmutableList {
    static final ImmutableList EMPTY = new RegularImmutableList(new Object[0], 0);
    final transient Object[] array;
    private final transient int size;

    /* access modifiers changed from: package-private */
    public int internalArrayStart() {
        return 0;
    }

    RegularImmutableList(Object[] objArr, int i) {
        this.array = objArr;
        this.size = i;
    }

    public int size() {
        return this.size;
    }

    /* access modifiers changed from: package-private */
    public Object[] internalArray() {
        return this.array;
    }

    /* access modifiers changed from: package-private */
    public int internalArrayEnd() {
        return this.size;
    }

    /* access modifiers changed from: package-private */
    public int copyIntoArray(Object[] objArr, int i) {
        System.arraycopy(this.array, 0, objArr, i, this.size);
        return i + this.size;
    }

    public Object get(int i) {
        Preconditions.checkElementIndex(i, this.size);
        Object obj = this.array[i];
        Objects.requireNonNull(obj);
        return obj;
    }
}
