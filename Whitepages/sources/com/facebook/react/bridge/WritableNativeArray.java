package com.facebook.react.bridge;

import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public final class WritableNativeArray extends ReadableNativeArray implements WritableArray {
    private final native void initHybrid();

    private final native void pushNativeArray(ReadableNativeArray readableNativeArray);

    private final native void pushNativeMap(ReadableNativeMap readableNativeMap);

    public native void pushBoolean(boolean z);

    public native void pushDouble(double d);

    public native void pushInt(int i);

    public native void pushLong(long j);

    public native void pushNull();

    public native void pushString(String str);

    public WritableNativeArray() {
        initHybrid();
    }

    public void pushArray(ReadableArray readableArray) {
        if (readableArray == null || (readableArray instanceof ReadableNativeArray)) {
            pushNativeArray((ReadableNativeArray) readableArray);
            return;
        }
        throw new IllegalStateException("Illegal type provided");
    }

    public void pushMap(ReadableMap readableMap) {
        if (readableMap == null || (readableMap instanceof ReadableNativeMap)) {
            pushNativeMap((ReadableNativeMap) readableMap);
            return;
        }
        throw new IllegalStateException("Illegal type provided");
    }
}
