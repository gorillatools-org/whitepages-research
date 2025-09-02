package com.google.firebase.crashlytics.internal.metadata;

import com.google.firebase.crashlytics.internal.metadata.UserMetadata;

public final /* synthetic */ class UserMetadata$SerializeableKeysMap$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ UserMetadata.SerializeableKeysMap f$0;

    public /* synthetic */ UserMetadata$SerializeableKeysMap$$ExternalSyntheticLambda0(UserMetadata.SerializeableKeysMap serializeableKeysMap) {
        this.f$0 = serializeableKeysMap;
    }

    public final void run() {
        this.f$0.lambda$scheduleSerializationTaskIfNeeded$0();
    }
}
