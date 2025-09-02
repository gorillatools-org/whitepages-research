package com.swmansion.gesturehandler.core;

import android.view.MotionEvent;
import kotlin.jvm.internal.Intrinsics;

public final class ManualGestureHandler extends GestureHandler {
    /* access modifiers changed from: protected */
    public void onHandle(MotionEvent motionEvent, MotionEvent motionEvent2) {
        Intrinsics.checkNotNullParameter(motionEvent, "event");
        Intrinsics.checkNotNullParameter(motionEvent2, "sourceEvent");
        if (getState() == 0) {
            begin();
        }
    }
}
