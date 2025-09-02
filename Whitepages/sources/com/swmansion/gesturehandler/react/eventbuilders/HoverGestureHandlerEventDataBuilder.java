package com.swmansion.gesturehandler.react.eventbuilders;

import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.PixelUtil;
import com.swmansion.gesturehandler.core.HoverGestureHandler;
import com.swmansion.gesturehandler.core.StylusData;
import kotlin.jvm.internal.Intrinsics;

public final class HoverGestureHandlerEventDataBuilder extends GestureHandlerEventDataBuilder {
    private final float absoluteX;
    private final float absoluteY;
    private final StylusData stylusData;
    private final float x;
    private final float y;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public HoverGestureHandlerEventDataBuilder(HoverGestureHandler hoverGestureHandler) {
        super(hoverGestureHandler);
        Intrinsics.checkNotNullParameter(hoverGestureHandler, "handler");
        this.x = hoverGestureHandler.getLastRelativePositionX();
        this.y = hoverGestureHandler.getLastRelativePositionY();
        this.absoluteX = hoverGestureHandler.getLastPositionInWindowX();
        this.absoluteY = hoverGestureHandler.getLastPositionInWindowY();
        this.stylusData = hoverGestureHandler.getStylusData();
    }

    public void buildEventData(WritableMap writableMap) {
        Intrinsics.checkNotNullParameter(writableMap, "eventData");
        super.buildEventData(writableMap);
        writableMap.putDouble("x", (double) PixelUtil.toDIPFromPixel(this.x));
        writableMap.putDouble("y", (double) PixelUtil.toDIPFromPixel(this.y));
        writableMap.putDouble("absoluteX", (double) PixelUtil.toDIPFromPixel(this.absoluteX));
        writableMap.putDouble("absoluteY", (double) PixelUtil.toDIPFromPixel(this.absoluteY));
        if (this.stylusData.getPressure() != -1.0d) {
            writableMap.putMap("stylusData", this.stylusData.toReadableMap());
        }
    }
}
