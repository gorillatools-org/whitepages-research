package com.google.common.collect;

import com.google.common.collect.ImmutableCollection;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

public abstract class ImmutableMap implements Map, Serializable {
    static final Map.Entry[] EMPTY_ENTRY_ARRAY = new Map.Entry[0];
    private transient ImmutableSet entrySet;
    private transient ImmutableSet keySet;
    private transient ImmutableCollection values;

    /* access modifiers changed from: package-private */
    public abstract ImmutableSet createEntrySet();

    /* access modifiers changed from: package-private */
    public abstract ImmutableSet createKeySet();

    /* access modifiers changed from: package-private */
    public abstract ImmutableCollection createValues();

    public abstract Object get(Object obj);

    public static ImmutableMap of(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8) {
        CollectPreconditions.checkEntryNotNull(obj, obj2);
        CollectPreconditions.checkEntryNotNull(obj3, obj4);
        CollectPreconditions.checkEntryNotNull(obj5, obj6);
        CollectPreconditions.checkEntryNotNull(obj7, obj8);
        return RegularImmutableMap.create(4, new Object[]{obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8});
    }

    public static ImmutableMap of(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10) {
        CollectPreconditions.checkEntryNotNull(obj, obj2);
        CollectPreconditions.checkEntryNotNull(obj3, obj4);
        CollectPreconditions.checkEntryNotNull(obj5, obj6);
        CollectPreconditions.checkEntryNotNull(obj7, obj8);
        CollectPreconditions.checkEntryNotNull(obj9, obj10);
        return RegularImmutableMap.create(5, new Object[]{obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10});
    }

    public static ImmutableMap ofEntries(Map.Entry... entryArr) {
        return copyOf(Arrays.asList(entryArr));
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        Object[] alternatingKeysAndValues;
        DuplicateKey duplicateKey;
        boolean entriesUsed;
        int size;
        Comparator valueComparator;

        public Builder() {
            this(4);
        }

        Builder(int i) {
            this.alternatingKeysAndValues = new Object[(i * 2)];
            this.size = 0;
            this.entriesUsed = false;
        }

        private void ensureCapacity(int i) {
            int i2 = i * 2;
            Object[] objArr = this.alternatingKeysAndValues;
            if (i2 > objArr.length) {
                this.alternatingKeysAndValues = Arrays.copyOf(objArr, ImmutableCollection.Builder.expandedCapacity(objArr.length, i2));
                this.entriesUsed = false;
            }
        }

        public Builder put(Object obj, Object obj2) {
            ensureCapacity(this.size + 1);
            CollectPreconditions.checkEntryNotNull(obj, obj2);
            Object[] objArr = this.alternatingKeysAndValues;
            int i = this.size;
            objArr[i * 2] = obj;
            objArr[(i * 2) + 1] = obj2;
            this.size = i + 1;
            return this;
        }

        public Builder put(Map.Entry entry) {
            return put(entry.getKey(), entry.getValue());
        }

        public Builder putAll(Map map) {
            return putAll((Iterable) map.entrySet());
        }

        public Builder putAll(Iterable iterable) {
            if (iterable instanceof Collection) {
                ensureCapacity(this.size + ((Collection) iterable).size());
            }
            Iterator it = iterable.iterator();
            while (it.hasNext()) {
                put((Map.Entry) it.next());
            }
            return this;
        }

        private ImmutableMap build(boolean z) {
            Object[] objArr;
            DuplicateKey duplicateKey2;
            DuplicateKey duplicateKey3;
            if (!z || (duplicateKey3 = this.duplicateKey) == null) {
                int i = this.size;
                if (this.valueComparator == null) {
                    objArr = this.alternatingKeysAndValues;
                } else {
                    if (this.entriesUsed) {
                        this.alternatingKeysAndValues = Arrays.copyOf(this.alternatingKeysAndValues, i * 2);
                    }
                    objArr = this.alternatingKeysAndValues;
                    if (!z) {
                        objArr = lastEntryForEachKey(objArr, this.size);
                        if (objArr.length < this.alternatingKeysAndValues.length) {
                            i = objArr.length >>> 1;
                        }
                    }
                    sortEntries(objArr, i, this.valueComparator);
                }
                this.entriesUsed = true;
                RegularImmutableMap create = RegularImmutableMap.create(i, objArr, this);
                if (!z || (duplicateKey2 = this.duplicateKey) == null) {
                    return create;
                }
                throw duplicateKey2.exception();
            }
            throw duplicateKey3.exception();
        }

        public ImmutableMap build() {
            return buildOrThrow();
        }

        public ImmutableMap buildOrThrow() {
            return build(true);
        }

        static void sortEntries(Object[] objArr, int i, Comparator comparator) {
            Map.Entry[] entryArr = new Map.Entry[i];
            for (int i2 = 0; i2 < i; i2++) {
                int i3 = i2 * 2;
                Object obj = objArr[i3];
                Objects.requireNonNull(obj);
                Object obj2 = objArr[i3 + 1];
                Objects.requireNonNull(obj2);
                entryArr[i2] = new AbstractMap.SimpleImmutableEntry(obj, obj2);
            }
            Arrays.sort(entryArr, 0, i, Ordering.from(comparator).onResultOf(Maps.valueFunction()));
            for (int i4 = 0; i4 < i; i4++) {
                int i5 = i4 * 2;
                objArr[i5] = entryArr[i4].getKey();
                objArr[i5 + 1] = entryArr[i4].getValue();
            }
        }

        private Object[] lastEntryForEachKey(Object[] objArr, int i) {
            HashSet hashSet = new HashSet();
            BitSet bitSet = new BitSet();
            for (int i2 = i - 1; i2 >= 0; i2--) {
                Object obj = objArr[i2 * 2];
                Objects.requireNonNull(obj);
                if (!hashSet.add(obj)) {
                    bitSet.set(i2);
                }
            }
            if (bitSet.isEmpty()) {
                return objArr;
            }
            Object[] objArr2 = new Object[((i - bitSet.cardinality()) * 2)];
            int i3 = 0;
            int i4 = 0;
            while (i3 < i * 2) {
                if (bitSet.get(i3 >>> 1)) {
                    i3 += 2;
                } else {
                    int i5 = i4 + 1;
                    int i6 = i3 + 1;
                    Object obj2 = objArr[i3];
                    Objects.requireNonNull(obj2);
                    objArr2[i4] = obj2;
                    i4 += 2;
                    i3 += 2;
                    Object obj3 = objArr[i6];
                    Objects.requireNonNull(obj3);
                    objArr2[i5] = obj3;
                }
            }
            return objArr2;
        }

        static final class DuplicateKey {
            private final Object key;
            private final Object value1;
            private final Object value2;

            DuplicateKey(Object obj, Object obj2, Object obj3) {
                this.key = obj;
                this.value1 = obj2;
                this.value2 = obj3;
            }

            /* access modifiers changed from: package-private */
            public IllegalArgumentException exception() {
                String valueOf = String.valueOf(this.key);
                String valueOf2 = String.valueOf(this.value1);
                String valueOf3 = String.valueOf(this.key);
                String valueOf4 = String.valueOf(this.value2);
                StringBuilder sb = new StringBuilder(valueOf.length() + 39 + valueOf2.length() + valueOf3.length() + valueOf4.length());
                sb.append("Multiple entries with same key: ");
                sb.append(valueOf);
                sb.append("=");
                sb.append(valueOf2);
                sb.append(" and ");
                sb.append(valueOf3);
                sb.append("=");
                sb.append(valueOf4);
                return new IllegalArgumentException(sb.toString());
            }
        }
    }

