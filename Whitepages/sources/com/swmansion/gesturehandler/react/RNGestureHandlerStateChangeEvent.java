package com.swmansion.gesturehandler.react;

import android.view.View;
import androidx.core.util.Pools$SynchronizedPool;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.UIManagerHelper;
import com.facebook.react.uimanager.events.Event;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.swmansion.gesturehandler.core.GestureHandler;
import com.swmansion.gesturehandler.react.eventbuilders.GestureHandlerEventDataBuilder;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class RNGestureHandlerStateChangeEvent extends Event {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final Pools$SynchronizedPool EVENTS_POOL = new Pools$SynchronizedPool(7);
    private GestureHandlerEventDataBuilder dataBuilder;
    private int newState;
    private int oldState;

    public /* synthetic */ RNGestureHandlerStateChangeEvent(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public boolean canCoalesce() {
        return false;
    }

    public short getCoalescingKey() {
        return 0;
    }

    private RNGestureHandlerStateChangeEvent() {
    }

    /* access modifiers changed from: private */
    public final void init(GestureHandler gestureHandler, int i, int i2, GestureHandlerEventDataBuilder gestureHandlerEventDataBuilder) {
        View view = gestureHandler.getView();
        Intrinsics.checkNotNull(view);
        super.init(UIManagerHelper.getSurfaceId(view), view.getId());
        this.dataBuilder = gestureHandlerEventDataBuilder;
        this.newState = i;
        this.oldState = i2;
    }

    public void onDispose() {
        this.dataBuilder = null;
        this.newState = 0;
        this.oldState = 0;
        EVENTS_POOL.release(this);
    }

    public String getEventName() {
        return "onGestureHandlerStateChange";
    }

    /* access modifiers changed from: protected */
    public WritableMap getEventData() {
        Companion companion = Companion;
        GestureHandlerEventDataBuilder gestureHandlerEventDataBuilder = this.dataBuilder;
        Intrinsics.checkNotNull(gestureHandlerEventDataBuilder);
        return companion.createEventData(gestureHandlerEventDataBuilder, this.newState, this.oldState);
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final RNGestureHandlerStateChangeEvent obtain(GestureHandler gestureHandler, int i, int i2, GestureHandlerEventDataBuilder gestureHandlerEventDataBuilder) {
            Intrinsics.checkNotNullParameter(gestureHandler, "handler");
            Intrinsics.checkNotNullParameter(gestureHandlerEventDataBuilder, "dataBuilder");
            RNGestureHandlerStateChangeEvent rNGestureHandlerStateChangeEvent = (RNGestureHandlerStateChangeEvent) RNGestureHandlerStateChangeEvent.EVENTS_POOL.acquire();
            if (rNGestureHandlerStateChangeEvent == null) {
                rNGestureHandlerStateChangeEvent = new RNGestureHandlerStateChangeEvent((DefaultConstructorMarker) null);
            }
            rNGestureHandlerStateChangeEvent.init(gestureHandler, i, i2, gestureHandlerEventDataBuilder);
            return rNGestureHandlerStateChangeEvent;
        }

        public final WritableMap createEventData(GestureHandlerEventDataBuilder gestureHandlerEventDataBuilder, int i, int i2) {
            Intrinsics.checkNotNullParameter(gestureHandlerEventDataBuilder, "dataBuilder");
            WritableMap createMap = Arguments.createMap();
            Intrinsics.checkNotNull(createMap);
            gestureHandlerEventDataBuilder.buildEventData(createMap);
            createMap.putInt(RemoteConfigConstants.ResponseFieldKey.STATE, i);
            createMap.putInt("oldState", i2);
            Intrinsics.checkNotNullExpressionValue(createMap, "apply(...)");
            return createMap;
        }
    }
}
