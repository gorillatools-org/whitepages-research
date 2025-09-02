package com.th3rdwave.safeareacontext;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class InsetsChangeEvent extends Event {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final Rect mFrame;
    private final EdgeInsets mInsets;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public InsetsChangeEvent(int i, int i2, EdgeInsets edgeInsets, Rect rect) {
        super(i2);
        Intrinsics.checkNotNullParameter(edgeInsets, "mInsets");
        Intrinsics.checkNotNullParameter(rect, "mFrame");
        this.mInsets = edgeInsets;
        this.mFrame = rect;
    }

    public String getEventName() {
        return "topInsetsChange";
    }

    public void dispatch(RCTEventEmitter rCTEventEmitter) {
        Intrinsics.checkNotNullParameter(rCTEventEmitter, "rctEventEmitter");
        WritableMap createMap = Arguments.createMap();
        createMap.putMap("insets", SerializationUtilsKt.edgeInsetsToJsMap(this.mInsets));
        createMap.putMap("frame", SerializationUtilsKt.rectToJsMap(this.mFrame));
        rCTEventEmitter.receiveEvent(getViewTag(), getEventName(), createMap);
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
