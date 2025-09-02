package com.facebook.drawee.controller;

import android.graphics.drawable.Animatable;

public interface ControllerListener {
    void onFailure(String str, Throwable th);

    void onFinalImageSet(String str, Object obj, Animatable animatable);

    void onIntermediateImageFailed(String str, Throwable th);

    void onIntermediateImageSet(String str, Object obj);

    void onRelease(String str);

    void onSubmit(String str, Object obj);
}
