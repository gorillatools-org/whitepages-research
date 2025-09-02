package com.swmansion.gesturehandler;

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.events.Event;
import kotlin.jvm.internal.Intrinsics;

public abstract class ReactContextExtensionsKt {
    public static final void dispatchEvent(ReactContext reactContext, Event event) {
        Intrinsics.checkNotNullParameter(reactContext, "<this>");
        Intrinsics.checkNotNullParameter(event, "event");
        try {
            NativeModule nativeModule = reactContext.getNativeModule(UIManagerModule.class);
            Intrinsics.checkNotNull(nativeModule);
            ((UIManagerModule) nativeModule).getEventDispatcher().dispatchEvent(event);
        } catch (NullPointerException e) {
            throw new Exception("Couldn't get an instance of UIManagerModule. Gesture Handler is unable to send an event.", e);
        }
    }
}
