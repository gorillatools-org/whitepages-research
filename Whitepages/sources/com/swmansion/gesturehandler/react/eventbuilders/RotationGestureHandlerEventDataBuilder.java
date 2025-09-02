package com.swmansion.gesturehandler.react.eventbuilders;

import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ViewProps;
import com.swmansion.gesturehandler.core.RotationGestureHandler;
import kotlin.jvm.internal.Intrinsics;

public final class RotationGestureHandlerEventDataBuilder extends GestureHandlerEventDataBuilder {
    private final float anchorX;
    private final float anchorY;
    private final double rotation;
    private final double velocity;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public RotationGestureHandlerEventDataBuilder(RotationGestureHandler rotationGestureHandler) {
        super(rotationGestureHandler);
        Intrinsics.checkNotNullParameter(rotationGestureHandler, "handler");
        this.rotation = rotationGestureHandler.getRotation();
        this.anchorX = rotationGestureHandler.getAnchorX();
        this.anchorY = rotationGestureHandler.getAnchorY();
        this.velocity = rotationGestureHandler.getVelocity();
    }

    public void buildEventData(WritableMap writableMap) {
        Intrinsics.checkNotNullParameter(writableMap, "eventData");
        super.buildEventData(writableMap);
        writableMap.putDouble(ViewProps.ROTATION, this.rotation);
        writableMap.putDouble("anchorX", (double) PixelUtil.toDIPFromPixel(this.anchorX));
        writableMap.putDouble("anchorY", (double) PixelUtil.toDIPFromPixel(this.anchorY));
        writableMap.putDouble("velocity", this.velocity);
    }
}
