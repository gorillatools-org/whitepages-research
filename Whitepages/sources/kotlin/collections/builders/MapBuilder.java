package kotlin.collections.builders;

import com.google.firebase.messaging.Constants;
import java.io.NotSerializableException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import kotlin.collections.AbstractList;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.jvm.internal.markers.KMutableMap;
import kotlin.ranges.RangesKt;

public final class MapBuilder implements Map, Serializable, KMutableMap {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final MapBuilder Empty;
    private MapBuilderEntries entriesView;
    private int[] hashArray;
    private int hashShift;
    private boolean isReadOnly;
    /* access modifiers changed from: private */
    public Object[] keysArray;
    private MapBuilderKeys keysView;
    /* access modifiers changed from: private */
    public int length;
    private int maxProbeDistance;
    /* access modifiers changed from: private */
    public int modCount;
    /* access modifiers changed from: private */
    public int[] presenceArray;
    private int size;
    /* access modifiers changed from: private */
    public Object[] valuesArray;
    private MapBuilderValues valuesView;

    private MapBuilder(Object[] objArr, Object[] objArr2, int[] iArr, int[] iArr2, int i, int i2) {
        this.keysArray = objArr;
        this.valuesArray = objArr2;
        this.presenceArray = iArr;
        this.hashArray = iArr2;
        this.maxProbeDistance = i;
        this.length = i2;
        this.hashShift = Companion.computeShift(getHashSize());
    }

    public final /* bridge */ Set entrySet() {
        return getEntries();
    }

    public final /* bridge */ Set keySet() {
        return getKeys();
    }

    public final /* bridge */ int size() {
        return getSize();
    }

    public final /* bridge */ Collection values() {
        return getValues();
    }

    public int getSize() {
        return this.size;
    }

    public final boolean isReadOnly$kotlin_stdlib() {
        return this.isReadOnly;
    }

    public MapBuilder() {
        this(8);
    }

    public MapBuilder(int i) {
        this(ListBuilderKt.arrayOfUninitializedElements(i), (Object[]) null, new int[i], new int[Companion.computeHashSize(i)], 2, 0);
    }

    public final Map build() {
        checkIsMutable$kotlin_stdlib();
        this.isReadOnly = true;
        if (size() > 0) {
            return this;
        }
        MapBuilder mapBuilder = Empty;
        Intrinsics.checkNotNull(mapBuilder, "null cannot be cast to non-null type kotlin.collections.Map<K of kotlin.collections.builders.MapBuilder, V of kotlin.collections.builders.MapBuilder>");
        return mapBuilder;
    }

