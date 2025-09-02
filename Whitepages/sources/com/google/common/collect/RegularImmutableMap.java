package com.google.common.collect;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

final class RegularImmutableMap extends ImmutableMap {
    static final ImmutableMap EMPTY = new RegularImmutableMap((Object) null, new Object[0], 0);
    private static final long serialVersionUID = 0;
    final transient Object[] alternatingKeysAndValues;
    private final transient Object hashTable;
    private final transient int size;

    static RegularImmutableMap create(int i, Object[] objArr) {
        return create(i, objArr, (ImmutableMap.Builder) null);
    }

    static RegularImmutableMap create(int i, Object[] objArr, ImmutableMap.Builder builder) {
        if (i == 0) {
            return (RegularImmutableMap) EMPTY;
        }
        if (i == 1) {
            Object obj = objArr[0];
            Objects.requireNonNull(obj);
            Object obj2 = objArr[1];
            Objects.requireNonNull(obj2);
            CollectPreconditions.checkEntryNotNull(obj, obj2);
            return new RegularImmutableMap((Object) null, objArr, 1);
        }
        Preconditions.checkPositionIndex(i, objArr.length >> 1);
        Object createHashTable = createHashTable(objArr, i, ImmutableSet.chooseTableSize(i), 0);
        if (createHashTable instanceof Object[]) {
            Object[] objArr2 = (Object[]) createHashTable;
            ImmutableMap.Builder.DuplicateKey duplicateKey = (ImmutableMap.Builder.DuplicateKey) objArr2[2];
            if (builder != null) {
                builder.duplicateKey = duplicateKey;
                Object obj3 = objArr2[0];
                int intValue = ((Integer) objArr2[1]).intValue();
                objArr = Arrays.copyOf(objArr, intValue * 2);
                createHashTable = obj3;
                i = intValue;
            } else {
                throw duplicateKey.exception();
            }
        }
        return new RegularImmutableMap(createHashTable, objArr, i);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v1, resolved type: java.lang.Object[]} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.Object createHashTable(java.lang.Object[] r16, int r17, int r18, int r19) {
        /*
            r0 = r17
            r1 = r18
            r2 = 0
            r3 = 1
            if (r0 != r3) goto L_0x0018
            r0 = r16[r19]
            java.util.Objects.requireNonNull(r0)
            r1 = r19 ^ 1
            r1 = r16[r1]
            java.util.Objects.requireNonNull(r1)
            com.google.common.collect.CollectPreconditions.checkEntryNotNull(r0, r1)
            return r2
        L_0x0018:
            int r4 = r1 + -1
            r5 = 128(0x80, float:1.794E-43)
            r6 = 3
            r7 = -1
            r8 = 2
            r9 = 0
            if (r1 > r5) goto L_0x008c
            byte[] r1 = new byte[r1]
            java.util.Arrays.fill(r1, r7)
            r5 = r9
            r7 = r5
        L_0x0029:
            if (r5 >= r0) goto L_0x007b
            int r10 = r5 * 2
            int r10 = r10 + r19
            int r11 = r7 * 2
            int r11 = r11 + r19
            r12 = r16[r10]
            java.util.Objects.requireNonNull(r12)
            r10 = r10 ^ r3
            r10 = r16[r10]
            java.util.Objects.requireNonNull(r10)
            com.google.common.collect.CollectPreconditions.checkEntryNotNull(r12, r10)
            int r13 = r12.hashCode()
            int r13 = com.google.common.collect.Hashing.smear(r13)
        L_0x0049:
            r13 = r13 & r4
            byte r14 = r1[r13]
            r15 = 255(0xff, float:3.57E-43)
            r14 = r14 & r15
            if (r14 != r15) goto L_0x005f
            byte r14 = (byte) r11
            r1[r13] = r14
            if (r7 >= r5) goto L_0x005c
            r16[r11] = r12
            r11 = r11 ^ 1
            r16[r11] = r10
        L_0x005c:
            int r7 = r7 + 1
            goto L_0x0075
        L_0x005f:
            r15 = r16[r14]
            boolean r15 = r12.equals(r15)
            if (r15 == 0) goto L_0x0078
            com.google.common.collect.ImmutableMap$Builder$DuplicateKey r2 = new com.google.common.collect.ImmutableMap$Builder$DuplicateKey
            r11 = r14 ^ 1
            r13 = r16[r11]
            java.util.Objects.requireNonNull(r13)
            r2.<init>(r12, r10, r13)
            r16[r11] = r10
        L_0x0075:
            int r5 = r5 + 1
            goto L_0x0029
        L_0x0078:
            int r13 = r13 + 1
            goto L_0x0049
        L_0x007b:
            if (r7 != r0) goto L_0x007e
            goto L_0x008b
        L_0x007e:
            java.lang.Object[] r0 = new java.lang.Object[r6]
            r0[r9] = r1
            java.lang.Integer r1 = java.lang.Integer.valueOf(r7)
            r0[r3] = r1
            r0[r8] = r2
            r1 = r0
        L_0x008b:
            return r1
        L_0x008c:
            r5 = 32768(0x8000, float:4.5918E-41)
            if (r1 > r5) goto L_0x00fc
            short[] r1 = new short[r1]
            java.util.Arrays.fill(r1, r7)
            r5 = r9
            r7 = r5
        L_0x0098:
            if (r5 >= r0) goto L_0x00eb
            int r10 = r5 * 2
            int r10 = r10 + r19
            int r11 = r7 * 2
            int r11 = r11 + r19
            r12 = r16[r10]
            java.util.Objects.requireNonNull(r12)
            r10 = r10 ^ r3
            r10 = r16[r10]
            java.util.Objects.requireNonNull(r10)
            com.google.common.collect.CollectPreconditions.checkEntryNotNull(r12, r10)
            int r13 = r12.hashCode()
            int r13 = com.google.common.collect.Hashing.smear(r13)
        L_0x00b8:
            r13 = r13 & r4
            short r14 = r1[r13]
            r15 = 65535(0xffff, float:9.1834E-41)
            r14 = r14 & r15
            if (r14 != r15) goto L_0x00cf
            short r14 = (short) r11
            r1[r13] = r14
            if (r7 >= r5) goto L_0x00cc
            r16[r11] = r12
            r11 = r11 ^ 1
            r16[r11] = r10
        L_0x00cc:
            int r7 = r7 + 1
            goto L_0x00e5
        L_0x00cf:
            r15 = r16[r14]
            boolean r15 = r12.equals(r15)
            if (r15 == 0) goto L_0x00e8
            com.google.common.collect.ImmutableMap$Builder$DuplicateKey r2 = new com.google.common.collect.ImmutableMap$Builder$DuplicateKey
            r11 = r14 ^ 1
            r13 = r16[r11]
            java.util.Objects.requireNonNull(r13)
            r2.<init>(r12, r10, r13)
            r16[r11] = r10
        L_0x00e5:
            int r5 = r5 + 1
            goto L_0x0098
        L_0x00e8:
            int r13 = r13 + 1
            goto L_0x00b8
        L_0x00eb:
            if (r7 != r0) goto L_0x00ee
            goto L_0x00fb
        L_0x00ee:
            java.lang.Object[] r0 = new java.lang.Object[r6]
            r0[r9] = r1
            java.lang.Integer r1 = java.lang.Integer.valueOf(r7)
            r0[r3] = r1
            r0[r8] = r2
            r1 = r0
        L_0x00fb:
            return r1
        L_0x00fc:
            int[] r1 = new int[r1]
            java.util.Arrays.fill(r1, r7)
            r5 = r9
            r10 = r5
        L_0x0103:
            if (r5 >= r0) goto L_0x0153
            int r11 = r5 * 2
            int r11 = r11 + r19
            int r12 = r10 * 2
            int r12 = r12 + r19
            r13 = r16[r11]
            java.util.Objects.requireNonNull(r13)
            r11 = r11 ^ r3
            r11 = r16[r11]
            java.util.Objects.requireNonNull(r11)
            com.google.common.collect.CollectPreconditions.checkEntryNotNull(r13, r11)
            int r14 = r13.hashCode()
            int r14 = com.google.common.collect.Hashing.smear(r14)
        L_0x0123:
            r14 = r14 & r4
            r15 = r1[r14]
            if (r15 != r7) goto L_0x0135
            r1[r14] = r12
            if (r10 >= r5) goto L_0x0132
            r16[r12] = r13
            r12 = r12 ^ 1
            r16[r12] = r11
        L_0x0132:
            int r10 = r10 + 1
            goto L_0x014b
        L_0x0135:
            r7 = r16[r15]
            boolean r7 = r13.equals(r7)
            if (r7 == 0) goto L_0x014f
            com.google.common.collect.ImmutableMap$Builder$DuplicateKey r2 = new com.google.common.collect.ImmutableMap$Builder$DuplicateKey
            r7 = r15 ^ 1
            r12 = r16[r7]
            java.util.Objects.requireNonNull(r12)
            r2.<init>(r13, r11, r12)
            r16[r7] = r11
        L_0x014b:
            int r5 = r5 + 1
            r7 = -1
            goto L_0x0103
        L_0x014f:
            int r14 = r14 + 1
            r7 = -1
            goto L_0x0123
        L_0x0153:
            if (r10 != r0) goto L_0x0156
            goto L_0x0163
        L_0x0156:
            java.lang.Object[] r0 = new java.lang.Object[r6]
            r0[r9] = r1
            java.lang.Integer r1 = java.lang.Integer.valueOf(r10)
            r0[r3] = r1
            r0[r8] = r2
            r1 = r0
        L_0x0163:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.RegularImmutableMap.createHashTable(java.lang.Object[], int, int, int):java.lang.Object");
    }

    private RegularImmutableMap(Object obj, Object[] objArr, int i) {
        this.hashTable = obj;
        this.alternatingKeysAndValues = objArr;
        this.size = i;
    }

    public int size() {
        return this.size;
    }

    public Object get(Object obj) {
        Object obj2 = get(this.hashTable, this.alternatingKeysAndValues, this.size, 0, obj);
        if (obj2 == null) {
            return null;
        }
        return obj2;
    }

    static Object get(Object obj, Object[] objArr, int i, int i2, Object obj2) {
        if (obj2 == null) {
            return null;
        }
        if (i == 1) {
            Object obj3 = objArr[i2];
            Objects.requireNonNull(obj3);
            if (!obj3.equals(obj2)) {
                return null;
            }
            Object obj4 = objArr[i2 ^ 1];
            Objects.requireNonNull(obj4);
            return obj4;
        } else if (obj == null) {
            return null;
        } else {
            if (obj instanceof byte[]) {
                byte[] bArr = (byte[]) obj;
                int length = bArr.length - 1;
                int smear = Hashing.smear(obj2.hashCode());
                while (true) {
                    int i3 = smear & length;
                    byte b = bArr[i3] & 255;
                    if (b == 255) {
                        return null;
                    }
                    if (obj2.equals(objArr[b])) {
                        return objArr[b ^ 1];
                    }
                    smear = i3 + 1;
                }
            } else if (obj instanceof short[]) {
                short[] sArr = (short[]) obj;
                int length2 = sArr.length - 1;
                int smear2 = Hashing.smear(obj2.hashCode());
                while (true) {
                    int i4 = smear2 & length2;
                    short s = sArr[i4] & 65535;
                    if (s == 65535) {
                        return null;
                    }
                    if (obj2.equals(objArr[s])) {
                        return objArr[s ^ 1];
                    }
                    smear2 = i4 + 1;
                }
            } else {
                int[] iArr = (int[]) obj;
                int length3 = iArr.length - 1;
                int smear3 = Hashing.smear(obj2.hashCode());
                while (true) {
                    int i5 = smear3 & length3;
                    int i6 = iArr[i5];
                    if (i6 == -1) {
                        return null;
                    }
                    if (obj2.equals(objArr[i6])) {
                        return objArr[i6 ^ 1];
                    }
                    smear3 = i5 + 1;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public ImmutableSet createEntrySet() {
        return new EntrySet(this, this.alternatingKeysAndValues, 0, this.size);
    }

    static class EntrySet extends ImmutableSet {
        /* access modifiers changed from: private */
        public final transient Object[] alternatingKeysAndValues;
        /* access modifiers changed from: private */
        public final transient int keyOffset;
        private final transient ImmutableMap map;
        /* access modifiers changed from: private */
        public final transient int size;

        EntrySet(ImmutableMap immutableMap, Object[] objArr, int i, int i2) {
            this.map = immutableMap;
            this.alternatingKeysAndValues = objArr;
            this.keyOffset = i;
            this.size = i2;
        }

        public UnmodifiableIterator iterator() {
            return asList().iterator();
        }

        /* access modifiers changed from: package-private */
        public int copyIntoArray(Object[] objArr, int i) {
            return asList().copyIntoArray(objArr, i);
        }

        /* access modifiers changed from: package-private */
        public ImmutableList createAsList() {
            return new ImmutableList() {
                public Map.Entry get(int i) {
                    Preconditions.checkElementIndex(i, EntrySet.this.size);
                    int i2 = i * 2;
                    Object obj = EntrySet.this.alternatingKeysAndValues[EntrySet.this.keyOffset + i2];
                    Objects.requireNonNull(obj);
                    Object obj2 = EntrySet.this.alternatingKeysAndValues[i2 + (EntrySet.this.keyOffset ^ 1)];
                    Objects.requireNonNull(obj2);
                    return new AbstractMap.SimpleImmutableEntry(obj, obj2);
                }

                public int size() {
                    return EntrySet.this.size;
                }
            };
        }

        public boolean contains(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            Object key = entry.getKey();
            Object value = entry.getValue();
            if (value == null || !value.equals(this.map.get(key))) {
                return false;
            }
            return true;
        }

        public int size() {
            return this.size;
        }
    }

