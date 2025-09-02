package com.facebook.react.uimanager.events;

import android.view.MotionEvent;
import android.view.View;
import com.facebook.react.uimanager.RootView;
import com.facebook.react.uimanager.RootViewUtil;
import kotlin.jvm.internal.Intrinsics;

public final class NativeGestureUtil {
    public static final NativeGestureUtil INSTANCE = new NativeGestureUtil();

    private NativeGestureUtil() {
    }

    public static final void notifyNativeGestureStarted(View view, MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(motionEvent, "event");
        RootView rootView = RootViewUtil.getRootView(view);
        if (rootView != null) {
            rootView.onChildStartedNativeGesture(view, motionEvent);
        }
    }

    public static final void notifyNativeGestureEnded(View view, MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(motionEvent, "event");
        RootView rootView = RootViewUtil.getRootView(view);
        if (rootView != null) {
            rootView.onChildEndedNativeGesture(view, motionEvent);
        }
    }
}
