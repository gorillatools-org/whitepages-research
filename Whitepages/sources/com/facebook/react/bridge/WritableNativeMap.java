package com.facebook.react.bridge;

import com.facebook.infer.annotation.Assertions;
import com.facebook.proguard.annotations.DoNotStrip;
import kotlin.jvm.internal.Intrinsics;

@DoNotStrip
public final class WritableNativeMap extends ReadableNativeMap implements WritableMap {
    private final native void initHybrid();

    private final native void mergeNativeMap(ReadableNativeMap readableNativeMap);

    private final native void putNativeArray(String str, ReadableNativeArray readableNativeArray);

    private final native void putNativeMap(String str, ReadableNativeMap readableNativeMap);

    public native void putBoolean(String str, boolean z);

    public native void putDouble(String str, double d);

    public native void putInt(String str, int i);

    public native void putLong(String str, long j);

    public native void putNull(String str);

    public native void putString(String str, String str2);

    public WritableNativeMap() {
        initHybrid();
    }

    public void putMap(String str, ReadableMap readableMap) {
        Intrinsics.checkNotNullParameter(str, "key");
        Assertions.assertCondition(readableMap == null || (readableMap instanceof ReadableNativeMap), "Illegal type provided");
        putNativeMap(str, (ReadableNativeMap) readableMap);
    }

    public void putArray(String str, ReadableArray readableArray) {
        Intrinsics.checkNotNullParameter(str, "key");
        Assertions.assertCondition(readableArray == null || (readableArray instanceof ReadableNativeArray), "Illegal type provided");
        putNativeArray(str, (ReadableNativeArray) readableArray);
    }

    public void merge(ReadableMap readableMap) {
        Intrinsics.checkNotNullParameter(readableMap, "source");
        Assertions.assertCondition(readableMap instanceof ReadableNativeMap, "Illegal type provided");
        mergeNativeMap((ReadableNativeMap) readableMap);
    }

    public WritableMap copy() {
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        writableNativeMap.merge(this);
        return writableNativeMap;
    }
}
