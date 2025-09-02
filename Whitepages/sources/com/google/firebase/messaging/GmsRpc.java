package com.google.firebase.messaging;

import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import androidx.privacysandbox.ads.adservices.measurement.MeasurementManagerImplCommon$$ExternalSyntheticLambda3;
import com.google.android.gms.cloudmessaging.CloudMessage;
import com.google.android.gms.cloudmessaging.Rpc;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.heartbeatinfo.HeartBeatInfo;
import com.google.firebase.inject.Provider;
import com.google.firebase.installations.FirebaseInstallationsApi;
import com.google.firebase.platforminfo.UserAgentPublisher;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ExecutionException;

class GmsRpc {
    static final String CMD_RST = "RST";
    static final String CMD_RST_FULL = "RST_FULL";
    static final String CMD_SYNC = "SYNC";
    static final String ERROR_INSTANCE_ID_RESET = "INSTANCE_ID_RESET";
    static final String ERROR_INTERNAL_SERVER_ERROR = "INTERNAL_SERVER_ERROR";
    static final String ERROR_INTERNAL_SERVER_ERROR_ALT = "InternalServerError";
    static final String ERROR_SERVICE_NOT_AVAILABLE = "SERVICE_NOT_AVAILABLE";
    private static final String EXTRA_DELETE = "delete";
    private static final String EXTRA_ERROR = "error";
    private static final String EXTRA_IID_OPERATION = "iid-operation";
    private static final String EXTRA_REGISTRATION_ID = "registration_id";
    private static final String EXTRA_SCOPE = "scope";
    private static final String EXTRA_SENDER = "sender";
    private static final String EXTRA_SUBTYPE = "subtype";
    private static final String EXTRA_TOPIC = "gcm.topic";
    private static final String EXTRA_UNREGISTERED = "unregistered";
    static final String FIREBASE_IID_HEARTBEAT_TAG = "fire-iid";
    private static final String PARAM_APP_VER_CODE = "app_ver";
    private static final String PARAM_APP_VER_NAME = "app_ver_name";
    private static final String PARAM_CLIENT_VER = "cliv";
    private static final String PARAM_FIREBASE_APP_NAME_HASH = "firebase-app-name-hash";
    private static final String PARAM_FIS_AUTH_TOKEN = "Goog-Firebase-Installations-Auth";
    private static final String PARAM_GMP_APP_ID = "gmp_app_id";
    private static final String PARAM_GMS_VER = "gmsv";
    private static final String PARAM_HEARTBEAT_CODE = "Firebase-Client-Log-Type";
    private static final String PARAM_INSTANCE_ID = "appid";
    private static final String PARAM_OS_VER = "osv";
    private static final String PARAM_USER_AGENT = "Firebase-Client";
    private static final String SCOPE_ALL = "*";
    static final String TAG = "FirebaseMessaging";
    static final String TOO_MANY_SUBSCRIBERS = "TOO_MANY_SUBSCRIBERS";
    private static final String TOPIC_PREFIX = "/topics/";

    /* renamed from: app  reason: collision with root package name */
    private final FirebaseApp f12app;
    private final FirebaseInstallationsApi firebaseInstallations;
    private final Provider<HeartBeatInfo> heartbeatInfo;
    private final Metadata metadata;
    private final Rpc rpc;
    private final Provider<UserAgentPublisher> userAgentPublisher;

    GmsRpc(FirebaseApp firebaseApp, Metadata metadata2, Provider<UserAgentPublisher> provider, Provider<HeartBeatInfo> provider2, FirebaseInstallationsApi firebaseInstallationsApi) {
        this(firebaseApp, metadata2, new Rpc(firebaseApp.getApplicationContext()), provider, provider2, firebaseInstallationsApi);
    }

    GmsRpc(FirebaseApp firebaseApp, Metadata metadata2, Rpc rpc2, Provider<UserAgentPublisher> provider, Provider<HeartBeatInfo> provider2, FirebaseInstallationsApi firebaseInstallationsApi) {
        this.f12app = firebaseApp;
        this.metadata = metadata2;
        this.rpc = rpc2;
        this.userAgentPublisher = provider;
        this.heartbeatInfo = provider2;
        this.firebaseInstallations = firebaseInstallationsApi;
    }

    /* access modifiers changed from: package-private */
    public Task<String> getToken() {
        return extractResponseWhenComplete(startRpc(Metadata.getDefaultSenderId(this.f12app), SCOPE_ALL, new Bundle()));
    }