    public static ImmutableMap copyOf(Iterable iterable) {
        Builder builder = new Builder(iterable instanceof Collection ? ((Collection) iterable).size() : 4);
        builder.putAll(iterable);
        return builder.build();
    }

    ImmutableMap() {
    }

    public final Object put(Object obj, Object obj2) {
        throw new UnsupportedOperationException();
    }

    public final Object remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    public final void putAll(Map map) {
        throw new UnsupportedOperationException();
    }

    public final void clear() {
        throw new UnsupportedOperationException();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean containsKey(Object obj) {
        return get(obj) != null;
    }

    public boolean containsValue(Object obj) {
        return values().contains(obj);
    }

    public final Object getOrDefault(Object obj, Object obj2) {
        Object obj3 = get(obj);
        return obj3 != null ? obj3 : obj2;
    }

    public ImmutableSet entrySet() {
        ImmutableSet immutableSet = this.entrySet;
        if (immutableSet != null) {
            return immutableSet;
        }
        ImmutableSet createEntrySet = createEntrySet();
        this.entrySet = createEntrySet;
        return createEntrySet;
    }

    public ImmutableSet keySet() {
        ImmutableSet immutableSet = this.keySet;
        if (immutableSet != null) {
            return immutableSet;
        }
        ImmutableSet createKeySet = createKeySet();
        this.keySet = createKeySet;
        return createKeySet;
    }

    public ImmutableCollection values() {
        ImmutableCollection immutableCollection = this.values;
        if (immutableCollection != null) {
            return immutableCollection;
        }
        ImmutableCollection createValues = createValues();
        this.values = createValues;
        return createValues;
    }

    public boolean equals(Object obj) {
        return Maps.equalsImpl(this, obj);
    }

    public int hashCode() {
        return Sets.hashCodeImpl(entrySet());
    }

    public String toString() {
        return Maps.toStringImpl(this);
    }

    static class SerializedForm implements Serializable {
        private static final long serialVersionUID = 0;
        private final Object keys;
        private final Object values;

        SerializedForm(ImmutableMap immutableMap) {
            Object[] objArr = new Object[immutableMap.size()];
            Object[] objArr2 = new Object[immutableMap.size()];
            UnmodifiableIterator it = immutableMap.entrySet().iterator();
            int i = 0;
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                objArr[i] = entry.getKey();
                objArr2[i] = entry.getValue();
                i++;
            }
            this.keys = objArr;
            this.values = objArr2;
        }

        /* access modifiers changed from: package-private */
        public final Object readResolve() {
            Object obj = this.keys;
            if (!(obj instanceof ImmutableSet)) {
                return legacyReadResolve();
            }
            ImmutableSet immutableSet = (ImmutableSet) obj;
            Builder makeBuilder = makeBuilder(immutableSet.size());
            UnmodifiableIterator it = immutableSet.iterator();
            UnmodifiableIterator it2 = ((ImmutableCollection) this.values).iterator();
            while (it.hasNext()) {
                makeBuilder.put(it.next(), it2.next());
            }
            return makeBuilder.buildOrThrow();
        }

        /* access modifiers changed from: package-private */
        public final Object legacyReadResolve() {
            Object[] objArr = (Object[]) this.keys;
            Object[] objArr2 = (Object[]) this.values;
            Builder makeBuilder = makeBuilder(objArr.length);
            for (int i = 0; i < objArr.length; i++) {
                makeBuilder.put(objArr[i], objArr2[i]);
            }
            return makeBuilder.buildOrThrow();
        }

        /* access modifiers changed from: package-private */
        public Builder makeBuilder(int i) {
            return new Builder(i);
        }
    }

    /* access modifiers changed from: package-private */
    public Object writeReplace() {
        return new SerializedForm(this);
    }
}
