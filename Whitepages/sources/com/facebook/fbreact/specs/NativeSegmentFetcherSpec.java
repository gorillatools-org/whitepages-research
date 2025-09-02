package com.facebook.fbreact.specs;

import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.turbomodule.core.interfaces.TurboModule;

public abstract class NativeSegmentFetcherSpec extends ReactContextBaseJavaModule implements TurboModule {
    public static final String NAME = "SegmentFetcher";

    @DoNotStrip
    @ReactMethod
    public abstract void fetchSegment(double d, ReadableMap readableMap, Callback callback);

    @DoNotStrip
    @ReactMethod
    public void getSegment(double d, ReadableMap readableMap, Callback callback) {
    }

    public NativeSegmentFetcherSpec(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    public String getName() {
        return NAME;
    }
}
