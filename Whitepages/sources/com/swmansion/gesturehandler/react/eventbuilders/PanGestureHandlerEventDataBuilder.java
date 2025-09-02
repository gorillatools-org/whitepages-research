package com.swmansion.gesturehandler.react.eventbuilders;

import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.PixelUtil;
import com.swmansion.gesturehandler.core.PanGestureHandler;
import com.swmansion.gesturehandler.core.StylusData;
import kotlin.jvm.internal.Intrinsics;

public final class PanGestureHandlerEventDataBuilder extends GestureHandlerEventDataBuilder {
    private final float absoluteX;
    private final float absoluteY;
    private final StylusData stylusData;
    private final float translationX;
    private final float translationY;
    private final float velocityX;
    private final float velocityY;
    private final float x;
    private final float y;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public PanGestureHandlerEventDataBuilder(PanGestureHandler panGestureHandler) {
        super(panGestureHandler);
        Intrinsics.checkNotNullParameter(panGestureHandler, "handler");
        this.x = panGestureHandler.getLastRelativePositionX();
        this.y = panGestureHandler.getLastRelativePositionY();
        this.absoluteX = panGestureHandler.getLastPositionInWindowX();
        this.absoluteY = panGestureHandler.getLastPositionInWindowY();
        this.translationX = panGestureHandler.getTranslationX();
        this.translationY = panGestureHandler.getTranslationY();
        this.velocityX = panGestureHandler.getVelocityX();
        this.velocityY = panGestureHandler.getVelocityY();
        this.stylusData = panGestureHandler.getStylusData();
    }

    public void buildEventData(WritableMap writableMap) {
        Intrinsics.checkNotNullParameter(writableMap, "eventData");
        super.buildEventData(writableMap);
        writableMap.putDouble("x", (double) PixelUtil.toDIPFromPixel(this.x));
        writableMap.putDouble("y", (double) PixelUtil.toDIPFromPixel(this.y));
        writableMap.putDouble("absoluteX", (double) PixelUtil.toDIPFromPixel(this.absoluteX));
        writableMap.putDouble("absoluteY", (double) PixelUtil.toDIPFromPixel(this.absoluteY));
        writableMap.putDouble("translationX", (double) PixelUtil.toDIPFromPixel(this.translationX));
        writableMap.putDouble("translationY", (double) PixelUtil.toDIPFromPixel(this.translationY));
        writableMap.putDouble("velocityX", (double) PixelUtil.toDIPFromPixel(this.velocityX));
        writableMap.putDouble("velocityY", (double) PixelUtil.toDIPFromPixel(this.velocityY));
        if (this.stylusData.getPressure() != -1.0d) {
            writableMap.putMap("stylusData", this.stylusData.toReadableMap());
        }
    }
}
