package com.swmansion.gesturehandler.core;

import android.os.Handler;
import android.os.Looper;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.swmansion.gesturehandler.react.RNGestureHandlerRootHelper;
import com.swmansion.gesturehandler.react.RNViewConfigurationHelper;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class HoverGestureHandler extends GestureHandler {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final RNViewConfigurationHelper viewConfigHelper = new RNViewConfigurationHelper();
    private Runnable finishRunnable = new HoverGestureHandler$$ExternalSyntheticLambda0(this);
    private Handler handler;
    private StylusData stylusData = new StylusData(0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 31, (DefaultConstructorMarker) null);

    /* access modifiers changed from: private */
    public static final void finishRunnable$lambda$0(HoverGestureHandler hoverGestureHandler) {
        hoverGestureHandler.finish();
    }

    public final StylusData getStylusData() {
        return this.stylusData;
    }

    private final boolean isAncestorOf(GestureHandler gestureHandler) {
        View view = gestureHandler.getView();
        while (view != null) {
            if (Intrinsics.areEqual((Object) view, (Object) getView())) {
                return true;
            }
            ViewParent parent = view.getParent();
            view = parent instanceof View ? (View) parent : null;
        }
        return false;
    }

    static /* synthetic */ Boolean isViewDisplayedOverAnother$default(HoverGestureHandler hoverGestureHandler, View view, View view2, View view3, int i, Object obj) {
        if ((i & 4) != 0) {
            view3 = view.getRootView();
        }
        return hoverGestureHandler.isViewDisplayedOverAnother(view, view2, view3);
    }

    private final Boolean isViewDisplayedOverAnother(View view, View view2, View view3) {
        if (Intrinsics.areEqual((Object) view3, (Object) view2)) {
            return Boolean.TRUE;
        }
        if (Intrinsics.areEqual((Object) view3, (Object) view)) {
            return Boolean.FALSE;
        }
        if (!(view3 instanceof ViewGroup)) {
            return null;
        }
        ViewGroup viewGroup = (ViewGroup) view3;
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            Boolean isViewDisplayedOverAnother = isViewDisplayedOverAnother(view, view2, viewConfigHelper.getChildInDrawingOrderAtIndex(viewGroup, i));
            if (isViewDisplayedOverAnother != null) {
                return isViewDisplayedOverAnother;
            }
        }
        return null;
    }

    public boolean shouldBeCancelledBy(GestureHandler gestureHandler) {
        Intrinsics.checkNotNullParameter(gestureHandler, "handler");
        if (gestureHandler instanceof HoverGestureHandler) {
            HoverGestureHandler hoverGestureHandler = (HoverGestureHandler) gestureHandler;
            if (!hoverGestureHandler.isAncestorOf(this)) {
                View view = hoverGestureHandler.getView();
                Intrinsics.checkNotNull(view);
                View view2 = getView();
                Intrinsics.checkNotNull(view2);
                Boolean isViewDisplayedOverAnother$default = isViewDisplayedOverAnother$default(this, view, view2, (View) null, 4, (Object) null);
                Intrinsics.checkNotNull(isViewDisplayedOverAnother$default);
                return isViewDisplayedOverAnother$default.booleanValue();
            }
        }
        return super.shouldBeCancelledBy(gestureHandler);
    }

    public boolean shouldRequireToWaitForFailure(GestureHandler gestureHandler) {
        Intrinsics.checkNotNullParameter(gestureHandler, "handler");
        if ((gestureHandler instanceof HoverGestureHandler) && !isAncestorOf(gestureHandler)) {
            HoverGestureHandler hoverGestureHandler = (HoverGestureHandler) gestureHandler;
            if (!hoverGestureHandler.isAncestorOf(this)) {
                View view = getView();
                Intrinsics.checkNotNull(view);
                View view2 = hoverGestureHandler.getView();
                Intrinsics.checkNotNull(view2);
                Boolean isViewDisplayedOverAnother$default = isViewDisplayedOverAnother$default(this, view, view2, (View) null, 4, (Object) null);
                if (isViewDisplayedOverAnother$default != null) {
                    return isViewDisplayedOverAnother$default.booleanValue();
                }
            }
        }
        return super.shouldRequireToWaitForFailure(gestureHandler);
    }

    public boolean shouldRecognizeSimultaneously(GestureHandler gestureHandler) {
        Intrinsics.checkNotNullParameter(gestureHandler, "handler");
        if ((!(gestureHandler instanceof HoverGestureHandler) || (!isAncestorOf(gestureHandler) && !((HoverGestureHandler) gestureHandler).isAncestorOf(this))) && !(gestureHandler instanceof RNGestureHandlerRootHelper.RootViewGestureHandler)) {
            return super.shouldRecognizeSimultaneously(gestureHandler);
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void onHandle(MotionEvent motionEvent, MotionEvent motionEvent2) {
        Intrinsics.checkNotNullParameter(motionEvent, "event");
        Intrinsics.checkNotNullParameter(motionEvent2, "sourceEvent");
        if (motionEvent.getAction() == 0) {
            Handler handler2 = this.handler;
            if (handler2 != null) {
                handler2.removeCallbacksAndMessages((Object) null);
            }
            this.handler = null;
        } else if (motionEvent.getAction() == 1 && !isWithinBounds()) {
            finish();
        }
    }

    /* access modifiers changed from: protected */
    public void onHandleHover(MotionEvent motionEvent, MotionEvent motionEvent2) {
        Intrinsics.checkNotNullParameter(motionEvent, "event");
        Intrinsics.checkNotNullParameter(motionEvent2, "sourceEvent");
        if (motionEvent.getAction() == 10) {
            if (this.handler == null) {
                this.handler = new Handler(Looper.getMainLooper());
            }
            Handler handler2 = this.handler;
            Intrinsics.checkNotNull(handler2);
            handler2.postDelayed(this.finishRunnable, 4);
        } else if (!isWithinBounds()) {
            finish();
        } else if (getState() == 4 && motionEvent.getToolType(0) == 2) {
            this.stylusData = StylusData.Companion.fromEvent(motionEvent);
        } else if (getState() != 0) {
        } else {
            if (motionEvent.getAction() == 7 || motionEvent.getAction() == 9) {
                begin();
                activate();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onReset() {
        super.onReset();
        this.stylusData = new StylusData(0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 31, (DefaultConstructorMarker) null);
    }

    private final void finish() {
        int state = getState();
        if (state == 0) {
            cancel();
        } else if (state == 2) {
            fail();
        } else if (state == 4) {
            end();
        }
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
