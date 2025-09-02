package com.swmansion.gesturehandler.react.eventbuilders;

import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.PixelUtil;
import com.swmansion.gesturehandler.core.LongPressGestureHandler;
import kotlin.jvm.internal.Intrinsics;

public final class LongPressGestureHandlerEventDataBuilder extends GestureHandlerEventDataBuilder {
    private final float absoluteX;
    private final float absoluteY;
    private final int duration;
    private final float x;
    private final float y;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public LongPressGestureHandlerEventDataBuilder(LongPressGestureHandler longPressGestureHandler) {
        super(longPressGestureHandler);
        Intrinsics.checkNotNullParameter(longPressGestureHandler, "handler");
        this.x = longPressGestureHandler.getLastRelativePositionX();
        this.y = longPressGestureHandler.getLastRelativePositionY();
        this.absoluteX = longPressGestureHandler.getLastPositionInWindowX();
        this.absoluteY = longPressGestureHandler.getLastPositionInWindowY();
        this.duration = longPressGestureHandler.getDuration();
    }

    public void buildEventData(WritableMap writableMap) {
        Intrinsics.checkNotNullParameter(writableMap, "eventData");
        super.buildEventData(writableMap);
        writableMap.putDouble("x", (double) PixelUtil.toDIPFromPixel(this.x));
        writableMap.putDouble("y", (double) PixelUtil.toDIPFromPixel(this.y));
        writableMap.putDouble("absoluteX", (double) PixelUtil.toDIPFromPixel(this.absoluteX));
        writableMap.putDouble("absoluteY", (double) PixelUtil.toDIPFromPixel(this.absoluteY));
        writableMap.putInt("duration", this.duration);
    }
}