    private final Object writeReplace() {
        if (this.isReadOnly) {
            return new SerializedMap(this);
        }
        throw new NotSerializableException("The map cannot be serialized while it is being built.");
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean containsKey(Object obj) {
        return findKey(obj) >= 0;
    }

    public boolean containsValue(Object obj) {
        return findValue(obj) >= 0;
    }

    public Object get(Object obj) {
        int findKey = findKey(obj);
        if (findKey < 0) {
            return null;
        }
        Object[] objArr = this.valuesArray;
        Intrinsics.checkNotNull(objArr);
        return objArr[findKey];
    }

    public Object put(Object obj, Object obj2) {
        checkIsMutable$kotlin_stdlib();
        int addKey$kotlin_stdlib = addKey$kotlin_stdlib(obj);
        Object[] allocateValuesArray = allocateValuesArray();
        if (addKey$kotlin_stdlib < 0) {
            int i = (-addKey$kotlin_stdlib) - 1;
            Object obj3 = allocateValuesArray[i];
            allocateValuesArray[i] = obj2;
            return obj3;
        }
        allocateValuesArray[addKey$kotlin_stdlib] = obj2;
        return null;
    }

    public void putAll(Map map) {
        Intrinsics.checkNotNullParameter(map, Constants.MessagePayloadKeys.FROM);
        checkIsMutable$kotlin_stdlib();
        putAllEntries(map.entrySet());
    }

    public Object remove(Object obj) {
        checkIsMutable$kotlin_stdlib();
        int findKey = findKey(obj);
        if (findKey < 0) {
            return null;
        }
        Object[] objArr = this.valuesArray;
        Intrinsics.checkNotNull(objArr);
        Object obj2 = objArr[findKey];
        removeEntryAt(findKey);
        return obj2;
    }

    public void clear() {
        checkIsMutable$kotlin_stdlib();
        int i = this.length - 1;
        if (i >= 0) {
            int i2 = 0;
            while (true) {
                int[] iArr = this.presenceArray;
                int i3 = iArr[i2];
                if (i3 >= 0) {
                    this.hashArray[i3] = 0;
                    iArr[i2] = -1;
                }
                if (i2 == i) {
                    break;
                }
                i2++;
            }
        }
        ListBuilderKt.resetRange(this.keysArray, 0, this.length);
        Object[] objArr = this.valuesArray;
        if (objArr != null) {
            ListBuilderKt.resetRange(objArr, 0, this.length);
        }
        this.size = 0;
        this.length = 0;
        registerModification();
    }

    public Set getKeys() {
        MapBuilderKeys mapBuilderKeys = this.keysView;
        if (mapBuilderKeys != null) {
            return mapBuilderKeys;
        }
        MapBuilderKeys mapBuilderKeys2 = new MapBuilderKeys(this);
        this.keysView = mapBuilderKeys2;
        return mapBuilderKeys2;
    }

    public Collection getValues() {
        MapBuilderValues mapBuilderValues = this.valuesView;
        if (mapBuilderValues != null) {
            return mapBuilderValues;
        }
        MapBuilderValues mapBuilderValues2 = new MapBuilderValues(this);
        this.valuesView = mapBuilderValues2;
        return mapBuilderValues2;
    }

    public Set getEntries() {
        MapBuilderEntries mapBuilderEntries = this.entriesView;
        if (mapBuilderEntries != null) {
            return mapBuilderEntries;
        }
        MapBuilderEntries mapBuilderEntries2 = new MapBuilderEntries(this);
        this.entriesView = mapBuilderEntries2;
        return mapBuilderEntries2;
    }

    public boolean equals(Object obj) {
        return obj == this || ((obj instanceof Map) && contentEquals((Map) obj));
    }

    public int hashCode() {
        EntriesItr entriesIterator$kotlin_stdlib = entriesIterator$kotlin_stdlib();
        int i = 0;
        while (entriesIterator$kotlin_stdlib.hasNext()) {
            i += entriesIterator$kotlin_stdlib.nextHashCode$kotlin_stdlib();
        }
        return i;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder((size() * 3) + 2);
        sb.append("{");
        EntriesItr entriesIterator$kotlin_stdlib = entriesIterator$kotlin_stdlib();
        int i = 0;
        while (entriesIterator$kotlin_stdlib.hasNext()) {
            if (i > 0) {
                sb.append(", ");
            }
            entriesIterator$kotlin_stdlib.nextAppendString(sb);
            i++;
        }
        sb.append("}");
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "toString(...)");
        return sb2;
    }

    public final int getCapacity$kotlin_stdlib() {
        return this.keysArray.length;
    }

    private final int getHashSize() {
        return this.hashArray.length;
    }

    private final void registerModification() {
        this.modCount++;
    }

    public final void checkIsMutable$kotlin_stdlib() {
        if (this.isReadOnly) {
            throw new UnsupportedOperationException();
        }
    }

    private final void ensureExtraCapacity(int i) {
        if (shouldCompact(i)) {
            compact(true);
        } else {
            ensureCapacity(this.length + i);
        }
    }

