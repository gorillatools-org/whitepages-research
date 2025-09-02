package com.facebook.react.uimanager.events;

import com.facebook.common.logging.FLog;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class BlackHoleEventDispatcher implements EventDispatcher {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final EventDispatcher eventDispatcher = new BlackHoleEventDispatcher();

    public static final EventDispatcher get() {
        return Companion.get();
    }

    public void addBatchEventDispatchedListener(BatchEventDispatchedListener batchEventDispatchedListener) {
        Intrinsics.checkNotNullParameter(batchEventDispatchedListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
    }

    public void addListener(EventDispatcherListener eventDispatcherListener) {
        Intrinsics.checkNotNullParameter(eventDispatcherListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
    }

    public void dispatchAllEvents() {
    }

    public void onCatalystInstanceDestroyed() {
    }

    public void registerEventEmitter(int i, RCTEventEmitter rCTEventEmitter) {
        Intrinsics.checkNotNullParameter(rCTEventEmitter, "eventEmitter");
    }

    public void registerEventEmitter(int i, RCTModernEventEmitter rCTModernEventEmitter) {
        Intrinsics.checkNotNullParameter(rCTModernEventEmitter, "eventEmitter");
    }

    public void removeBatchEventDispatchedListener(BatchEventDispatchedListener batchEventDispatchedListener) {
        Intrinsics.checkNotNullParameter(batchEventDispatchedListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
    }

    public void removeListener(EventDispatcherListener eventDispatcherListener) {
        Intrinsics.checkNotNullParameter(eventDispatcherListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
    }

    public void unregisterEventEmitter(int i) {
    }

    private BlackHoleEventDispatcher() {
    }

    public void dispatchEvent(Event<?> event) {
        Intrinsics.checkNotNullParameter(event, "event");
        String eventName = event.getEventName();
        FLog.d("BlackHoleEventDispatcher", "Trying to emit event to JS, but the React instance isn't ready. Event: " + eventName);
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final EventDispatcher get() {
            return BlackHoleEventDispatcher.eventDispatcher;
        }
    }
}
