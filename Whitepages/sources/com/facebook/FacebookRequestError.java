package com.facebook;

import android.os.Parcel;
import android.os.Parcelable;
import com.facebook.internal.FacebookRequestErrorClassification;
import com.facebook.internal.FetchedAppSettings;
import com.facebook.internal.FetchedAppSettingsManager;
import java.net.HttpURLConnection;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

public final class FacebookRequestError implements Parcelable {
    public static final Parcelable.Creator<FacebookRequestError> CREATOR = new FacebookRequestError$Companion$CREATOR$1();
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final Range HTTP_RANGE_SUCCESS = new Range(200, 299);
    private final Object batchRequestResult;
    private final Category category;
    private final HttpURLConnection connection;
    private final int errorCode;
    private final String errorMessage;
    private final String errorRecoveryMessage;
    private final String errorType;
    private final String errorUserMessage;
    private final String errorUserTitle;
    private FacebookException exception;
    private final JSONObject requestResult;
    private final JSONObject requestResultBody;
    private final int requestStatusCode;
    private final int subErrorCode;

    public enum Category {
        LOGIN_RECOVERABLE,
        OTHER,
        TRANSIENT
    }

    public /* synthetic */ FacebookRequestError(int i, int i2, int i3, String str, String str2, String str3, String str4, JSONObject jSONObject, JSONObject jSONObject2, Object obj, HttpURLConnection httpURLConnection, FacebookException facebookException, boolean z, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, i2, i3, str, str2, str3, str4, jSONObject, jSONObject2, obj, httpURLConnection, facebookException, z);
    }

    public /* synthetic */ FacebookRequestError(Parcel parcel, DefaultConstructorMarker defaultConstructorMarker) {
        this(parcel);
    }

    public int describeContents() {
        return 0;
    }

    private FacebookRequestError(int i, int i2, int i3, String str, String str2, String str3, String str4, JSONObject jSONObject, JSONObject jSONObject2, Object obj, HttpURLConnection httpURLConnection, FacebookException facebookException, boolean z) {
        Category category2;
        this.requestStatusCode = i;
        this.errorCode = i2;
        this.subErrorCode = i3;
        this.errorType = str;
        this.errorUserTitle = str3;
        this.errorUserMessage = str4;
        this.requestResultBody = jSONObject;
        this.requestResult = jSONObject2;
        this.batchRequestResult = obj;
        this.connection = httpURLConnection;
        this.errorMessage = str2;
        if (facebookException != null) {
            this.exception = facebookException;
            category2 = Category.OTHER;
        } else {
            this.exception = new FacebookServiceException(this, getErrorMessage());
            category2 = Companion.getErrorClassification().classify(i2, i3, z);
        }
        this.category = category2;
        this.errorRecoveryMessage = Companion.getErrorClassification().getRecoveryMessage(category2);
    }

    public final int getRequestStatusCode() {
        return this.requestStatusCode;
    }

    public final int getErrorCode() {
        return this.errorCode;
    }

    public final int getSubErrorCode() {
        return this.subErrorCode;
    }

    public final String getErrorType() {
        return this.errorType;
    }

    public static final class Range {
        private final int end;
        private final int start;

        public Range(int i, int i2) {
            this.start = i;
            this.end = i2;
        }

        public final boolean contains(int i) {
            return i <= this.end && this.start <= i;
        }
    }

    public final String getErrorMessage() {
        String str = this.errorMessage;
        if (str != null) {
            return str;
        }
        FacebookException facebookException = this.exception;
        if (facebookException != null) {
            return facebookException.getLocalizedMessage();
        }
        return null;
    }

    public final FacebookException getException() {
        return this.exception;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public FacebookRequestError(java.net.HttpURLConnection r17, java.lang.Exception r18) {
        /*
            r16 = this;
            r0 = r18
            boolean r1 = r0 instanceof com.facebook.FacebookException
            if (r1 == 0) goto L_0x000a
            com.facebook.FacebookException r0 = (com.facebook.FacebookException) r0
            r14 = r0
            goto L_0x0010
        L_0x000a:
            com.facebook.FacebookException r1 = new com.facebook.FacebookException
            r1.<init>((java.lang.Throwable) r0)
            r14 = r1
        L_0x0010:
            r15 = 0
            r3 = -1
            r4 = -1
            r5 = -1
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = 0
            r12 = 0
            r2 = r16
            r13 = r17
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.FacebookRequestError.<init>(java.net.HttpURLConnection, java.lang.Exception):void");
    }

    public FacebookRequestError(int i, String str, String str2) {
        this(-1, i, -1, str, str2, (String) null, (String) null, (JSONObject) null, (JSONObject) null, (Object) null, (HttpURLConnection) null, (FacebookException) null, false);
    }

    public String toString() {
        String str = "{HttpStatus: " + this.requestStatusCode + ", errorCode: " + this.errorCode + ", subErrorCode: " + this.subErrorCode + ", errorType: " + this.errorType + ", errorMessage: " + getErrorMessage() + "}";
        Intrinsics.checkNotNullExpressionValue(str, "StringBuilder(\"{HttpStat…(\"}\")\n        .toString()");
        return str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        Intrinsics.checkNotNullParameter(parcel, "out");
        parcel.writeInt(this.requestStatusCode);
        parcel.writeInt(this.errorCode);
        parcel.writeInt(this.subErrorCode);
        parcel.writeString(this.errorType);
        parcel.writeString(getErrorMessage());
        parcel.writeString(this.errorUserTitle);
        parcel.writeString(this.errorUserMessage);
    }

    private FacebookRequestError(Parcel parcel) {
        this(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), (JSONObject) null, (JSONObject) null, (Object) null, (HttpURLConnection) null, (FacebookException) null, false);
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Range getHTTP_RANGE_SUCCESS$facebook_core_release() {
            return FacebookRequestError.HTTP_RANGE_SUCCESS;
        }

        /* JADX WARNING: Removed duplicated region for block: B:48:0x00ce A[Catch:{ JSONException -> 0x0123 }] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final com.facebook.FacebookRequestError checkResponseAndCreateError(org.json.JSONObject r20, java.lang.Object r21, java.net.HttpURLConnection r22) {
            /*
                r19 = this;
                r9 = r20
                java.lang.String r0 = "error_code"
                java.lang.String r1 = "error"
                java.lang.String r2 = "FACEBOOK_NON_JSON_RESULT"
                java.lang.String r3 = "body"
                java.lang.String r4 = "code"
                java.lang.String r5 = "singleResult"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r5)
                r15 = 0
                boolean r5 = r9.has(r4)     // Catch:{ JSONException -> 0x0123 }
                if (r5 == 0) goto L_0x0123
                int r5 = r9.getInt(r4)     // Catch:{ JSONException -> 0x0123 }
                java.lang.Object r6 = com.facebook.internal.Utility.getStringPropertyAsJSON(r9, r3, r2)     // Catch:{ JSONException -> 0x0123 }
                if (r6 == 0) goto L_0x00ef
                boolean r7 = r6 instanceof org.json.JSONObject     // Catch:{ JSONException -> 0x0123 }
                if (r7 == 0) goto L_0x00ef
                r7 = r6
                org.json.JSONObject r7 = (org.json.JSONObject) r7     // Catch:{ JSONException -> 0x0123 }
                boolean r7 = r7.has(r1)     // Catch:{ JSONException -> 0x0123 }
                r8 = 1
                java.lang.String r10 = "error_subcode"
                r11 = 0
                r12 = -1
                if (r7 == 0) goto L_0x0082
                r0 = r6
                org.json.JSONObject r0 = (org.json.JSONObject) r0     // Catch:{ JSONException -> 0x0123 }
                java.lang.Object r0 = com.facebook.internal.Utility.getStringPropertyAsJSON(r0, r1, r15)     // Catch:{ JSONException -> 0x0123 }
                org.json.JSONObject r0 = (org.json.JSONObject) r0     // Catch:{ JSONException -> 0x0123 }
                if (r0 == 0) goto L_0x0046
                java.lang.String r1 = "type"
                java.lang.String r1 = r0.optString(r1, r15)     // Catch:{ JSONException -> 0x0123 }
                goto L_0x0047
            L_0x0046:
                r1 = r15
            L_0x0047:
                if (r0 == 0) goto L_0x0050
                java.lang.String r7 = "message"
                java.lang.String r7 = r0.optString(r7, r15)     // Catch:{ JSONException -> 0x0123 }
                goto L_0x0051
            L_0x0050:
                r7 = r15
            L_0x0051:
                if (r0 == 0) goto L_0x0058
                int r4 = r0.optInt(r4, r12)     // Catch:{ JSONException -> 0x0123 }
                goto L_0x0059
            L_0x0058:
                r4 = r12
            L_0x0059:
                if (r0 == 0) goto L_0x005f
                int r12 = r0.optInt(r10, r12)     // Catch:{ JSONException -> 0x0123 }
            L_0x005f:
                if (r0 == 0) goto L_0x0068
                java.lang.String r10 = "error_user_msg"
                java.lang.String r10 = r0.optString(r10, r15)     // Catch:{ JSONException -> 0x0123 }
                goto L_0x0069
            L_0x0068:
                r10 = r15
            L_0x0069:
                if (r0 == 0) goto L_0x0072
                java.lang.String r13 = "error_user_title"
                java.lang.String r13 = r0.optString(r13, r15)     // Catch:{ JSONException -> 0x0123 }
                goto L_0x0073
            L_0x0072:
                r13 = r15
            L_0x0073:
                if (r0 == 0) goto L_0x007b
                java.lang.String r14 = "is_transient"
                boolean r11 = r0.optBoolean(r14, r11)     // Catch:{ JSONException -> 0x0123 }
            L_0x007b:
                r14 = r11
                r11 = r8
                r8 = r7
                r7 = r12
                r12 = r4
            L_0x0080:
                r4 = r1
                goto L_0x00cc
            L_0x0082:
                r1 = r6
                org.json.JSONObject r1 = (org.json.JSONObject) r1     // Catch:{ JSONException -> 0x0123 }
                boolean r1 = r1.has(r0)     // Catch:{ JSONException -> 0x0123 }
                java.lang.String r4 = "error_reason"
                java.lang.String r7 = "error_msg"
                if (r1 != 0) goto L_0x00a9
                r1 = r6
                org.json.JSONObject r1 = (org.json.JSONObject) r1     // Catch:{ JSONException -> 0x0123 }
                boolean r1 = r1.has(r7)     // Catch:{ JSONException -> 0x0123 }
                if (r1 != 0) goto L_0x00a9
                r1 = r6
                org.json.JSONObject r1 = (org.json.JSONObject) r1     // Catch:{ JSONException -> 0x0123 }
                boolean r1 = r1.has(r4)     // Catch:{ JSONException -> 0x0123 }
                if (r1 == 0) goto L_0x00a2
                goto L_0x00a9
            L_0x00a2:
                r14 = r11
                r7 = r12
                r4 = r15
                r8 = r4
                r10 = r8
                r13 = r10
                goto L_0x00cc
            L_0x00a9:
                r1 = r6
                org.json.JSONObject r1 = (org.json.JSONObject) r1     // Catch:{ JSONException -> 0x0123 }
                java.lang.String r1 = r1.optString(r4, r15)     // Catch:{ JSONException -> 0x0123 }
                r4 = r6
                org.json.JSONObject r4 = (org.json.JSONObject) r4     // Catch:{ JSONException -> 0x0123 }
                java.lang.String r4 = r4.optString(r7, r15)     // Catch:{ JSONException -> 0x0123 }
                r7 = r6
                org.json.JSONObject r7 = (org.json.JSONObject) r7     // Catch:{ JSONException -> 0x0123 }
                int r0 = r7.optInt(r0, r12)     // Catch:{ JSONException -> 0x0123 }
                r7 = r6
                org.json.JSONObject r7 = (org.json.JSONObject) r7     // Catch:{ JSONException -> 0x0123 }
                int r7 = r7.optInt(r10, r12)     // Catch:{ JSONException -> 0x0123 }
                r12 = r0
                r14 = r11
                r10 = r15
                r13 = r10
                r11 = r8
                r8 = r4
                goto L_0x0080
            L_0x00cc:
                if (r11 == 0) goto L_0x00ef
                com.facebook.FacebookRequestError r16 = new com.facebook.FacebookRequestError     // Catch:{ JSONException -> 0x0123 }
                r11 = r6
                org.json.JSONObject r11 = (org.json.JSONObject) r11     // Catch:{ JSONException -> 0x0123 }
                r17 = 0
                r18 = 0
                r0 = r16
                r1 = r5
                r2 = r12
                r3 = r7
                r5 = r8
                r6 = r13
                r7 = r10
                r8 = r11
                r9 = r20
                r10 = r21
                r11 = r22
                r12 = r17
                r13 = r14
                r14 = r18
                r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14)     // Catch:{ JSONException -> 0x0123 }
                return r16
            L_0x00ef:
                com.facebook.FacebookRequestError$Range r0 = r19.getHTTP_RANGE_SUCCESS$facebook_core_release()     // Catch:{ JSONException -> 0x0123 }
                boolean r0 = r0.contains(r5)     // Catch:{ JSONException -> 0x0123 }
                if (r0 != 0) goto L_0x0123
                com.facebook.FacebookRequestError r16 = new com.facebook.FacebookRequestError     // Catch:{ JSONException -> 0x0123 }
                boolean r0 = r9.has(r3)     // Catch:{ JSONException -> 0x0123 }
                if (r0 == 0) goto L_0x0109
                java.lang.Object r0 = com.facebook.internal.Utility.getStringPropertyAsJSON(r9, r3, r2)     // Catch:{ JSONException -> 0x0123 }
                org.json.JSONObject r0 = (org.json.JSONObject) r0     // Catch:{ JSONException -> 0x0123 }
                r8 = r0
                goto L_0x010a
            L_0x0109:
                r8 = r15
            L_0x010a:
                r13 = 0
                r14 = 0
                r2 = -1
                r3 = -1
                r4 = 0
                r6 = 0
                r7 = 0
                r10 = 0
                r12 = 0
                r0 = r16
                r1 = r5
                r5 = r6
                r6 = r7
                r7 = r10
                r9 = r20
                r10 = r21
                r11 = r22
                r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14)     // Catch:{ JSONException -> 0x0123 }
                return r16
            L_0x0123:
                return r15
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.FacebookRequestError.Companion.checkResponseAndCreateError(org.json.JSONObject, java.lang.Object, java.net.HttpURLConnection):com.facebook.FacebookRequestError");
        }

        public final synchronized FacebookRequestErrorClassification getErrorClassification() {
            FetchedAppSettings appSettingsWithoutQuery = FetchedAppSettingsManager.getAppSettingsWithoutQuery(FacebookSdk.getApplicationId());
            if (appSettingsWithoutQuery == null) {
                return FacebookRequestErrorClassification.Companion.getDefaultErrorClassification();
            }
            return appSettingsWithoutQuery.getErrorClassification();
        }
    }
}
