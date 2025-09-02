package com.google.firebase.crashlytics.internal.metadata;

public final /* synthetic */ class UserMetadata$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ UserMetadata f$0;

    public /* synthetic */ UserMetadata$$ExternalSyntheticLambda2(UserMetadata userMetadata) {
        this.f$0 = userMetadata;
    }

    public final void run() {
        this.f$0.serializeUserDataIfNeeded();
    }
}
