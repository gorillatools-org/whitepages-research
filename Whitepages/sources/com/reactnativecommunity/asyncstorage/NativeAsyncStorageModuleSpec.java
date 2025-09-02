package com.reactnativecommunity.asyncstorage;

import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReactModuleWithSpec;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.turbomodule.core.interfaces.TurboModule;

public abstract class NativeAsyncStorageModuleSpec extends ReactContextBaseJavaModule implements ReactModuleWithSpec, TurboModule {
    @DoNotStrip
    @ReactMethod
    public abstract void clear(Callback callback);

    @DoNotStrip
    @ReactMethod
    public abstract void getAllKeys(Callback callback);

    @DoNotStrip
    @ReactMethod
    public abstract void multiGet(ReadableArray readableArray, Callback callback);

    @DoNotStrip
    @ReactMethod
    public abstract void multiMerge(ReadableArray readableArray, Callback callback);

    @DoNotStrip
    @ReactMethod
    public abstract void multiRemove(ReadableArray readableArray, Callback callback);

    @DoNotStrip
    @ReactMethod
    public abstract void multiSet(ReadableArray readableArray, Callback callback);

    public NativeAsyncStorageModuleSpec(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }
}
