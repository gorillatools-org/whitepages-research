package com.google.firebase.messaging;

import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;

public final /* synthetic */ class FirebaseMessaging$$ExternalSyntheticLambda0 implements SuccessContinuation {
    public final /* synthetic */ String f$0;

    public /* synthetic */ FirebaseMessaging$$ExternalSyntheticLambda0(String str) {
        this.f$0 = str;
    }

    public final Task then(Object obj) {
        return ((TopicsSubscriber) obj).subscribeToTopic(this.f$0);
    }
}
