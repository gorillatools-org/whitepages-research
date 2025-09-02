package com.swmansion.gesturehandler.react.eventbuilders;

import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.PixelUtil;
import com.swmansion.gesturehandler.core.PinchGestureHandler;
import kotlin.jvm.internal.Intrinsics;

public final class PinchGestureHandlerEventDataBuilder extends GestureHandlerEventDataBuilder {
    private final float focalX;
    private final float focalY;
    private final double scale;
    private final double velocity;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public PinchGestureHandlerEventDataBuilder(PinchGestureHandler pinchGestureHandler) {
        super(pinchGestureHandler);
        Intrinsics.checkNotNullParameter(pinchGestureHandler, "handler");
        this.scale = pinchGestureHandler.getScale();
        this.focalX = pinchGestureHandler.getFocalPointX();
        this.focalY = pinchGestureHandler.getFocalPointY();
        this.velocity = pinchGestureHandler.getVelocity();
    }

    public void buildEventData(WritableMap writableMap) {
        Intrinsics.checkNotNullParameter(writableMap, "eventData");
        super.buildEventData(writableMap);
        writableMap.putDouble("scale", this.scale);
        writableMap.putDouble("focalX", (double) PixelUtil.toDIPFromPixel(this.focalX));
        writableMap.putDouble("focalY", (double) PixelUtil.toDIPFromPixel(this.focalY));
        writableMap.putDouble("velocity", this.velocity);
    }
}
