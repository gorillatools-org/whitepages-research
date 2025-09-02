package com.google.firebase.remoteconfig.internal;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import java.util.Map;

public final /* synthetic */ class ConfigFetchHandler$$ExternalSyntheticLambda3 implements Continuation {
    public final /* synthetic */ ConfigFetchHandler f$0;
    public final /* synthetic */ Map f$1;

    public /* synthetic */ ConfigFetchHandler$$ExternalSyntheticLambda3(ConfigFetchHandler configFetchHandler, Map map) {
        this.f$0 = configFetchHandler;
        this.f$1 = map;
    }

    public final Object then(Task task) {
        return this.f$0.lambda$fetchNowWithTypeAndAttemptNumber$1(this.f$1, task);
    }
}
