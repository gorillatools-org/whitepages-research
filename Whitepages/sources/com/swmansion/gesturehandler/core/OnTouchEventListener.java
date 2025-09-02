package com.swmansion.gesturehandler.core;

import android.view.MotionEvent;

public interface OnTouchEventListener {
    void onHandlerUpdate(GestureHandler gestureHandler, MotionEvent motionEvent);

    void onStateChange(GestureHandler gestureHandler, int i, int i2);

    void onTouchEvent(GestureHandler gestureHandler);
}
