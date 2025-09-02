package com.google.firebase.remoteconfig;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerScope;

@DebugMetadata(c = "com.google.firebase.remoteconfig.RemoteConfigKt$configUpdates$1", f = "RemoteConfig.kt", l = {76}, m = "invokeSuspend")
final class RemoteConfigKt$configUpdates$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ FirebaseRemoteConfig $this_configUpdates;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RemoteConfigKt$configUpdates$1(FirebaseRemoteConfig firebaseRemoteConfig, Continuation continuation) {
        super(2, continuation);
        this.$this_configUpdates = firebaseRemoteConfig;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        RemoteConfigKt$configUpdates$1 remoteConfigKt$configUpdates$1 = new RemoteConfigKt$configUpdates$1(this.$this_configUpdates, continuation);
        remoteConfigKt$configUpdates$1.L$0 = obj;
        return remoteConfigKt$configUpdates$1;
    }

    public final Object invoke(ProducerScope producerScope, Continuation continuation) {
        return ((RemoteConfigKt$configUpdates$1) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            ProducerScope producerScope = (ProducerScope) this.L$0;
            FirebaseRemoteConfig firebaseRemoteConfig = this.$this_configUpdates;
            final ConfigUpdateListenerRegistration addOnConfigUpdateListener = firebaseRemoteConfig.addOnConfigUpdateListener(new RemoteConfigKt$configUpdates$1$registration$1(firebaseRemoteConfig, producerScope));
            Intrinsics.checkNotNullExpressionValue(addOnConfigUpdateListener, "FirebaseRemoteConfig.con…      }\n        }\n      )");
            AnonymousClass1 r3 = new Function0() {
                public final void invoke() {
                    addOnConfigUpdateListener.remove();
                }
            };
            this.label = 1;
            if (ProduceKt.awaitClose(producerScope, r3, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
