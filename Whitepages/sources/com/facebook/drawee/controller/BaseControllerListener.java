package com.facebook.drawee.controller;

import android.graphics.drawable.Animatable;

public class BaseControllerListener implements ControllerListener {
    private static final ControllerListener NO_OP_LISTENER = new BaseControllerListener();

    public void onFailure(String str, Throwable th) {
    }

    public void onFinalImageSet(String str, Object obj, Animatable animatable) {
    }

    public void onIntermediateImageFailed(String str, Throwable th) {
    }

    public void onIntermediateImageSet(String str, Object obj) {
    }

    public void onRelease(String str) {
    }

    public void onSubmit(String str, Object obj) {
    }

    public static ControllerListener getNoOpListener() {
        return NO_OP_LISTENER;
    }
}