    /* access modifiers changed from: package-private */
    public ImmutableSet createKeySet() {
        return new KeySet(this, new KeysOrValuesAsList(this.alternatingKeysAndValues, 0, this.size));
    }

    static final class KeysOrValuesAsList extends ImmutableList {
        private final transient Object[] alternatingKeysAndValues;
        private final transient int offset;
        private final transient int size;

        KeysOrValuesAsList(Object[] objArr, int i, int i2) {
            this.alternatingKeysAndValues = objArr;
            this.offset = i;
            this.size = i2;
        }

        public Object get(int i) {
            Preconditions.checkElementIndex(i, this.size);
            Object obj = this.alternatingKeysAndValues[(i * 2) + this.offset];
            Objects.requireNonNull(obj);
            return obj;
        }

        public int size() {
            return this.size;
        }
    }

    static final class KeySet extends ImmutableSet {
        private final transient ImmutableList list;
        private final transient ImmutableMap map;

        KeySet(ImmutableMap immutableMap, ImmutableList immutableList) {
            this.map = immutableMap;
            this.list = immutableList;
        }

        public UnmodifiableIterator iterator() {
            return asList().iterator();
        }

        /* access modifiers changed from: package-private */
        public int copyIntoArray(Object[] objArr, int i) {
            return asList().copyIntoArray(objArr, i);
        }

        public ImmutableList asList() {
            return this.list;
        }

        public boolean contains(Object obj) {
            return this.map.get(obj) != null;
        }

        public int size() {
            return this.map.size();
        }
    }

    /* access modifiers changed from: package-private */
    public ImmutableCollection createValues() {
        return new KeysOrValuesAsList(this.alternatingKeysAndValues, 1, this.size);
    }
}
