package com.wpconnect;

import com.facebook.react.ReactInstanceEventListener;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.bridge.ReactContext;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

public final class CallerIdOverlayService$getOrWaitForReactContext$listener$1 implements ReactInstanceEventListener {
    final /* synthetic */ AtomicBoolean $listenerCalled;
    final /* synthetic */ Function1 $onReady;
    final /* synthetic */ ReactInstanceManager $reactInstanceManager;

    CallerIdOverlayService$getOrWaitForReactContext$listener$1(AtomicBoolean atomicBoolean, ReactInstanceManager reactInstanceManager, Function1 function1) {
        this.$listenerCalled = atomicBoolean;
        this.$reactInstanceManager = reactInstanceManager;
        this.$onReady = function1;
    }

    public void onReactContextInitialized(ReactContext reactContext) {
        Intrinsics.checkNotNullParameter(reactContext, "context");
        if (this.$listenerCalled.compareAndSet(false, true)) {
            this.$reactInstanceManager.removeReactInstanceEventListener(this);
            this.$onReady.invoke(reactContext);
        }
    }
}
