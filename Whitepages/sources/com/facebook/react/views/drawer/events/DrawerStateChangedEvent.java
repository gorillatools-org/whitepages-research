package com.facebook.react.views.drawer.events;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class DrawerStateChangedEvent extends Event<DrawerStateChangedEvent> {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String EVENT_NAME = "topDrawerStateChanged";
    private final int drawerState;

    public DrawerStateChangedEvent(int i, int i2) {
        this(-1, i, i2);
    }

    public DrawerStateChangedEvent(int i, int i2, int i3) {
        super(i, i2);
        this.drawerState = i3;
    }

    public final int getDrawerState() {
        return this.drawerState;
    }

    public String getEventName() {
        return EVENT_NAME;
    }

    /* access modifiers changed from: protected */
    public WritableMap getEventData() {
        WritableMap createMap = Arguments.createMap();
        Intrinsics.checkNotNullExpressionValue(createMap, "createMap(...)");
        createMap.putInt("drawerState", getDrawerState());
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
