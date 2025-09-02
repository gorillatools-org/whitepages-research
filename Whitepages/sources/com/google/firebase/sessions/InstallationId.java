package com.google.firebase.sessions;

import kotlin.jvm.internal.DefaultConstructorMarker;

public final class InstallationId {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String TAG = "InstallationId";
    private final String authToken;
    private final String fid;

    public /* synthetic */ InstallationId(String str, String str2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2);
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX WARNING: Removed duplicated region for block: B:20:0x0048  */
        /* JADX WARNING: Removed duplicated region for block: B:31:0x0089 A[Catch:{ Exception -> 0x0034 }, RETURN] */
        /* JADX WARNING: Removed duplicated region for block: B:8:0x0028  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final java.lang.Object create(com.google.firebase.installations.FirebaseInstallationsApi r9, kotlin.coroutines.Continuation r10) {
            /*
                r8 = this;
                boolean r0 = r10 instanceof com.google.firebase.sessions.InstallationId$Companion$create$1
                if (r0 == 0) goto L_0x0013
                r0 = r10
                com.google.firebase.sessions.InstallationId$Companion$create$1 r0 = (com.google.firebase.sessions.InstallationId$Companion$create$1) r0
                int r1 = r0.label
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r3 = r1 & r2
                if (r3 == 0) goto L_0x0013
                int r1 = r1 - r2
                r0.label = r1
                goto L_0x0018
            L_0x0013:
                com.google.firebase.sessions.InstallationId$Companion$create$1 r0 = new com.google.firebase.sessions.InstallationId$Companion$create$1
                r0.<init>(r8, r10)
            L_0x0018:
                java.lang.Object r10 = r0.result
                java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r2 = r0.label
                java.lang.String r3 = ""
                java.lang.String r4 = "InstallationId"
                r5 = 2
                r6 = 1
                if (r2 == 0) goto L_0x0048
                if (r2 == r6) goto L_0x003e
                if (r2 != r5) goto L_0x0036
                java.lang.Object r9 = r0.L$0
                java.lang.String r9 = (java.lang.String) r9
                kotlin.ResultKt.throwOnFailure(r10)     // Catch:{ Exception -> 0x0034 }
                goto L_0x008a
            L_0x0034:
                r10 = move-exception
                goto L_0x0093
            L_0x0036:
                java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
                java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
                r9.<init>(r10)
                throw r9
            L_0x003e:
                java.lang.Object r9 = r0.L$0
                com.google.firebase.installations.FirebaseInstallationsApi r9 = (com.google.firebase.installations.FirebaseInstallationsApi) r9
                kotlin.ResultKt.throwOnFailure(r10)     // Catch:{ Exception -> 0x0046 }
                goto L_0x0060
            L_0x0046:
                r10 = move-exception
                goto L_0x006f
            L_0x0048:
                kotlin.ResultKt.throwOnFailure(r10)
                r10 = 0
                com.google.android.gms.tasks.Task r10 = r9.getToken(r10)     // Catch:{ Exception -> 0x0046 }
                java.lang.String r2 = "firebaseInstallations.getToken(false)"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r10, r2)     // Catch:{ Exception -> 0x0046 }
                r0.L$0 = r9     // Catch:{ Exception -> 0x0046 }
                r0.label = r6     // Catch:{ Exception -> 0x0046 }
                java.lang.Object r10 = kotlinx.coroutines.tasks.TasksKt.await(r10, r0)     // Catch:{ Exception -> 0x0046 }
                if (r10 != r1) goto L_0x0060
                return r1
            L_0x0060:
                com.google.firebase.installations.InstallationTokenResult r10 = (com.google.firebase.installations.InstallationTokenResult) r10     // Catch:{ Exception -> 0x0046 }
                java.lang.String r10 = r10.getToken()     // Catch:{ Exception -> 0x0046 }
                java.lang.String r2 = "{\n          firebaseInst…).await().token\n        }"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r10, r2)     // Catch:{ Exception -> 0x0046 }
                r7 = r10
                r10 = r9
                r9 = r7
                goto L_0x0076
            L_0x006f:
                java.lang.String r2 = "Error getting authentication token."
                android.util.Log.w(r4, r2, r10)
                r10 = r9
                r9 = r3
            L_0x0076:
                com.google.android.gms.tasks.Task r10 = r10.getId()     // Catch:{ Exception -> 0x0034 }
                java.lang.String r2 = "firebaseInstallations.id"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r10, r2)     // Catch:{ Exception -> 0x0034 }
                r0.L$0 = r9     // Catch:{ Exception -> 0x0034 }
                r0.label = r5     // Catch:{ Exception -> 0x0034 }
                java.lang.Object r10 = kotlinx.coroutines.tasks.TasksKt.await(r10, r0)     // Catch:{ Exception -> 0x0034 }
                if (r10 != r1) goto L_0x008a
                return r1
            L_0x008a:
                java.lang.String r0 = "{\n          firebaseInst…ions.id.await()\n        }"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r10, r0)     // Catch:{ Exception -> 0x0034 }
                java.lang.String r10 = (java.lang.String) r10     // Catch:{ Exception -> 0x0034 }
                r3 = r10
                goto L_0x0098
            L_0x0093:
                java.lang.String r0 = "Error getting Firebase installation id ."
                android.util.Log.w(r4, r0, r10)
            L_0x0098:
                com.google.firebase.sessions.InstallationId r10 = new com.google.firebase.sessions.InstallationId
                r0 = 0
                r10.<init>(r3, r9, r0)
                return r10
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.sessions.InstallationId.Companion.create(com.google.firebase.installations.FirebaseInstallationsApi, kotlin.coroutines.Continuation):java.lang.Object");
        }
    }

    private InstallationId(String str, String str2) {
        this.fid = str;
        this.authToken = str2;
    }

    public final String getAuthToken() {
        return this.authToken;
    }

    public final String getFid() {
        return this.fid;
    }
}
