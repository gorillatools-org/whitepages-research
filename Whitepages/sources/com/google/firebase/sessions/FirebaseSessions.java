package com.google.firebase.sessions;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import com.google.firebase.Firebase;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseKt;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.annotations.concurrent.Background;
import com.google.firebase.sessions.settings.SessionsSettings;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;

public final class FirebaseSessions {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String TAG = "FirebaseSessions";
    /* access modifiers changed from: private */
    public final FirebaseApp firebaseApp;
    /* access modifiers changed from: private */
    public final SessionsSettings settings;

    public FirebaseSessions(FirebaseApp firebaseApp2, SessionsSettings sessionsSettings, @Background final CoroutineContext coroutineContext, final SessionLifecycleServiceBinder sessionLifecycleServiceBinder) {
        Intrinsics.checkNotNullParameter(firebaseApp2, "firebaseApp");
        Intrinsics.checkNotNullParameter(sessionsSettings, "settings");
        Intrinsics.checkNotNullParameter(coroutineContext, "backgroundDispatcher");
        Intrinsics.checkNotNullParameter(sessionLifecycleServiceBinder, "lifecycleServiceBinder");
        this.firebaseApp = firebaseApp2;
        this.settings = sessionsSettings;
        Log.d("FirebaseSessions", "Initializing Firebase Sessions SDK.");
        Context applicationContext = firebaseApp2.getApplicationContext().getApplicationContext();
        if (applicationContext instanceof Application) {
            ((Application) applicationContext).registerActivityLifecycleCallbacks(SessionsActivityLifecycleCallbacks.INSTANCE);
            Job unused = BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(coroutineContext), (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass1(this, (Continuation) null), 3, (Object) null);
            return;
        }
        Log.e("FirebaseSessions", "Failed to register lifecycle callbacks, unexpected context " + applicationContext.getClass() + '.');
    }

    @DebugMetadata(c = "com.google.firebase.sessions.FirebaseSessions$1", f = "FirebaseSessions.kt", l = {51, 55}, m = "invokeSuspend")
    /* renamed from: com.google.firebase.sessions.FirebaseSessions$1  reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2 {
        int label;
        final /* synthetic */ FirebaseSessions this$0;

        {
            this.this$0 = r1;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass1(this.this$0, coroutineContext, sessionLifecycleServiceBinder, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARNING: Removed duplicated region for block: B:25:0x0075  */
        /* JADX WARNING: Removed duplicated region for block: B:26:0x007b  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final java.lang.Object invokeSuspend(java.lang.Object r6) {
            /*
                r5 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r5.label
                java.lang.String r2 = "FirebaseSessions"
                r3 = 2
                r4 = 1
                if (r1 == 0) goto L_0x0020
                if (r1 == r4) goto L_0x001c
                if (r1 != r3) goto L_0x0014
                kotlin.ResultKt.throwOnFailure(r6)
                goto L_0x0069
            L_0x0014:
                java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r6.<init>(r0)
                throw r6
            L_0x001c:
                kotlin.ResultKt.throwOnFailure(r6)
                goto L_0x002e
            L_0x0020:
                kotlin.ResultKt.throwOnFailure(r6)
                com.google.firebase.sessions.api.FirebaseSessionsDependencies r6 = com.google.firebase.sessions.api.FirebaseSessionsDependencies.INSTANCE
                r5.label = r4
                java.lang.Object r6 = r6.getRegisteredSubscribers$com_google_firebase_firebase_sessions(r5)
                if (r6 != r0) goto L_0x002e
                return r0
            L_0x002e:
                java.util.Map r6 = (java.util.Map) r6
                java.util.Collection r6 = r6.values()
                java.lang.Iterable r6 = (java.lang.Iterable) r6
                boolean r1 = r6 instanceof java.util.Collection
                if (r1 == 0) goto L_0x0044
                r1 = r6
                java.util.Collection r1 = (java.util.Collection) r1
                boolean r1 = r1.isEmpty()
                if (r1 == 0) goto L_0x0044
                goto L_0x009b
            L_0x0044:
                java.util.Iterator r6 = r6.iterator()
            L_0x0048:
                boolean r1 = r6.hasNext()
                if (r1 == 0) goto L_0x009b
                java.lang.Object r1 = r6.next()
                com.google.firebase.sessions.api.SessionSubscriber r1 = (com.google.firebase.sessions.api.SessionSubscriber) r1
                boolean r1 = r1.isDataCollectionEnabled()
                if (r1 == 0) goto L_0x0048
                com.google.firebase.sessions.FirebaseSessions r6 = r5.this$0
                com.google.firebase.sessions.settings.SessionsSettings r6 = r6.settings
                r5.label = r3
                java.lang.Object r6 = r6.updateSettings(r5)
                if (r6 != r0) goto L_0x0069
                return r0
            L_0x0069:
                com.google.firebase.sessions.FirebaseSessions r6 = r5.this$0
                com.google.firebase.sessions.settings.SessionsSettings r6 = r6.settings
                boolean r6 = r6.getSessionsEnabled()
                if (r6 != 0) goto L_0x007b
                java.lang.String r6 = "Sessions SDK disabled. Not listening to lifecycle events."
                android.util.Log.d(r2, r6)
                goto L_0x00a0
            L_0x007b:
                com.google.firebase.sessions.SessionLifecycleClient r6 = new com.google.firebase.sessions.SessionLifecycleClient
                kotlin.coroutines.CoroutineContext r0 = r9
                r6.<init>(r0)
                com.google.firebase.sessions.SessionLifecycleServiceBinder r0 = r10
                r6.bindToService(r0)
                com.google.firebase.sessions.SessionsActivityLifecycleCallbacks r0 = com.google.firebase.sessions.SessionsActivityLifecycleCallbacks.INSTANCE
                r0.setLifecycleClient(r6)
                com.google.firebase.sessions.FirebaseSessions r6 = r5.this$0
                com.google.firebase.FirebaseApp r6 = r6.firebaseApp
                com.google.firebase.sessions.FirebaseSessions$1$$ExternalSyntheticLambda0 r0 = new com.google.firebase.sessions.FirebaseSessions$1$$ExternalSyntheticLambda0
                r0.<init>()
                r6.addLifecycleEventListener(r0)
                goto L_0x00a0
            L_0x009b:
                java.lang.String r6 = "No Sessions subscribers. Not listening to lifecycle events."
                android.util.Log.d(r2, r6)
            L_0x00a0:
                kotlin.Unit r6 = kotlin.Unit.INSTANCE
                return r6
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.sessions.FirebaseSessions.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        /* access modifiers changed from: private */
        public static final void invokeSuspend$lambda$1(String str, FirebaseOptions firebaseOptions) {
            Log.w("FirebaseSessions", "FirebaseApp instance deleted. Sessions library will stop collecting data.");
            SessionsActivityLifecycleCallbacks.INSTANCE.setLifecycleClient((SessionLifecycleClient) null);
        }
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final FirebaseSessions getInstance() {
            Object obj = FirebaseKt.getApp(Firebase.INSTANCE).get(FirebaseSessions.class);
            Intrinsics.checkNotNullExpressionValue(obj, "Firebase.app[FirebaseSessions::class.java]");
            return (FirebaseSessions) obj;
        }
    }
}