    private final boolean shouldCompact(int i) {
        int capacity$kotlin_stdlib = getCapacity$kotlin_stdlib();
        int i2 = this.length;
        int i3 = capacity$kotlin_stdlib - i2;
        int size2 = i2 - size();
        return i3 < i && i3 + size2 >= i && size2 >= getCapacity$kotlin_stdlib() / 4;
    }

    private final void ensureCapacity(int i) {
        if (i < 0) {
            throw new OutOfMemoryError();
        } else if (i > getCapacity$kotlin_stdlib()) {
            int newCapacity$kotlin_stdlib = AbstractList.Companion.newCapacity$kotlin_stdlib(getCapacity$kotlin_stdlib(), i);
            this.keysArray = ListBuilderKt.copyOfUninitializedElements(this.keysArray, newCapacity$kotlin_stdlib);
            Object[] objArr = this.valuesArray;
            this.valuesArray = objArr != null ? ListBuilderKt.copyOfUninitializedElements(objArr, newCapacity$kotlin_stdlib) : null;
            int[] copyOf = Arrays.copyOf(this.presenceArray, newCapacity$kotlin_stdlib);
            Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(...)");
            this.presenceArray = copyOf;
            int access$computeHashSize = Companion.computeHashSize(newCapacity$kotlin_stdlib);
            if (access$computeHashSize > getHashSize()) {
                rehash(access$computeHashSize);
            }
        }
    }

    /* access modifiers changed from: private */
    public final Object[] allocateValuesArray() {
        Object[] objArr = this.valuesArray;
        if (objArr != null) {
            return objArr;
        }
        Object[] arrayOfUninitializedElements = ListBuilderKt.arrayOfUninitializedElements(getCapacity$kotlin_stdlib());
        this.valuesArray = arrayOfUninitializedElements;
        return arrayOfUninitializedElements;
    }

    private final int hash(Object obj) {
        return ((obj != null ? obj.hashCode() : 0) * -1640531527) >>> this.hashShift;
    }

