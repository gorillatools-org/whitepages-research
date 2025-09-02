package com.facebook;

import android.util.Log;
import com.facebook.AccessToken;
import com.facebook.internal.Logger;
import com.facebook.internal.Utility;
import com.salesforce.marketingcloud.messages.iam.j;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public final class GraphResponse {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final String TAG = GraphResponse.class.getCanonicalName();
    private final HttpURLConnection connection;
    private final FacebookRequestError error;
    private final JSONObject graphObject;
    private final JSONArray graphObjectArray;
    private final JSONArray jsonArray;
    private final JSONObject jsonObject;
    private final String rawResponse;
    private final GraphRequest request;

    public GraphResponse(GraphRequest graphRequest, HttpURLConnection httpURLConnection, String str, JSONObject jSONObject, JSONArray jSONArray, FacebookRequestError facebookRequestError) {
        Intrinsics.checkNotNullParameter(graphRequest, "request");
        this.request = graphRequest;
        this.connection = httpURLConnection;
        this.rawResponse = str;
        this.graphObject = jSONObject;
        this.graphObjectArray = jSONArray;
        this.error = facebookRequestError;
        this.jsonObject = jSONObject;
        this.jsonArray = jSONArray;
    }

    public final FacebookRequestError getError() {
        return this.error;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public GraphResponse(GraphRequest graphRequest, HttpURLConnection httpURLConnection, String str, JSONObject jSONObject) {
        this(graphRequest, httpURLConnection, str, jSONObject, (JSONArray) null, (FacebookRequestError) null);
        Intrinsics.checkNotNullParameter(graphRequest, "request");
        Intrinsics.checkNotNullParameter(str, "rawResponse");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public GraphResponse(GraphRequest graphRequest, HttpURLConnection httpURLConnection, String str, JSONArray jSONArray) {
        this(graphRequest, httpURLConnection, str, (JSONObject) null, jSONArray, (FacebookRequestError) null);
        Intrinsics.checkNotNullParameter(graphRequest, "request");
        Intrinsics.checkNotNullParameter(str, "rawResponse");
        Intrinsics.checkNotNullParameter(jSONArray, "graphObjects");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public GraphResponse(GraphRequest graphRequest, HttpURLConnection httpURLConnection, FacebookRequestError facebookRequestError) {
        this(graphRequest, httpURLConnection, (String) null, (JSONObject) null, (JSONArray) null, facebookRequestError);
        Intrinsics.checkNotNullParameter(graphRequest, "request");
        Intrinsics.checkNotNullParameter(facebookRequestError, "error");
    }

    public final JSONObject getJSONObject() {
        return this.graphObject;
    }

    public final JSONObject getJsonObject() {
        return this.jsonObject;
    }

    public String toString() {
        String str;
        try {
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            Locale locale = Locale.US;
            HttpURLConnection httpURLConnection = this.connection;
            str = String.format(locale, "%d", Arrays.copyOf(new Object[]{Integer.valueOf(httpURLConnection != null ? httpURLConnection.getResponseCode() : 200)}, 1));
            Intrinsics.checkNotNullExpressionValue(str, "format(locale, format, *args)");
        } catch (IOException unused) {
            str = j.h;
        }
        String str2 = "{Response: " + " responseCode: " + str + ", graphObject: " + this.graphObject + ", error: " + this.error + "}";
        Intrinsics.checkNotNullExpressionValue(str2, "StringBuilder()\n        …(\"}\")\n        .toString()");
        return str2;
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final List fromHttpConnection$facebook_core_release(HttpURLConnection httpURLConnection, GraphRequestBatch graphRequestBatch) {
            List list;
            Intrinsics.checkNotNullParameter(httpURLConnection, "connection");
            Intrinsics.checkNotNullParameter(graphRequestBatch, "requests");
            InputStream inputStream = null;
            try {
                if (FacebookSdk.isFullyInitialized()) {
                    if (httpURLConnection.getResponseCode() >= 400) {
                        inputStream = httpURLConnection.getErrorStream();
                    } else {
                        inputStream = httpURLConnection.getInputStream();
                    }
                    list = createResponsesFromStream$facebook_core_release(inputStream, httpURLConnection, graphRequestBatch);
                    Utility.closeQuietly(inputStream);
                    return list;
                }
                Log.e(GraphResponse.TAG, "GraphRequest can't be used when Facebook SDK isn't fully initialized");
                throw new FacebookException("GraphRequest can't be used when Facebook SDK isn't fully initialized");
            } catch (FacebookException e) {
                Logger.Companion.log(LoggingBehavior.REQUESTS, "Response", "Response <Error>: %s", e);
                list = constructErrorResponses(graphRequestBatch, httpURLConnection, e);
            } catch (Exception e2) {
                Logger.Companion.log(LoggingBehavior.REQUESTS, "Response", "Response <Error>: %s", e2);
                list = constructErrorResponses(graphRequestBatch, httpURLConnection, new FacebookException((Throwable) e2));
            } catch (Throwable th) {
                Utility.closeQuietly((Closeable) null);
                throw th;
            }
        }

        public final List createResponsesFromStream$facebook_core_release(InputStream inputStream, HttpURLConnection httpURLConnection, GraphRequestBatch graphRequestBatch) {
            Intrinsics.checkNotNullParameter(graphRequestBatch, "requests");
            String readStreamToString = Utility.readStreamToString(inputStream);
            Logger.Companion.log(LoggingBehavior.INCLUDE_RAW_RESPONSES, "Response", "Response (raw)\n  Size: %d\n  Response:\n%s\n", Integer.valueOf(readStreamToString.length()), readStreamToString);
            return createResponsesFromString$facebook_core_release(readStreamToString, httpURLConnection, graphRequestBatch);
        }

        public final List createResponsesFromString$facebook_core_release(String str, HttpURLConnection httpURLConnection, GraphRequestBatch graphRequestBatch) {
            Intrinsics.checkNotNullParameter(str, "responseString");
            Intrinsics.checkNotNullParameter(graphRequestBatch, "requests");
            Object nextValue = new JSONTokener(str).nextValue();
            Intrinsics.checkNotNullExpressionValue(nextValue, "resultObject");
            List createResponsesFromObject = createResponsesFromObject(httpURLConnection, graphRequestBatch, nextValue);
            Logger.Companion.log(LoggingBehavior.REQUESTS, "Response", "Response\n  Id: %s\n  Size: %d\n  Responses:\n%s\n", graphRequestBatch.getId(), Integer.valueOf(str.length()), createResponsesFromObject);
            return createResponsesFromObject;
        }

        /* JADX WARNING: Removed duplicated region for block: B:18:0x0058  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private final java.util.List createResponsesFromObject(java.net.HttpURLConnection r9, java.util.List r10, java.lang.Object r11) {
            /*
                r8 = this;
                int r0 = r10.size()
                java.util.ArrayList r1 = new java.util.ArrayList
                r1.<init>(r0)
                r2 = 1
                r3 = 0
                if (r0 != r2) goto L_0x0053
                java.lang.Object r2 = r10.get(r3)
                com.facebook.GraphRequest r2 = (com.facebook.GraphRequest) r2
                org.json.JSONObject r4 = new org.json.JSONObject     // Catch:{ JSONException -> 0x0026, IOException -> 0x0024 }
                r4.<init>()     // Catch:{ JSONException -> 0x0026, IOException -> 0x0024 }
                java.lang.String r5 = "body"
                r4.put(r5, r11)     // Catch:{ JSONException -> 0x0026, IOException -> 0x0024 }
                if (r9 == 0) goto L_0x0028
                int r5 = r9.getResponseCode()     // Catch:{ JSONException -> 0x0026, IOException -> 0x0024 }
                goto L_0x002a
            L_0x0024:
                r4 = move-exception
                goto L_0x0038
            L_0x0026:
                r4 = move-exception
                goto L_0x0046
            L_0x0028:
                r5 = 200(0xc8, float:2.8E-43)
            L_0x002a:
                java.lang.String r6 = "code"
                r4.put(r6, r5)     // Catch:{ JSONException -> 0x0026, IOException -> 0x0024 }
                org.json.JSONArray r5 = new org.json.JSONArray     // Catch:{ JSONException -> 0x0026, IOException -> 0x0024 }
                r5.<init>()     // Catch:{ JSONException -> 0x0026, IOException -> 0x0024 }
                r5.put(r4)     // Catch:{ JSONException -> 0x0026, IOException -> 0x0024 }
                goto L_0x0054
            L_0x0038:
                com.facebook.GraphResponse r5 = new com.facebook.GraphResponse
                com.facebook.FacebookRequestError r6 = new com.facebook.FacebookRequestError
                r6.<init>((java.net.HttpURLConnection) r9, (java.lang.Exception) r4)
                r5.<init>(r2, r9, r6)
                r1.add(r5)
                goto L_0x0053
            L_0x0046:
                com.facebook.GraphResponse r5 = new com.facebook.GraphResponse
                com.facebook.FacebookRequestError r6 = new com.facebook.FacebookRequestError
                r6.<init>((java.net.HttpURLConnection) r9, (java.lang.Exception) r4)
                r5.<init>(r2, r9, r6)
                r1.add(r5)
            L_0x0053:
                r5 = r11
            L_0x0054:
                boolean r2 = r5 instanceof org.json.JSONArray
                if (r2 == 0) goto L_0x00a4
                r2 = r5
                org.json.JSONArray r2 = (org.json.JSONArray) r2
                int r4 = r2.length()
                if (r4 != r0) goto L_0x00a4
                int r0 = r2.length()
            L_0x0065:
                if (r3 >= r0) goto L_0x00a3
                java.lang.Object r2 = r10.get(r3)
                com.facebook.GraphRequest r2 = (com.facebook.GraphRequest) r2
                r4 = r5
                org.json.JSONArray r4 = (org.json.JSONArray) r4     // Catch:{ JSONException -> 0x0083, FacebookException -> 0x0081 }
                java.lang.Object r4 = r4.get(r3)     // Catch:{ JSONException -> 0x0083, FacebookException -> 0x0081 }
                java.lang.String r6 = "obj"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r6)     // Catch:{ JSONException -> 0x0083, FacebookException -> 0x0081 }
                com.facebook.GraphResponse r4 = r8.createResponseFromObject(r2, r9, r4, r11)     // Catch:{ JSONException -> 0x0083, FacebookException -> 0x0081 }
                r1.add(r4)     // Catch:{ JSONException -> 0x0083, FacebookException -> 0x0081 }
                goto L_0x00a0
            L_0x0081:
                r4 = move-exception
                goto L_0x0085
            L_0x0083:
                r4 = move-exception
                goto L_0x0093
            L_0x0085:
                com.facebook.GraphResponse r6 = new com.facebook.GraphResponse
                com.facebook.FacebookRequestError r7 = new com.facebook.FacebookRequestError
                r7.<init>((java.net.HttpURLConnection) r9, (java.lang.Exception) r4)
                r6.<init>(r2, r9, r7)
                r1.add(r6)
                goto L_0x00a0
            L_0x0093:
                com.facebook.GraphResponse r6 = new com.facebook.GraphResponse
                com.facebook.FacebookRequestError r7 = new com.facebook.FacebookRequestError
                r7.<init>((java.net.HttpURLConnection) r9, (java.lang.Exception) r4)
                r6.<init>(r2, r9, r7)
                r1.add(r6)
            L_0x00a0:
                int r3 = r3 + 1
                goto L_0x0065
            L_0x00a3:
                return r1
            L_0x00a4:
                com.facebook.FacebookException r9 = new com.facebook.FacebookException
                java.lang.String r10 = "Unexpected number of results"
                r9.<init>((java.lang.String) r10)
                throw r9
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.GraphResponse.Companion.createResponsesFromObject(java.net.HttpURLConnection, java.util.List, java.lang.Object):java.util.List");
        }

        private final GraphResponse createResponseFromObject(GraphRequest graphRequest, HttpURLConnection httpURLConnection, Object obj, Object obj2) {
            if (obj instanceof JSONObject) {
                JSONObject jSONObject = (JSONObject) obj;
                FacebookRequestError checkResponseAndCreateError = FacebookRequestError.Companion.checkResponseAndCreateError(jSONObject, obj2, httpURLConnection);
                if (checkResponseAndCreateError != null) {
                    Log.e(GraphResponse.TAG, checkResponseAndCreateError.toString());
                    if (checkResponseAndCreateError.getErrorCode() == 190 && Utility.isCurrentAccessToken(graphRequest.getAccessToken())) {
                        if (checkResponseAndCreateError.getSubErrorCode() != 493) {
                            AccessToken.Companion.setCurrentAccessToken((AccessToken) null);
                        } else {
                            AccessToken.Companion companion = AccessToken.Companion;
                            AccessToken currentAccessToken = companion.getCurrentAccessToken();
                            if (currentAccessToken != null && !currentAccessToken.isExpired()) {
                                companion.expireCurrentAccessToken();
                            }
                        }
                    }
                    return new GraphResponse(graphRequest, httpURLConnection, checkResponseAndCreateError);
                }
                Object stringPropertyAsJSON = Utility.getStringPropertyAsJSON(jSONObject, "body", "FACEBOOK_NON_JSON_RESULT");
                if (stringPropertyAsJSON instanceof JSONObject) {
                    JSONObject jSONObject2 = (JSONObject) stringPropertyAsJSON;
                    return new GraphResponse(graphRequest, httpURLConnection, jSONObject2.toString(), jSONObject2);
                } else if (stringPropertyAsJSON instanceof JSONArray) {
                    JSONArray jSONArray = (JSONArray) stringPropertyAsJSON;
                    return new GraphResponse(graphRequest, httpURLConnection, jSONArray.toString(), jSONArray);
                } else {
                    obj = JSONObject.NULL;
                    Intrinsics.checkNotNullExpressionValue(obj, "NULL");
                }
            }
            if (obj == JSONObject.NULL) {
                return new GraphResponse(graphRequest, httpURLConnection, obj.toString(), (JSONObject) null);
            }
            throw new FacebookException("Got unexpected object type in response, class: " + obj.getClass().getSimpleName());
        }

        public final List constructErrorResponses(List list, HttpURLConnection httpURLConnection, FacebookException facebookException) {
            Intrinsics.checkNotNullParameter(list, "requests");
            Iterable<GraphRequest> iterable = list;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
            for (GraphRequest graphResponse : iterable) {
                arrayList.add(new GraphResponse(graphResponse, httpURLConnection, new FacebookRequestError(httpURLConnection, (Exception) facebookException)));
            }
            return arrayList;
        }
    }
}
