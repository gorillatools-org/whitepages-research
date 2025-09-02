package com.swmansion.gesturehandler.core;

import com.swmansion.gesturehandler.core.ScaleGestureDetector;
import kotlin.jvm.internal.Intrinsics;

public final class PinchGestureHandler$gestureListener$1 implements ScaleGestureDetector.OnScaleGestureListener {
    final /* synthetic */ PinchGestureHandler this$0;

    public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
        Intrinsics.checkNotNullParameter(scaleGestureDetector, "detector");
    }

    PinchGestureHandler$gestureListener$1(PinchGestureHandler pinchGestureHandler) {
        this.this$0 = pinchGestureHandler;
        pinchGestureHandler.setShouldCancelWhenOutside(false);
    }

    public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
        Intrinsics.checkNotNullParameter(scaleGestureDetector, "detector");
        double scale = this.this$0.getScale();
        PinchGestureHandler pinchGestureHandler = this.this$0;
        pinchGestureHandler.scale = pinchGestureHandler.getScale() * ((double) scaleGestureDetector.getScaleFactor());
        double timeDeltaSeconds = scaleGestureDetector.getTimeDeltaSeconds();
        if (timeDeltaSeconds > 0.0d) {
            PinchGestureHandler pinchGestureHandler2 = this.this$0;
            pinchGestureHandler2.velocity = (pinchGestureHandler2.getScale() - scale) / timeDeltaSeconds;
        }
        if (Math.abs(this.this$0.startingSpan - scaleGestureDetector.getCurrentSpan()) < this.this$0.spanSlop || this.this$0.getState() != 2) {
            return true;
        }
        this.this$0.activate();
        return true;
    }

    public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
        Intrinsics.checkNotNullParameter(scaleGestureDetector, "detector");
        this.this$0.startingSpan = scaleGestureDetector.getCurrentSpan();
        return true;
    }
}
