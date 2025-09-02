package com.google.firebase.sessions;

import android.util.Log;
import androidx.datastore.core.DataStore;
import androidx.datastore.preferences.core.MutablePreferences;
import androidx.datastore.preferences.core.PreferencesKt;
import com.google.firebase.sessions.SessionDatastoreImpl;
import java.io.IOException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@DebugMetadata(c = "com.google.firebase.sessions.SessionDatastoreImpl$updateSessionId$1", f = "SessionDatastore.kt", l = {95}, m = "invokeSuspend")
final class SessionDatastoreImpl$updateSessionId$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ String $sessionId;
    int label;
    final /* synthetic */ SessionDatastoreImpl this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SessionDatastoreImpl$updateSessionId$1(SessionDatastoreImpl sessionDatastoreImpl, String str, Continuation continuation) {
        super(2, continuation);
        this.this$0 = sessionDatastoreImpl;
        this.$sessionId = str;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new SessionDatastoreImpl$updateSessionId$1(this.this$0, this.$sessionId, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
        return ((SessionDatastoreImpl$updateSessionId$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            DataStore access$getDataStore = SessionDatastoreImpl.Companion.getDataStore(this.this$0.appContext);
            final String str = this.$sessionId;
            AnonymousClass1 r1 = new AnonymousClass1((Continuation) null);
            this.label = 1;
            if (PreferencesKt.edit(access$getDataStore, r1, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            try {
                ResultKt.throwOnFailure(obj);
            } catch (IOException e) {
                Log.w("FirebaseSessionsRepo", "Failed to update session Id: " + e);
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }

    @DebugMetadata(c = "com.google.firebase.sessions.SessionDatastoreImpl$updateSessionId$1$1", f = "SessionDatastore.kt", l = {}, m = "invokeSuspend")
    /* renamed from: com.google.firebase.sessions.SessionDatastoreImpl$updateSessionId$1$1  reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2 {
        /* synthetic */ Object L$0;
        int label;

        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass1 r0 = new AnonymousClass1(str, continuation);
            r0.L$0 = obj;
            return r0;
        }

        public final Object invoke(MutablePreferences mutablePreferences, Continuation continuation) {
            return ((AnonymousClass1) create(mutablePreferences, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                ((MutablePreferences) this.L$0).set(SessionDatastoreImpl.FirebaseSessionDataKeys.INSTANCE.getSESSION_ID(), str);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
