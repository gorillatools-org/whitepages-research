package com.google.firebase.remoteconfig.internal;

import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.remoteconfig.internal.ConfigFetchHandler;

public final /* synthetic */ class ConfigFetchHandler$$ExternalSyntheticLambda4 implements SuccessContinuation {
    public final /* synthetic */ ConfigFetchHandler.FetchResponse f$0;

    public /* synthetic */ ConfigFetchHandler$$ExternalSyntheticLambda4(ConfigFetchHandler.FetchResponse fetchResponse) {
        this.f$0 = fetchResponse;
    }

    public final Task then(Object obj) {
        return Tasks.forResult(this.f$0);
    }
}
