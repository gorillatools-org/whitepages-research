package androidx.datastore.preferences.protobuf;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public final class MapFieldLite extends LinkedHashMap {
    private static final MapFieldLite EMPTY_MAP_FIELD;
    private boolean isMutable = true;

    private MapFieldLite() {
    }

    private MapFieldLite(Map map) {
        super(map);
    }

    static {
        MapFieldLite mapFieldLite = new MapFieldLite();
        EMPTY_MAP_FIELD = mapFieldLite;
        mapFieldLite.makeImmutable();
    }

    public static MapFieldLite emptyMapField() {
        return EMPTY_MAP_FIELD;
    }

    public void mergeFrom(MapFieldLite mapFieldLite) {
        ensureMutable();
        if (!mapFieldLite.isEmpty()) {
            putAll(mapFieldLite);
        }
    }

    public Set entrySet() {
        return isEmpty() ? Collections.emptySet() : super.entrySet();
    }

    public void clear() {
        ensureMutable();
        super.clear();
    }

    public Object put(Object obj, Object obj2) {
        ensureMutable();
        Internal.checkNotNull(obj);
        Internal.checkNotNull(obj2);
        return super.put(obj, obj2);
    }

    public void putAll(Map map) {
        ensureMutable();
        checkForNullKeysAndValues(map);
        super.putAll(map);
    }

    public Object remove(Object obj) {
        ensureMutable();
        return super.remove(obj);
    }

    private static void checkForNullKeysAndValues(Map map) {
        for (Object next : map.keySet()) {
            Internal.checkNotNull(next);
            Internal.checkNotNull(map.get(next));
        }
    }

    private static boolean equals(Object obj, Object obj2) {
        if (!(obj instanceof byte[]) || !(obj2 instanceof byte[])) {
            return obj.equals(obj2);
        }
        return Arrays.equals((byte[]) obj, (byte[]) obj2);
    }

    /* JADX WARNING: Removed duplicated region for block: B:9:0x001e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static boolean equals(java.util.Map r4, java.util.Map r5) {
        /*
            r0 = 1
            if (r4 != r5) goto L_0x0004
            return r0
        L_0x0004:
            int r1 = r4.size()
            int r2 = r5.size()
            r3 = 0
            if (r1 == r2) goto L_0x0010
            return r3
        L_0x0010:
            java.util.Set r4 = r4.entrySet()
            java.util.Iterator r4 = r4.iterator()
        L_0x0018:
            boolean r1 = r4.hasNext()
            if (r1 == 0) goto L_0x0042
            java.lang.Object r1 = r4.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            java.lang.Object r2 = r1.getKey()
            boolean r2 = r5.containsKey(r2)
            if (r2 != 0) goto L_0x002f
            return r3
        L_0x002f:
            java.lang.Object r2 = r1.getValue()
            java.lang.Object r1 = r1.getKey()
            java.lang.Object r1 = r5.get(r1)
            boolean r1 = equals((java.lang.Object) r2, (java.lang.Object) r1)
            if (r1 != 0) goto L_0x0018
            return r3
        L_0x0042:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.datastore.preferences.protobuf.MapFieldLite.equals(java.util.Map, java.util.Map):boolean");
    }

    public boolean equals(Object obj) {
        return (obj instanceof Map) && equals((Map) this, (Map) obj);
    }

    private static int calculateHashCodeForObject(Object obj) {
        if (obj instanceof byte[]) {
            return Internal.hashCode((byte[]) obj);
        }
        return obj.hashCode();
    }

    static int calculateHashCodeForMap(Map map) {
        int i = 0;
        for (Map.Entry entry : map.entrySet()) {
            i += calculateHashCodeForObject(entry.getValue()) ^ calculateHashCodeForObject(entry.getKey());
        }
        return i;
    }

    public int hashCode() {
        return calculateHashCodeForMap(this);
    }

    public MapFieldLite mutableCopy() {
        return isEmpty() ? new MapFieldLite() : new MapFieldLite(this);
    }

    public void makeImmutable() {
        this.isMutable = false;
    }

    public boolean isMutable() {
        return this.isMutable;
    }

    private void ensureMutable() {
        if (!isMutable()) {
            throw new UnsupportedOperationException();
        }
    }
}
