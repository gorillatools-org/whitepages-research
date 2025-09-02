package com.swmansion.gesturehandler.react;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.uimanager.RootView;
import com.facebook.react.views.view.ReactViewGroup;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class RNGestureHandlerRootView extends ReactViewGroup {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private boolean _enabled;
    private RNGestureHandlerRootHelper rootHelper;

    public RNGestureHandlerRootView(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        boolean access$hasGestureHandlerEnabledRootView = Companion.hasGestureHandlerEnabledRootView(this);
        this._enabled = !access$hasGestureHandlerEnabledRootView;
        if (access$hasGestureHandlerEnabledRootView) {
            Log.i(ReactConstants.TAG, "[GESTURE HANDLER] Gesture handler is already enabled for a parent view");
        }
        if (this._enabled && this.rootHelper == null) {
            Context context = getContext();
            Intrinsics.checkNotNull(context, "null cannot be cast to non-null type com.facebook.react.bridge.ReactContext");
            this.rootHelper = new RNGestureHandlerRootHelper((ReactContext) context, this);
        }
    }

    public final void tearDown() {
        RNGestureHandlerRootHelper rNGestureHandlerRootHelper = this.rootHelper;
        if (rNGestureHandlerRootHelper != null) {
            rNGestureHandlerRootHelper.tearDown();
        }
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(motionEvent, "ev");
        if (this._enabled) {
            RNGestureHandlerRootHelper rNGestureHandlerRootHelper = this.rootHelper;
            Intrinsics.checkNotNull(rNGestureHandlerRootHelper);
            if (rNGestureHandlerRootHelper.dispatchTouchEvent(motionEvent)) {
                return true;
            }
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public boolean dispatchGenericMotionEvent(MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(motionEvent, "event");
        if (this._enabled) {
            RNGestureHandlerRootHelper rNGestureHandlerRootHelper = this.rootHelper;
            Intrinsics.checkNotNull(rNGestureHandlerRootHelper);
            if (rNGestureHandlerRootHelper.dispatchTouchEvent(motionEvent)) {
                return true;
            }
        }
        return super.dispatchGenericMotionEvent(motionEvent);
    }

    public void requestDisallowInterceptTouchEvent(boolean z) {
        if (this._enabled) {
            RNGestureHandlerRootHelper rNGestureHandlerRootHelper = this.rootHelper;
            Intrinsics.checkNotNull(rNGestureHandlerRootHelper);
            rNGestureHandlerRootHelper.requestDisallowInterceptTouchEvent();
        }
        super.requestDisallowInterceptTouchEvent(z);
    }

    public final void activateNativeHandlers(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        RNGestureHandlerRootHelper rNGestureHandlerRootHelper = this.rootHelper;
        if (rNGestureHandlerRootHelper != null) {
            rNGestureHandlerRootHelper.activateNativeHandlers(view);
        }
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* access modifiers changed from: private */
        public final boolean hasGestureHandlerEnabledRootView(ViewGroup viewGroup) {
            UiThreadUtil.assertOnUiThread();
            for (ViewParent parent = viewGroup.getParent(); parent != null; parent = parent.getParent()) {
                if (parent instanceof RNGestureHandlerRootView) {
                    return true;
                }
                if (parent instanceof RootView) {
                    return false;
                }
            }
            return false;
        }
    }
}
