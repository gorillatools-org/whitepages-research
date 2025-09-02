package com.swmansion.gesturehandler.core;

import android.view.MotionEvent;
import kotlin.jvm.internal.Intrinsics;

public final class GestureUtils {
    public static final GestureUtils INSTANCE = new GestureUtils();

    private GestureUtils() {
    }

    public final float getLastPointerX(MotionEvent motionEvent, boolean z) {
        Intrinsics.checkNotNullParameter(motionEvent, "event");
        int actionIndex = motionEvent.getActionMasked() == 6 ? motionEvent.getActionIndex() : -1;
        if (z) {
            int pointerCount = motionEvent.getPointerCount();
            float f = 0.0f;
            int i = 0;
            for (int i2 = 0; i2 < pointerCount; i2++) {
                if (i2 != actionIndex) {
                    f += motionEvent.getX(i2);
                    i++;
                }
            }
            return f / ((float) i);
        }
        int pointerCount2 = motionEvent.getPointerCount();
        int i3 = pointerCount2 - 1;
        if (i3 == actionIndex) {
            i3 = pointerCount2 - 2;
        }
        return motionEvent.getX(i3);
    }

    public final float getLastPointerY(MotionEvent motionEvent, boolean z) {
        Intrinsics.checkNotNullParameter(motionEvent, "event");
        int actionIndex = motionEvent.getActionMasked() == 6 ? motionEvent.getActionIndex() : -1;
        if (z) {
            int pointerCount = motionEvent.getPointerCount();
            float f = 0.0f;
            int i = 0;
            for (int i2 = 0; i2 < pointerCount; i2++) {
                if (i2 != actionIndex) {
                    f += motionEvent.getY(i2);
                    i++;
                }
            }
            return f / ((float) i);
        }
        int pointerCount2 = motionEvent.getPointerCount();
        int i3 = pointerCount2 - 1;
        if (i3 == actionIndex) {
            i3 = pointerCount2 - 2;
        }
        return motionEvent.getY(i3);
    }

    public final double coneToDeviation(double d) {
        return Math.cos(Math.toRadians(d / 2.0d));
    }
}
