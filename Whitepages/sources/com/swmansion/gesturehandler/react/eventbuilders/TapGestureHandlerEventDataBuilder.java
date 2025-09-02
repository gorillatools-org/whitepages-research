package com.swmansion.gesturehandler.react.eventbuilders;

import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.PixelUtil;
import com.swmansion.gesturehandler.core.TapGestureHandler;
import kotlin.jvm.internal.Intrinsics;

public final class TapGestureHandlerEventDataBuilder extends GestureHandlerEventDataBuilder {
    private final float absoluteX;
    private final float absoluteY;
    private final float x;
    private final float y;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public TapGestureHandlerEventDataBuilder(TapGestureHandler tapGestureHandler) {
        super(tapGestureHandler);
        Intrinsics.checkNotNullParameter(tapGestureHandler, "handler");
        this.x = tapGestureHandler.getLastRelativePositionX();
        this.y = tapGestureHandler.getLastRelativePositionY();
        this.absoluteX = tapGestureHandler.getLastPositionInWindowX();
        this.absoluteY = tapGestureHandler.getLastPositionInWindowY();
    }

    public void buildEventData(WritableMap writableMap) {
        Intrinsics.checkNotNullParameter(writableMap, "eventData");
        super.buildEventData(writableMap);
        writableMap.putDouble("x", (double) PixelUtil.toDIPFromPixel(this.x));
        writableMap.putDouble("y", (double) PixelUtil.toDIPFromPixel(this.y));
        writableMap.putDouble("absoluteX", (double) PixelUtil.toDIPFromPixel(this.absoluteX));
        writableMap.putDouble("absoluteY", (double) PixelUtil.toDIPFromPixel(this.absoluteY));
    }
}
