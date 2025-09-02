package com.facebook;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import app.notifee.core.event.LogEvent;
import com.facebook.GraphRequestBatch;
import com.facebook.internal.InternalSettings;
import com.facebook.internal.Logger;
import com.facebook.internal.ServerProtocol;
import com.facebook.internal.Utility;
import com.facebook.internal.Validate;
import com.facebook.react.devsupport.StackTraceHelper;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.reactnativecommunity.clipboard.ClipboardModule;
import com.salesforce.marketingcloud.storage.db.i;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class GraphRequest {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final String MIME_BOUNDARY;
    public static final String TAG = GraphRequest.class.getSimpleName();
    /* access modifiers changed from: private */
    public static String defaultBatchApplicationId;
    /* access modifiers changed from: private */
    public static volatile String userAgent;
    /* access modifiers changed from: private */
    public static final Pattern versionPattern = Pattern.compile("^/?v\\d+\\.\\d+/(.*)");
    private AccessToken accessToken;
    private String batchEntryDependsOn;
    private String batchEntryName;
    private boolean batchEntryOmitResultOnSuccess;
    private Callback callback;
    private boolean forceApplicationRequest;
    private JSONObject graphObject;
    private String graphPath;
    private HttpMethod httpMethod;
    private String overriddenURL;
    private Bundle parameters;
    private Object tag;
    private String version;

    public interface Callback {
        void onCompleted(GraphResponse graphResponse);
    }

    public interface GraphJSONObjectCallback {
        void onCompleted(JSONObject jSONObject, GraphResponse graphResponse);
    }

    private interface KeyValueSerializer {
        void writeString(String str, String str2);
    }

    public final AccessToken getAccessToken() {
        return this.accessToken;
    }

    public final String getGraphPath() {
        return this.graphPath;
    }

    public final JSONObject getGraphObject() {
        return this.graphObject;
    }

    public final void setGraphObject(JSONObject jSONObject) {
        this.graphObject = jSONObject;
    }

    public final Bundle getParameters() {
        return this.parameters;
    }

    public final void setParameters(Bundle bundle) {
        Intrinsics.checkNotNullParameter(bundle, "<set-?>");
        this.parameters = bundle;
    }

    public final Object getTag() {
        return this.tag;
    }

    public final void setTag(Object obj) {
        this.tag = obj;
    }

    public final Callback getCallback() {
        return this.callback;
    }

    public final void setCallback(Callback callback2) {
        if (FacebookSdk.isLoggingBehaviorEnabled(LoggingBehavior.GRAPH_API_DEBUG_INFO) || FacebookSdk.isLoggingBehaviorEnabled(LoggingBehavior.GRAPH_API_DEBUG_WARNING)) {
            this.callback = new GraphRequest$$ExternalSyntheticLambda0(callback2);
        } else {
            this.callback = callback2;
        }
    }

    /* access modifiers changed from: private */
    public static final void _set_callback_$lambda$0(Callback callback2, GraphResponse graphResponse) {
        Intrinsics.checkNotNullParameter(graphResponse, "response");
        JSONObject jSONObject = graphResponse.getJSONObject();
        JSONObject optJSONObject = jSONObject != null ? jSONObject.optJSONObject("__debug__") : null;
        JSONArray optJSONArray = optJSONObject != null ? optJSONObject.optJSONArray(i.e) : null;
        if (optJSONArray != null) {
            int length = optJSONArray.length();
            for (int i = 0; i < length; i++) {
                JSONObject optJSONObject2 = optJSONArray.optJSONObject(i);
                String optString = optJSONObject2 != null ? optJSONObject2.optString(StackTraceHelper.MESSAGE_KEY) : null;
                String optString2 = optJSONObject2 != null ? optJSONObject2.optString("type") : null;
                String optString3 = optJSONObject2 != null ? optJSONObject2.optString("link") : null;
                if (!(optString == null || optString2 == null)) {
                    LoggingBehavior loggingBehavior = LoggingBehavior.GRAPH_API_DEBUG_INFO;
                    if (Intrinsics.areEqual((Object) optString2, (Object) "warning")) {
                        loggingBehavior = LoggingBehavior.GRAPH_API_DEBUG_WARNING;
                    }
                    if (!Utility.isNullOrEmpty(optString3)) {
                        optString = optString + " Link: " + optString3;
                    }
                    Logger.Companion companion = Logger.Companion;
                    String str = TAG;
                    Intrinsics.checkNotNullExpressionValue(str, "TAG");
                    companion.log(loggingBehavior, str, optString);
                }
            }
        }
        if (callback2 != null) {
            callback2.onCompleted(graphResponse);
        }
    }

    public final HttpMethod getHttpMethod() {
        return this.httpMethod;
    }

    public final void setHttpMethod(HttpMethod httpMethod2) {
        if (this.overriddenURL == null || httpMethod2 == HttpMethod.GET) {
            if (httpMethod2 == null) {
                httpMethod2 = HttpMethod.GET;
            }
            this.httpMethod = httpMethod2;
            return;
        }
        throw new FacebookException("Can't change HTTP method on request with overridden URL.");
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final GraphRequest newMeRequest(AccessToken accessToken, GraphJSONObjectCallback graphJSONObjectCallback) {
            return new GraphRequest(accessToken, "me", (Bundle) null, (HttpMethod) null, new GraphRequest$Companion$$ExternalSyntheticLambda0(graphJSONObjectCallback), (String) null, 32, (DefaultConstructorMarker) null);
        }

        /* access modifiers changed from: private */
        public static final void newMeRequest$lambda$0(GraphJSONObjectCallback graphJSONObjectCallback, GraphResponse graphResponse) {
            Intrinsics.checkNotNullParameter(graphResponse, "response");
            if (graphJSONObjectCallback != null) {
                graphJSONObjectCallback.onCompleted(graphResponse.getJSONObject(), graphResponse);
            }
        }

        public final GraphRequest newPostRequest(AccessToken accessToken, String str, JSONObject jSONObject, Callback callback) {
            GraphRequest graphRequest = new GraphRequest(accessToken, str, (Bundle) null, HttpMethod.POST, callback, (String) null, 32, (DefaultConstructorMarker) null);
            graphRequest.setGraphObject(jSONObject);
            return graphRequest;
        }

        public final GraphRequest newPostRequestWithBundle(AccessToken accessToken, String str, Bundle bundle, Callback callback) {
            return new GraphRequest(accessToken, str, bundle, HttpMethod.POST, callback, (String) null, 32, (DefaultConstructorMarker) null);
        }

        public final GraphRequest newGraphPathRequest(AccessToken accessToken, String str, Callback callback) {
            return new GraphRequest(accessToken, str, (Bundle) null, (HttpMethod) null, callback, (String) null, 32, (DefaultConstructorMarker) null);
        }

        public final HttpURLConnection toHttpConnection(GraphRequestBatch graphRequestBatch) {
            URL url;
            Intrinsics.checkNotNullParameter(graphRequestBatch, "requests");
            validateFieldsParamForGetRequests$facebook_core_release(graphRequestBatch);
            try {
                if (graphRequestBatch.size() == 1) {
                    url = new URL(graphRequestBatch.get(0).getUrlForSingleRequest());
                } else {
                    url = new URL(ServerProtocol.getGraphUrlBase());
                }
                HttpURLConnection httpURLConnection = null;
                try {
                    httpURLConnection = createConnection(url);
                    serializeToUrlConnection$facebook_core_release(graphRequestBatch, httpURLConnection);
                    return httpURLConnection;
                } catch (IOException e) {
                    Utility.disconnectQuietly(httpURLConnection);
                    throw new FacebookException("could not construct request body", e);
                } catch (JSONException e2) {
                    Utility.disconnectQuietly(httpURLConnection);
                    throw new FacebookException("could not construct request body", e2);
                }
            } catch (MalformedURLException e3) {
                throw new FacebookException("could not construct URL for request", e3);
            }
        }

        public final GraphResponse executeAndWait(GraphRequest graphRequest) {
            Intrinsics.checkNotNullParameter(graphRequest, "request");
            List executeBatchAndWait = executeBatchAndWait(graphRequest);
            if (executeBatchAndWait.size() == 1) {
                return (GraphResponse) executeBatchAndWait.get(0);
            }
            throw new FacebookException("invalid state: expected a single response");
        }

        public final List executeBatchAndWait(GraphRequest... graphRequestArr) {
            Intrinsics.checkNotNullParameter(graphRequestArr, "requests");
            return executeBatchAndWait((Collection) ArraysKt.toList(graphRequestArr));
        }

        public final List executeBatchAndWait(Collection collection) {
            Intrinsics.checkNotNullParameter(collection, "requests");
            return executeBatchAndWait(new GraphRequestBatch(collection));
        }

        public final List executeBatchAndWait(GraphRequestBatch graphRequestBatch) {
            Exception exc;
            HttpURLConnection httpURLConnection;
            List list;
            Intrinsics.checkNotNullParameter(graphRequestBatch, "requests");
            Validate.notEmptyAndContainsNoNulls(graphRequestBatch, "requests");
            HttpURLConnection httpURLConnection2 = null;
            try {
                httpURLConnection = toHttpConnection(graphRequestBatch);
                exc = null;
            } catch (Exception e) {
                exc = e;
                httpURLConnection = null;
            } catch (Throwable th) {
                th = th;
                Utility.disconnectQuietly(httpURLConnection2);
                throw th;
            }
            if (httpURLConnection != null) {
                try {
                    list = executeConnectionAndWait(httpURLConnection, graphRequestBatch);
                } catch (Throwable th2) {
                    th = th2;
                    httpURLConnection2 = httpURLConnection;
                    Utility.disconnectQuietly(httpURLConnection2);
                    throw th;
                }
            } else {
                List constructErrorResponses = GraphResponse.Companion.constructErrorResponses(graphRequestBatch.getRequests(), (HttpURLConnection) null, new FacebookException((Throwable) exc));
                runCallbacks$facebook_core_release(graphRequestBatch, constructErrorResponses);
                list = constructErrorResponses;
            }
            Utility.disconnectQuietly(httpURLConnection);
            return list;
        }

        public final GraphRequestAsyncTask executeBatchAsync(GraphRequest... graphRequestArr) {
            Intrinsics.checkNotNullParameter(graphRequestArr, "requests");
            return executeBatchAsync((Collection) ArraysKt.toList(graphRequestArr));
        }

        public final GraphRequestAsyncTask executeBatchAsync(Collection collection) {
            Intrinsics.checkNotNullParameter(collection, "requests");
            return executeBatchAsync(new GraphRequestBatch(collection));
        }

        public final GraphRequestAsyncTask executeBatchAsync(GraphRequestBatch graphRequestBatch) {
            Intrinsics.checkNotNullParameter(graphRequestBatch, "requests");
            Validate.notEmptyAndContainsNoNulls(graphRequestBatch, "requests");
            GraphRequestAsyncTask graphRequestAsyncTask = new GraphRequestAsyncTask(graphRequestBatch);
            graphRequestAsyncTask.executeOnExecutor(FacebookSdk.getExecutor(), new Void[0]);
            return graphRequestAsyncTask;
        }

        public final List executeConnectionAndWait(HttpURLConnection httpURLConnection, GraphRequestBatch graphRequestBatch) {
            Intrinsics.checkNotNullParameter(httpURLConnection, "connection");
            Intrinsics.checkNotNullParameter(graphRequestBatch, "requests");
            List fromHttpConnection$facebook_core_release = GraphResponse.Companion.fromHttpConnection$facebook_core_release(httpURLConnection, graphRequestBatch);
            Utility.disconnectQuietly(httpURLConnection);
            int size = graphRequestBatch.size();
            if (size == fromHttpConnection$facebook_core_release.size()) {
                runCallbacks$facebook_core_release(graphRequestBatch, fromHttpConnection$facebook_core_release);
                AccessTokenManager.Companion.getInstance().extendAccessTokenIfNeeded();
                return fromHttpConnection$facebook_core_release;
            }
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String format = String.format(Locale.US, "Received %d responses while expecting %d", Arrays.copyOf(new Object[]{Integer.valueOf(fromHttpConnection$facebook_core_release.size()), Integer.valueOf(size)}, 2));
            Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
            throw new FacebookException(format);
        }

        public final void runCallbacks$facebook_core_release(GraphRequestBatch graphRequestBatch, List list) {
            Intrinsics.checkNotNullParameter(graphRequestBatch, "requests");
            Intrinsics.checkNotNullParameter(list, "responses");
            int size = graphRequestBatch.size();
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < size; i++) {
                GraphRequest graphRequest = graphRequestBatch.get(i);
                if (graphRequest.getCallback() != null) {
                    arrayList.add(new Pair(graphRequest.getCallback(), list.get(i)));
                }
            }
            if (arrayList.size() > 0) {
                GraphRequest$Companion$$ExternalSyntheticLambda1 graphRequest$Companion$$ExternalSyntheticLambda1 = new GraphRequest$Companion$$ExternalSyntheticLambda1(arrayList, graphRequestBatch);
                Handler callbackHandler = graphRequestBatch.getCallbackHandler();
                if (callbackHandler != null) {
                    callbackHandler.post(graphRequest$Companion$$ExternalSyntheticLambda1);
                } else {
                    graphRequest$Companion$$ExternalSyntheticLambda1.run();
                }
            }
        }

        /* access modifiers changed from: private */
        public static final void runCallbacks$lambda$2(ArrayList arrayList, GraphRequestBatch graphRequestBatch) {
            Intrinsics.checkNotNullParameter(arrayList, "$callbacks");
            Intrinsics.checkNotNullParameter(graphRequestBatch, "$requests");
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                Pair pair = (Pair) it.next();
                Object obj = pair.second;
                Intrinsics.checkNotNullExpressionValue(obj, "pair.second");
                ((Callback) pair.first).onCompleted((GraphResponse) obj);
            }
            for (GraphRequestBatch.Callback onBatchCompleted : graphRequestBatch.getCallbacks()) {
                onBatchCompleted.onBatchCompleted(graphRequestBatch);
            }
        }

        private final HttpURLConnection createConnection(URL url) {
            URLConnection openConnection = url.openConnection();
            Intrinsics.checkNotNull(openConnection, "null cannot be cast to non-null type java.net.HttpURLConnection");
            HttpURLConnection httpURLConnection = (HttpURLConnection) openConnection;
            httpURLConnection.setRequestProperty("User-Agent", getUserAgent());
            httpURLConnection.setRequestProperty("Accept-Language", Locale.getDefault().toString());
            httpURLConnection.setChunkedStreamingMode(0);
            return httpURLConnection;
        }

        private final boolean hasOnProgressCallbacks(GraphRequestBatch graphRequestBatch) {
            for (GraphRequestBatch.Callback callback : graphRequestBatch.getCallbacks()) {
            }
            Iterator it = graphRequestBatch.iterator();
            while (it.hasNext()) {
                ((GraphRequest) it.next()).getCallback();
            }
            return false;
        }

        private final void setConnectionContentType(HttpURLConnection httpURLConnection, boolean z) {
            if (z) {
                httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                httpURLConnection.setRequestProperty("Content-Encoding", "gzip");
                return;
            }
            httpURLConnection.setRequestProperty("Content-Type", getMimeContentType());
        }

        private final boolean isGzipCompressible(GraphRequestBatch graphRequestBatch) {
            Iterator it = graphRequestBatch.iterator();
            while (it.hasNext()) {
                GraphRequest graphRequest = (GraphRequest) it.next();
                Iterator<String> it2 = graphRequest.getParameters().keySet().iterator();
                while (true) {
                    if (it2.hasNext()) {
                        if (isSupportedAttachmentType(graphRequest.getParameters().get(it2.next()))) {
                            return false;
                        }
                    }
                }
            }
            return true;
        }

        public final void validateFieldsParamForGetRequests$facebook_core_release(GraphRequestBatch graphRequestBatch) {
            Intrinsics.checkNotNullParameter(graphRequestBatch, "requests");
            Iterator it = graphRequestBatch.iterator();
            while (it.hasNext()) {
                GraphRequest graphRequest = (GraphRequest) it.next();
                if (HttpMethod.GET == graphRequest.getHttpMethod() && Utility.isNullOrEmpty(graphRequest.getParameters().getString("fields"))) {
                    Logger.Companion companion = Logger.Companion;
                    LoggingBehavior loggingBehavior = LoggingBehavior.DEVELOPER_ERRORS;
                    StringBuilder sb = new StringBuilder();
                    sb.append("GET requests for /");
                    String graphPath = graphRequest.getGraphPath();
                    if (graphPath == null) {
                        graphPath = "";
                    }
                    sb.append(graphPath);
                    sb.append(" should contain an explicit \"fields\" parameter.");
                    companion.log(loggingBehavior, 5, "Request", sb.toString());
                }
            }
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: java.io.OutputStream} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: java.io.OutputStream} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: java.io.OutputStream} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v4, resolved type: java.io.OutputStream} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v6, resolved type: java.io.OutputStream} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v4, resolved type: com.facebook.ProgressOutputStream} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v7, resolved type: java.io.OutputStream} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v8, resolved type: java.io.OutputStream} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v9, resolved type: java.io.OutputStream} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v10, resolved type: com.facebook.ProgressOutputStream} */
        /* JADX WARNING: type inference failed for: r15v3, types: [java.io.OutputStream] */
        /* JADX WARNING: Multi-variable type inference failed */
        /* JADX WARNING: Removed duplicated region for block: B:32:0x00ec  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void serializeToUrlConnection$facebook_core_release(com.facebook.GraphRequestBatch r14, java.net.HttpURLConnection r15) {
            /*
                r13 = this;
                java.lang.String r0 = "requests"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r14, r0)
                java.lang.String r0 = "connection"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r15, r0)
                com.facebook.internal.Logger r0 = new com.facebook.internal.Logger
                com.facebook.LoggingBehavior r1 = com.facebook.LoggingBehavior.REQUESTS
                java.lang.String r2 = "Request"
                r0.<init>(r1, r2)
                int r10 = r14.size()
                boolean r11 = r13.isGzipCompressible(r14)
                r1 = 0
                r2 = 1
                if (r10 != r2) goto L_0x0029
                r3 = 0
                com.facebook.GraphRequest r3 = r14.get((int) r3)
                com.facebook.HttpMethod r3 = r3.getHttpMethod()
                goto L_0x002a
            L_0x0029:
                r3 = r1
            L_0x002a:
                if (r3 != 0) goto L_0x002e
                com.facebook.HttpMethod r3 = com.facebook.HttpMethod.POST
            L_0x002e:
                java.lang.String r4 = r3.name()
                r15.setRequestMethod(r4)
                r13.setConnectionContentType(r15, r11)
                java.net.URL r12 = r15.getURL()
                java.lang.String r4 = "Request:\n"
                r0.append(r4)
                java.lang.String r4 = "Id"
                java.lang.String r5 = r14.getId()
                r0.appendKeyValue(r4, r5)
                java.lang.String r4 = "url"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r12, r4)
                java.lang.String r4 = "URL"
                r0.appendKeyValue(r4, r12)
                java.lang.String r4 = r15.getRequestMethod()
                java.lang.String r5 = "connection.requestMethod"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r5)
                java.lang.String r5 = "Method"
                r0.appendKeyValue(r5, r4)
                java.lang.String r4 = "User-Agent"
                java.lang.String r5 = r15.getRequestProperty(r4)
                java.lang.String r6 = "connection.getRequestProperty(\"User-Agent\")"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r6)
                r0.appendKeyValue(r4, r5)
                java.lang.String r4 = "Content-Type"
                java.lang.String r5 = r15.getRequestProperty(r4)
                java.lang.String r6 = "connection.getRequestProperty(\"Content-Type\")"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r6)
                r0.appendKeyValue(r4, r5)
                int r4 = r14.getTimeout()
                r15.setConnectTimeout(r4)
                int r4 = r14.getTimeout()
                r15.setReadTimeout(r4)
                com.facebook.HttpMethod r4 = com.facebook.HttpMethod.POST
                if (r3 != r4) goto L_0x00f0
                r15.setDoOutput(r2)
                java.io.BufferedOutputStream r2 = new java.io.BufferedOutputStream     // Catch:{ all -> 0x00d4 }
                java.io.OutputStream r15 = r15.getOutputStream()     // Catch:{ all -> 0x00d4 }
                r2.<init>(r15)     // Catch:{ all -> 0x00d4 }
                if (r11 == 0) goto L_0x00a8
                java.util.zip.GZIPOutputStream r15 = new java.util.zip.GZIPOutputStream     // Catch:{ all -> 0x00a5 }
                r15.<init>(r2)     // Catch:{ all -> 0x00a5 }
                r1 = r15
                goto L_0x00a9
            L_0x00a5:
                r14 = move-exception
                r1 = r2
                goto L_0x00ea
            L_0x00a8:
                r1 = r2
            L_0x00a9:
                boolean r15 = r13.hasOnProgressCallbacks(r14)     // Catch:{ all -> 0x00d4 }
                if (r15 == 0) goto L_0x00d6
                com.facebook.ProgressNoopOutputStream r15 = new com.facebook.ProgressNoopOutputStream     // Catch:{ all -> 0x00d4 }
                android.os.Handler r2 = r14.getCallbackHandler()     // Catch:{ all -> 0x00d4 }
                r15.<init>(r2)     // Catch:{ all -> 0x00d4 }
                r5 = 0
                r3 = r13
                r4 = r14
                r6 = r10
                r7 = r12
                r8 = r15
                r9 = r11
                r3.processRequest(r4, r5, r6, r7, r8, r9)     // Catch:{ all -> 0x00d4 }
                int r2 = r15.getMaxProgress()     // Catch:{ all -> 0x00d4 }
                java.util.Map r6 = r15.getProgressMap()     // Catch:{ all -> 0x00d4 }
                com.facebook.ProgressOutputStream r15 = new com.facebook.ProgressOutputStream     // Catch:{ all -> 0x00d4 }
                long r7 = (long) r2     // Catch:{ all -> 0x00d4 }
                r3 = r15
                r4 = r1
                r5 = r14
                r3.<init>(r4, r5, r6, r7)     // Catch:{ all -> 0x00d4 }
                goto L_0x00d7
            L_0x00d4:
                r14 = move-exception
                goto L_0x00ea
            L_0x00d6:
                r15 = r1
            L_0x00d7:
                r1 = r13
                r2 = r14
                r3 = r0
                r4 = r10
                r5 = r12
                r6 = r15
                r7 = r11
                r1.processRequest(r2, r3, r4, r5, r6, r7)     // Catch:{ all -> 0x00e8 }
                r15.close()
                r0.log()
                return
            L_0x00e8:
                r14 = move-exception
                r1 = r15
            L_0x00ea:
                if (r1 == 0) goto L_0x00ef
                r1.close()
            L_0x00ef:
                throw r14
            L_0x00f0:
                r0.log()
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.GraphRequest.Companion.serializeToUrlConnection$facebook_core_release(com.facebook.GraphRequestBatch, java.net.HttpURLConnection):void");
        }

        private final void processRequest(GraphRequestBatch graphRequestBatch, Logger logger, int i, URL url, OutputStream outputStream, boolean z) {
            Serializer serializer = new Serializer(outputStream, logger, z);
            if (i == 1) {
                GraphRequest graphRequest = graphRequestBatch.get(0);
                HashMap hashMap = new HashMap();
                for (String next : graphRequest.getParameters().keySet()) {
                    Object obj = graphRequest.getParameters().get(next);
                    if (isSupportedAttachmentType(obj)) {
                        Intrinsics.checkNotNullExpressionValue(next, "key");
                        hashMap.put(next, new Attachment(graphRequest, obj));
                    }
                }
                if (logger != null) {
                    logger.append("  Parameters:\n");
                }
                serializeParameters(graphRequest.getParameters(), serializer, graphRequest);
                if (logger != null) {
                    logger.append("  Attachments:\n");
                }
                serializeAttachments(hashMap, serializer);
                JSONObject graphObject = graphRequest.getGraphObject();
                if (graphObject != null) {
                    String path = url.getPath();
                    Intrinsics.checkNotNullExpressionValue(path, "url.path");
                    processGraphObject(graphObject, path, serializer);
                    return;
                }
                return;
            }
            String batchAppId = getBatchAppId(graphRequestBatch);
            if (batchAppId.length() != 0) {
                serializer.writeString("batch_app_id", batchAppId);
                HashMap hashMap2 = new HashMap();
                serializeRequestsAsJSON(serializer, graphRequestBatch, hashMap2);
                if (logger != null) {
                    logger.append("  Attachments:\n");
                }
                serializeAttachments(hashMap2, serializer);
                return;
            }
            throw new FacebookException("App ID was not specified at the request or Settings.");
        }

        private final boolean isMeRequest(String str) {
            Matcher matcher = GraphRequest.versionPattern.matcher(str);
            if (matcher.matches()) {
                str = matcher.group(1);
                Intrinsics.checkNotNullExpressionValue(str, "matcher.group(1)");
            }
            if (StringsKt.startsWith$default(str, "me/", false, 2, (Object) null) || StringsKt.startsWith$default(str, "/me/", false, 2, (Object) null)) {
                return true;
            }
            return false;
        }

        /* access modifiers changed from: private */
        /* JADX WARNING: Removed duplicated region for block: B:12:0x002e  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void processGraphObject(org.json.JSONObject r10, java.lang.String r11, com.facebook.GraphRequest.KeyValueSerializer r12) {
            /*
                r9 = this;
                boolean r0 = r9.isMeRequest(r11)
                r1 = 1
                r2 = 0
                if (r0 == 0) goto L_0x0023
                r7 = 6
                r8 = 0
                java.lang.String r4 = ":"
                r5 = 0
                r6 = 0
                r3 = r11
                int r0 = kotlin.text.StringsKt.indexOf$default((java.lang.CharSequence) r3, (java.lang.String) r4, (int) r5, (boolean) r6, (int) r7, (java.lang.Object) r8)
                java.lang.String r4 = "?"
                int r11 = kotlin.text.StringsKt.indexOf$default((java.lang.CharSequence) r3, (java.lang.String) r4, (int) r5, (boolean) r6, (int) r7, (java.lang.Object) r8)
                r3 = 3
                if (r0 <= r3) goto L_0x0023
                r3 = -1
                if (r11 == r3) goto L_0x0021
                if (r0 >= r11) goto L_0x0023
            L_0x0021:
                r11 = r1
                goto L_0x0024
            L_0x0023:
                r11 = r2
            L_0x0024:
                java.util.Iterator r0 = r10.keys()
            L_0x0028:
                boolean r3 = r0.hasNext()
                if (r3 == 0) goto L_0x0053
                java.lang.Object r3 = r0.next()
                java.lang.String r3 = (java.lang.String) r3
                java.lang.Object r4 = r10.opt(r3)
                if (r11 == 0) goto L_0x0044
                java.lang.String r5 = "image"
                boolean r5 = kotlin.text.StringsKt.equals(r3, r5, r1)
                if (r5 == 0) goto L_0x0044
                r5 = r1
                goto L_0x0045
            L_0x0044:
                r5 = r2
            L_0x0045:
                java.lang.String r6 = "key"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r6)
                java.lang.String r6 = "value"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r6)
                r9.processGraphObjectProperty(r3, r4, r12, r5)
                goto L_0x0028
            L_0x0053:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.GraphRequest.Companion.processGraphObject(org.json.JSONObject, java.lang.String, com.facebook.GraphRequest$KeyValueSerializer):void");
        }

        private final void processGraphObjectProperty(String str, Object obj, KeyValueSerializer keyValueSerializer, boolean z) {
            Class<?> cls = obj.getClass();
            if (JSONObject.class.isAssignableFrom(cls)) {
                Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type org.json.JSONObject");
                JSONObject jSONObject = (JSONObject) obj;
                if (z) {
                    Iterator<String> keys = jSONObject.keys();
                    while (keys.hasNext()) {
                        String next = keys.next();
                        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                        String format = String.format("%s[%s]", Arrays.copyOf(new Object[]{str, next}, 2));
                        Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
                        Object opt = jSONObject.opt(next);
                        Intrinsics.checkNotNullExpressionValue(opt, "jsonObject.opt(propertyName)");
                        processGraphObjectProperty(format, opt, keyValueSerializer, z);
                    }
                } else if (jSONObject.has("id")) {
                    String optString = jSONObject.optString("id");
                    Intrinsics.checkNotNullExpressionValue(optString, "jsonObject.optString(\"id\")");
                    processGraphObjectProperty(str, optString, keyValueSerializer, z);
                } else if (jSONObject.has("url")) {
                    String optString2 = jSONObject.optString("url");
                    Intrinsics.checkNotNullExpressionValue(optString2, "jsonObject.optString(\"url\")");
                    processGraphObjectProperty(str, optString2, keyValueSerializer, z);
                } else if (jSONObject.has("fbsdk:create_object")) {
                    String jSONObject2 = jSONObject.toString();
                    Intrinsics.checkNotNullExpressionValue(jSONObject2, "jsonObject.toString()");
                    processGraphObjectProperty(str, jSONObject2, keyValueSerializer, z);
                }
            } else if (JSONArray.class.isAssignableFrom(cls)) {
                Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type org.json.JSONArray");
                JSONArray jSONArray = (JSONArray) obj;
                int length = jSONArray.length();
                for (int i = 0; i < length; i++) {
                    StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
                    String format2 = String.format(Locale.ROOT, "%s[%d]", Arrays.copyOf(new Object[]{str, Integer.valueOf(i)}, 2));
                    Intrinsics.checkNotNullExpressionValue(format2, "format(locale, format, *args)");
                    Object opt2 = jSONArray.opt(i);
                    Intrinsics.checkNotNullExpressionValue(opt2, "jsonArray.opt(i)");
                    processGraphObjectProperty(format2, opt2, keyValueSerializer, z);
                }
            } else if (String.class.isAssignableFrom(cls) || Number.class.isAssignableFrom(cls) || Boolean.class.isAssignableFrom(cls)) {
                keyValueSerializer.writeString(str, obj.toString());
            } else if (Date.class.isAssignableFrom(cls)) {
                Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type java.util.Date");
                String format3 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.US).format((Date) obj);
                Intrinsics.checkNotNullExpressionValue(format3, "iso8601DateFormat.format(date)");
                keyValueSerializer.writeString(str, format3);
            } else {
                String str2 = GraphRequest.TAG;
                Utility.logd(str2, "The type of property " + str + " in the graph object is unknown. It won't be sent in the request.");
            }
        }

        private final void serializeParameters(Bundle bundle, Serializer serializer, GraphRequest graphRequest) {
            for (String next : bundle.keySet()) {
                Object obj = bundle.get(next);
                if (isSupportedParameterType(obj)) {
                    Intrinsics.checkNotNullExpressionValue(next, "key");
                    serializer.writeObject(next, obj, graphRequest);
                }
            }
        }

        private final void serializeRequestsAsJSON(Serializer serializer, Collection collection, Map map) {
            JSONArray jSONArray = new JSONArray();
            Iterator it = collection.iterator();
            while (it.hasNext()) {
                ((GraphRequest) it.next()).serializeToBatch(jSONArray, map);
            }
            serializer.writeRequestsAsJson("batch", jSONArray, collection);
        }

        private final String getMimeContentType() {
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String format = String.format("multipart/form-data; boundary=%s", Arrays.copyOf(new Object[]{GraphRequest.MIME_BOUNDARY}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
            return format;
        }

        private final String getUserAgent() {
            if (GraphRequest.userAgent == null) {
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String format = String.format("%s.%s", Arrays.copyOf(new Object[]{"FBAndroidSDK", "18.1.3"}, 2));
                Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
                GraphRequest.userAgent = format;
                String customUserAgent = InternalSettings.getCustomUserAgent();
                if (!Utility.isNullOrEmpty(customUserAgent)) {
                    String format2 = String.format(Locale.ROOT, "%s/%s", Arrays.copyOf(new Object[]{GraphRequest.userAgent, customUserAgent}, 2));
                    Intrinsics.checkNotNullExpressionValue(format2, "format(locale, format, *args)");
                    GraphRequest.userAgent = format2;
                }
            }
            return GraphRequest.userAgent;
        }

        private final String getBatchAppId(GraphRequestBatch graphRequestBatch) {
            String batchApplicationId = graphRequestBatch.getBatchApplicationId();
            if (batchApplicationId != null && !graphRequestBatch.isEmpty()) {
                return batchApplicationId;
            }
            Iterator it = graphRequestBatch.iterator();
            while (it.hasNext()) {
                AccessToken accessToken = ((GraphRequest) it.next()).getAccessToken();
                if (accessToken != null) {
                    return accessToken.getApplicationId();
                }
            }
            String access$getDefaultBatchApplicationId$cp = GraphRequest.defaultBatchApplicationId;
            return (access$getDefaultBatchApplicationId$cp == null || access$getDefaultBatchApplicationId$cp.length() <= 0) ? FacebookSdk.getApplicationId() : access$getDefaultBatchApplicationId$cp;
        }

        /* access modifiers changed from: private */
        public final boolean isSupportedAttachmentType(Object obj) {
            return (obj instanceof Bitmap) || (obj instanceof byte[]) || (obj instanceof Uri) || (obj instanceof ParcelFileDescriptor) || (obj instanceof ParcelableResourceWithMimeType);
        }

        /* access modifiers changed from: private */
        public final boolean isSupportedParameterType(Object obj) {
            return (obj instanceof String) || (obj instanceof Boolean) || (obj instanceof Number) || (obj instanceof Date);
        }

        /* access modifiers changed from: private */
        public final String parameterToString(Object obj) {
            if (obj instanceof String) {
                return (String) obj;
            }
            if ((obj instanceof Boolean) || (obj instanceof Number)) {
                return obj.toString();
            }
            if (obj instanceof Date) {
                String format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.US).format((Date) obj);
                Intrinsics.checkNotNullExpressionValue(format, "iso8601DateFormat.format(value)");
                return format;
            }
            throw new IllegalArgumentException("Unsupported parameter type.");
        }

        private final void serializeAttachments(Map map, Serializer serializer) {
            for (Map.Entry entry : map.entrySet()) {
                if (GraphRequest.Companion.isSupportedAttachmentType(((Attachment) entry.getValue()).getValue())) {
                    serializer.writeObject((String) entry.getKey(), ((Attachment) entry.getValue()).getValue(), ((Attachment) entry.getValue()).getRequest());
                }
            }
        }
    }

    static {
        char[] charArray = "-_1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        Intrinsics.checkNotNullExpressionValue(charArray, "this as java.lang.String).toCharArray()");
        StringBuilder sb = new StringBuilder();
        SecureRandom secureRandom = new SecureRandom();
        int nextInt = secureRandom.nextInt(11) + 30;
        for (int i = 0; i < nextInt; i++) {
            sb.append(charArray[secureRandom.nextInt(charArray.length)]);
        }
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "buffer.toString()");
        MIME_BOUNDARY = sb2;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ GraphRequest(com.facebook.AccessToken r6, java.lang.String r7, android.os.Bundle r8, com.facebook.HttpMethod r9, com.facebook.GraphRequest.Callback r10, java.lang.String r11, int r12, kotlin.jvm.internal.DefaultConstructorMarker r13) {
        /*
            r5 = this;
            r13 = r12 & 1
            r0 = 0
            if (r13 == 0) goto L_0x0007
            r13 = r0
            goto L_0x0008
        L_0x0007:
            r13 = r6
        L_0x0008:
            r6 = r12 & 2
            if (r6 == 0) goto L_0x000e
            r1 = r0
            goto L_0x000f
        L_0x000e:
            r1 = r7
        L_0x000f:
            r6 = r12 & 4
            if (r6 == 0) goto L_0x0015
            r2 = r0
            goto L_0x0016
        L_0x0015:
            r2 = r8
        L_0x0016:
            r6 = r12 & 8
            if (r6 == 0) goto L_0x001c
            r3 = r0
            goto L_0x001d
        L_0x001c:
            r3 = r9
        L_0x001d:
            r6 = r12 & 16
            if (r6 == 0) goto L_0x0023
            r4 = r0
            goto L_0x0024
        L_0x0023:
            r4 = r10
        L_0x0024:
            r6 = r12 & 32
            if (r6 == 0) goto L_0x002a
            r12 = r0
            goto L_0x002b
        L_0x002a:
            r12 = r11
        L_0x002b:
            r6 = r5
            r7 = r13
            r8 = r1
            r9 = r2
            r10 = r3
            r11 = r4
            r6.<init>(r7, r8, r9, r10, r11, r12)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.GraphRequest.<init>(com.facebook.AccessToken, java.lang.String, android.os.Bundle, com.facebook.HttpMethod, com.facebook.GraphRequest$Callback, java.lang.String, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public GraphRequest(AccessToken accessToken2, String str, Bundle bundle, HttpMethod httpMethod2, Callback callback2, String str2) {
        this.batchEntryOmitResultOnSuccess = true;
        this.accessToken = accessToken2;
        this.graphPath = str;
        this.version = str2;
        setCallback(callback2);
        setHttpMethod(httpMethod2);
        if (bundle != null) {
            this.parameters = new Bundle(bundle);
        } else {
            this.parameters = new Bundle();
        }
        if (this.version == null) {
            this.version = FacebookSdk.getGraphApiVersion();
        }
    }

    public final void setForceApplicationRequest(boolean z) {
        this.forceApplicationRequest = z;
    }

    public final GraphResponse executeAndWait() {
        return Companion.executeAndWait(this);
    }

    public final GraphRequestAsyncTask executeAsync() {
        return Companion.executeBatchAsync(this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{Request: ");
        sb.append(" accessToken: ");
        Object obj = this.accessToken;
        if (obj == null) {
            obj = "null";
        }
        sb.append(obj);
        sb.append(", graphPath: ");
        sb.append(this.graphPath);
        sb.append(", graphObject: ");
        sb.append(this.graphObject);
        sb.append(", httpMethod: ");
        sb.append(this.httpMethod);
        sb.append(", parameters: ");
        sb.append(this.parameters);
        sb.append("}");
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "StringBuilder()\n        …(\"}\")\n        .toString()");
        return sb2;
    }

    private final void addCommonParameters() {
        Bundle bundle = this.parameters;
        if (shouldForceClientTokenForRequest()) {
            bundle.putString("access_token", getClientTokenForRequest());
        } else {
            String accessTokenToUseForRequest = getAccessTokenToUseForRequest();
            if (accessTokenToUseForRequest != null) {
                bundle.putString("access_token", accessTokenToUseForRequest);
            }
        }
        if (!bundle.containsKey("access_token") && Utility.isNullOrEmpty(FacebookSdk.getClientToken())) {
            Log.w(TAG, "Starting with v13 of the SDK, a client token must be embedded in your client code before making Graph API calls. Visit https://developers.facebook.com/docs/android/getting-started#client-token to learn how to implement this change.");
        }
        bundle.putString("sdk", "android");
        bundle.putString("format", "json");
        if (FacebookSdk.isLoggingBehaviorEnabled(LoggingBehavior.GRAPH_API_DEBUG_INFO)) {
            bundle.putString(LogEvent.LEVEL_DEBUG, LogEvent.LEVEL_INFO);
        } else if (FacebookSdk.isLoggingBehaviorEnabled(LoggingBehavior.GRAPH_API_DEBUG_WARNING)) {
            bundle.putString(LogEvent.LEVEL_DEBUG, "warning");
        }
    }

    private final String getAccessTokenToUseForRequest() {
        AccessToken accessToken2 = this.accessToken;
        if (accessToken2 != null) {
            if (!this.parameters.containsKey("access_token")) {
                String token = accessToken2.getToken();
                Logger.Companion.registerAccessToken(token);
                return token;
            }
        } else if (!this.parameters.containsKey("access_token")) {
            return getClientTokenForRequest();
        }
        return this.parameters.getString("access_token");
    }

    private final String getClientTokenForRequest() {
        String applicationId = FacebookSdk.getApplicationId();
        String clientToken = FacebookSdk.getClientToken();
        if (applicationId.length() <= 0 || clientToken.length() <= 0) {
            Utility.logd(TAG, "Warning: Request without access token missing application ID or client token.");
            return null;
        }
        return applicationId + '|' + clientToken;
    }

    private final String appendParametersToBaseUrl(String str, boolean z) {
        if (!z && this.httpMethod == HttpMethod.POST) {
            return str;
        }
        Uri.Builder buildUpon = Uri.parse(str).buildUpon();
        for (String next : this.parameters.keySet()) {
            Object obj = this.parameters.get(next);
            if (obj == null) {
                obj = "";
            }
            Companion companion = Companion;
            if (companion.isSupportedParameterType(obj)) {
                buildUpon.appendQueryParameter(next, companion.parameterToString(obj).toString());
            } else if (this.httpMethod != HttpMethod.GET) {
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String format = String.format(Locale.US, "Unsupported parameter type for GET request: %s", Arrays.copyOf(new Object[]{obj.getClass().getSimpleName()}, 1));
                Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
                throw new IllegalArgumentException(format);
            }
        }
        String builder = buildUpon.toString();
        Intrinsics.checkNotNullExpressionValue(builder, "uriBuilder.toString()");
        return builder;
    }

    public final String getRelativeUrlForBatchedRequest() {
        if (this.overriddenURL == null) {
            String urlWithGraphPath = getUrlWithGraphPath(ServerProtocol.getGraphUrlBase());
            addCommonParameters();
            Uri parse = Uri.parse(appendParametersToBaseUrl(urlWithGraphPath, true));
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String format = String.format("%s?%s", Arrays.copyOf(new Object[]{parse.getPath(), parse.getQuery()}, 2));
            Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
            return format;
        }
        throw new FacebookException("Can't override URL for a batch request");
    }

    public final String getUrlForSingleRequest() {
        String str;
        String str2 = this.overriddenURL;
        if (str2 != null) {
            return String.valueOf(str2);
        }
        String str3 = this.graphPath;
        if (this.httpMethod != HttpMethod.POST || str3 == null || !StringsKt.endsWith$default(str3, "/videos", false, 2, (Object) null)) {
            str = ServerProtocol.getGraphUrlBaseForSubdomain(FacebookSdk.getGraphDomain());
        } else {
            str = ServerProtocol.getGraphVideoUrlBase();
        }
        String urlWithGraphPath = getUrlWithGraphPath(str);
        addCommonParameters();
        return appendParametersToBaseUrl(urlWithGraphPath, false);
    }

    private final String getGraphPathWithVersion() {
        if (versionPattern.matcher(this.graphPath).matches()) {
            return this.graphPath;
        }
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format("%s/%s", Arrays.copyOf(new Object[]{this.version, this.graphPath}, 2));
        Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
        return format;
    }

    private final String getUrlWithGraphPath(String str) {
        if (!isValidGraphRequestForDomain()) {
            str = ServerProtocol.getFacebookGraphUrlBase();
        }
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format("%s/%s", Arrays.copyOf(new Object[]{str, getGraphPathWithVersion()}, 2));
        Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
        return format;
    }

    private final boolean shouldForceClientTokenForRequest() {
        String accessTokenToUseForRequest = getAccessTokenToUseForRequest();
        boolean contains$default = accessTokenToUseForRequest != null ? StringsKt.contains$default((CharSequence) accessTokenToUseForRequest, (CharSequence) "|", false, 2, (Object) null) : false;
        if (accessTokenToUseForRequest != null && StringsKt.startsWith$default(accessTokenToUseForRequest, "IG", false, 2, (Object) null) && !contains$default && isApplicationRequest()) {
            return true;
        }
        if (isValidGraphRequestForDomain() || contains$default) {
            return false;
        }
        return true;
    }

    private final boolean isValidGraphRequestForDomain() {
        if (!Intrinsics.areEqual((Object) FacebookSdk.getGraphDomain(), (Object) "instagram.com")) {
            return true;
        }
        return !isApplicationRequest();
    }

    private final boolean isApplicationRequest() {
        if (this.graphPath == null) {
            return false;
        }
        String str = "^/?" + FacebookSdk.getApplicationId() + "/?.*";
        if (this.forceApplicationRequest || Pattern.matches(str, this.graphPath) || Pattern.matches("^/?app/?.*", this.graphPath)) {
            return true;
        }
        return false;
    }

    private static final class Attachment {
        private final GraphRequest request;
        private final Object value;

        public Attachment(GraphRequest graphRequest, Object obj) {
            Intrinsics.checkNotNullParameter(graphRequest, "request");
            this.request = graphRequest;
            this.value = obj;
        }

        public final GraphRequest getRequest() {
            return this.request;
        }

        public final Object getValue() {
            return this.value;
        }
    }

    /* access modifiers changed from: private */
    public final void serializeToBatch(JSONArray jSONArray, Map map) {
        JSONObject jSONObject = new JSONObject();
        String str = this.batchEntryName;
        if (str != null) {
            jSONObject.put("name", str);
            jSONObject.put("omit_response_on_success", this.batchEntryOmitResultOnSuccess);
        }
        String str2 = this.batchEntryDependsOn;
        if (str2 != null) {
            jSONObject.put("depends_on", str2);
        }
        String relativeUrlForBatchedRequest = getRelativeUrlForBatchedRequest();
        jSONObject.put("relative_url", relativeUrlForBatchedRequest);
        jSONObject.put(FirebaseAnalytics.Param.METHOD, this.httpMethod);
        AccessToken accessToken2 = this.accessToken;
        if (accessToken2 != null) {
            Logger.Companion.registerAccessToken(accessToken2.getToken());
        }
        ArrayList arrayList = new ArrayList();
        for (String str3 : this.parameters.keySet()) {
            Object obj = this.parameters.get(str3);
            if (Companion.isSupportedAttachmentType(obj)) {
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String format = String.format(Locale.ROOT, "%s%d", Arrays.copyOf(new Object[]{"file", Integer.valueOf(map.size())}, 2));
                Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
                arrayList.add(format);
                map.put(format, new Attachment(this, obj));
            }
        }
        if (!arrayList.isEmpty()) {
            jSONObject.put("attached_files", TextUtils.join(",", arrayList));
        }
        JSONObject jSONObject2 = this.graphObject;
        if (jSONObject2 != null) {
            ArrayList arrayList2 = new ArrayList();
            Companion.processGraphObject(jSONObject2, relativeUrlForBatchedRequest, new GraphRequest$serializeToBatch$1(arrayList2));
            jSONObject.put("body", TextUtils.join("&", arrayList2));
        }
        jSONArray.put(jSONObject);
    }

    private static final class Serializer implements KeyValueSerializer {
        private boolean firstWrite = true;
        private final Logger logger;
        private final OutputStream outputStream;
        private final boolean useUrlEncode;

        public Serializer(OutputStream outputStream2, Logger logger2, boolean z) {
            Intrinsics.checkNotNullParameter(outputStream2, "outputStream");
            this.outputStream = outputStream2;
            this.logger = logger2;
            this.useUrlEncode = z;
        }

        public final void writeObject(String str, Object obj, GraphRequest graphRequest) {
            Intrinsics.checkNotNullParameter(str, "key");
            OutputStream outputStream2 = this.outputStream;
            if (outputStream2 instanceof RequestOutputStream) {
                Intrinsics.checkNotNull(outputStream2, "null cannot be cast to non-null type com.facebook.RequestOutputStream");
                ((RequestOutputStream) outputStream2).setCurrentRequest(graphRequest);
            }
            Companion companion = GraphRequest.Companion;
            if (companion.isSupportedParameterType(obj)) {
                writeString(str, companion.parameterToString(obj));
            } else if (obj instanceof Bitmap) {
                writeBitmap(str, (Bitmap) obj);
            } else if (obj instanceof byte[]) {
                writeBytes(str, (byte[]) obj);
            } else if (obj instanceof Uri) {
                writeContentUri(str, (Uri) obj, (String) null);
            } else if (obj instanceof ParcelFileDescriptor) {
                writeFile(str, (ParcelFileDescriptor) obj, (String) null);
            } else if (obj instanceof ParcelableResourceWithMimeType) {
                ParcelableResourceWithMimeType parcelableResourceWithMimeType = (ParcelableResourceWithMimeType) obj;
                Parcelable resource = parcelableResourceWithMimeType.getResource();
                String mimeType = parcelableResourceWithMimeType.getMimeType();
                if (resource instanceof ParcelFileDescriptor) {
                    writeFile(str, (ParcelFileDescriptor) resource, mimeType);
                } else if (resource instanceof Uri) {
                    writeContentUri(str, (Uri) resource, mimeType);
                } else {
                    throw getInvalidTypeError();
                }
            } else {
                throw getInvalidTypeError();
            }
        }

        private final RuntimeException getInvalidTypeError() {
            return new IllegalArgumentException("value is not a supported type.");
        }

        public final void writeRequestsAsJson(String str, JSONArray jSONArray, Collection collection) {
            Intrinsics.checkNotNullParameter(str, "key");
            Intrinsics.checkNotNullParameter(jSONArray, "requestJsonArray");
            Intrinsics.checkNotNullParameter(collection, "requests");
            OutputStream outputStream2 = this.outputStream;
            if (!(outputStream2 instanceof RequestOutputStream)) {
                String jSONArray2 = jSONArray.toString();
                Intrinsics.checkNotNullExpressionValue(jSONArray2, "requestJsonArray.toString()");
                writeString(str, jSONArray2);
                return;
            }
            Intrinsics.checkNotNull(outputStream2, "null cannot be cast to non-null type com.facebook.RequestOutputStream");
            RequestOutputStream requestOutputStream = (RequestOutputStream) outputStream2;
            writeContentDisposition(str, (String) null, (String) null);
            write("[", new Object[0]);
            Iterator it = collection.iterator();
            int i = 0;
            while (it.hasNext()) {
                int i2 = i + 1;
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                requestOutputStream.setCurrentRequest((GraphRequest) it.next());
                if (i > 0) {
                    write(",%s", jSONObject.toString());
                } else {
                    write("%s", jSONObject.toString());
                }
                i = i2;
            }
            write("]", new Object[0]);
            Logger logger2 = this.logger;
            if (logger2 != null) {
                String jSONArray3 = jSONArray.toString();
                Intrinsics.checkNotNullExpressionValue(jSONArray3, "requestJsonArray.toString()");
                logger2.appendKeyValue("    " + str, jSONArray3);
            }
        }

        public void writeString(String str, String str2) {
            Intrinsics.checkNotNullParameter(str, "key");
            Intrinsics.checkNotNullParameter(str2, "value");
            writeContentDisposition(str, (String) null, (String) null);
            writeLine("%s", str2);
            writeRecordBoundary();
            Logger logger2 = this.logger;
            if (logger2 != null) {
                logger2.appendKeyValue("    " + str, str2);
            }
        }

        public final void writeBitmap(String str, Bitmap bitmap) {
            Intrinsics.checkNotNullParameter(str, "key");
            Intrinsics.checkNotNullParameter(bitmap, "bitmap");
            writeContentDisposition(str, str, ClipboardModule.MIMETYPE_PNG);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, this.outputStream);
            writeLine("", new Object[0]);
            writeRecordBoundary();
            Logger logger2 = this.logger;
            if (logger2 != null) {
                logger2.appendKeyValue("    " + str, "<Image>");
            }
        }

        public final void writeBytes(String str, byte[] bArr) {
            Intrinsics.checkNotNullParameter(str, "key");
            Intrinsics.checkNotNullParameter(bArr, "bytes");
            writeContentDisposition(str, str, "content/unknown");
            this.outputStream.write(bArr);
            writeLine("", new Object[0]);
            writeRecordBoundary();
            Logger logger2 = this.logger;
            if (logger2 != null) {
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String format = String.format(Locale.ROOT, "<Data: %d>", Arrays.copyOf(new Object[]{Integer.valueOf(bArr.length)}, 1));
                Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
                logger2.appendKeyValue("    " + str, format);
            }
        }

        public final void writeContentUri(String str, Uri uri, String str2) {
            int i;
            Intrinsics.checkNotNullParameter(str, "key");
            Intrinsics.checkNotNullParameter(uri, "contentUri");
            if (str2 == null) {
                str2 = "content/unknown";
            }
            writeContentDisposition(str, str, str2);
            if (this.outputStream instanceof ProgressNoopOutputStream) {
                ((ProgressNoopOutputStream) this.outputStream).addProgress(Utility.getContentSize(uri));
                i = 0;
            } else {
                i = Utility.copyAndCloseInputStream(FacebookSdk.getApplicationContext().getContentResolver().openInputStream(uri), this.outputStream);
            }
            writeLine("", new Object[0]);
            writeRecordBoundary();
            Logger logger2 = this.logger;
            if (logger2 != null) {
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String format = String.format(Locale.ROOT, "<Data: %d>", Arrays.copyOf(new Object[]{Integer.valueOf(i)}, 1));
                Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
                logger2.appendKeyValue("    " + str, format);
            }
        }

        public final void writeFile(String str, ParcelFileDescriptor parcelFileDescriptor, String str2) {
            int i;
            Intrinsics.checkNotNullParameter(str, "key");
            Intrinsics.checkNotNullParameter(parcelFileDescriptor, "descriptor");
            if (str2 == null) {
                str2 = "content/unknown";
            }
            writeContentDisposition(str, str, str2);
            OutputStream outputStream2 = this.outputStream;
            if (outputStream2 instanceof ProgressNoopOutputStream) {
                ((ProgressNoopOutputStream) outputStream2).addProgress(parcelFileDescriptor.getStatSize());
                i = 0;
            } else {
                i = Utility.copyAndCloseInputStream(new ParcelFileDescriptor.AutoCloseInputStream(parcelFileDescriptor), this.outputStream);
            }
            writeLine("", new Object[0]);
            writeRecordBoundary();
            Logger logger2 = this.logger;
            if (logger2 != null) {
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String format = String.format(Locale.ROOT, "<Data: %d>", Arrays.copyOf(new Object[]{Integer.valueOf(i)}, 1));
                Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
                logger2.appendKeyValue("    " + str, format);
            }
        }

        public final void writeRecordBoundary() {
            if (!this.useUrlEncode) {
                writeLine("--%s", GraphRequest.MIME_BOUNDARY);
                return;
            }
            OutputStream outputStream2 = this.outputStream;
            byte[] bytes = "&".getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
            outputStream2.write(bytes);
        }

        public final void writeContentDisposition(String str, String str2, String str3) {
            if (!this.useUrlEncode) {
                write("Content-Disposition: form-data; name=\"%s\"", str);
                if (str2 != null) {
                    write("; filename=\"%s\"", str2);
                }
                writeLine("", new Object[0]);
                if (str3 != null) {
                    writeLine("%s: %s", "Content-Type", str3);
                }
                writeLine("", new Object[0]);
                return;
            }
            OutputStream outputStream2 = this.outputStream;
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String format = String.format("%s=", Arrays.copyOf(new Object[]{str}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
            byte[] bytes = format.getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
            outputStream2.write(bytes);
        }

        public final void write(String str, Object... objArr) {
            Intrinsics.checkNotNullParameter(str, "format");
            Intrinsics.checkNotNullParameter(objArr, "args");
            if (!this.useUrlEncode) {
                if (this.firstWrite) {
                    OutputStream outputStream2 = this.outputStream;
                    Charset charset = Charsets.UTF_8;
                    byte[] bytes = "--".getBytes(charset);
                    Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
                    outputStream2.write(bytes);
                    OutputStream outputStream3 = this.outputStream;
                    byte[] bytes2 = GraphRequest.MIME_BOUNDARY.getBytes(charset);
                    Intrinsics.checkNotNullExpressionValue(bytes2, "this as java.lang.String).getBytes(charset)");
                    outputStream3.write(bytes2);
                    OutputStream outputStream4 = this.outputStream;
                    byte[] bytes3 = "\r\n".getBytes(charset);
                    Intrinsics.checkNotNullExpressionValue(bytes3, "this as java.lang.String).getBytes(charset)");
                    outputStream4.write(bytes3);
                    this.firstWrite = false;
                }
                OutputStream outputStream5 = this.outputStream;
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                Object[] copyOf = Arrays.copyOf(objArr, objArr.length);
                String format = String.format(str, Arrays.copyOf(copyOf, copyOf.length));
                Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
                byte[] bytes4 = format.getBytes(Charsets.UTF_8);
                Intrinsics.checkNotNullExpressionValue(bytes4, "this as java.lang.String).getBytes(charset)");
                outputStream5.write(bytes4);
                return;
            }
            OutputStream outputStream6 = this.outputStream;
            StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
            Locale locale = Locale.US;
            Object[] copyOf2 = Arrays.copyOf(objArr, objArr.length);
            String format2 = String.format(locale, str, Arrays.copyOf(copyOf2, copyOf2.length));
            Intrinsics.checkNotNullExpressionValue(format2, "format(locale, format, *args)");
            String encode = URLEncoder.encode(format2, "UTF-8");
            Intrinsics.checkNotNullExpressionValue(encode, "encode(String.format(Loc… format, *args), \"UTF-8\")");
            byte[] bytes5 = encode.getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes5, "this as java.lang.String).getBytes(charset)");
            outputStream6.write(bytes5);
        }

        public final void writeLine(String str, Object... objArr) {
            Intrinsics.checkNotNullParameter(str, "format");
            Intrinsics.checkNotNullParameter(objArr, "args");
            write(str, Arrays.copyOf(objArr, objArr.length));
            if (!this.useUrlEncode) {
                write("\r\n", new Object[0]);
            }
        }
    }

    public static final class ParcelableResourceWithMimeType implements Parcelable {
        public static final Parcelable.Creator<ParcelableResourceWithMimeType> CREATOR = new GraphRequest$ParcelableResourceWithMimeType$Companion$CREATOR$1();
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        private final String mimeType;
        private final Parcelable resource;

        public /* synthetic */ ParcelableResourceWithMimeType(Parcel parcel, DefaultConstructorMarker defaultConstructorMarker) {
            this(parcel);
        }

        public int describeContents() {
            return 1;
        }

        public final String getMimeType() {
            return this.mimeType;
        }

        public final Parcelable getResource() {
            return this.resource;
        }

        public void writeToParcel(Parcel parcel, int i) {
            Intrinsics.checkNotNullParameter(parcel, "out");
            parcel.writeString(this.mimeType);
            parcel.writeParcelable(this.resource, i);
        }

        public ParcelableResourceWithMimeType(Parcelable parcelable, String str) {
            this.mimeType = str;
            this.resource = parcelable;
        }

        private ParcelableResourceWithMimeType(Parcel parcel) {
            this.mimeType = parcel.readString();
            this.resource = parcel.readParcelable(FacebookSdk.getApplicationContext().getClassLoader());
        }

        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }
        }
    }
}
