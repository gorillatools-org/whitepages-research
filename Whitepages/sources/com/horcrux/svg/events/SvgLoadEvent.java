package com.horcrux.svg.events;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.views.imagehelper.ImageSource;

public class SvgLoadEvent extends Event<SvgLoadEvent> {
    public static final String EVENT_NAME = "topLoad";
    private final float height;
    private final String uri;
    private final float width;

    public short getCoalescingKey() {
        return 0;
    }

    public SvgLoadEvent(int i, int i2, ReactContext reactContext, String str, float f, float f2) {
        super(i, i2);
        this.uri = new ImageSource(reactContext, str).getSource();
        this.width = f;
        this.height = f2;
    }

    public String getEventName() {
        return EVENT_NAME;
    }

    public void dispatch(RCTEventEmitter rCTEventEmitter) {
        rCTEventEmitter.receiveEvent(getViewTag(), getEventName(), getEventData());
    }

    /* access modifiers changed from: protected */
    public WritableMap getEventData() {
        WritableMap createMap = Arguments.createMap();
        createMap.putDouble("width", (double) this.width);
        createMap.putDouble("height", (double) this.height);
        createMap.putString("uri", this.uri);
        WritableMap createMap2 = Arguments.createMap();
        createMap2.putMap("source", createMap);
        return createMap2;
    }
}
