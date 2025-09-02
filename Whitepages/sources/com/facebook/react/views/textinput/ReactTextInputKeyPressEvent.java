package com.facebook.react.views.textinput;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;

class ReactTextInputKeyPressEvent extends Event<ReactTextInputKeyPressEvent> {
    public static final String EVENT_NAME = "topKeyPress";
    private String mKey;

    public boolean canCoalesce() {
        return false;
    }

    @Deprecated
    ReactTextInputKeyPressEvent(int i, String str) {
        this(-1, i, str);
    }

    ReactTextInputKeyPressEvent(int i, int i2, String str) {
        super(i, i2);
        this.mKey = str;
    }

    public String getEventName() {
        return EVENT_NAME;
    }

    /* access modifiers changed from: protected */
    public WritableMap getEventData() {
        WritableMap createMap = Arguments.createMap();
        createMap.putString("key", this.mKey);
        return createMap;
    }
}
