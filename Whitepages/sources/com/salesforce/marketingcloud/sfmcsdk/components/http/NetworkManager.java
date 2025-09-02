package com.salesforce.marketingcloud.sfmcsdk.components.http;

import android.content.Context;
import android.content.SharedPreferences;
import com.salesforce.marketingcloud.sfmcsdk.components.logging.SFMCSdkLogger;
import com.salesforce.marketingcloud.sfmcsdk.components.utils.SdkExecutors;
import com.salesforce.marketingcloud.sfmcsdk.components.utils.SdkExecutorsKt;
import com.salesforce.marketingcloud.sfmcsdk.util.NetworkUtils;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class NetworkManager {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final long MAX_SERVER_RETRY = 86400000;
    public static final String TAG = "~$NetworkManager";
    private final Authenticator authenticator;
    private final Context context;
    private final SdkExecutors executors;
    private final SharedPreferences networkPreferences;
    private final Map<String, AtomicBoolean> requestsInFlight;

    public static /* synthetic */ void getRequestsInFlight$sfmcsdk_release$annotations() {
    }

    public NetworkManager(Context context2, SdkExecutors sdkExecutors, SharedPreferences sharedPreferences, Authenticator authenticator2) {
        Intrinsics.checkNotNullParameter(context2, "context");
        Intrinsics.checkNotNullParameter(sdkExecutors, "executors");
        Intrinsics.checkNotNullParameter(sharedPreferences, "networkPreferences");
        this.context = context2;
        this.executors = sdkExecutors;
        this.networkPreferences = sharedPreferences;
        this.authenticator = authenticator2;
        this.requestsInFlight = new LinkedHashMap();
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ NetworkManager(Context context2, SdkExecutors sdkExecutors, SharedPreferences sharedPreferences, Authenticator authenticator2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context2, sdkExecutors, sharedPreferences, (i & 8) != 0 ? null : authenticator2);
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String getServerRetryKey$sfmcsdk_release(String str) {
            Intrinsics.checkNotNullParameter(str, "requestName");
            return "retry_server_" + str;
        }

        public final String getDeviceRetryKey$sfmcsdk_release(String str) {
            Intrinsics.checkNotNullParameter(str, "requestName");
            return "retry_device_" + str;
        }
    }

    public final Map<String, AtomicBoolean> getRequestsInFlight$sfmcsdk_release() {
        return this.requestsInFlight;
    }

    public final void executeAsync(Request request, Callback callback) {
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(callback, "callback");
        SdkExecutorsKt.namedRunnable(this.executors.getNetworkIO(), "network_manager_execute", new NetworkManager$executeAsync$1(callback, request, this));
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0074 A[Catch:{ Exception -> 0x004d }] */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x007f A[Catch:{ Exception -> 0x004d }] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x009f A[Catch:{ Exception -> 0x004d }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.salesforce.marketingcloud.sfmcsdk.components.http.Response executeSync(com.salesforce.marketingcloud.sfmcsdk.components.http.Request r12) {
        /*
            r11 = this;
            java.lang.String r0 = "~$NetworkManager"
            java.lang.String r1 = "request"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r12, r1)
            kotlin.jvm.internal.Ref$ObjectRef r1 = new kotlin.jvm.internal.Ref$ObjectRef
            r1.<init>()
            r1.element = r12
            r2 = 0
            java.util.Map<java.lang.String, java.util.concurrent.atomic.AtomicBoolean> r3 = r11.requestsInFlight     // Catch:{ Exception -> 0x004d }
            java.lang.String r12 = r12.getName()     // Catch:{ Exception -> 0x004d }
            java.lang.Object r12 = r3.get(r12)     // Catch:{ Exception -> 0x004d }
            java.util.concurrent.atomic.AtomicBoolean r12 = (java.util.concurrent.atomic.AtomicBoolean) r12     // Catch:{ Exception -> 0x004d }
            r3 = 429(0x1ad, float:6.01E-43)
            r4 = 1
            if (r12 == 0) goto L_0x0050
            boolean r5 = r12.get()     // Catch:{ Exception -> 0x004d }
            if (r5 != 0) goto L_0x0027
            goto L_0x0050
        L_0x0027:
            boolean r12 = r12.get()     // Catch:{ Exception -> 0x004d }
            if (r12 == 0) goto L_0x0062
            com.salesforce.marketingcloud.sfmcsdk.components.http.Response$Companion r12 = com.salesforce.marketingcloud.sfmcsdk.components.http.Response.Companion     // Catch:{ Exception -> 0x004d }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x004d }
            r4.<init>()     // Catch:{ Exception -> 0x004d }
            java.lang.Object r5 = r1.element     // Catch:{ Exception -> 0x004d }
            com.salesforce.marketingcloud.sfmcsdk.components.http.Request r5 = (com.salesforce.marketingcloud.sfmcsdk.components.http.Request) r5     // Catch:{ Exception -> 0x004d }
            java.lang.String r5 = r5.getName()     // Catch:{ Exception -> 0x004d }
            r4.append(r5)     // Catch:{ Exception -> 0x004d }
            java.lang.String r5 = " request already in-flight"
            r4.append(r5)     // Catch:{ Exception -> 0x004d }
            java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x004d }
            com.salesforce.marketingcloud.sfmcsdk.components.http.Response r12 = r12.error$sfmcsdk_release(r4, r3)     // Catch:{ Exception -> 0x004d }
            return r12
        L_0x004d:
            r12 = move-exception
            goto L_0x01d0
        L_0x0050:
            java.util.Map<java.lang.String, java.util.concurrent.atomic.AtomicBoolean> r12 = r11.requestsInFlight     // Catch:{ Exception -> 0x004d }
            java.lang.Object r5 = r1.element     // Catch:{ Exception -> 0x004d }
            com.salesforce.marketingcloud.sfmcsdk.components.http.Request r5 = (com.salesforce.marketingcloud.sfmcsdk.components.http.Request) r5     // Catch:{ Exception -> 0x004d }
            java.lang.String r5 = r5.getName()     // Catch:{ Exception -> 0x004d }
            java.util.concurrent.atomic.AtomicBoolean r6 = new java.util.concurrent.atomic.AtomicBoolean     // Catch:{ Exception -> 0x004d }
            r6.<init>(r4)     // Catch:{ Exception -> 0x004d }
            r12.put(r5, r6)     // Catch:{ Exception -> 0x004d }
        L_0x0062:
            java.util.Map<java.lang.String, java.util.concurrent.atomic.AtomicBoolean> r12 = r11.requestsInFlight     // Catch:{ Exception -> 0x004d }
            java.lang.Object r5 = r1.element     // Catch:{ Exception -> 0x004d }
            com.salesforce.marketingcloud.sfmcsdk.components.http.Request r5 = (com.salesforce.marketingcloud.sfmcsdk.components.http.Request) r5     // Catch:{ Exception -> 0x004d }
            java.lang.String r5 = r5.getName()     // Catch:{ Exception -> 0x004d }
            java.lang.Object r12 = r12.get(r5)     // Catch:{ Exception -> 0x004d }
            java.util.concurrent.atomic.AtomicBoolean r12 = (java.util.concurrent.atomic.AtomicBoolean) r12     // Catch:{ Exception -> 0x004d }
            if (r12 == 0) goto L_0x0077
            r12.set(r4)     // Catch:{ Exception -> 0x004d }
        L_0x0077:
            android.content.Context r12 = r11.context     // Catch:{ Exception -> 0x004d }
            boolean r12 = com.salesforce.marketingcloud.sfmcsdk.util.NetworkUtils.hasConnectivity(r12)     // Catch:{ Exception -> 0x004d }
            if (r12 != 0) goto L_0x009f
            java.util.Map<java.lang.String, java.util.concurrent.atomic.AtomicBoolean> r12 = r11.requestsInFlight     // Catch:{ Exception -> 0x004d }
            java.lang.Object r3 = r1.element     // Catch:{ Exception -> 0x004d }
            com.salesforce.marketingcloud.sfmcsdk.components.http.Request r3 = (com.salesforce.marketingcloud.sfmcsdk.components.http.Request) r3     // Catch:{ Exception -> 0x004d }
            java.lang.String r3 = r3.getName()     // Catch:{ Exception -> 0x004d }
            java.lang.Object r12 = r12.get(r3)     // Catch:{ Exception -> 0x004d }
            java.util.concurrent.atomic.AtomicBoolean r12 = (java.util.concurrent.atomic.AtomicBoolean) r12     // Catch:{ Exception -> 0x004d }
            if (r12 == 0) goto L_0x0094
            r12.set(r2)     // Catch:{ Exception -> 0x004d }
        L_0x0094:
            com.salesforce.marketingcloud.sfmcsdk.components.http.Response$Companion r12 = com.salesforce.marketingcloud.sfmcsdk.components.http.Response.Companion     // Catch:{ Exception -> 0x004d }
            java.lang.String r3 = "Device has no network connectivity"
            r4 = 599(0x257, float:8.4E-43)
            com.salesforce.marketingcloud.sfmcsdk.components.http.Response r12 = r12.error$sfmcsdk_release(r3, r4)     // Catch:{ Exception -> 0x004d }
            return r12
        L_0x009f:
            java.lang.Object r12 = r1.element     // Catch:{ Exception -> 0x004d }
            com.salesforce.marketingcloud.sfmcsdk.components.http.Request r12 = (com.salesforce.marketingcloud.sfmcsdk.components.http.Request) r12     // Catch:{ Exception -> 0x004d }
            java.lang.String r12 = r12.getName()     // Catch:{ Exception -> 0x004d }
            boolean r12 = r11.isBlockedByRetryAfter$sfmcsdk_release(r12)     // Catch:{ Exception -> 0x004d }
            if (r12 == 0) goto L_0x00e7
            java.util.Map<java.lang.String, java.util.concurrent.atomic.AtomicBoolean> r12 = r11.requestsInFlight     // Catch:{ Exception -> 0x004d }
            java.lang.Object r4 = r1.element     // Catch:{ Exception -> 0x004d }
            com.salesforce.marketingcloud.sfmcsdk.components.http.Request r4 = (com.salesforce.marketingcloud.sfmcsdk.components.http.Request) r4     // Catch:{ Exception -> 0x004d }
            java.lang.String r4 = r4.getName()     // Catch:{ Exception -> 0x004d }
            java.lang.Object r12 = r12.get(r4)     // Catch:{ Exception -> 0x004d }
            java.util.concurrent.atomic.AtomicBoolean r12 = (java.util.concurrent.atomic.AtomicBoolean) r12     // Catch:{ Exception -> 0x004d }
            if (r12 == 0) goto L_0x00c2
            r12.set(r2)     // Catch:{ Exception -> 0x004d }
        L_0x00c2:
            com.salesforce.marketingcloud.sfmcsdk.components.http.Response$Companion r12 = com.salesforce.marketingcloud.sfmcsdk.components.http.Response.Companion     // Catch:{ Exception -> 0x004d }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x004d }
            r4.<init>()     // Catch:{ Exception -> 0x004d }
            java.lang.String r5 = "Too many requests. "
            r4.append(r5)     // Catch:{ Exception -> 0x004d }
            java.lang.Object r5 = r1.element     // Catch:{ Exception -> 0x004d }
            com.salesforce.marketingcloud.sfmcsdk.components.http.Request r5 = (com.salesforce.marketingcloud.sfmcsdk.components.http.Request) r5     // Catch:{ Exception -> 0x004d }
            java.lang.String r5 = r5.getName()     // Catch:{ Exception -> 0x004d }
            r4.append(r5)     // Catch:{ Exception -> 0x004d }
            java.lang.String r5 = " request aborted."
            r4.append(r5)     // Catch:{ Exception -> 0x004d }
            java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x004d }
            com.salesforce.marketingcloud.sfmcsdk.components.http.Response r12 = r12.error$sfmcsdk_release(r4, r3)     // Catch:{ Exception -> 0x004d }
            return r12
        L_0x00e7:
            android.content.Context r12 = r11.context     // Catch:{ Exception -> 0x004d }
            com.salesforce.marketingcloud.sfmcsdk.util.NetworkUtils.installProvidersIfNeeded(r12)     // Catch:{ Exception -> 0x004d }
            java.lang.Object r12 = r1.element     // Catch:{ Exception -> 0x004d }
            r6 = r12
            com.salesforce.marketingcloud.sfmcsdk.components.http.Request r6 = (com.salesforce.marketingcloud.sfmcsdk.components.http.Request) r6     // Catch:{ Exception -> 0x004d }
            r9 = 2
            r10 = 0
            r7 = 0
            r5 = r11
            recordDeviceRetryAfter$sfmcsdk_release$default(r5, r6, r7, r9, r10)     // Catch:{ Exception -> 0x004d }
            com.salesforce.marketingcloud.sfmcsdk.components.http.Authenticator r12 = r11.authenticator     // Catch:{ Exception -> 0x004d }
            if (r12 == 0) goto L_0x014e
            r3 = 0
            kotlin.Pair r12 = com.salesforce.marketingcloud.sfmcsdk.components.http.Authenticator.getAuthTokenHeader$sfmcsdk_release$default(r12, r2, r4, r3)     // Catch:{ Exception -> 0x004d }
            if (r12 != 0) goto L_0x0130
            com.salesforce.marketingcloud.sfmcsdk.components.http.Response$Companion r12 = com.salesforce.marketingcloud.sfmcsdk.components.http.Response.Companion     // Catch:{ Exception -> 0x004d }
            java.lang.String r3 = "Expectation Failed"
            r4 = 417(0x1a1, float:5.84E-43)
            com.salesforce.marketingcloud.sfmcsdk.components.http.Response r12 = r12.error$sfmcsdk_release(r3, r4)     // Catch:{ Exception -> 0x004d }
            com.salesforce.marketingcloud.sfmcsdk.components.logging.SFMCSdkLogger r3 = com.salesforce.marketingcloud.sfmcsdk.components.logging.SFMCSdkLogger.INSTANCE     // Catch:{ Exception -> 0x004d }
            com.salesforce.marketingcloud.sfmcsdk.components.http.NetworkManager$executeSync$authHeader$1$1 r4 = com.salesforce.marketingcloud.sfmcsdk.components.http.NetworkManager$executeSync$authHeader$1$1.INSTANCE     // Catch:{ Exception -> 0x004d }
            r3.w(r0, r4)     // Catch:{ Exception -> 0x004d }
            com.salesforce.marketingcloud.sfmcsdk.components.http.Authenticator r3 = r11.authenticator     // Catch:{ Exception -> 0x004d }
            r3.deleteCachedToken()     // Catch:{ Exception -> 0x004d }
            java.util.Map<java.lang.String, java.util.concurrent.atomic.AtomicBoolean> r3 = r11.requestsInFlight     // Catch:{ Exception -> 0x004d }
            java.lang.Object r4 = r1.element     // Catch:{ Exception -> 0x004d }
            com.salesforce.marketingcloud.sfmcsdk.components.http.Request r4 = (com.salesforce.marketingcloud.sfmcsdk.components.http.Request) r4     // Catch:{ Exception -> 0x004d }
            java.lang.String r4 = r4.getName()     // Catch:{ Exception -> 0x004d }
            java.lang.Object r3 = r3.get(r4)     // Catch:{ Exception -> 0x004d }
            java.util.concurrent.atomic.AtomicBoolean r3 = (java.util.concurrent.atomic.AtomicBoolean) r3     // Catch:{ Exception -> 0x004d }
            if (r3 == 0) goto L_0x012f
            r3.set(r2)     // Catch:{ Exception -> 0x004d }
        L_0x012f:
            return r12
        L_0x0130:
            java.lang.Object r3 = r1.element     // Catch:{ Exception -> 0x004d }
            com.salesforce.marketingcloud.sfmcsdk.components.http.Request r3 = (com.salesforce.marketingcloud.sfmcsdk.components.http.Request) r3     // Catch:{ Exception -> 0x004d }
            com.salesforce.marketingcloud.sfmcsdk.components.http.Request$Builder r3 = r3.toBuilder$sfmcsdk_release()     // Catch:{ Exception -> 0x004d }
            java.lang.Object r4 = r12.getFirst()     // Catch:{ Exception -> 0x004d }
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ Exception -> 0x004d }
            java.lang.Object r12 = r12.getSecond()     // Catch:{ Exception -> 0x004d }
            java.lang.String r12 = (java.lang.String) r12     // Catch:{ Exception -> 0x004d }
            com.salesforce.marketingcloud.sfmcsdk.components.http.Request$Builder r12 = r3.addOrReplaceHeader(r4, r12)     // Catch:{ Exception -> 0x004d }
            com.salesforce.marketingcloud.sfmcsdk.components.http.Request r12 = r12.build()     // Catch:{ Exception -> 0x004d }
            r1.element = r12     // Catch:{ Exception -> 0x004d }
        L_0x014e:
            kotlin.jvm.internal.Ref$ObjectRef r12 = new kotlin.jvm.internal.Ref$ObjectRef     // Catch:{ Exception -> 0x004d }
            r12.<init>()     // Catch:{ Exception -> 0x004d }
            java.lang.Object r3 = r1.element     // Catch:{ Exception -> 0x004d }
            com.salesforce.marketingcloud.sfmcsdk.components.http.Request r3 = (com.salesforce.marketingcloud.sfmcsdk.components.http.Request) r3     // Catch:{ Exception -> 0x004d }
            com.salesforce.marketingcloud.sfmcsdk.components.http.Response r3 = r11.makeRequest$sfmcsdk_release(r3)     // Catch:{ Exception -> 0x004d }
            r12.element = r3     // Catch:{ Exception -> 0x004d }
            int r3 = r3.getCode()     // Catch:{ Exception -> 0x004d }
            r4 = 401(0x191, float:5.62E-43)
            if (r3 != r4) goto L_0x01a1
            com.salesforce.marketingcloud.sfmcsdk.components.http.Authenticator r3 = r11.authenticator     // Catch:{ Exception -> 0x004d }
            if (r3 == 0) goto L_0x01a1
            r3.deleteCachedToken()     // Catch:{ Exception -> 0x004d }
            kotlin.Pair r3 = r3.refreshAuthTokenHeader()     // Catch:{ Exception -> 0x004d }
            if (r3 == 0) goto L_0x01a1
            java.lang.Object r5 = r1.element     // Catch:{ Exception -> 0x004d }
            com.salesforce.marketingcloud.sfmcsdk.components.http.Request r5 = (com.salesforce.marketingcloud.sfmcsdk.components.http.Request) r5     // Catch:{ Exception -> 0x004d }
            com.salesforce.marketingcloud.sfmcsdk.components.http.Request$Builder r5 = r5.toBuilder$sfmcsdk_release()     // Catch:{ Exception -> 0x004d }
            java.lang.Object r6 = r3.getFirst()     // Catch:{ Exception -> 0x004d }
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ Exception -> 0x004d }
            java.lang.Object r3 = r3.getSecond()     // Catch:{ Exception -> 0x004d }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ Exception -> 0x004d }
            com.salesforce.marketingcloud.sfmcsdk.components.http.Request$Builder r3 = r5.addOrReplaceHeader(r6, r3)     // Catch:{ Exception -> 0x004d }
            com.salesforce.marketingcloud.sfmcsdk.components.http.Request r3 = r3.build()     // Catch:{ Exception -> 0x004d }
            r1.element = r3     // Catch:{ Exception -> 0x004d }
            com.salesforce.marketingcloud.sfmcsdk.components.http.Response r3 = r11.makeRequest$sfmcsdk_release(r3)     // Catch:{ Exception -> 0x004d }
            int r5 = r3.getCode()     // Catch:{ Exception -> 0x004d }
            if (r5 != r4) goto L_0x019f
            com.salesforce.marketingcloud.sfmcsdk.components.http.Authenticator r4 = r11.authenticator     // Catch:{ Exception -> 0x004d }
            r4.deleteCachedToken()     // Catch:{ Exception -> 0x004d }
        L_0x019f:
            r12.element = r3     // Catch:{ Exception -> 0x004d }
        L_0x01a1:
            java.lang.Object r3 = r1.element     // Catch:{ Exception -> 0x004d }
            com.salesforce.marketingcloud.sfmcsdk.components.http.Request r3 = (com.salesforce.marketingcloud.sfmcsdk.components.http.Request) r3     // Catch:{ Exception -> 0x004d }
            java.lang.Object r4 = r12.element     // Catch:{ Exception -> 0x004d }
            com.salesforce.marketingcloud.sfmcsdk.components.http.Response r4 = (com.salesforce.marketingcloud.sfmcsdk.components.http.Response) r4     // Catch:{ Exception -> 0x004d }
            r11.recordRetryAfter$sfmcsdk_release(r3, r4)     // Catch:{ Exception -> 0x004d }
            java.util.Map<java.lang.String, java.util.concurrent.atomic.AtomicBoolean> r3 = r11.requestsInFlight     // Catch:{ Exception -> 0x004d }
            java.lang.Object r4 = r1.element     // Catch:{ Exception -> 0x004d }
            com.salesforce.marketingcloud.sfmcsdk.components.http.Request r4 = (com.salesforce.marketingcloud.sfmcsdk.components.http.Request) r4     // Catch:{ Exception -> 0x004d }
            java.lang.String r4 = r4.getName()     // Catch:{ Exception -> 0x004d }
            java.lang.Object r3 = r3.get(r4)     // Catch:{ Exception -> 0x004d }
            java.util.concurrent.atomic.AtomicBoolean r3 = (java.util.concurrent.atomic.AtomicBoolean) r3     // Catch:{ Exception -> 0x004d }
            if (r3 == 0) goto L_0x01c1
            r3.set(r2)     // Catch:{ Exception -> 0x004d }
        L_0x01c1:
            com.salesforce.marketingcloud.sfmcsdk.components.logging.SFMCSdkLogger r3 = com.salesforce.marketingcloud.sfmcsdk.components.logging.SFMCSdkLogger.INSTANCE     // Catch:{ Exception -> 0x004d }
            com.salesforce.marketingcloud.sfmcsdk.components.http.NetworkManager$executeSync$2 r4 = new com.salesforce.marketingcloud.sfmcsdk.components.http.NetworkManager$executeSync$2     // Catch:{ Exception -> 0x004d }
            r4.<init>(r1, r12)     // Catch:{ Exception -> 0x004d }
            r3.d(r0, r4)     // Catch:{ Exception -> 0x004d }
            java.lang.Object r12 = r12.element     // Catch:{ Exception -> 0x004d }
            com.salesforce.marketingcloud.sfmcsdk.components.http.Response r12 = (com.salesforce.marketingcloud.sfmcsdk.components.http.Response) r12     // Catch:{ Exception -> 0x004d }
            return r12
        L_0x01d0:
            com.salesforce.marketingcloud.sfmcsdk.components.logging.SFMCSdkLogger r3 = com.salesforce.marketingcloud.sfmcsdk.components.logging.SFMCSdkLogger.INSTANCE
            com.salesforce.marketingcloud.sfmcsdk.components.http.NetworkManager$executeSync$3 r4 = com.salesforce.marketingcloud.sfmcsdk.components.http.NetworkManager$executeSync$3.INSTANCE
            r3.e(r0, r12, r4)
            java.util.Map<java.lang.String, java.util.concurrent.atomic.AtomicBoolean> r12 = r11.requestsInFlight
            java.lang.Object r0 = r1.element
            com.salesforce.marketingcloud.sfmcsdk.components.http.Request r0 = (com.salesforce.marketingcloud.sfmcsdk.components.http.Request) r0
            java.lang.String r0 = r0.getName()
            java.lang.Object r12 = r12.get(r0)
            java.util.concurrent.atomic.AtomicBoolean r12 = (java.util.concurrent.atomic.AtomicBoolean) r12
            if (r12 == 0) goto L_0x01ec
            r12.set(r2)
        L_0x01ec:
            com.salesforce.marketingcloud.sfmcsdk.components.http.Response$Companion r12 = com.salesforce.marketingcloud.sfmcsdk.components.http.Response.Companion
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = "An unknown error occurred. The "
            r0.append(r2)
            java.lang.Object r2 = r1.element
            com.salesforce.marketingcloud.sfmcsdk.components.http.Request r2 = (com.salesforce.marketingcloud.sfmcsdk.components.http.Request) r2
            java.lang.String r2 = r2.getName()
            r0.append(r2)
            java.lang.String r2 = " request to "
            r0.append(r2)
            java.lang.Object r1 = r1.element
            com.salesforce.marketingcloud.sfmcsdk.components.http.Request r1 = (com.salesforce.marketingcloud.sfmcsdk.components.http.Request) r1
            java.lang.String r1 = r1.getUrl()
            r0.append(r1)
            java.lang.String r1 = " could not be completed."
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r1 = -999(0xfffffffffffffc19, float:NaN)
            com.salesforce.marketingcloud.sfmcsdk.components.http.Response r12 = r12.error$sfmcsdk_release(r0, r1)
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.sfmcsdk.components.http.NetworkManager.executeSync(com.salesforce.marketingcloud.sfmcsdk.components.http.Request):com.salesforce.marketingcloud.sfmcsdk.components.http.Response");
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(3:34|35|(1:37)) */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00bd, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
        kotlin.io.CloseableKt.closeFinally(r1, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x00c1, code lost:
        throw r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:?, code lost:
        r1 = com.salesforce.marketingcloud.sfmcsdk.util.FileUtilsKt.readAll(((java.net.HttpURLConnection) r5.element).getErrorStream());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0110, code lost:
        if (r1 != null) goto L_0x0112;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0112, code lost:
        r0.body(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0142, code lost:
        if (r14 != null) goto L_0x0144;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0144, code lost:
        r14.disconnect();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x0176, code lost:
        if (r14 == null) goto L_0x0179;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x0179, code lost:
        return r0;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:34:0x0104 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.salesforce.marketingcloud.sfmcsdk.components.http.Response makeRequest$sfmcsdk_release(com.salesforce.marketingcloud.sfmcsdk.components.http.Request r14) {
        /*
            r13 = this;
            r0 = 2
            r1 = 1
            java.lang.String r2 = "~$NetworkManager"
            java.lang.String r3 = "request"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r14, r3)
            long r3 = java.lang.System.currentTimeMillis()
            kotlin.jvm.internal.Ref$ObjectRef r5 = new kotlin.jvm.internal.Ref$ObjectRef
            r5.<init>()
            r6 = 0
            java.net.URL r7 = new java.net.URL     // Catch:{ Exception -> 0x0082 }
            java.lang.String r8 = r14.getUrl()     // Catch:{ Exception -> 0x0082 }
            r7.<init>(r8)     // Catch:{ Exception -> 0x0082 }
            java.net.URLConnection r7 = r7.openConnection()     // Catch:{ Exception -> 0x0082 }
            if (r7 == 0) goto L_0x0148
            java.net.HttpURLConnection r7 = (java.net.HttpURLConnection) r7     // Catch:{ Exception -> 0x0082 }
            r5.element = r7     // Catch:{ Exception -> 0x0082 }
            java.lang.String r8 = r14.getMethod()     // Catch:{ Exception -> 0x0082 }
            r7.setRequestMethod(r8)     // Catch:{ Exception -> 0x0082 }
            java.lang.Object r7 = r5.element     // Catch:{ Exception -> 0x0082 }
            java.net.HttpURLConnection r7 = (java.net.HttpURLConnection) r7     // Catch:{ Exception -> 0x0082 }
            r7.setDoInput(r1)     // Catch:{ Exception -> 0x0082 }
            java.lang.Object r7 = r5.element     // Catch:{ Exception -> 0x0082 }
            java.net.HttpURLConnection r7 = (java.net.HttpURLConnection) r7     // Catch:{ Exception -> 0x0082 }
            r7.setUseCaches(r6)     // Catch:{ Exception -> 0x0082 }
            java.lang.Object r7 = r5.element     // Catch:{ Exception -> 0x0082 }
            java.net.HttpURLConnection r7 = (java.net.HttpURLConnection) r7     // Catch:{ Exception -> 0x0082 }
            r7.setAllowUserInteraction(r6)     // Catch:{ Exception -> 0x0082 }
            java.lang.Object r7 = r5.element     // Catch:{ Exception -> 0x0082 }
            java.net.HttpURLConnection r7 = (java.net.HttpURLConnection) r7     // Catch:{ Exception -> 0x0082 }
            int r8 = r14.getConnectionTimeout()     // Catch:{ Exception -> 0x0082 }
            r7.setConnectTimeout(r8)     // Catch:{ Exception -> 0x0082 }
            java.util.List r7 = r14.getHeaders()     // Catch:{ Exception -> 0x0082 }
            int r7 = r7.size()     // Catch:{ Exception -> 0x0082 }
            int r7 = r7 + -1
            int r7 = kotlin.internal.ProgressionUtilKt.getProgressionLastElement(r6, r7, r0)     // Catch:{ Exception -> 0x0082 }
            if (r7 < 0) goto L_0x0085
            r8 = r6
        L_0x005e:
            java.lang.Object r9 = r5.element     // Catch:{ Exception -> 0x0082 }
            java.net.HttpURLConnection r9 = (java.net.HttpURLConnection) r9     // Catch:{ Exception -> 0x0082 }
            java.util.List r10 = r14.getHeaders()     // Catch:{ Exception -> 0x0082 }
            java.lang.Object r10 = r10.get(r8)     // Catch:{ Exception -> 0x0082 }
            java.lang.String r10 = (java.lang.String) r10     // Catch:{ Exception -> 0x0082 }
            java.util.List r11 = r14.getHeaders()     // Catch:{ Exception -> 0x0082 }
            int r12 = r8 + 1
            java.lang.Object r11 = r11.get(r12)     // Catch:{ Exception -> 0x0082 }
            java.lang.String r11 = (java.lang.String) r11     // Catch:{ Exception -> 0x0082 }
            r9.setRequestProperty(r10, r11)     // Catch:{ Exception -> 0x0082 }
            if (r8 == r7) goto L_0x0085
            int r8 = r8 + r0
            goto L_0x005e
        L_0x007f:
            r0 = move-exception
            goto L_0x017a
        L_0x0082:
            r0 = move-exception
            goto L_0x0150
        L_0x0085:
            java.lang.String r0 = r14.getRequestBody()     // Catch:{ Exception -> 0x0082 }
            if (r0 == 0) goto L_0x00c2
            java.lang.Object r7 = r5.element     // Catch:{ Exception -> 0x0082 }
            java.net.HttpURLConnection r7 = (java.net.HttpURLConnection) r7     // Catch:{ Exception -> 0x0082 }
            r7.setDoOutput(r1)     // Catch:{ Exception -> 0x0082 }
            com.salesforce.marketingcloud.sfmcsdk.components.logging.SFMCSdkLogger r1 = com.salesforce.marketingcloud.sfmcsdk.components.logging.SFMCSdkLogger.INSTANCE     // Catch:{ Exception -> 0x0082 }
            com.salesforce.marketingcloud.sfmcsdk.components.http.NetworkManager$makeRequest$1$1 r7 = new com.salesforce.marketingcloud.sfmcsdk.components.http.NetworkManager$makeRequest$1$1     // Catch:{ Exception -> 0x0082 }
            r7.<init>(r14, r5)     // Catch:{ Exception -> 0x0082 }
            r1.d(r2, r7)     // Catch:{ Exception -> 0x0082 }
            java.lang.Object r1 = r5.element     // Catch:{ Exception -> 0x0082 }
            java.net.HttpURLConnection r1 = (java.net.HttpURLConnection) r1     // Catch:{ Exception -> 0x0082 }
            java.io.OutputStream r1 = r1.getOutputStream()     // Catch:{ Exception -> 0x0082 }
            java.nio.charset.Charset r7 = com.salesforce.marketingcloud.sfmcsdk.components.http.RequestKt.getUTF_8()     // Catch:{ all -> 0x00bb }
            byte[] r0 = r0.getBytes(r7)     // Catch:{ all -> 0x00bb }
            java.lang.String r7 = "this as java.lang.String).getBytes(charset)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r7)     // Catch:{ all -> 0x00bb }
            r1.write(r0)     // Catch:{ all -> 0x00bb }
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x00bb }
            r0 = 0
            kotlin.io.CloseableKt.closeFinally(r1, r0)     // Catch:{ Exception -> 0x0082 }
            goto L_0x00c2
        L_0x00bb:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x00bd }
        L_0x00bd:
            r3 = move-exception
            kotlin.io.CloseableKt.closeFinally(r1, r0)     // Catch:{ Exception -> 0x0082 }
            throw r3     // Catch:{ Exception -> 0x0082 }
        L_0x00c2:
            com.salesforce.marketingcloud.sfmcsdk.components.http.Response$Builder r0 = new com.salesforce.marketingcloud.sfmcsdk.components.http.Response$Builder     // Catch:{ Exception -> 0x0082 }
            r0.<init>()     // Catch:{ Exception -> 0x0082 }
            java.lang.Object r1 = r5.element     // Catch:{ Exception -> 0x0082 }
            java.net.HttpURLConnection r1 = (java.net.HttpURLConnection) r1     // Catch:{ Exception -> 0x0082 }
            int r1 = r1.getResponseCode()     // Catch:{ Exception -> 0x0082 }
            r0.code(r1)     // Catch:{ Exception -> 0x0082 }
            java.lang.Object r1 = r5.element     // Catch:{ Exception -> 0x0082 }
            java.net.HttpURLConnection r1 = (java.net.HttpURLConnection) r1     // Catch:{ Exception -> 0x0082 }
            java.lang.String r1 = r1.getResponseMessage()     // Catch:{ Exception -> 0x0082 }
            java.lang.String r7 = "connection.responseMessage"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r7)     // Catch:{ Exception -> 0x0082 }
            r0.message(r1)     // Catch:{ Exception -> 0x0082 }
            java.lang.Object r1 = r5.element     // Catch:{ Exception -> 0x0082 }
            java.net.HttpURLConnection r1 = (java.net.HttpURLConnection) r1     // Catch:{ Exception -> 0x0082 }
            java.util.Map r1 = r1.getHeaderFields()     // Catch:{ Exception -> 0x0082 }
            java.lang.String r7 = "connection.headerFields"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r7)     // Catch:{ Exception -> 0x0082 }
            r0.headers(r1)     // Catch:{ Exception -> 0x0082 }
            java.lang.Object r1 = r5.element     // Catch:{ IOException -> 0x0104 }
            java.net.HttpURLConnection r1 = (java.net.HttpURLConnection) r1     // Catch:{ IOException -> 0x0104 }
            java.io.InputStream r1 = r1.getInputStream()     // Catch:{ IOException -> 0x0104 }
            java.lang.String r1 = com.salesforce.marketingcloud.sfmcsdk.util.FileUtilsKt.readAll(r1)     // Catch:{ IOException -> 0x0104 }
            if (r1 == 0) goto L_0x0115
            r0.body(r1)     // Catch:{ IOException -> 0x0104 }
            goto L_0x0115
        L_0x0104:
            java.lang.Object r1 = r5.element     // Catch:{ Exception -> 0x0082 }
            java.net.HttpURLConnection r1 = (java.net.HttpURLConnection) r1     // Catch:{ Exception -> 0x0082 }
            java.io.InputStream r1 = r1.getErrorStream()     // Catch:{ Exception -> 0x0082 }
            java.lang.String r1 = com.salesforce.marketingcloud.sfmcsdk.util.FileUtilsKt.readAll(r1)     // Catch:{ Exception -> 0x0082 }
            if (r1 == 0) goto L_0x0115
            r0.body(r1)     // Catch:{ Exception -> 0x0082 }
        L_0x0115:
            r0.startTimeMillis(r3)     // Catch:{ Exception -> 0x0082 }
            long r3 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x0082 }
            r0.endTimeMillis(r3)     // Catch:{ Exception -> 0x0082 }
            com.salesforce.marketingcloud.sfmcsdk.components.http.Response r0 = r0.build()     // Catch:{ Exception -> 0x0082 }
            com.salesforce.marketingcloud.sfmcsdk.components.logging.SFMCSdkLogger r1 = com.salesforce.marketingcloud.sfmcsdk.components.logging.SFMCSdkLogger.INSTANCE     // Catch:{ Exception -> 0x0082 }
            com.salesforce.marketingcloud.sfmcsdk.components.http.NetworkManager$makeRequest$3$1 r3 = new com.salesforce.marketingcloud.sfmcsdk.components.http.NetworkManager$makeRequest$3$1     // Catch:{ Exception -> 0x0082 }
            r3.<init>(r0, r14)     // Catch:{ Exception -> 0x0082 }
            r1.d(r2, r3)     // Catch:{ Exception -> 0x0082 }
            java.util.Map<java.lang.String, java.util.concurrent.atomic.AtomicBoolean> r1 = r13.requestsInFlight
            java.lang.String r14 = r14.getName()
            java.lang.Object r14 = r1.get(r14)
            java.util.concurrent.atomic.AtomicBoolean r14 = (java.util.concurrent.atomic.AtomicBoolean) r14
            if (r14 == 0) goto L_0x013e
            r14.set(r6)
        L_0x013e:
            java.lang.Object r14 = r5.element
            java.net.HttpURLConnection r14 = (java.net.HttpURLConnection) r14
            if (r14 == 0) goto L_0x0179
        L_0x0144:
            r14.disconnect()
            goto L_0x0179
        L_0x0148:
            java.lang.NullPointerException r0 = new java.lang.NullPointerException     // Catch:{ Exception -> 0x0082 }
            java.lang.String r1 = "null cannot be cast to non-null type java.net.HttpURLConnection"
            r0.<init>(r1)     // Catch:{ Exception -> 0x0082 }
            throw r0     // Catch:{ Exception -> 0x0082 }
        L_0x0150:
            com.salesforce.marketingcloud.sfmcsdk.components.logging.SFMCSdkLogger r1 = com.salesforce.marketingcloud.sfmcsdk.components.logging.SFMCSdkLogger.INSTANCE     // Catch:{ all -> 0x007f }
            com.salesforce.marketingcloud.sfmcsdk.components.http.NetworkManager$makeRequest$4 r3 = com.salesforce.marketingcloud.sfmcsdk.components.http.NetworkManager$makeRequest$4.INSTANCE     // Catch:{ all -> 0x007f }
            r1.e(r2, r0, r3)     // Catch:{ all -> 0x007f }
            com.salesforce.marketingcloud.sfmcsdk.components.http.Response$Companion r0 = com.salesforce.marketingcloud.sfmcsdk.components.http.Response.Companion     // Catch:{ all -> 0x007f }
            java.lang.String r1 = "ERROR"
            r2 = -100
            com.salesforce.marketingcloud.sfmcsdk.components.http.Response r0 = r0.error$sfmcsdk_release(r1, r2)     // Catch:{ all -> 0x007f }
            java.util.Map<java.lang.String, java.util.concurrent.atomic.AtomicBoolean> r1 = r13.requestsInFlight
            java.lang.String r14 = r14.getName()
            java.lang.Object r14 = r1.get(r14)
            java.util.concurrent.atomic.AtomicBoolean r14 = (java.util.concurrent.atomic.AtomicBoolean) r14
            if (r14 == 0) goto L_0x0172
            r14.set(r6)
        L_0x0172:
            java.lang.Object r14 = r5.element
            java.net.HttpURLConnection r14 = (java.net.HttpURLConnection) r14
            if (r14 == 0) goto L_0x0179
            goto L_0x0144
        L_0x0179:
            return r0
        L_0x017a:
            java.util.Map<java.lang.String, java.util.concurrent.atomic.AtomicBoolean> r1 = r13.requestsInFlight
            java.lang.String r14 = r14.getName()
            java.lang.Object r14 = r1.get(r14)
            java.util.concurrent.atomic.AtomicBoolean r14 = (java.util.concurrent.atomic.AtomicBoolean) r14
            if (r14 == 0) goto L_0x018b
            r14.set(r6)
        L_0x018b:
            java.lang.Object r14 = r5.element
            java.net.HttpURLConnection r14 = (java.net.HttpURLConnection) r14
            if (r14 == 0) goto L_0x0194
            r14.disconnect()
        L_0x0194:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.sfmcsdk.components.http.NetworkManager.makeRequest$sfmcsdk_release(com.salesforce.marketingcloud.sfmcsdk.components.http.Request):com.salesforce.marketingcloud.sfmcsdk.components.http.Response");
    }

    public final boolean isBlockedByRetryAfter$sfmcsdk_release(String str) {
        Intrinsics.checkNotNullParameter(str, "requestName");
        long currentTimeMillis = System.currentTimeMillis();
        long serverRetryAfterTime$sfmcsdk_release = serverRetryAfterTime$sfmcsdk_release(str);
        long deviceRetryAfterTime$sfmcsdk_release = deviceRetryAfterTime$sfmcsdk_release(str);
        if (currentTimeMillis > serverRetryAfterTime$sfmcsdk_release && currentTimeMillis > deviceRetryAfterTime$sfmcsdk_release) {
            return false;
        }
        SFMCSdkLogger.INSTANCE.w(TAG, new NetworkManager$isBlockedByRetryAfter$1$1(str));
        return true;
    }

    public final boolean canMakeRequest(String... strArr) {
        Intrinsics.checkNotNullParameter(strArr, "requestNames");
        if (!NetworkUtils.hasConnectivity(this.context)) {
            return false;
        }
        for (String isBlockedByRetryAfter$sfmcsdk_release : strArr) {
            if (isBlockedByRetryAfter$sfmcsdk_release(isBlockedByRetryAfter$sfmcsdk_release)) {
                return false;
            }
        }
        return true;
    }

    public final long serverRetryAfterTime$sfmcsdk_release(String str) {
        Intrinsics.checkNotNullParameter(str, "requestName");
        return this.networkPreferences.getLong(Companion.getServerRetryKey$sfmcsdk_release(str), 0);
    }

    public final long deviceRetryAfterTime$sfmcsdk_release(String str) {
        Intrinsics.checkNotNullParameter(str, "requestName");
        return this.networkPreferences.getLong(Companion.getDeviceRetryKey$sfmcsdk_release(str), 0);
    }

    public static /* synthetic */ void recordDeviceRetryAfter$sfmcsdk_release$default(NetworkManager networkManager, Request request, long j, int i, Object obj) {
        if ((i & 2) != 0) {
            j = System.currentTimeMillis();
        }
        networkManager.recordDeviceRetryAfter$sfmcsdk_release(request, j);
    }

    public final void recordDeviceRetryAfter$sfmcsdk_release(Request request, long j) {
        Intrinsics.checkNotNullParameter(request, "request");
        if (request.getRateLimit() > 0) {
            SharedPreferences.Editor edit = this.networkPreferences.edit();
            edit.putLong(Companion.getDeviceRetryKey$sfmcsdk_release(request.getName()), request.getRateLimit() + j);
            edit.apply();
        }
    }

    public final void recordRetryAfter$sfmcsdk_release(Request request, Response response) {
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(response, "response");
        SharedPreferences.Editor edit = this.networkPreferences.edit();
        recordDeviceRetryAfter$sfmcsdk_release(request, response.getEndTimeMillis());
        List list = response.getHeaders().get("Retry-After");
        if (list != null && !list.isEmpty()) {
            try {
                long parseLong = Long.parseLong((String) list.get(0)) * 1000;
                String serverRetryKey$sfmcsdk_release = Companion.getServerRetryKey$sfmcsdk_release(request.getName());
                long endTimeMillis = response.getEndTimeMillis();
                if (parseLong > MAX_SERVER_RETRY) {
                    parseLong = 86400000;
                }
                edit.putLong(serverRetryKey$sfmcsdk_release, endTimeMillis + parseLong);
                edit.apply();
            } catch (Exception e) {
                SFMCSdkLogger.INSTANCE.d(TAG, e, NetworkManager$recordRetryAfter$1.INSTANCE);
            }
        }
    }
}
