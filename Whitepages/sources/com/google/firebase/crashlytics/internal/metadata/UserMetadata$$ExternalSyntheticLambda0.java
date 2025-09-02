package com.google.firebase.crashlytics.internal.metadata;

import java.util.List;

public final /* synthetic */ class UserMetadata$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ UserMetadata f$0;
    public final /* synthetic */ List f$1;

    public /* synthetic */ UserMetadata$$ExternalSyntheticLambda0(UserMetadata userMetadata, List list) {
        this.f$0 = userMetadata;
        this.f$1 = list;
    }

    public final void run() {
        this.f$0.lambda$updateRolloutsState$1(this.f$1);
    }
}
