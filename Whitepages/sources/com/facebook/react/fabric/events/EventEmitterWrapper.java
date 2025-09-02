package com.facebook.react.fabric.events;

import android.annotation.SuppressLint;
import com.facebook.jni.HybridClassBase;
import com.facebook.proguard.annotations.DoNotStripAny;
import com.facebook.react.bridge.NativeMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.fabric.FabricSoLoader;
import com.salesforce.marketingcloud.config.a;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@DoNotStripAny
@SuppressLint({"MissingNativeLoadLibrary"})
public final class EventEmitterWrapper extends HybridClassBase {
    private static final Companion Companion = new Companion((DefaultConstructorMarker) null);

    private final native void dispatchEvent(String str, NativeMap nativeMap, int i);

    private final native void dispatchEventSynchronously(String str, NativeMap nativeMap);

    private final native void dispatchUniqueEvent(String str, NativeMap nativeMap);

    private EventEmitterWrapper() {
    }

    public final synchronized void dispatch(String str, WritableMap writableMap, int i) {
        Intrinsics.checkNotNullParameter(str, a.h);
        if (isValid()) {
            dispatchEvent(str, (NativeMap) writableMap, i);
        }
    }

    public final synchronized void dispatchEventSynchronously(String str, WritableMap writableMap) {
        Intrinsics.checkNotNullParameter(str, a.h);
        if (isValid()) {
            dispatchEventSynchronously(str, (NativeMap) writableMap);
        }
    }

    public final synchronized void dispatchUnique(String str, WritableMap writableMap) {
        Intrinsics.checkNotNullParameter(str, a.h);
        if (isValid()) {
            dispatchUniqueEvent(str, (NativeMap) writableMap);
        }
    }

    public final synchronized void destroy() {
        if (isValid()) {
            resetNative();
        }
    }

    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    static {
        FabricSoLoader.staticInit();
    }
}
