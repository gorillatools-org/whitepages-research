package com.google.common.collect;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableCollection;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

public abstract class ImmutableList extends ImmutableCollection implements List, RandomAccess {
    private static final UnmodifiableListIterator EMPTY_ITR = new Itr(RegularImmutableList.EMPTY, 0);

    public static ImmutableList of() {
        return RegularImmutableList.EMPTY;
    }

    public static ImmutableList of(Object obj, Object obj2) {
        return construct(obj, obj2);
    }

    public static ImmutableList of(Object obj, Object obj2, Object obj3) {
        return construct(obj, obj2, obj3);
    }

    public static ImmutableList of(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7) {
        return construct(obj, obj2, obj3, obj4, obj5, obj6, obj7);
    }

    public static ImmutableList of(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10, Object obj11, Object obj12, Object... objArr) {
        Object[] objArr2 = objArr;
        Preconditions.checkArgument(objArr2.length <= 2147483635, "the total number of elements must fit in an int");
        Object[] objArr3 = new Object[(objArr2.length + 12)];
        objArr3[0] = obj;
        objArr3[1] = obj2;
        objArr3[2] = obj3;
        objArr3[3] = obj4;
        objArr3[4] = obj5;
        objArr3[5] = obj6;
        objArr3[6] = obj7;
        objArr3[7] = obj8;
        objArr3[8] = obj9;
        objArr3[9] = obj10;
        objArr3[10] = obj11;
        objArr3[11] = obj12;
        System.arraycopy(objArr2, 0, objArr3, 12, objArr2.length);
        return construct(objArr3);
    }

    public static ImmutableList copyOf(Object[] objArr) {
        if (objArr.length == 0) {
            return of();
        }
        return construct((Object[]) objArr.clone());
    }

    private static ImmutableList construct(Object... objArr) {
        return asImmutableList(ObjectArrays.checkElementsNotNull(objArr));
    }

    static ImmutableList asImmutableList(Object[] objArr) {
        return asImmutableList(objArr, objArr.length);
    }

    static ImmutableList asImmutableList(Object[] objArr, int i) {
        if (i == 0) {
            return of();
        }
        return new RegularImmutableList(objArr, i);
    }

    ImmutableList() {
    }

    public UnmodifiableIterator iterator() {
        return listIterator();
    }

    public UnmodifiableListIterator listIterator() {
        return listIterator(0);
    }

    public UnmodifiableListIterator listIterator(int i) {
        Preconditions.checkPositionIndex(i, size());
        if (isEmpty()) {
            return EMPTY_ITR;
        }
        return new Itr(this, i);
    }

    static class Itr extends AbstractIndexedListIterator {
        private final ImmutableList list;

        Itr(ImmutableList immutableList, int i) {
            super(immutableList.size(), i);
            this.list = immutableList;
        }

        /* access modifiers changed from: protected */
        public Object get(int i) {
            return this.list.get(i);
        }
    }

    public int indexOf(Object obj) {
        if (obj == null) {
            return -1;
        }
        return Lists.indexOfImpl(this, obj);
    }

    public int lastIndexOf(Object obj) {
        if (obj == null) {
            return -1;
        }
        return Lists.lastIndexOfImpl(this, obj);
    }

    public boolean contains(Object obj) {
        return indexOf(obj) >= 0;
    }

    public ImmutableList subList(int i, int i2) {
        Preconditions.checkPositionIndexes(i, i2, size());
        int i3 = i2 - i;
        if (i3 == size()) {
            return this;
        }
        if (i3 == 0) {
            return of();
        }
        return subListUnchecked(i, i2);
    }

    /* access modifiers changed from: package-private */
    public ImmutableList subListUnchecked(int i, int i2) {
        return new SubList(i, i2 - i);
    }

    class SubList extends ImmutableList {
        final transient int length;
        final transient int offset;

        public /* bridge */ /* synthetic */ Iterator iterator() {
            return ImmutableList.super.iterator();
        }

        public /* bridge */ /* synthetic */ ListIterator listIterator() {
            return ImmutableList.super.listIterator();
        }

        public /* bridge */ /* synthetic */ ListIterator listIterator(int i) {
            return ImmutableList.super.listIterator(i);
        }

        SubList(int i, int i2) {
            this.offset = i;
            this.length = i2;
        }

        public int size() {
            return this.length;
        }

        /* access modifiers changed from: package-private */
        public Object[] internalArray() {
            return ImmutableList.this.internalArray();
        }

        /* access modifiers changed from: package-private */
        public int internalArrayStart() {
            return ImmutableList.this.internalArrayStart() + this.offset;
        }

        /* access modifiers changed from: package-private */
        public int internalArrayEnd() {
            return ImmutableList.this.internalArrayStart() + this.offset + this.length;
        }

        public Object get(int i) {
            Preconditions.checkElementIndex(i, this.length);
            return ImmutableList.this.get(i + this.offset);
        }

        public ImmutableList subList(int i, int i2) {
            Preconditions.checkPositionIndexes(i, i2, this.length);
            ImmutableList immutableList = ImmutableList.this;
            int i3 = this.offset;
            return immutableList.subList(i + i3, i2 + i3);
        }
    }

    public final boolean addAll(int i, Collection collection) {
        throw new UnsupportedOperationException();
    }

    public final Object set(int i, Object obj) {
        throw new UnsupportedOperationException();
    }

    public final void add(int i, Object obj) {
        throw new UnsupportedOperationException();
    }

    public final Object remove(int i) {
        throw new UnsupportedOperationException();
    }

    /* access modifiers changed from: package-private */
    public int copyIntoArray(Object[] objArr, int i) {
        int size = size();
        for (int i2 = 0; i2 < size; i2++) {
            objArr[i + i2] = get(i2);
        }
        return i + size;
    }

    public boolean equals(Object obj) {
        return Lists.equalsImpl(this, obj);
    }

    public int hashCode() {
        int size = size();
        int i = 1;
        for (int i2 = 0; i2 < size; i2++) {
            i = ~(~((i * 31) + get(i2).hashCode()));
        }
        return i;
    }

    static class SerializedForm implements Serializable {
        private static final long serialVersionUID = 0;
        final Object[] elements;

        SerializedForm(Object[] objArr) {
            this.elements = objArr;
        }

        /* access modifiers changed from: package-private */
        public Object readResolve() {
            return ImmutableList.copyOf(this.elements);
        }
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Use SerializedForm");
    }

    /* access modifiers changed from: package-private */
    public Object writeReplace() {
        return new SerializedForm(toArray());
    }

    public static final class Builder extends ImmutableCollection.ArrayBasedBuilder {
        public Builder() {
            this(4);
        }

        Builder(int i) {
            super(i);
        }

        public Builder add(Object... objArr) {
            super.add(objArr);
            return this;
        }

        public ImmutableList build() {
            this.forceCopy = true;
            return ImmutableList.asImmutableList(this.contents, this.size);
        }
    }
}
