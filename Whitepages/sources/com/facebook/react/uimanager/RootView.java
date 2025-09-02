package com.facebook.react.uimanager;

import android.view.MotionEvent;
import android.view.View;
import kotlin.jvm.internal.Intrinsics;

public interface RootView {
    void handleException(Throwable th);

    void onChildEndedNativeGesture(View view, MotionEvent motionEvent);

    void onChildStartedNativeGesture(View view, MotionEvent motionEvent);

    void onChildStartedNativeGesture(MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(motionEvent, "ev");
        onChildStartedNativeGesture((View) null, motionEvent);
    }
}
