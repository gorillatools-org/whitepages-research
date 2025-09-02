package com.google.firebase.remoteconfig.internal;

import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;

public final /* synthetic */ class ConfigCacheClient$$ExternalSyntheticLambda1 implements SuccessContinuation {
    public final /* synthetic */ ConfigCacheClient f$0;
    public final /* synthetic */ boolean f$1;
    public final /* synthetic */ ConfigContainer f$2;

    public /* synthetic */ ConfigCacheClient$$ExternalSyntheticLambda1(ConfigCacheClient configCacheClient, boolean z, ConfigContainer configContainer) {
        this.f$0 = configCacheClient;
        this.f$1 = z;
        this.f$2 = configContainer;
    }

    public final Task then(Object obj) {
        return this.f$0.lambda$put$1(this.f$1, this.f$2, (Void) obj);
    }
}
