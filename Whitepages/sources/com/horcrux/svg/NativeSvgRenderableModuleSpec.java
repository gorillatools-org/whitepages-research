package com.horcrux.svg;

import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReactModuleWithSpec;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.turbomodule.core.interfaces.TurboModule;

public abstract class NativeSvgRenderableModuleSpec extends ReactContextBaseJavaModule implements ReactModuleWithSpec, TurboModule {
    public static final String NAME = "RNSVGRenderableModule";

    @DoNotStrip
    @ReactMethod(isBlockingSynchronousMethod = true)
    public abstract WritableMap getBBox(Double d, ReadableMap readableMap);

    @DoNotStrip
    @ReactMethod(isBlockingSynchronousMethod = true)
    public abstract WritableMap getCTM(Double d);

    @DoNotStrip
    @ReactMethod(isBlockingSynchronousMethod = true)
    public abstract WritableMap getPointAtLength(Double d, ReadableMap readableMap);

    @DoNotStrip
    @ReactMethod
    public abstract void getRawResource(String str, Promise promise);

    @DoNotStrip
    @ReactMethod(isBlockingSynchronousMethod = true)
    public abstract WritableMap getScreenCTM(Double d);

    @DoNotStrip
    @ReactMethod(isBlockingSynchronousMethod = true)
    public abstract double getTotalLength(Double d);

    @DoNotStrip
    @ReactMethod(isBlockingSynchronousMethod = true)
    public abstract boolean isPointInFill(Double d, ReadableMap readableMap);

    @DoNotStrip
    @ReactMethod(isBlockingSynchronousMethod = true)
    public abstract boolean isPointInStroke(Double d, ReadableMap readableMap);

    public NativeSvgRenderableModuleSpec(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    public String getName() {
        return "RNSVGRenderableModule";
    }
}
