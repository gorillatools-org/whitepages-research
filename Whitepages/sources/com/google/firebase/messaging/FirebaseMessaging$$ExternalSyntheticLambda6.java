package com.google.firebase.messaging;

import com.google.android.gms.cloudmessaging.CloudMessage;
import com.google.android.gms.tasks.OnSuccessListener;

public final /* synthetic */ class FirebaseMessaging$$ExternalSyntheticLambda6 implements OnSuccessListener {
    public final /* synthetic */ FirebaseMessaging f$0;

    public /* synthetic */ FirebaseMessaging$$ExternalSyntheticLambda6(FirebaseMessaging firebaseMessaging) {
        this.f$0 = firebaseMessaging;
    }

    public final void onSuccess(Object obj) {
        this.f$0.lambda$handleProxiedNotificationData$5((CloudMessage) obj);
    }
}
