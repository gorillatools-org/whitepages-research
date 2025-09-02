package com.swmansion.rnscreens.events;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import kotlin.jvm.internal.DefaultConstructorMarker;

public final class SearchBarFocusEvent extends Event {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);

    public short getCoalescingKey() {
        return 0;
    }

    public SearchBarFocusEvent(int i, int i2) {
        super(i, i2);
    }

    public String getEventName() {
        return "topSearchFocus";
    }

    /* access modifiers changed from: protected */
    public WritableMap getEventData() {
        return Arguments.createMap();
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