    private final void compact(boolean z) {
        int i;
        Object[] objArr = this.valuesArray;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            i = this.length;
            if (i2 >= i) {
                break;
            }
            int[] iArr = this.presenceArray;
            int i4 = iArr[i2];
            if (i4 >= 0) {
                Object[] objArr2 = this.keysArray;
                objArr2[i3] = objArr2[i2];
                if (objArr != null) {
                    objArr[i3] = objArr[i2];
                }
                if (z) {
                    iArr[i3] = i4;
                    this.hashArray[i4] = i3 + 1;
                }
                i3++;
            }
            i2++;
        }
        ListBuilderKt.resetRange(this.keysArray, i3, i);
        if (objArr != null) {
            ListBuilderKt.resetRange(objArr, i3, this.length);
        }
        this.length = i3;
    }

    private final void rehash(int i) {
        registerModification();
        int i2 = 0;
        if (this.length > size()) {
            compact(false);
        }
        this.hashArray = new int[i];
        this.hashShift = Companion.computeShift(i);
        while (i2 < this.length) {
            int i3 = i2 + 1;
            if (putRehash(i2)) {
                i2 = i3;
            } else {
                throw new IllegalStateException("This cannot happen with fixed magic multiplier and grow-only hash array. Have object hashCodes changed?");
            }
        }
    }

    private final boolean putRehash(int i) {
        int hash = hash(this.keysArray[i]);
        int i2 = this.maxProbeDistance;
        while (true) {
            int[] iArr = this.hashArray;
            if (iArr[hash] == 0) {
                iArr[hash] = i + 1;
                this.presenceArray[i] = hash;
                return true;
            }
            i2--;
            if (i2 < 0) {
                return false;
            }
            hash = hash == 0 ? getHashSize() - 1 : hash - 1;
        }
    }

    private final int findKey(Object obj) {
        int hash = hash(obj);
        int i = this.maxProbeDistance;
        while (true) {
            int i2 = this.hashArray[hash];
            if (i2 == 0) {
                return -1;
            }
            if (i2 > 0) {
                int i3 = i2 - 1;
                if (Intrinsics.areEqual(this.keysArray[i3], obj)) {
                    return i3;
                }
            }
            i--;
            if (i < 0) {
                return -1;
            }
            hash = hash == 0 ? getHashSize() - 1 : hash - 1;
        }
    }

    private final int findValue(Object obj) {
        int i = this.length;
        while (true) {
            i--;
            if (i < 0) {
                return -1;
            }
            if (this.presenceArray[i] >= 0) {
                Object[] objArr = this.valuesArray;
                Intrinsics.checkNotNull(objArr);
                if (Intrinsics.areEqual(objArr[i], obj)) {
                    return i;
                }
            }
        }
    }

    public final int addKey$kotlin_stdlib(Object obj) {
        checkIsMutable$kotlin_stdlib();
        while (true) {
            int hash = hash(obj);
            int coerceAtMost = RangesKt.coerceAtMost(this.maxProbeDistance * 2, getHashSize() / 2);
            int i = 0;
            while (true) {
                int i2 = this.hashArray[hash];
                if (i2 <= 0) {
                    if (this.length >= getCapacity$kotlin_stdlib()) {
                        ensureExtraCapacity(1);
                    } else {
                        int i3 = this.length;
                        int i4 = i3 + 1;
                        this.length = i4;
                        this.keysArray[i3] = obj;
                        this.presenceArray[i3] = hash;
                        this.hashArray[hash] = i4;
                        this.size = size() + 1;
                        registerModification();
                        if (i > this.maxProbeDistance) {
                            this.maxProbeDistance = i;
                        }
                        return i3;
                    }
                } else if (Intrinsics.areEqual(this.keysArray[i2 - 1], obj)) {
                    return -i2;
                } else {
                    i++;
                    if (i > coerceAtMost) {
                        rehash(getHashSize() * 2);
                        break;
                    }
                    hash = hash == 0 ? getHashSize() - 1 : hash - 1;
                }
            }
        }
    }

    public final boolean removeKey$kotlin_stdlib(Object obj) {
        checkIsMutable$kotlin_stdlib();
        int findKey = findKey(obj);
        if (findKey < 0) {
            return false;
        }
        removeEntryAt(findKey);
        return true;
    }

    /* access modifiers changed from: private */
    public final void removeEntryAt(int i) {
        ListBuilderKt.resetAt(this.keysArray, i);
        Object[] objArr = this.valuesArray;
        if (objArr != null) {
            ListBuilderKt.resetAt(objArr, i);
        }
        removeHashAt(this.presenceArray[i]);
        this.presenceArray[i] = -1;
        this.size = size() - 1;
        registerModification();
    }

    private final void removeHashAt(int i) {
        int coerceAtMost = RangesKt.coerceAtMost(this.maxProbeDistance * 2, getHashSize() / 2);
        int i2 = 0;
        int i3 = i;
        do {
            i = i == 0 ? getHashSize() - 1 : i - 1;
            i2++;
            if (i2 > this.maxProbeDistance) {
                this.hashArray[i3] = 0;
                return;
            }
            int[] iArr = this.hashArray;
            int i4 = iArr[i];
            if (i4 == 0) {
                iArr[i3] = 0;
                return;
            }
            if (i4 < 0) {
                iArr[i3] = -1;
            } else {
                int i5 = i4 - 1;
                if (((hash(this.keysArray[i5]) - i) & (getHashSize() - 1)) >= i2) {
                    this.hashArray[i3] = i4;
                    this.presenceArray[i5] = i3;
                }
                coerceAtMost--;
            }
            i3 = i;
            i2 = 0;
            coerceAtMost--;
        } while (coerceAtMost >= 0);
        this.hashArray[i3] = -1;
    }

    public final boolean containsEntry$kotlin_stdlib(Map.Entry entry) {
        Intrinsics.checkNotNullParameter(entry, "entry");
        int findKey = findKey(entry.getKey());
        if (findKey < 0) {
            return false;
        }
        Object[] objArr = this.valuesArray;
        Intrinsics.checkNotNull(objArr);
        return Intrinsics.areEqual(objArr[findKey], entry.getValue());
    }

    private final boolean contentEquals(Map map) {
        return size() == map.size() && containsAllEntries$kotlin_stdlib(map.entrySet());
    }

    public final boolean containsAllEntries$kotlin_stdlib(Collection collection) {
        Intrinsics.checkNotNullParameter(collection, "m");
        for (Object next : collection) {
            if (next != null) {
                try {
                    if (!containsEntry$kotlin_stdlib((Map.Entry) next)) {
                    }
                } catch (ClassCastException unused) {
                }
            }
            return false;
        }
        return true;
    }

    private final boolean putEntry(Map.Entry entry) {
        int addKey$kotlin_stdlib = addKey$kotlin_stdlib(entry.getKey());
        Object[] allocateValuesArray = allocateValuesArray();
        if (addKey$kotlin_stdlib >= 0) {
            allocateValuesArray[addKey$kotlin_stdlib] = entry.getValue();
            return true;
        }
        int i = (-addKey$kotlin_stdlib) - 1;
        if (Intrinsics.areEqual(entry.getValue(), allocateValuesArray[i])) {
            return false;
        }
        allocateValuesArray[i] = entry.getValue();
        return true;
    }

    private final boolean putAllEntries(Collection collection) {
        boolean z = false;
        if (collection.isEmpty()) {
            return false;
        }
        ensureExtraCapacity(collection.size());
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            if (putEntry((Map.Entry) it.next())) {
                z = true;
            }
        }
        return z;
    }

    public final boolean removeEntry$kotlin_stdlib(Map.Entry entry) {
        Intrinsics.checkNotNullParameter(entry, "entry");
        checkIsMutable$kotlin_stdlib();
        int findKey = findKey(entry.getKey());
        if (findKey < 0) {
            return false;
        }
        Object[] objArr = this.valuesArray;
        Intrinsics.checkNotNull(objArr);
        if (!Intrinsics.areEqual(objArr[findKey], entry.getValue())) {
            return false;
        }
        removeEntryAt(findKey);
        return true;
    }

    public final boolean removeValue$kotlin_stdlib(Object obj) {
        checkIsMutable$kotlin_stdlib();
        int findValue = findValue(obj);
        if (findValue < 0) {
            return false;
        }
        removeEntryAt(findValue);
        return true;
    }

    public final KeysItr keysIterator$kotlin_stdlib() {
        return new KeysItr(this);
    }

    public final ValuesItr valuesIterator$kotlin_stdlib() {
        return new ValuesItr(this);
    }

    public final EntriesItr entriesIterator$kotlin_stdlib() {
        return new EntriesItr(this);
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final MapBuilder getEmpty$kotlin_stdlib() {
            return MapBuilder.Empty;
        }

        /* access modifiers changed from: private */
        public final int computeHashSize(int i) {
            return Integer.highestOneBit(RangesKt.coerceAtLeast(i, 1) * 3);
        }

        /* access modifiers changed from: private */
        public final int computeShift(int i) {
            return Integer.numberOfLeadingZeros(i) + 1;
        }
    }

    static {
        MapBuilder mapBuilder = new MapBuilder(0);
        mapBuilder.isReadOnly = true;
        Empty = mapBuilder;
    }

    public static class Itr {
        private int expectedModCount;
        private int index;
        private int lastIndex = -1;
        private final MapBuilder map;

        public Itr(MapBuilder mapBuilder) {
            Intrinsics.checkNotNullParameter(mapBuilder, "map");
            this.map = mapBuilder;
            this.expectedModCount = mapBuilder.modCount;
            initNext$kotlin_stdlib();
        }

        public final MapBuilder getMap$kotlin_stdlib() {
            return this.map;
        }

        public final int getIndex$kotlin_stdlib() {
            return this.index;
        }

        public final void setIndex$kotlin_stdlib(int i) {
            this.index = i;
        }

        public final int getLastIndex$kotlin_stdlib() {
            return this.lastIndex;
        }

        public final void setLastIndex$kotlin_stdlib(int i) {
            this.lastIndex = i;
        }

        public final void initNext$kotlin_stdlib() {
            while (this.index < this.map.length) {
                int[] access$getPresenceArray$p = this.map.presenceArray;
                int i = this.index;
                if (access$getPresenceArray$p[i] < 0) {
                    this.index = i + 1;
                } else {
                    return;
                }
            }
        }

        public final boolean hasNext() {
            return this.index < this.map.length;
        }

        public final void remove() {
            checkForComodification$kotlin_stdlib();
            if (this.lastIndex != -1) {
                this.map.checkIsMutable$kotlin_stdlib();
                this.map.removeEntryAt(this.lastIndex);
                this.lastIndex = -1;
                this.expectedModCount = this.map.modCount;
                return;
            }
            throw new IllegalStateException("Call next() before removing element from the iterator.");
        }

        public final void checkForComodification$kotlin_stdlib() {
            if (this.map.modCount != this.expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }
    }

    public static final class KeysItr extends Itr implements Iterator, KMappedMarker {
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public KeysItr(MapBuilder mapBuilder) {
            super(mapBuilder);
            Intrinsics.checkNotNullParameter(mapBuilder, "map");
        }

        public Object next() {
            checkForComodification$kotlin_stdlib();
            if (getIndex$kotlin_stdlib() < getMap$kotlin_stdlib().length) {
                int index$kotlin_stdlib = getIndex$kotlin_stdlib();
                setIndex$kotlin_stdlib(index$kotlin_stdlib + 1);
                setLastIndex$kotlin_stdlib(index$kotlin_stdlib);
                Object obj = getMap$kotlin_stdlib().keysArray[getLastIndex$kotlin_stdlib()];
                initNext$kotlin_stdlib();
                return obj;
            }
            throw new NoSuchElementException();
        }
    }

    public static final class ValuesItr extends Itr implements Iterator, KMappedMarker {
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public ValuesItr(MapBuilder mapBuilder) {
            super(mapBuilder);
            Intrinsics.checkNotNullParameter(mapBuilder, "map");
        }

        public Object next() {
            checkForComodification$kotlin_stdlib();
            if (getIndex$kotlin_stdlib() < getMap$kotlin_stdlib().length) {
                int index$kotlin_stdlib = getIndex$kotlin_stdlib();
                setIndex$kotlin_stdlib(index$kotlin_stdlib + 1);
                setLastIndex$kotlin_stdlib(index$kotlin_stdlib);
                Object[] access$getValuesArray$p = getMap$kotlin_stdlib().valuesArray;
                Intrinsics.checkNotNull(access$getValuesArray$p);
                Object obj = access$getValuesArray$p[getLastIndex$kotlin_stdlib()];
                initNext$kotlin_stdlib();
                return obj;
            }
            throw new NoSuchElementException();
        }
    }

    public static final class EntriesItr extends Itr implements Iterator, KMappedMarker {
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public EntriesItr(MapBuilder mapBuilder) {
            super(mapBuilder);
            Intrinsics.checkNotNullParameter(mapBuilder, "map");
        }

        public EntryRef next() {
            checkForComodification$kotlin_stdlib();
            if (getIndex$kotlin_stdlib() < getMap$kotlin_stdlib().length) {
                int index$kotlin_stdlib = getIndex$kotlin_stdlib();
                setIndex$kotlin_stdlib(index$kotlin_stdlib + 1);
                setLastIndex$kotlin_stdlib(index$kotlin_stdlib);
                EntryRef entryRef = new EntryRef(getMap$kotlin_stdlib(), getLastIndex$kotlin_stdlib());
                initNext$kotlin_stdlib();
                return entryRef;
            }
            throw new NoSuchElementException();
        }

        public final int nextHashCode$kotlin_stdlib() {
            if (getIndex$kotlin_stdlib() < getMap$kotlin_stdlib().length) {
                int index$kotlin_stdlib = getIndex$kotlin_stdlib();
                setIndex$kotlin_stdlib(index$kotlin_stdlib + 1);
                setLastIndex$kotlin_stdlib(index$kotlin_stdlib);
                Object obj = getMap$kotlin_stdlib().keysArray[getLastIndex$kotlin_stdlib()];
                int i = 0;
                int hashCode = obj != null ? obj.hashCode() : 0;
                Object[] access$getValuesArray$p = getMap$kotlin_stdlib().valuesArray;
                Intrinsics.checkNotNull(access$getValuesArray$p);
                Object obj2 = access$getValuesArray$p[getLastIndex$kotlin_stdlib()];
                if (obj2 != null) {
                    i = obj2.hashCode();
                }
                int i2 = hashCode ^ i;
                initNext$kotlin_stdlib();
                return i2;
            }
            throw new NoSuchElementException();
        }

        public final void nextAppendString(StringBuilder sb) {
            Intrinsics.checkNotNullParameter(sb, "sb");
            if (getIndex$kotlin_stdlib() < getMap$kotlin_stdlib().length) {
                int index$kotlin_stdlib = getIndex$kotlin_stdlib();
                setIndex$kotlin_stdlib(index$kotlin_stdlib + 1);
                setLastIndex$kotlin_stdlib(index$kotlin_stdlib);
                Object obj = getMap$kotlin_stdlib().keysArray[getLastIndex$kotlin_stdlib()];
                if (obj == getMap$kotlin_stdlib()) {
                    sb.append("(this Map)");
                } else {
                    sb.append(obj);
                }
                sb.append('=');
                Object[] access$getValuesArray$p = getMap$kotlin_stdlib().valuesArray;
                Intrinsics.checkNotNull(access$getValuesArray$p);
                Object obj2 = access$getValuesArray$p[getLastIndex$kotlin_stdlib()];
                if (obj2 == getMap$kotlin_stdlib()) {
                    sb.append("(this Map)");
                } else {
                    sb.append(obj2);
                }
                initNext$kotlin_stdlib();
                return;
            }
            throw new NoSuchElementException();
        }
    }

    public static final class EntryRef implements Map.Entry, KMappedMarker {
        private final int index;
        private final MapBuilder map;

        public EntryRef(MapBuilder mapBuilder, int i) {
            Intrinsics.checkNotNullParameter(mapBuilder, "map");
            this.map = mapBuilder;
            this.index = i;
        }

        public Object getKey() {
            return this.map.keysArray[this.index];
        }

        public Object getValue() {
            Object[] access$getValuesArray$p = this.map.valuesArray;
            Intrinsics.checkNotNull(access$getValuesArray$p);
            return access$getValuesArray$p[this.index];
        }

        public Object setValue(Object obj) {
            this.map.checkIsMutable$kotlin_stdlib();
            Object[] access$allocateValuesArray = this.map.allocateValuesArray();
            int i = this.index;
            Object obj2 = access$allocateValuesArray[i];
            access$allocateValuesArray[i] = obj;
            return obj2;
        }

        public boolean equals(Object obj) {
            if (obj instanceof Map.Entry) {
                Map.Entry entry = (Map.Entry) obj;
                return Intrinsics.areEqual(entry.getKey(), getKey()) && Intrinsics.areEqual(entry.getValue(), getValue());
            }
        }

        public int hashCode() {
            Object key = getKey();
            int i = 0;
            int hashCode = key != null ? key.hashCode() : 0;
            Object value = getValue();
            if (value != null) {
                i = value.hashCode();
            }
            return hashCode ^ i;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(getKey());
            sb.append('=');
            sb.append(getValue());
            return sb.toString();
        }
    }
}
