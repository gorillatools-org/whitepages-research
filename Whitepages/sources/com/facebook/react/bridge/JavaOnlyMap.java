package com.facebook.react.bridge;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import kotlin.NoWhenBranchMatchedException;
import kotlin.internal.ProgressionUtilKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class JavaOnlyMap implements ReadableMap, WritableMap {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public final Map<String, Object> backingMap;

    public /* synthetic */ JavaOnlyMap(Object[] objArr, DefaultConstructorMarker defaultConstructorMarker) {
        this(objArr);
    }

    public static final JavaOnlyMap deepClone(ReadableMap readableMap) {
        return Companion.deepClone(readableMap);
    }

    public static final JavaOnlyMap from(Map<String, ? extends Object> map) {
        return Companion.from(map);
    }

    public static final JavaOnlyMap of(Object... objArr) {
        return Companion.of(objArr);
    }

    public static final class Companion {

        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            /* JADX WARNING: Can't wrap try/catch for region: R(15:0|1|2|3|4|5|6|7|8|9|10|11|12|13|15) */
            /* JADX WARNING: Failed to process nested try/catch */
            /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0034 */
            /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
            /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
            /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0022 */
            /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x002b */
            static {
                /*
                    com.facebook.react.bridge.ReadableType[] r0 = com.facebook.react.bridge.ReadableType.values()
                    int r0 = r0.length
                    int[] r0 = new int[r0]
                    com.facebook.react.bridge.ReadableType r1 = com.facebook.react.bridge.ReadableType.Null     // Catch:{ NoSuchFieldError -> 0x0010 }
                    int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                    r2 = 1
                    r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
                L_0x0010:
                    com.facebook.react.bridge.ReadableType r1 = com.facebook.react.bridge.ReadableType.Boolean     // Catch:{ NoSuchFieldError -> 0x0019 }
                    int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                    r2 = 2
                    r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
                L_0x0019:
                    com.facebook.react.bridge.ReadableType r1 = com.facebook.react.bridge.ReadableType.Number     // Catch:{ NoSuchFieldError -> 0x0022 }
                    int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                    r2 = 3
                    r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
                L_0x0022:
                    com.facebook.react.bridge.ReadableType r1 = com.facebook.react.bridge.ReadableType.String     // Catch:{ NoSuchFieldError -> 0x002b }
                    int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002b }
                    r2 = 4
                    r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002b }
                L_0x002b:
                    com.facebook.react.bridge.ReadableType r1 = com.facebook.react.bridge.ReadableType.Map     // Catch:{ NoSuchFieldError -> 0x0034 }
                    int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0034 }
                    r2 = 5
                    r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0034 }
                L_0x0034:
                    com.facebook.react.bridge.ReadableType r1 = com.facebook.react.bridge.ReadableType.Array     // Catch:{ NoSuchFieldError -> 0x003d }
                    int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003d }
                    r2 = 6
                    r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003d }
                L_0x003d:
                    $EnumSwitchMapping$0 = r0
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.bridge.JavaOnlyMap.Companion.WhenMappings.<clinit>():void");
            }
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final JavaOnlyMap of(Object... objArr) {
            Intrinsics.checkNotNullParameter(objArr, "keysAndValues");
            return new JavaOnlyMap(Arrays.copyOf(objArr, objArr.length), (DefaultConstructorMarker) null);
        }

        public final JavaOnlyMap from(Map<String, ? extends Object> map) {
            Intrinsics.checkNotNullParameter(map, "map");
            return new JavaOnlyMap(new Object[]{map}, (DefaultConstructorMarker) null);
        }

        public final JavaOnlyMap deepClone(ReadableMap readableMap) {
            JavaOnlyMap javaOnlyMap = new JavaOnlyMap();
            if (readableMap == null) {
                return javaOnlyMap;
            }
            ReadableMapKeySetIterator keySetIterator = readableMap.keySetIterator();
            while (keySetIterator.hasNextKey()) {
                String nextKey = keySetIterator.nextKey();
                switch (WhenMappings.$EnumSwitchMapping$0[readableMap.getType(nextKey).ordinal()]) {
                    case 1:
                        javaOnlyMap.putNull(nextKey);
                        break;
                    case 2:
                        javaOnlyMap.putBoolean(nextKey, readableMap.getBoolean(nextKey));
                        break;
                    case 3:
                        javaOnlyMap.putDouble(nextKey, readableMap.getDouble(nextKey));
                        break;
                    case 4:
                        javaOnlyMap.putString(nextKey, readableMap.getString(nextKey));
                        break;
                    case 5:
                        javaOnlyMap.putMap(nextKey, deepClone(readableMap.getMap(nextKey)));
                        break;
                    case 6:
                        javaOnlyMap.putArray(nextKey, JavaOnlyArray.Companion.deepClone(readableMap.getArray(nextKey)));
                        break;
                    default:
                        throw new NoWhenBranchMatchedException();
                }
            }
            return javaOnlyMap;
        }
    }

    public JavaOnlyMap() {
        this.backingMap = new HashMap();
    }

    private JavaOnlyMap(Object... objArr) {
        this();
        if (objArr.length % 2 == 0) {
            int i = 0;
            int progressionLastElement = ProgressionUtilKt.getProgressionLastElement(0, objArr.length - 1, 2);
            if (progressionLastElement >= 0) {
                while (true) {
                    Double d = objArr[i + 1];
                    d = d instanceof Number ? Double.valueOf(d.doubleValue()) : d;
                    Map<String, Object> map = this.backingMap;
                    String str = objArr[i];
                    Intrinsics.checkNotNull(str, "null cannot be cast to non-null type kotlin.String");
                    map.put(str, d);
                    if (i != progressionLastElement) {
                        i += 2;
                    } else {
                        return;
                    }
                }
            }
        } else {
            throw new IllegalArgumentException("You must provide the same number of keys and values");
        }
    }

    public boolean hasKey(String str) {
        Intrinsics.checkNotNullParameter(str, "name");
        return this.backingMap.containsKey(str);
    }

    public boolean isNull(String str) {
        Intrinsics.checkNotNullParameter(str, "name");
        return this.backingMap.get(str) == null;
    }

    public boolean getBoolean(String str) {
        Intrinsics.checkNotNullParameter(str, "name");
        Object obj = this.backingMap.get(str);
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Boolean");
        return ((Boolean) obj).booleanValue();
    }

    public double getDouble(String str) {
        Intrinsics.checkNotNullParameter(str, "name");
        Object obj = this.backingMap.get(str);
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Number");
        return ((Number) obj).doubleValue();
    }

    public int getInt(String str) {
        Intrinsics.checkNotNullParameter(str, "name");
        Object obj = this.backingMap.get(str);
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Number");
        return ((Number) obj).intValue();
    }

    public long getLong(String str) {
        Intrinsics.checkNotNullParameter(str, "name");
        Object obj = this.backingMap.get(str);
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Number");
        return ((Number) obj).longValue();
    }

    public String getString(String str) {
        Intrinsics.checkNotNullParameter(str, "name");
        return (String) this.backingMap.get(str);
    }

    public ReadableMap getMap(String str) {
        Intrinsics.checkNotNullParameter(str, "name");
        return (ReadableMap) this.backingMap.get(str);
    }

    public ReadableArray getArray(String str) {
        Intrinsics.checkNotNullParameter(str, "name");
        return (ReadableArray) this.backingMap.get(str);
    }

    public Dynamic getDynamic(String str) {
        Intrinsics.checkNotNullParameter(str, "name");
        DynamicFromMap create = DynamicFromMap.create(this, str);
        Intrinsics.checkNotNullExpressionValue(create, "create(...)");
        return create;
    }

    public ReadableType getType(String str) {
        Intrinsics.checkNotNullParameter(str, "name");
        Object obj = this.backingMap.get(str);
        if (obj == null) {
            return ReadableType.Null;
        }
        if (obj instanceof Number) {
            return ReadableType.Number;
        }
        if (obj instanceof String) {
            return ReadableType.String;
        }
        if (obj instanceof Boolean) {
            return ReadableType.Boolean;
        }
        if (obj instanceof ReadableMap) {
            return ReadableType.Map;
        }
        if (obj instanceof ReadableArray) {
            return ReadableType.Array;
        }
        if (obj instanceof Dynamic) {
            return ((Dynamic) obj).getType();
        }
        throw new IllegalArgumentException("Invalid value " + obj + " for key " + str + " contained in JavaOnlyMap");
    }

    public Iterator<Map.Entry<String, Object>> getEntryIterator() {
        return this.backingMap.entrySet().iterator();
    }

    public ReadableMapKeySetIterator keySetIterator() {
        return new JavaOnlyMap$keySetIterator$1(this);
    }

    public void putBoolean(String str, boolean z) {
        Intrinsics.checkNotNullParameter(str, "key");
        this.backingMap.put(str, Boolean.valueOf(z));
    }

    public void putDouble(String str, double d) {
        Intrinsics.checkNotNullParameter(str, "key");
        this.backingMap.put(str, Double.valueOf(d));
    }

    public void putInt(String str, int i) {
        Intrinsics.checkNotNullParameter(str, "key");
        this.backingMap.put(str, Double.valueOf((double) i));
    }

    public void putLong(String str, long j) {
        Intrinsics.checkNotNullParameter(str, "key");
        this.backingMap.put(str, Double.valueOf((double) j));
    }

    public void putString(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "key");
        this.backingMap.put(str, str2);
    }

    public void putNull(String str) {
        Intrinsics.checkNotNullParameter(str, "key");
        this.backingMap.put(str, (Object) null);
    }

    public void putMap(String str, ReadableMap readableMap) {
        Intrinsics.checkNotNullParameter(str, "key");
        this.backingMap.put(str, readableMap);
    }

    public void merge(ReadableMap readableMap) {
        Intrinsics.checkNotNullParameter(readableMap, "source");
        this.backingMap.putAll(((JavaOnlyMap) readableMap).backingMap);
    }

    public WritableMap copy() {
        JavaOnlyMap javaOnlyMap = new JavaOnlyMap();
        javaOnlyMap.merge(this);
        return javaOnlyMap;
    }

    public void putArray(String str, ReadableArray readableArray) {
        Intrinsics.checkNotNullParameter(str, "key");
        this.backingMap.put(str, readableArray);
    }

    public final void remove(String str) {
        Intrinsics.checkNotNullParameter(str, "key");
        this.backingMap.remove(str);
    }

    public HashMap<String, Object> toHashMap() {
        return new HashMap<>(this.backingMap);
    }

    public String toString() {
        return this.backingMap.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null) {
            if (Intrinsics.areEqual((Object) JavaOnlyMap.class, (Object) obj.getClass())) {
                return Intrinsics.areEqual((Object) this.backingMap, (Object) ((JavaOnlyMap) obj).backingMap);
            }
        }
        return false;
    }

    public int hashCode() {
        return this.backingMap.hashCode();
    }
}
