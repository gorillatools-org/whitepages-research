package com.google.firebase.messaging;

import com.google.android.gms.tasks.TaskCompletionSource;

public final /* synthetic */ class FirebaseMessaging$$ExternalSyntheticLambda9 implements Runnable {
    public final /* synthetic */ FirebaseMessaging f$0;
    public final /* synthetic */ TaskCompletionSource f$1;

    public /* synthetic */ FirebaseMessaging$$ExternalSyntheticLambda9(FirebaseMessaging firebaseMessaging, TaskCompletionSource taskCompletionSource) {
        this.f$0 = firebaseMessaging;
        this.f$1 = taskCompletionSource;
    }

    public final void run() {
        this.f$0.lambda$deleteToken$8(this.f$1);
    }
}
