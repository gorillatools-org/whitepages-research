package com.google.firebase.sessions;

import android.util.Log;
import com.google.firebase.FirebaseApp;
import com.google.firebase.annotations.concurrent.Background;
import com.google.firebase.installations.FirebaseInstallationsApi;
import com.google.firebase.sessions.settings.SessionsSettings;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;

public final class SessionFirelogPublisherImpl implements SessionFirelogPublisher {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String TAG = "SessionFirelogPublisher";
    private static final double randomValueForSampling = Math.random();
    private final CoroutineContext backgroundDispatcher;
    private final EventGDTLoggerInterface eventGDTLogger;
    /* access modifiers changed from: private */
    public final FirebaseApp firebaseApp;
    /* access modifiers changed from: private */
    public final FirebaseInstallationsApi firebaseInstallations;
    /* access modifiers changed from: private */
    public final SessionsSettings sessionSettings;

    public SessionFirelogPublisherImpl(FirebaseApp firebaseApp2, FirebaseInstallationsApi firebaseInstallationsApi, SessionsSettings sessionsSettings, EventGDTLoggerInterface eventGDTLoggerInterface, @Background CoroutineContext coroutineContext) {
        Intrinsics.checkNotNullParameter(firebaseApp2, "firebaseApp");
        Intrinsics.checkNotNullParameter(firebaseInstallationsApi, "firebaseInstallations");
        Intrinsics.checkNotNullParameter(sessionsSettings, "sessionSettings");
        Intrinsics.checkNotNullParameter(eventGDTLoggerInterface, "eventGDTLogger");
        Intrinsics.checkNotNullParameter(coroutineContext, "backgroundDispatcher");
        this.firebaseApp = firebaseApp2;
        this.firebaseInstallations = firebaseInstallationsApi;
        this.sessionSettings = sessionsSettings;
        this.eventGDTLogger = eventGDTLoggerInterface;
        this.backgroundDispatcher = coroutineContext;
    }

    public void logSession(SessionDetails sessionDetails) {
        Intrinsics.checkNotNullParameter(sessionDetails, "sessionDetails");
        Job unused = BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(this.backgroundDispatcher), (CoroutineContext) null, (CoroutineStart) null, new SessionFirelogPublisherImpl$logSession$1(this, sessionDetails, (Continuation) null), 3, (Object) null);
    }

    /* access modifiers changed from: private */
    public final void attemptLoggingSessionEvent(SessionEvent sessionEvent) {
        try {
            this.eventGDTLogger.log(sessionEvent);
            Log.d(TAG, "Successfully logged Session Start event.");
        } catch (RuntimeException e) {
            Log.e(TAG, "Error logging Session Start event to DataTransport: ", e);
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0037  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0056  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0060  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object shouldLogSession(kotlin.coroutines.Continuation r6) {
        /*
            r5 = this;
            boolean r0 = r6 instanceof com.google.firebase.sessions.SessionFirelogPublisherImpl$shouldLogSession$1
            if (r0 == 0) goto L_0x0013
            r0 = r6
            com.google.firebase.sessions.SessionFirelogPublisherImpl$shouldLogSession$1 r0 = (com.google.firebase.sessions.SessionFirelogPublisherImpl$shouldLogSession$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.google.firebase.sessions.SessionFirelogPublisherImpl$shouldLogSession$1 r0 = new com.google.firebase.sessions.SessionFirelogPublisherImpl$shouldLogSession$1
            r0.<init>(r5, r6)
        L_0x0018:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            java.lang.String r3 = "SessionFirelogPublisher"
            r4 = 1
            if (r2 == 0) goto L_0x0037
            if (r2 != r4) goto L_0x002f
            java.lang.Object r0 = r0.L$0
            com.google.firebase.sessions.SessionFirelogPublisherImpl r0 = (com.google.firebase.sessions.SessionFirelogPublisherImpl) r0
            kotlin.ResultKt.throwOnFailure(r6)
            goto L_0x004d
        L_0x002f:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)
            throw r6
        L_0x0037:
            kotlin.ResultKt.throwOnFailure(r6)
            java.lang.String r6 = "Data Collection is enabled for at least one Subscriber"
            android.util.Log.d(r3, r6)
            com.google.firebase.sessions.settings.SessionsSettings r6 = r5.sessionSettings
            r0.L$0 = r5
            r0.label = r4
            java.lang.Object r6 = r6.updateSettings(r0)
            if (r6 != r1) goto L_0x004c
            return r1
        L_0x004c:
            r0 = r5
        L_0x004d:
            com.google.firebase.sessions.settings.SessionsSettings r6 = r0.sessionSettings
            boolean r6 = r6.getSessionsEnabled()
            r1 = 0
            if (r6 != 0) goto L_0x0060
            java.lang.String r6 = "Sessions SDK disabled. Events will not be sent."
            android.util.Log.d(r3, r6)
            java.lang.Boolean r6 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r1)
            return r6
        L_0x0060:
            boolean r6 = r0.shouldCollectEvents()
            if (r6 != 0) goto L_0x0070
            java.lang.String r6 = "Sessions SDK has dropped this session due to sampling."
            android.util.Log.d(r3, r6)
            java.lang.Boolean r6 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r1)
            return r6
        L_0x0070:
            java.lang.Boolean r6 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r4)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.sessions.SessionFirelogPublisherImpl.shouldLogSession(kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final boolean shouldCollectEvents() {
        return randomValueForSampling <= this.sessionSettings.getSamplingRate();
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
