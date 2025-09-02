package com.swmansion.gesturehandler.react;

import android.view.View;
import androidx.core.util.Pools$SynchronizedPool;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.UIManagerHelper;
import com.facebook.react.uimanager.events.Event;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.swmansion.gesturehandler.core.GestureHandler;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class RNGestureHandlerTouchEvent extends Event {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final Pools$SynchronizedPool EVENTS_POOL = new Pools$SynchronizedPool(7);
    private short coalescingKey;
    private WritableMap extraData;

    public /* synthetic */ RNGestureHandlerTouchEvent(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public boolean canCoalesce() {
        return true;
    }

    private RNGestureHandlerTouchEvent() {
    }

    /* access modifiers changed from: private */
    public final void init(GestureHandler gestureHandler) {
        View view = gestureHandler.getView();
        Intrinsics.checkNotNull(view);
        super.init(UIManagerHelper.getSurfaceId(view), view.getId());
        this.extraData = Companion.createEventData(gestureHandler);
        this.coalescingKey = gestureHandler.getEventCoalescingKey();
    }

    public void onDispose() {
        this.extraData = null;
        EVENTS_POOL.release(this);
    }

    public String getEventName() {
        return "onGestureHandlerEvent";
    }

    public short getCoalescingKey() {
        return this.coalescingKey;
    }

    /* access modifiers changed from: protected */
    public WritableMap getEventData() {
        return this.extraData;
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final RNGestureHandlerTouchEvent obtain(GestureHandler gestureHandler) {
            Intrinsics.checkNotNullParameter(gestureHandler, "handler");
            RNGestureHandlerTouchEvent rNGestureHandlerTouchEvent = (RNGestureHandlerTouchEvent) RNGestureHandlerTouchEvent.EVENTS_POOL.acquire();
            if (rNGestureHandlerTouchEvent == null) {
                rNGestureHandlerTouchEvent = new RNGestureHandlerTouchEvent((DefaultConstructorMarker) null);
            }
            rNGestureHandlerTouchEvent.init(gestureHandler);
            return rNGestureHandlerTouchEvent;
        }

        public final WritableMap createEventData(GestureHandler gestureHandler) {
            Intrinsics.checkNotNullParameter(gestureHandler, "handler");
            WritableMap createMap = Arguments.createMap();
            createMap.putInt("handlerTag", gestureHandler.getTag());
            createMap.putInt(RemoteConfigConstants.ResponseFieldKey.STATE, gestureHandler.getState());
            createMap.putInt("numberOfTouches", gestureHandler.getTrackedPointersCount());
            createMap.putInt("eventType", gestureHandler.getTouchEventType());
            createMap.putInt("pointerType", gestureHandler.getPointerType());
            WritableArray consumeChangedTouchesPayload = gestureHandler.consumeChangedTouchesPayload();
            if (consumeChangedTouchesPayload != null) {
                createMap.putArray("changedTouches", consumeChangedTouchesPayload);
            }
            WritableArray consumeAllTouchesPayload = gestureHandler.consumeAllTouchesPayload();
            if (consumeAllTouchesPayload != null) {
                createMap.putArray("allTouches", consumeAllTouchesPayload);
            }
            if (gestureHandler.isAwaiting() && gestureHandler.getState() == 4) {
                createMap.putInt(RemoteConfigConstants.ResponseFieldKey.STATE, 2);
            }
            Intrinsics.checkNotNullExpressionValue(createMap, "apply(...)");
            return createMap;
        }
    }
}
