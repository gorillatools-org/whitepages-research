package com.swmansion.gesturehandler.core;

import android.view.MotionEvent;
import android.view.View;
import com.swmansion.gesturehandler.core.NativeViewGestureHandler;

public final class NativeViewGestureHandler$Companion$defaultHook$1 implements NativeViewGestureHandler.NativeViewGestureHandlerHook {
    NativeViewGestureHandler$Companion$defaultHook$1() {
    }

    public void afterGestureEnd(MotionEvent motionEvent) {
        NativeViewGestureHandler.NativeViewGestureHandlerHook.DefaultImpls.afterGestureEnd(this, motionEvent);
    }

    public boolean canActivate(View view) {
        return NativeViewGestureHandler.NativeViewGestureHandlerHook.DefaultImpls.canActivate(this, view);
    }

    public boolean canBegin(MotionEvent motionEvent) {
        return NativeViewGestureHandler.NativeViewGestureHandlerHook.DefaultImpls.canBegin(this, motionEvent);
    }

    public void handleEventBeforeActivation(MotionEvent motionEvent) {
        NativeViewGestureHandler.NativeViewGestureHandlerHook.DefaultImpls.handleEventBeforeActivation(this, motionEvent);
    }

    public Boolean sendTouchEvent(View view, MotionEvent motionEvent) {
        return NativeViewGestureHandler.NativeViewGestureHandlerHook.DefaultImpls.sendTouchEvent(this, view, motionEvent);
    }

    public boolean shouldCancelRootViewGestureHandlerIfNecessary() {
        return NativeViewGestureHandler.NativeViewGestureHandlerHook.DefaultImpls.shouldCancelRootViewGestureHandlerIfNecessary(this);
    }

    public Boolean shouldRecognizeSimultaneously(GestureHandler gestureHandler) {
        return NativeViewGestureHandler.NativeViewGestureHandlerHook.DefaultImpls.shouldRecognizeSimultaneously(this, gestureHandler);
    }

    public boolean wantsToHandleEventBeforeActivation() {
        return NativeViewGestureHandler.NativeViewGestureHandlerHook.DefaultImpls.wantsToHandleEventBeforeActivation(this);
    }
}
