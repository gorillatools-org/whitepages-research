package com.google.common.collect;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.Arrays;
import java.util.Collection;

public abstract class ImmutableCollection extends AbstractCollection implements Serializable {
    private static final Object[] EMPTY_ARRAY = new Object[0];

    public abstract boolean contains(Object obj);

    /* access modifiers changed from: package-private */
    public abstract int copyIntoArray(Object[] objArr, int i);

    /* access modifiers changed from: package-private */
    public Object[] internalArray() {
        return null;
    }

    public abstract UnmodifiableIterator iterator();

    ImmutableCollection() {
    }

    public final Object[] toArray() {
        return toArray(EMPTY_ARRAY);
    }

    public final Object[] toArray(Object[] objArr) {
        Preconditions.checkNotNull(objArr);
        int size = size();
        if (objArr.length < size) {
            Object[] internalArray = internalArray();
            if (internalArray != null) {
                return Platform.copy(internalArray, internalArrayStart(), internalArrayEnd(), objArr);
            }
            objArr = ObjectArrays.newArray(objArr, size);
        } else if (objArr.length > size) {
            objArr[size] = null;
        }
        copyIntoArray(objArr, 0);
        return objArr;
    }

    /* access modifiers changed from: package-private */
    public int internalArrayStart() {
        throw new UnsupportedOperationException();
    }

    /* access modifiers changed from: package-private */
    public int internalArrayEnd() {
        throw new UnsupportedOperationException();
    }

    public final boolean add(Object obj) {
        throw new UnsupportedOperationException();
    }

    public final boolean remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    public final boolean addAll(Collection collection) {
        throw new UnsupportedOperationException();
    }

    public final boolean removeAll(Collection collection) {
        throw new UnsupportedOperationException();
    }

    public final boolean retainAll(Collection collection) {
        throw new UnsupportedOperationException();
    }

    public final void clear() {
        throw new UnsupportedOperationException();
    }

    /* access modifiers changed from: package-private */
    public Object writeReplace() {
        return new ImmutableList.SerializedForm(toArray());
    }

    public static abstract class Builder {
        static int expandedCapacity(int i, int i2) {
            if (i2 >= 0) {
                int i3 = i + (i >> 1) + 1;
                if (i3 < i2) {
                    i3 = Integer.highestOneBit(i2 - 1) << 1;
                }
                if (i3 < 0) {
                    return Integer.MAX_VALUE;
                }
                return i3;
            }
            throw new AssertionError("cannot store more than MAX_VALUE elements");
        }

        Builder() {
        }
    }

    static abstract class ArrayBasedBuilder extends Builder {
        Object[] contents;
        boolean forceCopy;
        int size = 0;

        ArrayBasedBuilder(int i) {
            CollectPreconditions.checkNonnegative(i, "initialCapacity");
            this.contents = new Object[i];
        }

        private void getReadyToExpandTo(int i) {
            Object[] objArr = this.contents;
            if (objArr.length < i) {
                this.contents = Arrays.copyOf(objArr, Builder.expandedCapacity(objArr.length, i));
                this.forceCopy = false;
            } else if (this.forceCopy) {
                this.contents = (Object[]) objArr.clone();
                this.forceCopy = false;
            }
        }

        public Builder add(Object... objArr) {
            addAll(objArr, objArr.length);
            return this;
        }

        /* access modifiers changed from: package-private */
        public final void addAll(Object[] objArr, int i) {
            ObjectArrays.checkElementsNotNull(objArr, i);
            getReadyToExpandTo(this.size + i);
            System.arraycopy(objArr, 0, this.contents, this.size, i);
            this.size += i;
        }
    }
}
