package com.facebook.appevents.cloudbridge;

import com.facebook.GraphRequest;
import com.facebook.LoggingBehavior;
import com.facebook.internal.Logger;
import com.facebook.internal.Utility;
import com.google.firebase.messaging.Constants;
import com.google.firebase.sessions.settings.RemoteSettings;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.TuplesKt;
import kotlin.UninitializedPropertyAccessException;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.ranges.IntRange;
import kotlin.text.StringsKt;
import org.json.JSONArray;
import org.json.JSONObject;

public final class AppEventsConversionsAPITransformerWebRequests {
    /* access modifiers changed from: private */
    public static final HashSet ACCEPTABLE_HTTP_RESPONSE = SetsKt.hashSetOf(200, 202);
    public static final AppEventsConversionsAPITransformerWebRequests INSTANCE = new AppEventsConversionsAPITransformerWebRequests();
    private static final HashSet RETRY_EVENTS_HTTP_RESPONSE = SetsKt.hashSetOf(503, 504, 429);
    public static CloudBridgeCredentials credentials;
    private static int currentRetryCount;
    public static List transformedEvents;

    private AppEventsConversionsAPITransformerWebRequests() {
    }

    public static final class CloudBridgeCredentials {
        private final String accessKey;
        private final String cloudBridgeURL;
        private final String datasetID;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof CloudBridgeCredentials)) {
                return false;
            }
            CloudBridgeCredentials cloudBridgeCredentials = (CloudBridgeCredentials) obj;
            return Intrinsics.areEqual((Object) this.datasetID, (Object) cloudBridgeCredentials.datasetID) && Intrinsics.areEqual((Object) this.cloudBridgeURL, (Object) cloudBridgeCredentials.cloudBridgeURL) && Intrinsics.areEqual((Object) this.accessKey, (Object) cloudBridgeCredentials.accessKey);
        }

        public int hashCode() {
            return (((this.datasetID.hashCode() * 31) + this.cloudBridgeURL.hashCode()) * 31) + this.accessKey.hashCode();
        }

        public String toString() {
            return "CloudBridgeCredentials(datasetID=" + this.datasetID + ", cloudBridgeURL=" + this.cloudBridgeURL + ", accessKey=" + this.accessKey + ')';
        }

        public CloudBridgeCredentials(String str, String str2, String str3) {
            Intrinsics.checkNotNullParameter(str, "datasetID");
            Intrinsics.checkNotNullParameter(str2, "cloudBridgeURL");
            Intrinsics.checkNotNullParameter(str3, "accessKey");
            this.datasetID = str;
            this.cloudBridgeURL = str2;
            this.accessKey = str3;
        }

        public final String getDatasetID() {
            return this.datasetID;
        }

        public final String getCloudBridgeURL() {
            return this.cloudBridgeURL;
        }

        public final String getAccessKey() {
            return this.accessKey;
        }
    }

    public final CloudBridgeCredentials getCredentials$facebook_core_release() {
        CloudBridgeCredentials cloudBridgeCredentials = credentials;
        if (cloudBridgeCredentials != null) {
            return cloudBridgeCredentials;
        }
        Intrinsics.throwUninitializedPropertyAccessException("credentials");
        return null;
    }

    public final void setCredentials$facebook_core_release(CloudBridgeCredentials cloudBridgeCredentials) {
        Intrinsics.checkNotNullParameter(cloudBridgeCredentials, "<set-?>");
        credentials = cloudBridgeCredentials;
    }

    public final List getTransformedEvents$facebook_core_release() {
        List list = transformedEvents;
        if (list != null) {
            return list;
        }
        Intrinsics.throwUninitializedPropertyAccessException("transformedEvents");
        return null;
    }

    public final void setTransformedEvents$facebook_core_release(List list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        transformedEvents = list;
    }

    public static final void configure(String str, String str2, String str3) {
        Intrinsics.checkNotNullParameter(str, "datasetID");
        Intrinsics.checkNotNullParameter(str2, "url");
        Intrinsics.checkNotNullParameter(str3, "accessKey");
        Logger.Companion.log(LoggingBehavior.APP_EVENTS, "CAPITransformerWebRequests", " \n\nCloudbridge Configured: \n================\ndatasetID: %s\nurl: %s\naccessKey: %s\n\n", str, str2, str3);
        AppEventsConversionsAPITransformerWebRequests appEventsConversionsAPITransformerWebRequests = INSTANCE;
        appEventsConversionsAPITransformerWebRequests.setCredentials$facebook_core_release(new CloudBridgeCredentials(str, str2, str3));
        appEventsConversionsAPITransformerWebRequests.setTransformedEvents$facebook_core_release(new ArrayList());
    }

    public static final void transformGraphRequestAndSendToCAPIGEndPoint(GraphRequest graphRequest) {
        Intrinsics.checkNotNullParameter(graphRequest, "request");
        Utility.runOnNonUiThread(new AppEventsConversionsAPITransformerWebRequests$$ExternalSyntheticLambda0(graphRequest));
    }

    /* access modifiers changed from: private */
    public static final void transformGraphRequestAndSendToCAPIGEndPoint$lambda$0(GraphRequest graphRequest) {
        Intrinsics.checkNotNullParameter(graphRequest, "$request");
        String graphPath = graphRequest.getGraphPath();
        List split$default = graphPath != null ? StringsKt.split$default((CharSequence) graphPath, new String[]{RemoteSettings.FORWARD_SLASH_STRING}, false, 0, 6, (Object) null) : null;
        if (split$default == null || split$default.size() != 2) {
            Logger.Companion.log(LoggingBehavior.DEVELOPER_ERRORS, "CAPITransformerWebRequests", "\n GraphPathComponents Error when logging: \n%s", graphRequest);
            return;
        }
        try {
            AppEventsConversionsAPITransformerWebRequests appEventsConversionsAPITransformerWebRequests = INSTANCE;
            String str = appEventsConversionsAPITransformerWebRequests.getCredentials$facebook_core_release().getCloudBridgeURL() + "/capi/" + appEventsConversionsAPITransformerWebRequests.getCredentials$facebook_core_release().getDatasetID() + "/events";
            List transformAppEventRequestForCAPIG = appEventsConversionsAPITransformerWebRequests.transformAppEventRequestForCAPIG(graphRequest);
            if (transformAppEventRequestForCAPIG != null) {
                appEventsConversionsAPITransformerWebRequests.appendEvents$facebook_core_release(transformAppEventRequestForCAPIG);
                int min = Math.min(appEventsConversionsAPITransformerWebRequests.getTransformedEvents$facebook_core_release().size(), 10);
                List slice = CollectionsKt.slice(appEventsConversionsAPITransformerWebRequests.getTransformedEvents$facebook_core_release(), new IntRange(0, min - 1));
                appEventsConversionsAPITransformerWebRequests.getTransformedEvents$facebook_core_release().subList(0, min).clear();
                JSONArray jSONArray = new JSONArray(slice);
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                linkedHashMap.put(Constants.ScionAnalytics.MessageType.DATA_MESSAGE, jSONArray);
                linkedHashMap.put("accessKey", appEventsConversionsAPITransformerWebRequests.getCredentials$facebook_core_release().getAccessKey());
                JSONObject jSONObject = new JSONObject(linkedHashMap);
                Logger.Companion companion = Logger.Companion;
                LoggingBehavior loggingBehavior = LoggingBehavior.APP_EVENTS;
                String jSONObject2 = jSONObject.toString(2);
                Intrinsics.checkNotNullExpressionValue(jSONObject2, "jsonBodyStr.toString(2)");
                companion.log(loggingBehavior, "CAPITransformerWebRequests", "\nTransformed_CAPI_JSON:\nURL: %s\nFROM=========\n%s\n>>>>>>TO>>>>>>\n%s\n=============\n", str, graphRequest, jSONObject2);
                appEventsConversionsAPITransformerWebRequests.makeHttpRequest$facebook_core_release(str, "POST", jSONObject.toString(), MapsKt.mapOf(TuplesKt.to("Content-Type", "application/json")), 60000, new AppEventsConversionsAPITransformerWebRequests$transformGraphRequestAndSendToCAPIGEndPoint$1$1(slice));
            }
        } catch (UninitializedPropertyAccessException e) {
            Logger.Companion.log(LoggingBehavior.DEVELOPER_ERRORS, "CAPITransformerWebRequests", "\n Credentials not initialized Error when logging: \n%s", e);
        }
    }

    private final List transformAppEventRequestForCAPIG(GraphRequest graphRequest) {
        JSONObject graphObject = graphRequest.getGraphObject();
        if (graphObject == null) {
            return null;
        }
        Map mutableMap = MapsKt.toMutableMap(Utility.convertJSONObjectToHashMap(graphObject));
        Object tag = graphRequest.getTag();
        Intrinsics.checkNotNull(tag, "null cannot be cast to non-null type kotlin.Any");
        mutableMap.put("custom_events", tag);
        StringBuilder sb = new StringBuilder();
        for (String str : mutableMap.keySet()) {
            sb.append(str);
            sb.append(" : ");
            sb.append(mutableMap.get(str));
            sb.append(System.getProperty("line.separator"));
        }
        Logger.Companion.log(LoggingBehavior.APP_EVENTS, "CAPITransformerWebRequests", "\nGraph Request data: \n\n%s \n\n", sb);
        return AppEventsConversionsAPITransformer.INSTANCE.conversionsAPICompatibleEvent$facebook_core_release(mutableMap);
    }

    public final void handleError$facebook_core_release(Integer num, List list, int i) {
        Intrinsics.checkNotNullParameter(list, "processedEvents");
        if (!CollectionsKt.contains(RETRY_EVENTS_HTTP_RESPONSE, num)) {
            return;
        }
        if (currentRetryCount >= i) {
            getTransformedEvents$facebook_core_release().clear();
            currentRetryCount = 0;
            return;
        }
        getTransformedEvents$facebook_core_release().addAll(0, list);
        currentRetryCount++;
    }

    public final void appendEvents$facebook_core_release(List list) {
        if (list != null) {
            getTransformedEvents$facebook_core_release().addAll(list);
        }
        int max = Math.max(0, getTransformedEvents$facebook_core_release().size() - 1000);
        if (max > 0) {
            List drop = CollectionsKt.drop(getTransformedEvents$facebook_core_release(), max);
            Intrinsics.checkNotNull(drop, "null cannot be cast to non-null type kotlin.collections.MutableList<kotlin.collections.Map<kotlin.String, kotlin.Any>>");
            setTransformedEvents$facebook_core_release(TypeIntrinsics.asMutableList(drop));
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x00a2 A[Catch:{ UnknownHostException -> 0x0049, IOException -> 0x0046 }] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00e8 A[Catch:{ UnknownHostException -> 0x0049, IOException -> 0x0046 }] */
    /* JADX WARNING: Removed duplicated region for block: B:49:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void makeHttpRequest$facebook_core_release(java.lang.String r6, java.lang.String r7, java.lang.String r8, java.util.Map r9, int r10, kotlin.jvm.functions.Function2 r11) {
        /*
            r5 = this;
            java.lang.String r0 = "UTF-8"
            java.lang.String r1 = "CAPITransformerWebRequests"
            java.lang.String r2 = "urlStr"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r2)
            java.lang.String r2 = "requestMethod"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r2)
            r2 = 0
            java.net.URL r3 = new java.net.URL     // Catch:{ UnknownHostException -> 0x0049, IOException -> 0x0046 }
            r3.<init>(r6)     // Catch:{ UnknownHostException -> 0x0049, IOException -> 0x0046 }
            java.net.URLConnection r6 = r3.openConnection()     // Catch:{ UnknownHostException -> 0x0049, IOException -> 0x0046 }
            java.lang.String r3 = "null cannot be cast to non-null type java.net.HttpURLConnection"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r6, r3)     // Catch:{ UnknownHostException -> 0x0049, IOException -> 0x0046 }
            java.net.HttpURLConnection r6 = (java.net.HttpURLConnection) r6     // Catch:{ UnknownHostException -> 0x0049, IOException -> 0x0046 }
            r6.setRequestMethod(r7)     // Catch:{ UnknownHostException -> 0x0049, IOException -> 0x0046 }
            if (r9 == 0) goto L_0x004c
            java.util.Set r7 = r9.keySet()     // Catch:{ UnknownHostException -> 0x0049, IOException -> 0x0046 }
            if (r7 == 0) goto L_0x004c
            java.lang.Iterable r7 = (java.lang.Iterable) r7     // Catch:{ UnknownHostException -> 0x0049, IOException -> 0x0046 }
            java.util.Iterator r7 = r7.iterator()     // Catch:{ UnknownHostException -> 0x0049, IOException -> 0x0046 }
        L_0x0030:
            boolean r3 = r7.hasNext()     // Catch:{ UnknownHostException -> 0x0049, IOException -> 0x0046 }
            if (r3 == 0) goto L_0x004c
            java.lang.Object r3 = r7.next()     // Catch:{ UnknownHostException -> 0x0049, IOException -> 0x0046 }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ UnknownHostException -> 0x0049, IOException -> 0x0046 }
            java.lang.Object r4 = r9.get(r3)     // Catch:{ UnknownHostException -> 0x0049, IOException -> 0x0046 }
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ UnknownHostException -> 0x0049, IOException -> 0x0046 }
            r6.setRequestProperty(r3, r4)     // Catch:{ UnknownHostException -> 0x0049, IOException -> 0x0046 }
            goto L_0x0030
        L_0x0046:
            r6 = move-exception
            goto L_0x00f4
        L_0x0049:
            r6 = move-exception
            goto L_0x0106
        L_0x004c:
            java.lang.String r7 = r6.getRequestMethod()     // Catch:{ UnknownHostException -> 0x0049, IOException -> 0x0046 }
            java.lang.String r9 = "POST"
            boolean r7 = r7.equals(r9)     // Catch:{ UnknownHostException -> 0x0049, IOException -> 0x0046 }
            if (r7 != 0) goto L_0x0067
            java.lang.String r7 = r6.getRequestMethod()     // Catch:{ UnknownHostException -> 0x0049, IOException -> 0x0046 }
            java.lang.String r9 = "PUT"
            boolean r7 = r7.equals(r9)     // Catch:{ UnknownHostException -> 0x0049, IOException -> 0x0046 }
            if (r7 == 0) goto L_0x0065
            goto L_0x0067
        L_0x0065:
            r7 = 0
            goto L_0x0068
        L_0x0067:
            r7 = 1
        L_0x0068:
            r6.setDoOutput(r7)     // Catch:{ UnknownHostException -> 0x0049, IOException -> 0x0046 }
            r6.setConnectTimeout(r10)     // Catch:{ UnknownHostException -> 0x0049, IOException -> 0x0046 }
            java.io.BufferedOutputStream r7 = new java.io.BufferedOutputStream     // Catch:{ UnknownHostException -> 0x0049, IOException -> 0x0046 }
            java.io.OutputStream r9 = r6.getOutputStream()     // Catch:{ UnknownHostException -> 0x0049, IOException -> 0x0046 }
            r7.<init>(r9)     // Catch:{ UnknownHostException -> 0x0049, IOException -> 0x0046 }
            java.io.BufferedWriter r9 = new java.io.BufferedWriter     // Catch:{ UnknownHostException -> 0x0049, IOException -> 0x0046 }
            java.io.OutputStreamWriter r10 = new java.io.OutputStreamWriter     // Catch:{ UnknownHostException -> 0x0049, IOException -> 0x0046 }
            r10.<init>(r7, r0)     // Catch:{ UnknownHostException -> 0x0049, IOException -> 0x0046 }
            r9.<init>(r10)     // Catch:{ UnknownHostException -> 0x0049, IOException -> 0x0046 }
            r9.write(r8)     // Catch:{ UnknownHostException -> 0x0049, IOException -> 0x0046 }
            r9.flush()     // Catch:{ UnknownHostException -> 0x0049, IOException -> 0x0046 }
            r9.close()     // Catch:{ UnknownHostException -> 0x0049, IOException -> 0x0046 }
            r7.close()     // Catch:{ UnknownHostException -> 0x0049, IOException -> 0x0046 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ UnknownHostException -> 0x0049, IOException -> 0x0046 }
            r7.<init>()     // Catch:{ UnknownHostException -> 0x0049, IOException -> 0x0046 }
            java.util.HashSet r8 = ACCEPTABLE_HTTP_RESPONSE     // Catch:{ UnknownHostException -> 0x0049, IOException -> 0x0046 }
            int r9 = r6.getResponseCode()     // Catch:{ UnknownHostException -> 0x0049, IOException -> 0x0046 }
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ UnknownHostException -> 0x0049, IOException -> 0x0046 }
            boolean r8 = r8.contains(r9)     // Catch:{ UnknownHostException -> 0x0049, IOException -> 0x0046 }
            if (r8 == 0) goto L_0x00c8
            java.io.BufferedReader r8 = new java.io.BufferedReader     // Catch:{ UnknownHostException -> 0x0049, IOException -> 0x0046 }
            java.io.InputStreamReader r9 = new java.io.InputStreamReader     // Catch:{ UnknownHostException -> 0x0049, IOException -> 0x0046 }
            java.io.InputStream r10 = r6.getInputStream()     // Catch:{ UnknownHostException -> 0x0049, IOException -> 0x0046 }
            r9.<init>(r10, r0)     // Catch:{ UnknownHostException -> 0x0049, IOException -> 0x0046 }
            r8.<init>(r9)     // Catch:{ UnknownHostException -> 0x0049, IOException -> 0x0046 }
        L_0x00b0:
            java.lang.String r9 = r8.readLine()     // Catch:{ all -> 0x00ba }
            if (r9 == 0) goto L_0x00bc
            r7.append(r9)     // Catch:{ all -> 0x00ba }
            goto L_0x00b0
        L_0x00ba:
            r6 = move-exception
            goto L_0x00c2
        L_0x00bc:
            kotlin.Unit r9 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x00ba }
            kotlin.io.CloseableKt.closeFinally(r8, r2)     // Catch:{ UnknownHostException -> 0x0049, IOException -> 0x0046 }
            goto L_0x00c8
        L_0x00c2:
            throw r6     // Catch:{ all -> 0x00c3 }
        L_0x00c3:
            r7 = move-exception
            kotlin.io.CloseableKt.closeFinally(r8, r6)     // Catch:{ UnknownHostException -> 0x0049, IOException -> 0x0046 }
            throw r7     // Catch:{ UnknownHostException -> 0x0049, IOException -> 0x0046 }
        L_0x00c8:
            java.lang.String r7 = r7.toString()     // Catch:{ UnknownHostException -> 0x0049, IOException -> 0x0046 }
            java.lang.String r8 = "connResponseSB.toString()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, r8)     // Catch:{ UnknownHostException -> 0x0049, IOException -> 0x0046 }
            com.facebook.internal.Logger$Companion r8 = com.facebook.internal.Logger.Companion     // Catch:{ UnknownHostException -> 0x0049, IOException -> 0x0046 }
            com.facebook.LoggingBehavior r9 = com.facebook.LoggingBehavior.APP_EVENTS     // Catch:{ UnknownHostException -> 0x0049, IOException -> 0x0046 }
            java.lang.String r10 = "\nResponse Received: \n%s\n%s"
            int r0 = r6.getResponseCode()     // Catch:{ UnknownHostException -> 0x0049, IOException -> 0x0046 }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ UnknownHostException -> 0x0049, IOException -> 0x0046 }
            java.lang.Object[] r0 = new java.lang.Object[]{r7, r0}     // Catch:{ UnknownHostException -> 0x0049, IOException -> 0x0046 }
            r8.log((com.facebook.LoggingBehavior) r9, (java.lang.String) r1, (java.lang.String) r10, (java.lang.Object[]) r0)     // Catch:{ UnknownHostException -> 0x0049, IOException -> 0x0046 }
            if (r11 == 0) goto L_0x0122
            int r6 = r6.getResponseCode()     // Catch:{ UnknownHostException -> 0x0049, IOException -> 0x0046 }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ UnknownHostException -> 0x0049, IOException -> 0x0046 }
            r11.invoke(r7, r6)     // Catch:{ UnknownHostException -> 0x0049, IOException -> 0x0046 }
            goto L_0x0122
        L_0x00f4:
            com.facebook.internal.Logger$Companion r7 = com.facebook.internal.Logger.Companion
            com.facebook.LoggingBehavior r8 = com.facebook.LoggingBehavior.DEVELOPER_ERRORS
            java.lang.String r6 = r6.toString()
            java.lang.Object[] r6 = new java.lang.Object[]{r6}
            java.lang.String r9 = "Send to server failed: \n%s"
            r7.log((com.facebook.LoggingBehavior) r8, (java.lang.String) r1, (java.lang.String) r9, (java.lang.Object[]) r6)
            goto L_0x0122
        L_0x0106:
            com.facebook.internal.Logger$Companion r7 = com.facebook.internal.Logger.Companion
            com.facebook.LoggingBehavior r8 = com.facebook.LoggingBehavior.APP_EVENTS
            java.lang.String r6 = r6.toString()
            java.lang.Object[] r6 = new java.lang.Object[]{r6}
            java.lang.String r9 = "Connection failed, retrying: \n%s"
            r7.log((com.facebook.LoggingBehavior) r8, (java.lang.String) r1, (java.lang.String) r9, (java.lang.Object[]) r6)
            if (r11 == 0) goto L_0x0122
            r6 = 503(0x1f7, float:7.05E-43)
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            r11.invoke(r2, r6)
        L_0x0122:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.appevents.cloudbridge.AppEventsConversionsAPITransformerWebRequests.makeHttpRequest$facebook_core_release(java.lang.String, java.lang.String, java.lang.String, java.util.Map, int, kotlin.jvm.functions.Function2):void");
    }
}
