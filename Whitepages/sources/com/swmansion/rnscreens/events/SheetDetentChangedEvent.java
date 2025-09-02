package com.swmansion.rnscreens.events;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.google.firebase.analytics.FirebaseAnalytics;
import kotlin.jvm.internal.DefaultConstructorMarker;

public final class SheetDetentChangedEvent extends Event {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final int index;
    private final boolean isStable;

    public short getCoalescingKey() {
        return 0;
    }

    public SheetDetentChangedEvent(int i, int i2, int i3, boolean z) {
        super(i, i2);
        this.index = i3;
        this.isStable = z;
    }

    public String getEventName() {
        return "topSheetDetentChanged";
    }

    /* access modifiers changed from: protected */
    public WritableMap getEventData() {
        WritableMap createMap = Arguments.createMap();
        createMap.putInt(FirebaseAnalytics.Param.INDEX, this.index);
        createMap.putBoolean("isStable", this.isStable);
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
