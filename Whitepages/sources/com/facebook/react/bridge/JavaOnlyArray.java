package com.facebook.react.bridge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class JavaOnlyArray implements ReadableArray, WritableArray {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final List<Object> backingList;

    public /* synthetic */ JavaOnlyArray(List list, DefaultConstructorMarker defaultConstructorMarker) {
        this((List<?>) list);
    }

    public /* synthetic */ JavaOnlyArray(Object[] objArr, DefaultConstructorMarker defaultConstructorMarker) {
        this(objArr);
    }

    public static final JavaOnlyArray deepClone(ReadableArray readableArray) {
        return Companion.deepClone(readableArray);
    }

    public static final JavaOnlyArray from(List<?> list) {
        return Companion.from(list);
    }

    public static final JavaOnlyArray of(Object... objArr) {
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
                throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.bridge.JavaOnlyArray.Companion.WhenMappings.<clinit>():void");
            }
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final JavaOnlyArray from(List<?> list) {
            Intrinsics.checkNotNullParameter(list, "list");
            return new JavaOnlyArray((List) list, (DefaultConstructorMarker) null);
        }

        public final JavaOnlyArray of(Object... objArr) {
            Intrinsics.checkNotNullParameter(objArr, "values");
            return new JavaOnlyArray(Arrays.copyOf(objArr, objArr.length), (DefaultConstructorMarker) null);
        }

        public final JavaOnlyArray deepClone(ReadableArray readableArray) {
            JavaOnlyArray javaOnlyArray = new JavaOnlyArray();
            if (readableArray == null) {
                return javaOnlyArray;
            }
            int size = readableArray.size();
            for (int i = 0; i < size; i++) {
                switch (WhenMappings.$EnumSwitchMapping$0[readableArray.getType(i).ordinal()]) {
                    case 1:
                        javaOnlyArray.pushNull();
                        break;
                    case 2:
                        javaOnlyArray.pushBoolean(readableArray.getBoolean(i));
                        break;
                    case 3:
                        javaOnlyArray.pushDouble(readableArray.getDouble(i));
                        break;
                    case 4:
                        javaOnlyArray.pushString(readableArray.getString(i));
                        break;
                    case 5:
                        javaOnlyArray.pushMap(JavaOnlyMap.Companion.deepClone(readableArray.getMap(i)));
                        break;
                    case 6:
                        javaOnlyArray.pushArray(JavaOnlyArray.Companion.deepClone(readableArray.getArray(i)));
                        break;
                    default:
                        throw new NoWhenBranchMatchedException();
                }
            }
            return javaOnlyArray;
        }
    }

    private JavaOnlyArray(Object... objArr) {
        this.backingList = CollectionsKt.mutableListOf(Arrays.copyOf(objArr, objArr.length));
    }

    private JavaOnlyArray(List<?> list) {
        this.backingList = new ArrayList(list);
    }

    public JavaOnlyArray() {
        this.backingList = new ArrayList();
    }

    public int size() {
        return this.backingList.size();
    }

    public boolean isNull(int i) {
        return this.backingList.get(i) == null;
    }

    public double getDouble(int i) {
        Object obj = this.backingList.get(i);
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Number");
        return ((Number) obj).doubleValue();
    }

    public int getInt(int i) {
        Object obj = this.backingList.get(i);
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Number");
        return ((Number) obj).intValue();
    }

    public long getLong(int i) {
        Object obj = this.backingList.get(i);
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Number");
        return ((Number) obj).longValue();
    }

    public String getString(int i) {
        return (String) this.backingList.get(i);
    }

    public ReadableArray getArray(int i) {
        return (ReadableArray) this.backingList.get(i);
    }

    public boolean getBoolean(int i) {
        Object obj = this.backingList.get(i);
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Boolean");
        return ((Boolean) obj).booleanValue();
    }

    public ReadableMap getMap(int i) {
        return (ReadableMap) this.backingList.get(i);
    }

    public Dynamic getDynamic(int i) {
        DynamicFromArray create = DynamicFromArray.create(this, i);
        Intrinsics.checkNotNullExpressionValue(create, "create(...)");
        return create;
    }

    public ReadableType getType(int i) {
        Object obj = this.backingList.get(i);
        if (obj == null) {
            return ReadableType.Null;
        }
        if (obj instanceof Boolean) {
            return ReadableType.Boolean;
        }
        if ((obj instanceof Double) || (obj instanceof Float) || (obj instanceof Integer) || (obj instanceof Long)) {
            return ReadableType.Number;
        }
        if (obj instanceof String) {
            return ReadableType.String;
        }
        if (obj instanceof ReadableArray) {
            return ReadableType.Array;
        }
        if (obj instanceof ReadableMap) {
            return ReadableType.Map;
        }
        Class<?> cls = obj.getClass();
        throw new IllegalStateException("Invalid type " + cls + ")");
    }

    public void pushBoolean(boolean z) {
        this.backingList.add(Boolean.valueOf(z));
    }

    public void pushDouble(double d) {
        this.backingList.add(Double.valueOf(d));
    }

    public void pushInt(int i) {
        this.backingList.add(Double.valueOf((double) i));
    }

    public void pushLong(long j) {
        this.backingList.add(Double.valueOf((double) j));
    }

    public void pushString(String str) {
        this.backingList.add(str);
    }

    public void pushArray(ReadableArray readableArray) {
        this.backingList.add(readableArray);
    }

    public void pushMap(ReadableMap readableMap) {
        this.backingList.add(readableMap);
    }

    public void pushNull() {
        this.backingList.add((Object) null);
    }

    public ArrayList<Object> toArrayList() {
        return new ArrayList<>(this.backingList);
    }

    public String toString() {
        return this.backingList.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!Intrinsics.areEqual((Object) JavaOnlyArray.class, (Object) obj.getClass())) {
            return false;
        }
        return Intrinsics.areEqual((Object) this.backingList, (Object) ((JavaOnlyArray) obj).backingList);
    }

    public int hashCode() {
        return this.backingList.hashCode();
    }
}
