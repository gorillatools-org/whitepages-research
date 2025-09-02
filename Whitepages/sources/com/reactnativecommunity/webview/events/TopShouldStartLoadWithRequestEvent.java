package com.reactnativecommunity.webview.events;

import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class TopShouldStartLoadWithRequestEvent extends Event {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final WritableMap mData;

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
    public TopShouldStartLoadWithRequestEvent(int i, WritableMap writableMap) {
        super(i);
        Intrinsics.checkNotNullParameter(writableMap, "mData");
        this.mData = writableMap;
        writableMap.putString("navigationType", "other");
        writableMap.putBoolean("isTopFrame", true);
    }

    public String getEventName() {
        return "topShouldStartLoadWithRequest";
    }

    public void dispatch(RCTEventEmitter rCTEventEmitter) {
        Intrinsics.checkNotNullParameter(rCTEventEmitter, "rctEventEmitter");
        rCTEventEmitter.receiveEvent(getViewTag(), "topShouldStartLoadWithRequest", this.mData);
    }
}
