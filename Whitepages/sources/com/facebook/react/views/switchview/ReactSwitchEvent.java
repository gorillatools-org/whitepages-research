package com.facebook.react.views.switchview;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import kotlin.jvm.internal.DefaultConstructorMarker;

public final class ReactSwitchEvent extends Event<ReactSwitchEvent> {
    private static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String EVENT_NAME = "topChange";
    private final boolean isChecked;

    public ReactSwitchEvent(int i, int i2, boolean z) {
        super(i, i2);
        this.isChecked = z;
    }

    public ReactSwitchEvent(int i, boolean z) {
        this(-1, i, z);
    }

    public String getEventName() {
        return "topChange";
    }

    public WritableMap getEventData() {
        WritableMap createMap = Arguments.createMap();
        createMap.putInt("target", getViewTag());
        createMap.putBoolean("value", this.isChecked);
        return createMap;
    }

    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
