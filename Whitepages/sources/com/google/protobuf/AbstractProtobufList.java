package com.google.protobuf;

import com.google.protobuf.Internal;
import java.util.AbstractList;
import java.util.Collection;
import java.util.List;
import java.util.RandomAccess;

abstract class AbstractProtobufList extends AbstractList implements Internal.ProtobufList {
    private boolean isMutable;

    public abstract Object remove(int i);

    AbstractProtobufList() {
        this(true);
    }

    AbstractProtobufList(boolean z) {
        this.isMutable = z;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof List)) {
            return false;
        }
        if (!(obj instanceof RandomAccess)) {
            return super.equals(obj);
        }
        List list = (List) obj;
        int size = size();
        if (size != list.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!get(i).equals(list.get(i))) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        int size = size();
        int i = 1;
        for (int i2 = 0; i2 < size; i2++) {
            i = (i * 31) + get(i2).hashCode();
        }
        return i;
    }

    public boolean add(Object obj) {
        ensureIsMutable();
        return super.add(obj);
    }

    public boolean addAll(Collection collection) {
        ensureIsMutable();
        return super.addAll(collection);
    }

    public boolean addAll(int i, Collection collection) {
        ensureIsMutable();
        return super.addAll(i, collection);
    }

    public void clear() {
        ensureIsMutable();
        super.clear();
    }

    public boolean isModifiable() {
        return this.isMutable;
    }

    public final void makeImmutable() {
        if (this.isMutable) {
            this.isMutable = false;
        }
    }

    public boolean remove(Object obj) {
        ensureIsMutable();
        int indexOf = indexOf(obj);
        if (indexOf == -1) {
            return false;
        }
        remove(indexOf);
        return true;
    }

    public boolean removeAll(Collection collection) {
        ensureIsMutable();
        return super.removeAll(collection);
    }

    public boolean retainAll(Collection collection) {
        ensureIsMutable();
        return super.retainAll(collection);
    }

    /* access modifiers changed from: protected */
    public void ensureIsMutable() {
        if (!this.isMutable) {
            throw new UnsupportedOperationException();
        }
    }
}
