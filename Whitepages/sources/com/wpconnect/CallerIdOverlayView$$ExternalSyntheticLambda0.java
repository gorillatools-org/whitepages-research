package com.wpconnect;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public final /* synthetic */ class CallerIdOverlayView$$ExternalSyntheticLambda0 implements View.OnTouchListener {
    public final /* synthetic */ GestureDetector f$0;

    public /* synthetic */ CallerIdOverlayView$$ExternalSyntheticLambda0(GestureDetector gestureDetector) {
        this.f$0 = gestureDetector;
    }

    public final boolean onTouch(View view, MotionEvent motionEvent) {
        return CallerIdOverlayView.setupSwipeToDismiss$lambda$2(this.f$0, view, motionEvent);
    }
}
