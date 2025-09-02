package com.airbnb.android.react.lottie;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import kotlin.jvm.internal.DefaultConstructorMarker;

public final class OnAnimationFinishEvent extends Event {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final boolean isCancelled;

    public short getCoalescingKey() {
        return 0;
    }

    public OnAnimationFinishEvent(int i, int i2, boolean z) {
        super(i, i2);
        this.isCancelled = z;
    }

    public String getEventName() {
        return "topAnimationFinish";
    }

    /* access modifiers changed from: protected */
    public WritableMap getEventData() {
        WritableMap createMap = Arguments.createMap();
        createMap.putBoolean("isCancelled", this.isCancelled);
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
