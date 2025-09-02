package com.swmansion.rnscreens.events;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import kotlin.jvm.internal.DefaultConstructorMarker;

public final class SearchBarChangeTextEvent extends Event {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final String text;

    public short getCoalescingKey() {
        return 0;
    }

    public SearchBarChangeTextEvent(int i, int i2, String str) {
        super(i, i2);
        this.text = str;
    }

    public String getEventName() {
        return "topChangeText";
    }

    /* access modifiers changed from: protected */
    public WritableMap getEventData() {
        WritableMap createMap = Arguments.createMap();
        createMap.putString("text", this.text);
        return createMap;
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
