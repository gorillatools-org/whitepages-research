package com.facebook.react.views.textinput;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;

class ReactTextInputSubmitEditingEvent extends Event<ReactTextInputSubmitEditingEvent> {
    private static final String EVENT_NAME = "topSubmitEditing";
    private String mText;

    public boolean canCoalesce() {
        return false;
    }

    @Deprecated
    public ReactTextInputSubmitEditingEvent(int i, String str) {
        this(-1, i, str);
    }

    public ReactTextInputSubmitEditingEvent(int i, int i2, String str) {
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
