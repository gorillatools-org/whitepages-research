package com.google.firebase.messaging;

import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.RequestDeduplicator;
import com.google.firebase.messaging.Store;

public final /* synthetic */ class FirebaseMessaging$$ExternalSyntheticLambda8 implements RequestDeduplicator.GetTokenRequest {
    public final /* synthetic */ FirebaseMessaging f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ Store.Token f$2;

    public /* synthetic */ FirebaseMessaging$$ExternalSyntheticLambda8(FirebaseMessaging firebaseMessaging, String str, Store.Token token) {
        this.f$0 = firebaseMessaging;
        this.f$1 = str;
        this.f$2 = token;
    }

    public final Task start() {
        return this.f$0.lambda$blockingGetToken$14(this.f$1, this.f$2);
    }
}
