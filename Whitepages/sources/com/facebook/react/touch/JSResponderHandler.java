package com.facebook.react.touch;

import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.ViewParent;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class JSResponderHandler implements OnInterceptTouchEventListener {
    private static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final int JS_RESPONDER_UNSET = -1;
    private volatile int currentJSResponder = -1;
    private ViewParent viewParentBlockingNativeResponder;

    public final void setJSResponder(int i, ViewParent viewParent) {
        this.currentJSResponder = i;
        maybeUnblockNativeResponder();
        if (viewParent != null) {
            viewParent.requestDisallowInterceptTouchEvent(true);
            this.viewParentBlockingNativeResponder = viewParent;
        }
    }

    public final void clearJSResponder() {
        this.currentJSResponder = -1;
        maybeUnblockNativeResponder();
    }

    private final void maybeUnblockNativeResponder() {
        ViewParent viewParent = this.viewParentBlockingNativeResponder;
        if (viewParent != null) {
            viewParent.requestDisallowInterceptTouchEvent(false);
        }
        this.viewParentBlockingNativeResponder = null;
    }

    public boolean onInterceptTouchEvent(ViewGroup viewGroup, MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(viewGroup, "view");
        Intrinsics.checkNotNullParameter(motionEvent, "event");
        int i = this.currentJSResponder;
        if (i == -1 || motionEvent.getAction() == 1 || viewGroup.getId() != i) {
            return false;
        }
        return true;
    }

    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
