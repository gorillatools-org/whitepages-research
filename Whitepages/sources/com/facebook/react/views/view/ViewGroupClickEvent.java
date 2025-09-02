package com.facebook.react.views.view;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class ViewGroupClickEvent extends Event<ViewGroupClickEvent> {
    private static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String EVENT_NAME = "topClick";

    public boolean canCoalesce() {
        return false;
    }

    public ViewGroupClickEvent(int i, int i2) {
        super(i, i2);
    }

    public ViewGroupClickEvent(int i) {
        this(-1, i);
    }

    public String getEventName() {
        return "topClick";
    }

    /* access modifiers changed from: protected */
    public WritableMap getEventData() {
        WritableMap createMap = Arguments.createMap();
        Intrinsics.checkNotNullExpressionValue(createMap, "createMap(...)");
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
