package io.invertase.firebase.common;

public final /* synthetic */ class ReactNativeFirebaseEventEmitter$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ ReactNativeFirebaseEventEmitter f$0;

    public /* synthetic */ ReactNativeFirebaseEventEmitter$$ExternalSyntheticLambda2(ReactNativeFirebaseEventEmitter reactNativeFirebaseEventEmitter) {
        this.f$0 = reactNativeFirebaseEventEmitter;
    }

    public final void run() {
        this.f$0.sendQueuedEvents();
    }
}
