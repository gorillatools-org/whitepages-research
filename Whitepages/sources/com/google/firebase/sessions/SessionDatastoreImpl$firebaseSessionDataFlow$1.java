package com.google.firebase.sessions;

import android.util.Log;
import androidx.datastore.preferences.core.Preferences;
import androidx.datastore.preferences.core.PreferencesFactory;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.FlowCollector;

@DebugMetadata(c = "com.google.firebase.sessions.SessionDatastoreImpl$firebaseSessionDataFlow$1", f = "SessionDatastore.kt", l = {82}, m = "invokeSuspend")
final class SessionDatastoreImpl$firebaseSessionDataFlow$1 extends SuspendLambda implements Function3 {
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;

    SessionDatastoreImpl$firebaseSessionDataFlow$1(Continuation continuation) {
        super(3, continuation);
    }

    public final Object invoke(FlowCollector flowCollector, Throwable th, Continuation continuation) {
        SessionDatastoreImpl$firebaseSessionDataFlow$1 sessionDatastoreImpl$firebaseSessionDataFlow$1 = new SessionDatastoreImpl$firebaseSessionDataFlow$1(continuation);
        sessionDatastoreImpl$firebaseSessionDataFlow$1.L$0 = flowCollector;
        sessionDatastoreImpl$firebaseSessionDataFlow$1.L$1 = th;
        return sessionDatastoreImpl$firebaseSessionDataFlow$1.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Log.e("FirebaseSessionsRepo", "Error reading stored session data.", (Throwable) this.L$1);
            Preferences createEmpty = PreferencesFactory.createEmpty();
            this.L$0 = null;
            this.label = 1;
            if (((FlowCollector) this.L$0).emit(createEmpty, this) == coroutine_suspended) {
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