    /* access modifiers changed from: package-private */
    public Task<?> deleteToken() {
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_DELETE, "1");
        return extractResponseWhenComplete(startRpc(Metadata.getDefaultSenderId(this.f12app), SCOPE_ALL, bundle));
    }

    /* access modifiers changed from: package-private */
    public Task<?> subscribeToTopic(String str, String str2) {
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_TOPIC, TOPIC_PREFIX + str2);
        return extractResponseWhenComplete(startRpc(str, TOPIC_PREFIX + str2, bundle));
    }

    /* access modifiers changed from: package-private */
    public Task<?> unsubscribeFromTopic(String str, String str2) {
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_TOPIC, TOPIC_PREFIX + str2);
        bundle.putString(EXTRA_DELETE, "1");
        return extractResponseWhenComplete(startRpc(str, TOPIC_PREFIX + str2, bundle));
    }

    /* access modifiers changed from: package-private */
    public Task<Void> setRetainProxiedNotifications(boolean z) {
        return this.rpc.setRetainProxiedNotifications(z);
    }

    /* access modifiers changed from: package-private */
    public Task<CloudMessage> getProxyNotificationData() {
        return this.rpc.getProxiedNotificationData();
    }

    private Task<Bundle> startRpc(String str, String str2, Bundle bundle) {
        try {
            setDefaultAttributesToBundle(str, str2, bundle);
            return this.rpc.send(bundle);
        } catch (InterruptedException | ExecutionException e) {
            return Tasks.forException(e);
        }
    }

    private static String base64UrlSafe(byte[] bArr) {
        return Base64.encodeToString(bArr, 11);
    }

    private String getHashedFirebaseAppName() {
        try {
            return base64UrlSafe(MessageDigest.getInstance("SHA-1").digest(this.f12app.getName().getBytes()));
        } catch (NoSuchAlgorithmException unused) {
            return "[HASH-ERROR]";
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x00c0 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:18:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void setDefaultAttributesToBundle(java.lang.String r3, java.lang.String r4, android.os.Bundle r5) throws java.util.concurrent.ExecutionException, java.lang.InterruptedException {
        /*
            r2 = this;
            java.lang.String r0 = "FirebaseMessaging"
            java.lang.String r1 = "scope"
            r5.putString(r1, r4)
            java.lang.String r4 = "sender"
            r5.putString(r4, r3)
            java.lang.String r4 = "subtype"
            r5.putString(r4, r3)
            com.google.firebase.FirebaseApp r3 = r2.f12app
            com.google.firebase.FirebaseOptions r3 = r3.getOptions()
            java.lang.String r3 = r3.getApplicationId()
            java.lang.String r4 = "gmp_app_id"
            r5.putString(r4, r3)
            com.google.firebase.messaging.Metadata r3 = r2.metadata
            int r3 = r3.getGmsVersionCode()
            java.lang.String r3 = java.lang.Integer.toString(r3)
            java.lang.String r4 = "gmsv"
            r5.putString(r4, r3)
            int r3 = android.os.Build.VERSION.SDK_INT
            java.lang.String r3 = java.lang.Integer.toString(r3)
            java.lang.String r4 = "osv"
            r5.putString(r4, r3)
            com.google.firebase.messaging.Metadata r3 = r2.metadata
            java.lang.String r3 = r3.getAppVersionCode()
            java.lang.String r4 = "app_ver"
            r5.putString(r4, r3)
            com.google.firebase.messaging.Metadata r3 = r2.metadata
            java.lang.String r3 = r3.getAppVersionName()
            java.lang.String r4 = "app_ver_name"
            r5.putString(r4, r3)
            java.lang.String r3 = "firebase-app-name-hash"
            java.lang.String r4 = r2.getHashedFirebaseAppName()
            r5.putString(r3, r4)
            com.google.firebase.installations.FirebaseInstallationsApi r3 = r2.firebaseInstallations     // Catch:{ ExecutionException -> 0x0078, InterruptedException -> 0x0076 }
            r4 = 0
            com.google.android.gms.tasks.Task r3 = r3.getToken(r4)     // Catch:{ ExecutionException -> 0x0078, InterruptedException -> 0x0076 }
            java.lang.Object r3 = com.google.android.gms.tasks.Tasks.await(r3)     // Catch:{ ExecutionException -> 0x0078, InterruptedException -> 0x0076 }
            com.google.firebase.installations.InstallationTokenResult r3 = (com.google.firebase.installations.InstallationTokenResult) r3     // Catch:{ ExecutionException -> 0x0078, InterruptedException -> 0x0076 }
            java.lang.String r3 = r3.getToken()     // Catch:{ ExecutionException -> 0x0078, InterruptedException -> 0x0076 }
            boolean r4 = android.text.TextUtils.isEmpty(r3)     // Catch:{ ExecutionException -> 0x0078, InterruptedException -> 0x0076 }
            if (r4 != 0) goto L_0x007a
            java.lang.String r4 = "Goog-Firebase-Installations-Auth"
            r5.putString(r4, r3)     // Catch:{ ExecutionException -> 0x0078, InterruptedException -> 0x0076 }
            goto L_0x0085
        L_0x0076:
            r3 = move-exception
            goto L_0x0080
        L_0x0078:
            r3 = move-exception
            goto L_0x0080
        L_0x007a:
            java.lang.String r3 = "FIS auth token is empty"
            android.util.Log.w(r0, r3)     // Catch:{ ExecutionException -> 0x0078, InterruptedException -> 0x0076 }
            goto L_0x0085
        L_0x0080:
            java.lang.String r4 = "Failed to get FIS auth token"
            android.util.Log.e(r0, r4, r3)
        L_0x0085:
            com.google.firebase.installations.FirebaseInstallationsApi r3 = r2.firebaseInstallations
            com.google.android.gms.tasks.Task r3 = r3.getId()
            java.lang.Object r3 = com.google.android.gms.tasks.Tasks.await(r3)
            java.lang.String r3 = (java.lang.String) r3
            java.lang.String r4 = "appid"
            r5.putString(r4, r3)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "fcm-"
            r3.append(r4)
            java.lang.String r4 = "24.1.1"
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            java.lang.String r4 = "cliv"
            r5.putString(r4, r3)
            com.google.firebase.inject.Provider<com.google.firebase.heartbeatinfo.HeartBeatInfo> r3 = r2.heartbeatInfo
            java.lang.Object r3 = r3.get()
            com.google.firebase.heartbeatinfo.HeartBeatInfo r3 = (com.google.firebase.heartbeatinfo.HeartBeatInfo) r3
            com.google.firebase.inject.Provider<com.google.firebase.platforminfo.UserAgentPublisher> r4 = r2.userAgentPublisher
            java.lang.Object r4 = r4.get()
            com.google.firebase.platforminfo.UserAgentPublisher r4 = (com.google.firebase.platforminfo.UserAgentPublisher) r4
            if (r3 == 0) goto L_0x00e2
            if (r4 == 0) goto L_0x00e2
            java.lang.String r0 = "fire-iid"
            com.google.firebase.heartbeatinfo.HeartBeatInfo$HeartBeat r3 = r3.getHeartBeatCode(r0)
            com.google.firebase.heartbeatinfo.HeartBeatInfo$HeartBeat r0 = com.google.firebase.heartbeatinfo.HeartBeatInfo.HeartBeat.NONE
            if (r3 == r0) goto L_0x00e2
            int r3 = r3.getCode()
            java.lang.String r3 = java.lang.Integer.toString(r3)
            java.lang.String r0 = "Firebase-Client-Log-Type"
            r5.putString(r0, r3)
            java.lang.String r3 = "Firebase-Client"
            java.lang.String r4 = r4.getUserAgent()
            r5.putString(r3, r4)
        L_0x00e2:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.messaging.GmsRpc.setDefaultAttributesToBundle(java.lang.String, java.lang.String, android.os.Bundle):void");
    }

    private String handleResponse(Bundle bundle) throws IOException {
        if (bundle != null) {
            String string = bundle.getString(EXTRA_REGISTRATION_ID);
            if (string != null) {
                return string;
            }
            String string2 = bundle.getString(EXTRA_UNREGISTERED);
            if (string2 != null) {
                return string2;
            }
            String string3 = bundle.getString("error");
            if (CMD_RST.equals(string3)) {
                throw new IOException(ERROR_INSTANCE_ID_RESET);
            } else if (string3 != null) {
                throw new IOException(string3);
            } else {
                Log.w("FirebaseMessaging", "Unexpected response: " + bundle, new Throwable());
                throw new IOException("SERVICE_NOT_AVAILABLE");
            }
        } else {
            throw new IOException("SERVICE_NOT_AVAILABLE");
        }
    }

    private Task<String> extractResponseWhenComplete(Task<Bundle> task) {
        return task.continueWith(new MeasurementManagerImplCommon$$ExternalSyntheticLambda3(), new GmsRpc$$ExternalSyntheticLambda0(this));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ String lambda$extractResponseWhenComplete$0(Task task) throws Exception {
        return handleResponse((Bundle) task.getResult(IOException.class));
    }

    static boolean isErrorMessageForRetryableError(String str) {
        return "SERVICE_NOT_AVAILABLE".equals(str) || ERROR_INTERNAL_SERVER_ERROR.equals(str) || ERROR_INTERNAL_SERVER_ERROR_ALT.equals(str);
    }
}
