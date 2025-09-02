package com.google.firebase.sessions.settings;

import android.util.Log;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

@DebugMetadata(c = "com.google.firebase.sessions.settings.RemoteSettings$updateSettings$2$2", f = "RemoteSettings.kt", l = {}, m = "invokeSuspend")
final class RemoteSettings$updateSettings$2$2 extends SuspendLambda implements Function2 {
    /* synthetic */ Object L$0;
    int label;

    RemoteSettings$updateSettings$2$2(Continuation continuation) {
        super(2, continuation);
    }

    public final Continuation create(Object obj, Continuation continuation) {
        RemoteSettings$updateSettings$2$2 remoteSettings$updateSettings$2$2 = new RemoteSettings$updateSettings$2$2(continuation);
        remoteSettings$updateSettings$2$2.L$0 = obj;
        return remoteSettings$updateSettings$2$2;
    }

    public final Object invoke(String str, Continuation continuation) {
        return ((RemoteSettings$updateSettings$2$2) create(str, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            Log.e(RemoteSettings.TAG, "Error failing to fetch the remote configs: " + ((String) this.L$0));
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
