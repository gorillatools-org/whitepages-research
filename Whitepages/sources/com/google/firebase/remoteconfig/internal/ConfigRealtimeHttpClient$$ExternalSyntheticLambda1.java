package com.google.firebase.remoteconfig.internal;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

public final /* synthetic */ class ConfigRealtimeHttpClient$$ExternalSyntheticLambda1 implements Continuation {
    public final /* synthetic */ ConfigRealtimeHttpClient f$0;
    public final /* synthetic */ Task f$1;

    public /* synthetic */ ConfigRealtimeHttpClient$$ExternalSyntheticLambda1(ConfigRealtimeHttpClient configRealtimeHttpClient, Task task) {
        this.f$0 = configRealtimeHttpClient;
        this.f$1 = task;
    }

    public final Object then(Task task) {
        return this.f$0.lambda$beginRealtimeHttpStream$1(this.f$1, task);
    }
}
