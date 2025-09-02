package com.swmansion.gesturehandler.core;

import com.swmansion.gesturehandler.core.RotationGestureDetector;
import kotlin.jvm.internal.Intrinsics;

public final class RotationGestureHandler$gestureListener$1 implements RotationGestureDetector.OnRotationGestureListener {
    final /* synthetic */ RotationGestureHandler this$0;

    public boolean onRotationBegin(RotationGestureDetector rotationGestureDetector) {
        Intrinsics.checkNotNullParameter(rotationGestureDetector, "detector");
        return true;
    }

    RotationGestureHandler$gestureListener$1(RotationGestureHandler rotationGestureHandler) {
        this.this$0 = rotationGestureHandler;
    }

    public boolean onRotation(RotationGestureDetector rotationGestureDetector) {
        Intrinsics.checkNotNullParameter(rotationGestureDetector, "detector");
        double rotation = this.this$0.getRotation();
        RotationGestureHandler rotationGestureHandler = this.this$0;
        rotationGestureHandler.rotation = rotationGestureHandler.getRotation() + rotationGestureDetector.getRotation();
        long timeDelta = rotationGestureDetector.getTimeDelta();
        if (timeDelta > 0) {
            RotationGestureHandler rotationGestureHandler2 = this.this$0;
            rotationGestureHandler2.velocity = (rotationGestureHandler2.getRotation() - rotation) / ((double) timeDelta);
        }
        if (Math.abs(this.this$0.getRotation()) < 0.08726646259971647d || this.this$0.getState() != 2) {
            return true;
        }
        this.this$0.activate();
        return true;
    }

    public void onRotationEnd(RotationGestureDetector rotationGestureDetector) {
        Intrinsics.checkNotNullParameter(rotationGestureDetector, "detector");
        this.this$0.end();
    }
}
