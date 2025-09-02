package com.swmansion.gesturehandler.react;

import android.os.SystemClock;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.uimanager.RootView;
import com.facebook.react.uimanager.ThemedReactContext;
import com.swmansion.gesturehandler.core.GestureHandler;
import com.swmansion.gesturehandler.core.GestureHandlerOrchestrator;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class RNGestureHandlerRootHelper {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final ReactContext context;
    private final GestureHandler jsGestureHandler;
    private final GestureHandlerOrchestrator orchestrator;
    private boolean passingTouch;
    private final ViewGroup rootView;
    /* access modifiers changed from: private */
    public boolean shouldIntercept;

    public RNGestureHandlerRootHelper(ReactContext reactContext, ViewGroup viewGroup) {
        Intrinsics.checkNotNullParameter(reactContext, "context");
        Intrinsics.checkNotNullParameter(viewGroup, "wrappedView");
        this.context = reactContext;
        UiThreadUtil.assertOnUiThread();
        int id = viewGroup.getId();
        if (id >= 1) {
            Intrinsics.checkNotNull(reactContext, "null cannot be cast to non-null type com.facebook.react.uimanager.ThemedReactContext");
            NativeModule nativeModule = ((ThemedReactContext) reactContext).getReactApplicationContext().getNativeModule(RNGestureHandlerModule.class);
            Intrinsics.checkNotNull(nativeModule);
            RNGestureHandlerModule rNGestureHandlerModule = (RNGestureHandlerModule) nativeModule;
            RNGestureHandlerRegistry registry = rNGestureHandlerModule.getRegistry();
            ViewGroup access$findRootViewTag = Companion.findRootViewTag(viewGroup);
            this.rootView = access$findRootViewTag;
            Log.i(ReactConstants.TAG, "[GESTURE HANDLER] Initialize gesture handler for root view " + access$findRootViewTag);
            GestureHandlerOrchestrator gestureHandlerOrchestrator = new GestureHandlerOrchestrator(viewGroup, registry, new RNViewConfigurationHelper());
            gestureHandlerOrchestrator.setMinimumAlphaForTraversal(0.1f);
            this.orchestrator = gestureHandlerOrchestrator;
            RootViewGestureHandler rootViewGestureHandler = new RootViewGestureHandler();
            rootViewGestureHandler.setTag(-id);
            this.jsGestureHandler = rootViewGestureHandler;
            registry.registerHandler(rootViewGestureHandler);
            registry.attachHandlerToView(rootViewGestureHandler.getTag(), id, 3);
            rNGestureHandlerModule.registerRootHelper(this);
            return;
        }
        throw new IllegalStateException(("Expect view tag to be set for " + viewGroup).toString());
    }

    public final ViewGroup getRootView() {
        return this.rootView;
    }

    public final void tearDown() {
        Log.i(ReactConstants.TAG, "[GESTURE HANDLER] Tearing down gesture handler registered for root view " + this.rootView);
        ReactContext reactContext = this.context;
        Intrinsics.checkNotNull(reactContext, "null cannot be cast to non-null type com.facebook.react.uimanager.ThemedReactContext");
        NativeModule nativeModule = ((ThemedReactContext) reactContext).getReactApplicationContext().getNativeModule(RNGestureHandlerModule.class);
        Intrinsics.checkNotNull(nativeModule);
        RNGestureHandlerModule rNGestureHandlerModule = (RNGestureHandlerModule) nativeModule;
        RNGestureHandlerRegistry registry = rNGestureHandlerModule.getRegistry();
        GestureHandler gestureHandler = this.jsGestureHandler;
        Intrinsics.checkNotNull(gestureHandler);
        registry.dropHandler(gestureHandler.getTag());
        rNGestureHandlerModule.unregisterRootHelper(this);
    }

    public final class RootViewGestureHandler extends GestureHandler {
        public RootViewGestureHandler() {
        }

        private final void handleEvent(MotionEvent motionEvent) {
            GestureHandlerOrchestrator orchestrator;
            if (getState() == 0 && (!RNGestureHandlerRootHelper.this.shouldIntercept || (orchestrator = getOrchestrator()) == null || !orchestrator.isAnyHandlerActive())) {
                begin();
                RNGestureHandlerRootHelper.this.shouldIntercept = false;
            }
            if (motionEvent.getActionMasked() == 1 || motionEvent.getActionMasked() == 10) {
                end();
            }
        }

        /* access modifiers changed from: protected */
        public void onHandle(MotionEvent motionEvent, MotionEvent motionEvent2) {
            Intrinsics.checkNotNullParameter(motionEvent, "event");
            Intrinsics.checkNotNullParameter(motionEvent2, "sourceEvent");
            handleEvent(motionEvent);
        }

        /* access modifiers changed from: protected */
        public void onHandleHover(MotionEvent motionEvent, MotionEvent motionEvent2) {
            Intrinsics.checkNotNullParameter(motionEvent, "event");
            Intrinsics.checkNotNullParameter(motionEvent2, "sourceEvent");
            handleEvent(motionEvent);
        }

        /* access modifiers changed from: protected */
        public void onCancel() {
            RNGestureHandlerRootHelper.this.shouldIntercept = true;
            long uptimeMillis = SystemClock.uptimeMillis();
            MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
            obtain.setAction(3);
            if (RNGestureHandlerRootHelper.this.getRootView() instanceof RootView) {
                ViewGroup rootView = RNGestureHandlerRootHelper.this.getRootView();
                Intrinsics.checkNotNull(obtain);
                ((RootView) RNGestureHandlerRootHelper.this.getRootView()).onChildStartedNativeGesture(rootView, obtain);
            }
            obtain.recycle();
        }
    }

    public final void requestDisallowInterceptTouchEvent() {
        if (this.orchestrator != null && !this.passingTouch) {
            tryCancelAllHandlers();
        }
    }

    public final boolean dispatchTouchEvent(MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(motionEvent, "ev");
        this.passingTouch = true;
        GestureHandlerOrchestrator gestureHandlerOrchestrator = this.orchestrator;
        Intrinsics.checkNotNull(gestureHandlerOrchestrator);
        gestureHandlerOrchestrator.onTouchEvent(motionEvent);
        this.passingTouch = false;
        return this.shouldIntercept;
    }

    private final void tryCancelAllHandlers() {
        GestureHandler gestureHandler = this.jsGestureHandler;
        if (gestureHandler != null && gestureHandler.getState() == 2) {
            gestureHandler.activate();
            gestureHandler.end();
        }
    }

    /* access modifiers changed from: private */
    public static final void handleSetJSResponder$lambda$6(RNGestureHandlerRootHelper rNGestureHandlerRootHelper) {
        rNGestureHandlerRootHelper.tryCancelAllHandlers();
    }

    public final void handleSetJSResponder(int i, boolean z) {
        if (z) {
            UiThreadUtil.runOnUiThread(new RNGestureHandlerRootHelper$$ExternalSyntheticLambda0(this));
        }
    }

    public final void activateNativeHandlers(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        GestureHandlerOrchestrator gestureHandlerOrchestrator = this.orchestrator;
        if (gestureHandlerOrchestrator != null) {
            gestureHandlerOrchestrator.activateNativeHandlersForView(view);
        }
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* access modifiers changed from: private */
        public final ViewGroup findRootViewTag(ViewGroup viewGroup) {
            UiThreadUtil.assertOnUiThread();
            ViewParent viewParent = viewGroup;
            while (viewParent != null && !(viewParent instanceof RootView)) {
                viewParent = viewParent.getParent();
            }
            if (viewParent != null) {
                return (ViewGroup) viewParent;
            }
            throw new IllegalStateException(("View " + viewGroup + " has not been mounted under ReactRootView").toString());
        }
    }
}
