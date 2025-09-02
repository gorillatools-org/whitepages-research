package com.airbnb.android.react.lottie;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class OnAnimationFailureEvent extends Event {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final Throwable error;

    public short getCoalescingKey() {
        return 0;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public OnAnimationFailureEvent(int i, int i2, Throwable th) {
        super(i, i2);
        Intrinsics.checkNotNullParameter(th, "error");
        this.error = th;
    }

    public String getEventName() {
        return "topAnimationFailure";
    }

    /* access modifiers changed from: protected */
    public WritableMap getEventData() {
        WritableMap createMap = Arguments.createMap();
        createMap.putString("error", this.error.getMessage());
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
