package com.facebook.react.uimanager.events;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.views.textinput.ReactContentSizeChangedEvent;
import kotlin.jvm.internal.Intrinsics;

public final class ContentSizeChangeEvent extends Event<ContentSizeChangeEvent> {
    private final int height;
    private final int width;

    public ContentSizeChangeEvent(int i, int i2, int i3, int i4) {
        super(i, i2);
        this.width = i3;
        this.height = i4;
    }

    public ContentSizeChangeEvent(int i, int i2, int i3) {
        this(-1, i, i2, i3);
    }

    public String getEventName() {
        return ReactContentSizeChangedEvent.EVENT_NAME;
    }

    /* access modifiers changed from: protected */
    public WritableMap getEventData() {
        WritableMap createMap = Arguments.createMap();
        createMap.putDouble("width", (double) PixelUtil.toDIPFromPixel((float) this.width));
        createMap.putDouble("height", (double) PixelUtil.toDIPFromPixel((float) this.height));
        Intrinsics.checkNotNull(createMap);
        return createMap;
    }
}
