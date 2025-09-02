package com.reactnativecommunity.webview.events;

import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class TopLoadingStartEvent extends Event {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final WritableMap mEventData;

    public boolean canCoalesce() {
        return false;
    }

    public short getCoalescingKey() {
        return 0;
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public TopLoadingStartEvent(int i, WritableMap writableMap) {
        super(i);
        Intrinsics.checkNotNullParameter(writableMap, "mEventData");
        this.mEventData = writableMap;
    }

    public String getEventName() {
        return "topLoadingStart";
    }

    public void dispatch(RCTEventEmitter rCTEventEmitter) {
        Intrinsics.checkNotNullParameter(rCTEventEmitter, "rctEventEmitter");
        rCTEventEmitter.receiveEvent(getViewTag(), getEventName(), this.mEventData);
    }
}
