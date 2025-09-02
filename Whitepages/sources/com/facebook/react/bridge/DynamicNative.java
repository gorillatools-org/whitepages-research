package com.facebook.react.bridge;

import com.facebook.jni.HybridClassBase;
import com.facebook.proguard.annotations.DoNotStripAny;

@DoNotStripAny
final class DynamicNative extends HybridClassBase implements Dynamic {
    private final native ReadableType getTypeNative();

    private final native boolean isNullNative();

    public native ReadableArray asArray();

    public native boolean asBoolean();

    public native double asDouble();

    public native ReadableMap asMap();

    public native String asString();

    public void recycle() {
    }

    public ReadableType getType() {
        return getTypeNative();
    }

    public boolean isNull() {
        return isNullNative();
    }

    public int asInt() {
        return (int) asDouble();
    }
}
