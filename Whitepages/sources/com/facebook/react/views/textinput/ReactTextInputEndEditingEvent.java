package com.facebook.react.views.textinput;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;

class ReactTextInputEndEditingEvent extends Event<ReactTextInputEndEditingEvent> {
    private static final String EVENT_NAME = "topEndEditing";
    private String mText;

    public boolean canCoalesce() {
        return false;
    }

    @Deprecated
    public ReactTextInputEndEditingEvent(int i, String str) {
        this(-1, i, str);
    }

    public ReactTextInputEndEditingEvent(int i, int i2, String str) {
        super(i, i2);
        this.mText = str;
    }

    public String getEventName() {
        return EVENT_NAME;
    }

    /* access modifiers changed from: protected */
    public WritableMap getEventData() {
        WritableMap createMap = Arguments.createMap();
        createMap.putInt("target", getViewTag());
        createMap.putString("text", this.mText);
        return createMap;
    }
}
