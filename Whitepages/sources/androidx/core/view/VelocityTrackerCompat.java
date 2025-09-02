package androidx.core.view;

import android.os.Build;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;

public abstract class VelocityTrackerCompat {
    private static Map sFallbackTrackers = Collections.synchronizedMap(new WeakHashMap());

    public static float getAxisVelocity(VelocityTracker velocityTracker, int i) {
        if (Build.VERSION.SDK_INT >= 34) {
            return Api34Impl.getAxisVelocity(velocityTracker, i);
        }
        if (i == 0) {
            return velocityTracker.getXVelocity();
        }
        if (i == 1) {
            return velocityTracker.getYVelocity();
        }
        VelocityTrackerFallback fallbackTrackerOrNull = getFallbackTrackerOrNull(velocityTracker);
        if (fallbackTrackerOrNull != null) {
            return fallbackTrackerOrNull.getAxisVelocity(i);
        }
        return 0.0f;
    }

    public static void computeCurrentVelocity(VelocityTracker velocityTracker, int i, float f) {
        velocityTracker.computeCurrentVelocity(i, f);
        VelocityTrackerFallback fallbackTrackerOrNull = getFallbackTrackerOrNull(velocityTracker);
        if (fallbackTrackerOrNull != null) {
            fallbackTrackerOrNull.computeCurrentVelocity(i, f);
        }
    }

    public static void computeCurrentVelocity(VelocityTracker velocityTracker, int i) {
        computeCurrentVelocity(velocityTracker, i, Float.MAX_VALUE);
    }

    public static void addMovement(VelocityTracker velocityTracker, MotionEvent motionEvent) {
        velocityTracker.addMovement(motionEvent);
        if (Build.VERSION.SDK_INT < 34 && motionEvent.getSource() == 4194304) {
            if (!sFallbackTrackers.containsKey(velocityTracker)) {
                sFallbackTrackers.put(velocityTracker, new VelocityTrackerFallback());
            }
            ((VelocityTrackerFallback) sFallbackTrackers.get(velocityTracker)).addMovement(motionEvent);
        }
    }

    private static VelocityTrackerFallback getFallbackTrackerOrNull(VelocityTracker velocityTracker) {
        return (VelocityTrackerFallback) sFallbackTrackers.get(velocityTracker);
    }

    private static class Api34Impl {
        static boolean isAxisSupported(VelocityTracker velocityTracker, int i) {
            return velocityTracker.isAxisSupported(i);
        }

        static float getAxisVelocity(VelocityTracker velocityTracker, int i, int i2) {
            return velocityTracker.getAxisVelocity(i, i2);
        }

        static float getAxisVelocity(VelocityTracker velocityTracker, int i) {
            return velocityTracker.getAxisVelocity(i);
        }
    }
}
