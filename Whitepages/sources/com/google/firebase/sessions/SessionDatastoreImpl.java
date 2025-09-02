package com.google.firebase.sessions;

import android.content.Context;
import androidx.datastore.core.DataStore;
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler;
import androidx.datastore.preferences.PreferenceDataStoreDelegateKt;
import androidx.datastore.preferences.core.Preferences;
import androidx.datastore.preferences.core.PreferencesKeys;
import com.google.firebase.annotations.concurrent.Background;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference2Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.ReadOnlyProperty;
import kotlin.reflect.KProperty;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;

public final class SessionDatastoreImpl implements SessionDatastore {
    /* access modifiers changed from: private */
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String TAG = "FirebaseSessionsRepo";
    /* access modifiers changed from: private */
    public static final ReadOnlyProperty dataStore$delegate = PreferenceDataStoreDelegateKt.preferencesDataStore$default(SessionDataStoreConfigs.INSTANCE.getSESSIONS_CONFIG_NAME(), new ReplaceFileCorruptionHandler(SessionDatastoreImpl$Companion$dataStore$2.INSTANCE), (Function1) null, (CoroutineScope) null, 12, (Object) null);
    /* access modifiers changed from: private */
    public final Context appContext;
    private final CoroutineContext backgroundDispatcher;
    /* access modifiers changed from: private */
    public final AtomicReference<FirebaseSessionsData> currentSessionFromDatastore = new AtomicReference<>();
    /* access modifiers changed from: private */
    public final Flow firebaseSessionDataFlow;

    public SessionDatastoreImpl(Context context, @Background CoroutineContext coroutineContext) {
        Intrinsics.checkNotNullParameter(context, "appContext");
        Intrinsics.checkNotNullParameter(coroutineContext, "backgroundDispatcher");
        this.appContext = context;
        this.backgroundDispatcher = coroutineContext;
        this.firebaseSessionDataFlow = new SessionDatastoreImpl$special$$inlined$map$1(FlowKt.m924catch(Companion.getDataStore(context).getData(), new SessionDatastoreImpl$firebaseSessionDataFlow$1((Continuation) null)), this);
        Job unused = BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(coroutineContext), (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass1(this, (Continuation) null), 3, (Object) null);
    }

    private static final class FirebaseSessionDataKeys {
        public static final FirebaseSessionDataKeys INSTANCE = new FirebaseSessionDataKeys();
        private static final Preferences.Key SESSION_ID = PreferencesKeys.stringKey("session_id");

        private FirebaseSessionDataKeys() {
        }

        public final Preferences.Key getSESSION_ID() {
            return SESSION_ID;
        }
    }

    @DebugMetadata(c = "com.google.firebase.sessions.SessionDatastoreImpl$1", f = "SessionDatastore.kt", l = {88}, m = "invokeSuspend")
    /* renamed from: com.google.firebase.sessions.SessionDatastoreImpl$1  reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2 {
        int label;
        final /* synthetic */ SessionDatastoreImpl this$0;

        {
            this.this$0 = r1;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass1(this.this$0, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Flow access$getFirebaseSessionDataFlow$p = this.this$0.firebaseSessionDataFlow;
                final SessionDatastoreImpl sessionDatastoreImpl = this.this$0;
                AnonymousClass1 r1 = new FlowCollector() {
                    public final Object emit(FirebaseSessionsData firebaseSessionsData, Continuation continuation) {
                        sessionDatastoreImpl.currentSessionFromDatastore.set(firebaseSessionsData);
                        return Unit.INSTANCE;
                    }
                };
                this.label = 1;
                if (access$getFirebaseSessionDataFlow$p.collect(r1, this) == coroutine_suspended) {
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

    public void updateSessionId(String str) {
        Intrinsics.checkNotNullParameter(str, "sessionId");
        Job unused = BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(this.backgroundDispatcher), (CoroutineContext) null, (CoroutineStart) null, new SessionDatastoreImpl$updateSessionId$1(this, str, (Continuation) null), 3, (Object) null);
    }

    public String getCurrentSessionId() {
        FirebaseSessionsData firebaseSessionsData = this.currentSessionFromDatastore.get();
        if (firebaseSessionsData != null) {
            return firebaseSessionsData.getSessionId();
        }
        return null;
    }

    /* access modifiers changed from: private */
    public final FirebaseSessionsData mapSessionsData(Preferences preferences) {
        return new FirebaseSessionsData((String) preferences.get(FirebaseSessionDataKeys.INSTANCE.getSESSION_ID()));
    }

    private static final class Companion {
        static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property2(new PropertyReference2Impl(Companion.class, "dataStore", "getDataStore(Landroid/content/Context;)Landroidx/datastore/core/DataStore;", 0))};

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* access modifiers changed from: private */
        public final DataStore getDataStore(Context context) {
            return (DataStore) SessionDatastoreImpl.dataStore$delegate.getValue(context, $$delegatedProperties[0]);
        }
    }
}
