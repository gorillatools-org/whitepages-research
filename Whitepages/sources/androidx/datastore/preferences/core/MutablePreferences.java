package androidx.datastore.preferences.core;

import androidx.datastore.preferences.core.Preferences;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

public final class MutablePreferences extends Preferences {
    private final AtomicBoolean frozen;
    private final Map preferencesMap;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ MutablePreferences(Map map, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new LinkedHashMap() : map, (i & 2) != 0 ? true : z);
    }

    public MutablePreferences(Map map, boolean z) {
        Intrinsics.checkNotNullParameter(map, "preferencesMap");
        this.preferencesMap = map;
        this.frozen = new AtomicBoolean(z);
    }

    public final void checkNotFrozen$datastore_preferences_core() {
        if (this.frozen.get()) {
            throw new IllegalStateException("Do mutate preferences once returned to DataStore.");
        }
    }

    public final void freeze$datastore_preferences_core() {
        this.frozen.set(true);
    }

    public Object get(Preferences.Key key) {
        Intrinsics.checkNotNullParameter(key, "key");
        Object obj = this.preferencesMap.get(key);
        if (!(obj instanceof byte[])) {
            return obj;
        }
        byte[] bArr = (byte[]) obj;
        byte[] copyOf = Arrays.copyOf(bArr, bArr.length);
        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, size)");
        return copyOf;
    }

    public Map asMap() {
        Pair pair;
        Iterable<Map.Entry> entrySet = this.preferencesMap.entrySet();
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(entrySet, 10)), 16));
        for (Map.Entry entry : entrySet) {
            Object value = entry.getValue();
            if (value instanceof byte[]) {
                Object key = entry.getKey();
                byte[] bArr = (byte[]) value;
                byte[] copyOf = Arrays.copyOf(bArr, bArr.length);
                Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, size)");
                pair = new Pair(key, copyOf);
            } else {
                pair = new Pair(entry.getKey(), entry.getValue());
            }
            linkedHashMap.put(pair.getFirst(), pair.getSecond());
        }
        return Actual_jvmKt.immutableMap(linkedHashMap);
    }

    public final void set(Preferences.Key key, Object obj) {
        Intrinsics.checkNotNullParameter(key, "key");
        setUnchecked$datastore_preferences_core(key, obj);
    }

    public final void setUnchecked$datastore_preferences_core(Preferences.Key key, Object obj) {
        Intrinsics.checkNotNullParameter(key, "key");
        checkNotFrozen$datastore_preferences_core();
        if (obj == null) {
            remove(key);
        } else if (obj instanceof Set) {
            this.preferencesMap.put(key, Actual_jvmKt.immutableCopyOfSet((Set) obj));
        } else if (obj instanceof byte[]) {
            Map map = this.preferencesMap;
            byte[] bArr = (byte[]) obj;
            byte[] copyOf = Arrays.copyOf(bArr, bArr.length);
            Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, size)");
            map.put(key, copyOf);
        } else {
            this.preferencesMap.put(key, obj);
        }
    }

    public final void putAll(Preferences.Pair... pairArr) {
        Intrinsics.checkNotNullParameter(pairArr, "pairs");
        checkNotFrozen$datastore_preferences_core();
        if (pairArr.length > 0) {
            Preferences.Pair pair = pairArr[0];
            throw null;
        }
    }

    public final Object remove(Preferences.Key key) {
        Intrinsics.checkNotNullParameter(key, "key");
        checkNotFrozen$datastore_preferences_core();
        return this.preferencesMap.remove(key);
    }

    public final void clear() {
        checkNotFrozen$datastore_preferences_core();
        this.preferencesMap.clear();
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x0067 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r6) {
        /*
            r5 = this;
            boolean r0 = r6 instanceof androidx.datastore.preferences.core.MutablePreferences
            r1 = 0
            if (r0 != 0) goto L_0x0006
            return r1
        L_0x0006:
            androidx.datastore.preferences.core.MutablePreferences r6 = (androidx.datastore.preferences.core.MutablePreferences) r6
            java.util.Map r0 = r6.preferencesMap
            java.util.Map r2 = r5.preferencesMap
            r3 = 1
            if (r0 != r2) goto L_0x0010
            return r3
        L_0x0010:
            int r0 = r0.size()
            java.util.Map r2 = r5.preferencesMap
            int r2 = r2.size()
            if (r0 == r2) goto L_0x001d
            return r1
        L_0x001d:
            java.util.Map r6 = r6.preferencesMap
            boolean r0 = r6.isEmpty()
            if (r0 == 0) goto L_0x0027
        L_0x0025:
            r1 = r3
            goto L_0x0067
        L_0x0027:
            java.util.Set r6 = r6.entrySet()
            java.util.Iterator r6 = r6.iterator()
        L_0x002f:
            boolean r0 = r6.hasNext()
            if (r0 == 0) goto L_0x0025
            java.lang.Object r0 = r6.next()
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0
            java.util.Map r2 = r5.preferencesMap
            java.lang.Object r4 = r0.getKey()
            java.lang.Object r2 = r2.get(r4)
            if (r2 == 0) goto L_0x0064
            java.lang.Object r0 = r0.getValue()
            boolean r4 = r0 instanceof byte[]
            if (r4 == 0) goto L_0x005f
            boolean r4 = r2 instanceof byte[]
            if (r4 == 0) goto L_0x0064
            byte[] r0 = (byte[]) r0
            byte[] r2 = (byte[]) r2
            boolean r0 = java.util.Arrays.equals(r0, r2)
            if (r0 == 0) goto L_0x0064
            r0 = r3
            goto L_0x0065
        L_0x005f:
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r0, (java.lang.Object) r2)
            goto L_0x0065
        L_0x0064:
            r0 = r1
        L_0x0065:
            if (r0 != 0) goto L_0x002f
        L_0x0067:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.datastore.preferences.core.MutablePreferences.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        int i;
        int i2 = 0;
        for (Map.Entry value : this.preferencesMap.entrySet()) {
            Object value2 = value.getValue();
            if (value2 instanceof byte[]) {
                i = Arrays.hashCode((byte[]) value2);
            } else {
                i = value2.hashCode();
            }
            i2 += i;
        }
        return i2;
    }

    public String toString() {
        return CollectionsKt.joinToString$default(this.preferencesMap.entrySet(), ",\n", "{\n", "\n}", 0, (CharSequence) null, MutablePreferences$toString$1.INSTANCE, 24, (Object) null);
    }
}
