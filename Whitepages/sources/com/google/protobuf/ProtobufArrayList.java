package com.google.protobuf;

import java.util.Arrays;
import java.util.RandomAccess;

final class ProtobufArrayList extends AbstractProtobufList implements RandomAccess {
    private static final ProtobufArrayList EMPTY_LIST = new ProtobufArrayList(new Object[0], 0, false);
    private Object[] array;
    private int size;

    public static ProtobufArrayList emptyList() {
        return EMPTY_LIST;
    }

    private ProtobufArrayList(Object[] objArr, int i, boolean z) {
        super(z);
        this.array = objArr;
        this.size = i;
    }

    public ProtobufArrayList mutableCopyWithCapacity(int i) {
        if (i >= this.size) {
            return new ProtobufArrayList(Arrays.copyOf(this.array, i), this.size, true);
        }
        throw new IllegalArgumentException();
    }

    public boolean add(Object obj) {
        ensureIsMutable();
        int i = this.size;
        Object[] objArr = this.array;
        if (i == objArr.length) {
            this.array = Arrays.copyOf(objArr, ((i * 3) / 2) + 1);
        }
        Object[] objArr2 = this.array;
        int i2 = this.size;
        this.size = i2 + 1;
        objArr2[i2] = obj;
        this.modCount++;
        return true;
    }

    public void add(int i, Object obj) {
        int i2;
        ensureIsMutable();
        if (i < 0 || i > (i2 = this.size)) {
            throw new IndexOutOfBoundsException(makeOutOfBoundsExceptionMessage(i));
        }
        Object[] objArr = this.array;
        if (i2 < objArr.length) {
            System.arraycopy(objArr, i, objArr, i + 1, i2 - i);
        } else {
            Object[] createArray = createArray(((i2 * 3) / 2) + 1);
            System.arraycopy(this.array, 0, createArray, 0, i);
            System.arraycopy(this.array, i, createArray, i + 1, this.size - i);
            this.array = createArray;
        }
        this.array[i] = obj;
        this.size++;
        this.modCount++;
    }

    public Object get(int i) {
        ensureIndexInRange(i);
        return this.array[i];
    }

    public Object remove(int i) {
        ensureIsMutable();
        ensureIndexInRange(i);
        Object[] objArr = this.array;
        Object obj = objArr[i];
        int i2 = this.size;
        if (i < i2 - 1) {
            System.arraycopy(objArr, i + 1, objArr, i, (i2 - i) - 1);
        }
        this.size--;
        this.modCount++;
        return obj;
    }

    public Object set(int i, Object obj) {
        ensureIsMutable();
        ensureIndexInRange(i);
        Object[] objArr = this.array;
        Object obj2 = objArr[i];
        objArr[i] = obj;
        this.modCount++;
        return obj2;
    }

    public int size() {
        return this.size;
    }

    private static Object[] createArray(int i) {
        return new Object[i];
    }

    private void ensureIndexInRange(int i) {
        if (i < 0 || i >= this.size) {
            throw new IndexOutOfBoundsException(makeOutOfBoundsExceptionMessage(i));
        }
    }

    private String makeOutOfBoundsExceptionMessage(int i) {
        return "Index:" + i + ", Size:" + this.size;
    }
}
