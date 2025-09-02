package com.facebook.react.bridge;

import android.annotation.SuppressLint;
import com.facebook.infer.annotation.Assertions;
import com.facebook.proguard.annotations.DoNotStripAny;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@DoNotStripAny
public class ReadableNativeMap extends NativeMap implements ReadableMap {
    private static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static int jniPassCounter;
    private final Lazy keys$delegate;
    private final Lazy localMap$delegate;
    private final Lazy localTypeMap$delegate;

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
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.bridge.ReadableNativeMap.WhenMappings.<clinit>():void");
        }
    }

    public static final int getJNIPassCounter() {
        return Companion.getJNIPassCounter();
    }

    private final native String[] importKeys();

    private final native Object[] importTypes();

    private final native Object[] importValues();

    protected ReadableNativeMap() {
        LazyThreadSafetyMode lazyThreadSafetyMode = LazyThreadSafetyMode.SYNCHRONIZED;
        this.keys$delegate = LazyKt.lazy(lazyThreadSafetyMode, new ReadableNativeMap$$ExternalSyntheticLambda0(this));
        this.localMap$delegate = LazyKt.lazy(lazyThreadSafetyMode, new ReadableNativeMap$$ExternalSyntheticLambda1(this));
        this.localTypeMap$delegate = LazyKt.lazy(lazyThreadSafetyMode, new ReadableNativeMap$$ExternalSyntheticLambda2(this));
    }

    private final String[] getKeys() {
        return (String[]) this.keys$delegate.getValue();
    }

    /* access modifiers changed from: private */
    public static final String[] keys_delegate$lambda$1(ReadableNativeMap readableNativeMap) {
        String[] importKeys = readableNativeMap.importKeys();
        jniPassCounter++;
        return importKeys;
    }

    private final HashMap<String, Object> getLocalMap() {
        return (HashMap) this.localMap$delegate.getValue();
    }

    /* access modifiers changed from: private */
    public static final HashMap localMap_delegate$lambda$2(ReadableNativeMap readableNativeMap) {
        int length = readableNativeMap.getKeys().length;
        HashMap hashMap = new HashMap(length);
        Object[] importValues = readableNativeMap.importValues();
        jniPassCounter++;
        for (int i = 0; i < length; i++) {
            hashMap.put(readableNativeMap.getKeys()[i], importValues[i]);
        }
        return hashMap;
    }

    private final HashMap<String, ReadableType> getLocalTypeMap() {
        return (HashMap) this.localTypeMap$delegate.getValue();
    }

    /* access modifiers changed from: private */
    public static final HashMap localTypeMap_delegate$lambda$3(ReadableNativeMap readableNativeMap) {
        int length = readableNativeMap.getKeys().length;
        HashMap hashMap = new HashMap(length);
        Object[] importTypes = readableNativeMap.importTypes();
        jniPassCounter++;
        for (int i = 0; i < length; i++) {
            String str = readableNativeMap.getKeys()[i];
            Object obj = importTypes[i];
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type com.facebook.react.bridge.ReadableType");
            hashMap.put(str, (ReadableType) obj);
        }
        return hashMap;
    }

    public boolean hasKey(String str) {
        Intrinsics.checkNotNullParameter(str, "name");
        return getLocalMap().containsKey(str);
    }

    public boolean isNull(String str) {
        Intrinsics.checkNotNullParameter(str, "name");
        if (getLocalMap().containsKey(str)) {
            return getLocalMap().get(str) == null;
        }
        throw new NoSuchKeyException(str);
    }

    @SuppressLint({"ReflectionMethodUse"})
    private final /* synthetic */ <T> T checkInstance(String str, Object obj, Class<T> cls) {
        Intrinsics.reifiedOperationMarker(2, "T");
        if (obj != null) {
            return obj;
        }
        String simpleName = obj != null ? obj.getClass().getSimpleName() : "NULL";
        String simpleName2 = cls.getSimpleName();
        throw new UnexpectedNativeTypeException("Value for " + str + " cannot be cast from " + simpleName + " to " + simpleName2);
    }

    private final Object getValue(String str) {
        if (hasKey(str)) {
            Object assertNotNull = Assertions.assertNotNull(getLocalMap().get(str));
            Intrinsics.checkNotNullExpressionValue(assertNotNull, "assertNotNull(...)");
            return assertNotNull;
        }
        throw new NoSuchKeyException(str);
    }

    private final /* synthetic */ <T> T getValue(String str, Class<T> cls) {
        T value = getValue(str);
        Intrinsics.reifiedOperationMarker(2, "T");
        if (value != null) {
            return value;
        }
        String simpleName = value != null ? value.getClass().getSimpleName() : "NULL";
        String simpleName2 = cls.getSimpleName();
        throw new UnexpectedNativeTypeException("Value for " + str + " cannot be cast from " + simpleName + " to " + simpleName2);
    }

    private final Object getNullableValue(String str) {
        return getLocalMap().get(str);
    }

    private final /* synthetic */ <T> T getNullableValue(String str, Class<T> cls) {
        T nullableValue = getNullableValue(str);
        if (nullableValue == null) {
            return null;
        }
        Intrinsics.reifiedOperationMarker(2, "T");
        return nullableValue;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v1, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v0, resolved type: com.facebook.react.bridge.ReadableArray} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: com.facebook.react.bridge.ReadableArray} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: com.facebook.react.bridge.ReadableArray} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v5, resolved type: com.facebook.react.bridge.ReadableArray} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.facebook.react.bridge.ReadableArray getArray(java.lang.String r6) {
        /*
            r5 = this;
            java.lang.String r0 = "name"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            java.lang.Object r0 = r5.getNullableValue(r6)
            r1 = 0
            if (r0 != 0) goto L_0x000d
            goto L_0x0017
        L_0x000d:
            boolean r2 = r0 instanceof com.facebook.react.bridge.ReadableArray
            if (r2 != 0) goto L_0x0012
            goto L_0x0013
        L_0x0012:
            r1 = r0
        L_0x0013:
            com.facebook.react.bridge.ReadableArray r1 = (com.facebook.react.bridge.ReadableArray) r1
            if (r1 == 0) goto L_0x0018
        L_0x0017:
            return r1
        L_0x0018:
            com.facebook.react.bridge.UnexpectedNativeTypeException r1 = new com.facebook.react.bridge.UnexpectedNativeTypeException
            java.lang.Class r0 = r0.getClass()
            java.lang.String r0 = r0.getSimpleName()
            java.lang.Class<com.facebook.react.bridge.ReadableArray> r2 = com.facebook.react.bridge.ReadableArray.class
            java.lang.String r2 = r2.getSimpleName()
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Value for "
            r3.append(r4)
            r3.append(r6)
            java.lang.String r6 = " cannot be cast from "
            r3.append(r6)
            r3.append(r0)
            java.lang.String r6 = " to "
            r3.append(r6)
            r3.append(r2)
            java.lang.String r6 = r3.toString()
            r1.<init>(r6)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.bridge.ReadableNativeMap.getArray(java.lang.String):com.facebook.react.bridge.ReadableArray");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v1, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v0, resolved type: com.facebook.react.bridge.ReadableNativeMap} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: com.facebook.react.bridge.ReadableNativeMap} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: com.facebook.react.bridge.ReadableNativeMap} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v5, resolved type: com.facebook.react.bridge.ReadableNativeMap} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.facebook.react.bridge.ReadableNativeMap getMap(java.lang.String r6) {
        /*
            r5 = this;
            java.lang.String r0 = "name"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            java.lang.Object r0 = r5.getNullableValue(r6)
            r1 = 0
            if (r0 != 0) goto L_0x000d
            goto L_0x0017
        L_0x000d:
            boolean r2 = r0 instanceof com.facebook.react.bridge.ReadableNativeMap
            if (r2 != 0) goto L_0x0012
            goto L_0x0013
        L_0x0012:
            r1 = r0
        L_0x0013:
            com.facebook.react.bridge.ReadableNativeMap r1 = (com.facebook.react.bridge.ReadableNativeMap) r1
            if (r1 == 0) goto L_0x0018
        L_0x0017:
            return r1
        L_0x0018:
            com.facebook.react.bridge.UnexpectedNativeTypeException r1 = new com.facebook.react.bridge.UnexpectedNativeTypeException
            java.lang.Class r0 = r0.getClass()
            java.lang.String r0 = r0.getSimpleName()
            java.lang.Class<com.facebook.react.bridge.ReadableNativeMap> r2 = com.facebook.react.bridge.ReadableNativeMap.class
            java.lang.String r2 = r2.getSimpleName()
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Value for "
            r3.append(r4)
            r3.append(r6)
            java.lang.String r6 = " cannot be cast from "
            r3.append(r6)
            r3.append(r0)
            java.lang.String r6 = " to "
            r3.append(r6)
            r3.append(r2)
            java.lang.String r6 = r3.toString()
            r1.<init>(r6)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.bridge.ReadableNativeMap.getMap(java.lang.String):com.facebook.react.bridge.ReadableNativeMap");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v1, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v0, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v5, resolved type: java.lang.String} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getString(java.lang.String r6) {
        /*
            r5 = this;
            java.lang.String r0 = "name"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            java.lang.Object r0 = r5.getNullableValue(r6)
            r1 = 0
            if (r0 != 0) goto L_0x000d
            goto L_0x0017
        L_0x000d:
            boolean r2 = r0 instanceof java.lang.String
            if (r2 != 0) goto L_0x0012
            goto L_0x0013
        L_0x0012:
            r1 = r0
        L_0x0013:
            java.lang.String r1 = (java.lang.String) r1
            if (r1 == 0) goto L_0x0018
        L_0x0017:
            return r1
        L_0x0018:
            com.facebook.react.bridge.UnexpectedNativeTypeException r1 = new com.facebook.react.bridge.UnexpectedNativeTypeException
            java.lang.Class r0 = r0.getClass()
            java.lang.String r0 = r0.getSimpleName()
            java.lang.Class<java.lang.String> r2 = java.lang.String.class
            java.lang.String r2 = r2.getSimpleName()
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Value for "
            r3.append(r4)
            r3.append(r6)
            java.lang.String r6 = " cannot be cast from "
            r3.append(r6)
            r3.append(r0)
            java.lang.String r6 = " to "
            r3.append(r6)
            r3.append(r2)
            java.lang.String r6 = r3.toString()
            r1.<init>(r6)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.bridge.ReadableNativeMap.getString(java.lang.String):java.lang.String");
    }

    public boolean getBoolean(String str) {
        Intrinsics.checkNotNullParameter(str, "name");
        Class cls = Boolean.TYPE;
        Object value = getValue(str);
        Boolean bool = (Boolean) (!(value instanceof Boolean) ? null : value);
        if (bool != null) {
            return bool.booleanValue();
        }
        String simpleName = value != null ? value.getClass().getSimpleName() : "NULL";
        String simpleName2 = cls.getSimpleName();
        throw new UnexpectedNativeTypeException("Value for " + str + " cannot be cast from " + simpleName + " to " + simpleName2);
    }

    public double getDouble(String str) {
        Intrinsics.checkNotNullParameter(str, "name");
        Class cls = Double.TYPE;
        Object value = getValue(str);
        Double d = (Double) (!(value instanceof Double) ? null : value);
        if (d != null) {
            return d.doubleValue();
        }
        String simpleName = value != null ? value.getClass().getSimpleName() : "NULL";
        String simpleName2 = cls.getSimpleName();
        throw new UnexpectedNativeTypeException("Value for " + str + " cannot be cast from " + simpleName + " to " + simpleName2);
    }

    public int getInt(String str) {
        Intrinsics.checkNotNullParameter(str, "name");
        Class cls = Double.TYPE;
        Object value = getValue(str);
        Double d = (Double) (!(value instanceof Double) ? null : value);
        if (d != null) {
            return (int) d.doubleValue();
        }
        String simpleName = value != null ? value.getClass().getSimpleName() : "NULL";
        String simpleName2 = cls.getSimpleName();
        throw new UnexpectedNativeTypeException("Value for " + str + " cannot be cast from " + simpleName + " to " + simpleName2);
    }

    public long getLong(String str) {
        Intrinsics.checkNotNullParameter(str, "name");
        Class cls = Long.TYPE;
        Object value = getValue(str);
        Long l = (Long) (!(value instanceof Long) ? null : value);
        if (l != null) {
            return l.longValue();
        }
        String simpleName = value != null ? value.getClass().getSimpleName() : "NULL";
        String simpleName2 = cls.getSimpleName();
        throw new UnexpectedNativeTypeException("Value for " + str + " cannot be cast from " + simpleName + " to " + simpleName2);
    }

    public ReadableType getType(String str) {
        Intrinsics.checkNotNullParameter(str, "name");
        ReadableType readableType = getLocalTypeMap().get(str);
        if (readableType != null) {
            return readableType;
        }
        throw new NoSuchKeyException(str);
    }

    public Dynamic getDynamic(String str) {
        Intrinsics.checkNotNullParameter(str, "name");
        DynamicFromMap create = DynamicFromMap.create(this, str);
        Intrinsics.checkNotNullExpressionValue(create, "create(...)");
        return create;
    }

    public Iterator<Map.Entry<String, Object>> getEntryIterator() {
        ReadableNativeMap$entryIterator$1$1 readableNativeMap$entryIterator$1$1;
        synchronized (this) {
            String[] keys = getKeys();
            Object[] importValues = importValues();
            jniPassCounter++;
            readableNativeMap$entryIterator$1$1 = new ReadableNativeMap$entryIterator$1$1(keys, importValues);
        }
        return readableNativeMap$entryIterator$1$1;
    }

    public ReadableMapKeySetIterator keySetIterator() {
        return new ReadableNativeMap$keySetIterator$1(getKeys());
    }

    public int hashCode() {
        return getLocalMap().hashCode();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ReadableNativeMap)) {
            return false;
        }
        return Intrinsics.areEqual((Object) getLocalMap(), (Object) ((ReadableNativeMap) obj).getLocalMap());
    }

    public HashMap<String, Object> toHashMap() {
        HashMap<String, Object> hashMap = new HashMap<>(getLocalMap());
        for (String next : hashMap.keySet()) {
            Intrinsics.checkNotNull(next, "null cannot be cast to non-null type kotlin.String");
            String str = next;
            switch (WhenMappings.$EnumSwitchMapping$0[getType(str).ordinal()]) {
                case 1:
                case 2:
                case 3:
                case 4:
                    break;
                case 5:
                    hashMap.put(str, ((ReadableNativeMap) Assertions.assertNotNull(getMap(str))).toHashMap());
                    break;
                case 6:
                    hashMap.put(str, ((ReadableArray) Assertions.assertNotNull(getArray(str))).toArrayList());
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
        }
        return hashMap;
    }

    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final int getJNIPassCounter() {
            return ReadableNativeMap.jniPassCounter;
        }
    }
}
