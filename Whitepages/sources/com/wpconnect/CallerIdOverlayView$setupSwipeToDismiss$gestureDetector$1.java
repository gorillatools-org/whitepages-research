package com.wpconnect;

import android.view.GestureDetector;
import android.view.MotionEvent;
import kotlin.jvm.internal.Intrinsics;

public final class CallerIdOverlayView$setupSwipeToDismiss$gestureDetector$1 extends GestureDetector.SimpleOnGestureListener {
    final /* synthetic */ CallerIdOverlayView this$0;

    CallerIdOverlayView$setupSwipeToDismiss$gestureDetector$1(CallerIdOverlayView callerIdOverlayView) {
        this.this$0 = callerIdOverlayView;
    }

    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        Intrinsics.checkNotNullParameter(motionEvent2, "e2");
        if (Math.abs(f) <= 1000.0f && Math.abs(f2) <= 1000.0f) {
            return false;
        }
        this.this$0.animateExit();
        return true;
    }
}
