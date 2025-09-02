package com.swmansion.rnscreens.events;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.views.progressbar.ReactProgressBarViewManager;
import kotlin.jvm.internal.DefaultConstructorMarker;

public final class ScreenTransitionProgressEvent extends Event {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final short coalescingKey;
    private final boolean isClosing;
    private final boolean isGoingForward;
    private final float progress;

    public ScreenTransitionProgressEvent(int i, int i2, float f, boolean z, boolean z2, short s) {
        super(i, i2);
        this.progress = f;
        this.isClosing = z;
        this.isGoingForward = z2;
        this.coalescingKey = s;
    }

    public String getEventName() {
        return "topTransitionProgress";
    }

    public short getCoalescingKey() {
        return this.coalescingKey;
    }

    /* access modifiers changed from: protected */
    public WritableMap getEventData() {
        WritableMap createMap = Arguments.createMap();
        createMap.putDouble(ReactProgressBarViewManager.PROP_PROGRESS, (double) this.progress);
        createMap.putInt("closing", this.isClosing ? 1 : 0);
        createMap.putInt("goingForward", this.isGoingForward ? 1 : 0);
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
