package com.facebook.react.uimanager;

import androidx.core.util.Pools$SynchronizedPool;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;

public class OnLayoutEvent extends Event<OnLayoutEvent> {
    private static final Pools$SynchronizedPool EVENTS_POOL = new Pools$SynchronizedPool(20);
    private int mHeight;
    private int mWidth;
    private int mX;
    private int mY;

    @Deprecated
    public static OnLayoutEvent obtain(int i, int i2, int i3, int i4, int i5) {
        return obtain(-1, i, i2, i3, i4, i5);
    }

    public static OnLayoutEvent obtain(int i, int i2, int i3, int i4, int i5, int i6) {
        OnLayoutEvent onLayoutEvent = (OnLayoutEvent) EVENTS_POOL.acquire();
        if (onLayoutEvent == null) {
            onLayoutEvent = new OnLayoutEvent();
        }
        onLayoutEvent.init(i, i2, i3, i4, i5, i6);
        return onLayoutEvent;
    }

    public void onDispose() {
        EVENTS_POOL.release(this);
    }

    private OnLayoutEvent() {
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public void init(int i, int i2, int i3, int i4, int i5) {
        init(-1, i, i2, i3, i4, i5);
    }

    /* access modifiers changed from: protected */
    public void init(int i, int i2, int i3, int i4, int i5, int i6) {
        super.init(i, i2);
        this.mX = i3;
        this.mY = i4;
        this.mWidth = i5;
        this.mHeight = i6;
    }

    public String getEventName() {
        return "topLayout";
    }

    /* access modifiers changed from: protected */
    public WritableMap getEventData() {
        WritableMap createMap = Arguments.createMap();
        createMap.putDouble("x", (double) PixelUtil.toDIPFromPixel((float) this.mX));
        createMap.putDouble("y", (double) PixelUtil.toDIPFromPixel((float) this.mY));
        createMap.putDouble("width", (double) PixelUtil.toDIPFromPixel((float) this.mWidth));
        createMap.putDouble("height", (double) PixelUtil.toDIPFromPixel((float) this.mHeight));
        WritableMap createMap2 = Arguments.createMap();
        createMap2.putMap("layout", createMap);
        createMap2.putInt("target", getViewTag());
        return createMap2;
    }
}
