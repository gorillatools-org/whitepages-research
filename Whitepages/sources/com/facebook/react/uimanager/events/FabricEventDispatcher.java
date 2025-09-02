package com.facebook.react.uimanager.events;

import android.os.Handler;
import android.view.Choreographer;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactNoCrashSoftException;
import com.facebook.react.bridge.ReactSoftExceptionLogger;
import com.facebook.react.bridge.UIManager;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.internal.featureflags.ReactNativeFeatureFlags;
import com.facebook.react.modules.core.ReactChoreographer;
import com.facebook.react.uimanager.UIManagerHelper;
import com.facebook.systrace.Systrace;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public class FabricEventDispatcher implements EventDispatcher, LifecycleEventListener {
    private static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final Handler uiThreadHandler;
    /* access modifiers changed from: private */
    public final ScheduleDispatchFrameCallback currentFrameCallback = new ScheduleDispatchFrameCallback();
    private final Runnable dispatchEventsRunnable = new FabricEventDispatcher$$ExternalSyntheticLambda1(this);
    private boolean isDispatchScheduled;
    private final CopyOnWriteArrayList<EventDispatcherListener> listeners = new CopyOnWriteArrayList<>();
    /* access modifiers changed from: private */
    public final CopyOnWriteArrayList<BatchEventDispatchedListener> postEventDispatchListeners = new CopyOnWriteArrayList<>();
    /* access modifiers changed from: private */
    public final ReactApplicationContext reactContext;
    private final ReactEventEmitter reactEventEmitter;

    public FabricEventDispatcher(ReactApplicationContext reactApplicationContext) {
        Intrinsics.checkNotNullParameter(reactApplicationContext, "reactContext");
        this.reactContext = reactApplicationContext;
        reactApplicationContext.addLifecycleEventListener(this);
        this.reactEventEmitter = new ReactEventEmitter(reactApplicationContext);
    }

    /* access modifiers changed from: private */
    public static final void dispatchEventsRunnable$lambda$0(FabricEventDispatcher fabricEventDispatcher) {
        fabricEventDispatcher.isDispatchScheduled = false;
        Systrace.beginSection(0, "BatchEventDispatchedListeners");
        try {
            Iterator<BatchEventDispatchedListener> it = fabricEventDispatcher.postEventDispatchListeners.iterator();
            Intrinsics.checkNotNullExpressionValue(it, "iterator(...)");
            while (it.hasNext()) {
                it.next().onBatchEventDispatched();
            }
        } finally {
            Systrace.endSection(0);
        }
    }

    public void dispatchEvent(Event<?> event) {
        Intrinsics.checkNotNullParameter(event, "event");
        Iterator<EventDispatcherListener> it = this.listeners.iterator();
        Intrinsics.checkNotNullExpressionValue(it, "iterator(...)");
        while (it.hasNext()) {
            it.next().onEventDispatch(event);
        }
        if (event.experimental_isSynchronous()) {
            dispatchSynchronous(event);
        } else {
            event.dispatchModern(this.reactEventEmitter);
        }
        event.dispose();
        scheduleDispatchOfBatchedEvents();
    }

    private final void dispatchSynchronous(Event<?> event) {
        String eventName = event.getEventName();
        Systrace.beginSection(0, "FabricEventDispatcher.dispatchSynchronous('" + eventName + "')");
        try {
            UIManager uIManager = UIManagerHelper.getUIManager(this.reactContext, 2);
            if (uIManager instanceof SynchronousEventReceiver) {
                int surfaceId = event.getSurfaceId();
                int viewTag = event.getViewTag();
                String eventName2 = event.getEventName();
                Intrinsics.checkNotNullExpressionValue(eventName2, "getEventName(...)");
                ((SynchronousEventReceiver) uIManager).receiveEvent(surfaceId, viewTag, eventName2, event.canCoalesce(), event.getEventData(), event.getEventCategory(), true);
            } else {
                ReactSoftExceptionLogger.logSoftException("FabricEventDispatcher", new ReactNoCrashSoftException("Fabric UIManager expected to implement SynchronousEventReceiver."));
            }
        } finally {
            Systrace.endSection(0);
        }
    }

    public void dispatchAllEvents() {
        scheduleDispatchOfBatchedEvents();
    }

    private final void scheduleDispatchOfBatchedEvents() {
        if (!ReactNativeFeatureFlags.useOptimizedEventBatchingOnAndroid()) {
            this.currentFrameCallback.maybeScheduleDispatchOfBatchedEvents();
        } else if (!this.isDispatchScheduled) {
            this.isDispatchScheduled = true;
            uiThreadHandler.postAtFrontOfQueue(this.dispatchEventsRunnable);
        }
    }

    public void addListener(EventDispatcherListener eventDispatcherListener) {
        Intrinsics.checkNotNullParameter(eventDispatcherListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        this.listeners.add(eventDispatcherListener);
    }

    public void removeListener(EventDispatcherListener eventDispatcherListener) {
        Intrinsics.checkNotNullParameter(eventDispatcherListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        this.listeners.remove(eventDispatcherListener);
    }

    public void addBatchEventDispatchedListener(BatchEventDispatchedListener batchEventDispatchedListener) {
        Intrinsics.checkNotNullParameter(batchEventDispatchedListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        this.postEventDispatchListeners.add(batchEventDispatchedListener);
    }

    public void removeBatchEventDispatchedListener(BatchEventDispatchedListener batchEventDispatchedListener) {
        Intrinsics.checkNotNullParameter(batchEventDispatchedListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        this.postEventDispatchListeners.remove(batchEventDispatchedListener);
    }

    public void onHostResume() {
        scheduleDispatchOfBatchedEvents();
        if (!ReactNativeFeatureFlags.useOptimizedEventBatchingOnAndroid()) {
            this.currentFrameCallback.resume();
        }
    }

    public void onHostPause() {
        cancelDispatchOfBatchedEvents();
    }

    public void onHostDestroy() {
        cancelDispatchOfBatchedEvents();
    }

    /* access modifiers changed from: private */
    public static final void onCatalystInstanceDestroyed$lambda$1(FabricEventDispatcher fabricEventDispatcher) {
        fabricEventDispatcher.cancelDispatchOfBatchedEvents();
    }

    public void onCatalystInstanceDestroyed() {
        UiThreadUtil.runOnUiThread(new FabricEventDispatcher$$ExternalSyntheticLambda0(this));
    }

    private final void cancelDispatchOfBatchedEvents() {
        UiThreadUtil.assertOnUiThread();
        if (ReactNativeFeatureFlags.useOptimizedEventBatchingOnAndroid()) {
            this.isDispatchScheduled = false;
            uiThreadHandler.removeCallbacks(this.dispatchEventsRunnable);
            return;
        }
        this.currentFrameCallback.stop();
    }

    public void registerEventEmitter(int i, RCTEventEmitter rCTEventEmitter) {
        Intrinsics.checkNotNullParameter(rCTEventEmitter, "eventEmitter");
        this.reactEventEmitter.register(i, rCTEventEmitter);
    }

    public void registerEventEmitter(int i, RCTModernEventEmitter rCTModernEventEmitter) {
        Intrinsics.checkNotNullParameter(rCTModernEventEmitter, "eventEmitter");
        this.reactEventEmitter.register(i, rCTModernEventEmitter);
    }

    public void unregisterEventEmitter(int i) {
        this.reactEventEmitter.unregister(i);
    }

    private final class ScheduleDispatchFrameCallback implements Choreographer.FrameCallback {
        private volatile boolean isFrameCallbackDispatchScheduled;
        private boolean shouldStop;

        public ScheduleDispatchFrameCallback() {
        }

        public void doFrame(long j) {
            UiThreadUtil.assertOnUiThread();
            if (this.shouldStop) {
                this.isFrameCallbackDispatchScheduled = false;
            } else {
                dispatchBatchedEvents();
            }
            Systrace.beginSection(0, "BatchEventDispatchedListeners");
            try {
                Iterator it = FabricEventDispatcher.this.postEventDispatchListeners.iterator();
                Intrinsics.checkNotNullExpressionValue(it, "iterator(...)");
                while (it.hasNext()) {
                    ((BatchEventDispatchedListener) it.next()).onBatchEventDispatched();
                }
            } finally {
                Systrace.endSection(0);
            }
        }

        public final void stop() {
            this.shouldStop = true;
        }

        public final void resume() {
            this.shouldStop = false;
        }

        public final void maybeDispatchBatchedEvents() {
            if (!this.isFrameCallbackDispatchScheduled) {
                this.isFrameCallbackDispatchScheduled = true;
                dispatchBatchedEvents();
            }
        }

        private final void dispatchBatchedEvents() {
            ReactChoreographer.Companion.getInstance().postFrameCallback(ReactChoreographer.CallbackType.TIMERS_EVENTS, FabricEventDispatcher.this.currentFrameCallback);
        }

        public final void maybeScheduleDispatchOfBatchedEvents() {
            if (!this.isFrameCallbackDispatchScheduled) {
                if (FabricEventDispatcher.this.reactContext.isOnUiQueueThread()) {
                    maybeDispatchBatchedEvents();
                } else {
                    FabricEventDispatcher.this.reactContext.runOnUiQueueThread(new FabricEventDispatcher$ScheduleDispatchFrameCallback$$ExternalSyntheticLambda0(this));
                }
            }
        }

        /* access modifiers changed from: private */
        public static final void maybeScheduleDispatchOfBatchedEvents$lambda$0(ScheduleDispatchFrameCallback scheduleDispatchFrameCallback) {
            scheduleDispatchFrameCallback.maybeDispatchBatchedEvents();
        }
    }

    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    static {
        Handler uiThreadHandler2 = UiThreadUtil.getUiThreadHandler();
        Intrinsics.checkNotNullExpressionValue(uiThreadHandler2, "getUiThreadHandler(...)");
        uiThreadHandler = uiThreadHandler2;
    }
}
