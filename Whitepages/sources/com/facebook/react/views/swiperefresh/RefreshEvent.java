package com.facebook.react.views.swiperefresh;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;

public final class RefreshEvent extends Event<RefreshEvent> {
    public RefreshEvent(int i) {
        this(-1, i);
    }

    public RefreshEvent(int i, int i2) {
        super(i, i2);
    }

    public String getEventName() {
        return "topRefresh";
    }

    /* access modifiers changed from: protected */
    public WritableMap getEventData() {
        return Arguments.createMap();
    }
}
