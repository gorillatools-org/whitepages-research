package com.google.firebase.remoteconfig.internal;

import java.util.concurrent.Callable;

public final /* synthetic */ class ConfigCacheClient$$ExternalSyntheticLambda0 implements Callable {
    public final /* synthetic */ ConfigCacheClient f$0;
    public final /* synthetic */ ConfigContainer f$1;

    public /* synthetic */ ConfigCacheClient$$ExternalSyntheticLambda0(ConfigCacheClient configCacheClient, ConfigContainer configContainer) {
        this.f$0 = configCacheClient;
        this.f$1 = configContainer;
    }

    public final Object call() {
        return this.f$0.lambda$put$0(this.f$1);
    }
}
