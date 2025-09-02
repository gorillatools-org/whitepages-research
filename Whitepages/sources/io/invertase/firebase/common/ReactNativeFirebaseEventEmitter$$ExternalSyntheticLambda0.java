package io.invertase.firebase.common;

public final /* synthetic */ class ReactNativeFirebaseEventEmitter$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ ReactNativeFirebaseEventEmitter f$0;
    public final /* synthetic */ Boolean f$1;

    public /* synthetic */ ReactNativeFirebaseEventEmitter$$ExternalSyntheticLambda0(ReactNativeFirebaseEventEmitter reactNativeFirebaseEventEmitter, Boolean bool) {
        this.f$0 = reactNativeFirebaseEventEmitter;
        this.f$1 = bool;
    }

    public final void run() {
        this.f$0.lambda$notifyJsReady$1(this.f$1);
    }
}
