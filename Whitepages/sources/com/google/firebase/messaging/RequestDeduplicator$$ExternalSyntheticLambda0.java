package com.google.firebase.messaging;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

public final /* synthetic */ class RequestDeduplicator$$ExternalSyntheticLambda0 implements Continuation {
    public final /* synthetic */ RequestDeduplicator f$0;
    public final /* synthetic */ String f$1;

    public /* synthetic */ RequestDeduplicator$$ExternalSyntheticLambda0(RequestDeduplicator requestDeduplicator, String str) {
        this.f$0 = requestDeduplicator;
        this.f$1 = str;
    }

    public final Object then(Task task) {
        return this.f$0.lambda$getOrStartGetTokenRequest$0(this.f$1, task);
    }
}
