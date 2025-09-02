package com.swmansion.gesturehandler.react.eventbuilders;

import com.facebook.react.bridge.WritableMap;
import com.swmansion.gesturehandler.core.NativeViewGestureHandler;
import kotlin.jvm.internal.Intrinsics;

public final class NativeGestureHandlerEventDataBuilder extends GestureHandlerEventDataBuilder {
    private final boolean pointerInside;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public NativeGestureHandlerEventDataBuilder(NativeViewGestureHandler nativeViewGestureHandler) {
        super(nativeViewGestureHandler);
        Intrinsics.checkNotNullParameter(nativeViewGestureHandler, "handler");
        this.pointerInside = nativeViewGestureHandler.isWithinBounds();
    }

    public void buildEventData(WritableMap writableMap) {
        Intrinsics.checkNotNullParameter(writableMap, "eventData");
        super.buildEventData(writableMap);
        writableMap.putBoolean("pointerInside", this.pointerInside);
    }
}
