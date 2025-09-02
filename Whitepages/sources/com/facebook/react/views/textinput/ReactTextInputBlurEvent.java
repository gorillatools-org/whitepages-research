package com.facebook.react.views.textinput;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;

class ReactTextInputBlurEvent extends Event<ReactTextInputBlurEvent> {
    private static final String EVENT_NAME = "topBlur";

    public boolean canCoalesce() {
        return false;
    }

    @Deprecated
    public ReactTextInputBlurEvent(int i) {
        this(-1, i);
    }

    public ReactTextInputBlurEvent(int i, int i2) {
        super(i, i2);
    }

    public String getEventName() {
        return EVENT_NAME;
    }

    /* access modifiers changed from: protected */
    public WritableMap getEventData() {
        WritableMap createMap = Arguments.createMap();
        createMap.putInt("target", getViewTag());
        return createMap;
    }
}
