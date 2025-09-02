package com.facebook.react.bridge;

import com.facebook.proguard.annotations.DoNotStrip;
import java.util.ArrayList;
import java.util.Arrays;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@DoNotStrip
public class ReadableNativeArray extends NativeArray implements ReadableArray {
    private static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static int jniPassCounter;
    private final Lazy localArray$delegate;
    private final Lazy localTypeArray$delegate;

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
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.bridge.ReadableNativeArray.WhenMappings.<clinit>():void");
        }
    }

    public static final int getJNIPassCounter() {
        return Companion.getJNIPassCounter();
    }

    private final native Object[] importArray();

    private final native Object[] importTypeArray();

    protected ReadableNativeArray() {
        LazyThreadSafetyMode lazyThreadSafetyMode = LazyThreadSafetyMode.SYNCHRONIZED;
        this.localArray$delegate = LazyKt.lazy(lazyThreadSafetyMode, new ReadableNativeArray$$ExternalSyntheticLambda0(this));
        this.localTypeArray$delegate = LazyKt.lazy(lazyThreadSafetyMode, new ReadableNativeArray$$ExternalSyntheticLambda1(this));
    }

    private final Object[] getLocalArray() {
        return (Object[]) this.localArray$delegate.getValue();
    }

    /* access modifiers changed from: private */
    public static final Object[] localArray_delegate$lambda$0(ReadableNativeArray readableNativeArray) {
        jniPassCounter++;
        return readableNativeArray.importArray();
    }

    private final ReadableType[] getLocalTypeArray() {
        Object value = this.localTypeArray$delegate.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
        return (ReadableType[]) value;
    }

    /* access modifiers changed from: private */
    public static final ReadableType[] localTypeArray_delegate$lambda$1(ReadableNativeArray readableNativeArray) {
        jniPassCounter++;
        Object[] importTypeArray = readableNativeArray.importTypeArray();
        return (ReadableType[]) Arrays.copyOf(importTypeArray, importTypeArray.length, ReadableType[].class);
    }

    public int size() {
        return getLocalArray().length;
    }

    public boolean isNull(int i) {
        return getLocalArray()[i] == null;
    }

    public boolean getBoolean(int i) {
        Object obj = getLocalArray()[i];
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Boolean");
        return ((Boolean) obj).booleanValue();
    }

    public double getDouble(int i) {
        Object obj = getLocalArray()[i];
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Double");
        return ((Double) obj).doubleValue();
    }

    public int getInt(int i) {
        Object obj = getLocalArray()[i];
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Double");
        return (int) ((Double) obj).doubleValue();
    }

    public long getLong(int i) {
        Object obj = getLocalArray()[i];
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Long");
        return ((Long) obj).longValue();
    }

    public String getString(int i) {
        return (String) getLocalArray()[i];
    }

    public ReadableNativeArray getArray(int i) {
        return (ReadableNativeArray) getLocalArray()[i];
    }

    public ReadableNativeMap getMap(int i) {
        return (ReadableNativeMap) getLocalArray()[i];
    }

    public ReadableType getType(int i) {
        return getLocalTypeArray()[i];
    }

    public Dynamic getDynamic(int i) {
        DynamicFromArray create = DynamicFromArray.create(this, i);
        Intrinsics.checkNotNullExpressionValue(create, "create(...)");
        return create;
    }

    public int hashCode() {
        return getLocalArray().hashCode();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ReadableNativeArray)) {
            return false;
        }
        return ArraysKt.contentDeepEquals(getLocalArray(), ((ReadableNativeArray) obj).getLocalArray());
    }

    public ArrayList<Object> toArrayList() {
        ArrayList<Object> arrayList = new ArrayList<>();
        int size = size();
        for (int i = 0; i < size; i++) {
            Object obj = null;
            switch (WhenMappings.$EnumSwitchMapping$0[getType(i).ordinal()]) {
                case 1:
                    arrayList.add((Object) null);
                    break;
                case 2:
                    arrayList.add(Boolean.valueOf(getBoolean(i)));
                    break;
                case 3:
                    arrayList.add(Double.valueOf(getDouble(i)));
                    break;
                case 4:
                    arrayList.add(getString(i));
                    break;
                case 5:
                    ReadableNativeMap map = getMap(i);
                    if (map != null) {
                        obj = map.toHashMap();
                    }
                    arrayList.add(obj);
                    break;
                case 6:
                    ReadableNativeArray array = getArray(i);
                    if (array != null) {
                        obj = array.toArrayList();
                    }
                    arrayList.add(obj);
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
        }
        return arrayList;
    }

    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final int getJNIPassCounter() {
            return ReadableNativeArray.jniPassCounter;
        }
    }
}
