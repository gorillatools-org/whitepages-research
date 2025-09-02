package com.facebook.react.animated;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.JSApplicationCausedNativeException;
import com.facebook.react.bridge.ReadableMap;
import kotlin.jvm.internal.Intrinsics;

public abstract class AnimationDriver {
    public ValueAnimatedNode animatedValue;
    public Callback endCallback;
    public boolean hasFinished;
    public int id;

    public abstract void runAnimationStep(long j);

    public void resetConfig(ReadableMap readableMap) {
        Intrinsics.checkNotNullParameter(readableMap, "config");
        String simpleName = getClass().getSimpleName();
        throw new JSApplicationCausedNativeException("Animation config for " + simpleName + " cannot be reset");
    }
}
