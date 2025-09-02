package com.google.firebase.remoteconfig.ktx;

import com.google.firebase.remoteconfig.ConfigUpdate;
import kotlinx.coroutines.channels.ProducerScope;

public final /* synthetic */ class RemoteConfigKt$configUpdates$1$registration$1$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ ProducerScope f$0;
    public final /* synthetic */ ConfigUpdate f$1;

    public /* synthetic */ RemoteConfigKt$configUpdates$1$registration$1$$ExternalSyntheticLambda0(ProducerScope producerScope, ConfigUpdate configUpdate) {
        this.f$0 = producerScope;
        this.f$1 = configUpdate;
    }

    public final void run() {
        RemoteConfigKt$configUpdates$1$registration$1.onUpdate$lambda$0(this.f$0, this.f$1);
    }
}
