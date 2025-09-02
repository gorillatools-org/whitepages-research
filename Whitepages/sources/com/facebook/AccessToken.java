package com.facebook;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.amplitude.reactnative.AmplitudeReactNativeModule;
import com.facebook.LegacyTokenHelper;
import com.facebook.internal.Utility;
import com.facebook.internal.Validate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class AccessToken implements Parcelable {
    public static final Parcelable.Creator<AccessToken> CREATOR = new AccessToken$Companion$CREATOR$1();
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final AccessTokenSource DEFAULT_ACCESS_TOKEN_SOURCE = AccessTokenSource.FACEBOOK_APPLICATION_WEB;
    private static final Date DEFAULT_EXPIRATION_TIME;
    private static final Date DEFAULT_LAST_REFRESH_TIME = new Date();
    private static final Date MAX_DATE;
    private final String applicationId;
    private final Date dataAccessExpirationTime;
    private final Set declinedPermissions;
    private final Set expiredPermissions;
    private final Date expires;
    private final String graphDomain;
    private final Date lastRefresh;
    private final Set permissions;
    private final AccessTokenSource source;
    private final String token;
    private final String userId;

    public interface AccessTokenRefreshCallback {
        void OnTokenRefreshFailed(FacebookException facebookException);

        void OnTokenRefreshed(AccessToken accessToken);
    }

    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(9:0|1|2|3|4|5|6|7|9) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        static {
            /*
                com.facebook.AccessTokenSource[] r0 = com.facebook.AccessTokenSource.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.facebook.AccessTokenSource r1 = com.facebook.AccessTokenSource.FACEBOOK_APPLICATION_WEB     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                com.facebook.AccessTokenSource r1 = com.facebook.AccessTokenSource.CHROME_CUSTOM_TAB     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                com.facebook.AccessTokenSource r1 = com.facebook.AccessTokenSource.WEB_VIEW     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.AccessToken.WhenMappings.<clinit>():void");
        }
    }

    public int describeContents() {
        return 0;
    }

    public final Date getExpires() {
        return this.expires;
    }

    public final Set getPermissions() {
        return this.permissions;
    }

    public final Set getDeclinedPermissions() {
        return this.declinedPermissions;
    }

    public final Set getExpiredPermissions() {
        return this.expiredPermissions;
    }

    public final String getToken() {
        return this.token;
    }

    public final AccessTokenSource getSource() {
        return this.source;
    }

    public final Date getLastRefresh() {
        return this.lastRefresh;
    }

    public final String getApplicationId() {
        return this.applicationId;
    }

    public final String getUserId() {
        return this.userId;
    }

    public final Date getDataAccessExpirationTime() {
        return this.dataAccessExpirationTime;
    }

    public final String getGraphDomain() {
        return this.graphDomain;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ AccessToken(java.lang.String r14, java.lang.String r15, java.lang.String r16, java.util.Collection r17, java.util.Collection r18, java.util.Collection r19, com.facebook.AccessTokenSource r20, java.util.Date r21, java.util.Date r22, java.util.Date r23, java.lang.String r24, int r25, kotlin.jvm.internal.DefaultConstructorMarker r26) {
        /*
            r13 = this;
            r0 = r25
            r0 = r0 & 1024(0x400, float:1.435E-42)
            if (r0 == 0) goto L_0x000a
            java.lang.String r0 = "facebook"
            r12 = r0
            goto L_0x000c
        L_0x000a:
            r12 = r24
        L_0x000c:
            r1 = r13
            r2 = r14
            r3 = r15
            r4 = r16
            r5 = r17
            r6 = r18
            r7 = r19
            r8 = r20
            r9 = r21
            r10 = r22
            r11 = r23
            r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.AccessToken.<init>(java.lang.String, java.lang.String, java.lang.String, java.util.Collection, java.util.Collection, java.util.Collection, com.facebook.AccessTokenSource, java.util.Date, java.util.Date, java.util.Date, java.lang.String, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public AccessToken(String str, String str2, String str3, Collection collection, Collection collection2, Collection collection3, AccessTokenSource accessTokenSource, Date date, Date date2, Date date3, String str4) {
        HashSet hashSet;
        HashSet hashSet2;
        HashSet hashSet3;
        Intrinsics.checkNotNullParameter(str, "accessToken");
        Intrinsics.checkNotNullParameter(str2, "applicationId");
        Intrinsics.checkNotNullParameter(str3, "userId");
        Validate.notEmpty(str, "accessToken");
        Validate.notEmpty(str2, "applicationId");
        Validate.notEmpty(str3, "userId");
        this.expires = date == null ? DEFAULT_EXPIRATION_TIME : date;
        if (collection == null) {
            hashSet = new HashSet();
        }
        Set unmodifiableSet = Collections.unmodifiableSet(hashSet);
        Intrinsics.checkNotNullExpressionValue(unmodifiableSet, "unmodifiableSet(if (perm…missions) else HashSet())");
        this.permissions = unmodifiableSet;
        if (collection2 == null) {
            hashSet2 = new HashSet();
        }
        Set unmodifiableSet2 = Collections.unmodifiableSet(hashSet2);
        Intrinsics.checkNotNullExpressionValue(unmodifiableSet2, "unmodifiableSet(\n       …missions) else HashSet())");
        this.declinedPermissions = unmodifiableSet2;
        if (collection3 == null) {
            hashSet3 = new HashSet();
        }
        Set unmodifiableSet3 = Collections.unmodifiableSet(hashSet3);
        Intrinsics.checkNotNullExpressionValue(unmodifiableSet3, "unmodifiableSet(\n       …missions) else HashSet())");
        this.expiredPermissions = unmodifiableSet3;
        this.token = str;
        this.source = convertTokenSourceForGraphDomain(accessTokenSource == null ? DEFAULT_ACCESS_TOKEN_SOURCE : accessTokenSource, str4);
        this.lastRefresh = date2 == null ? DEFAULT_LAST_REFRESH_TIME : date2;
        this.applicationId = str2;
        this.userId = str3;
        this.dataAccessExpirationTime = (date3 == null || date3.getTime() == 0) ? DEFAULT_EXPIRATION_TIME : date3;
        this.graphDomain = str4 == null ? "facebook" : str4;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{AccessToken");
        sb.append(" token:");
        sb.append(tokenToString());
        appendPermissions(sb);
        sb.append("}");
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "builder.toString()");
        return sb2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AccessToken)) {
            return false;
        }
        AccessToken accessToken = (AccessToken) obj;
        if (Intrinsics.areEqual((Object) this.expires, (Object) accessToken.expires) && Intrinsics.areEqual((Object) this.permissions, (Object) accessToken.permissions) && Intrinsics.areEqual((Object) this.declinedPermissions, (Object) accessToken.declinedPermissions) && Intrinsics.areEqual((Object) this.expiredPermissions, (Object) accessToken.expiredPermissions) && Intrinsics.areEqual((Object) this.token, (Object) accessToken.token) && this.source == accessToken.source && Intrinsics.areEqual((Object) this.lastRefresh, (Object) accessToken.lastRefresh) && Intrinsics.areEqual((Object) this.applicationId, (Object) accessToken.applicationId) && Intrinsics.areEqual((Object) this.userId, (Object) accessToken.userId) && Intrinsics.areEqual((Object) this.dataAccessExpirationTime, (Object) accessToken.dataAccessExpirationTime)) {
            String str = this.graphDomain;
            String str2 = accessToken.graphDomain;
            if (str == null ? str2 == null : Intrinsics.areEqual((Object) str, (Object) str2)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int hashCode = (((((((((((((((((((527 + this.expires.hashCode()) * 31) + this.permissions.hashCode()) * 31) + this.declinedPermissions.hashCode()) * 31) + this.expiredPermissions.hashCode()) * 31) + this.token.hashCode()) * 31) + this.source.hashCode()) * 31) + this.lastRefresh.hashCode()) * 31) + this.applicationId.hashCode()) * 31) + this.userId.hashCode()) * 31) + this.dataAccessExpirationTime.hashCode()) * 31;
        String str = this.graphDomain;
        return hashCode + (str != null ? str.hashCode() : 0);
    }

    public final boolean isExpired() {
        return new Date().after(this.expires);
    }

    public final JSONObject toJSONObject$facebook_core_release() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("version", 1);
        jSONObject.put("token", this.token);
        jSONObject.put("expires_at", this.expires.getTime());
        jSONObject.put("permissions", new JSONArray(this.permissions));
        jSONObject.put("declined_permissions", new JSONArray(this.declinedPermissions));
        jSONObject.put("expired_permissions", new JSONArray(this.expiredPermissions));
        jSONObject.put("last_refresh", this.lastRefresh.getTime());
        jSONObject.put("source", this.source.name());
        jSONObject.put("application_id", this.applicationId);
        jSONObject.put(AmplitudeReactNativeModule.USER_ID_KEY, this.userId);
        jSONObject.put("data_access_expiration_time", this.dataAccessExpirationTime.getTime());
        String str = this.graphDomain;
        if (str != null) {
            jSONObject.put("graph_domain", str);
        }
        return jSONObject;
    }

    private final String tokenToString() {
        if (FacebookSdk.isLoggingBehaviorEnabled(LoggingBehavior.INCLUDE_ACCESS_TOKENS)) {
            return this.token;
        }
        return "ACCESS_TOKEN_REMOVED";
    }

    private final void appendPermissions(StringBuilder sb) {
        sb.append(" permissions:");
        sb.append("[");
        sb.append(TextUtils.join(", ", this.permissions));
        sb.append("]");
    }

    private final AccessTokenSource convertTokenSourceForGraphDomain(AccessTokenSource accessTokenSource, String str) {
        if (str == null || !str.equals("instagram")) {
            return accessTokenSource;
        }
        int i = WhenMappings.$EnumSwitchMapping$0[accessTokenSource.ordinal()];
        if (i == 1) {
            return AccessTokenSource.INSTAGRAM_APPLICATION_WEB;
        }
        if (i == 2) {
            return AccessTokenSource.INSTAGRAM_CUSTOM_CHROME_TAB;
        }
        if (i != 3) {
            return accessTokenSource;
        }
        return AccessTokenSource.INSTAGRAM_WEB_VIEW;
    }

    public AccessToken(Parcel parcel) {
        AccessTokenSource accessTokenSource;
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        this.expires = new Date(parcel.readLong());
        ArrayList arrayList = new ArrayList();
        parcel.readStringList(arrayList);
        Set unmodifiableSet = Collections.unmodifiableSet(new HashSet(arrayList));
        Intrinsics.checkNotNullExpressionValue(unmodifiableSet, "unmodifiableSet(HashSet(permissionsList))");
        this.permissions = unmodifiableSet;
        arrayList.clear();
        parcel.readStringList(arrayList);
        Set unmodifiableSet2 = Collections.unmodifiableSet(new HashSet(arrayList));
        Intrinsics.checkNotNullExpressionValue(unmodifiableSet2, "unmodifiableSet(HashSet(permissionsList))");
        this.declinedPermissions = unmodifiableSet2;
        arrayList.clear();
        parcel.readStringList(arrayList);
        Set unmodifiableSet3 = Collections.unmodifiableSet(new HashSet(arrayList));
        Intrinsics.checkNotNullExpressionValue(unmodifiableSet3, "unmodifiableSet(HashSet(permissionsList))");
        this.expiredPermissions = unmodifiableSet3;
        this.token = Validate.notNullOrEmpty(parcel.readString(), "token");
        String readString = parcel.readString();
        if (readString != null) {
            accessTokenSource = AccessTokenSource.valueOf(readString);
        } else {
            accessTokenSource = DEFAULT_ACCESS_TOKEN_SOURCE;
        }
        this.source = accessTokenSource;
        this.lastRefresh = new Date(parcel.readLong());
        this.applicationId = Validate.notNullOrEmpty(parcel.readString(), "applicationId");
        this.userId = Validate.notNullOrEmpty(parcel.readString(), "userId");
        this.dataAccessExpirationTime = new Date(parcel.readLong());
        this.graphDomain = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        Intrinsics.checkNotNullParameter(parcel, "dest");
        parcel.writeLong(this.expires.getTime());
        parcel.writeStringList(new ArrayList(this.permissions));
        parcel.writeStringList(new ArrayList(this.declinedPermissions));
        parcel.writeStringList(new ArrayList(this.expiredPermissions));
        parcel.writeString(this.token);
        parcel.writeString(this.source.name());
        parcel.writeLong(this.lastRefresh.getTime());
        parcel.writeString(this.applicationId);
        parcel.writeString(this.userId);
        parcel.writeLong(this.dataAccessExpirationTime.getTime());
        parcel.writeString(this.graphDomain);
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final AccessToken getCurrentAccessToken() {
            return AccessTokenManager.Companion.getInstance().getCurrentAccessToken();
        }

        public final void setCurrentAccessToken(AccessToken accessToken) {
            AccessTokenManager.Companion.getInstance().setCurrentAccessToken(accessToken);
        }

        public final boolean isCurrentAccessTokenActive() {
            AccessToken currentAccessToken = AccessTokenManager.Companion.getInstance().getCurrentAccessToken();
            return currentAccessToken != null && !currentAccessToken.isExpired();
        }

        public final void expireCurrentAccessToken() {
            AccessToken currentAccessToken = AccessTokenManager.Companion.getInstance().getCurrentAccessToken();
            if (currentAccessToken != null) {
                setCurrentAccessToken(createExpired$facebook_core_release(currentAccessToken));
            }
        }

        public final AccessToken createExpired$facebook_core_release(AccessToken accessToken) {
            Intrinsics.checkNotNullParameter(accessToken, "current");
            return new AccessToken(accessToken.getToken(), accessToken.getApplicationId(), accessToken.getUserId(), accessToken.getPermissions(), accessToken.getDeclinedPermissions(), accessToken.getExpiredPermissions(), accessToken.getSource(), new Date(), new Date(), accessToken.getDataAccessExpirationTime(), (String) null, 1024, (DefaultConstructorMarker) null);
        }

        public final AccessToken createFromLegacyCache$facebook_core_release(Bundle bundle) {
            String str;
            Bundle bundle2 = bundle;
            Intrinsics.checkNotNullParameter(bundle2, "bundle");
            List permissionsFromBundle$facebook_core_release = getPermissionsFromBundle$facebook_core_release(bundle2, "com.facebook.TokenCachingStrategy.Permissions");
            List permissionsFromBundle$facebook_core_release2 = getPermissionsFromBundle$facebook_core_release(bundle2, "com.facebook.TokenCachingStrategy.DeclinedPermissions");
            List permissionsFromBundle$facebook_core_release3 = getPermissionsFromBundle$facebook_core_release(bundle2, "com.facebook.TokenCachingStrategy.ExpiredPermissions");
            LegacyTokenHelper.Companion companion = LegacyTokenHelper.Companion;
            String applicationId = companion.getApplicationId(bundle2);
            if (Utility.isNullOrEmpty(applicationId)) {
                applicationId = FacebookSdk.getApplicationId();
            }
            String str2 = applicationId;
            String token = companion.getToken(bundle2);
            if (token == null) {
                return null;
            }
            JSONObject awaitGetGraphMeRequestWithCache = Utility.awaitGetGraphMeRequestWithCache(token);
            if (awaitGetGraphMeRequestWithCache != null) {
                try {
                    str = awaitGetGraphMeRequestWithCache.getString("id");
                } catch (JSONException unused) {
                    return null;
                }
            } else {
                str = null;
            }
            if (str2 == null || str == null) {
                return null;
            }
            return new AccessToken(token, str2, str, permissionsFromBundle$facebook_core_release, permissionsFromBundle$facebook_core_release2, permissionsFromBundle$facebook_core_release3, companion.getSource(bundle2), companion.getExpirationDate(bundle2), companion.getLastRefreshDate(bundle2), (Date) null, (String) null, 1024, (DefaultConstructorMarker) null);
        }

        public final List getPermissionsFromBundle$facebook_core_release(Bundle bundle, String str) {
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            ArrayList<String> stringArrayList = bundle.getStringArrayList(str);
            if (stringArrayList == null) {
                return CollectionsKt.emptyList();
            }
            List unmodifiableList = Collections.unmodifiableList(new ArrayList(stringArrayList));
            Intrinsics.checkNotNullExpressionValue(unmodifiableList, "{\n            Collection…Permissions))\n          }");
            return unmodifiableList;
        }

        public final AccessToken createFromJSONObject$facebook_core_release(JSONObject jSONObject) {
            Collection collection;
            Intrinsics.checkNotNullParameter(jSONObject, "jsonObject");
            if (jSONObject.getInt("version") <= 1) {
                String string = jSONObject.getString("token");
                Date date = new Date(jSONObject.getLong("expires_at"));
                JSONArray jSONArray = jSONObject.getJSONArray("permissions");
                JSONArray jSONArray2 = jSONObject.getJSONArray("declined_permissions");
                JSONArray optJSONArray = jSONObject.optJSONArray("expired_permissions");
                Date date2 = new Date(jSONObject.getLong("last_refresh"));
                String string2 = jSONObject.getString("source");
                Intrinsics.checkNotNullExpressionValue(string2, "jsonObject.getString(SOURCE_KEY)");
                AccessTokenSource valueOf = AccessTokenSource.valueOf(string2);
                String string3 = jSONObject.getString("application_id");
                String string4 = jSONObject.getString(AmplitudeReactNativeModule.USER_ID_KEY);
                Date date3 = new Date(jSONObject.optLong("data_access_expiration_time", 0));
                String optString = jSONObject.optString("graph_domain", (String) null);
                Intrinsics.checkNotNullExpressionValue(string, "token");
                Intrinsics.checkNotNullExpressionValue(string3, "applicationId");
                Intrinsics.checkNotNullExpressionValue(string4, "userId");
                Intrinsics.checkNotNullExpressionValue(jSONArray, "permissionsArray");
                Collection jsonArrayToStringList = Utility.jsonArrayToStringList(jSONArray);
                Intrinsics.checkNotNullExpressionValue(jSONArray2, "declinedPermissionsArray");
                Collection jsonArrayToStringList2 = Utility.jsonArrayToStringList(jSONArray2);
                if (optJSONArray == null) {
                    collection = new ArrayList();
                } else {
                    collection = Utility.jsonArrayToStringList(optJSONArray);
                }
                return new AccessToken(string, string3, string4, jsonArrayToStringList, jsonArrayToStringList2, collection, valueOf, date, date2, date3, optString);
            }
            throw new FacebookException("Unknown AccessToken serialization format.");
        }
    }

    static {
        Date date = new Date(Long.MAX_VALUE);
        MAX_DATE = date;
        DEFAULT_EXPIRATION_TIME = date;
    }
}
