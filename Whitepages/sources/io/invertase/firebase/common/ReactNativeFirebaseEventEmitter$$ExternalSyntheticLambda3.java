package io.invertase.firebase.common;

import io.invertase.firebase.interfaces.NativeEvent;

public final /* synthetic */ class ReactNativeFirebaseEventEmitter$$ExternalSyntheticLambda3 implements Runnable {
    public final /* synthetic */ ReactNativeFirebaseEventEmitter f$0;
    public final /* synthetic */ NativeEvent f$1;

    public /* synthetic */ ReactNativeFirebaseEventEmitter$$ExternalSyntheticLambda3(ReactNativeFirebaseEventEmitter reactNativeFirebaseEventEmitter, NativeEvent nativeEvent) {
        this.f$0 = reactNativeFirebaseEventEmitter;
        this.f$1 = nativeEvent;
    }

    public final void run() {
        this.f$0.lambda$sendEvent$2(this.f$1);
    }
}
