package com.google.firebase.remoteconfig;

import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.channels.ChannelsKt;
import kotlinx.coroutines.channels.ProducerScope;

public final class RemoteConfigKt$configUpdates$1$registration$1 implements ConfigUpdateListener {
    final /* synthetic */ ProducerScope $$this$callbackFlow;
    final /* synthetic */ FirebaseRemoteConfig $this_configUpdates;

    RemoteConfigKt$configUpdates$1$registration$1(FirebaseRemoteConfig firebaseRemoteConfig, ProducerScope producerScope) {
        this.$this_configUpdates = firebaseRemoteConfig;
        this.$$this$callbackFlow = producerScope;
    }

    /* access modifiers changed from: private */
    public static final void onUpdate$lambda$0(ProducerScope producerScope, ConfigUpdate configUpdate) {
        Intrinsics.checkNotNullParameter(producerScope, "$$this$callbackFlow");
        Intrinsics.checkNotNullParameter(configUpdate, "$configUpdate");
        ChannelsKt.trySendBlocking(producerScope, configUpdate);
    }

    public void onUpdate(ConfigUpdate configUpdate) {
        Intrinsics.checkNotNullParameter(configUpdate, "configUpdate");
        this.$this_configUpdates.schedule(new RemoteConfigKt$configUpdates$1$registration$1$$ExternalSyntheticLambda0(this.$$this$callbackFlow, configUpdate));
    }

    public void onError(FirebaseRemoteConfigException firebaseRemoteConfigException) {
        Intrinsics.checkNotNullParameter(firebaseRemoteConfigException, "error");
        CoroutineScopeKt.cancel(this.$$this$callbackFlow, "Error listening for config updates.", firebaseRemoteConfigException);
    }
}
