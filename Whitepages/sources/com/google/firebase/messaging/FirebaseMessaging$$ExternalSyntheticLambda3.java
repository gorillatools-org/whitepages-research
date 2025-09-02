package com.google.firebase.messaging;

import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.Store;

public final /* synthetic */ class FirebaseMessaging$$ExternalSyntheticLambda3 implements SuccessContinuation {
    public final /* synthetic */ FirebaseMessaging f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ Store.Token f$2;

    public /* synthetic */ FirebaseMessaging$$ExternalSyntheticLambda3(FirebaseMessaging firebaseMessaging, String str, Store.Token token) {
        this.f$0 = firebaseMessaging;
        this.f$1 = str;
        this.f$2 = token;
    }

    public final Task then(Object obj) {
        return this.f$0.lambda$blockingGetToken$13(this.f$1, this.f$2, (String) obj);
    }
}
