package com.facebook.react.views.textinput;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;

public class ReactTextChangedEvent extends Event<ReactTextChangedEvent> {
    public static final String EVENT_NAME = "topChange";
    private int mEventCount;
    private String mText;

    @Deprecated
    public ReactTextChangedEvent(int i, String str, int i2) {
        this(-1, i, str, i2);
    }

    public ReactTextChangedEvent(int i, int i2, String str, int i3) {
        super(i, i2);
        this.mText = str;
        this.mEventCount = i3;
    }

    public String getEventName() {
        return EVENT_NAME;
    }

    /* access modifiers changed from: protected */
    public WritableMap getEventData() {
        WritableMap createMap = Arguments.createMap();
        createMap.putString("text", this.mText);
        createMap.putInt("eventCount", this.mEventCount);
        createMap.putInt("target", getViewTag());
        return createMap;
    }
}
