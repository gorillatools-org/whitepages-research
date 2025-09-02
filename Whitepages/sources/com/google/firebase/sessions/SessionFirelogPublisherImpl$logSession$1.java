package com.google.firebase.sessions;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@DebugMetadata(c = "com.google.firebase.sessions.SessionFirelogPublisherImpl$logSession$1", f = "SessionFirelogPublisher.kt", l = {69, 70, 76}, m = "invokeSuspend")
final class SessionFirelogPublisherImpl$logSession$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ SessionDetails $sessionDetails;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    int label;
    final /* synthetic */ SessionFirelogPublisherImpl this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SessionFirelogPublisherImpl$logSession$1(SessionFirelogPublisherImpl sessionFirelogPublisherImpl, SessionDetails sessionDetails, Continuation continuation) {
        super(2, continuation);
        this.this$0 = sessionFirelogPublisherImpl;
        this.$sessionDetails = sessionDetails;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new SessionFirelogPublisherImpl$logSession$1(this.this$0, this.$sessionDetails, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
        return ((SessionFirelogPublisherImpl$logSession$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0095 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0096  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r11) {
        /*
            r10 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r10.label
            r2 = 3
            r3 = 2
            r4 = 1
            if (r1 == 0) goto L_0x0045
            if (r1 == r4) goto L_0x0041
            if (r1 == r3) goto L_0x003d
            if (r1 != r2) goto L_0x0035
            java.lang.Object r0 = r10.L$5
            com.google.firebase.sessions.settings.SessionsSettings r0 = (com.google.firebase.sessions.settings.SessionsSettings) r0
            java.lang.Object r1 = r10.L$4
            com.google.firebase.sessions.SessionDetails r1 = (com.google.firebase.sessions.SessionDetails) r1
            java.lang.Object r2 = r10.L$3
            com.google.firebase.FirebaseApp r2 = (com.google.firebase.FirebaseApp) r2
            java.lang.Object r3 = r10.L$2
            com.google.firebase.sessions.SessionEvents r3 = (com.google.firebase.sessions.SessionEvents) r3
            java.lang.Object r4 = r10.L$1
            com.google.firebase.sessions.SessionFirelogPublisherImpl r4 = (com.google.firebase.sessions.SessionFirelogPublisherImpl) r4
            java.lang.Object r5 = r10.L$0
            com.google.firebase.sessions.InstallationId r5 = (com.google.firebase.sessions.InstallationId) r5
            kotlin.ResultKt.throwOnFailure(r11)
            r7 = r4
            r8 = r3
            r3 = r0
            r0 = r8
            r9 = r2
            r2 = r1
            r1 = r9
            goto L_0x009d
        L_0x0035:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r0)
            throw r11
        L_0x003d:
            kotlin.ResultKt.throwOnFailure(r11)
            goto L_0x006c
        L_0x0041:
            kotlin.ResultKt.throwOnFailure(r11)
            goto L_0x0053
        L_0x0045:
            kotlin.ResultKt.throwOnFailure(r11)
            com.google.firebase.sessions.SessionFirelogPublisherImpl r11 = r10.this$0
            r10.label = r4
            java.lang.Object r11 = r11.shouldLogSession(r10)
            if (r11 != r0) goto L_0x0053
            return r0
        L_0x0053:
            java.lang.Boolean r11 = (java.lang.Boolean) r11
            boolean r11 = r11.booleanValue()
            if (r11 == 0) goto L_0x00b0
            com.google.firebase.sessions.InstallationId$Companion r11 = com.google.firebase.sessions.InstallationId.Companion
            com.google.firebase.sessions.SessionFirelogPublisherImpl r1 = r10.this$0
            com.google.firebase.installations.FirebaseInstallationsApi r1 = r1.firebaseInstallations
            r10.label = r3
            java.lang.Object r11 = r11.create(r1, r10)
            if (r11 != r0) goto L_0x006c
            return r0
        L_0x006c:
            r5 = r11
            com.google.firebase.sessions.InstallationId r5 = (com.google.firebase.sessions.InstallationId) r5
            com.google.firebase.sessions.SessionFirelogPublisherImpl r4 = r10.this$0
            com.google.firebase.sessions.SessionEvents r3 = com.google.firebase.sessions.SessionEvents.INSTANCE
            com.google.firebase.FirebaseApp r11 = r4.firebaseApp
            com.google.firebase.sessions.SessionDetails r1 = r10.$sessionDetails
            com.google.firebase.sessions.SessionFirelogPublisherImpl r6 = r10.this$0
            com.google.firebase.sessions.settings.SessionsSettings r6 = r6.sessionSettings
            com.google.firebase.sessions.api.FirebaseSessionsDependencies r7 = com.google.firebase.sessions.api.FirebaseSessionsDependencies.INSTANCE
            r10.L$0 = r5
            r10.L$1 = r4
            r10.L$2 = r3
            r10.L$3 = r11
            r10.L$4 = r1
            r10.L$5 = r6
            r10.label = r2
            java.lang.Object r2 = r7.getRegisteredSubscribers$com_google_firebase_firebase_sessions(r10)
            if (r2 != r0) goto L_0x0096
            return r0
        L_0x0096:
            r0 = r3
            r7 = r4
            r3 = r6
            r8 = r1
            r1 = r11
            r11 = r2
            r2 = r8
        L_0x009d:
            r4 = r11
            java.util.Map r4 = (java.util.Map) r4
            java.lang.String r11 = r5.getFid()
            java.lang.String r6 = r5.getAuthToken()
            r5 = r11
            com.google.firebase.sessions.SessionEvent r11 = r0.buildSession(r1, r2, r3, r4, r5, r6)
            r7.attemptLoggingSessionEvent(r11)
        L_0x00b0:
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.sessions.SessionFirelogPublisherImpl$logSession$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
