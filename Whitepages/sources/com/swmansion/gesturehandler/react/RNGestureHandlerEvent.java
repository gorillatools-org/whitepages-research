package com.swmansion.gesturehandler.react;

import android.view.View;
import androidx.core.util.Pools$SynchronizedPool;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.UIManagerHelper;
import com.facebook.react.uimanager.events.Event;
import com.swmansion.gesturehandler.core.GestureHandler;
import com.swmansion.gesturehandler.react.eventbuilders.GestureHandlerEventDataBuilder;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class RNGestureHandlerEvent extends Event {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final Pools$SynchronizedPool EVENTS_POOL = new Pools$SynchronizedPool(7);
    private short coalescingKey;
    private GestureHandlerEventDataBuilder dataBuilder;
    private boolean useTopPrefixedName;

    public /* synthetic */ RNGestureHandlerEvent(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public boolean canCoalesce() {
        return true;
    }

    private RNGestureHandlerEvent() {
    }

    /* access modifiers changed from: private */
    public final void init(GestureHandler gestureHandler, GestureHandlerEventDataBuilder gestureHandlerEventDataBuilder, boolean z) {
        View view = gestureHandler.getView();
        Intrinsics.checkNotNull(view);
        super.init(UIManagerHelper.getSurfaceId(view), view.getId());
        this.dataBuilder = gestureHandlerEventDataBuilder;
        this.useTopPrefixedName = z;
        this.coalescingKey = gestureHandler.getEventCoalescingKey();
    }

    public void onDispose() {
        this.dataBuilder = null;
        EVENTS_POOL.release(this);
    }

    public String getEventName() {
        return this.useTopPrefixedName ? "topGestureHandlerEvent" : "onGestureHandlerEvent";
    }

    public short getCoalescingKey() {
        return this.coalescingKey;
    }

    /* access modifiers changed from: protected */
    public WritableMap getEventData() {
        Companion companion = Companion;
        GestureHandlerEventDataBuilder gestureHandlerEventDataBuilder = this.dataBuilder;
        Intrinsics.checkNotNull(gestureHandlerEventDataBuilder);
        return companion.createEventData(gestureHandlerEventDataBuilder);
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public static /* synthetic */ RNGestureHandlerEvent obtain$default(Companion companion, GestureHandler gestureHandler, GestureHandlerEventDataBuilder gestureHandlerEventDataBuilder, boolean z, int i, Object obj) {
            if ((i & 4) != 0) {
                z = false;
            }
            return companion.obtain(gestureHandler, gestureHandlerEventDataBuilder, z);
        }

        public final RNGestureHandlerEvent obtain(GestureHandler gestureHandler, GestureHandlerEventDataBuilder gestureHandlerEventDataBuilder, boolean z) {
            Intrinsics.checkNotNullParameter(gestureHandler, "handler");
            Intrinsics.checkNotNullParameter(gestureHandlerEventDataBuilder, "dataBuilder");
            RNGestureHandlerEvent rNGestureHandlerEvent = (RNGestureHandlerEvent) RNGestureHandlerEvent.EVENTS_POOL.acquire();
            if (rNGestureHandlerEvent == null) {
                rNGestureHandlerEvent = new RNGestureHandlerEvent((DefaultConstructorMarker) null);
            }
            rNGestureHandlerEvent.init(gestureHandler, gestureHandlerEventDataBuilder, z);
            return rNGestureHandlerEvent;
        }

        public final WritableMap createEventData(GestureHandlerEventDataBuilder gestureHandlerEventDataBuilder) {
            Intrinsics.checkNotNullParameter(gestureHandlerEventDataBuilder, "dataBuilder");
            WritableMap createMap = Arguments.createMap();
            Intrinsics.checkNotNull(createMap);
            gestureHandlerEventDataBuilder.buildEventData(createMap);
            Intrinsics.checkNotNullExpressionValue(createMap, "apply(...)");
            return createMap;
        }
    }
}
