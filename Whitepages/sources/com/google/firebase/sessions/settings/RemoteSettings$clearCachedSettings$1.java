package com.google.firebase.sessions.settings;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@DebugMetadata(c = "com.google.firebase.sessions.settings.RemoteSettings$clearCachedSettings$1", f = "RemoteSettings.kt", l = {151}, m = "invokeSuspend")
final class RemoteSettings$clearCachedSettings$1 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ RemoteSettings this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RemoteSettings$clearCachedSettings$1(RemoteSettings remoteSettings, Continuation continuation) {
        super(2, continuation);
        this.this$0 = remoteSettings;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new RemoteSettings$clearCachedSettings$1(this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
        return ((RemoteSettings$clearCachedSettings$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            SettingsCache access$getSettingsCache = this.this$0.getSettingsCache();
            this.label = 1;
            if (access$getSettingsCache.removeConfigs$com_google_firebase_firebase_sessions(this) == coroutine_suspended) {
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
