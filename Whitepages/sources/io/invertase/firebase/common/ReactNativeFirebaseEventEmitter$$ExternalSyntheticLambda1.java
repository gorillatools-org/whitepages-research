package io.invertase.firebase.common;

import com.facebook.react.bridge.ReactContext;

public final /* synthetic */ class ReactNativeFirebaseEventEmitter$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ ReactNativeFirebaseEventEmitter f$0;
    public final /* synthetic */ ReactContext f$1;

    public /* synthetic */ ReactNativeFirebaseEventEmitter$$ExternalSyntheticLambda1(ReactNativeFirebaseEventEmitter reactNativeFirebaseEventEmitter, ReactContext reactContext) {
        this.f$0 = reactNativeFirebaseEventEmitter;
        this.f$1 = reactContext;
    }

    public final void run() {
        this.f$0.lambda$attachReactContext$0(this.f$1);
    }
}
