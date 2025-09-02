package com.swmansion.rnscreens.events;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import kotlin.jvm.internal.DefaultConstructorMarker;

public final class HeaderHeightChangeEvent extends Event {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final int headerHeight;

    public HeaderHeightChangeEvent(int i, int i2, int i3) {
        super(i, i2);
        this.headerHeight = i3;
    }

    public String getEventName() {
        return "topHeaderHeightChange";
    }

    public short getCoalescingKey() {
        return (short) this.headerHeight;
    }

    /* access modifiers changed from: protected */
    public WritableMap getEventData() {
        WritableMap createMap = Arguments.createMap();
        createMap.putDouble("headerHeight", (double) this.headerHeight);
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
