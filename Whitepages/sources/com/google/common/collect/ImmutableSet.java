package com.google.common.collect;

import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;
import java.util.Set;

public abstract class ImmutableSet extends ImmutableCollection implements Set {
    private transient ImmutableList asList;

    private static boolean shouldTrim(int i, int i2) {
        return i < (i2 >> 1) + (i2 >> 2);
    }

    /* access modifiers changed from: package-private */
    public boolean isHashCodeFast() {
        return false;
    }

    public abstract UnmodifiableIterator iterator();

    public static ImmutableSet of() {
        return RegularImmutableSet.EMPTY;
    }

    public static ImmutableSet of(Object obj) {
        return new SingletonImmutableSet(obj);
    }

    public static ImmutableSet of(Object obj, Object obj2, Object obj3) {
        return construct(3, obj, obj2, obj3);
    }

    public static ImmutableSet of(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object... objArr) {
        Preconditions.checkArgument(objArr.length <= 2147483641, "the total number of elements must fit in an int");
        int length = objArr.length + 6;
        Object[] objArr2 = new Object[length];
        objArr2[0] = obj;
        objArr2[1] = obj2;
        objArr2[2] = obj3;
        objArr2[3] = obj4;
        objArr2[4] = obj5;
        objArr2[5] = obj6;
        System.arraycopy(objArr, 0, objArr2, 6, objArr.length);
        return construct(length, objArr2);
    }

    private static ImmutableSet construct(int i, Object... objArr) {
        if (i == 0) {
            return of();
        }
        if (i != 1) {
            int chooseTableSize = chooseTableSize(i);
            Object[] objArr2 = new Object[chooseTableSize];
            int i2 = chooseTableSize - 1;
            int i3 = 0;
            int i4 = 0;
            for (int i5 = 0; i5 < i; i5++) {
                Object checkElementNotNull = ObjectArrays.checkElementNotNull(objArr[i5], i5);
                int hashCode = checkElementNotNull.hashCode();
                int smear = Hashing.smear(hashCode);
                while (true) {
                    int i6 = smear & i2;
                    Object obj = objArr2[i6];
                    if (obj == null) {
                        objArr[i4] = checkElementNotNull;
                        objArr2[i6] = checkElementNotNull;
                        i3 += hashCode;
                        i4++;
                        break;
                    } else if (obj.equals(checkElementNotNull)) {
                        break;
                    } else {
                        smear++;
                    }
                }
            }
            Arrays.fill(objArr, i4, i, (Object) null);
            if (i4 == 1) {
                Object obj2 = objArr[0];
                Objects.requireNonNull(obj2);
                return new SingletonImmutableSet(obj2);
            } else if (chooseTableSize(i4) < chooseTableSize / 2) {
                return construct(i4, objArr);
            } else {
                if (shouldTrim(i4, objArr.length)) {
                    objArr = Arrays.copyOf(objArr, i4);
                }
                return new RegularImmutableSet(objArr, i3, objArr2, i2, i4);
            }
        } else {
            Object obj3 = objArr[0];
            Objects.requireNonNull(obj3);
            return of(obj3);
        }
    }

    static int chooseTableSize(int i) {
        int max = Math.max(i, 2);
        boolean z = true;
        if (max < 751619276) {
            int highestOneBit = Integer.highestOneBit(max - 1) << 1;
            while (((double) highestOneBit) * 0.7d < ((double) max)) {
                highestOneBit <<= 1;
            }
            return highestOneBit;
        }
        if (max >= 1073741824) {
            z = false;
        }
        Preconditions.checkArgument(z, "collection too large");
        return 1073741824;
    }

    public static ImmutableSet copyOf(Object[] objArr) {
        int length = objArr.length;
        if (length == 0) {
            return of();
        }
        if (length != 1) {
            return construct(objArr.length, (Object[]) objArr.clone());
        }
        return of(objArr[0]);
    }

    ImmutableSet() {
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ImmutableSet) || !isHashCodeFast() || !((ImmutableSet) obj).isHashCodeFast() || hashCode() == obj.hashCode()) {
            return Sets.equalsImpl(this, obj);
        }
        return false;
    }

    public int hashCode() {
        return Sets.hashCodeImpl(this);
    }

    public ImmutableList asList() {
        ImmutableList immutableList = this.asList;
        if (immutableList != null) {
            return immutableList;
        }
        ImmutableList createAsList = createAsList();
        this.asList = createAsList;
        return createAsList;
    }

    /* access modifiers changed from: package-private */
    public ImmutableList createAsList() {
        return ImmutableList.asImmutableList(toArray());
    }

    private static class SerializedForm implements Serializable {
        private static final long serialVersionUID = 0;
        final Object[] elements;

        SerializedForm(Object[] objArr) {
            this.elements = objArr;
        }

        /* access modifiers changed from: package-private */
        public Object readResolve() {
            return ImmutableSet.copyOf(this.elements);
        }
    }

    /* access modifiers changed from: package-private */
    public Object writeReplace() {
        return new SerializedForm(toArray());
    }
}
