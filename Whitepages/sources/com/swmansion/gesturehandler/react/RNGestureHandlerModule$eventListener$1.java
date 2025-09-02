package com.swmansion.gesturehandler.react;

import android.view.MotionEvent;
import com.swmansion.gesturehandler.core.GestureHandler;
import com.swmansion.gesturehandler.core.OnTouchEventListener;
import kotlin.jvm.internal.Intrinsics;

public final class RNGestureHandlerModule$eventListener$1 implements OnTouchEventListener {
    final /* synthetic */ RNGestureHandlerModule this$0;

    RNGestureHandlerModule$eventListener$1(RNGestureHandlerModule rNGestureHandlerModule) {
        this.this$0 = rNGestureHandlerModule;
    }

    public void onHandlerUpdate(GestureHandler gestureHandler, MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(gestureHandler, "handler");
        Intrinsics.checkNotNullParameter(motionEvent, "event");
        this.this$0.onHandlerUpdate(gestureHandler);
    }

    public void onStateChange(GestureHandler gestureHandler, int i, int i2) {
        Intrinsics.checkNotNullParameter(gestureHandler, "handler");
        this.this$0.onStateChange(gestureHandler, i, i2);
    }

    public void onTouchEvent(GestureHandler gestureHandler) {
        Intrinsics.checkNotNullParameter(gestureHandler, "handler");
        this.this$0.onTouchEvent(gestureHandler);
    }
}
