package com.swmansion.gesturehandler.react.eventbuilders;

import com.swmansion.gesturehandler.core.ManualGestureHandler;
import kotlin.jvm.internal.Intrinsics;

public final class ManualGestureHandlerEventDataBuilder extends GestureHandlerEventDataBuilder {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ManualGestureHandlerEventDataBuilder(ManualGestureHandler manualGestureHandler) {
        super(manualGestureHandler);
        Intrinsics.checkNotNullParameter(manualGestureHandler, "handler");
    }
}
